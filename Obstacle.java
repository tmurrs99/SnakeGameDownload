

//Snake Game
//Tyler Murray

import java.awt.Color;
import java.util.ArrayList;

public class Obstacle implements Locatable
{
	
	private ArrayList<Location> locs;
	private int yLength;
	private int xLength;
	
	
	
	public Obstacle()
	{
		yLength = 1;
		xLength = 1;
		locs = new ArrayList<Location>();
		locs.add(new Location(0,0));
	}
	public Obstacle(Location l)
	{
		locs = new ArrayList<Location>();
		locs.add(l);
		yLength = 1;
		xLength = 1;
	}
	public Obstacle(Location l, int y, int x)
	{
		locs = new ArrayList<Location>();
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
	
	public void setDirection(Direction d)
	{
		
	}
	
	public String toString()
	{
		return "Obstacle";
	}
	
	public Color color()
	{
		return new Color(0xFAFAFA);
	}
	
	public void move(boolean eats)
	{
		//obstacles dont move for now
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

