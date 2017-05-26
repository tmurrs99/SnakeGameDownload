import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

//Snake Game
//Tyler Murray


public class SnakeGame extends JFrame implements KeyListener, MouseListener
{
	
	final static int ROWS = 25;
	final static int COLS = 25;
	final static int GRID_LENGTH = 500;
	final static int k = GRID_LENGTH/ROWS;
	Color BACK = new Color(0x636562);
	private static int score = 0;
	private static ArrayList<Locatable> ent = new ArrayList<Locatable>();
	private static boolean titleScreen = true;
	public static boolean spacePress = false;
	private static Direction inputDir = Direction.NORTH;
	private static int currLvl;
	private static Level theLvl;
	
	public SnakeGame()
	{
		super("Snake++");
		addKeyListener(this);
	}
	
	public void paint(Graphics g)
	{
		if(titleScreen)
		{
			paintTitle(g);
			for(long i = 0; i < 100000000; i++);
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
			
			for(int i = 0; i < ent.size(); i++)
			{
				for(int j = 0; j < ent.get(i).location().size(); j++)
				{
					paintLoc(ent.get(i).location().get(j), g, ent.get(i).color());
					
				}
				//System.out.println(ent.get(i).location().get(0).toString());
			}
			//do header with level, points info
		}
	}
	
	public static void main(String[] args)
	{
		
		SnakeEnv theEnv = new SnakeEnv(ROWS, COLS);
		
		SnakeGame w = new SnakeGame();
		w.setSize(GRID_LENGTH+100, GRID_LENGTH +100);
		w.show();
		
		
		
		while(!spacePress)
		{
			w.repaint();
		}
		titleScreen = false;
		//repeat this block for each level
		/*start level block*/
		ArrayList<Locatable> ent1 = new ArrayList<Locatable>();
		Snake player = new Snake(theEnv, new Location(10,10));
		theEnv.add(new SnakePart(theEnv, new Location(10,10)));
		ent1.add(player);
		ent1.add(new Food(theEnv, new Location(20, 20)));
		//ent1.add(new Coin(theEnv));
		Level level1 = new Level(theEnv, true, ent1, 10, score);
		
		while(!level1.isOver())
		{
			theLvl = level1;
			currLvl = 1;
			System.out.println(inputDir.toString());
			level1.simStep(inputDir, player);
			ent = level1.getEntities();
			w.repaint();
			//System.out.println(ent.get(ent.size()-1).location().get(0).toString());
			//paint level
			delay(200);
		}
		score += level1.getScore();
		if(level1.won())
		{
			
		}
		else
		{
			
		}
		/*end level block*/
		
		
	}
	
	public void paintLoc(Location loc, Graphics gr, Color c)
	{
		int x1 = (int)loc.col()*(GRID_LENGTH/COLS)+50;
		int y1 = (int)loc.row()*(GRID_LENGTH/ROWS)+50;
		
		gr.setColor(c);
		gr.fillRect(x1, y1, (GRID_LENGTH/COLS), (GRID_LENGTH/COLS));
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
		
		if(e.getKeyCode() == KeyEvent.VK_W)
			inputDir = Direction.NORTH;
		else if(e.getKeyCode() == KeyEvent.VK_A)
			inputDir = Direction.WEST;
		else if(e.getKeyCode() == KeyEvent.VK_S)
			inputDir = Direction.SOUTH;
		else if(e.getKeyCode() == KeyEvent.VK_D)
			inputDir = Direction.EAST;
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			spacePress = true;
			System.out.println("Space!");
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
