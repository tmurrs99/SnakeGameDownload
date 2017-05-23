//Snake

public class Snake
{
	private ArrayList<Location> locs;
	private int length;
	private Direction dir;
	
	public Snake()
	{
		locs = locs.add(new Location(0,0));
		length = 1;
	}
	
	public Snake(ArrayList<Location> loc, int l)
	{
		locs = loc;
		for(int i = 0; i < l; i++)
		{
			locs.add(new Location(loc.row(), loc.col() + i);
		}
		length = l;
	}
	
	public void grow()
	{
		if(dir == Direction.EAST)
		{
			locs.add(new Location(locs.get(locs.size()-1).row(),))
		}
	}
	
	public void move(boolean eats)
	{
		if(eats)
		{
			if()locs.add()
		}
		locs.add(locs.remove(locs.size-1));
	}
}