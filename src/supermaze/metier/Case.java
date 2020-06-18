package supermaze.metier;


public class Case
{
    private boolean north;
    private boolean south;
    private boolean east;
    private boolean west;

    private int ligne;
    private int colonne;
    private int nbLigne;
    private int nbColonne;
    private Maze maze;

    int id;

    public Case(int id, int ligne, int colonne, int nbLigne, int nbColonne, Maze maze)
    {
        this.north = false;
        this.south = false;
        this.east = false;
        this.west = false;

        this.ligne = ligne;
        this.colonne = colonne;
        this.nbLigne = nbLigne;
        this.nbColonne = nbColonne;

        this.id = id;
        this.maze = maze;



    }

    public boolean isNorth()
    {
        return north;
    }

    public boolean isSouth()
    {
        return south;
    }

    public boolean isEast()
    {
        return east;
    }

    public boolean isWest()
    {
        return west;
    }

    public void setNorth(boolean north)
    {
        this.north = north;
    }

    public void setSouth(boolean south)
    {
        this.south = south;
    }

    public void setEast(boolean east)
    {
        this.east = east;
    }

    public void setWest(boolean west)
    {
        this.west = west;
    }

    public boolean open(char dir, Case[][] maze)
    {
        int oldId = -1;
        int newId = -1;
        switch(dir)
        {
            case 'N' :
                if(this.ligne != 0 && maze[ligne - 1][colonne].getId() != this.id)
                {
                    north = true;
                    maze[ligne - 1][colonne].setSouth(true);

                    if(maze[ligne - 1][colonne].getId() > this.id)
                    {
                        oldId = maze[ligne - 1][colonne].getId();
                        newId = this.id;
                    }
                    else
                    {
                        oldId = this.id;
                        newId = maze[ligne - 1][colonne].getId();
                    }
                }
                break;
            case 'S' :
                if(this.ligne != nbLigne - 1 && maze[ligne + 1][colonne].getId() != this.id)
                {
                    south = true;
                    maze[ligne + 1][colonne].setNorth(true);

                    if(maze[ligne + 1][colonne].getId() > this.id)
                    {
                        oldId = maze[ligne + 1][colonne].getId();
                        newId = this.id;
                    }
                    else
                    {
                        oldId = this.id;
                        newId = maze[ligne + 1][colonne].getId();
                    }
                }
                break;
            case 'E' :
                if(this.colonne != nbColonne - 1 && maze[ligne][colonne + 1].getId() != this.id)
                {
                    east = true;
                    maze[ligne][colonne + 1].setWest(true);

                    if(maze[ligne][colonne + 1].getId() > this.id)
                    {
                        oldId = maze[ligne][colonne + 1].getId();
                        newId = this.id;
                    }
                    else
                    {
                        oldId = this.id;
                        newId = maze[ligne][colonne + 1].getId();
                    }
                }
                break;
            case 'W' :
                if(this.colonne != 0 && maze[ligne][colonne - 1].getId() != this.id)
                {
                    west = true;
                    maze[ligne][colonne - 1].setEast(true);

                    if(maze[ligne][colonne - 1].getId() > this.id)
                    {
                        oldId = maze[ligne][colonne - 1].getId();
                        newId = this.id;
                    }
                    else
                    {
                        oldId = this.id;
                        newId = maze[ligne][colonne - 1].getId();
                    }
                }
                break;
            default:
                break;
        }
        for(int i = 0; i < nbLigne; i++)
        {
            for(int j = 0; j < nbColonne; j++)
            {
                if(maze[i][j].getId() == oldId)
                    maze[i][j].setId(newId);
            }
        }
        return newId != -1;
    }

    public boolean estRelie()
    {
        return north || south || east || west;
    }

    public int getId()
    {
        return id;
    }

    private void setId(int id)
    {
        this.id = id;
    }

    public boolean canBeOpen(Case[][] maze)
    {
        return (this.ligne != 0 && (!north && maze[ligne - 1][colonne].getId() != this.id)) ||
                (this.ligne != nbLigne - 1 && (!south && maze[ligne + 1][colonne].getId() != this.id)) ||
                (this.colonne != 0 && (!west && maze[ligne][colonne - 1].getId() != this.id)) ||
                (this.colonne != this.nbColonne - 1 && (!east && maze[ligne][colonne + 1].getId() != this.getId()));
    }

    public int getColonne()
    {
        return colonne;
    }

    public int getLigne()
    {
        return ligne;
    }

    public boolean canWalk(Entity personnage, int deltaX, int deltaY, int width)
    {
        Case[][] lab = maze.getMaze();
        int tailleCase = maze.getTailleCase();
        int widthX;
        int widthY;
        int posX = personnage.getPosX();
        int posY = personnage.getPosY();

        width = width / 2;
        widthX = deltaX > 0 ? width : -width;
        widthY = deltaY > 0 ? width : -width;
        posX += widthX;
        posY += widthY;

        int xCase = (posX - colonne * tailleCase) + deltaX;
        int yCase = (posY - ligne * tailleCase) + deltaY;

        if(xCase > tailleCase && (!lab[ligne][colonne].isEast()) && deltaX != 0)
                return false;
        if(xCase < 50 && !lab[ligne][colonne].isWest())
            return false;
        if(yCase > tailleCase && !lab[ligne][colonne].isSouth())
            return false;
        if(yCase < 100 && !lab[ligne][colonne].isNorth())
            return false;

        return true;
    }
}
