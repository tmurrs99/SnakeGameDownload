import java.util.ArrayList;
//Snake Game
//Tyler Murray

public abstract class Pickup implements Locatable
{
	
	private Location loc;
	private SnakeEnv theEnv;
	
	public Pickup()
	{
		loc = new Location(0,0);
	}
	
	public Pickup(Location l)
	{
		loc = l;	
	}
	
	public Pickup(Location l, SnakeEnv env)
	{
		loc = l;
		theEnv = env;
	}
	
	public ArrayList<Location> location()
	{
		ArrayList<Location> v = new ArrayList<Location>();
		v.add(loc);
		return v;
	}
	public void move()
	{
		
	}
	
	public void setDirection(Direction d)
	{
		
	}
	
	public String toString()
	{
		return "";
	}
		
	
}