package novum.graphics;

import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class VertexArray
{
	int objectID;
	
	public void initialise()
	{
		objectID = glGenVertexArrays();
	}
	
	public void terminate()
	{
		this.bind();
		this.disableAttributes();
		this.free();
		
		glDeleteVertexArrays(objectID);
	}
	
	public void bind()
	{
		glBindVertexArray(objectID);
	}
	
	public void free()
	{
		glBindVertexArray(0);
	}
	
	public void enableAttributes()
	{
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
	}
	
	public void disableAttributes()
	{
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(2);
	}
}