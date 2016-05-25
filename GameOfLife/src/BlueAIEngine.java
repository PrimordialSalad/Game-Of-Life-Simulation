import java.util.BitSet;

public class BlueAIEngine
{
	private GameOfLife m_game;
	private int m_columnCount;
	private int m_rowCount;
	private CoreAIEngine core;
	
	public BlueAIEngine(GameOfLife game, int columnCount, int rowCount)
	{
		m_game = game;
		m_columnCount = columnCount;
		m_rowCount = rowCount;
		core = new CoreAIEngine(m_columnCount, m_rowCount);
	}
	
	public BitSet createBlueCore()
	{
	   BitSet result =  m_game.getData();
	   
	   result.set(core.indexFinder(m_rowCount - 1, 0));
	   result.set(core.indexFinder(m_rowCount - 2, 0));
	   result.set(core.indexFinder(m_rowCount - 1, 1));
	   result.set(core.indexFinder(m_rowCount - 2, 1));
	   result.set(core.indexFinder(m_rowCount - 3, 2));
	   result.set(core.indexFinder(m_rowCount - 4, 2));
	   result.set(core.indexFinder(m_rowCount - 3, 3));
	   result.set(core.indexFinder(m_rowCount - 4, 3));
	   
	   return result;
	}
}
