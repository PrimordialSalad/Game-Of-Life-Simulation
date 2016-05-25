import java.util.BitSet;

public class StepGenerator
{
    private BitSet m_tempData;
    private int m_columnCount;
    private int m_rowCount;
    
    public StepGenerator(int givenRowCount, int givenColumnCount)
    {
    	m_columnCount = givenColumnCount;
    	m_rowCount = givenRowCount;
    	m_tempData = new BitSet(lengthFinder());
    }
    
    public int lengthFinder()
    {
    	return (m_columnCount * m_rowCount);
    }
    
    public BitSet generate(BitSet original)
    {
    	final int MIN_X = 0;
    	final int MIN_Y = 0;
    	final int MAX_X = m_columnCount - 1;
    	final int MAX_Y = m_rowCount - 1;
    	
    	for(int r = 0; r < m_rowCount; r++)
    	{
    		for(int c = 0; c < m_columnCount; c++)
    		{
    	    	int numOfLiveCells = 0;
    			final boolean centerCell = original.get(indexFinder(r, c));
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
				    		numOfLiveCells++;
				    	}
	    	    	}
    			}
    	    	
    	    	if((!centerCell) && (numOfLiveCells == 3))
    	    	{
    	    		m_tempData.set(indexFinder(r, c), true);
    	    	}
    	    	else
    	    	{
    	    		if(numOfLiveCells > 3)
    	    		{
    	    			m_tempData.set(indexFinder(r, c), false);
    	    		}
    	    		else if((numOfLiveCells == 2) || (numOfLiveCells == 3))
    	    		{
    	    			m_tempData.set(indexFinder(r, c), original.get(indexFinder(r, c)));
    	    		}
    	    		else if(numOfLiveCells < 2)
    	    		{
    	    			m_tempData.set(indexFinder(r, c), false);
    	    		}
    	    	}
    		}	
    	}
    	
    	return m_tempData;
    	
    }
    
    private int indexFinder(int row, int column)
    {
    	return ((row * m_columnCount) + column);
    }
    
    
}
