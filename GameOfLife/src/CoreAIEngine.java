import java.awt.Point;
import java.util.BitSet;

public class CoreAIEngine
{
	private int m_columnCount;
	private int m_rowCount;
	private BitSet m_tempData;
	
	public CoreAIEngine(int columnCount, int rowCount)
	{
		m_columnCount = columnCount;
		m_rowCount = rowCount;
		m_tempData = new BitSet(lengthFinder());
	}
	
	public int indexFinder(int row, int column)
	{
	    return ((row * m_columnCount) + column);
	}
	
	public int lengthFinder()
	{
	    return (m_columnCount * m_rowCount);
	}
	
	public BitSet createGlider(Point centralPoint)
	{
	    BitSet result = m_tempData;
	    
	    result.set(indexFinder(centralPoint.x, centralPoint.y));
	    result.set(indexFinder(centralPoint.x, centralPoint.y -1));
	    result.set(indexFinder(centralPoint.x, centralPoint.y - 2));
	    result.set(indexFinder(centralPoint.x - 1, centralPoint.y));
	    result.set(indexFinder(centralPoint.x - 2, centralPoint.y - 1));
	    
	    return result;
	}
	
}
