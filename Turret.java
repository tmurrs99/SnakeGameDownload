import java.awt.Color;
import java.util.ArrayList;

public class Turret implements Locatable
{
	private Direction dir;
	private int fireDelay;
	private ArrayList<Bullet> bullets;
	private Location center;
	private int count = 0;
	private SnakeEnv theEnv;
	
	
	

	public Turret(SnakeEnv env, Location loc, Direction d, int fd)
	{
		center = loc;
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
	
	public void updateFrequency(int l)
	{
		if(fireDelay < l)
		{
			fireDelay = l;
		}
	}
	
	public void move(boolean eats)
	{
			
		
		for(int i  = 0; i < bullets.size(); i++)
		{
			if(bullets.get(i).location().get(0)./*nextTo(dir).*/row() >= theEnv.numRows()-2 || bullets.get(i).location().get(0)./*nextTo(dir).*/col() >= theEnv.numCols()-2
				|| bullets.get(i).location().get(0)./*nextTo(dir).*/row() < 2 || bullets.get(i).location().get(0)/*.nextTo(dir)*/.col() < 2)
			{	
				//theEnv.remove(bullets.get(i));
				bullets.get(i).move(false);
				bullets.remove(i);
			}
			else
			{
				//bullets.set(i, new Location((bullets.get(i).nextTo(dir)).row(), (bullets.get(i).nextTo(dir)).col()));
				bullets.get(i).move(false);
			}	
		}
		if(count == fireDelay)
		{
			bullets.add(new Bullet(theEnv, new Location(center.nextTo(dir).row(), center.nextTo(dir).col()), dir));	
			count = 0;
		}
		count++;
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