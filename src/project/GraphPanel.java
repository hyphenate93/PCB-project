package project;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A panel to draw a graph
 */
@SuppressWarnings("serial")
public class GraphPanel extends JComponent {
	/**
      Constructs a graph.
      @param aToolBar the tool bar with the node and edge tools
      @param aGraph the graph to be displayed and edited
	*/
	public GraphPanel(ToolBar aToolBar, Graph aGraph)
	{
		toolBar = aToolBar;
		graph = aGraph;
		setBackground(Color.WHITE);

		addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent event) {
			Point2D mousePoint = event.getPoint();
			Node n = graph.findNode(mousePoint);
			Object tool = toolBar.getSelectedTool();
			graph.resetConnections();
			if (tool == null) // select
			{
				if (n != null) {
					selected = n;
					dragStartPoint = mousePoint;
					dragStartBounds = n.getBounds();
				
				} else {
					selected = null;
				}
			}
			if(tool == "rotate" && graph.findNode(mousePoint) != null) {
				
//				int xCord = (int) (n.getX() + (double) 50 / 2) / 50 * 50;
//				int yCord = (int) (n.getY() + (double) 50 / 2) / 50 * 50;
				
				if(n instanceof Node && n.getOrientation().equals("vertical")) {
					n.setOrientation("horizontal");
//					graph.setOccupied((xCord + 5)/50,(yCord + 5)/50,n);		
					graph.resetConnections();
				}
				
				else if(n instanceof Node && n.getOrientation().equals("horizontal")){
					n.setOrientation("vertical");
//					graph.setOccupied((xCord + 5)/50,(yCord + 5)/50,null);
					graph.resetConnections();
				}
				revalidate();
				repaint();
			}	
			
			if(tool == "connection") {
				graph.setConnections();
				repaint();
			}
			
			else if (tool instanceof Node 
					&& graph.findNode(mousePoint) == null
					&& graph.getComponentAmount() < 15) {
				
				
				Node prototype = (Node) tool;
				Node newNode = (Node) prototype.clone();
				
				boolean added = graph.add(newNode, mousePoint);
				
				if (added) {
					int xCord = (int) (newNode.getX() + (double) 50 / 2) / 50 * 50;
					int yCord = (int) (newNode.getY() + (double) 50 / 2) / 50 * 50;
					newNode.setX((xCord+5));
					newNode.setY((yCord+5));
					graph.setOccupied((int)(newNode.getX())/50,(int)(newNode.getY())/50,newNode);
					selected = newNode;
					graph.resetConnections();
					dragStartPoint = mousePoint;
					dragStartBounds = newNode.getBounds();
					graph.addAmount(prototype.getComponent());
					graph.updatePrice();
					graph.updateText();

				} else if (n != null) {
					graph.resetConnections();
					selected = n;
					dragStartPoint = mousePoint;
					dragStartBounds = n.getBounds();
				}
			}	
			repaint();
		}

		public void mouseReleased(MouseEvent event) {
			
			Point2D mousePoint = event.getPoint();
			Node n = (Node) selected;

			if (selected != null && mousePoint.getX() >= 55 && mousePoint.getX() < 705 && mousePoint.getY() > 0
					&& mousePoint.getY() < 505) {
				
				int xCord = (int) (n.getX() + (double) 50 / 2) / 50 * 50;
				int yCord = (int) (n.getY() + (double) 50 / 2) / 50 * 50;

				if(graph.getOccupied((xCord+5)/50,(yCord+5)/50)==null) {
					n.setX(xCord + 5);
					n.setY(yCord + 5);
					graph.setOccupied((xCord + 5)/50,(yCord + 5)/50,n);
				}
				else {
					if(!(n.equals(graph.getOccupied((xCord)/50,(yCord)/50)))
							&& graph.getOccupied((xCord+5)/50,(yCord+5)/50)!= null) { 
						
						n.setX(800);
						n.setY(105);
						graph.setOccupied((xCord + 5)/50,(yCord + 5)/50,null);
				}}
			}
			revalidate();
			repaint();
			
			
			dragStartBounds = null;
			
		}
	});

	addMouseMotionListener(new MouseMotionAdapter() {
		public void mouseDragged(MouseEvent event) {
			Point2D mousePoint = event.getPoint();
               if (dragStartBounds != null)
               {
                  if (selected instanceof Node)
                  {
                	  graph.setOccupied((int)(dragStartBounds.getX())/50,(int)(dragStartBounds.getY())/50,null);
                     Node n = (Node) selected;
                     Rectangle2D bounds = n.getBounds();
                     n.translate(
                        dragStartBounds.getX() - bounds.getX() 
                        + mousePoint.getX() - dragStartPoint.getX(),
                        dragStartBounds.getY() - bounds.getY() 
                        + mousePoint.getY() - dragStartPoint.getY());
                  }       
               }  
               repaint();        
            }
         });
	}

	public void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		graph.draw(g2);
		if (selected instanceof Node) {
			Rectangle2D grabberBounds = ((Node) selected).getBounds();

			drawGrabber(g2, grabberBounds.getMinX(), grabberBounds.getMinY());
			drawGrabber(g2, grabberBounds.getMinX(), grabberBounds.getMaxY());
			drawGrabber(g2, grabberBounds.getMaxX(), grabberBounds.getMinY());
			drawGrabber(g2, grabberBounds.getMaxX(), grabberBounds.getMaxY());
		}

	}

	/**
	 * Removes the selected node or edge.
	 */
	public void removeSelected() {
		if (selected instanceof Node) {
			graph.removeNode((Node) selected);
			graph.setAmount();
			graph.updatePrice();
			graph.updateText();
		}
		selected = null;
		repaint();
		 
	}

	/**
	 * Draws a single "grabber", a filled square
	 * 
	 * @param g2 the graphics context
	 * @param x  the x coordinate of the center of the grabber
	 * @param y  the y coordinate of the center of the grabber
	 */
	public static void drawGrabber(Graphics2D g2, double x, double y) {
		final int SIZE = 5;
		Color oldColor = g2.getColor();
		g2.setColor(PURPLE);
		g2.fill(new Rectangle2D.Double(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE));
		g2.setColor(oldColor);
	}

	
	public static void rotateArrow(Graphics2D g2, int x, int y) {
		Color oldColor = g2.getColor();
		g2.setColor(Color.BLACK);
		g2.drawArc(x, y, 20, 20, 230, -200);
		g2.draw(new Line2D.Double(x+20, y, x+20, y + 5));
		g2.draw(new Line2D.Double(x+15, y + 8, x+20, y + 5));
		g2.setColor(oldColor);
	}
	
	public static void checkConnection(Graphics2D g2, int x, int y) {
		Color oldColor = g2.getColor();
		g2.setColor(Color.YELLOW);
		g2.fill(new Ellipse2D.Double(x, y, 20, 20));
		g2.setColor(Color.BLACK);
		g2.draw(new Ellipse2D.Double(x, y, 20, 20));
		g2.fillRect(x + 5, y + 18, 11, 5);
		g2.setColor(oldColor);
	}
	
	
	public Dimension getPreferredSize() {
		Rectangle2D bounds = graph.getBounds((Graphics2D) getGraphics());
		return new Dimension((int) bounds.getMaxX(), (int) bounds.getMaxY());
	}

	private Graph graph;
	private ToolBar toolBar;
	private Point2D dragStartPoint;
	private Rectangle2D dragStartBounds;
	private Object selected;
	public static final boolean occupied = true;
	public static final boolean free = false;
	private static final Color PURPLE = new Color(0.7f, 0.4f, 0.7f);
}
