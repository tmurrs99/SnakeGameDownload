

//Snake Game
//Tyler Murray

import java.util.ArrayList;

public class Obstacle implements Locatable
{
	
	private ArrayList<Location> locs;
	private int yLength;
	private int xLength;
	
	
	public Obstacle(Location l)
	{
		locs.add(l);
		yLength = 1;
		xLength = 1;
	}
	public Obstacle(Location l, int y, int x)
	{
		for(int i = 0; i < y; i++)
		{
			for(int j = 0; j < x; j++)
			{
				locs.add(new Location(l.row()+i, l.col()+j));
			}
		}
		yLength = y;
		xLength = x;
	}
	
	public ArrayList<Location> location()
	{
		return locs;
	}
	
	public void move()
	{
		//obstacles wont move for now
	}
	
	public int getyLength()
	{
		return yLength;
	}
	
	public int getxLength()
	{
		return xLength;
	}
	
	//Dont know if we need this
	public boolean isOccupied(Location other)
	{
		for(Location loc : locs)
		{
			if(other.equals(loc))
				return true;
		}
		return false;
	}
	
	
}

