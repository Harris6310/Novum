package novum.graphics;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STREAM_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class VertexBuffer
{
	int objectID;
	
	float x;
	float y;
	float width;
	float height;
	
	Vertex[] vertices;
	ByteBuffer byteBuffer;
	FloatBuffer floatBuffer;
	
	public VertexBuffer(float x, float y, float width, float height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void initialise()
	{
		vertices = new Vertex[4];
		
		vertices[0] = new Vertex(x, y + height, 0f, 1f, 1f, 1f, 1f, 1f, 0f, 0f);
		vertices[1] = new Vertex(x, y, 0f, 1f, 1f, 1f, 1f, 1f, 0f, 1f);
		vertices[2] = new Vertex(x + width, y, 0f, 1f, 1f, 1f, 1f, 1f, 1f, 1f);
		vertices[3] = new Vertex(x + width, y + height, 0f, 1f, 1f, 1f, 1f, 1f, 1f, 0f);
		
		byteBuffer = BufferUtils.createByteBuffer(vertices.length * Vertex.SIZE);
		floatBuffer = byteBuffer.asFloatBuffer();
		
		for(int i = 0; i < vertices.length; i++)
		{
			floatBuffer.put(vertices[i].getData());
		}
		
		floatBuffer.flip();
		
		objectID = glGenBuffers();
		
		this.bind();
		
		glBufferData(GL_ARRAY_BUFFER, floatBuffer, GL_STREAM_DRAW);
		glVertexAttribPointer(1, Vertex.POSITION_ELEMENT_COUNT, GL_FLOAT, false, Vertex.SIZE, Vertex.POSITION_LOCATION);
		glVertexAttribPointer(0, Vertex.COLOR_ELEMENT_COUNT, GL_FLOAT, false, Vertex.SIZE, Vertex.COLOR_LOCATION);
		glVertexAttribPointer(2, Vertex.TEXTURE_ELEMENT_COUNT, GL_FLOAT, false, Vertex.SIZE, Vertex.TEXTURE_LOCATION);
		
		this.free();
	}
	
	public void terminate()
	{
		this.free();
		
		glDeleteBuffers(objectID);
	}
	
	public void bind()
	{
		glBindBuffer(GL_ARRAY_BUFFER, objectID);
	}
	
	public void free()
	{
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
}