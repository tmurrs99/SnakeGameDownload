
public enum Direction
{
	NORTH, SOUTH, EAST, WEST, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST;
	
	public String toString()
	{
		if(this == Direction.EAST)
		{
			return "EAST";
		}
		else if(this == Direction.SOUTH)
		{
			return "SOUTH";
		}
		else if(this == Direction.WEST)
		{
			return "WEST";
		}
		else
		{
			return "NORTH";
		}
	}
	
	public Direction reverse()
	{
		if(this == Direction.EAST)
		{
			return Direction.WEST;
		}
		else if(this == Direction.SOUTH)
		{
			return Direction.NORTH;
		}
		else if(this == Direction.WEST)
		{
			return Direction.EAST;
		}
		else
		{
			return Direction.NORTH;
		}
	}
}