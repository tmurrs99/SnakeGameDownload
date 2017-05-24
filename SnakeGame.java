import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

//Snake Game
//Tyler Murray


public class SnakeGame extends JFrame
{
	
	final static int ROWS = 10;
	final static int COLS = 10;
	final static int GRID_LENGTH = 500;
	Color BACK = new Color(0x636562);
	private static int score = 0;
	private static ArrayList<Locatable> ent = new ArrayList<Locatable>();
	
	public SnakeGame()
	{
		super("Snake on 'Roids");
	}
	
	public void paint(Graphics g)
	{
		g.setColor(BACK);
		g.fillRect(10, 0, 500, 490);
		for(int i = 0; i < ent.size(); i++)
		{
			for(int j = 0; j < ent.get(i).location().size(); j++)
			{
				paintLoc(ent.get(i).location().get(j), g, ent.get(i).color());
			}
		}
		//do header with level, points info
	}
	
	public static void main(String[] args)
	{
		
		SnakeEnv env = new SnakeEnv(ROWS, COLS);
		Direction inputDir;
		
		SnakeGame w = new SnakeGame();
		w.setSize(GRID_LENGTH, GRID_LENGTH);
		w.show();
		
		//repeat this block for each level
		/*start level block*/
		ArrayList<Locatable> ent1 = new ArrayList<Locatable>();
		ent1.add(new Snake(new Location(5,5)));
		Level level1 = new Level(env, true, ent1, 10, score);
		while(!level1.isOver())
		{
			//get user input
			//inputDir = //stuff
			level1.simStep(inputDir);
			ent = level1.getEntities();
			w.repaint();
			//paint level
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
		int x1 = (int)loc.col()*(GRID_LENGTH/COLS);
		int y1 = (int)loc.row()*(GRID_LENGTH/ROWS);
		
		gr.setColor(c);
		gr.fillRect(x1, y1, (GRID_LENGTH/COLS), (GRID_LENGTH/COLS));
	}
	
	
}
