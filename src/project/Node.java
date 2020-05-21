package project;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * 
 * @author Niklas Andersson
 * @author Andreas Östlin
 *
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
	 * Get the bounding rectangle of the shape of this node
	 * 
	 * @return the bounding rectangle
	 */
	Rectangle2D getBounds();

	/**
	 * 
	 * @return name of component
	 */
	String getComponent();

	Object clone();
	
	double getPrice();

	double getX();

	double getY();
	
	void setX(double a);

	void setY(double a);
	
	/**
	 * Check whether component is connected or not
	 * 
	 * @return true if component is connected to another component
	 */
	boolean getConnection();
	
	/**
	 * Set component as connected or not connected
	 * 
	 * @param a true if component is connected to another
	 * @param b Location of connection
	 */
	void setConnection(boolean a, String b);
	
	void setOrientation(String s);
	
	String getOrientation();
}
