import java.awt.Color;
import java.util.ArrayList;

public class Turret implements Locatable
{
	private Direction dir;
	private int initFd;
	private int fireDelay;
	private ArrayList<Bullet> bullets;
	private Location center;
	private int count = 0;
	private SnakeEnv theEnv;
	
	
	

	public Turret(SnakeEnv env, Location loc, Direction d, int fd)
	{
		center = loc;
		initFd = fd;
		fireDelay = fd;
		bullets = new ArrayList<Bullet>();
		dir = d;
		theEnv = env;
		theEnv.add(this);
	}
	
	public ArrayList<Location> location()
	{
		ArrayList<Location> loc = new ArrayList<Location>();
		loc.add(center);
		return loc;
	}
	
	public void updateValues(int l)
	{
		if(fireDelay < l + (initFd - 1))
		{
			fireDelay = l + (initFd - 1);
		}
	}
	
	public void move(boolean eats)
	{

		if(count == fireDelay)
		{
			bullets.add(new Bullet(theEnv, new Location(center.nextTo(dir).row(), center.nextTo(dir).col()), dir));	
			count = 0;
		}
		count++;
		if(bullets.size() == 0)
			return;
		
		if(bullets.get(0).location().get(0).row() >= theEnv.numRows()-2 || 
				bullets.get(0).location().get(0).col() >= theEnv.numCols()-2 ||
				bullets.get(0).location().get(0).row() < 2 ||
				bullets.get(0).location().get(0).col() < 2)
		{
			bullets.get(0).move(true);
			bullets.remove(0);
		}
		
		
		for(int i = 0; i < bullets.size(); i++)
		{
			if(theEnv.objectAt(bullets.get(i).location().get(0).nextTo(dir)) instanceof Obstacle)
			{
				bullets.get(i).move(true);
				bullets.remove(i);
			}
			else
				bullets.get(i).move(false);
		}
		
	}
	
	public void setDirection(Direction d)
	{
		dir = d;
	}
	
	public Color color()
	{
		return new Color(0xFF5F00);
	}
	
	public ArrayList<Bullet> getBullets()
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