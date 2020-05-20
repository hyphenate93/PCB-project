package project;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

@SuppressWarnings("serial")
public class Resistor implements Node {
	
	private String component;
 	private String orientation;
 	private double price;
 	private double x;
 	private double y;
 	private double size;
 	private Color color;  
 	private static final int DEFAULT_SIZE = 20;
 	private boolean up,right,left = false;
 	
	/**
   		Construct a circle node with a given size and color.
    	@param aColor the fill color
	*/
	public Resistor(Color aColor) {
		component = "Resistor"; 
		price = 1.99;
		size = DEFAULT_SIZE;
		x = 0;
		y = 0;
		color = aColor;
		orientation = "horizontal";
	}

	public void setColor(Color aColor) {
		color = aColor;
	}

	public Color getColor() {
		return color;
	}

	public Object clone() {
		try {
			return super.clone();
		}
		catch (CloneNotSupportedException exception) {
			return null;
		}
	}

	public void draw(Graphics2D g2) {
		if(orientation == "vertical") {

			g2.draw(new Line2D.Double(x+size+25, y-30, x+size+25, y+size));
			g2.fillRoundRect((int)(x+size+19), (int)y-26, 2+(int)size/2, 2+(int)size*2, 10, 10);
			Color oldColor = g2.getColor();
			g2.setColor(color);
			g2.fillRoundRect((int)(x+size+20), (int)y-25, (int)size/2, (int)size*2, 10, 10);
			g2.setColor(oldColor);
			g2.fillRoundRect((int)(x+size+19), (int)y-15, 2+(int)size/2, 3, 1, 1);
			g2.fillRoundRect((int)(x+size+19), (int)y+5, 2+(int)size/2, 3, 1, 1);
			g2.fillRoundRect((int)(x+size+19), (int)y, 2+(int)size/2, 3, 1, 1);
		}
		else {

			g2.draw(new Line2D.Double(x-6, y+size, x+size+26, y+size));
			g2.fillRoundRect((int)x-1, (int)y-1+15, 2+(int)size*2, 2+(int)size/2, 10, 10);
			Color oldColor = g2.getColor();
			g2.setColor(color);
			g2.fillRoundRect((int)x, (int)y+15, (int)size*2, (int)size/2, 10, 10);
			g2.setColor(oldColor);
			g2.fillRoundRect((int)x+10, (int)y-1+15, 3, 2+(int)size/2, 1, 1);
			g2.fillRoundRect((int)x+25, (int)y-1+15, 3, 2+(int)size/2, 1, 1);
			g2.fillRoundRect((int)x+30, (int)y-1+15, 3, 2+(int)size/2, 1, 1);
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

	 public boolean getConnection() {

 		 if (up!=false || right !=false || left !=false) {
 			 return true;
 		 }
 		 return false;
 	 }
	
 	 public void setConnection(boolean a, String b) {
 		 if(b.equals("left")) {left = a;}
 		 if(b.equals("right")) {right = a;}
 		 if(b.equals("up")) {up = a;}
 	 }
}
