import java.util.ArrayList;

//Snake Game
//Tyler Murray

public class Food implements Locatable
{
	
	private Location loc;
	
	public Food(Location l)
	{
		loc = l;	
	}
	
	public ArrayList<Location> location()
	{
		ArrayList<Location> v = new ArrayList<Location>();
		v.add(loc);
		return v;
	}
	
}

