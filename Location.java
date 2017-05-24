public class Location
{
	private int row;
	private int col;
	
	public Location(int r, int c)
	{
		row = r;
		col = c;
	}
	
	public int row()
	{
		return row;
	}
	
	public int col()
	{
		return col;
	}
	
	public boolean equals(Location other)
	{
		return this.row() == other.row() && this.col() == other.col();
	}
	
	public int compareTo(Location other)
	{
		if(this.row() > other.row())
			return 1;
		else if(this.row() < other.row())
			return -1;
		else if(this.col() > other.row())
			return 1;
		else if(this.col() < other.col())
			return -1;
		return 0;
	}
	
	public Location nextTo(Direction dir)
	{
		if(dir == Direction.NORTH)
			return new Location(this.row()-1,this.col());
		else if(dir == Direction.SOUTH)
			return new Location(this.row()+1,this.col());
		else if(dir == Direction.EAST)
			return new Location(this.row(),this.col()+1);
		else if(dir == Direction.WEST)
			return new Location(this.row(),this.col()-1);
		return null;
	}
	
	public String toString()
	{
		return "Location (" + row() + "," + col() + ")";
	}
}

