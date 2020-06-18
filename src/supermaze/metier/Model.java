package supermaze.metier;

import java.util.ArrayList;

public class Model
{
    private final ArrayList<Entity> entities;
    private Maze maze;
    private Population population;
    int ligne;
    int colonne;


    public Model(int ligne, int colonne)
    {
        this.maze = new Maze(ligne, colonne);
        this.entities = new ArrayList<>();
        this.population = new Population(500, this);
    }

    public Maze getMaze()
    {
        return maze;
    }

    public void setMaze(Maze maze)
    {
        this.maze = maze;
    }

    public ArrayList<Personnage> getPersonnage()
    {
        return population.getPersonnages();
    }

    public Personnage getLastPersonnage()
    {
        return this.population.getPersonnages().get(this.population.getPersonnages().size() - 1);
    }

    public ArrayList<Entity> getEntities()
    {
        return entities;
    }

    public Population getPopulation()
    {
        return population;
    }
}
