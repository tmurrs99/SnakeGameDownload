


//Snake Game
//Tyler Murray

import java.awt.Color;
import java.util.ArrayList;

public class Obstacle implements Locatable
{
	
	private ArrayList<Location> locs;
	private int yLength;
	private int xLength;
	private SnakeEnv theEnv;
	
	
	public Obstacle(SnakeEnv env, Location l)
	{
		locs = new ArrayList<Location>();
		locs.add(l);
		yLength = 1;
		xLength = 1;
		theEnv = env;
	}
	public Obstacle(SnakeEnv env, Location l, int y, int x)
	{
		locs = new ArrayList<Location>();
		theEnv = env;
		int c = 0;
		for(int i = 0; i < y; i++)
		{
			for(int j = 0; j < x; j++)
			{
				locs.add(new Location(l.row()+i, l.col()+j));
				if(i > 1)
					theEnv.add(new Obstacle(env, locs.get(c)));
				c++;
			}
		}
		yLength = y;
		xLength = x;
		
	}
	
	public ArrayList<Location> location()
	{
		return locs;
	}
	
	public Direction getDirection()
	{
		return null;
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
	
	
	
	
}
