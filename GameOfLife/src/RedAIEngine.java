import java.awt.Point;
import java.util.BitSet;

public class RedAIEngine
{
	private GameOfLife m_game;
	private int m_columnCount;
	private int m_rowCount;
	private CoreAIEngine core;
	
	public RedAIEngine(GameOfLife game, int columnCount, int rowCount)
	{
		m_game = game;
		m_columnCount = columnCount;
		m_rowCount = rowCount;
		core = new CoreAIEngine(m_columnCount, m_rowCount);
	}
	
	public BitSet createRedCore() // The red core is in the bottom right corner.
	{
	    BitSet result = m_game.getData();
	    
	    result.set(core.indexFinder(m_rowCount - 1, m_columnCount - 1));
	    result.set(core.indexFinder(m_rowCount - 1, m_columnCount - 2));
	    result.set(core.indexFinder(m_rowCount - 2, m_columnCount - 1));
	    result.set(core.indexFinder(m_rowCount - 2, m_columnCount - 2));
	    result.set(core.indexFinder(m_rowCount - 3, m_columnCount - 3));
	    result.set(core.indexFinder(m_rowCount - 3, m_columnCount - 4));
	    result.set(core.indexFinder(m_rowCount - 4, m_columnCount - 3));
	    result.set(core.indexFinder(m_rowCount - 4, m_columnCount - 4));
	    
	    return result;
	}
	
	public void won()
	{
	    if(!(m_game.getData().get(core.indexFinder(m_rowCount - 1, 0))))
	    {
		m_game.setWon();
	    }
	}
	
	public BitSet createGlider(Point centralPoint)
	{
	    BitSet result = m_game.getData();
	    
	    result.set(core.indexFinder(centralPoint.x, centralPoint.y));
	    result.set(core.indexFinder(centralPoint.x, centralPoint.y -1));
	    result.set(core.indexFinder(centralPoint.x, centralPoint.y - 2));
	    result.set(core.indexFinder(centralPoint.x - 1, centralPoint.y));
	    result.set(core.indexFinder(centralPoint.x - 2, centralPoint.y - 1));
	    
	    return result;
	}
}
