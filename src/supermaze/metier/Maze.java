package supermaze.metier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maze
{
    public static char[] DIR = {'N','S','E','W'};
    private Case[][] maze;
    private List<Case> nonTraite;
    private int[][] matriceCoup;
    private boolean generer;
    private int tailleCase;

    private ArrayList<Entity> entities;
    private ArrayList<int[]> chemin;

    public Maze(int ligne, int colonne)
    {
        matriceCoup = new int[ligne][colonne];
        maze = new Case[ligne][colonne];
        nonTraite = new ArrayList<>();
        for(int i = 0; i < ligne; i ++)
        {
            for (int j = 0; j < colonne; j++)
            {
                matriceCoup[i][j] = 100000;
                maze[i][j] = new Case(i * colonne + j, i, j, ligne, colonne, this);
                nonTraite.add(maze[i][j]);
            }
        }

        this.genererLabyrinthe();
        this.genererCheminSortie();
        this.matriceCoup = genererMatriceCoup(matriceCoup,0,0,0);
        for(int[] l : matriceCoup)
        {
            System.out.println(Arrays.toString(l));
        }
    }

    public void genererCheminSortie()
    {
        this.chemin = new ArrayList<>();
        int[][] matriceCoup = new int[maze.length][maze[0].length];
        for (int i = 0; i < matriceCoup.length; i++)
        {
            for (int j = 0; j < matriceCoup[0].length; j++)
                matriceCoup[i][j] = 10000000;
        }
        matriceCoup = this.genererMatriceCoup(matriceCoup, 0, 0, 0);
        int ligne = matriceCoup.length - 1;
        int colonne = matriceCoup[0].length - 1;
        int nouvelleLigne = 0;
        int nouvelleColonne = 0;
        int minimum = 10000000;
        int cpt = 0;
        while (this.estGenerer() && (ligne != 0 || colonne != 0))
        {
            if (ligne > 0)
            {
                if (matriceCoup[ligne - 1][colonne] < minimum && this.maze[ligne][colonne].isNorth())
                {
                    nouvelleLigne = ligne - 1;
                    minimum = matriceCoup[ligne - 1][colonne];
                    ligne = nouvelleLigne;
                    this.chemin.add(new int[]{nouvelleLigne, colonne});
                    continue;
                }
            }

            if (ligne < matriceCoup.length - 1)
            {
                if (matriceCoup[ligne + 1][colonne] < minimum && this.maze[ligne][colonne].isSouth())
                {
                    nouvelleLigne = ligne + 1;
                    minimum = matriceCoup[ligne + 1][colonne];
                    ligne = nouvelleLigne;
                    this.chemin.add(new int[]{nouvelleLigne, colonne});
                    continue;

                }
            }

            if (colonne > 0)
            {
                if (matriceCoup[ligne][colonne - 1] < minimum && this.maze[ligne][colonne].isWest())
                {
                    nouvelleColonne = colonne - 1;
                    minimum = matriceCoup[ligne][colonne - 1];
                    colonne = nouvelleColonne;
                    this.chemin.add(new int[]{ligne, nouvelleColonne});
                    continue;


                }
            }
            if (colonne < matriceCoup[0].length - 1)
            {
                if (matriceCoup[ligne][colonne + 1] < minimum && this.maze[ligne][colonne].isEast())
                {
                    nouvelleColonne = colonne + 1;
                    minimum = matriceCoup[ligne][colonne + 1];
                    colonne = nouvelleColonne;
                    this.chemin.add(new int[]{ligne, nouvelleColonne});
                    continue;


                }
            }
            minimum = 1000000000;
        }
    }


    public void genererLabyrinthe()
    {
        int random;
        int dir;
        Case c;
        boolean open;

        while(sommeLab() != 0)
        {
            random =(int)(Math.random() * (nonTraite.size() - 1));
            c = nonTraite.get(random);
            dir = (int)(Math.random() * 4);
            open = c.open(DIR[dir], maze);
            dir = 0;
            while(!open)
            {
                dir = dir + 1;
                if(dir == 4)
                {
                    nonTraite.remove(c);
                    break;
                }
                open = c.open(DIR[dir], maze);
            }
        }
        this.matriceCoup = this.genererMatriceCoup(matriceCoup,0,0,0);
        this.generer = true;
    }

    public int[][] genererMatriceCoup(int[][] matriceCoup, int id, int ligneArr, int colArr)
    {

        matriceCoup[ligneArr][colArr] = id;
        id++;
        if ((ligneArr - 1) >= 0 && matriceCoup[ligneArr - 1][colArr] > id && maze[ligneArr][colArr].isNorth())
        {
            this.genererMatriceCoup(matriceCoup, id, ligneArr - 1, colArr);
        }

        if (ligneArr + 1 < this.maze.length && maze[ligneArr][colArr].isSouth()
                && matriceCoup[ligneArr + 1][colArr] > id)
        {
            this.genererMatriceCoup(matriceCoup, id, ligneArr + 1, colArr);
        }

        if (colArr - 1 >= 0 && maze[ligneArr][colArr].isWest()
                && matriceCoup[ligneArr][colArr - 1] > id)
        {
            this.genererMatriceCoup(matriceCoup, id, ligneArr, colArr - 1);
        }

        if (colArr + 1 < this.maze.length && maze[ligneArr][colArr].isEast()
                && matriceCoup[ligneArr][colArr + 1] > id)
        {
            this.genererMatriceCoup(matriceCoup, id, ligneArr, colArr + 1);
        }
        return matriceCoup;
    }

    public Case[][] getMaze()
    {
        return maze;
    }

    public int[][] getMatriceCoup()
    {
        return matriceCoup;
    }
    private int sommeLab()
    {
        int somme = 0;
        for(int i = 0; i < maze.length; i++)
            for(int j = 0; j < maze[0].length; j++)
                somme += maze[i][j].id;
        return somme;

    }

    public boolean estGenerer()
    {
        return this.generer;
    }

    public void setTailleCase(int tailleCase)
    {
        this.tailleCase = tailleCase;
    }

    public int getTailleCase()
    {
        return tailleCase;
    }

    public ArrayList<int[]> getChemin()
    {
        return chemin;
    }
}
