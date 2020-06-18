package supermaze.tools;

import supermaze.Component.LabyrinthModel;
import supermaze.engine.GameEngine;
import supermaze.metier.Maze;
import supermaze.metier.Personnage;

import java.util.ArrayList;
import java.util.Arrays;

public class Autotravel implements Runnable
{
    private final Maze maze;
    private GameEngine engine;
    private Personnage personnage;
    private int size;
    private boolean running;

    public Autotravel(Personnage personnage, Maze m)
    {
        this.maze = m;
        this.engine = engine;
        this.personnage = personnage;
        this.size = LabyrinthModel.CELL_SIZE;
    }

    public boolean isRunning()
    {
        return running;
    }

    public void setRunning(boolean running)
    {
        this.running = running;
    }

    public void setEngine(GameEngine engine)
    {
        this.engine = engine;
    }

    @Override
    public void run()
    {
        System.out.println("Starting " + personnage.getId());
        this.running = true;
        ArrayList<int[]> road = personnage.getRoad();
        for (int[] c : road)
        {
            try
            {
                Thread.sleep(1000/10);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            personnage.deplacer(c[1] * LabyrinthModel.CELL_SIZE, c[0]* LabyrinthModel.CELL_SIZE);
            System.out.println(personnage.canMove(c[1],c[0])+ " " + c[1] + " " + c[0]);
            personnage.nextMoove();
            personnage.distanceFromEnd();

        }
        System.out.println(personnage.getId() + " : "  + Arrays.toString(personnage.getCurrentCell()));
    }
}
