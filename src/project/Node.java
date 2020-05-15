package project;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * A node in a graph.
 */
public interface Node extends Serializable, Cloneable {
	/**
	 * Draw the node.
	 * 
	 * @param g2 the graphics context
	 */
	void draw(Graphics2D g2);

	/**
	 * Translates the node by a given amount.
	 * 
	 * @param dx the amount to translate in the x-direction
	 * @param dy the amount to translate in the y-direction
	 */
	void translate(double dx, double dy);

	/**
	 * Tests whether the node contains a point.
	 * 
	 * @param aPoint the point to test
	 * @return true if this node contains aPoint
	 */
	boolean contains(Point2D aPoint);

	/**
	 * Get the best connection point to connect this node with another node. This
	 * should be a point on the boundary of the shape of this node.
	 * 
	 * @param aPoint an exterior point that is to be joined with this node
	 * @return the recommended connection point
	 */
	Point2D getConnectionPoint(Point2D aPoint);

	/**
	 * Get the bounding rectangle of the shape of this node
	 * 
	 * @return the bounding rectangle
	 */

	Rectangle2D getBounds();

	String getComponent();

	double getPrice();

	public double getX();

	public double getY();

	public void setX(double a);

	public void setY(double a);

	Object clone();
}
