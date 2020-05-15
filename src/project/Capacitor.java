package project;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.math.BigDecimal;

public class Capacitor implements Node {

	
	
	public Capacitor(Color aColor)
	{
		component = "Capacitor";
		price = 4.55;
		size = DEFAULT_SIZE;
		x = 0;
		y = 0;
		color = aColor;
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
			Line2D.Double line1 = new Line2D.Double();
			line1.setLine(x, y+size, x+size-5, y+size);
			Line2D.Double line2 = new Line2D.Double();
			line2.setLine(x+size+5, y+size, x+size*2, y+size);
			Line2D.Double line3 = new Line2D.Double();
			line3.setLine(x+size-5, y+size*1.5, x+size-5, y+size/2);
			Line2D.Double line4 = new Line2D.Double();
			line4.setLine(x+size+5, y+size*1.5, x+size+5, y+size/2);
			g2.draw(line1);
			g2.draw(line2);
			g2.draw(line3);
			g2.draw(line4);
		} 
		else {
			Line2D.Double line1 = new Line2D.Double();
			line1.setLine(x, y+size, x+size-5, y+size);
			Line2D.Double line2 = new Line2D.Double();
			line2.setLine(x+size+5, y+size, x+size*2, y+size);
			Line2D.Double line3 = new Line2D.Double();
			line3.setLine(x+size-5, y+size*1.5, x+size-5, y+size/2);
			Line2D.Double line4 = new Line2D.Double();
			line4.setLine(x+size+5, y+size*1.5, x+size+5, y+size/2);
			g2.draw(line1);
			g2.draw(line2);
			g2.draw(line3);
			g2.draw(line4);
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
 	 
 	 public void setPrice(double cost) {
 		 price = cost;
	 }
 	 
 	 public void flip() {
 		 if(orientation == "horizontal") {
 			 orientation = "vertical";
 		 }
 		 else if(orientation == "vertical") {
 			 orientation = "horizontal";
 		 }
 	 }
 	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(double x) {
		this.x = x;
	}
public void setY(double y) {
	this.y = y;
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
