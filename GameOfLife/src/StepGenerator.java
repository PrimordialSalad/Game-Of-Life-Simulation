import java.util.BitSet;

public class StepGenerator
{
//	The temporary BitSet that is used to determine the new alive and dead cells.    
    private BitSet m_tempData;
//	The columnCount of the grid.    
    private int m_columnCount;
//	The rowCount of the grid.    
    private int m_rowCount;
    
    public StepGenerator(int givenRowCount, int givenColumnCount)
    {
//	The columnCount is set to the columnCount given by the GameOfLife class.	
    	m_columnCount = givenColumnCount;
//	The rowCount is set to the rowCount given by the GameOfLife class.    	
    	m_rowCount = givenRowCount;
//	The tempData is created using the length finder function and the values passed by the GameOfLife class.    	
    	m_tempData = new BitSet(lengthFinder());
    }
    
    public int lengthFinder()
    {
//	This returns the grid as a 1D representation.	
    	return (m_columnCount * m_rowCount);
    }
    
    public BitSet generate(BitSet original)
    {
//	Setting the starting and end points on the grid.	
    	final int MIN_X = 0;
    	final int MIN_Y = 0;
    	final int MAX_X = m_columnCount - 1;
    	final int MAX_Y = m_rowCount - 1;
    	
    	for(int r = 0; r < m_rowCount; r++)
    	{
    		for(int c = 0; c < m_columnCount; c++)
    		{
//    		The counter for the number of live cells around the central one.    
    	    	int numOfLiveCells = 0;
    	    	
//		The center cell's state is saved.    	    	
    	    	final boolean centerCell = original.get(indexFinder(r, c));
    	    	
//		Finding the end and start positions for both x and y.  Mainly used for cells on the outer edges.    	    	
    		final int startPosX = (c - 1 < MIN_X) ? c : c - 1;
    		final int endPosX = (c + 1 > MAX_X) ? c : c + 1;
    	    	final int startPosY = (r - 1 < MIN_Y) ? r : r - 1;
    	    	final int endPosY = (r + 1 > MAX_Y) ? r : r + 1;
    	    	
    	    	for(int rowNum = startPosY; rowNum <= endPosY; rowNum++)
    	    	{
	    	    	for(int colNum = startPosX; colNum <= endPosX; colNum++)
	    	    	{
	    	    		final boolean bit = original.get(indexFinder(rowNum, colNum));
				    	if( !((rowNum == r) && (colNum == c)) && (bit == true) )
				    	{
//					Increments the number of live cells around the central one.				    	    
				    		numOfLiveCells++;
				    	}
	    	    	}
    			}
//		This is where the Conway's Game Of Life rules are used to find the live and dead cells.    	    	
    	    	if((!centerCell) && (numOfLiveCells == 3))
    	    	{
//		Sets the cell true if it has exactly 3 neighbors.    	    	    
    	    		m_tempData.set(indexFinder(r, c), true);
    	    	}
    	    	else
    	    	{
    	    		if(numOfLiveCells > 3)
    	    		{
//			The cell dies if it has more than three neighbors.    	    		    
    	    			m_tempData.set(indexFinder(r, c), false);
    	    		}
    	    		else if((numOfLiveCells == 2) || (numOfLiveCells == 3))
    	    		{
//			The cell stays the same if it has exactly 2 or 3 neighbors alive.    	    		    
    	    			m_tempData.set(indexFinder(r, c), original.get(indexFinder(r, c)));
    	    		}
    	    		else if(numOfLiveCells < 2)
    	    		{
//			If it has less than 2 live neighbors then it dies.    	    		   
    	    			m_tempData.set(indexFinder(r, c), false);
    	    		}
    	    	}
    		}	
    	}
//	Returns the new data with the 4 rules applied.  This is set as the current data.    	
    	return m_tempData;
    	
    }
    
    private int indexFinder(int row, int column)
    {
//	Returns the index of the 1D location so the coder can keep thinking in 2D.	
    	return ((row * m_columnCount) + column);
    }
    
    
}
