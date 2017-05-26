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
		return new Color(0x58097E);
	}
	
	public void setDirection(Direction d)
	{
		
	}
	
	public String toString()
	{
		return "Food";
	}
	
	public void move(boolean eats)
	{
		
	}
	
}
