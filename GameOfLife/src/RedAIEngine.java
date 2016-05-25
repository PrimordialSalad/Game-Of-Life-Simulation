
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
		core = new CoreAIEngine(this, m_columnCount, m_rowCount);
	}
}
