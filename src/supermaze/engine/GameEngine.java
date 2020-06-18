package supermaze.engine;

import javax.swing.*;
import java.awt.*;

public class GameEngine implements Runnable
{
	private final int MS_PER_SECOND = 1000 / 60;
	private GameCanvas canvas;
	private GameFrame window;
	
	private Camera camera;
	private Input input;
	
	private Screen screen;
	
	/**
	 * @param width Largeur de la fenêtre
	 * @param height Hauteur de la fenêtre
	 */
	public GameEngine(String title, int width, int height)
	{
		this.input = new Input();
		this.canvas = new GameCanvas(this);
		
		this.window = new GameFrame();
		this.window.setTitle(title);
		this.window.setContentPane(this.canvas);
		this.window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setWindowSize(width, height);
		
		this.camera = new Camera();
	}
	
	public int getWidth()
	{
		return this.canvas.getWidth();
	}
	
	public int getHeight()
	{
		return this.canvas.getHeight();
	}
	
	public Input getInput()
	{
		return this.input;
	}
	
	public Camera getCamera()
	{
		return this.camera;
	}
	
	public void setScreen(Screen screen)
	{
		if (screen != null)
			screen.init();
		this.screen = screen;
	}
	
	public Screen getScreen()
	{
		return this.screen;
	}
	
	/**
	 * Définit la taille de la fenêtre
	 * @param width  Largeur de la fenêtre
	 * @param height Hauteur de la fenêtre
	 */
	public void setWindowSize(int width, int height)
	{
		this.window.getContentPane().setPreferredSize(new Dimension(width, height));
		this.window.pack();
		this.window.setLocationRelativeTo(null);
	}
	
	/**
	 * Démarre le moteur de jeu
	 */
	public void start()
	{
		this.window.setVisible(true);
		
		new Thread(this).start();
	}
	
	@Override
	public void run()
	{
		long start = 0;
		long fps;
		double delta = 0.0;

		while (this.window.isVisible())
		{
			try
			{

//				start = System.currentTimeMillis();
				this.screen.update(this);
				this.canvas.update();
				this.canvas.repaint();
//
//				delta =  System.currentTimeMillis() - start  == 0 ? 1 :
//						1 - (double)(System.currentTimeMillis() - start) / (double)MS_PER_SECOND;
//				fps = MS_PER_SECOND - (System.currentTimeMillis() - start);
				Thread.sleep(1000/60);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
}
