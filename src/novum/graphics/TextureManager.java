package novum.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL30.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

public class TextureManager
{
	static int TEXTURE_UNIT = GL_TEXTURE0;
	
	int characterOneID;
	int characterTwoID;
	
	String characterOneLocation;
	String characterTwoLocation;
	
	public TextureManager(String characterOneLocation, String characterTwoLocation)
	{
		this.characterOneLocation = characterOneLocation;
		this.characterTwoLocation = characterTwoLocation;
	}
	
	public void initialise()
	{
		characterOneID = loadTexture(characterOneLocation);
		characterTwoID = loadTexture(characterTwoLocation);
	}
	
	public void terminate()
	{
		glDeleteTextures(characterOneID);
		glDeleteTextures(characterTwoID);
	}
	
	public void bind(int index)
	{
		glActiveTexture(TEXTURE_UNIT);
		glBindTexture(GL_TEXTURE_2D, characterOneID);
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