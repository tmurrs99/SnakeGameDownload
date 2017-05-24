import java.awt.Color;
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
	
	public Color color()
	{
		return Color.MAGENTA;
	}
	
	public void move(boolean eats)
	{
		
	}
	
}
