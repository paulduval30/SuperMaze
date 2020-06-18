//package supermaze.IHM;
//
//import supermaze.metier.Maze;
//import supermaze.metier.Model;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
//public class SuperMaze implements Runnable
//{
//    private JFrame mainFrame;
//    private GraphicsMaze graphicsMaze;
//    private Maze maze;
//
//    private final int HEIGHT_WINDOW = 1000;
//    private final int WIDTH_WINDOW = 1000;
//
//    private JButton button;
//    private Model model;
//
//    private SuperMaze()
//    {
//        this.createModel();
//        this.createView();
//        this.placeComponents();
//        this.createController();
//        new Thread(this).start();
//    }
//
//    private void createController()
//    {
//        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        button.addActionListener(new ActionListener()
//        {
//            @Override
//            public void actionPerformed(ActionEvent e)
//            {
//                maze.genererLabyrinthe();
//                graphicsMaze.requestFocus();
//                refresh();
//            }
//        });
//
//        graphicsMaze.addMouseMotionListener(new MouseMotionListener()
//        {
//            @Override
//            public void mouseDragged(MouseEvent e)
//            {
//
//            }
//
//            @Override
//            public void mouseMoved(MouseEvent e)
//            {
//            }
//        });
//
//        graphicsMaze.addKeyListener(new KeyListener()
//        {
//            @Override
//            public void keyTyped(KeyEvent e)
//            {
//
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e)
//            {
////                if(e.getKeyCode() == KeyEvent.VK_RIGHT)
////                    model.getPersonnage().deplacer(10,0);
////                if(e.getKeyCode() == KeyEvent.VK_LEFT)
////                    model.getPersonnage().deplacer(-10,0);
////                if(e.getKeyCode() == KeyEvent.VK_UP)
////                    model.getPersonnage().deplacer(0,-10);
////                if(e.getKeyCode() == KeyEvent.VK_DOWN)
////                    model.getPersonnage().deplacer(0,10);
//
//                refresh();
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e)
//            {
//
//            }
//        });
//    }
//
//    private void placeComponents()
//    {
//        this.mainFrame.add(graphicsMaze, BorderLayout.CENTER);
//       // this.mainFrame.add(button, BorderLayout.SOUTH);
//    }
//
//    private void createView()
//    {
//        this.mainFrame = new JFrame();
//        this.mainFrame.setSize(WIDTH_WINDOW, HEIGHT_WINDOW);
//
//        this.graphicsMaze = new GraphicsMaze(model, mainFrame);
//        button = new JButton("go");
//    }
//
//    private void createModel()
//    {
//        this.model = new Model(10,10);
//        this.maze = model.getMaze();
//    }
//
//    private void display() {
//
//        mainFrame.setLocation(500,10);
//        this.graphicsMaze.setSize(new Dimension(this.mainFrame.getWidth(), this.mainFrame.getHeight()));
//
//        mainFrame.setVisible(true);
//        this.mainFrame.setFocusable(true);
//        this.graphicsMaze.requestFocus();
//        this.mainFrame.setResizable(false);
//    }
//
//    public void refresh()
//    {
//        this.graphicsMaze.repaint();
//        this.mainFrame.repaint();
//    }
//    public static void main (String argv[]) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new SuperMaze().display();
//            }
//        });
//    }
//
//    @Override
//    public void run()
//    {
//        while(true)
//        {
//
//            try
//            {
//                Thread.sleep(1000/60);
//            }
//            catch (InterruptedException e)
//            {
//                e.printStackTrace();
//            }
//
//            refresh();
//        }
//    }
//}
