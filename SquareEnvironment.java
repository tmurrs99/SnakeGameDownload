//Snake Game
//Tyler Murray

import java.util.ArrayList;
import java.util.Random;

public class SquareEnvironment
{
	
	private int numRows;
	private int numCols;
	
	public SquareEnvironment()
	{
		numRows = 10;
		numCols = 10;
	}
	public SquareEnvironment(int nR, int nC)
	{
		numRows = nR;
		numCols = nC;	
	}
	
	
	public Direction getDirection(Location fromLoc, Location toLoc)
	{
		if((fromLoc.row() < toLoc.row()) && (fromLoc.col() == toLoc.col()))
		{
			return Direction.EAST;
		}
		else if((fromLoc.row() > toLoc.row()) && (fromLoc.col() == toLoc.col()))
		{
			return Direction.WEST;
		}
		else if((fromLoc.col() < toLoc.col()) && (fromLoc.row() == toLoc.row()))
		{
			return Direction.SOUTH;
		}
		else if((fromLoc.row() < toLoc.row()) && (fromLoc.col() < toLoc.col()))
		{
			return Direction.SOUTHEAST;
		}
		else if((fromLoc.row() > toLoc.row()) && (fromLoc.col() < toLoc.col()))
		{
			return Direction.SOUTHWEST;
		}
		else if((fromLoc.row() < toLoc.row()) && (fromLoc.col() > toLoc.col()))
		{
			return Direction.NORTHEAST;
		}
		else if((fromLoc.row() > toLoc.row()) && (fromLoc.col() > toLoc.col()))
		{
			return Direction.NORTHWEST;
		}
		else
		{
			return Direction.NORTH;
		}
	}
	
	public Location getNeighbor(Location fromLoc, Direction dir)
	{
		int r = fromLoc.row();
		int c = fromLoc.col();
		
		if(dir.equals(Direction.EAST))
		{
			return new Location(r+1 ,c);
		}
		else if(dir.equals(Direction.WEST))
		{
			return new Location(r-1 ,c);
		}
		else if(dir.equals(Direction.SOUTH))
		{
			return new Location(r ,c+1);
		}
		else if(dir.equals(Direction.NORTH))
		{
			return new Location(r ,c-1);
		}
		else if(dir.equals(Direction.SOUTHEAST))
		{
			return new Location(r+1 ,c+1);
		}
		else if(dir.equals(Direction.NORTHEAST))
		{
			return new Location(r+1 ,c-1);
		}
		else if(dir.equals(Direction.SOUTHWEST))
		{
			return new Location(r-1 ,c+1);
		}
		else
		{
			return new Location(r-1 ,c-1);
		}
	}
	
	public ArrayList<Location> neighborsOf(Location ofLoc)
	{
		ArrayList<Location> n = new ArrayList<Location>();
		n.add(new Location(ofLoc.row()+1, ofLoc.col()));
		n.add(new Location(ofLoc.row()-1, ofLoc.col()));
		n.add(new Location(ofLoc.row(), ofLoc.col()+1));
		n.add(new Location(ofLoc.row(), ofLoc.col()-1));
		n.add(new Location(ofLoc.row()+1, ofLoc.col()+1));
		n.add(new Location(ofLoc.row()+1, ofLoc.col()-1));
		n.add(new Location(ofLoc.row()-1,ofLoc.col()-1));
		n.add(new Location(ofLoc.row()-1, ofLoc.col()+1));
		
		return n;
	}
	
	public int numAdjacentNeighbors(Location ofLoc)
	{
		if(ofLoc.row() == numRows)
		{
			if(ofLoc.col() == numCols)
				return 3;
			else
				return 5;	
		}
		else if(ofLoc.col() == numCols)
		{
			if(ofLoc.row() == numRows)
				return 3;
			else
				return 5;
		}
		else
		{
			return 8;
		}
	}
	
	public Direction randomDirection()
	{
		
		double x = Math.random()*8;
		if(x < 1)
		{
			return Direction.EAST;
		}
		else if(x < 2)
		{
			return Direction.WEST;
		}
		else if(x < 3)
		{
			return Direction.SOUTH;
		}
		else if(x < 4)
		{
			return Direction.SOUTHEAST;
		}
		else if(x < 5)
		{
			return Direction.SOUTHWEST;
		}
		else if(x < 6)
		{
			return Direction.NORTHEAST;
		}
		else if(x < 7)
		{
			return Direction.NORTHWEST;
		}
		else
		{
			return Direction.NORTH;
		}
	}
	
	public int numCellSides()
	{
		return 4;
	}
	
}


