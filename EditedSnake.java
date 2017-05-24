import java.awt.Color;
import java.util.ArrayList;

//Snake

public class Snake implements Locatable
{
	private ArrayList<Location> locs;
	private int length;
	private Direction dir;
	
	public Snake()
	{
		locs = new ArrayList<Location>();
		locs.add(new Location(0,0));
		length = 1;
	}
	
	public Snake(ArrayList<Location> loc, int l)
	{
		locs = loc;
		for(int i = 0; i < l; i++)
		{
			locs.add(new Location(loc.get(i).row(), loc.get(i).col() + i));
		}
		length = l;
	}
	
	public Snake(Location loc)
	{
		locs = new ArrayList<Location>();
		locs.add(loc);
		length = 1;
	}
	
	public ArrayList<Location> location()
	{
		return locs;
	}
	
	public Color color()
	{
		return Color.BLUE;
	}
	
	public void grow(Direction dir)
	{
		if(dir == Direction.NORTH)
		{
			locs.add(new Location(locs.get(locs.size()-1).row()-1,locs.get(locs.size()-1).col()));
		}
		else if(dir == Direction.SOUTH)
		{
			locs.add(new Location(locs.get(locs.size()-1).row()+1,locs.get(locs.size()-1).col()));
		}
		else if(dir == Direction.EAST)
		{
			locs.add(new Location(locs.get(locs.size()-1).row(),locs.get(locs.size()-1).col()+1));
		}
		else if(dir == Direction.WEST)
		{
			locs.add(new Location(locs.get(locs.size()-1).row(),locs.get(locs.size()-1).col()-1));
		}
	}
	
	
	public void move(boolean eats)
	{
		if(eats)
		{
			//this.grow();
			locs.add(locs.remove(0));
			//Dont want to change your code but idk if this will work
			//try:
			/*
			 * locs.add(0, new Location(locs.get(0).nextTo(dir)));
			 * 
			 */
		}
		else
		{
			locs.add(locs.remove(0));
			/*
			 * loc.remove(locs.size()-1);
			 * locs.add(0, new Location(locs.get(0).nextTo(dir)));
			 * 
			 */
		}
	}
	
	/*public boolean eats()
	{
		if(locs.get(locs.size()-1) instanceOf 
		*/
}
