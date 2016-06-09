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
//	This point tracks which cell the mouse is hovering over.	
    private Point m_mouseOverCell = null;
//	This point tracks which cell the mouse has clicked on.    
    private Point m_clickedCell = null;
//	This is the column count of the grid.    
    private int m_columnCount;
//	This is the row count of the grid.    
    private int m_rowCount;
//	This is the instantiation of the game of life GUI class.    
    private GameOfLifeGui m_gameGui;
//	This is the variable for the mode.  This controls what sprite is to be drawn.    
    private int m_mode = 0;
    
    
    public GameOfLifeGridRenderer(GameOfLifeGui gameGui, int columnCount, int rowCount)
    {
//	This is where the column count passed in by the GUI class is saved.	
    	m_columnCount = columnCount;
//	This is where the row count passed in by the GUI class is saved.    	
    	m_rowCount = rowCount;
//	This is where the instantiation is set by the GUI class passing itself in.
    	m_gameGui = gameGui;
    	
//	This is the mouse adapter responsible for the hovering feature.    	
    	MouseAdapter mouseMotion = new MouseAdapter()
    			{
    				public void mouseMoved(MouseEvent m)
    				{
//				Used to find the width of the grid.    					
    					int width = getWidth();
//				Used to find the height of the grid.    					
    					int height = getHeight();
    					
//				Used to calculate the cell width.    					
    					int cellWidth = width / m_columnCount;
//				Used to calculate the cell height.    					
    					int cellHeight = height / m_rowCount;
    					
//				Used to figure out the xoffset of the cells.    					
    					int xOffset = (width - (m_columnCount * cellWidth)) / 2;
//				Used to figure out the yoffset of the cells.    					
    					int yOffset = (height - (m_rowCount * cellHeight)) / 2;
    					
//				This is where the point is set as null.    					
    					m_mouseOverCell = null;
    					if(m.getX() >= xOffset && m.getY() >= yOffset)
    					{
//					Used to figure out what cell the mouse is in.    					    
    						int column = (m.getX() - xOffset) / cellWidth;
    						int row = (m.getY() - yOffset) / cellHeight;
    						
    						if(column >= 0 && row >= 0 && column < m_columnCount && row < m_rowCount)
    						{
//						This is where the point is set, the cell's column and row.		    
    							m_mouseOverCell = new Point(column, row);
    						}
    					}
//					This is where the grid repaints itself.    					
    					repaint();
    				}
    			};
//			Adds a mouse motion listener.    			
    			addMouseMotionListener(mouseMotion);
    	
//		The mouse event responsible for the mouse clicked event.    			
    	MouseAdapter mouseClick = new MouseAdapter()
    			{
    				public void mouseClicked(MouseEvent m)
    				{
//				Used to find the width of the grid.  		    
    					int width = getWidth();
//				Used to find the height of the grid.    					
    					int height = getHeight();
    					
//				Used to find the width of each cell.    					
    					int cellWidth = width / m_columnCount;
//				Used to find the height of each cell.    					
    					int cellHeight = height / m_rowCount;
    					
//				Used to find the xoffset of the cells.    					
    					int xOffset = (width - (m_columnCount * cellWidth)) / 2;
//				Used to find the yoffset of the cells.    					
    					int yOffset = (height - (m_rowCount * cellHeight)) / 2;
    					
//				This is where the point is set to null.    					
    					m_clickedCell = null;
    					if(m.getX() >= xOffset && m.getY() >= yOffset)
    					{
//					Used to figure out what cell the mouse is in.    					    
    						int column = (m.getX() - xOffset) / cellWidth;
    						int row = (m.getY() - yOffset) / cellHeight;
    						
    						if(column >=0 && row >= 0 && column < m_columnCount && row < m_rowCount)
    						{
//						This is where the point is set, the cell's row and column.    						    
    							m_clickedCell = new Point(column, row);
//						This is the call to draw a sprite around the given point.    							
    							setPointBasedOnMode(m_clickedCell);
    						}
    					}
//					Used to repaint the grid.    					
    					repaint();
    				}
    			};
//			Adds a mouse listener. 			
    			addMouseListener(mouseClick);
    }
    
    public Dimension getPreferredSize()
    {
//	Returns the dimensions of the panel, used to draw the grid.	
    	return new Dimension(700, 700);
    }
    
    public void invalidate()
    {
//	Causes the cells to become invalid.	
    	m_mouseOverCell = null;
    	super.invalidate();
    }
    
    public void paintComponent(Graphics g)
    {
//	The function responsible for painting the grid.	
    	super.paintComponent(g);
//	This is a Java API class used to create 2D graphics.    	
    	Graphics2D g2d = (Graphics2D) g.create();

//	This is where the cell data comes to, to be drawn.    	
    	final BitSet cells = m_gameGui.getCellData();
    	
//	Asks for the array of safe indexes for the blue AI.    	
    	final int[] blueSafeIndexes = m_gameGui.giveBlueSafeIndexes();
//	Asks for the array of safe indexes for the red AI.    	
    	final int[] redSafeIndexes = m_gameGui.giveRedSafeIndexes();
    	
///////	Same as above/////////    	
    	int width = getWidth();
    	int height = getHeight();
    	
    	int cellWidth = width / m_columnCount;
    	int cellHeight = height / m_rowCount;
    	
    	int xOffset = (width - (m_columnCount * cellWidth)) / 2;
    	int yOffset = (height - (m_rowCount * cellHeight)) / 2;
///////Same as above///////    	
    	
    	
//	Checks the size of the passed in BitSet is the same size.    	
    	if(cells.size() >= m_columnCount * m_rowCount)
    	{
    		for(int r = 0; r < m_rowCount; r++)
    		{
    			for(int c = 0; c < m_columnCount; c++)
    			{
//			The two for loops to cycle through the entirety of the grid.
//			The 1D index from the 2D for loops.    			    
    				final int index = (r * m_columnCount) + c;
    				
    				if(cells.get(index))
    				{
//				If the cells are true, paint them black.    				    
						g2d.setColor(Color.BLACK);
    				}
    				else
    				{
//				If the cells are false, then paint them white.    				    
    					g2d.setColor(Color.WHITE);
    				}
    				
    				for(int i = 0; i < blueSafeIndexes.length; i++)
    				{
    				    if(index == blueSafeIndexes[i])
    				    {
//    					Checks if the index is the same as the index in the array.
//					If it is then set it blue.    					
    					g2d.setColor(Color.BLUE);
    				    }
    				}
    				
    				for(int i = 0; i < redSafeIndexes.length; i++)
    				{
    				    if(index == redSafeIndexes[i])
    				    {
//    					Checks if the index is the same as the index in the array.
//					If it is then set it red.    					
    					g2d.setColor(Color.RED);
    				    }
    				}
    				
    				if(m_mouseOverCell != null)
    				{
//				If the mouse is hovering over a cell, find the index.    				    
	    				final int selectedIndex = m_mouseOverCell.x + (m_mouseOverCell.y * m_columnCount);
	    				
	    				if(selectedIndex == index)
	    				{
//				Sets the cell that the mouse is hovering over to green, momentarily.	    				    
	    					g2d.setColor(Color.GREEN);
	    				}
    				}
//				Creates the rectangles which are the cells.    				
    				Rectangle cell = new Rectangle(
							xOffset + (c * cellWidth),
							yOffset + (r * cellHeight),
							cellWidth,
							cellHeight);
//				Fills the cells.    				
					g2d.fill(cell);
//				Sets the cells borders of the cells to a grey.					
    				g2d.setColor(Color.GRAY);
//				Draws the cells.    				
    				g2d.draw(cell);
    			}
    		}
    	}
//	Deletes this edition of the graphics component.    	
    	g2d.dispose();
    }
    
    public void setMode(int mode)
    {
//	The function used by the sprite panel to tell what sprite to draw.	
    	m_mode = mode;
    }
    
    public void setPointBasedOnMode(Point centrePoint)
    {
//	A case statement to call a certain function in accordance with the mode that is set.	
    	switch(m_mode)
    	{
    	case 1:
    		createBlinker(centrePoint);
    		break;
    	case 2:
    		createToad(centrePoint);
    		break;
    	case 3:
    		createBeacon(centrePoint);
    		break;
    	case 4:
    		createPulsar(centrePoint);
    		break;
    	case 5:
    		createPent(centrePoint);
    		break;
    	default:
    		createIndividualPoint(centrePoint);
    		break;
    	}
    }
    
    public void createIndividualPoint(Point center)
    {
//	Simply sets the cell that the user clicked on to either on or off.	
    	m_gameGui.toggleCell(center);
    }
    
    public void createBlinker(Point center)
    {
//	Draws the blinker.	
    	m_gameGui.setCell(center.y, center.x);
    	m_gameGui.setCell(center.y, center.x + 1);
    	m_gameGui.setCell(center.y, center.x - 1);
    }
    
    public void createToad(Point center)
    {
//	Draws the toad.	
//	The bottom line
    	m_gameGui.setCell(center.y, center.x);
    	m_gameGui.setCell(center.y, center.x - 1);
    	m_gameGui.setCell(center.y, center.x + 1);
//	The top line
    	m_gameGui.setCell(center.y - 1, center.x);
    	m_gameGui.setCell(center.y - 1, center.x + 1);
    	m_gameGui.setCell(center.y - 1, center.x + 2);
    }
    
    public void createBeacon(Point center)
    {
//	Draws the beacon.	
//	The top square
    	m_gameGui.setCell(center.y, center.x);
    	m_gameGui.setCell(center.y, center.x + 1);
    	m_gameGui.setCell(center.y + 1, center.x);
    	m_gameGui.setCell(center.y + 1, center.x + 1);
//	The top square
    	m_gameGui.setCell(center.y - 1, center.x - 1);
    	m_gameGui.setCell(center.y - 1, center.x - 2);
    	m_gameGui.setCell(center.y - 2, center.x - 1);
    	m_gameGui.setCell(center.y - 2, center.x - 2);
    }
    
    public void createPulsar(Point center)
    {
//	Draws the pulsar.	
//	Right top circle.
	m_gameGui.setCell(center.y - 2, center.x + 1);
	m_gameGui.setCell(center.y - 3, center.x + 1);
	m_gameGui.setCell(center.y - 4, center.x + 1);
	m_gameGui.setCell(center.y - 6, center.x + 2);
	m_gameGui.setCell(center.y - 6, center.x + 3);
	m_gameGui.setCell(center.y - 6, center.x + 4);
	m_gameGui.setCell(center.y - 4, center.x + 6);
	m_gameGui.setCell(center.y - 3, center.x + 6);
	m_gameGui.setCell(center.y - 2, center.x + 6);
	m_gameGui.setCell(center.y - 1, center.x + 2);
	m_gameGui.setCell(center.y - 1, center.x + 3);
	m_gameGui.setCell(center.y - 1, center.x + 4);
//	Left top circle.
	m_gameGui.setCell(center.y - 2, center.x - 1);
	m_gameGui.setCell(center.y - 3, center.x - 1);
	m_gameGui.setCell(center.y - 4, center.x - 1);
	m_gameGui.setCell(center.y - 6, center.x - 2);
	m_gameGui.setCell(center.y - 6, center.x - 3);
	m_gameGui.setCell(center.y - 6, center.x - 4);
	m_gameGui.setCell(center.y - 4, center.x - 6);
	m_gameGui.setCell(center.y - 3, center.x - 6);
	m_gameGui.setCell(center.y - 2, center.x - 6);
	m_gameGui.setCell(center.y - 1, center.x - 2);
	m_gameGui.setCell(center.y - 1, center.x - 3);
	m_gameGui.setCell(center.y - 1, center.x - 4);
//	Right bottom circle
	m_gameGui.setCell(center.y + 2, center.x + 1);
	m_gameGui.setCell(center.y + 3, center.x + 1);
	m_gameGui.setCell(center.y + 4, center.x + 1);
	m_gameGui.setCell(center.y + 6, center.x + 2);
	m_gameGui.setCell(center.y + 6, center.x + 3);
	m_gameGui.setCell(center.y + 6, center.x + 4);
	m_gameGui.setCell(center.y + 4, center.x + 6);
	m_gameGui.setCell(center.y + 3, center.x + 6);
	m_gameGui.setCell(center.y + 2, center.x + 6);
	m_gameGui.setCell(center.y + 1, center.x + 2);
	m_gameGui.setCell(center.y + 1, center.x + 3);
	m_gameGui.setCell(center.y + 1, center.x + 4);
// Left bottom circle
	m_gameGui.setCell(center.y + 2, center.x - 1);
	m_gameGui.setCell(center.y + 3, center.x - 1);
	m_gameGui.setCell(center.y + 4, center.x - 1);
	m_gameGui.setCell(center.y + 6, center.x - 2);
	m_gameGui.setCell(center.y + 6, center.x - 3);
	m_gameGui.setCell(center.y + 6, center.x - 4);
	m_gameGui.setCell(center.y + 4, center.x - 6);
	m_gameGui.setCell(center.y + 3, center.x - 6);
	m_gameGui.setCell(center.y + 2, center.x - 6);
	m_gameGui.setCell(center.y + 1, center.x - 2);
	m_gameGui.setCell(center.y + 1, center.x - 3);
	m_gameGui.setCell(center.y + 1, center.x - 4);
    }
    
    public void createPent(Point center)
    {
//	Draws the pentadecathlon.	
//	The top triangle
	m_gameGui.setCell(center.y - 3, center.x);
	m_gameGui.setCell(center.y - 3, center.x + 1);
	m_gameGui.setCell(center.y - 3, center.x + 2);
	m_gameGui.setCell(center.y - 3, center.x - 1);
	m_gameGui.setCell(center.y - 3, center.x - 2);
	m_gameGui.setCell(center.y - 4, center.x);
	m_gameGui.setCell(center.y - 4, center.x + 1);
	m_gameGui.setCell(center.y - 4, center.x - 1);
	m_gameGui.setCell(center.y - 5, center.x);
//	The bottom triangle
	m_gameGui.setCell(center.y + 4, center.x);
	m_gameGui.setCell(center.y + 4, center.x + 1);
	m_gameGui.setCell(center.y + 4, center.x + 2);
	m_gameGui.setCell(center.y + 4, center.x - 1);
	m_gameGui.setCell(center.y + 4, center.x - 2);
	m_gameGui.setCell(center.y + 5, center.x);
	m_gameGui.setCell(center.y + 5, center.x + 1);
	m_gameGui.setCell(center.y + 5, center.x - 1);
	m_gameGui.setCell(center.y + 6, center.x);
    }
}