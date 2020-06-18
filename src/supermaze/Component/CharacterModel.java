package supermaze.Component;

import supermaze.engine.Component;
import supermaze.engine.GameEngine;
import supermaze.engine.GameGraphics;
import supermaze.engine.Input;
import supermaze.metier.Personnage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CharacterModel extends Component
{
    private Personnage personnage;
    private boolean selected;

    public CharacterModel(Personnage p, int number)
    {
        this.personnage = p;
    }

    @Override
    public void init()
    {
    }

    @Override
    public void render(GameEngine engine, GameGraphics gg)
    {
        gg.setColor(Color.BLACK);
        gg.fillRect(personnage.getCurrentCell()[1] * LabyrinthModel.CELL_SIZE, personnage.getCurrentCell()[0]* LabyrinthModel.CELL_SIZE,LabyrinthModel.CELL_SIZE / 4,LabyrinthModel.CELL_SIZE / 4);
        gg.setColor(Color.RED);
        gg.drawString(Arrays.toString(personnage.getCurrentCell()),personnage.getCurrentCell()[1] * LabyrinthModel.CELL_SIZE, personnage.getCurrentCell()[0]* LabyrinthModel.CELL_SIZE );
        gg.setColor(Color.BLACK);

    }

    @Override
    public void update(GameEngine engine)
    {

    }

    public Personnage getPersonnage()
    {
        return personnage;
    }
}
