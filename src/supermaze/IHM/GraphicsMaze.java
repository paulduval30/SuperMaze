//package supermaze.IHM;
//
//import supermaze.metier.Case;
//import supermaze.metier.Entity;
//import supermaze.metier.Maze;
//import supermaze.metier.Model;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.geom.Point2D;
//import java.io.File;
//import java.io.IOException;
//
//public class GraphicsMaze extends JComponent
//{
//    private Maze maze;
//    private Model model;
//    private float x;
//    private float y;
//    private float radius;
//    private float velX;
//    private float velY;
//    private float deltaX;
//    private float deltaY;
//
//    private int oldTailleCase;
//    private int tailleCase;
//    private Image solPic;
//
//    private Image personnagePic;
//
//
//    public GraphicsMaze(Model model, JFrame mainFrame)
//    {
//        this.model = model;
//        this.maze = model.getMaze();
//        this.setSize(mainFrame.getSize());
//        oldTailleCase = (this.getWidth() < this.getHeight() ? this.getHeight() / 2 :
//                this.getWidth() / 2);
//        this.tailleCase = oldTailleCase;
////        this.x = model.getPersonnage().getPosX() + -1* model.getPersonnage().getX() + tailleCase / 2;
////        this.y = model.getPersonnage().getPosY() + -1* model.getPersonnage().getY()+ tailleCase / 2;
////        this.radius = this.tailleCase;
////        this.velY = -1;
////        this.velX = -1;
////
////        this.model.getPersonnage().setPosX(tailleCase / 2);
////        this.model.getPersonnage().setPosY(tailleCase / 2);
////
////        this.model.getPersonnage().setX(0);
////        this.model.getPersonnage().setY(0);
//
//        try
//        {
//            this.solPic = ImageIO.read(new File("res/sol.jpg"));
//            this.personnagePic = ImageIO.read(new File("res/fermier.png"));
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//
//    }
//
//
//    @Override
//    protected void paintComponent(Graphics g)
//    {
//        super.paintComponent(g);
//        g.fillRect(0,0, this.getWidth(), this.getHeight());
//        this.paintFloor(g);
//        this.paintMaze(g);
//        this.drawEntities(g);
//        this.drawLight(g);
//        this.drawShadows(g);
//    }
//
//    private void paintFloor(Graphics g)
//    {
//        int offsetX = -1 * this.model.getPersonnage().getX();
//        int offsetY = -1 * this.model.getPersonnage().getY();
//        Case[][] grid = this.maze.getMaze();
//
//        for(int i = 0; i < grid.length; i++)
//        {
//            for(int j = 0; j < grid[0].length; j++)
//            {
//                if(Math.abs(this.model.getPersonnage().getLigneMaze() - i) + Math.abs(this.model.getPersonnage().getColonneMaze() - j) > 2)
//                    continue;
//                    g.drawImage(solPic, (tailleCase / 2) + offsetX + tailleCase * j, (tailleCase / 2) + offsetY + tailleCase * i,
//                tailleCase - 1, tailleCase - 1, null);
//            }
//        }
//    }
//
//    private void drawShadows(Graphics g)
//    {
//        int offsetX = -1 * this.model.getPersonnage().getX();
//        int offsetY = -1 * this.model.getPersonnage().getY();
//        int[][] vision = this.model.getPersonnage().getVision();
//        Case[][] lab = maze.getMaze();
//        g.setColor(Color.BLACK);
//
//        for(int i = 0; i < vision.length; i++)
//        {
//            for(int j = 0; j < vision[0].length; j++)
//            {
//                g.setColor(new Color(0f,0f,0f,0.8f));
//                if(Math.abs(this.model.getPersonnage().getLigneMaze() - i) + Math.abs(this.model.getPersonnage().getColonneMaze() - j) > 2)
//                    continue;
//                if(vision[i][j] == 0)
//                {
//                    g.setColor(Color.BLACK);
//                    g.fillRect((tailleCase / 2) + offsetX + tailleCase * j, (tailleCase / 2) + offsetY + tailleCase * i +
//                                    (lab[i][j].isNorth() ? 0 : 20),
//                            tailleCase - (lab[i][j].isEast() ? 0 : 20),
//                            tailleCase - 20 + (lab[i][j].isNorth() ? 20 : 0));
//                }
//                if(vision[i][j] == 2)
//                {
//                    int x1 = (tailleCase / 2) + offsetX + tailleCase * (this.model.getPersonnage().getColonneMaze());
//                    int y1 = (tailleCase / 2) + offsetY + tailleCase * this.model.getPersonnage().getLigneMaze();
//                    int decalageX = this.model.getPersonnage().getPosX() - (tailleCase * this.model.getPersonnage().getColonneMaze());
//                    int decalageY = this.model.getPersonnage().getPosY() - (tailleCase * (this.model.getPersonnage().getLigneMaze()));
//                    double m = ( (double)decalageY / (double)decalageX);
//                    double q = (double)y1 - ( m * x1);
//                    int y2 =(int)q;
//
//                    g.fillPolygon(new Polygon(new int[]{x1 - 1,0,0}, new int[]{y1 + 1,y1 + 1,y2 + 1}, 3));
//
//                }
//                if(vision[i][j] == 3)
//                {
//                    int x1 = (tailleCase / 2) + tailleCase + offsetX + tailleCase * (this.model.getPersonnage().getColonneMaze())
//                            - (lab[i + 1][j].isWest() ? 0 : 20);
//                    int y1 = (tailleCase / 2) + offsetY + tailleCase * this.model.getPersonnage().getLigneMaze();
//                    int decalageX = (tailleCase * j) - this.model.getPersonnage().getPosX();
//                    int decalageY = this.model.getPersonnage().getPosY() - (tailleCase * i) - tailleCase;
//                    double m = ( (double)decalageY / (double)decalageX);
//                    double q = (double)y1 - ( m * x1);
//                    int y2 =(int)q;
//                    g.fillPolygon(new Polygon(new int[]{x1 - 1, this.getWidth(), this.getWidth()}, new int[]{y1 + 1,y1 + 1,y2 + 1}, 3));
//                }
//                if(vision[i][j] == 4)
//                {
//                    int x1 = (tailleCase / 2) + offsetX + tailleCase * (this.model.getPersonnage().getColonneMaze());
//                    int y1 = (tailleCase / 2) + offsetY + tailleCase * (this.model.getPersonnage().getLigneMaze() + 1);
//                    int decalageX = this.model.getPersonnage().getPosX() - (tailleCase * (this.model.getPersonnage().getColonneMaze()));
//                    int decalageY = (tailleCase * (this.model.getPersonnage().getLigneMaze() + 1)) - this.model.getPersonnage().getPosY();
//                    double m = ( (double)decalageY / (double)decalageX);
//                    double q = (double)y1 + ( m * x1);
//                    int y2 =(int)q;
//
//                    g.fillPolygon(new Polygon(new int[]{x1 - 1,0,0}, new int[]{y1 + 1,y1 + 1,y2 + 1}, 3));
//                }
//                if(vision[i][j] == 5)
//                {
//                    int x1 = (tailleCase / 2) + offsetX + tailleCase * (this.model.getPersonnage().getColonneMaze() + 1) -
//                    (maze.getMaze()[i - 1][j].isWest() ? 0 :  20);
//                    int y1 = (tailleCase / 2) + offsetY + tailleCase * (this.model.getPersonnage().getLigneMaze() + 1);
//                    int decalageX = (tailleCase * (this.model.getPersonnage().getColonneMaze() + 1)) - this.model.getPersonnage().getPosX();
//                    int decalageY = (tailleCase * (this.model.getPersonnage().getLigneMaze() + 1)) - this.model.getPersonnage().getPosY();
//                    double m = ( (double)decalageY / (double)decalageX);
//                    double q = (double)y1 + ( m * x1);
//                    int y2 =(int)q;
//
//
//                    g.fillPolygon(new Polygon(new int[]{x1 - 1,this.getWidth(),this.getWidth()}, new int[]{y1 + 1,y1 + 1,y2 + 1}, 3));
//                }
//                if(vision[i][j] == 6)
//                {
//                    int x1 = (tailleCase / 2) + offsetX + tailleCase * (this.model.getPersonnage().getColonneMaze()) -
//                            (maze.getMaze()[i][j + 1].isWest() ? 0 :  20);
//                    int y1 = (tailleCase / 2) + offsetY + tailleCase * (this.model.getPersonnage().getLigneMaze());
//                    int decalageX = this.model.getPersonnage().getPosX() - (tailleCase * (this.model.getPersonnage().getColonneMaze()));
//                    int decalageY = this.model.getPersonnage().getPosY() - (tailleCase * (this.model.getPersonnage().getLigneMaze()));
//                    double m = ( (double)decalageY / (double)decalageX);
//                    double q = (double)y1 - ( m * x1);
//                    int x2 =(int)( - q / m);
//
//                    g.fillPolygon(new Polygon(new int[]{x1 - 1,x1 - 1,x2}, new int[]{y1 + 1,0,0}, 3));
//                }
//                if(vision[i][j] == 7)
//                {
//                    int x1 = (tailleCase / 2) + offsetX + tailleCase * (this.model.getPersonnage().getColonneMaze())
//                            - (lab[i][j + 1].isWest() ? 0 : 20);
//                    int y1 = (tailleCase / 2) + offsetY + tailleCase * (this.model.getPersonnage().getLigneMaze() + 1);
//                    int decalageX = this.model.getPersonnage().getPosX() - (tailleCase * (this.model.getPersonnage().getColonneMaze()));
//                    int decalageY = (tailleCase * (this.model.getPersonnage().getLigneMaze() + 1)) - this.model.getPersonnage().getPosY();
//                    double m = ( (double)decalageY / (double)decalageX);
//                    double q = (double)y1 - ( m * x1);
//                    int x2 =(int)( - q / m);
//                    g.fillPolygon(new Polygon(new int[]{x1 - 1,x1 - 1,x2}, new int[]{y1 + 1,this.getHeight(),this.getHeight()}, 3));
//                }
//                if(vision[i][j] == 8)
//                {
//                    int x1 = (tailleCase / 2) + offsetX + tailleCase * (this.model.getPersonnage().getColonneMaze() + 1);
//                    int y1 = (tailleCase / 2) + offsetY + tailleCase * (this.model.getPersonnage().getLigneMaze()) +
//                            (lab[i + 1][j - 1].isNorth() ? 0 : 60);
//                    int decalageX = (tailleCase * (this.model.getPersonnage().getColonneMaze() + 1)) - this.model.getPersonnage().getPosX() ;
//                    int decalageY = this.model.getPersonnage().getPosY() - (tailleCase * (this.model.getPersonnage().getLigneMaze()));
//                    double m = ( (double)decalageY / (double)decalageX);
//                    double q = (double)y1 + ( m * x1);
//                    int x2 =(int)(q / m);
//
//                    g.fillPolygon(new Polygon(new int[]{x1 - 1,x1 - 1,x2}, new int[]{y1 + 1,0,0}, 3));
//                }
//                if(vision[i][j] == 9)
//                {
//                    int x1 = (tailleCase / 2) + offsetX + tailleCase * (this.model.getPersonnage().getColonneMaze() + 1);
//                    int y1 = (tailleCase / 2) + offsetY + tailleCase * (this.model.getPersonnage().getLigneMaze() + 1);
//                    int decalageX = (tailleCase * (this.model.getPersonnage().getColonneMaze() + 1)) - this.model.getPersonnage().getPosX() ;
//                    int decalageY = (tailleCase * (this.model.getPersonnage().getLigneMaze() + 1)) - this.model.getPersonnage().getPosY();
//                    double m = ( (double)decalageY / (double)decalageX);
//                    double q = (double)y1 + ( m * x1);
//                    int x2 =(int)(q / m);
//                    g.fillPolygon(new Polygon(new int[]{x1 - 1, x1 - 1 - 1,x2}, new int[]{y1 + 1,this.getHeight(),this.getHeight()}, 3));
//                }
//            }
//        }
//    }
//
//    private void drawLight(Graphics g)
//    {
//        Graphics2D g2d = (Graphics2D) g;
//        Point2D center = new Point2D.Float(x, y);
//        float[] distance = {0.0f, 0.5f};
//        Color[] colors = {new Color(96, 22, 0, 125 ), Color.BLACK};
//        RadialGradientPaint p = new RadialGradientPaint(center, radius, distance, colors);
//        g2d.setPaint(p);
//        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
//        g2d.fillRect(0,0,this.getWidth(), this.getHeight());
//
//    }
//
//    private void drawEntities(Graphics g)
//    {
//        for(Entity e : model.getEntities())
//        {
//            g.setColor(Color.GREEN);
//            e.setColonneMaze((int)Math.floor((double)this.model.getPersonnage().getPosX() / (double)tailleCase));
//            e.setLigneMaze((int)Math.floor((double)this.model.getPersonnage().getPosY() / (double)tailleCase));
//            e.updateCurrentCell();
//            if(e.getType().equals("Personnage"))
//            {
//                g.drawImage(personnagePic, e.getPosX() + -1*model.getPersonnage().getX() + tailleCase / 2 - tailleCase / 8,
//                        e.getPosY() + -1*model.getPersonnage().getY()+ tailleCase / 2 - tailleCase / 8, 50,50, null);
//            }
//            if(e.getType().equals("BouncingBall"))
//            {
//                e.deplacer(3,3);
//                g.setColor(Color.RED);
//                g.fillOval(e.getPosX() + -1*model.getPersonnage().getX() + tailleCase / 2,
//                        e.getPosY() + -1*model.getPersonnage().getY()+ tailleCase / 2, 50,50);
//            }
//
//        }
//    }
//
//    private void paintMaze(Graphics g)
//    {
//        Case[][] grid = this.maze.getMaze();
//        Case c;
//
//        tailleCase = (this.getWidth() < this.getHeight() ? this.getHeight() / 2 :
//        this.getWidth() / 2);
//        maze.setTailleCase(tailleCase);
//
///*
//        ArrayList<int[]> chemin = this.supermaze.metier.getPersonnage().getChemin();
//*/
//        int offsetX = -1 * this.model.getPersonnage().getX();
//        int offsetY = -1 * this.model.getPersonnage().getY();
//
//
//        for(int i = 0; i < grid.length; i++)
//        {
//            for(int j = 0; j < grid[0].length; j++)
//            {
//                if(Math.abs(this.model.getPersonnage().getLigneMaze() - i) + Math.abs(this.model.getPersonnage().getColonneMaze() - j) > 2)
//                    continue;
//                g.setColor(Color.WHITE);
//                ((Graphics2D) g).setStroke(new BasicStroke(3));
//
//                c = grid[i][j];
//                if(!c.isNorth())
//                    this.drawNorthWall(g, i, j);
//
//                if(!c.isSouth())
//                    g.drawLine((tailleCase / 2) + offsetX + tailleCase * j, (tailleCase / 2) + offsetY + tailleCase + tailleCase * i,
//                            (tailleCase / 2) + offsetX + tailleCase + tailleCase * j, (tailleCase / 2) + offsetY + tailleCase + tailleCase * i);
//                if(!c.isWest())
//                {
//                    this.drawSideWall(g,i,j,- 20);
//                    /*g.drawLine((tailleCase / 2) + offsetX + tailleCase * j, (tailleCase / 2) + offsetY + tailleCase * i,
//                            (tailleCase / 2) + offsetX + tailleCase * j, (tailleCase / 2) + offsetY + tailleCase + tailleCase * i);*/
//                }
//                if(!c.isEast())
//                {
//                    this.drawSideWall(g,i,j, tailleCase - 20);
//                    /*g.drawLine((tailleCase / 2) + offsetX + tailleCase + tailleCase * j, (tailleCase / 2) + offsetY + tailleCase * i,
//                            (tailleCase / 2) + offsetX + tailleCase + tailleCase * j, (tailleCase / 2) + offsetY + tailleCase + tailleCase * i);*/
//                }
//            }
//        }
//
//        this.radius = tailleCase * 1.3f ;
//        deltaX += velX;
//        deltaY += velY;
//        this.x = this.getWidth() / 2 + deltaX;
//        this.y = this.getHeight() / 2 + deltaY;
//
//        if(deltaX > 10 || deltaX < - 10)
//            velX *= -1;
//        if(deltaY > 10 || deltaY  < -10)
//            velY *= -1;
//    }
//
//    private void drawSideWall(Graphics g, int i, int j, int decallage)
//    {
//        int offsetX = -1 * this.model.getPersonnage().getX();
//        int offsetY = -1 * this.model.getPersonnage().getY();
//        g.setColor(Color.DARK_GRAY);
//        g.fillRect((tailleCase / 2) + offsetX + tailleCase * j + decallage, (tailleCase / 2) + offsetY + tailleCase * i,
//                20, tailleCase);
//    }
//
//    private void drawNorthWall(Graphics g, int i, int j)
//    {
//        int offsetX = -1 * this.model.getPersonnage().getX();
//        int offsetY = -1 * this.model.getPersonnage().getY();
//            g.setColor(Color.DARK_GRAY);
//            g.fillRect((tailleCase / 2) + offsetX + tailleCase * j, (tailleCase / 2) + offsetY + tailleCase * i,
//                    tailleCase,20);
//            g.setColor(Color.BLACK);
//            g.drawRect((tailleCase / 2) + offsetX + tailleCase * j, (tailleCase / 2) + offsetY + tailleCase * i + 20,
//                    tailleCase,40);
//            for(int brique = 0; brique < 8; brique++)
//            {
//                g.setColor(Color.GRAY);
//                g.fillRect(brique * (tailleCase / 8) + (tailleCase / 2) + offsetX + tailleCase * j, (brique% 2 == 0 ? 10 : 0) + (tailleCase / 2) + offsetY + tailleCase * i + 20,
//                        tailleCase / 8,10);
//                g.fillRect(brique * (tailleCase / 8) + (tailleCase / 2) + offsetX + tailleCase * j, (brique% 2 == 0 ? 30 : 20) + (tailleCase / 2) + offsetY + tailleCase * i + 20,
//                        tailleCase / 8,10);
//                g.setColor(Color.LIGHT_GRAY);
//                g.fillRect(brique * (tailleCase / 8) + (tailleCase / 2) + offsetX + tailleCase * j, (brique% 2 == 0 ? 0 : 10) + (tailleCase / 2) + offsetY + tailleCase * i + 20,
//                        tailleCase / 8,10);
//                g.fillRect(brique * (tailleCase / 8) + (tailleCase / 2) + offsetX + tailleCase * j, (brique% 2 == 0 ? 20 : 30) + (tailleCase / 2) + offsetY + tailleCase * i + 20,
//                        tailleCase / 8,10);
//            }
//        }
//}
