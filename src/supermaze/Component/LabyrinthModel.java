package supermaze.Component;

import supermaze.engine.Component;
import supermaze.engine.GameEngine;
import supermaze.engine.GameGraphics;
import supermaze.metier.Case;
import supermaze.metier.Maze;
import supermaze.metier.Model;
import supermaze.metier.Personnage;

import java.util.ArrayList;

public class LabyrinthModel extends Component
{
    private Maze maze;
    private ArrayList<Component> toRender;
    private ArrayList<CharacterModel> characters;
    public static final int CELL_SIZE = 24;

    public LabyrinthModel(Model m)
    {
        this.maze = m.getMaze();
        this.toRender = new ArrayList<>();
        this.characters = new ArrayList<>();
        resetPop(m.getPersonnage());
    }

    @Override
    public void init()
    {
        for(Case[] ligne: this.maze.getMaze())
        {
            for(Case c : ligne)
            {
                this.toRender.add(new CaseModel(c));
            }
        }
    }

    @Override
    public void render(GameEngine engine, GameGraphics gg)
    {
        for(Component c : toRender)
        {
            c.render(engine, gg);
        }

        for(CharacterModel c : characters)
        {
            c.render(engine, gg);
        }
    }

    @Override
    public void update(GameEngine engine)
    {
        for(Component c : toRender)
            c.update(engine);
        for(CharacterModel c : characters)
            c.update(engine);
    }

    public void resetPop(ArrayList<Personnage> personnages)
    {
        this.characters = new ArrayList<>();
        for(Personnage p : personnages)
            this.characters.add(new CharacterModel(p, characters.size()));
    }
}
