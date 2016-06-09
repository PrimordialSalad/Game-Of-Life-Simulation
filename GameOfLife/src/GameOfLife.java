import java.awt.Point;
import java.util.BitSet;
import java.util.concurrent.TimeUnit;

public class GameOfLife
{
//	The grid's column count
	private int m_columnCount = 70;
//	The grid's row count
	private int m_rowCount = 70;
//	The BitSet which represents the board.
	private BitSet m_data = new BitSet(m_columnCount * m_rowCount);
//	The instantiation of the gui class.
	private GameOfLifeGui m_newGui = new GameOfLifeGui(this, m_columnCount, m_rowCount);
//	The instantiation of the generator class.
	private StepGenerator m_newGenerator = new StepGenerator(m_rowCount, m_columnCount);
//	The instantiation of the red AI engine.
	private RedAIEngine m_redAI = new RedAIEngine(this, m_columnCount, m_rowCount);
//	The instantiation of the blue AI engine.
	private BlueAIEngine m_blueAI = new BlueAIEngine(this, m_columnCount, m_rowCount);
//	The variable responsible for determining whether the program is exited.  Used by the AIs.
	private boolean m_notDone = true;
//	The variable responsible for the stopping and starting of the while loop.
	private boolean m_run = false;
	
	
	public static void main(String[] args)
	{
//	Used to run the whole program.  This is the only main function present.
		GameOfLife newGame = new GameOfLife();
		newGame.run();
	}
	public void run()
	{
//	This is the loop that is controlled by the buttons.
		while(m_notDone)
		{
			if(m_run)
			{
//			This is used to change the board dictated by the 4 rules.
				callToGenerate();
//			This is used to run the AI's.  Using the updated board.
				runAIs();
//			This is used to repaint after the board has been updated.
				m_newGui.refresh();
			}
			try
			{
//			Used so that the buttons are able to be used during run time.
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
//	This sets the whole grid to false.  Causing the grid to turn all white.
		m_data.clear();
	}
	
	public boolean setRun(boolean doRun)
	{
//	This is used to cause the loop to run.  Used by the Go button.
		boolean result = m_run;
		m_run = doRun;
		return result;
	}
	
	public void setWon()
	{
//	This is used by the AIs when either of them have won.
		m_notDone = false;
	}
	
	public void callToGenerate()
	{
//	This is the function responsible for passing the board to be recalculated based on the 4 rules.
		m_data = (BitSet)(m_newGenerator.generate(m_data).clone());
	}
	
	public void unsetCell(int row, int column)
	{
//	This is used to unset a certain cell.  Used in conjunction with set cell.
		final int cellToUnsetIndex = column + (row * m_columnCount);
		
		m_data.clear(cellToUnsetIndex);
	}
	
    public void setCell(int row, int column)
    {
//	Used to set the cell.  Used in conjunction with unset cell.
    	final int cellToSetIndex = column + (row * m_columnCount);
    	
    	m_data.set(cellToSetIndex);
    }
	
    public void toggleCell(Point cellToToggle)
    {
//	This is used to toggle a certain point on and off.
    	final int cellToToggleIndex = cellToToggle.x + (cellToToggle.y * m_columnCount);
    	
    	m_data.flip(cellToToggleIndex);
    }
    
    public BitSet getData()
    {
//	Used to return the board data that is housed in this class.
    	return m_data;
    }
    
    public void setData(BitSet dataToSet)
    {
//	Used to set the board data that is housed in this class.
    	m_data = dataToSet;
    }
    
    public void createAICores()
    {
//	Creates the AI's cores that they are trying to destroy and protect.
    	
    	m_data = m_blueAI.createBlueCore();
    	m_data = m_redAI.createRedCore();
	
    }
    
    public void runAIs()
    {
//	Used so the call to both run functions are compacted and not spread out everywhere.	
	m_blueAI.run(m_data);
	m_redAI.run(m_data);
    }
    
    public int[] giveBlueSafeIndexes()
    {
//	Gives the data of the safe indexes from the Blue AI class.
	return m_blueAI.giveSafeIndexes();
    }
    
    public int[] giveRedSafeIndexes()
    {
//	Gives the data of the safe indexes from the Red AI class.	
	return m_redAI.giveSafeIndexes();
    }
}
