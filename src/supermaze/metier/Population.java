package supermaze.metier;

import supermaze.Component.LabyrinthModel;
import supermaze.tools.Autotravel;
import supermaze.tools.DistanceComparator;

import java.util.ArrayList;

public class Population
{
    private int size;
    private ArrayList<Personnage> personnages;
    private Model model;
    private boolean ready;

    public Population(int size, Model model)
    {
        this.model = model;
        this.size = size;
        this.personnages = new ArrayList<>();
        this.ready = true;

    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public void createPersonnage()
    {
        Personnage personnage = new Personnage(model.getMaze(), LabyrinthModel.CELL_SIZE / 4,LabyrinthModel.CELL_SIZE / 4, personnages.size());
        personnage.createGenome(personnage.getChemin().size());
        this.personnages.add(personnage);
    }

    public void fitness()
    {
        this.personnages.sort(new DistanceComparator());
    }

    public void select()
    {
        fitness();
        int size = personnages.size();
        ArrayList<Personnage> sortPop = (ArrayList<Personnage>) personnages.clone();
        for(int i = 0; i < personnages.size() / 2; i++)
        {
            sortPop.remove(personnages.get(i));
        }
        personnages = sortPop;
    }

    public void newGen()
    {
        this.ready = false;
        System.out.println("BEFORE SELECTION " + personnages.size());

        select();
        System.out.println("AFTER SELECTION " + personnages.size());

        ArrayList<Personnage> newPop = new ArrayList<>();
        for(int i = 0; i < personnages.size(); i++)
        {
            int parent1 = (int)(Math.random() * (personnages.size() - 1));
            int parent2 = (int)(Math.random() * (personnages.size() - 1));

            newPop.add(crossover(personnages.get(parent1), personnages.get(parent2)));
        }

        for(Personnage p : personnages)
        {
            p.setPosX(LabyrinthModel.CELL_SIZE / 4);
            p.setPosY(LabyrinthModel.CELL_SIZE / 4);
            p.setScore(0);
            p.setCurrentCell(new int[]{0,0});

            newPop.add(p);
        }



        this.personnages = newPop;
        System.out.println("NEW GEN " + personnages.size());
        this.ready = true;

    }

    public void startGen()
    {
        System.out.println("NOUVELLE GENERATION : " + personnages.size());
        for(Personnage p : this.model.getPopulation().getPersonnages())
        {
            new Thread(new Autotravel(p, model.getMaze())).start();
        }
    }
    public ArrayList<Personnage> getPersonnages()
    {
        return personnages;
    }

    public Personnage crossover(Personnage p1, Personnage p2)
    {
        int[] genome1 = p1.getGenome();
        int[] genome2 = p2.getGenome();
        int[] newGenome = new int[genome1.length];

        for(int i = 0; i < genome1.length; i++)
        {
            double random = Math.random();
            if(random > 0.5)
            {
                newGenome[i] = genome1[i];
            }
            else
                newGenome[i] = genome2[i];
        }

        Personnage p = new Personnage(model.getMaze(), LabyrinthModel.CELL_SIZE / 4,LabyrinthModel.CELL_SIZE / 4, personnages.size());
        p.setGenome(mutate(newGenome));

        return p;
    }

    private int[] mutate(int[] newGenome)
    {
        int gene = (int)(Math.random() * (newGenome.length - 1));
        newGenome[gene] = (int) (Math.random() * 4);
        return newGenome;
    }

    public boolean isReady()
    {
        return ready;
    }
}
