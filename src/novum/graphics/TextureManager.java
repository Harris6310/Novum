package novum.graphics;

import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_LINEAR_MIPMAP_LINEAR;
import static org.lwjgl.opengl.GL11.GL_REPEAT;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.GL_UNPACK_ALIGNMENT;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDeleteTextures;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glPixelStorei;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

public class TextureManager
{
	static int TEXTURE_UNIT = GL_TEXTURE0;
	static String CHARACTER_LOCATION = "res/characters/";
	
	int[] characters;
	
	public void initialise()
	{
		characters = new int[2];
		
		characters[0] = loadTexture(CHARACTER_LOCATION + "001-Fighter01.png");
		characters[1] = loadTexture(CHARACTER_LOCATION + "002-Fighter02.png");
	}
	
	public void terminate()
	{
		glDeleteTextures(characters[0]);
		glDeleteTextures(characters[1]);
	}
	
	public void bind(int index)
	{
		glActiveTexture(TEXTURE_UNIT);
		glBindTexture(GL_TEXTURE_2D, characters[index]);
	}
	
	public int loadTexture(String location)
	{
		ByteBuffer byteBuffer = null;
		int width = 0;
		int height = 0;
		
		try
		{
			InputStream inputStream = new FileInputStream(location);
			PNGDecoder pngDecoder = new PNGDecoder(inputStream);
			
			width = pngDecoder.getWidth();
			height = pngDecoder.getHeight();
			
			byteBuffer = ByteBuffer.allocateDirect(width * height * 4);
			
			pngDecoder.decode(byteBuffer, width * 4, Format.RGBA);
			byteBuffer.flip();
			inputStream.close();
		}
		catch (IOException exception)
		{
			exception.printStackTrace();
		}
		
		int textureID = glGenTextures();
		
		glActiveTexture(TEXTURE_UNIT);
		glBindTexture(GL_TEXTURE_2D, textureID);
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, byteBuffer);
		glGenerateMipmap(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
		
		return textureID;
	}
}