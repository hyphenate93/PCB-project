package project;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * @author Niklas Andersson
 * @author Andreas Östlin
 * 
 */
@SuppressWarnings("serial")
public class Wire implements Node {

	private String component;
	private String orientation;
	private double price;
	private double x;
	private double y;
	private double size;
	private Color color;
	private static final int DEFAULT_SIZE = 50;
	private boolean up, right, left = false;

	public Wire() {
		component = "Wire";
		price = 0.10;
		size = DEFAULT_SIZE;
		x = 0;
		y = 0;
		color = Color.RED;
		orientation = "horizontal";
	}

	/**
	 * @return the message that is at the head of the queue
	 * @precondition aColor != null
	 * @param aColor
	 */
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

			g2.setColor(color);
			g2.drawArc((int) x + 36, (int) y - 40, 50, 70, -135, -90);
			g2.drawArc((int) x + 37, (int) y - 40, 50, 70, -135, -90);
			g2.drawArc((int) x + 38, (int) y - 40, 50, 70, -135, -90);
			g2.setColor(Color.BLACK);
			g2.drawArc((int) x + 35, (int) y - 40, 50, 70, -135, -90);
			g2.drawArc((int) x + 39, (int) y - 40, 50, 70, -135, -90);

		} else {

			g2.setColor(color);
			g2.drawArc((int) x - 15, (int) y + 11, 70, 50, 45, 90);
			g2.drawArc((int) x - 15, (int) y + 12, 70, 50, 45, 90);
			g2.drawArc((int) x - 15, (int) y + 13, 70, 50, 45, 90);
			g2.setColor(Color.BLACK);
			g2.drawArc((int) x - 15, (int) y + 10, 70, 50, 45, 90);
			g2.drawArc((int) x - 15, (int) y + 14, 70, 50, 45, 90);
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