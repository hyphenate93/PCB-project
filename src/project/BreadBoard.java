package project;

import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

/**
 * @author Niklas Andersson
 * @author Andreas Östlin
 * 
 * A graph consisting of selectable nodes.
 */
@SuppressWarnings("serial")
public abstract class BreadBoard implements Serializable {

	private double total;
	private int resistorAmount;
	private int capacitorAmount;
	private int inductorAmount;
	private int potAmount;
	private int wireAmount;

	private Node[][] occupied = new Node[14][10];
	private DecimalFormat df = new DecimalFormat("#.00");
	private int squareSize = 50;
	private ArrayList<Node> nodes;

	/**
	 * Constructs a graph with no nodes.
	 */
	public BreadBoard() {
		nodes = new ArrayList<Node>();

		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 10; j++) {
				occupied[i][j] = null;
			}
		}
	}

	/**
	 * Adds a node to the graph so that the top left corner of the bounding
	 * rectangle is at the given point.
	 * 
	 * @param n the node to add
	 * @param p the desired location
	 */
	public boolean add(Node n, Point2D p) {
		n.translate(p.getX() - 24, p.getY());
		nodes.add(n);
		return true;
	}

	/**
	 * Finds a node containing the given point.
	 * 
	 * @param p a point
	 * @return a node containing p or null if no nodes contain p
	 */
	public Node findNode(Point2D p) {
		for (int i = nodes.size() - 1; i >= 0; i--) {
			Node n = nodes.get(i);
			if (n.contains(p))
				return n;
		}
		return null;
	}


	/**
	 * Removes a node
	 * 
	 * @param n the node to remove
	 */
	public void removeNode(Node n) {
		nodes.remove(n);
	}

	/**
	 * Gets the smallest rectangle enclosing the graph
	 * 
	 * @param g2 the graphics context
	 * @return the bounding rectangle
	 */
	public Rectangle2D getBounds(Graphics2D g2) {
		Rectangle2D r = null;
		for (Node n : nodes) {
			Rectangle2D b = n.getBounds();
			if (r == null)
				r = b;
			else
				r.add(b);
		}
		return r == null ? new Rectangle2D.Double() : r;
	}

	/**
	 * Gets the node types of a particular graph type.
	 * 
	 * @return an array of node prototypes
	 */
	public abstract Node[] getNodePrototypes();

	/**
	 * Gets the nodes of this graph.
	 * 
	 * @return an unmodifiable list of the nodes
	 */
	public List<Node> getNodes() {
		return Collections.unmodifiableList(nodes);
	}

	public int getComponentAmount() {
		int componentAmount = resistorAmount 
							+ capacitorAmount 
							+ inductorAmount 
							+ potAmount 
							+ wireAmount;
		return componentAmount;
	}

	public void updateText() {
		Framework.getText()
				.setText("ShoppingList: \n\n\n" 
						+ resistorAmount + "x Resistor\n" + capacitorAmount + "x Capacitor\n"
						+ inductorAmount + "x Inductor\n" + potAmount + "x Potentiometer\n" 
						+ wireAmount + "x Wire\n\n\n\n\n\n\n\n\n" 
						+ "total cost: \n" + getPrice() + "$");
	}
	
	public void setAmount() {

		int resistor = 0;
		int capacitor = 0;
		int inductor = 0;
		int potentiometer = 0;
		int wire = 0;
		for (Node n : nodes) {
			if (n.getComponent().equals("Resistor")) {
				resistor++;
			} else if (n.getComponent().equals("Capacitor")) {
				capacitor++;
			} else if (n.getComponent().equals("Inductor")) {
				inductor++;
			} else if (n.getComponent().equals("Potentiometer")) {
				potentiometer++;
			} else if (n.getComponent().equals("Wire")) {
				wire++;
			}
		}
		resistorAmount = resistor;
		capacitorAmount = capacitor;
		inductorAmount = inductor;
		potAmount = potentiometer;
		wireAmount = wire;
	}

	public void addAmount(String component) {

		if (component.equals("Resistor")) {
			resistorAmount++;
		} else if (component.equals("Capacitor")) {
			capacitorAmount++;
		} else if (component.equals("Inductor")) {
			inductorAmount++;
		} else if (component.equals("Potentiometer")) {
			potAmount++;
		} else if (component.equals("Wire")) {
			wireAmount++;
		}
	}

	public String getPrice() {

		String price = "";
		if (total < 0.1) {
			return "0.00";
		} else {
			price = df.format(total);
			return price;
		}
	}

	public void updatePrice() {
		double price = 0.0;
		for (Node n : nodes) {
			if (n.getComponent().equals("Resistor") 
					|| n.getComponent().equals("Capacitor")
					|| n.getComponent().equals("Inductor") 
					|| n.getComponent().equals("Potentiometer")
					|| n.getComponent().equals("Wire")) {

				price = price + n.getPrice();
			}
		}
		total = price;
	}

	public void setOccupied(int a, int b, Node c) {

		if (-1 < a && a < 14 && -1 < b && b < 10) {

			occupied[a][b] = c;
		}
	}

	public Node getOccupied(int a, int b) {

		if (-1 < a && a < 14 && -1 < b && b < 10) {
			return occupied[a][b];
		} else {
			return null;
		}
	}
	
	public void resetConnections() {
		for (Node n : nodes) {
			n.setConnection(false, "left");
			n.setConnection(false, "right");
			n.setConnection(false, "up");
		}
	}

	public void setConnections() {

		System.out.println("Finding Connections");

		for (Node n : nodes) {
			int xCheck = (int)(n.getX()+5) / 50;
			int yCheck = (int)(n.getY()+5) / 50;
	
			//component to the left or under
			if(n.getOrientation().equals("horizontal")) {
				
				if(getOccupied(xCheck - 1, yCheck) != null) {
					n.setConnection(true, "left");			
				}
			} 	
			
			if(n.getOrientation().equals("horizontal")) {
				if(getOccupied(xCheck - 1, yCheck + 1) != null) {
					if(getOccupied(xCheck - 1, yCheck + 1).getOrientation().equals("vertical")) {
						n.setConnection(true, "left");
					}
				}
			}
				
			
			// component to the right or under (vertical orientation)
			if(getOccupied(xCheck + 1, yCheck) != null) {
				
				if(getOccupied(xCheck + 1, yCheck).getOrientation().equals("horizontal")) {	
					n.setConnection(true, "right");
				}
			}	
			if(getOccupied(xCheck, yCheck + 1) != null) {
				if(getOccupied(xCheck, yCheck + 1).getOrientation().equals("vertical")) {
					n.setConnection(true, "right");
				}
			}
			
			// Component over this one
			if(n.getOrientation().equals("vertical")) {
				
				if(getOccupied(xCheck, yCheck - 1) != null) {
	
					n.setConnection(true, "up");			
				}
			}	
				if(n.getOrientation().equals("vertical")) {
			if(getOccupied(xCheck + 1, yCheck - 1) != null) {
			
				if(getOccupied(xCheck + 1, yCheck - 1).getOrientation().equals("horizontal")) {
					n.setConnection(true, "up");
				}
			}
			
		}	}
		System.out.println("Found:");
		for (Node n : nodes) {
			if (n.getConnection() == true) {
				System.out.println("  " + n.getComponent());
			}
		}
	}
	
	public int getResistorAmount() {
		return this.resistorAmount;
	}

	public void setResistorAmount(int amount) {
		this.resistorAmount = amount;
	}

	public int getCapacitorAmount() {
		return this.capacitorAmount;
	}

	public void setCapacitorAmount(int amount) {
		this.capacitorAmount = amount;
	}

	public int getInductorAmount() {
		return this.inductorAmount;
	}

	public void setInductorAmount(int amount) {
		this.inductorAmount = amount;
	}

	public int getPotAmount() {
		return this.potAmount;
	}

	public void setPotAmount(int amount) {
		this.potAmount = amount;
	}

	public int getWireAmount() {
		return this.wireAmount;
	}

	public void setWireAmount(int amount) {
		this.wireAmount = amount;
	}
	
	/**
	 * Draws the PCB-Board and the components
	 * 
	 * @param g2 the graphics context
	 */
	public void draw(Graphics2D g2) {
		
		g2.setColor(Color.BLACK);
		g2.fillRoundRect(48, 48, (squareSize * 14) + 4, (squareSize * 10) + 4, 10, 10);
		g2.setColor(new Color(0, 160, 0));
		g2.fillRoundRect(50, 50, squareSize * 14, squareSize * 10, 10, 10);
		g2.setColor(Color.BLACK);
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 10; j++) {
				g2.setColor(new Color(198, 165, 48));
				g2.fillRoundRect(97 + squareSize * i, 72 + squareSize * j, 7, 7, 3, 3);
				g2.setColor(Color.BLACK);
				g2.fillRoundRect(98 + squareSize * i, 73 + squareSize * j, 4, 4, 3, 3);
			}
		}

		for (Node n : nodes) {
			g2.setColor(Color.BLACK);
			n.draw(g2);
			if(n.getConnection() == true) {
				g2.setColor(Color.YELLOW);
				g2.fillRoundRect((int)n.getX()+40, (int)n.getY()+15, 10, 10,10,10);
			}	
		}
	}
}