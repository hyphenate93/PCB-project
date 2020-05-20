package project;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


@SuppressWarnings("serial")
public class Capacitor implements Node {
	 private boolean up,right,left = false;
	 private String component;
 	 private String orientation;
 	 private double price;
 	 private double x;
 	 private double y;
 	 private double size;
 	 private Color color;  
 	 private static final int DEFAULT_SIZE = 20;
	
	public Capacitor(Color aColor) {
		component = "Capacitor";
		price = 4.55;
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
			Ellipse2D.Double base = new Ellipse2D.Double(x+size, y-12, 14, 14);
			Line2D.Double line1 = new Line2D.Double();
			line1.setLine(x+29, y-12, x+size*2+6, y-30);
			Line2D.Double line2 = new Line2D.Double();
			line2.setLine(x+29, y+3, x+size*2+6, y+size);
			g2.setColor(new Color(255, 150, 50));
			g2.fill(base);
			g2.setColor(Color.BLACK);
			g2.draw(base);
			g2.draw(line1);
			g2.draw(line2);
		
		} else {
			
			Ellipse2D.Double base = new Ellipse2D.Double(x+13, y, 14, 14);
			Line2D.Double line1 = new Line2D.Double();
			line1.setLine(x-7, y+size, x+size-6, y+size/2);
			Line2D.Double line2 = new Line2D.Double();
			line2.setLine(x+size+6, y+size/2, x+size*2+7, y+size);
			g2.setColor(new Color(255, 150, 50));
			g2.fill(base);
			g2.setColor(Color.BLACK);
			g2.draw(base);
			g2.draw(line1);
			g2.draw(line2);
		}
	}

	public void translate(double dx, double dy) {
		x += dx;
		y += dy;
	}

	public boolean contains(Point2D p) {
		Rectangle2D rectangle = new Rectangle2D.Double(
	    		x, y, size*2, size*2);
	    return rectangle.contains(p);
	}

	public Rectangle2D getBounds() {
	    return new Rectangle2D.Double(
	            x, y, size*2, size*2);
	}

	public Point2D getConnectionPoint(Point2D other) {
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
