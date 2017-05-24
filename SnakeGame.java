import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

//Snake Game
//Tyler Murray


public class SnakeGame extends JFrame
{
	
	final static int ROWS = 10;
	final static int COLS = 10;
	final static int GRID_LENGTH = 500;
	private static int score = 0;
	
	public SnakeGame()
	{
		super("Snake on 'Roids");
	}
	
	public void paint(Graphics g)
	{
		
	}
	
	public static void main(String[] args)
	{
		
		
		SnakeEnv env = new SnakeEnv(ROWS, COLS);
		Direction inputDir;
		
		//repeat this block for each level
		/*start level block*/
		ArrayList<Locatable> ent1 = new ArrayList<Locatable>();
		ent1.add(new Snake(5,5));
		Level level1 = new Level(env, true, ent1, 10, score);
		while(!level1.isOver())
		{
			//get user input
			inputDir = //stuff
			level1.simStep(inputDir);
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
