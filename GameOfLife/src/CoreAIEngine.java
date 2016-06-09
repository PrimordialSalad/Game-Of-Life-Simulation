import java.awt.Point;
import java.util.BitSet;

public class CoreAIEngine
{
//	This is the column count of the grid.    
	private int m_columnCount;
//	This is the row count of the grid.
	private int m_rowCount;
	
	public CoreAIEngine(int columnCount, int rowCount)
	{
//	This is where the column count gets set to the value passed in.	    
		m_columnCount = columnCount;
//	This is where the row count gets set to the value passed in.		
		m_rowCount = rowCount;
	}
	
	
	public int indexFinder(int row, int column)
	{
//	Returns the 1D index of the 2D row and column.	    
	    return ((row * m_columnCount) + column);
	}
	
	public BitSet createSpaceship(Point centralPoint, BitSet original)
	{
//	Sets the result to the passed in BitSet	    
	    BitSet result = original;
	    
//	Creates the spaceship here, by turning on specific cells.	    
	    result.set(indexFinder(centralPoint.x + 1, centralPoint.y));
	    result.set(indexFinder(centralPoint.x - 1, centralPoint.y));
	    result.set(indexFinder(centralPoint.x + 2, centralPoint.y + 1));
	    result.set(indexFinder(centralPoint.x + 2, centralPoint.y + 2));
	    result.set(indexFinder(centralPoint.x + 2, centralPoint.y + 3));
	    result.set(indexFinder(centralPoint.x + 2, centralPoint.y + 4));
	    result.set(indexFinder(centralPoint.x + 1, centralPoint.y + 4));
	    result.set(indexFinder(centralPoint.x, centralPoint.y + 4));
	    result.set(indexFinder(centralPoint.x - 1, centralPoint.y + 3));
	    
//	Returns the result.	    
	    return result;
	}
	
	public BitSet createInvertedSpaceship(Point centralPoint, BitSet original)
	{
//	Sets the result to the passed in BitSet	    
	    BitSet result = original;
	    
//	This is where the inverted spaceship is created, specific cells are turned on.	    
	    result.set(indexFinder(centralPoint.x + 1, centralPoint.y));
	    result.set(indexFinder(centralPoint.x - 1, centralPoint.y));
	    result.set(indexFinder(centralPoint.x + 2, centralPoint.y - 1));
	    result.set(indexFinder(centralPoint.x + 2, centralPoint.y - 2));
	    result.set(indexFinder(centralPoint.x + 2, centralPoint.y - 3));
	    result.set(indexFinder(centralPoint.x + 2, centralPoint.y - 4));
	    result.set(indexFinder(centralPoint.x + 1, centralPoint.y - 4));
	    result.set(indexFinder(centralPoint.x, centralPoint.y - 4));
	    result.set(indexFinder(centralPoint.x - 1, centralPoint.y - 3));	    
//	Returns the result.	    
	    return result;
	}
	
	public BitSet createBlock(Point centralPoint, BitSet original)
	{
//	Sets the result to the passed in BitSet	    
	    BitSet result = original;
	    
//	Here is where the block is created, specific cells are turned on.	    
	    result.set(indexFinder(centralPoint.x, centralPoint.y));
	    result.set(indexFinder(centralPoint.x - 1, centralPoint.y));
	    result.set(indexFinder(centralPoint.x, centralPoint.y - 1));
	    result.set(indexFinder(centralPoint.x - 1, centralPoint.y - 1));
	    
//	Returns the result.	    
	    return result;
	}
}
