import java.awt.Color;
import java.util.ArrayList;

public class Turret implements Locatable
{
	private Direction dir;
	private int fireDelay;
	private ArrayList<Location> bullets;
	private Location center;
	private int count = 0;
	private SnakeEnv theEnv;
	
	
	public Turret()
	{
		center = new Location(0,0);
		fireDelay = 5;
		bullets = new ArrayList<Location>();
		dir = Direction.NORTH;
	}
	public Turret(Location loc, Direction d, int fd, SnakeEnv env)
	{
		center = loc;
		fireDelay = fd;
		bullets = new ArrayList<Location>();
		dir = d;
		theEnv = env;
	}
	
	public ArrayList<Location> location()
	{
		ArrayList<Location> loc = new ArrayList<Location>();
		loc.add(center);
		return loc;
	}
	
	public void move(boolean eats)
	{
			
		for(int i  = 0; i < bullets.size(); i++)
		{
			if(bullets.get(i).nextTo(dir).row() >= theEnv.numRows() || bullets.get(i).nextTo(dir).col() >= theEnv.numCols()
				|| bullets.get(i).nextTo(dir).row() < 0 || bullets.get(i).nextTo(dir).col() < 0)
			{	
				bullets.remove(i);
			}
			else
			{
				bullets.set(i, new Location((bullets.get(i).nextTo(dir)).row(), (bullets.get(i).nextTo(dir)).col()));
			}	
		}
		if(count == fireDelay)
		{
			bullets.add(new Location(center.nextTo(dir).row(), center.nextTo(dir).col()));	
		}
	}
	
	public void setDirection(Direction d)
	{
		
	}
	
	public Color color()
	{
		return new Color(0xFF5F00);
	}
	
	public ArrayList<Location> getBullets()
	{
		return bullets;
	}
	
	public String toString()
	{
		return "Turret";
	}
	
	public Direction getDirection()
	{
		return dir;
	}
}
