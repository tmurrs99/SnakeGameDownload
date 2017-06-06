
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
	private static int score = 0;
	//private static ArrayList<Locatable> ent = new ArrayList<Locatable>();
	private static boolean titleScreen = true;
	private static boolean spacePress = false;
	private static Direction inputDir = Direction.NORTH;
	private static int currLvl;
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
			for(long i = 0; i < 100000000; i++);
		}
		else if(success)
		{
			System.out.println("Success");
			paintLvlSuccess(g);
		}
		else if(restartScreen)
		{
			paintRestart(g);
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
			g.drawString("Points: " +theLvl.getScore() +", prev. total= " +score, 250, 575);
			
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
		//w.show();
		
		Level[] levels = new Level[10];
		
		
		while(!spacePress)
		{
			w.repaint();
		}
		titleScreen = false;
		
		/*
		 *Make an array of levels and loop through the while loop section of each level block
		 *in a for loop
		 */
		ArrayList<Locatable> ent1 = new ArrayList<Locatable>();
		Snake player = new Snake(theEnv, new Location(10,10));
		//theEnv.add(new SnakePart(theEnv, new Location(10,10)));
		//theEnv.add(new Obstacle(theEnv, new Location(15,15), 2, 2));
		ent1.add(player);
		ent1.add(new Food(theEnv, new Location(20, 20)));
		//ent1.add(new Coin(theEnv));
		Level level1 = new Level(theEnv, true, new ArrayList<Locatable>(Arrays.asList(theEnv.allObjects())), 5, score);
		levels[0] = level1;
			
		ArrayList<Locatable> ent2 = new ArrayList<Locatable>();
		player = new Snake(theEnv, new Location(5,10));
		//theEnv.add(new SnakePart(theEnv, new Location(5,10)));
		ent2.add(player);
		ent2.add(new Food(theEnv, new Location(20, 20)));
		ent2.add(new Turret(theEnv, new Location(10,1), Direction.EAST, 4));
		//ent1.add(new Coin(theEnv));
		Level level2 = new Level(theEnv, true, ent2, 10, score);
		levels[1] = level2;
		/*
		do
		{
			//repeat this block for each level
			
			ArrayList<Locatable> ent1 = new ArrayList<Locatable>();
			Snake player = new Snake(theEnv, new Location(10,10));
			theEnv.add(new SnakePart(theEnv, new Location(10,10)));
			theEnv.add(new Obstacle(theEnv, new Location(15,15), 2, 2));
			ent1.add(player);
			ent1.add(new Food(theEnv, new Location(20, 20)));
			//ent1.add(new Coin(theEnv));
			Level level1 = new Level(theEnv, true, new ArrayList<Locatable>(Arrays.asList(theEnv.allObjects())), 5, score);
			levels[0] = level1;
			
			while(!level1.isOver())
			{
				theLvl = level1;
				currLvl = 1;
				level1.simStep(inputDir, player);
				//ent = level1.getEntities();
				w.repaint();
				delay(120);
			}
			score += level1.getScore();
			if(level1.won())
			{
				success = true;
			}
			else
			{
				restartScreen = true;
				w.repaint();
				delay(10000);
				success = false;
			}
			w.repaint();
			delay(2000);
			
			theEnv.removeAll();
			
			
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
				Level level2 = new Level(theEnv, true, ent2, 10, score);
				levels[1] = level2;
				
				while(!level2.isOver())
				{
					theLvl = level2;
					currLvl = 2;
					System.out.println(inputDir.toString());
					level2.simStep(inputDir, player);
					//ent = level2.getEntities();
					w.repaint();
					//System.out.println(ent.get(ent.size()-1).location().get(0).toString());
					//paint level
					delay(120);
				}
				score += level1.getScore();
				if(level2.won())
				{
					success = true;
				}
				else
				{
					restartScreen = true;
					w.repaint();
					delay(10000);
					success = false;
				}
				w.repaint();
				delay(2000);
			
			}
		}while(resPress);
		gameOver = true;
		*/
		
		
		do
		{
			for(int i = 0; i < levels.length; i++)
			{
				if(success)
					{
						success = false;
						/*start level block*/
						
						for(int j = 0; j < levels[i].getEntities().size(); j++)
							theEnv.add(levels[i].getEntities().get(j));
						
						while(!level2.isOver())
						{
							theLvl = level2;
							currLvl = 2;
							System.out.println(inputDir.toString());
							level2.simStep(inputDir, player);
							//ent = level2.getEntities();
							w.repaint();
							//System.out.println(ent.get(ent.size()-1).location().get(0).toString());
							//paint level
							delay(120);
						}
						score += level1.getScore();
						if(level2.won())
						{
							success = true;
						}
						else
						{
							restartScreen = true;
							w.repaint();
							delay(10000);
							success = false;
						}
						w.repaint();
						delay(2000);
						/*end level block*/
					}
				}
				theEnv.removeAll();
		 }while(resPress);
		 gameOver = true;
			
		}
	
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
		g.drawString("Press R to Restart", 25, 300);
		g.drawString("Final Score: " +score, 50, 400);
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
		g.drawString("(Press SpaceBar to begin)", 250, 450);
		g.drawString("(Use WASD to move)", 250, 475);
		
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