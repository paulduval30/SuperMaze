package supermaze.engine;

public abstract class Screen
{
	public abstract void init();
	public abstract void render(GameEngine engine, GameGraphics gg);
	public abstract void update(GameEngine engine);
}
