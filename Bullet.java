import java.awt.Color;
import java.util.ArrayList;

public class Bullet implements Locatable {

	private Location loc;
	private Direction dir;
	private SnakeEnv theEnv;
	
	public Bullet(SnakeEnv env, Location l, Direction d)
	{
		loc = l;
		dir = d;
		theEnv = env;
		theEnv.add(this);
	}
	
	@Override
	public ArrayList<Location> location()
	{
		ArrayList<Location> locs = new ArrayList<Location>();
		locs.add(loc);
		return locs;
	}
	
	public Direction getDirection()
	{
		return dir;
	}

	@Override
	public void move(boolean eats)
	{
		if(this.location().get(0)./*nextTo(dir).*/row() >= theEnv.numRows() || this.location().get(0)./*nextTo(dir).*/col() >= theEnv.numCols()
				|| this.location().get(0)./*nextTo(dir).*/row() < 0 || this.location().get(0)/*.nextTo(dir)*/.col() < 0)
		{	
			theEnv.remove(this);
		}
		else
		{
			Location oldLoc = loc;
			loc = loc.nextTo(dir);
			theEnv.recordMove(this, oldLoc);
		}
			
	}

	@Override
	public Color color() {
		// TODO Auto-generated method stub
		return Color.BLACK;
	}

	@Override
	public void setDirection(Direction d)
	{
		// TODO Auto-generated method stub

	}

}