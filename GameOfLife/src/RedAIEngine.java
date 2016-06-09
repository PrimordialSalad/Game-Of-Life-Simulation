import java.awt.Point;
import java.util.BitSet;
import java.util.Random;

public class RedAIEngine
{
//	This is the instantiation of the game of life class.    
	private GameOfLife m_game;
//	The column count for the grid.	
	private int m_columnCount;
//	The row count for the grid.	
	private int m_rowCount;
//	The instantiation of the core AI class.	
	private CoreAIEngine core;
//	The collection of safe indexes.  The indexes of the "hazard" line.
	private int[] m_safeIndexes = new int[15];
//	The collection of random points that can be the central point.  Used for spaceship creation.
	private int[] m_randomCentralPoint = new int[2];
//	The random variable for use when a random number is needed.
	private Random m_randInt = new Random();
//	The point that all the sprites created by the AI are created around this point.
	private Point m_centralPoint;
//	The amount of turns left before the AI can create another spaceship or block.
	private int turnsLeft = m_randInt.nextInt(6);
	
	public RedAIEngine(GameOfLife game, int columnCount, int rowCount)
	{
//	The instantiation of the game of life class is set to the game of life class passing itself in.	    
		m_game = game;
//	The column count is being set to the value being passed in.		
		m_columnCount = columnCount;
//	The row count is being set to the value being passed in.		
		m_rowCount = rowCount;
//	The instantiation of the core AI class is being set.		
		core = new CoreAIEngine(m_columnCount, m_rowCount);
		
//		Adds the central index.  If it was in the for loop it would have been added twice.
		m_safeIndexes[0] = core.indexFinder(m_rowCount / 2, m_columnCount - 18);
		
		for(int r = 1; r < 8; r++)
		{
//			Creates all indexes above the central point.
		    m_safeIndexes[2 * r - 1] = core.indexFinder((m_rowCount / 2) + r, m_columnCount - 18);
//		    Creates all indexes below the central point.	
		    m_safeIndexes[2 * r] = core.indexFinder((m_rowCount / 2) - r, m_columnCount - 18);
		}
		
//		Adds the two indexes where the spaceship can be randomly created.
		m_randomCentralPoint[0] = m_columnCount / 2;
		m_randomCentralPoint[1] = (m_columnCount / 2) + 1;
	}
	
	public BitSet createRedCore() // The red core is on the right hand side.
	{
//	The core is what the AI is trying to destroy and protect.	    
//	A temp variable that is set in the game of life class to be the new data BitSet	    
	    BitSet result = m_game.getData();
	    
//	This is where the core is created, the specific cells are turned on.	    
	    result.set(core.indexFinder(m_rowCount - (m_rowCount / 2), m_columnCount - 1));
	    result.set(core.indexFinder(m_rowCount - (m_rowCount / 2) + 1, m_columnCount - 2));
	    result.set(core.indexFinder(m_rowCount - (m_rowCount / 2) + 1, m_columnCount - 1));
	    result.set(core.indexFinder(m_rowCount - (m_rowCount / 2), m_columnCount - 2));
	    result.set(core.indexFinder(m_rowCount - (m_rowCount / 2) + 3, m_columnCount - 3));
	    result.set(core.indexFinder(m_rowCount - (m_rowCount / 2) + 2, m_columnCount - 4));
	    result.set(core.indexFinder(m_rowCount - (m_rowCount / 2) + 2, m_columnCount - 3));
	    result.set(core.indexFinder(m_rowCount - (m_rowCount / 2) + 3, m_columnCount - 4));
//	Returns the result of the temp variable.	    
	    return result;
	}
	
	public void run(BitSet original)
	{
//		Checks to make sure it won, first.  Setting the turns left is just a way to break from the function.
	    if(won())
	    {
		turnsLeft = 1000;
	    }
	    
	    if(turnsLeft == 0)
	    {
//		If it hasn't won, then is checks to see if it has 0 turns left, before it can
//		create a sprite.
		
//		This is the possible index of a breach in the "hazard" line
		int possibleIndex = checkSafeIndexes(original);
		
//		If there is no breach than the function returns negative 1.	
		if(possibleIndex == -1)
		{
//			Creates a point around one of the two random points it can be created.	
		    m_centralPoint = new Point(m_randomCentralPoint[m_randInt.nextInt(2)], m_columnCount - 20);
		    
//			Sets the data to the return of the AI creating a spaceship.
		    m_game.setData(core.createInvertedSpaceship(m_centralPoint, original));
//			Sets the turns left to a random number.		
		    turnsLeft = 35 + m_randInt.nextInt(20);
		}
		
		else
		{
//			If it has been breached then it creates a point in front of where
//			the sprite got trough.		    
		    m_centralPoint = new Point(((possibleIndex - 12) / m_columnCount), m_columnCount - 16);
//			Then creates a block there to obstruct the path of the incoming sprite.	
		    m_game.setData(core.createBlock(m_centralPoint, original));
//			Sets the turns left to a random number.		    
		    turnsLeft = 35 + m_randInt.nextInt(20);
		}
	    }
	    
	    else
	    {
//		Decrements the turns left if it still does.
		turnsLeft--;
	    }
	}
	
	public boolean won()
	{
//	This is the function to figure out whether or not the AI has won.
	    boolean result = false;
	    
	    if(!(m_game.getData().get(core.indexFinder(m_rowCount - (m_rowCount / 2), 0))))
	    {
//		This makes the loop exit in the game of life class which stops the program.		
	    	m_game.setWon();
	    	result = true;
	    }
	    
//		Returns the boolean as to whether or not it has won yet.
	    return result;
	}
	
	public int checkSafeIndexes(BitSet original)
	{
//		Checks to make sure the none of the safe indexes are true.
//		Sets the initial result to -1.	   
	    int result = -1;
	    
	    for(int i = 0; i < m_safeIndexes.length; i++)
	    {
//		Loops through the whole array checking each value which corresponds to the "hazard" line.
		if(original.get(m_safeIndexes[i]))
		{
//			If one result is found that index is set as the result.	
		    result = m_safeIndexes[i];
		}
	    }
	    
//		Returns the result.
	    return result;
	}
	
	public int[] giveSafeIndexes()
	{
//		Returns the array which houses the safe indexes.
	    return m_safeIndexes;
	}
}
