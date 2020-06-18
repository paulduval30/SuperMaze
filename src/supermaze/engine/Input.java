package supermaze.engine;

import java.util.HashMap;

public class Input
{
	private HashMap<Integer, Boolean> keys;
	private HashMap<Integer, Boolean> oldKeys;
	
	private HashMap<Integer, Boolean> mouseButtons;
	private HashMap<Integer, Boolean> oldMouseButtons;
	
	private int oldMouseX;
	private int oldMouseY;
	private int mouseX;
	private int mouseY;
	
	public Input()
	{
		this.keys = new HashMap<>();
		this.oldKeys = new HashMap<>();
		
		this.mouseButtons = new HashMap<>();
		this.oldMouseButtons = new HashMap<>();
	}
	
	public void setKey(int key, boolean state)
	{
		this.keys.put(key, state);
	}
	
	public void setMouseButton(int button, boolean state)
	{
		this.mouseButtons.put(button, state);
	}
	
	public void setMousePos(int x, int y)
	{
		this.mouseX = x;
		this.mouseY = y;
	}
	
	public boolean isKeyPressed(int key)
	{
		return this.keys.getOrDefault(key, false) && !this.oldKeys.getOrDefault(key, false);
	}
	
	public boolean isKeyDown(int key)
	{
		return this.keys.getOrDefault(key, false);
	}
	
	public boolean isKeyReleased(int key)
	{
		return !this.keys.getOrDefault(key, false) && this.oldKeys.getOrDefault(key, false);
	}
	
	public boolean isMousePressed(int button)
	{
		return this.mouseButtons.getOrDefault(button, false) && !this.oldMouseButtons.getOrDefault(button, false);
	}
	
	public boolean isMouseDown(int button)
	{
		return this.mouseButtons.getOrDefault(button, false);
	}
	
	public boolean isMouseReleased(int button)
	{
		return !this.mouseButtons.getOrDefault(button, false) && this.oldMouseButtons.getOrDefault(button, false);
	}
	
	public int getMouseX()
	{
		return this.mouseX;
	}
	
	public int getMouseDX()
	{
		return this.mouseX - this.oldMouseX;
	}
	
	public int getMouseY()
	{
		return this.mouseY;
	}
	
	public int getMouseDY()
	{
		return this.mouseY - this.oldMouseY;
	}
	
	public void update()
	{
		this.oldMouseX = this.mouseX;
		this.oldMouseY = this.mouseY;
		
		for (int key : this.keys.keySet())
			this.oldKeys.put(key, this.keys.get(key));
		
		for (int button : this.mouseButtons.keySet())
			this.oldMouseButtons.put(button, this.mouseButtons.get(button));
	}
}
