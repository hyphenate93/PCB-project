package project;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * @author Niklas Andersson
 * @author Andreas Östlin
 * 
 */
@SuppressWarnings("serial")
public class Resistor implements Node {

	private String component;
	private String orientation;
	private double price;
	private double x;
	private double y;
	private double size;
	private Color color;
	private static final int DEFAULT_SIZE = 50;
	private boolean up, right, left = false;

	/**
	 * Construct a circle node with a given size and color.
	 * 
	 * @param aColor the fill color
	 */
	public Resistor() {
		component = "Resistor";
		price = 1.99;
		size = DEFAULT_SIZE;
		x = 0;
		y = 0;
		color = new Color(255, 100, 50);
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

			g2.draw(new Line2D.Double(x + 45, y - 30, x + 45, y + 20));
			g2.fillRoundRect((int) x + 39, (int) y - 26, 12, 42, 10, 10);
			g2.setColor(color);
			g2.fillRoundRect((int) x + 40, (int) y - 25, 10, 40, 10, 10);
			g2.setColor(Color.BLACK);
			g2.fillRoundRect((int) x + 39, (int) y - 15, 12, 3, 1, 1);
			g2.fillRoundRect((int) x + 39, (int) y + 5, 12, 3, 1, 1);
			g2.fillRoundRect((int) x + 39, (int) y, 12, 3, 1, 1);

		} else {

			g2.draw(new Line2D.Double(x - 6, y + 20, x + 46, y + 20));
			g2.fillRoundRect((int) x - 1, (int) y + 14, 42, 12, 10, 10);
			g2.setColor(color);
			g2.fillRoundRect((int) x, (int) y + 15, 40, 10, 10, 10);
			g2.setColor(Color.BLACK);
			g2.fillRoundRect((int) x + 10, (int) y + 14, 3, 12, 1, 1);
			g2.fillRoundRect((int) x + 25, (int) y + 14, 3, 12, 1, 1);
			g2.fillRoundRect((int) x + 30, (int) y + 14, 3, 12, 1, 1);
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
