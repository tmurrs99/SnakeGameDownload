import java.awt.Color;
import java.util.ArrayList;

public class SnakePart implements Locatable
{
	private Location loc;
	private SnakeEnv theEnv;
	
	public SnakePart(SnakeEnv env, Location l)
	{
		loc = l;
		theEnv = env;
	}
	
	@Override
	public ArrayList<Location> location()
	{
		ArrayList<Location> locs = new ArrayList<Location>();
		locs.add(loc);
		return locs;
	}

	@Override
	public void move(boolean eats)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Color color() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDirection(Direction d)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Direction getDirection()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString()
	{
		return "";
	}

}
