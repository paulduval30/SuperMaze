package supermaze.metier;

public abstract class Entity
{
    protected Maze maze;
    protected int posX;
    protected int posY;
    protected Case currentCell;
    protected int ligneMaze;
    protected int colonneMaze;
    protected String type;

    public Entity(Maze maze, int posX, int posY, Case currentCell, int ligneMaze, int colonneMaze, String type)
    {
        this.maze = maze;
        this.posX = posX;
        this.posY = posY;
        this.currentCell = currentCell;
        this.ligneMaze = ligneMaze;
        this.colonneMaze = colonneMaze;
        this.type = type;
    }

    public int getPosX()
    {
        return posX;
    }

    public void setPosX(int posX)
    {
        this.posX = posX;
    }

    public int getPosY()
    {
        return posY;
    }

    public void setPosY(int posY)
    {
        this.posY = posY;
    }

    public abstract void deplacer(int deltaX, int deltaY);

    public void setLigneMaze(int ligneMaze)
    {
        this.ligneMaze = ligneMaze;
    }

    public void setColonneMaze(int colonneMaze)
    {
        this.colonneMaze = colonneMaze;
    }

    public void updateCurrentCell()
    {
        this.currentCell = this.maze.getMaze()[ligneMaze][colonneMaze];
    }

    public String getType()
    {
        return type;
    }

    public int getLigneMaze()
    {
        return ligneMaze;
    }

    public int getColonneMaze()
    {
        return colonneMaze;
    }
}
