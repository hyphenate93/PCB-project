package project;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Niklas Andersson
 * @author Andreas Östlin
 * 
 *         Board Interactions contains the functionalities of the nodes.
 */
@SuppressWarnings("serial")
public class BreadBoardInteractions extends JComponent {
	private BreadBoard board;
	private ToolBar toolBar;
	private Point2D dragStartPoint;
	private Rectangle2D dragStartBounds;
	private Object selected;
	private int spaceComp = 0;
	public static final boolean occupied = true;
	public static final boolean free = false;
	private static final Color PURPLE = new Color(0.7f, 0.4f, 0.7f);

	/**
	 * Constructs a Board.
	 * 
	 * @param aToolBar the tool bar with the node and edge tools
	 * @param aBoard   the board to be displayed and edited
	 */
	public BreadBoardInteractions(ToolBar aToolBar, BreadBoard aBoard) {
		toolBar = aToolBar;
		board = aBoard;

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				Point2D mousePoint = event.getPoint();
				Node n = board.findNode(mousePoint);
				Object tool = toolBar.getSelectedTool();
				board.resetConnections();
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
				if (tool == "rotate" && board.findNode(mousePoint) != null) {

					if (n instanceof Node && n.getOrientation().equals("vertical")) {
						n.setOrientation("horizontal");

						board.resetConnections();
					}

					else if (n instanceof Node && n.getOrientation().equals("horizontal")) {
						n.setOrientation("vertical");

						board.resetConnections();
					}
					revalidate();
					repaint();
				}

				if (tool == "connection") {
					board.setConnections();
					repaint();
				}

				else if (tool instanceof Node && board.findNode(mousePoint) == null
						&& board.getComponentAmount() < 15) {

					Node prototype = (Node) tool;
					Node newNode = (Node) prototype.clone();

					boolean added = board.add(newNode, mousePoint);

					if (added) {
						int xCord = (int) (newNode.getX() + (double) 50 / 2) / 50 * 50;
						int yCord = (int) (newNode.getY() + (double) 50 / 2) / 50 * 50;
						newNode.setX((xCord + 5));
						newNode.setY((yCord + 5));
						board.setOccupied((int) ((newNode.getX()) / 50), (int) ((newNode.getY()) / 50), newNode);
						selected = newNode;
						board.resetConnections();
						dragStartPoint = mousePoint;
						dragStartBounds = newNode.getBounds();
						board.addAmount(prototype.getComponent());
						board.updatePrice();
						board.updateText();

					} else if (n != null) {
						board.resetConnections();
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

					if (board.getOccupied((xCord + 5) / 50, (yCord + 5) / 50) == null) {
						board.setOccupied((int) (dragStartBounds.getX()) / 50, (int) (dragStartBounds.getY()) / 50,
								null);
						n.setX(xCord + 5);
						n.setY(yCord + 5);
						board.setOccupied((xCord + 5) / 50, (yCord + 5) / 50, n);

					} else if (board.getOccupied((xCord + 5) / 50, (yCord + 5) / 50) != null
							&& (n.equals(board.getOccupied((xCord) / 50, (yCord) / 50)))) {
						n.setX(xCord + 5);
						n.setY(yCord + 5);
					} else {
						if (!(n.equals(board.getOccupied((xCord) / 50, (yCord) / 50)))
								&& board.getOccupied((xCord + 5) / 50, (yCord + 5) / 50) != null) {

							n.setX(800);
							n.setY(105 + spaceComp);
							spaceComp += 10;
							board.setOccupied((xCord + 5) / 50, (yCord + 5) / 50, null);
						}
					}
				}
				revalidate();
				repaint();

				dragStartBounds = null;

			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent event) {
				Point2D mousePoint = event.getPoint();
				if (dragStartBounds != null) {
					if (selected instanceof Node) {
						board.setOccupied((int) (dragStartBounds.getX()) / 50, (int) (dragStartBounds.getY()) / 50,
								null);
						Node n = (Node) selected;
						Rectangle2D bounds = n.getBounds();
						n.translate(dragStartBounds.getX() - bounds.getX() + mousePoint.getX() - dragStartPoint.getX(),
								dragStartBounds.getY() - bounds.getY() + mousePoint.getY() - dragStartPoint.getY());
					}
				}
				repaint();
			}
		});
	}

	public void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		board.draw(g2);
		if (selected instanceof Node) {
			Rectangle2D grabberBounds = ((Node) selected).getBounds();

			drawGrabber(g2, grabberBounds.getMinX(), grabberBounds.getMinY());
			drawGrabber(g2, grabberBounds.getMinX(), grabberBounds.getMaxY());
			drawGrabber(g2, grabberBounds.getMaxX(), grabberBounds.getMinY());
			drawGrabber(g2, grabberBounds.getMaxX(), grabberBounds.getMaxY());
		}

	}

	/**
	 * Removes the selected node.
	 * 
	 */
	public void removeSelected() {
		if (selected instanceof Node) {
			board.removeNode((Node) selected);
			board.setAmount();
			board.updatePrice();
			board.updateText();
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
		g2.setColor(PURPLE);
		g2.fill(new Rectangle2D.Double(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE));
		g2.setColor(Color.BLACK);
	}

	public Dimension getPreferredSize() {
		Rectangle2D bounds = board.getBounds((Graphics2D) getGraphics());
		return new Dimension((int) bounds.getMaxX(), (int) bounds.getMaxY());
	}

}
