package supermaze.screen;

import supermaze.Component.LabyrinthModel;
import supermaze.engine.GameEngine;
import supermaze.engine.GameGraphics;
import supermaze.engine.Screen;
import supermaze.metier.Model;
import supermaze.metier.Personnage;
import supermaze.tools.Autotravel;

import java.awt.event.KeyEvent;

public class LabScreen extends Screen
{
    private LabyrinthModel labyrinthModel;
    private Model m;
    private Autotravel a;

    public LabScreen(Model m)
    {
        this.m = m;
        this.labyrinthModel = new LabyrinthModel(m);
    }
    @Override
    public void init()
    {
        labyrinthModel.init();
        m.getPopulation().startGen();
    }

    @Override
    public void render(GameEngine engine, GameGraphics gg)
    {
        labyrinthModel.render(engine, gg);
        int best = 0;
        for(Personnage p : m.getPopulation().getPersonnages())
        {
            if(p.getScore() > best)
                best = p.getScore();
        }

        gg.drawString("SCORE : " + best, 0,0);

    }

    @Override
    public void update(GameEngine engine)
    {
        labyrinthModel.update(engine);
        if(engine.getInput().isKeyDown(KeyEvent.VK_LEFT))
        {
            engine.getCamera().translate(10, 0);
//            m.getPersonnage().deplacer(-10,0);
        }
        if(engine.getInput().isKeyDown(KeyEvent.VK_RIGHT))
        {
            engine.getCamera().translate(-10, 0);
//            m.getPersonnage().deplacer(10,0);
        }
        if(engine.getInput().isKeyDown(KeyEvent.VK_UP))
        {
            engine.getCamera().translate(0, 10);
//            m.getPersonnage().deplacer(0,-10);
        }
        if(engine.getInput().isKeyDown(KeyEvent.VK_DOWN))
        {
            engine.getCamera().translate(0, -10);
//            m.getPersonnage().deplacer(0,10);
        }
        if(engine.getInput().isKeyDown(KeyEvent.VK_F1))
        {
            if(!m.getPopulation().isReady())
                return;
            m.getPopulation().newGen();
            labyrinthModel.resetPop(m.getPopulation().getPersonnages());
            m.getPopulation().startGen();
        }
    }
}
