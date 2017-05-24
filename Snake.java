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
	
	public void grow(Direction dir)
	{
		if(dir == Direction.NORTH)
		{
			locs.add(new Location(locs.get(locs.size()-1).row()-1,locs.get(locs.size()-1).col());
		}
		else if(dir == Direction.SOUTH)
		{
			locs.add(new Location(locs.get(locs.size()-1).row()+1,locs.get(locs.size()-1).col());
		}
		else if(dir == Direction.EAST)
		{
			locs.add(new Location(locs.get(locs.size()-1).row(),locs.get(locs.size()-1).col()+1);
		}
		else if(dir == Direction.WEST)
		{
			locs.add(new Location(locs.get(locs.size()-1).row(),locs.get(locs.size()-1).col()-1);
		}
	}
	
	public void move(boolean eats)
	{
		if(eats)
		{
			this.grow();
			locs.add(locs.remove(0));
		}
		else
		{
			locs.add(locs.remove(0));
		}
	}
	
	public boolean eats()
	{
		if(locs.get(locs.size()-1) instanceOf 
}
