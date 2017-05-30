//Snake Game


public class SnakeEnv extends SquareEnvironment
{
    // Instance Variables: Encapsulated data for each SnakeEnv object
    private Locatable[][] theGrid;  // grid representing the environment
    private int objectCount;        // # of objects in current environment


  // constructors

    /** Constructs an empty SnakeEnv object with the given dimensions.
     *  (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     *  @param rows        number of rows in SnakeEnv
     *  @param cols        number of columns in SnakeEnv
     **/
    public SnakeEnv(int rows, int cols)
    {
        // Construct and initialize inherited attributes.
        super();

        theGrid = new Locatable[rows][cols];
        objectCount = 0;
    }


  // accessor methods

    /** Returns number of rows in the environment.
     *  @return   the number of rows, or -1 if this environment is unbounded
     **/
    public int numRows()
    {
        return theGrid.length;
    }

    /** Returns number of columns in the environment.
     *  @return   the number of columns, or -1 if this environment is unbounded
     **/
    public int numCols()
    {
        // Note: according to the constructor precondition, numRows() > 0, so
        // theGrid[0] is non-null.
        return theGrid[0].length;
    }

    /** Verifies whether a location is valid in this environment.
     *  @param  loc    location to check
     *  @return <code>true</code> if <code>loc</code> is valid;
     *          <code>false</code> otherwise
     **/
    public boolean isValid(Location loc)
    {
        if ( loc == null )
            return false;

        return (0 <= loc.row() && loc.row() < numRows()) &&
               (0 <= loc.col() && loc.col() < numCols());
    }

    /** Returns the number of objects in this environment.
     *  @return   the number of objects
     **/
    public int numObjects()
    {
        return objectCount;
    }

    /** Returns all the objects in this environment.
     *  @return    an array of all the environment objects
     **/
    public Locatable[] allObjects()
    {
        Locatable[] theObjects = new Locatable[numObjects()];
        int tempObjectCount = 0;

        // Look at all grid locations.
        for ( int r = 0; r < numRows(); r++ )
        {
            for ( int c = 0; c < numCols(); c++ )
            {
                // If there's an object at this location, put it in the array.
                Locatable obj = theGrid[r][c];
                if ( obj != null )
                {
                    theObjects[tempObjectCount] = obj;
                    tempObjectCount++;
                }
            }
        }

        return theObjects;
    }

    /** Determines whether a specific location in this environment is
     *  empty.
     *  @param loc  the location to test
     *  @return     <code>true</code> if <code>loc</code> is a
     *              valid location in the context of this environment
     *              and is empty; <code>false</code> otherwise
     **/
    public boolean isEmpty(Location loc)
    {
        return isValid(loc) && objectAt(loc) == null;
    }

    /** Returns the object at a specific location in this environment.
     *  @param loc    the location in which to look
     *  @return       the object at location <code>loc</code>;
     *                <code>null</code> if <code>loc</code> is not
     *                in the environment or is empty
     **/
    public Locatable objectAt(Location loc)
    {
        if ( ! isValid(loc) )
            return null;

        return theGrid[loc.row()][loc.col()];
    }

    /** Creates a single string representing all the objects in this
     *  environment (not necessarily in any particular order).
     *  @return    a string indicating all the objects in this environment
     **/
    public String toString()
    {
        Locatable[] theObjects = allObjects();
        String s = "Environment contains " + numObjects() + " objects: ";
        for ( int index = 0; index < theObjects.length; index++ )
            s += theObjects[index].toString() + " ";
        return s;
    }


  // modifier methods

    /** Adds a new object to this environment at the location it specifies.
     *  (Precondition: <code>obj.location()</code> is a valid empty location.)
     *  @param obj the new object to be added
     *  @throws    IllegalArgumentException if the precondition is not met
     **/
    public void add(Locatable obj)
    {
        // Check precondition.  Location should be empty.
        Location loc = obj.location().get(0);
        if ( ! isEmpty(loc) )
            throw new IllegalArgumentException("Location " + loc +
                                    " is not a valid empty location");

        // Add object to the environment.
        theGrid[loc.row()][loc.col()] = obj;
        objectCount++;
        System.out.println("" +obj.toString() +" added at " +loc.toString());
    }

    /** Removes the object from this environment.
     *  (Precondition: <code>obj</code> is in this environment.)
     *  @param obj     the object to be removed
     *  @throws    IllegalArgumentException if the precondition is not met
     **/
    public void remove(Locatable obj)
    {
        // Make sure that the object is there to remove.
        Location loc = obj.location().get(0);
        if ( objectAt(loc) != obj )
            throw new IllegalArgumentException("Cannot remove " + 
                                               obj + "; not there");

        // Remove the object from the grid.
        theGrid[loc.row()][loc.col()] = null;
        objectCount--;
    }
    
    public void removeAll()
    {
	    for(int i = 0; i < theGrid.length; i++)
	    {
	    	for(int j = 0; j < theGrid[i].length; j++)
	    	{
	    		theGrid[i][j] = null;
	    	}
	    }
	    objectCount = 0;
    }
    
    /*public void snakeRemove(Locatable obj, int l)
    {
    	// Make sure that the object is there to remove.
        Location loc = obj.location().get(obj.location().size()-1);
        if ( objectAt(loc) != obj )
            throw new IllegalArgumentException("Cannot remove " + 
                                               obj + "; not there");

        // Remove the object from the grid.
        theGrid[loc.row()][loc.col()] = null;
        objectCount--;
    }*/

    /** Updates this environment to reflect the fact that an object moved.
     *  (Precondition: <code>obj.location()</code> is a valid location
     *  and there is no other object there.
     *  Postcondition: <code>obj</code> is at the appropriate location
     *  (<code>obj.location()</code>), and either <code>oldLoc</code> is 
     *  equal to <code>obj.location()</code> (there was no movement) or
     *  <code>oldLoc</code> is empty.)
     *  @param obj       the object that moved
     *  @param oldLoc    the previous location of <code>obj</code>
     *  @throws    IllegalArgumentException if the precondition is not met
     **/
    public void recordMove(Locatable obj, Location oldLoc)
    {
        // Simplest case: There was no movement.
        Location newLoc = obj.location().get(0);
        if ( newLoc.equals(oldLoc) )
            return;

        // Otherwise, oldLoc should contain the object that is
        //   moving and the new location should be empty.
        Locatable foundObject = objectAt(oldLoc);
        if ( ! (foundObject == obj && isEmpty(newLoc)) )
            throw new IllegalArgumentException("Precondition violation moving "
                + obj + " from " + oldLoc);

        // Move the object to the proper location in the grid.
        theGrid[newLoc.row()][newLoc.col()] = obj;
        theGrid[oldLoc.row()][oldLoc.col()] = null;
    }

}
