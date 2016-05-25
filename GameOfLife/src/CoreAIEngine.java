
public class CoreAIEngine
{
	private int m_columnCount;
	private int m_rowCount;
	
	public CoreAIEngine(int columnCount, int rowCount)
	{
		m_columnCount = columnCount;
		m_rowCount = rowCount;
	}
	
	public int indexFinder(int row, int column)
	{
	    return ((row * m_columnCount) + column);
	}
	
}
