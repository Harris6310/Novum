package novum.graphics;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glViewport;

public class Graphics
{
	Window window;
	ShaderProgram shaderProgram;
	
	public void initialise()
	{
		window = new Window(640, 480);
		shaderProgram = new ShaderProgram("shaders/vertex.shader", "shaders/fragment.shader");
		
		window.initialise();
		
		glClearColor(1f, 1f, 1f, 0f);
		glViewport(0, 0, window.getWidth(), window.getHeight());
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		shaderProgram.initialise();
	}
	
	public void update()
	{
		glClear(GL_COLOR_BUFFER_BIT);
		
		window.update();
	}
	
	public void terminate()
	{
		shaderProgram.terminate();
		window.terminate();
	}
	
	public boolean isCloseRequested()
	{
		return window.isCloseRequested();
	}
}