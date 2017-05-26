import java.util.ArrayList;

//Snake Game
//Tyler Murray

public class Level
{
	private ArrayList<Locatable> entities;
	private SnakeEnv theEnv;
	private int clock = 0;
	private int foodEaten = 0;
	private int foodRequired;
	private boolean over;
	private boolean won = false;
	private int score;
	
	public Level(SnakeEnv env)
	{
		entities = new ArrayList<Locatable>();
		theEnv = env;
	}
	public Level(SnakeEnv env, boolean bounded)
	{
		entities = new ArrayList<Locatable>();
		theEnv = env;
		if(bounded)
		{
			for(int i = 0; i < theEnv.numRows(); i++)
				entities.add(new Obstacle(new Location(0, i)));
			for(int i = 1; i < theEnv.numRows()-1; i++)
				entities.add(new Obstacle(new Location(theEnv.numCols()-1, i)));
			for(int i = 1; i < theEnv.numCols(); i++)
				entities.add(new Obstacle(new Location(i, 0)));
			for(int i = 0; i < theEnv.numCols(); i++)
				entities.add(new Obstacle(new Location(i, theEnv.numRows()-1)));
		}
	}
	public Level(SnakeEnv env, boolean bounded, ArrayList<Locatable> initEnt, int foodR, int s)
	{
		entities = new ArrayList<Locatable>();
		theEnv = env;
		foodRequired = foodR;
		score = s;
		if(bounded)
		{
			for(int i = 0; i < theEnv.numRows(); i++)
				entities.add(new Obstacle(new Location(0, i)));
			for(int i = 1; i < theEnv.numRows()-1; i++)
				entities.add(new Obstacle(new Location(theEnv.numCols()-1, i)));
			for(int i = 1; i < theEnv.numCols(); i++)
				entities.add(new Obstacle(new Location(i, 0)));
			for(int i = 0; i < theEnv.numCols(); i++)
				entities.add(new Obstacle(new Location(i, theEnv.numRows()-1)));
		}
		for(int i = 0; i < initEnt.size(); i++)
		{
			entities.add(initEnt.get(i));
		}
	}
	
	public int getClock()
	{
		return clock;
	}
	
	public boolean isOver()
	{
		return over;
	}
	
	public boolean won()
	{
		return won;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public int getFoodEaten()
	{
		return foodEaten;
	}
	
	public int getFoodReq()
	{
		return foodRequired;
	}
	
	public ArrayList<Locatable> getEntities()
	{
		return entities;
	}
	
	public void simStep(Direction dir)
	{
		ArrayList<Location> oldLoc = new ArrayList<Location>();
		for(int i = 0; i < entities.size(); i++)
		{	
			
			//check if the snake collides with an enemy and should die or eats food
			if(entities.get(i) instanceof Snake)
			{
				entities.get(i).setDirection(dir);
				for(int j = 0; j < entities.size(); j++)
				{
					if(j == i);
					else if((entities.get(j)) instanceof Obstacle || entities.get(j) instanceof Turret)
					{
						if(entities.get(j).location().equals(entities.get(i).location().get(0).nextTo(dir)))
						{
							//Do the level failed animation
							//Exit level
							over = true;
							won = false;
							break;
						}
					}
					else if(entities.get(j) instanceof Coin && 
							entities.get(j).location().equals(entities.get(i).location().get(0).nextTo(dir)))
					{
						score += 5;
						theEnv.remove(entities.get(j));
						entities.remove(j);
						entities.get(i).move(false);
						break;
					}
					else if(entities.get(j) instanceof Food &&
							entities.get(j).location().equals(entities.get(i).location().get(0).nextTo(dir)))
					{
						oldLoc = entities.get(i).location();
						entities.get(i).move(true);
						foodEaten++;
						entities.remove(j);
						break;
					}
				}
				entities.get(i).move(false);
				if(foodEaten >= foodRequired)
				{
					//LevelComplete
					over = true;
					won = true;
				}
						
			}
			else
			{
				entities.get(i).move(false);
			}
		}
		
		clock++;
	}
}
