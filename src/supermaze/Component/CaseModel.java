package supermaze.Component;

import supermaze.engine.Component;
import supermaze.engine.GameEngine;
import supermaze.engine.GameGraphics;
import supermaze.metier.Case;

public class CaseModel extends Component
{
    private Case c;
    boolean display;
    private int size;
    private int posX;
    private int posY;

    public CaseModel(Case c)
    {
        this.c = c;
    }
    @Override
    public void init()
    {

    }

    @Override
    public void render(GameEngine engine, GameGraphics gg)
    {
            if(!c.isNorth())
                gg.drawLine(this.posX, this.posY,
                        this.posX + size, posY);
            if(!c.isSouth())
                gg.drawLine(this.posX, this.posY +  size,
                        this.size + this.posX, posY +  size);
            if(!c.isWest())
                gg.drawLine(this.posX, this.posY,
                        this.posX, this.posY + size);
            if(!c.isEast())
                gg.drawLine(this.posX + size, this.posY,
                        this.posX + size, this.posY + size );
    }

    @Override
    public void update(GameEngine engine)
    {
        this.size = LabyrinthModel.CELL_SIZE;
        this.posX = c.getColonne() * size;
        this.posY = c.getLigne() * size;

        int cameraX = (int) engine.getCamera().getX();
        int cameraY = (int) engine.getCamera().getY();

        this.display = posX >= cameraX && posX <= cameraX + engine.getCamera().getWidth() * 4
                && posY >= cameraY && cameraY <= cameraY + engine.getCamera().getHeight() * 4;
    }
}
