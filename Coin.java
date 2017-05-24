import java.util.ArrayList;

//Snake Game
//Tyler Murray

public class Coin extends Pickup implements Locatable
{
	
	//private Location loc;
	private SnakeEnv theEnv;
	
	public Coin(SnakeEnv env)
	{
		do
		{
			Location l = new Location((int)Math.random(theEnv.))
		}while(!theEnv.isEmpty(l))
		super
	}
	
	public Coin(SnakeEnv env, Location l)
	{
		super(l);	
	}
	
	public ArrayList<Location> location()
	{
		return super.location();
	}
	
	public void move(boolean eats)
	{
		return this.location();
	}
	
}
