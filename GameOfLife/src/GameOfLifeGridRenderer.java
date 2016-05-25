import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.BitSet;

import javax.swing.JPanel;



public class GameOfLifeGridRenderer extends JPanel
{	
	
    private Point mouseOverCell = null;
    private Point clickedCell = null;
    private int m_columnCount;
    private int m_rowCount;
    private GameOfLifeGui m_gameGui;
    
    
    public GameOfLifeGridRenderer(GameOfLifeGui gameGui, int columnCount, int rowCount)
    {
    	m_columnCount = columnCount;
    	m_rowCount = rowCount;
    	m_gameGui = gameGui;
    	
    	MouseAdapter mouseMotion = new MouseAdapter()
    			{
    				public void mouseMoved(MouseEvent m)
    				{
    					
    					int width = getWidth();
    					int height = getHeight();
    					
    					int cellWidth = width / m_columnCount;
    					int cellHeight = height / m_rowCount;
    					
    					int xOffset = (width - (m_columnCount * cellWidth)) / 2;
    					int yOffset = (height - (m_rowCount * cellHeight)) / 2;
    					
    					mouseOverCell = null;
    					if(m.getX() >= xOffset && m.getY() >= yOffset)
    					{
    						int column = (m.getX() - xOffset) / cellWidth;
    						int row = (m.getY() - yOffset) / cellHeight;
    						
    						if(column >= 0 && row >= 0 && column < m_columnCount && row < m_rowCount)
    						{
    							mouseOverCell = new Point(column, row);
    						}
    					}
    					
    					repaint();
    				}
    			};
    			addMouseMotionListener(mouseMotion);
    	
    	MouseAdapter mouseClick = new MouseAdapter()
    			{
    				public void mouseClicked(MouseEvent m)
    				{
    					int width = getWidth();
    					int height = getHeight();
    					
    					int cellWidth = width / m_columnCount;
    					int cellHeight = height / m_rowCount;
    					
    					int xOffset = (width - (m_columnCount * cellWidth)) / 2;
    					int yOffset = (height - (m_rowCount * cellHeight)) / 2;
    					
    					clickedCell = null;
    					if(m.getX() >= xOffset && m.getY() >= yOffset)
    					{
    						int column = (m.getX() - xOffset) / cellWidth;
    						int row = (m.getY() - yOffset) / cellHeight;
    						
    						if(column >=0 && row >= 0 && column < m_columnCount && row < m_rowCount)
    						{
    							clickedCell = new Point(column, row);
    							m_gameGui.toggleCell(clickedCell);
    						}
    					}
    					repaint();
    				}
    			};
    			addMouseListener(mouseClick);
    }
    
    public Dimension getPreferredSize()
    {
    	return new Dimension(700, 700);
    }
    
    public void invalidate()
    {
    	mouseOverCell = null;
    	super.invalidate();
    }
    
    public void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
    	Graphics2D g2d = (Graphics2D) g.create();
    
    	final BitSet cells = m_gameGui.getCellData();
    	
    	int width = getWidth();
    	int height = getHeight();
    	
    	int cellWidth = width / m_columnCount;
    	int cellHeight = height / m_rowCount;
    	
    	int xOffset = (width - (m_columnCount * cellWidth)) / 2;
    	int yOffset = (height - (m_rowCount * cellHeight)) / 2;
    	
    	
    	
    	if(cells.size() >= m_columnCount * m_rowCount)
    	{
    		for(int r = 0; r < m_rowCount; r++)
    		{
    			for(int c = 0; c < m_columnCount; c++)
    			{
    				final int index = (r * m_columnCount) + c;
    				
    				if(cells.get(index))
    				{
						g2d.setColor(Color.BLACK);
    				}
    				else
    				{
    					g2d.setColor(Color.WHITE);
    				}
    				if(mouseOverCell != null)
    				{
	    				final int selectedIndex = mouseOverCell.x + (mouseOverCell.y * m_columnCount);
	    				
	    				if(selectedIndex == index)
	    				{
	    					g2d.setColor(Color.BLUE);
	    				}
    				}
    				
    				Rectangle cell = new Rectangle(
							xOffset + (c * cellWidth),
							yOffset + (r * cellHeight),
							cellWidth,
							cellHeight);
					g2d.fill(cell);
					
    				g2d.setColor(Color.GRAY);
    				g2d.draw(cell);
    			}
    		}
    	}
    	
    	g2d.dispose();
    }
    
}
