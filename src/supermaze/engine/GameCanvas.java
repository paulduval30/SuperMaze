package supermaze.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GameCanvas extends JPanel implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener
{
	private final GameEngine engine;
	private Input input;
	
	public GameCanvas(GameEngine engine)
	{
		this.engine = engine;
		this.input = this.engine.getInput();
		
		this.setFocusable(true);
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		Camera camera = this.engine.getCamera();
		Graphics2D g2d = (Graphics2D) g;
		GameGraphics gg = new GameGraphics(camera, g2d);
		
		Screen screen = this.engine.getScreen();
		if (screen != null)
			screen.render(this.engine, gg);
	}
	
	public void update()
	{
		this.input.update();
	}
	
	@Override
	public void keyTyped(KeyEvent e)
	{
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		this.input.setKey(e.getKeyCode(), true);
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		this.input.setKey(e.getKeyCode(), false);
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		this.input.setMouseButton(e.getButton(), true);
		this.input.setMousePos(e.getX(), e.getY());
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		this.input.setMouseButton(e.getButton(), false);
		this.input.setMousePos(e.getX(), e.getY());
	}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
		this.input.setMousePos(e.getX(), e.getY());
	}
	
	@Override
	public void mouseExited(MouseEvent e)
	{
		this.input.setMousePos(e.getX(), e.getY());
	}
	
	@Override
	public void mouseDragged(MouseEvent e)
	{
		this.input.setMouseButton(e.getButton(), true);
		this.input.setMousePos(e.getX(), e.getY());
	}
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		this.input.setMousePos(e.getX(), e.getY());
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e)
	{
	
	}
}
