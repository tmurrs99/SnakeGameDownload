import java.awt.Color;
import java.util.ArrayList;


public class Snake implements Locatable
{
	private ArrayList<Location> locs;
	private ArrayList<SnakePart> parts;
	private int length;
	private Direction dir;
	private SnakeEnv theEnv;
	
	
	public Snake(SnakeEnv env, ArrayList<Location> loc, int l)
	{
		theEnv = env;
		locs = new ArrayList<Location>();
		parts = new ArrayList<SnakePart>();
		locs = loc;
		for(int i = 0; i < l; i++)
		{
			locs.add(new Location(loc.get(i).row(), loc.get(i).col() + i));
			parts.add(new SnakePart(theEnv, new Location(loc.get(i).row(), loc.get(i).col() + i)));
			theEnv.add(new Snake(theEnv, locs.get(i)));
		}
		length = l;
	}
	
	public Snake(SnakeEnv env, Location loc)
	{
		theEnv = env;
		locs = new ArrayList<Location>();
		parts = new ArrayList<SnakePart>();
		locs.add(loc);
		parts.add(new SnakePart(theEnv, loc));
		//env.add(this);
		length = 1;
	}
	
	public ArrayList<Location> location()
	{
		return locs;
	}
	
	public Color color()
	{
		return new Color(0x0000E1);
	}
	
	public int getLength()
	{
		return length;
	}
	
	public void setDirection(Direction d)
	{
		dir = d;
	}
	
	public Direction getDirection()
	{
		return dir;
	}
	
	public String toString()
	{
		return "Snake";
	}
	
	
	public void move(boolean eats)
	{
		System.out.println(length);
		for(Location l : locs)
			System.out.println(l.toString());
		if(eats)
		{
			if(parts.get(0).location().get(0).row() >= theEnv.numRows() && dir.equals(Direction.SOUTH))
			{	
				locs.add(0, new Location(0, locs.get(0).nextTo(dir).col()));
				parts.add(0, new SnakePart(theEnv, new Location(0, locs.get(0).nextTo(dir).col())));
				theEnv.add(new SnakePart(theEnv, locs.get(0)));
				length++;

			}
			else if(parts.get(0).location().get(0).col() >= theEnv.numCols() && dir.equals(Direction.EAST))
			{
				locs.add(0, new Location(locs.get(0).nextTo(dir).row(), 0));
				parts.add(0, new SnakePart(theEnv, new Location(locs.get(0).nextTo(dir).row(), 0)));
				theEnv.add(new SnakePart(theEnv, locs.get(0)));
				length++;
			}
			else if(parts.get(0).location().get(0).row() < 0 && dir.equals(Direction.NORTH))
			{
				locs.add(0, new Location(theEnv.numRows()-1, locs.get(0).nextTo(dir).col()));
				parts.add(0, new SnakePart(theEnv, new Location(theEnv.numRows()-1, locs.get(0).nextTo(dir).col())));
				theEnv.add(new SnakePart(theEnv, locs.get(0)));
				length++;
			}
			else if(parts.get(0).location().get(0).col() < 0 && dir.equals(Direction.WEST))
			{
				locs.add(0, new Location(locs.get(0).nextTo(dir).row(), theEnv.numCols()-1));
				parts.add(0, new SnakePart(theEnv, new Location(locs.get(0).nextTo(dir).row(), theEnv.numCols()-1)));
				theEnv.add(new SnakePart(theEnv, locs.get(0)));
				length++;
			}
			else
			{
				locs.add(0, new Location(locs.get(0).nextTo(dir).row(), locs.get(0).nextTo(dir).col()));
				parts.add(0, new SnakePart(theEnv, new Location(locs.get(0).nextTo(dir).row(), locs.get(0).nextTo(dir).col())));
				theEnv.add(new SnakePart(theEnv, locs.get(0)));
				length++;
			}
		}
		else
		{
			if(length > 1 && parts.get(0).location().get(0).nextTo(dir).equals(parts.get(1).location().get(0)))
			{
				//locs.add(0, new Location(locs.get(0).nextTo(dir.reverse()).row(), locs.get(0).nextTo(dir.reverse()).col()));
				//locs.remove(locs.size()-1);
				System.out.println("You cant go backwards, dude");
				this.setDirection(dir.reverse());
			}
			if(parts.get(0).location().get(0).row() >= theEnv.numRows() && dir.equals(Direction.SOUTH))
			{	
				locs.add(0, new Location(0, locs.get(0).nextTo(dir).col()));
				parts.add(0, new SnakePart(theEnv, new Location(0, locs.get(0).nextTo(dir).col())));
				theEnv.add(new SnakePart(theEnv, locs.get(0)));
				theEnv.remove(theEnv.objectAt(locs.get(length)));
				System.out.println("env remove");
				locs.remove(locs.size()-1);

			}
			else if(parts.get(0).location().get(0).col() >= theEnv.numCols() && dir.equals(Direction.EAST))
			{
				locs.add(0, new Location(locs.get(0).nextTo(dir).row(), 0));
				parts.add(0, new SnakePart(theEnv, new Location(locs.get(0).nextTo(dir).row(), 0)));
				theEnv.add(new SnakePart(theEnv, locs.get(0)));
				theEnv.remove(theEnv.objectAt(locs.get(length)));
				System.out.println("env remove");
				locs.remove(locs.size()-1);
			}
			else if(parts.get(0).location().get(0).row() < 0 && dir.equals(Direction.NORTH))
			{
				locs.add(0, new Location(theEnv.numRows()-1, locs.get(0).nextTo(dir).col()));
				parts.add(0, new SnakePart(theEnv, new Location(theEnv.numRows()-1, locs.get(0).nextTo(dir).col())));
				theEnv.add(new SnakePart(theEnv, locs.get(0)));
				theEnv.remove(theEnv.objectAt(locs.get(length)));
				System.out.println("env remove");
				locs.remove(locs.size()-1);
			}
			else if(parts.get(0).location().get(0).col() < 0 && dir.equals(Direction.WEST))
			{
				locs.add(0, new Location(locs.get(0).nextTo(dir).row(), theEnv.numCols()-1));
				parts.add(0, new SnakePart(theEnv, new Location(locs.get(0).nextTo(dir).row(), theEnv.numCols()-1)));
				theEnv.add(new SnakePart(theEnv, locs.get(0)));
				theEnv.remove(theEnv.objectAt(locs.get(length)));
				System.out.println("env remove");
				locs.remove(locs.size()-1);
			}
			else
			{
				System.out.println("Direction: " +dir.toString());
				locs.add(0, new Location(locs.get(0).nextTo(dir).row(), locs.get(0).nextTo(dir).col()));
				parts.add(0, new SnakePart(theEnv, new Location(locs.get(0).nextTo(dir).row(), locs.get(0).nextTo(dir).col())));
				theEnv.add(new SnakePart(theEnv, locs.get(0)));
				System.out.println("Tail: " +locs.get(locs.size()-1).toString());
				theEnv.remove(theEnv.objectAt(locs.get(length)));
				System.out.println("env remove");
				locs.remove(locs.size()-1);
			}
			
			
			
		}
	}

	@Override
	public void updateValues(int l) {
		// TODO Auto-generated method stub
		
	}
}