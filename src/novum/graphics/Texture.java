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
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glPixelStorei;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

public class Texture
{
	int objectID;
	int targetID;
	
	int width;
	int height;
	
	String fileLocation;
	
	public Texture(String fileLocation, int targetID)
	{
		this.fileLocation = fileLocation;
		this.targetID = targetID;
	}
	
	public void initialise()
	{
		try
		{
			InputStream inputStream = new FileInputStream(fileLocation);
			PNGDecoder pngDecoder = new PNGDecoder(inputStream);
			
			width = pngDecoder.getWidth();
			height = pngDecoder.getHeight();
			
			ByteBuffer byteBuffer = BufferUtils.createByteBuffer(width * height * 4);
			
			pngDecoder.decode(byteBuffer, width * 4, Format.RGBA);
			byteBuffer.flip();
			inputStream.close();
			
			glEnable(targetID);
			
			objectID = glGenTextures();
			
			this.bind();
			
			glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, byteBuffer);
		}
		catch (IOException exception)
		{
			exception.printStackTrace();
		}
	}
	
	public void bind()
	{
		glBindTexture(targetID, objectID);
	}
	
	public void terminate()
	{
		glDeleteTextures(objectID);
	}
}