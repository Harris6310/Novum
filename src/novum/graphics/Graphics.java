package novum.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

public class Graphics
{
	Window window;
	ShaderProgram shaderProgram;
	TextureManager textureManager;
	
	VertexArray vertexArray;
	IndexBuffer indexBuffer;
	VertexBuffer vertexBuffer;
	
	public void initialise()
	{
		window = new Window(640, 480);
		shaderProgram = new ShaderProgram("shaders/vertex.shader", "shaders/fragment.shader");
		textureManager = new TextureManager("res/characters/001-Fighter01.png", "res/characters/002-Fighter02.png");
		
		vertexArray = new VertexArray();
		indexBuffer = new IndexBuffer(0, 1, 2, 2, 3, 0);
		vertexBuffer = new VertexBuffer(0f, 0f, 1f, 1f, 1f, 1f, 1f, 1f);
		
		window.initialise();
		
		glClearColor(1f, 1f, 1f, 0f);
		glViewport(0, 0, window.getWidth(), window.getHeight());
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		shaderProgram.initialise();
		textureManager.initialise();
		
		vertexArray.initialise();
		vertexArray.bind();
		vertexBuffer.initialise();
		vertexArray.free();
		indexBuffer.initialise();
	}
	
	public void update()
	{
		glClear(GL_COLOR_BUFFER_BIT);
		
		textureManager.bind(0);
		
		vertexArray.bind();
		vertexArray.enableAttributes();
		indexBuffer.bind();
		
		glDrawElements(GL_TRIANGLES, indexBuffer.getLength(), GL_UNSIGNED_BYTE, 0);
		
		indexBuffer.free();
		vertexArray.disableAttributes();
		vertexArray.free();
		
		window.update();
	}
	
	public void terminate()
	{
		indexBuffer.terminate();
		vertexArray.bind();
		vertexBuffer.terminate();
		vertexArray.free();
		vertexArray.terminate();
		
		textureManager.terminate();
		shaderProgram.terminate();
		window.terminate();
	}
	
	public boolean isCloseRequested()
	{
		return window.isCloseRequested();
	}
}