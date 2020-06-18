import supermaze.engine.GameEngine;
import supermaze.metier.Maze;
import supermaze.metier.Model;
import supermaze.screen.LabScreen;

import javax.swing.*;

public class Main
{
    public static void main(String[] argv)
    {
        SwingUtilities.invokeLater(() ->
        {
            GameEngine engine = new GameEngine("Game editor", 1280, 720);
            Model model = new Model(100,100);
            for(int i  = 0; i < 1; i++)
            {
                model.getPopulation().createPersonnage();
            }
            engine.setScreen(new LabScreen(model));
            engine.start();
        });
    }

}
