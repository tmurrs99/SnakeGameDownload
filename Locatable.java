import java.util.ArrayList;

public interface Locatable
{
	//all objects implementing Locatable have a location that can be accessed
	public ArrayList<Location> location();
	
	public void move();
}
