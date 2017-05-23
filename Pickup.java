import java.util.ArrayList;
//Snake Game
//Tyler Murray

public abstract class Pickup
{
	
	private Location loc;
	
	public Pickup(Location l)
	{
		loc = l;	
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
		
	
}
