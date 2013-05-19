package novum;

import novum.graphics.Graphics;

public class Game
{
	Graphics graphics;
	
	boolean isRunning = false;
	
	public static void main(String[] args)
	{
		Game game = new Game();
		
		game.initialise();
		game.run();
	}
	
	public void initialise()
	{
		graphics = new Graphics();
		
		graphics.initialise();
		
		isRunning = true;
	}
	
	public void run()
	{
		while(isRunning)
		{
			if(graphics.isCloseRequested())
			{
				terminate();
			}
			
			update();
		}
	}
	
	public void update()
	{
		graphics.update();
	}
	
	public void terminate()
	{
		graphics.terminate();
		System.exit(0);
	}
}