package novum.graphics;

import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;

public class IndexBuffer
{
	int objectID;
	
	byte index1;
	byte index2;
	byte index3;
	byte index4;
	byte index5;
	byte index6;
	
	byte[] indices;
	
	ByteBuffer byteBuffer;
	
	public IndexBuffer(int index1, int index2, int index3, int index4, int index5, int index6)
	{
		this.index1 = (byte) index1;
		this.index2 = (byte) index2;
		this.index3 = (byte) index3;
		this.index4 = (byte) index4;
		this.index5 = (byte) index5;
		this.index6 = (byte) index6;
	}
	
	public void initialise()
	{
		indices = new byte[6];
		
		indices[0] = index1;
		indices[1] = index2;
		indices[2] = index3;
		indices[3] = index4;
		indices[4] = index5;
		indices[5] = index6;
		
		byteBuffer = BufferUtils.createByteBuffer(indices.length);
		
		byteBuffer.put(indices);
		byteBuffer.flip();
		
		objectID = glGenBuffers();
		
		this.bind();
		
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, byteBuffer, GL_STATIC_DRAW);
		
		this.free();
	}
	
	public void terminate()
	{
		this.free();
		
		glDeleteBuffers(objectID);
	}
	
	public void bind()
	{
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, objectID);
	}
	
	public void free()
	{
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	public int getLength()
	{
		return indices.length;
	}
}