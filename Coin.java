import java.util.ArrayList;

//Snake Game
//Tyler Murray

public class Coin extends Pickup implements Locatable
{
	
	private Location loc;
	
	public Coin(Location l)
	{
		super(l);	
	}
	
	public ArrayList<Location> location()
	{
		return super.location();
	}
	
	public void move()
	{
		//coins dont move
	}
	
}