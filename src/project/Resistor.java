package project;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Resistor implements Node {
	  /**
    	Construct a circle node with a given size and color.
    	@param aColor the fill color
	   */
	public Resistor(Color aColor)
	{
		component = "Resistor"; 
		price = 1.99;
		size = DEFAULT_SIZE;
		x = 0;
		y = 0;
		color = aColor;
		orientation = "horizontal";
	}

	public void setColor(Color aColor)
	{
		color = aColor;
	}

	public Color getColor()
	{
		return color;
	}

	public Object clone()
	{
		try
		{
			return super.clone();
		}
		catch (CloneNotSupportedException exception)
		{
			return null;
		}
	}

	public void draw(Graphics2D g2)
	{
		if(orientation == "vertical") {
			Rectangle2D rectangle = new Rectangle2D.Double(
					x, y+size/2, size*2, size);
			Line2D.Double line1
			= new Line2D.Double();
			line1.setLine(x-5, y+size, x+size+25, y+size);
			g2.draw(line1);
			Color oldColor = g2.getColor();
			g2.setColor(color);
			g2.fill(rectangle);
			g2.setColor(oldColor);
			g2.draw(rectangle);
		}
		else {
			Rectangle2D rectangle = new Rectangle2D.Double(
					x, y+size/2, size*2, size);
			Line2D.Double line1
			= new Line2D.Double();
			line1.setLine(x-5, y+size, x+size+25, y+size);
			g2.draw(line1);
			Color oldColor = g2.getColor();
			g2.setColor(color);
			g2.fill(rectangle);
			g2.setColor(oldColor);
			g2.draw(rectangle);
		}
	}

	public void translate(double dx, double dy)
	{
		x += dx;
		y += dy;
	}

	public boolean contains(Point2D p)
	{
		Rectangle2D rectangle = new Rectangle2D.Double(
				x, y+size/2, size*2, size);
		return rectangle.contains(p);
	}

	public Rectangle2D getBounds()
	{
		return new Rectangle2D.Double(
				x, y, size*2, size*2);
	}

	public Point2D getConnectionPoint(Point2D other)
	{
		double centerX = x + size / 2;
		double centerY = y + size / 2;
		double dx = other.getX() - centerX;
		double dy = other.getY() - centerY;
		double distance = Math.sqrt(dx * dx + dy * dy);
		if (distance == 0) return other;
		else return new Point2D.Double(
				centerX + dx * (size / 2) / distance,
				centerY + dy * (size / 2) / distance);
	}
 

	public String getComponent() {
		return component;
	}
 
	public double getPrice() {
	 	return price;
 	}
	
	public void flip() {
		if(orientation == "horizontal") {
			orientation = "vertical";
		}
		else if(orientation == "vertical") {
			orientation = "horizontal";
		}
 	}

 	private String component;
 	private String orientation;
 	private double price;
 	private double x;
 	private double y;
 	private double size;
 	private Color color;  
 	private static final int DEFAULT_SIZE = 20;

}
