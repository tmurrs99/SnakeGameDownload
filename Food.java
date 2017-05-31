import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

//Snake Game
//Tyler Murray

public class Food extends Pickup implements Locatable
{
	
	private Location loc;
	private SnakeEnv theEnv;
	private Random gen = new Random();
	
	public Food(SnakeEnv env, Location l)
	{
		loc = l;	
		theEnv = env;
		theEnv.add(this);
	}
	
	public ArrayList<Location> location()
	{
		ArrayList<Location> locs = new ArrayList<Location>();
		locs.add(loc);
		return locs;
	}
	
	public Color color()
	{
		return new Color(0x58097E);
	}
	
	public void setDirection(Direction d)
	{
		
	}
	
	public Direction getDirection()
	{
		return null;
	}
	
	public String toString()
	{
		return "Food";
	}
	
	public void move(boolean eats)
	{
		Location l;
		do
		{
			l = new Location((int)(gen.nextDouble()*theEnv.getRows()), (int)(gen.nextDouble()*theEnv.getCols()));
		}while(!theEnv.isEmpty(l));
		Location oldLoc = loc;
		loc = l;
		theEnv.recordMove(this, oldLoc);
	}
	
}