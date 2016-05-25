import java.util.BitSet;

public class RedAIEngine
{
	private GameOfLife m_gameOfLife;
	private int m_columnCount;
	private int m_rowCount;
	private CoreAIEngine core;
	
	public RedAIEngine(GameOfLife game, int columnCount, int rowCount)
	{
		m_gameOfLife = game;
		m_columnCount = columnCount;
		m_rowCount = rowCount;
		core = new CoreAIEngine(m_columnCount, m_rowCount);
	}
	
	public BitSet createRedCore()
	{
	    BitSet result = m_gameOfLife.getData();
	    
	    result.set(core.indexFinder(0, m_columnCount - 1));
	    result.set(core.indexFinder(0, m_columnCount - 2));
	    result.set(core.indexFinder(1, m_columnCount - 1));
	    result.set(core.indexFinder(1, m_columnCount - 2));
	    result.set(core.indexFinder(2, m_columnCount - 3));
	    result.set(core.indexFinder(2, m_columnCount - 4));
	    result.set(core.indexFinder(3, m_columnCount - 3));
	    result.set(core.indexFinder(3, m_columnCount - 4));
	    
	    return result;
	}
}
