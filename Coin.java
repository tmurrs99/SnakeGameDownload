import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

//Snake Game
//Tyler Murray

public class Coin extends Pickup implements Locatable
{
	
	//private Location loc;
	private SnakeEnv theEnv;
	private Location loc;
	private Random gen = new Random();
	
	public Coin(SnakeEnv env)
	{
		
		theEnv = env;
		do
		{
			loc = new Location((int)(Math.random()*theEnv.numRows()), (int)(Math.random()*theEnv.numCols()));
		}while(!theEnv.isEmpty(loc));
		theEnv.add(this);
		
		
	}
	
	public Coin(SnakeEnv env, Location l)
	{
		//dont use this
		super(l);	
	}
	
	public ArrayList<Location> location()
	{
		ArrayList<Location> locs = new ArrayList<Location>();
		locs.add(loc);
		return locs;
	}
	
	public Color color()
	{
		return new Color(0xFFD900);
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
		return "Coin";
	}
	
	public void move(boolean eats)
	{
		Location l;
		boolean noTurrets = true;
		do
		{
			l = new Location((int)(gen.nextDouble()*theEnv.getRows()), (int)(gen.nextDouble()*theEnv.getCols()));
			
			for(Direction dir : Direction.allDirections())
				if(theEnv.objectAt(l.nextTo(dir)) instanceof Turret)
					noTurrets = false;
				
		}while(!theEnv.isEmpty(l) || !noTurrets);
		Location oldLoc = loc;
		loc = l;
		theEnv.recordMove(this, oldLoc);
	}

	
	@Override
	public void updateValues(int l) {
		// TODO Auto-generated method stub
		
	}
	
}