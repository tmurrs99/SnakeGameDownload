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
	private boolean over = false;
	private boolean won = false;
	private int score;
	//private Location[] foods;
	
	public Level(SnakeEnv env)
	{
		entities = new ArrayList<Locatable>();
		theEnv = env;
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
			{
				entities.add(new Obstacle(theEnv, new Location(0, i)));
				theEnv.add(entities.get(entities.size()-1));
			}
			for(int i = 1; i < theEnv.numRows()-1; i++)
			{
				entities.add(new Obstacle(theEnv, new Location(theEnv.numCols()-1, i)));
				theEnv.add(entities.get(entities.size()-1));
			}
			for(int i = 1; i < theEnv.numCols(); i++)
			{
				entities.add(new Obstacle(theEnv, new Location(i, 0)));
				theEnv.add(entities.get(entities.size()-1));
			}
			for(int i = 1; i < theEnv.numCols(); i++)
			{
				entities.add(new Obstacle(theEnv, new Location(i, theEnv.numRows()-1)));
				theEnv.add(entities.get(entities.size()-1));
			}
		}
		for(int i = 0; i < initEnt.size(); i++)
		{
			entities.add(initEnt.get(i));
			//theEnv.add(initEnt.get(i));
		}
		
		entities.add(new Coin(theEnv));
		
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
	
	
	public void simStep(Direction dir, Snake player)
	{
		System.out.println("Sim Step");
		player.setDirection(dir);
		Locatable adj = theEnv.objectAt(player.location().get(0).nextTo(dir));
		if(adj instanceof Food)
		{
			adj.move(false);
			player.move(true);
			foodEaten++;
			if(foodEaten >= foodRequired)
			{
				//LevelComplete
				over = true;
				won = true;
				return;
			}
		}
		else if(adj instanceof Coin)
		{
			adj.move(false);
			player.move(false);
			score += 5;
		}
		else if(adj instanceof Bullet || adj instanceof Turret || adj instanceof Obstacle)
		{
			over = true;
			won = false;
			return;	
		}
		else if(adj instanceof SnakePart)
		{
			player.move(false);
			if(player.location().get(0).equals(adj.location().get(0)))
			{
				over = true;
				won = false;
				return;
			}
		}
		else
		{
			player.move(false);
		}
		
		for(int i = 0; i < theEnv.allObjects().length; i++)
		{
			if(theEnv.allObjects()[i] instanceof Turret)
			{
				theEnv.allObjects()[i].updateValues(player.getLength());
				theEnv.allObjects()[i].move(false);
			}
			if(theEnv.allObjects()[i] instanceof Bullet)
			{	
				if(theEnv.objectAt(theEnv.allObjects()[i].location().get(0).nextTo(theEnv.allObjects()[i].getDirection())) instanceof Snake)
				{
					over = true;
					won = false;
					return;
				}
			}
			
		}
		clock++;
		
	}
}