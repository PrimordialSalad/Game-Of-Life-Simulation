import java.awt.Dimension;

import javax.swing.JPanel;

public class SpritePanel extends JPanel
{
//	An instantiation of the GridRenderer class.    
	private GameOfLifeGridRenderer m_grid;

	public SpritePanel(GameOfLifeGridRenderer grid)
	{
//		The instantiation of the grid renderer class is set to the grid passed by the GUI class.	    
		m_grid = grid;
	}
	
	public Dimension getPreferredSize()
	{
//	Used to get the size of the panel that will be added.	    
		return new Dimension(150, 350);
	}
	
	public void createBlinker()
	{
//	Calls the function in the Grid Renderer class to set the mode to create a blinker.	    
		m_grid.setMode(1);
	}
	
	public void createToad()
	{
//	Calls the function in the Grid Renderer class to set the mode to create a toad.	    
		m_grid.setMode(2);
	}
	
	public void createBeacon()
	{
//	Calls the function in the Grid Renderer class to set the mode to create a beacon.	    
		m_grid.setMode(3);
	}
	
	public void createPulsar()
	{
//	Calls the function in the Grid Renderer class to set the mode to create a pulsar.	    
		m_grid.setMode(4);
	}
	
	public void createPent()
	{
//	Calls the function in the Grid Renderer class to set the mode to create a pentadecathlon.	    
		m_grid.setMode(5);
	}
	
}
