
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JPanel;

//Snake Game
//Tyler Murray


public class SnakeGame extends JPanel implements KeyListener, MouseListener
{
	
	final static int ROWS = 25;
	final static int COLS = 25;
	final static int GRID_LENGTH = 500;
	final static int k = GRID_LENGTH/ROWS;
	Color BACK = new Color(0x636562);
	private static boolean gameOver;
	private static boolean success = false;
	private static boolean restartScreen = false;
	private static boolean resPress = false;
	private static int resCounter;
	private static int score = 0;
	//private static ArrayList<Locatable> ent = new ArrayList<Locatable>();
	private static boolean titleScreen = true;
	private static boolean spacePress = false;
	private static Direction inputDir = Direction.NORTH;
	private static int currLvl = 0;
	private static Level theLvl;
	private static SnakeEnv theEnv;
	
	public SnakeGame()
	{
		//super("Snake++");
		//addKeyListener(this);
	}
	
	public void paint(Graphics g)
	{
		if(titleScreen)
		{
			paintTitle(g);
			//for(long i = 0; i < 100000000; i++);
		}
		else if(success)
		{
			System.out.println("Success");
			paintLvlSuccess(g);
		}
		else if(restartScreen)
		{
			paintRestart(g);
			delay(1000);
			resCounter--;
		}
		else if(gameOver)
		{
			paintGameOver(g);
		}
		else
		{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, GRID_LENGTH+100, GRID_LENGTH+100);
			g.setColor(BACK);
			g.fillRect(50, 50, 500, 500);
			
			g.setColor(Color.DARK_GRAY);
			g.fillRect(50, 550, 500, 50);
			g.setColor(BACK);
			g.fillRect(50, 550, 500, 5);
			g.fillRect(50, 45, 500, 5);
			g.fillRect(45, 45, 5, 510);
			g.fillRect(550, 45, 5, 510);
			g.setColor(Color.WHITE);
			g.drawString("Level: " +currLvl, 60, 575);
			g.drawString("Food Eaten: " +theLvl.getFoodEaten() +"/" +theLvl.getFoodReq(), 140, 575);
			g.drawString("Points: " +theLvl.getScore() +"pts - " +theLvl.getClock()/(2*(int)(.5*theLvl.getFoodReq()+5))+"pts, Total = "
					+(score + theLvl.getScore() - theLvl.getClock()/(2*(int)(.5*theLvl.getFoodReq()+5))), 250, 575);
			g.drawString("Time: " +theLvl.getClock(), 450, 575);
			
			for(int i = 0; i < theEnv.numObjects(); i++)
			{
					paintLoc(theEnv.allObjects()[i].location().get(0), g, theEnv.allObjects()[i].color());
			}
		}
	}
	
	public static void main(String[] args)
	{
		
		theEnv = new SnakeEnv(ROWS, COLS);
		
		SnakeGame w = new SnakeGame();
		JFrame frame = new JFrame("Snake++");
		frame.setSize(GRID_LENGTH+110, GRID_LENGTH +145);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(w);
		frame.setVisible(true);
		frame.addKeyListener(w);
		frame.setFocusCycleRoot(true);
		
		while(!spacePress)
		{
			w.repaint();
		}
		titleScreen = false;
		
		do
		{
			
			restartScreen = false;
			resCounter = 10;
			score = 0;
			
			//Level 1
				ArrayList<Locatable> ent1 = new ArrayList<Locatable>();
				Snake player = new Snake(theEnv, new Location(10,10));
				theEnv.add(new SnakePart(theEnv, new Location(10,10)));
				theEnv.add(new Obstacle(theEnv, new Location(17,17)));theEnv.add(new Obstacle(theEnv, new Location(18,18)));
				theEnv.add(new Obstacle(theEnv, new Location(19,19)));theEnv.add(new Obstacle(theEnv, new Location(5,5)));
				theEnv.add(new Obstacle(theEnv, new Location(6,6)));theEnv.add(new Obstacle(theEnv, new Location(7,7)));
				theEnv.add(new Obstacle(theEnv, new Location(17,5)));theEnv.add(new Obstacle(theEnv, new Location(18,6)));
				theEnv.add(new Obstacle(theEnv, new Location(19,7)));theEnv.add(new Obstacle(theEnv, new Location(5,17)));
				theEnv.add(new Obstacle(theEnv, new Location(6,18)));theEnv.add(new Obstacle(theEnv, new Location(7,19)));
				ent1.add(player);
				ent1.add(new Food(theEnv, new Location(21, 21)));
				Level level1 = new Level(theEnv, true, new ArrayList<Locatable>(Arrays.asList(theEnv.allObjects())), 1);
				
				inputDir = null;
				runLvl(level1, w, player);
				w.repaint();
				delay(2000);
				theEnv.removeAll();
			
			//Level 2
			if(success)
			{
				success = false;
				
				ArrayList<Locatable> ent2 = new ArrayList<Locatable>();
				player = new Snake(theEnv, new Location(5,10));
				theEnv.add(new SnakePart(theEnv, new Location(5,10)));
				ent2.add(player);
				ent2.add(new Food(theEnv, new Location(20, 20)));
				ent2.add(new Turret(theEnv, new Location(10,1), Direction.EAST, 4));
				//ent1.add(new Coin(theEnv));
				Level level2 = new Level(theEnv, true, ent2, 1);
				
				
				inputDir = null;
				runLvl(level2, w, player);
				
				w.repaint();
				delay(2000);
				theEnv.removeAll();
			}
			
			//Level 3
			if(success)
			{
				success = false;
				
				ArrayList<Locatable> ent3 = new ArrayList<Locatable>();
				player = new Snake(theEnv, new Location(5,10));
				theEnv.add(new SnakePart(theEnv, new Location(5,10)));
				ent3.add(player);
				ent3.add(new Food(theEnv, new Location(20, 20)));
				
				theEnv.add(new Obstacle(theEnv, new Location(13,3)));theEnv.add(new Obstacle(theEnv, new Location(11,14)));
				theEnv.add(new Obstacle(theEnv, new Location(3,8)));theEnv.add(new Obstacle(theEnv, new Location(18,7)));
				theEnv.add(new Obstacle(theEnv, new Location(23,2)));theEnv.add(new Obstacle(theEnv, new Location(2,7)));
				theEnv.add(new Obstacle(theEnv, new Location(23,8)));theEnv.add(new Obstacle(theEnv, new Location(7,7)));
				theEnv.add(new Obstacle(theEnv, new Location(11,23)));theEnv.add(new Obstacle(theEnv, new Location(6,1)));
				theEnv.add(new Obstacle(theEnv, new Location(12,23)));theEnv.add(new Obstacle(theEnv, new Location(5,9)));
				theEnv.add(new Obstacle(theEnv, new Location(21,15)));theEnv.add(new Obstacle(theEnv, new Location(13,17)));
				theEnv.add(new Obstacle(theEnv, new Location(11,17)));theEnv.add(new Obstacle(theEnv, new Location(4,11)));
				theEnv.add(new Obstacle(theEnv, new Location(20,15)));theEnv.add(new Obstacle(theEnv, new Location(18,2)));
				theEnv.add(new Obstacle(theEnv, new Location(17,18)));theEnv.add(new Obstacle(theEnv, new Location(13,4)));
				theEnv.add(new Obstacle(theEnv, new Location(8,3)));theEnv.add(new Obstacle(theEnv, new Location(17,11)));
				theEnv.add(new Obstacle(theEnv, new Location(8,21)));theEnv.add(new Obstacle(theEnv, new Location(3,2)));
				theEnv.add(new Obstacle(theEnv, new Location(21,2)));theEnv.add(new Obstacle(theEnv, new Location(17,20)));
				theEnv.add(new Obstacle(theEnv, new Location(2,17)));theEnv.add(new Obstacle(theEnv, new Location(14,13)));
				theEnv.add(new Obstacle(theEnv, new Location(11,10)));theEnv.add(new Obstacle(theEnv, new Location(14,3)));
				theEnv.add(new Obstacle(theEnv, new Location(7,14)));
				//Adding turrets and obstacles goes here
				
				Level level3 = new Level(theEnv, true, ent3, 10);
				
				inputDir = null;
				runLvl(level3, w, player);
				
				w.repaint();
				delay(2000);
				theEnv.removeAll();
			}
			
			//Level 4
			if(success)
			{
				success = false;
				
				ArrayList<Locatable> ent4 = new ArrayList<Locatable>();
				 
				player = new Snake(theEnv, new Location(21,6));
				theEnv.add(new SnakePart(theEnv, new Location(21,6)));
				ent4.add(new Food(theEnv, new Location(21,10)));
				ent4.add(player);
				
				theEnv.add(new Obstacle(theEnv, new Location (2,4)));
				theEnv.add(new Obstacle(theEnv, new Location (2,5)));theEnv.add(new Obstacle(theEnv, new Location (2,8)));
				theEnv.add(new Obstacle(theEnv, new Location (2,9)));theEnv.add(new Obstacle(theEnv, new Location (2,12)));
				theEnv.add(new Obstacle(theEnv, new Location (2,13)));theEnv.add(new Obstacle(theEnv, new Location (2,16)));
				theEnv.add(new Obstacle(theEnv, new Location (2,17)));theEnv.add(new Obstacle(theEnv, new Location (2,20)));
				theEnv.add(new Obstacle(theEnv, new Location (2,21)));theEnv.add(new Obstacle(theEnv, new Location (3,4)));
				theEnv.add(new Obstacle(theEnv, new Location (3,5)));theEnv.add(new Obstacle(theEnv, new Location (3,8)));
				theEnv.add(new Obstacle(theEnv, new Location (3,9)));theEnv.add(new Obstacle(theEnv, new Location (3,12)));
				theEnv.add(new Obstacle(theEnv, new Location (3,13)));theEnv.add(new Obstacle(theEnv, new Location (3,16)));
				theEnv.add(new Obstacle(theEnv, new Location (3,17)));theEnv.add(new Obstacle(theEnv, new Location (3,20)));
				theEnv.add(new Obstacle(theEnv, new Location (3,21)));theEnv.add(new Obstacle(theEnv, new Location (6,12)));
				theEnv.add(new Obstacle(theEnv, new Location (6,13)));theEnv.add(new Obstacle(theEnv, new Location (7,12)));
				theEnv.add(new Obstacle(theEnv, new Location (7,13)));theEnv.add(new Obstacle(theEnv, new Location (9,12)));
				theEnv.add(new Obstacle(theEnv, new Location (9,13)));theEnv.add(new Obstacle(theEnv, new Location (10,12)));
				theEnv.add(new Obstacle(theEnv, new Location (10,13)));theEnv.add(new Obstacle(theEnv, new Location (13,6)));
				theEnv.add(new Obstacle(theEnv, new Location (13,12)));theEnv.add(new Obstacle(theEnv, new Location (13,13)));
				theEnv.add(new Obstacle(theEnv, new Location (13,19)));theEnv.add(new Obstacle(theEnv, new Location (14,12)));
				theEnv.add(new Obstacle(theEnv, new Location (14,13)));theEnv.add(new Obstacle(theEnv, new Location (16,12)));
				theEnv.add(new Obstacle(theEnv, new Location (16,13)));theEnv.add(new Obstacle(theEnv, new Location (17,12)));
				theEnv.add(new Obstacle(theEnv, new Location (20,12)));theEnv.add(new Obstacle(theEnv, new Location (20,13)));
				theEnv.add(new Obstacle(theEnv, new Location (21,12)));theEnv.add(new Obstacle(theEnv, new Location (21,13)));
				
				ent4.add(new Turret(theEnv, new Location(3,3), Direction.WEST, 4));
				ent4.add(new Turret(theEnv, new Location(3,22), Direction.EAST, 4));
				ent4.add(new Turret(theEnv, new Location(6,6), Direction.SOUTH, 4));
				ent4.add(new Turret(theEnv, new Location(6,19), Direction.SOUTH, 4));
				//Adding turrets and obstacles goes here
				Level level4 = new Level(theEnv, true, ent4, 8);
				
				inputDir = null;
				runLvl(level4, w, player);
				
				w.repaint();
				delay(2000);
				theEnv.removeAll();
			}
			
			
			//Level 5
			if(success)
			{
				success = false;
				
				ArrayList<Locatable> ent5 = new ArrayList<Locatable>();
				player = new Snake(theEnv, new Location(22,7));
				theEnv.add(new SnakePart(theEnv, new Location(22,7)));
				ent5.add(player);
				ent5.add(new Food(theEnv, new Location(18, 9)));
				theEnv.add(new Obstacle(theEnv, new Location (6,2)));theEnv.add(new Obstacle(theEnv, new Location (6,12)));
				theEnv.add(new Obstacle(theEnv, new Location (6,20)));theEnv.add(new Obstacle(theEnv, new Location (7,3)));
				theEnv.add(new Obstacle(theEnv, new Location (7,13)));theEnv.add(new Obstacle(theEnv, new Location (7,21)));
				theEnv.add(new Obstacle(theEnv, new Location (8,4)));theEnv.add(new Obstacle(theEnv, new Location (8,14)));
				theEnv.add(new Obstacle(theEnv, new Location (8,22)));theEnv.add(new Obstacle(theEnv, new Location (9,5)));
				theEnv.add(new Obstacle(theEnv, new Location (9,15)));theEnv.add(new Obstacle(theEnv, new Location (9,23)));
				theEnv.add(new Obstacle(theEnv, new Location (16,2)));theEnv.add(new Obstacle(theEnv, new Location (16,12)));
				theEnv.add(new Obstacle(theEnv, new Location (16,20)));theEnv.add(new Obstacle(theEnv, new Location (17,3)));
				theEnv.add(new Obstacle(theEnv, new Location (17,13)));theEnv.add(new Obstacle(theEnv, new Location (17,21)));
				theEnv.add(new Obstacle(theEnv, new Location (18,4)));theEnv.add(new Obstacle(theEnv, new Location (18,14)));
				theEnv.add(new Obstacle(theEnv, new Location (18,22)));theEnv.add(new Obstacle(theEnv, new Location (19,5)));
				theEnv.add(new Obstacle(theEnv, new Location (19,15)));theEnv.add(new Obstacle(theEnv, new Location (19,23)));
				ent5.add(new Turret(theEnv, new Location(13,23), Direction.WEST, 4));
				
				Level level5 = new Level(theEnv, true, ent5, 1); 
				
				inputDir = null;
				runLvl(level5, w, player);
				
				w.repaint();
				delay(2000);
				theEnv.removeAll();
			}
			
			//Level 6
			if(success)
			{
				success = false;
				
				ArrayList<Locatable> ent6 = new ArrayList<Locatable>();
				player = new Snake(theEnv, new Location(11,4));
				theEnv.add(new SnakePart(theEnv, new Location(11,4)));
				ent6.add(player);
				ent6.add(new Food(theEnv, new Location(11,17)));
				theEnv.add(new Obstacle(theEnv, new Location (5,2)));theEnv.add(new Obstacle(theEnv, new Location (5,5)));
				theEnv.add(new Obstacle(theEnv, new Location (5,15)));theEnv.add(new Obstacle(theEnv, new Location (5,18)));
				theEnv.add(new Obstacle(theEnv, new Location (6,3)));theEnv.add(new Obstacle(theEnv, new Location (6,4)));
				theEnv.add(new Obstacle(theEnv, new Location (6,16)));theEnv.add(new Obstacle(theEnv, new Location (6,17)));
				theEnv.add(new Obstacle(theEnv, new Location (7,3)));theEnv.add(new Obstacle(theEnv, new Location (7,4)));
				theEnv.add(new Obstacle(theEnv, new Location (7,16)));theEnv.add(new Obstacle(theEnv, new Location (7,17)));
				theEnv.add(new Obstacle(theEnv, new Location (8,2)));theEnv.add(new Obstacle(theEnv, new Location (8,5)));
				theEnv.add(new Obstacle(theEnv, new Location (8,15)));theEnv.add(new Obstacle(theEnv, new Location (8,18)));
				theEnv.add(new Obstacle(theEnv, new Location (16,2)));theEnv.add(new Obstacle(theEnv, new Location (16,5)));
				theEnv.add(new Obstacle(theEnv, new Location (16,15)));theEnv.add(new Obstacle(theEnv, new Location (16,18)));
				theEnv.add(new Obstacle(theEnv, new Location (17,3)));theEnv.add(new Obstacle(theEnv, new Location (17,4)));
				theEnv.add(new Obstacle(theEnv, new Location (17,16)));theEnv.add(new Obstacle(theEnv, new Location (17,17)));
				theEnv.add(new Obstacle(theEnv, new Location (18,3)));theEnv.add(new Obstacle(theEnv, new Location (18,4)));
				theEnv.add(new Obstacle(theEnv, new Location (18,16)));theEnv.add(new Obstacle(theEnv, new Location (18,17)));
				theEnv.add(new Obstacle(theEnv, new Location (19,2)));theEnv.add(new Obstacle(theEnv, new Location (19,5)));
				theEnv.add(new Obstacle(theEnv, new Location (19,15)));theEnv.add(new Obstacle(theEnv, new Location (19,18)));
				
				ent6.add(new Turret(theEnv, new Location(1,9), Direction.SOUTH, 4));
				ent6.add(new Turret(theEnv, new Location(23,21), Direction.NORTH, 4));
				
				Level level6 = new Level(theEnv, true, ent6, 1);
				
				inputDir = null;
				runLvl(level6, w, player);
				
				w.repaint();
				delay(2000);
				theEnv.removeAll();
			}
			
			//Level 7
			if(success)
			{
				success = false;
				
				ArrayList<Locatable> ent7 = new ArrayList<Locatable>();
				 
				player = new Snake(theEnv, new Location(21,6));
				theEnv.add(new SnakePart(theEnv, new Location(21,6)));
				ent7.add(new Food(theEnv, new Location(21,10)));
				ent7.add(player);
				
				theEnv.add(new Obstacle(theEnv, new Location (2,4)));
				theEnv.add(new Obstacle(theEnv, new Location (2,5)));theEnv.add(new Obstacle(theEnv, new Location (2,8)));
				theEnv.add(new Obstacle(theEnv, new Location (2,9)));theEnv.add(new Obstacle(theEnv, new Location (2,12)));
				theEnv.add(new Obstacle(theEnv, new Location (2,13)));theEnv.add(new Obstacle(theEnv, new Location (2,16)));
				theEnv.add(new Obstacle(theEnv, new Location (2,17)));theEnv.add(new Obstacle(theEnv, new Location (2,20)));
				theEnv.add(new Obstacle(theEnv, new Location (2,21)));theEnv.add(new Obstacle(theEnv, new Location (3,4)));
				theEnv.add(new Obstacle(theEnv, new Location (3,5)));theEnv.add(new Obstacle(theEnv, new Location (3,8)));
				theEnv.add(new Obstacle(theEnv, new Location (3,9)));theEnv.add(new Obstacle(theEnv, new Location (3,12)));
				theEnv.add(new Obstacle(theEnv, new Location (3,13)));theEnv.add(new Obstacle(theEnv, new Location (3,16)));
				theEnv.add(new Obstacle(theEnv, new Location (3,17)));theEnv.add(new Obstacle(theEnv, new Location (3,20)));
				theEnv.add(new Obstacle(theEnv, new Location (3,21)));theEnv.add(new Obstacle(theEnv, new Location (6,12)));
				theEnv.add(new Obstacle(theEnv, new Location (6,13)));theEnv.add(new Obstacle(theEnv, new Location (7,12)));
				theEnv.add(new Obstacle(theEnv, new Location (7,13)));theEnv.add(new Obstacle(theEnv, new Location (9,12)));
				theEnv.add(new Obstacle(theEnv, new Location (9,13)));theEnv.add(new Obstacle(theEnv, new Location (10,12)));
				theEnv.add(new Obstacle(theEnv, new Location (10,13)));theEnv.add(new Obstacle(theEnv, new Location (13,6)));
				theEnv.add(new Obstacle(theEnv, new Location (13,12)));theEnv.add(new Obstacle(theEnv, new Location (13,13)));
				theEnv.add(new Obstacle(theEnv, new Location (13,19)));theEnv.add(new Obstacle(theEnv, new Location (14,12)));
				theEnv.add(new Obstacle(theEnv, new Location (14,13)));theEnv.add(new Obstacle(theEnv, new Location (16,12)));
				theEnv.add(new Obstacle(theEnv, new Location (16,13)));theEnv.add(new Obstacle(theEnv, new Location (17,12)));
				theEnv.add(new Obstacle(theEnv, new Location (20,12)));theEnv.add(new Obstacle(theEnv, new Location (20,13)));
				theEnv.add(new Obstacle(theEnv, new Location (21,12)));theEnv.add(new Obstacle(theEnv, new Location (21,13)));
				
				ent7.add(new Turret(theEnv, new Location(3,3), Direction.WEST, 4));
				ent7.add(new Turret(theEnv, new Location(3,22), Direction.EAST, 4));
				ent7.add(new Turret(theEnv, new Location(6,6), Direction.SOUTH, 4));
				ent7.add(new Turret(theEnv, new Location(6,19), Direction.SOUTH, 4));
				//Adding turrets and obstacles goes here
				Level level7 = new Level(theEnv, true, ent7, 8);
				
				inputDir = null;
				runLvl(level7, w, player);
				
				w.repaint();
				delay(2000);
				theEnv.removeAll();
			}
			
			//Level 6
			if(success)
			{
				success = false;
				
				ArrayList<Locatable> ent3 = new ArrayList<Locatable>();
				player = new Snake(theEnv, new Location(5,10));
				theEnv.add(new SnakePart(theEnv, new Location(5,10)));
				ent3.add(player);
				ent3.add(new Food(theEnv, new Location(20, 20)));
				
				//Adding turrets and obstacles goes here
				
				Level level3 = new Level(theEnv, true, ent3, 10);
				
				inputDir = null;
				runLvl(level3, w, player);
				
				w.repaint();
				delay(2000);
				theEnv.removeAll();
			}
			
			//Level 7
			if(success)
			{
				success = false;
				
				ArrayList<Locatable> ent3 = new ArrayList<Locatable>();
				player = new Snake(theEnv, new Location(5,10));
				theEnv.add(new SnakePart(theEnv, new Location(5,10)));
				ent3.add(player);
				ent3.add(new Food(theEnv, new Location(20, 20)));
				
				//Adding turrets and obstacles goes here
				
				Level level3 = new Level(theEnv, true, ent3, 10);
				
				inputDir = null;
				runLvl(level3, w, player);
				
				w.repaint();
				delay(2000);
				theEnv.removeAll();
			}
			
		}while(resPress);
		restartScreen = false;
		gameOver = true;
		w.repaint();
			
		}
	
	public static void runLvl(Level lvl, SnakeGame window, Snake p)
	{
		currLvl++;
		while(!lvl.isOver())
		{
			theLvl = lvl;
			//System.out.println(inputDir.toString());
			if(inputDir == null || p.getDirection() == null) {}
			else if(inputDir.equals(p.getDirection().reverse()) && p.getLength() > 1)
				inputDir = inputDir.reverse();
			lvl.simStep(inputDir, p);
			window.repaint();
			delay(120);
		}
		score += lvl.getScore() - theLvl.getClock()/(2*(int)(.5*theLvl.getFoodReq()+5));
		if(lvl.won())
		{
			success = true;
			
		}
		else
		{
			restartScreen = true;
			resPress = false;
			currLvl = 0;
			while(resCounter > 0 && !resPress)
			{
				window.repaint();
			}
			success = false;
		}
	}
	
	//Various paint methods
	
	public void paintLoc(Location loc, Graphics gr, Color c)
	{
		int x1 = (int)loc.col()*(GRID_LENGTH/COLS)+50;
		int y1 = (int)loc.row()*(GRID_LENGTH/ROWS)+50;
		
		gr.setColor(c);
		gr.fillRect(x1, y1, (GRID_LENGTH/COLS), (GRID_LENGTH/COLS));
	}
	
	public void paintRestart(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GRID_LENGTH+100, GRID_LENGTH+100);
		g.setFont(new Font("Courier", Font.PLAIN, 18));
		g.setColor(Color.WHITE);
		g.drawString("Press \"R\" to Restart " +resCounter, 170, 270);
		g.drawString("Final Score: " +score, 200, 300);
		if(resPress)
			g.drawString("Restarting...", 230, 350);
	}
	
	public void paintTitle(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GRID_LENGTH+100, GRID_LENGTH+100);
		
		Color c = new Color(0x0000E1);
		g.setColor(c);
		g.fillRect(2*k, 3*k, 4*k, 7*k);
		/*g.fillRect(2*k, 3*k, 4*k, k);
		g.fillRect(2*k, 4*k, k, 3*k);
		g.fillRect(3*k, 6*k, 3*k, k);
		g.fillRect(5*k, 7*k, k, 3*k);
		g.fillRect(2*k, 9*k, 3*k, k);*/
		g.fillRect(16*k, 3*k, 5*k, 7*k);
		g.fillRect(2*k, 11*k, 4*k, 7*k);
		g.fillRect(8*k, 11*k, 4*k, 7*k);
		g.fillRect(13*k, 14*k, 3*k, 1*k);
		g.fillRect(14*k, 13*k, 1*k, 3*k);
		g.fillRect(17*k, 14*k, 3*k, 1*k);
		g.fillRect(18*k, 13*k, 1*k, 3*k);
		g.fillRect(8*k, 3*k, k, 7*k);
		g.fillRect(13*k, 3*k, k, 7*k);
		g.fillRect(9*k, 3*k, k, 2*k);
		g.fillRect(10*k, 4*k, k, 2*k);
		g.fillRect(11*k, 6*k, k, 2*k);
		g.fillRect(12*k, 8*k, k, 2*k);
		g.setFont(new Font("Courier", Font.PLAIN, 20));
		g.drawString("(Press the spacebar to begin)", 120, 425);
		g.drawString("(Use WASD to move)", 175, 450);
		g.setFont(new Font("Courier", Font.PLAIN, 12));
		g.drawString("Created by: Tyler Murray, Naheen Iqra, Jesse Wadler, and Squid", 150, 595);
		
		g.setColor(Color.BLACK);
		g.fillRect(3*k, 4*k, 3*k, 2*k);
		g.fillRect(2*k, 7*k, 3*k, 2*k);
		g.fillRect(17*k, 4*k, 3*k, 2*k);
		g.fillRect(17*k, 7*k, 3*k, 3*k);
		g.fillRect(3*k, 11*k, 2*k, 2*k);
		g.fillRect(3*k, 13*k, k, k);
		g.fillRect(5*k, 13*k, k, k);
		g.fillRect(4*k, 14*k, k, k);
		g.fillRect(3*k, 16*k, 2*k, 2*k);
		g.fillRect(3*k, 15*k, k, k);
		g.fillRect(3*k, 18*k, k, k);
		g.fillRect(3*k, 19*k, 2*k, 2*k);
		g.fillRect(5*k, 14*k, k, 2*k);
		g.fillRect(9*k, 12*k, 3*k, 2*k);
		g.fillRect(9*k, 15*k, 3*k, 2*k);
		g.fillRect(10*k, 14*k, 2*k, k);
		
		
	}
	
	public void paintLvlSuccess(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GRID_LENGTH+100, GRID_LENGTH+100);
		g.setFont(new Font("Courier", Font.PLAIN, 36)); 
		g.setColor(Color.WHITE);
		g.drawString("Level Success!!!", 150, 300);
	}
	
	public void paintGameOver(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GRID_LENGTH+100, GRID_LENGTH+100);
		g.setFont(new Font("Courier", Font.PLAIN, 36));
		g.setColor(Color.WHITE);
		g.drawString("gAme ovER", 200, 300);
		g.drawString("Final Score: " +score, 200, 400);
	}
	
	public static void delay(int MS)
	{
		try 
		{
			TimeUnit.MILLISECONDS.sleep(MS);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		
		
		
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_KP_UP)
			inputDir = Direction.NORTH;
		else if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_KP_LEFT)
			inputDir = Direction.WEST;
		else if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_KP_DOWN)
			inputDir = Direction.SOUTH;
		else if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_KP_RIGHT)
			inputDir = Direction.EAST;
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			spacePress = true;
			System.out.println("Space!");
		}
		if(e.getKeyCode() == KeyEvent.VK_R)
		{
			resPress = true;
			System.out.println("R pressed");
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			spacePress = false;
		}
		
	}
	
}