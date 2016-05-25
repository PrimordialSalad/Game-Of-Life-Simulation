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
	private JFrame m_frame;
	
	
    private int m_columnCount;
    private int m_rowCount;
    private GameOfLife m_gameOfLife;
    private GameOfLifeGridRenderer m_grid;
    private SpritePanel m_spritePanel;
    
    private JButton m_butClear;
    private JButton m_butGo;
    private JButton m_butStop;
    
    private JButton m_butBlinker;
    private JButton m_butToad;
    private JButton m_butBeacon;
    private JButton m_butPulsar;
    private JButton m_butPent;

    public GameOfLifeGui(GameOfLife game, int givenColumnCount, int givenRowCount)
    {
        m_columnCount = givenColumnCount;
    	m_rowCount = givenRowCount;
    	m_gameOfLife = game;
    	
    	m_grid = new GameOfLifeGridRenderer(this, m_columnCount, m_rowCount);
    	m_spritePanel = new SpritePanel();
    	
    	m_frame = new JFrame("GameOfLife");
    	
        m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m_frame.setLayout(new BorderLayout());
        m_spritePanel.setLayout(new BoxLayout(m_spritePanel, BoxLayout.PAGE_AXIS));
        m_frame.add(m_grid, BorderLayout.CENTER);
        m_frame.add(m_spritePanel, BorderLayout.EAST);
        
        m_butGo = new JButton("Go");
        m_butGo.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		m_gameOfLife.setRun(true);
        	}
        });
        
        m_butClear = new JButton("Clear");
        m_butClear.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		m_gameOfLife.clearGrid();
        		m_grid.repaint();
        	}
        });
        
        m_butStop = new JButton("Stop");
        m_butStop.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		m_gameOfLife.setRun(false);
        	}
        });
		
        m_frame.add(m_butGo, BorderLayout.NORTH);
        m_frame.add(m_butClear, BorderLayout.SOUTH);
        m_frame.add(m_butStop, BorderLayout.WEST);
        
        m_spritePanel.add(new JButton("Blinker"));
        m_spritePanel.add(new JButton("Toad"));
        m_spritePanel.add(new JButton("Beacon"));
        m_spritePanel.add(new JButton("Pulsar"));
        m_spritePanel.add(new JButton("Pentadecathlon"));
        
        m_frame.pack();
        m_frame.setLocationRelativeTo(null);
        m_frame.setVisible(true);
        
       EventQueue.invokeLater(new Runnable() 
       {
            @Override
            public void run() 
            {
                try 
                {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } 
                catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) 
                {
                	
                }
            }
        });
    }
    public void toggleCell(Point cellToToggle)
    {
    	m_gameOfLife.toggleCell(cellToToggle);
    }
    
    public BitSet getCellData()
    {
    	return m_gameOfLife.getData();
    }
    
    public void refresh()
    {
    	m_grid.repaint();
    }
}
