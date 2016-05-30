import java.awt.Dimension;
import java.util.BitSet;

import javax.swing.JPanel;

public class SpritePanel extends JPanel
{
	private int mode = 0;
	private GameOfLifeGridRenderer m_grid;

	public SpritePanel(int columnCount, int rowCount, GameOfLifeGui gameGui)
	{
		m_grid = new GameOfLifeGridRenderer(gameGui, columnCount, rowCount);
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(150, 350);
	}
	
	public void createBlinker()
	{
		
	}
	
	public void createToad()
	{
		
	}
	
	public void createBeacon()
	{
		
	}
	
	public void createPulsar()
	{
		
	}
	
	public void createPent()
	{
		
	}
	
}
