import java.util.ArrayList;

//Snake Game
//Tyler Murray

public class Food extends Pickup implements Locatable
{
	
	//private Location loc;
	
	public Food(Location l)
	{
		super(l);	
	}
	
	public ArrayList<Location> location()
	{
		return super.location();
	}
	
	public Location move(boolean eats)
	{
		return this.location();
	}
	
}
