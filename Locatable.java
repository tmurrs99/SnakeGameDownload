import java.awt.Color;
import java.util.ArrayList;

public interface Locatable
{
	//all objects implementing Locatable have a location that can be accessed
	public ArrayList<Location> location();
	
	public void move(boolean eats);
	
	public Color color();
	
	public void setDirection(Direction d);
	
	public String toString();
	
	public Direction getDirection();
}
