import java.awt.Color;
import java.util.ArrayList;


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
		locs = new ArrayList<Location>();
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
		return new Color(0x0000E1);
	}
	
	public void setDirection(Direction d)
	{
		dir = d;
	}
	
	public String toString()
	{
		return "Snake";
	}
	/*
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
	*/
	
	
	public void move(boolean eats)
	{
		System.out.println(length);
		if(eats)
		{
			locs.add(0, new Location(locs.get(0).nextTo(dir).row(), locs.get(0).nextTo(dir).col()));
			length++;
		}
		else
		{
			if(length > 1 && this.location().get(0).nextTo(dir).equals(this.location().get(1)))
			{
				//locs.add(0, new Location(locs.get(0).nextTo(dir.reverse()).row(), locs.get(0).nextTo(dir.reverse()).col()));
				//locs.remove(locs.size()-1);
				this.setDirection(dir.reverse());
			}
			locs.add(0, new Location(locs.get(0).nextTo(dir).row(), locs.get(0).nextTo(dir).col()));
			locs.remove(locs.size()-1);
			
		}
	}
}
