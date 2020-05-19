package project;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.math.BigDecimal;

public class Wire implements Node {

	
	
	public Wire(Color aColor)
	{
		component = "Wire";
		price = 0.10;
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
			
			g2.setColor(Color.RED);
			g2.drawArc((int)(x+size+15)+2, (int)(y-40), 50, 70, -135, -90);
			g2.drawArc((int)(x+size+15)+1, (int)(y-40), 50, 70, -135, -90);
			g2.drawArc((int)(x+size+15)+3, (int)(y-40), 50, 70, -135, -90);
			g2.setColor(Color.BLACK);
			g2.drawArc((int)(x+size+15), (int)(y-40), 50, 70, -135, -90);
			g2.drawArc((int)(x+size+15)+4, (int)(y-40), 50, 70, -135, -90);
		} 
		else {
			g2.setColor(Color.RED);
			g2.drawArc((int)x-15, (int)(y+size-10), 70, 50, 45, 90);
			g2.drawArc((int)x-15, (int)(y+size-9), 70, 50, 45, 90);
			g2.drawArc((int)x-15, (int)(y+size-8), 70, 50, 45, 90);
			g2.drawArc((int)x-15, (int)(y+size-7), 70, 50, 45, 90);
			g2.setColor(Color.BLACK);
			g2.drawArc((int)x-15, (int)(y+size-10), 70, 50, 45, 90);
			g2.drawArc((int)x-15, (int)(y+size-6), 70, 50, 45, 90);
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
	    		x, y, size*2, size*2);
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
	
	public void setOrientation(String s) {
		this.orientation = s;	
	}   
	
	public String getOrientation() {
		return this.orientation;
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
