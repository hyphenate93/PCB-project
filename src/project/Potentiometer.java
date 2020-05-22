package project;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * 
 * @author Niklas Andersson
 * @author Andreas Östlin
 * 
 */
@SuppressWarnings("serial")
public class Potentiometer implements Node {

	private String component;
	private String orientation;
	private double price;
	private double x;
	private double y;
	private double size;
	private Color color;
	private static final int DEFAULT_SIZE = 50;
	private boolean up, right, left = false;

	public Potentiometer() {
		component = "Potentiometer";
		price = 4.55;
		size = DEFAULT_SIZE;
		x = 0;
		y = 0;
		color = Color.BLUE;
		price = 7.99;
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
		} catch (CloneNotSupportedException exception) {
			return null;
		}
	}

	public void draw(Graphics2D g2) {
		if (orientation == "vertical") {

			g2.fillRoundRect((int) x - 1, (int) y - 21, 44, 44, 10, 10);
			g2.setColor(color);
			g2.fillRoundRect((int) x, (int) y - 20, 40, 40, 10, 10);
			g2.setColor(Color.BLACK);
			g2.draw(new Rectangle2D.Double(x + 18, y - 10, 5, 18));
			g2.draw(new Ellipse2D.Double(x + 5, y - 15, 30, 30));

		} else {

			g2.fillRoundRect((int) x - 1, (int) y - 21, 44, 44, 10, 10);
			g2.setColor(color);
			g2.fillRoundRect((int) x, (int) y - 20, 40, 40, 10, 10);
			g2.setColor(Color.BLACK);
			g2.draw(new Rectangle2D.Double(x + 10, y - 2, 18, 5));
			g2.draw(new Ellipse2D.Double(x + 5, y - 15, 30, 30));
			g2.setColor(Color.BLACK);
		}
	}

	public void translate(double dx, double dy) {
		x += dx;
		y += dy;
	}

	public boolean contains(Point2D p) {
		Rectangle2D rectangle = new Rectangle2D.Double(x - 5, y - 25, size, size);

		return rectangle.contains(p);
	}

	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x - 5, y - 25, size, size);
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

		if (up != false || right != false || left != false) {
			return true;
		}
		return false;
	}

	public void setConnection(boolean a, String b) {
		if (b.equals("left")) {
			left = a;
		}
		if (b.equals("right")) {
			right = a;
		}
		if (b.equals("up")) {
			up = a;
		}
	}
}
