package supermaze.engine;

public class Camera
{
	private float x;
	private float y;
	private float width;
	private float height;
	
	public Camera()
	{
	
	}
	
	public float getX()
	{
		return this.x;
	}
	
	public void setX(float x)
	{
		this.x = x;
	}
	
	public float getY()
	{
		return this.y;
	}
	
	public void setY(float y)
	{
		this.y = y;
	}
	
	public float getWidth()
	{
		return this.width;
	}
	
	public void setWidth(float width)
	{
		this.width = width;
	}
	
	public float getHeight()
	{
		return this.height;
	}
	
	public void setHeight(float height)
	{
		this.height = height;
	}

	public void translate (float x, float y)
	{
		this.x += x;
		this.y += y;
	}
}
