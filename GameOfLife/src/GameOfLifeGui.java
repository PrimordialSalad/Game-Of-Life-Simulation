import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.BitSet;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GameOfLifeGui
{
// The main frame component of my GUI.
    private JFrame m_frame;
	
//	The column Count of the grid.
    private int m_columnCount;
//	The row count of the grid
    private int m_rowCount;
//	The instantiation of the game of life class.
    private GameOfLife m_gameOfLife;
//	The instantiation of the grid renderer class.
    private GameOfLifeGridRenderer m_grid;
//	The instantiation of the sprite panel class.
    private SpritePanel m_spritePanel;
    
//	The JButton "Clear".
    private JButton m_butClear;
//	The JButton "Go".
    private JButton m_butGo;
//	The JButton "Stop".
    private JButton m_butStop;
    
//	The JButton "Blinker".
    private JButton m_butBlinker;
//	The JButton "Toad".
    private JButton m_butToad;
//	The JButton "Beacon".c
    private JButton m_butBeacon;
//	The JButton "Pulsar".
    private JButton m_butPulsar;
//	The JButton Pentadecathlon
    private JButton m_butPent;

    public GameOfLifeGui(GameOfLife game, int givenColumnCount, int givenRowCount)
    {
//	The columnCount is set to what is passed in from the GameOfLife class.
        m_columnCount = givenColumnCount;
//	The rowCount is set to what is passed in from the GameOfLife class.
    	m_rowCount = givenRowCount;
//	The gameOfLife instantiation is set to the GameOfLife passing it itself.
    	m_gameOfLife = game;
    	
//	The grid renderer class instantiation is set to a new instance of that class.
    	m_grid = new GameOfLifeGridRenderer(this, m_columnCount, m_rowCount);
//	The sprite panel class instantiation is set to an new instance of that class.
    	m_spritePanel = new SpritePanel(m_grid);
    	
//	The frame is set as a new JFrame.
    	m_frame = new JFrame("GameOfLife");
    	
//	This is where the x-button on the window is detailed. The window closes when that button is clicked.
        m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	The frame's border is set here to a border layout.
        m_frame.setLayout(new BorderLayout());
//	The layout of the sprite panel is set up here to a box layout.
        m_spritePanel.setLayout(new BoxLayout(m_spritePanel, BoxLayout.PAGE_AXIS));
        
//	The grid is added to the frame.
        m_frame.add(m_grid, BorderLayout.CENTER);
//	The sprite panel is added to the frame.
        m_frame.add(m_spritePanel, BorderLayout.EAST);
        
//	The "Go" button is created and it is added as a new action listener.
        m_butGo = new JButton("Go");
        m_butGo.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
//		When the button is clicked it does these functions.
        		m_gameOfLife.setRun(true);
        		m_gameOfLife.createAICores();
        	}
        });
        
//	The "Clear" button is created and it is added as a new action listener.        
        m_butClear = new JButton("Clear");
        m_butClear.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
//		When the button is clicked it does these functions.        	 
        		m_gameOfLife.clearGrid();
        		m_grid.repaint();
        	}
        });
        
//	The "Stop button is created and it is added as a new action listener.        
        m_butStop = new JButton("Stop");
        m_butStop.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
//		When the button is clicked it does this function.        	    
        		m_gameOfLife.setRun(false);
        	}
        });
		
//	The "Go" button is added to the frame.        
        m_frame.add(m_butGo, BorderLayout.NORTH);
//	The "Clear" button is added to the frame.       
        m_frame.add(m_butClear, BorderLayout.SOUTH);
//	The "Stop" button is added to the frame.        
        m_frame.add(m_butStop, BorderLayout.WEST);
        
        
//	The "Blinker" button is created and it is added as a new action listener.        
        m_butBlinker = new JButton("Blinker");
        m_butBlinker.addActionListener(new ActionListener()
        	{
        		public void actionPerformed(ActionEvent e)
        		{
//			When the button is pressed it executes this function.        		    
        			m_spritePanel.createBlinker();
        		}
        	});
        
//	The "Toad" button is created and it is added as a new action listener.        
        m_butToad = new JButton("Toad");
        m_butToad.addActionListener(new ActionListener()
        	{
        		public void actionPerformed(ActionEvent e)
        		{
//			When the button is pressed this function is executed.        		            		
        			m_spritePanel.createToad();
        		}
        	
        	});
        
//	The "Beacon" button is created and added as a new action listener.
        m_butBeacon = new JButton("Beacon");
        m_butBeacon.addActionListener(new ActionListener()
        	{
        		public void actionPerformed(ActionEvent e)
        		{
//			When the button is pressed it executes this function.        		   
        			m_spritePanel.createBeacon();
        		}
        	});
        
//	The "Pulsar" button is created and added as a new action listener.        
        m_butPulsar = new JButton("Pulsar");
        m_butPulsar.addActionListener(new ActionListener()
        	{            
        		public void actionPerformed(ActionEvent e)
        		{
//			When the button is pressed this function executes.        		    
        			m_spritePanel.createPulsar();
        		}
        	});
        
//	The "Pentadecathlon" button is created and added as a new action listener.        
        m_butPent = new JButton("Pentadecathlon");
        m_butPent.addActionListener(new ActionListener()
        	{
        		public void actionPerformed(ActionEvent e)
        		{
//			When the button is pressed this function is executed.        		    
        			m_spritePanel.createPent();
        		}
        	});
//	The "Blinker" button is added to the sprite panel.        
        m_spritePanel.add(m_butBlinker);
//	The "Toad" button is added the the sprite panel.        
        m_spritePanel.add(m_butToad);
//	The "Beacon" button is added to the sprite panel.        
        m_spritePanel.add(m_butBeacon);
//	The "Pulsar" button is added to the sprite panel.        
        m_spritePanel.add(m_butPulsar);
//	The "Pentadecathlon" button is added to the sprite panel.        
        m_spritePanel.add(m_butPent);
        
//	A function to initiate the frame.        
        m_frame.pack();
//	Sets the frame in the middle of the screen.        
        m_frame.setLocationRelativeTo(null);
//	Sets the frame visible to the user.        
        m_frame.setVisible(true);
       
//	This event queue is used to catch any exceptions that are created when the GUI is created.        
       EventQueue.invokeLater(new Runnable() 
       {
            @Override
            public void run() 
            {
                try 
                {
//		This is used to set the look of the frame to one similar to the OS window's.                    
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } 
                catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) 
                {
                	
                }
            }
        });
    }
    
    public void unsetCell(int row, int column)
    {
//	This is used to unset a cell at a given row and column.  Used in conjunction with set cell.	
    	m_gameOfLife.unsetCell(row, column);
    }
    
    public void setCell(int row, int column)
    {
//	This is used to set a cell at a given row and column.  Used in conjunction with unset cell.
    	m_gameOfLife.setCell(row, column);
    }
    
    public void setCellData(BitSet data)
    {
//	This sets the data of the BitSet in the GameOfLife class.	
    	m_gameOfLife.setData(data);
    }
    
    public void toggleCell(Point cellToToggle)
    {
//	This toggles a single cell in accordance with the point that is given.	
    	m_gameOfLife.toggleCell(cellToToggle);
    }
    
    public BitSet getCellData()
    {
//	This gets the data BitSet to be used in classes like the GridRenderer and the SpritePanel.	
    	return m_gameOfLife.getData();
    }
    
    public void refresh()
    {
//	This causes the grid to redraw itself.  Refreshing the view of the grid.	
    	m_grid.repaint();
    }
    
    public int[] giveBlueSafeIndexes()
    {
//	This transfers the data to the grid renderer class.  From the Blue AI.	
	return m_gameOfLife.giveBlueSafeIndexes();
    }
    
    public int[] giveRedSafeIndexes()
    {
//	This transfers the data to the grid renderer class.  From the Red AI.	
	return m_gameOfLife.giveRedSafeIndexes();
    }
}
