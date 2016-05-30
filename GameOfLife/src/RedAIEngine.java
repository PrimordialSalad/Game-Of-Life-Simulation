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
	
	public BitSet createRedCore() // The red core is on the right hand side.
	{
	    BitSet result = m_game.getData();
	    
	    result.set(core.indexFinder(m_rowCount - (m_rowCount / 2), m_columnCount - 1));
	    result.set(core.indexFinder(m_rowCount - (m_rowCount / 2) + 1, m_columnCount - 2));
	    result.set(core.indexFinder(m_rowCount - (m_rowCount / 2) + 1, m_columnCount - 1));
	    result.set(core.indexFinder(m_rowCount - (m_rowCount / 2), m_columnCount - 2));
	    result.set(core.indexFinder(m_rowCount - (m_rowCount / 2) + 3, m_columnCount - 3));
	    result.set(core.indexFinder(m_rowCount - (m_rowCount / 2) + 2, m_columnCount - 4));
	    result.set(core.indexFinder(m_rowCount - (m_rowCount / 2) + 2, m_columnCount - 3));
	    result.set(core.indexFinder(m_rowCount - (m_rowCount / 2) + 3, m_columnCount - 4));
	    
	    return result;
	}
	
	public void won()
	{
	    if(!(m_game.getData().get(core.indexFinder(m_rowCount - (m_rowCount / 2), 0))))
	    {
	    	m_game.setWon();
	    }
	}
}
