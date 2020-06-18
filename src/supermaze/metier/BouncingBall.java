package supermaze.metier;

public class BouncingBall extends Entity
{
    private Maze maze;
    private int velX;
    private int velY;

    public BouncingBall(Maze maze, int posX, int posY)
    {
        super(maze, posX, posY, null, 0,0, "BouncingBall");
        this.velX = 1;
        this.velY = 1;
    }

    public Maze getMaze()
    {
        return maze;
    }

    public void setMaze(Maze maze)
    {
        this.maze = maze;
    }


    public int getVelX()
    {
        return velX;
    }

    public void setVelX(int velX)
    {
        this.velX = velX;
    }

    public int getVelY()
    {
        return velY;
    }

    public void setVelY(int velY)
    {
        this.velY = velY;
    }

    @Override
    public void deplacer(int deltaX, int deltaY)
    {
        if(!this.currentCell.canWalk(this, deltaX * velX, deltaY * velY, 50))
        {
            this.velX *= -1;
            this.velY *= -1;
        }
            this.posX += deltaX * velX;
            this.posY += deltaY * velY;


    }
}
