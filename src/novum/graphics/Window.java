package novum.graphics;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

public class Window
{
	int width;
	int height;
	
	PixelFormat pixelFormat;
	ContextAttribs contextAttribs;
	
	public Window(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	public void initialise()
	{
		pixelFormat = new PixelFormat();
		contextAttribs = new ContextAttribs(3, 2);
		
		contextAttribs.withForwardCompatible(true);
		contextAttribs.withProfileCore(true);
		
		try
		{
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create(pixelFormat, contextAttribs);
		}
		catch(LWJGLException exception)
		{
			exception.printStackTrace();
		}
	}
	
	public void update()
	{
		Display.update();
		Display.sync(60);
	}
	
	public void terminate()
	{
		Display.destroy();
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public boolean isCloseRequested()
	{
		return Display.isCloseRequested();
	}
}