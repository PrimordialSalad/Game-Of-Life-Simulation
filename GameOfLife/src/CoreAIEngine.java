import java.awt.Point;

public class CoreAIEngine
{
	private RedAIEngine m_red;
	private BlueAIEngine m_blue;
	private int m_columnCountR;
	private int m_rowCountR;
	private int m_columnCountB;
	private int m_rowCountB;
	
	public CoreAIEngine(RedAIEngine redAI, int columnCount, int rowCount)
	{
		m_red = redAI;
		m_columnCountR = columnCount;
		m_rowCountR = rowCount;
	}
	public CoreAIEngine(BlueAIEngine blueAI, int columnCount, int rowCount)
	{
		m_blue = blueAI;
		m_columnCountB = columnCount;
		m_rowCountB = rowCount;
	}
	
}
