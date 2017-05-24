import java.awt.Color;
import java.util.ArrayList;

//Snake Game
//Tyler Murray

public class Coin extends Pickup implements Locatable
{
	
	//private Location loc;
	private SnakeEnv theEnv;
	Location loc;
	
	public Coin()
	{
		super();
	}
	
	public Coin(SnakeEnv env)
	{
		
		theEnv = env;
		do
		{
			loc = new Location((int)Math.random()*theEnv.numRows(), (int)Math.random()*theEnv.numCols());
		}while(!theEnv.isEmpty(loc));
		
		
	}
	
	public Coin(SnakeEnv env, Location l)
	{
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
		return Color.YELLOW;
	}
	
	public void move(boolean eats)
	{
		
	}
	
}
