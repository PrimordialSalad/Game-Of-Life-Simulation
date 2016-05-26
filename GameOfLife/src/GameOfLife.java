import java.awt.Point;
import java.util.BitSet;
import java.util.concurrent.TimeUnit;

public class GameOfLife
{
	private int m_columnCount = 70;
	private int m_rowCount = 70;
	private BitSet m_data = new BitSet(m_columnCount * m_rowCount);
	private GameOfLifeGui m_newGui = new GameOfLifeGui(this, m_columnCount, m_rowCount);
	private StepGenerator m_newGenerator = new StepGenerator(m_rowCount, m_columnCount);
	private RedAIEngine m_redAI = new RedAIEngine(this, m_columnCount, m_rowCount);
	private BlueAIEngine m_blueAI = new BlueAIEngine(this, m_columnCount, m_rowCount);
	private boolean m_notDone = true;
	private boolean m_run = false;
	private Point gliderCenter = new Point(36,36); // This needs to be recreated.  Here for testing.
	
	public static void main(String[] args)
	{
		GameOfLife newGame = new GameOfLife();
		newGame.run();
	}
	public void run()
	{
		while(m_notDone)
		{
			if(m_run)
			{
				callToGenerate();
				m_newGui.refresh();
			}
			try
			{
				TimeUnit.MILLISECONDS.sleep(150);
			}
			catch(InterruptedException e)
			{
//				Thread.currentThread().interrupt();
			}
		}
		m_run = false;
	}
	
	public void clearGrid()
	{
		m_data.clear();
	}
	
	public boolean setRun(boolean doRun)
	{
		boolean result = m_run;
		m_run = doRun;
		return result;
	}
	
	public void setWon()
	{
		m_notDone = false;
	}
	
	public void callToGenerate()
	{
		m_data = (BitSet)(m_newGenerator.generate(m_data).clone());
	}
	
    public void toggleCell(Point cellToToggle)
    {
    	final int cellToToggleIndex = cellToToggle.x + (cellToToggle.y * m_columnCount);
    	
    	m_data.flip(cellToToggleIndex);
    }
    
    public BitSet getData()
    {
    	return m_data;
    }
    
 // Red has a big disadvantage because it is easy to get gliders to move down.  Red has to get them to move up.
    public void createAICores()
    {
	m_data = m_blueAI.createBlueCore();
	m_data = m_redAI.createRedCore();
    }
    
    public void createGliders()
    {
	m_data = m_redAI.createGlider(gliderCenter);
    }
    
    public int indexFinder(int row, int column)
    {
	return ((row * m_columnCount) + column);
    }
}