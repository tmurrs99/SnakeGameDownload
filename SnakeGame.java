import java.util.ArrayList;

//Snake Game
//Tyler Murray


public class SnakeGame
{
	
	public static void main(String[] args)
	{
		int score = 0;
		final int ROWS = 10;
		final int COLS = 10;
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
	
	
}
