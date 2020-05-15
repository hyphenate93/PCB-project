package project;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.math.BigDecimal;

public class Inductor implements Node {

	
	
	public Inductor(Color aColor)
	{
		component = "Inductor";
		price = 4.55;
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
			Ellipse2D e1 = new Ellipse2D.Double(
		          		x, y, size, size*2);
			Ellipse2D e2 = new Ellipse2D.Double(
						x+size/3, y, size, size*2);
			Ellipse2D e3 = new Ellipse2D.Double(
						x+2*size/3, y, size, size*2);
			Ellipse2D e4 = new Ellipse2D.Double(
						x+size, y, size, size*2);
			g2.draw(e1);
			g2.draw(e2);
			g2.draw(e3);
			g2.draw(e4);
		}
		else {
			g2.setColor(Color.DARK_GRAY);
			g2.fillRoundRect((int)x+4, (int)y+11, (int)size*2-8, (int)(size-1)/2, 2, 2);
			g2.setColor(Color.BLACK);
			for(int i = 0; i < 8; i++){
				Ellipse2D el = new Ellipse2D.Double(
						x+3+4*i, y+10, 5, size/2);
				g2.draw(el);
			}
			Line2D.Double line1 = new Line2D.Double();
			line1.setLine(x-6, y+size, x+7, y+size/2);
			Line2D.Double line2 = new Line2D.Double();
			line2.setLine(x+37, y+5+size/2, x+size*2+6, y+size);
			g2.draw(line1);
			g2.draw(line2);
			/*
			Ellipse2D e1 = new Ellipse2D.Double(
						x, y, size, size*2);
			Ellipse2D e2 = new Ellipse2D.Double(
						x+size/3, y, size, size*2);
			Ellipse2D e3 = new Ellipse2D.Double(
						x+2*size/3, y, size, size*2);
			Ellipse2D e4 = new Ellipse2D.Double(
						x+size, y, size, size*2);
			g2.draw(e1);
			g2.draw(e2);
			g2.draw(e3);
			g2.draw(e4);
			*/
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

 	 private String component;
 	 private String orientation;
 	 private double price;
 	 private double x;
 	 private double y;
 	 private double size;
 	 private Color color;  
 	 private static final int DEFAULT_SIZE = 20;
}
