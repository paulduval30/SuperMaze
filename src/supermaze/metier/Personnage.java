package supermaze.metier;

import supermaze.Component.LabyrinthModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Personnage extends Entity implements Runnable
{
    private int[][] vision;
    private int[] genome;
    private ArrayList<int[]> chemin;
    private int nbMoove;
    private ArrayList<int[]> road;
    private boolean ready;
    private int score;
    int[] currentCell;
    private int id;

    private boolean alive;

    public Personnage(Maze maze, int x, int y, int id)
    {
        super(maze, 0,0,null,0,0, "Personnage");
        this.currentCell = new int[]{0,0};
        this.score = 0;
        this.id = id;
        this.posX = LabyrinthModel.CELL_SIZE / 4;
        this.posY = LabyrinthModel.CELL_SIZE / 4;
        this.nbMoove = 0;
        this.chemin = new ArrayList<>();
//        this.vision = new int[maze.getMaze().length][maze.getMaze()[0].length];
        this.alive = true;

//        for(int i = 0; i < vision.length; i++)
//        {
//            for(int j = 0; j < vision[0].length; j++)
//            {
//                this.vision[i][j] = 1;
//            }
//        }
        this.chemin = maze.getChemin();
        this.road = new ArrayList<>();
    }

    public void setGenome(int[] genome)
    {
        this.genome = genome;
        ArrayList<int[]> road = new ArrayList<>();
        road.add(this.getCurrentCell());
        for (int i = 0; i < genome.length; i++)
        {
            int deltaX = 0;
            int deltaY = 0;

            if (genome[i] == 0)
            {
                deltaY = -1;
            }
            if (genome[i] == 1)
            {

                deltaY = 1;
            }
            if (genome[i] == 2)
            {
                deltaX = -1;
            }
            if (genome[i] == 3)
            {
                deltaX = 1;
            }
            this.road.add(new int[]{deltaY, deltaX});
        }
        this.ready = true;
    }
    public void createGenome(int size)
    {
        this.genome = new int[size];
        ArrayList<int[]> road = new ArrayList<>();
        for (int i = 0; i < size; i++)
        {
            genome[i] = (int) (Math.random() * 4);
        }
        setGenome(genome);

    }

    public ArrayList<int[]> getRoad()
    {
        return this.road;
    }

    public boolean isAlive()
    {
        return alive;
    }

    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }

    public int[] getCurrentCell()
    {
        return this.currentCell;
    }

    public int distanceFromEnd()
    {
        int[][] matriceCoup = maze.getMatriceCoup();
        this.score = matriceCoup[this.getCurrentCell()[0]][this.getCurrentCell()[1]];
        return score;
    }

    public boolean canMove(int deltaX, int deltaY)
    {

        if(deltaX == 1)
        {
            return this.maze.getMaze()[currentCell[0]][currentCell[1]].isEast();
        }
        if(deltaX == -1)
        {
            return this.maze.getMaze()[currentCell[0]][currentCell[1]].isWest();
        }
        if(deltaY == 1)
        {
            return this.maze.getMaze()[currentCell[0]][currentCell[1]].isSouth();
        }
        if(deltaY == -1)
        {
            return this.maze.getMaze()[currentCell[0]][currentCell[1]].isNorth();
        }

        return false;
    }
    @Override
    public void deplacer(int deltaX, int deltaY)
    {
        if(!canMove(deltaX > 0 ? 1 : 0, deltaY > 0 ? 1 : 0))
            return;
        this.posX += deltaX;
        this.posY += deltaY;
        this.currentCell[0] += deltaX > 0 ? 1 : 0;
        this.currentCell[1] += deltaY > 0 ? 1 : 0;

    }

    public void nextCell()
    {

    }

    public int getLigneMaze()
    {
        return ligneMaze;
    }

    public void setLigneMaze(int ligneMaze)
    {
        this.ligneMaze = ligneMaze;
    }

    public int getColonneMaze()
    {
        return colonneMaze;
    }

    public void setColonneMaze(int colonneMaze)
    {
        this.colonneMaze = colonneMaze;
    }

    @Override
    public void updateCurrentCell()
    {
    }



    private void updateVision()
    {
        for(int i = 0; i < vision.length; i++){
            for(int j = 0; j < vision[0].length; j++)
            {
                this.vision[i][j] = 0;
            }
        }

        this.vision[ligneMaze][colonneMaze] = 1;

        Case[][] maze = this.maze.getMaze();
        if(this.ligneMaze != 0)
        {
            if (maze[ligneMaze - 1][colonneMaze].isSouth())
            {
                this.vision[ligneMaze - 1][colonneMaze] = 1;
                if (colonneMaze != 0)
                    if(maze[ligneMaze - 1][colonneMaze - 1].isEast())
                        vision[ligneMaze - 1][colonneMaze - 1] = 2;
                if(colonneMaze < maze[0].length - 1)
                    if(maze[ligneMaze - 1][colonneMaze + 1].isWest())
                        vision[ligneMaze - 1][colonneMaze + 1] = 3;
            }
        }

        if(this.ligneMaze < maze.length - 1)
        {
            if (maze[ligneMaze + 1][colonneMaze].isNorth())
            {
                this.vision[ligneMaze + 1][colonneMaze] = 1;
                if (colonneMaze != 0)
                    if(maze[ligneMaze + 1][colonneMaze - 1].isEast())
                        vision[ligneMaze + 1][colonneMaze - 1] = 4;
                if(colonneMaze < maze[0].length - 1)
                    if(maze[ligneMaze + 1][colonneMaze + 1].isWest())
                        vision[ligneMaze + 1][colonneMaze + 1] = 5;
            }


        }

        if(this.colonneMaze > 0)
            if(maze[ligneMaze][colonneMaze].isWest())
            {
                this.vision[ligneMaze][colonneMaze - 1] = 1;
                if(ligneMaze != 0)
                    if(maze[ligneMaze - 1][colonneMaze - 1].isSouth())
                        this.vision[ligneMaze - 1][colonneMaze - 1] = 6;
                if(ligneMaze < vision.length - 1)
                    if(maze[ligneMaze + 1][colonneMaze - 1].isNorth())
                        this.vision[ligneMaze + 1][colonneMaze - 1] = 7;
            }
        if(colonneMaze < maze[0].length - 1)
            if(maze[ligneMaze][colonneMaze].isEast())
            {
                vision[ligneMaze][colonneMaze + 1] = 1;
                if(ligneMaze != 0)
                    if(maze[ligneMaze - 1][colonneMaze + 1].isSouth())
                        this.vision[ligneMaze - 1][colonneMaze + 1] = 8;
                if(ligneMaze < vision.length - 1)
                    if(maze[ligneMaze + 1][colonneMaze + 1].isNorth())
                        this.vision[ligneMaze + 1][colonneMaze + 1] = 9;
            }
    }

    public ArrayList<int[]> getChemin()
    {
        return this.chemin;
    }

    public int[][] getVision()
    {
        return vision;
    }

    @Override
    public void run()
    {

    }

    public boolean isReady()
    {
        return ready;
    }

    public int[] getGenome()
    {
        return genome;
    }

    public int getNbMoove()
    {
        return nbMoove;
    }

    public void nextMoove()
    {
        nbMoove++;
    }

    public void resetMoove()
    {
        this.nbMoove = 0;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public void setCurrentCell(int[] currentCell)
    {
        this.currentCell = currentCell;
    }

    public int getId()
    {
        return id;
    }
}
