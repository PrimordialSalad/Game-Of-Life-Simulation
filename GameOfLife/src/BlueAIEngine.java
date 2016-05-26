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
	
	public BitSet createBlueCore() // The blue core is in the top left corner.
	{
	   BitSet result =  m_game.getData();
	   
	   result.set(core.indexFinder(0, 0));
	   result.set(core.indexFinder(1, 0));
	   result.set(core.indexFinder(0, 1));
	   result.set(core.indexFinder(1, 1));
	   result.set(core.indexFinder(2, 2));
	   result.set(core.indexFinder(3, 2));
	   result.set(core.indexFinder(2, 3));
	   result.set(core.indexFinder(3, 3));
	   
	   return result;
	}
	
	public void won()
	{
	    if(!(m_game.getData().get(core.indexFinder(0, m_columnCount - 1))))
		    {
			m_game.setWon();
		    }
	}
}
