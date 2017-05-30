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
		Location oldLoc = loc;
		loc = loc.nextTo(dir);
		theEnv.recordMove(this, oldLoc);
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