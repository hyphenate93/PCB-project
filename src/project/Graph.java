package project;

import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

import javax.swing.JTextArea;


/**
   A graph consisting of selectable nodes and edges.
*/
public abstract class Graph implements Serializable
{
   /**
      Constructs a graph with no nodes or edges.
   */
   public Graph()
   {
      nodes = new ArrayList<Node>();
      edges = new ArrayList<Edge>();
      for(int i = 0; i < 14; i++){
          for(int j = 0; j < 10; j++){
              occupied[i][j] =  false ;
          }
      }
   }

   /**
      Adds an edge to the graph that joins the nodes containing
      the given points. If the points aren't both inside nodes,
      then no edge is added.
      @param e the edge to add
      @param p1 a point in the starting node
      @param p2 a point in the ending node
   */
   public boolean connect(Edge e, Point2D p1, Point2D p2)
   {
      Node n1 = findNode(p1);
      Node n2 = findNode(p2);
      if (n1 != null && n2 != null)
      {
         e.connect(n1, n2);
         edges.add(e);
         return true;
      }
      return false;
   }

   /**
      Adds a node to the graph so that the top left corner of
      the bounding rectangle is at the given point.
      @param n the node to add
      @param p the desired location
   */
   public boolean add(Node n, Point2D p)
   {
      Rectangle2D bounds = n.getBounds();
      n.translate(p.getX() - bounds.getX(),
         p.getY() - bounds.getY());
      nodes.add(n);
      return true;
   }

   /**
      Finds a node containing the given point.
      @param p a point
      @return a node containing p or null if no nodes contain p
   */
   public Node findNode(Point2D p)
   {
      for (int i = nodes.size() - 1; i >= 0; i--)
      {
         Node n = nodes.get(i);
         if (n.contains(p)) return n;
      }
      return null;
   }

   /**
      Finds an edge containing the given point.
      @param p a point
      @return an edge containing p or null if no edges contain p
   */
   public Edge findEdge(Point2D p)
   {
      for (int i = edges.size() - 1; i >= 0; i--)
      {
         Edge e = edges.get(i);
         if (e.contains(p)) return e;
      }
      return null;
   }

   /**
      Draws the graph
      @param g2 the graphics context
   */
   public void draw(Graphics2D g2)
   {
	   g2.setColor(new Color(0,160,0));
       g2.fillRect( 50, 50, squareSize * 14, squareSize* 10);
       g2.setColor( Color.BLACK );
       g2.drawRect( 50, 50, squareSize * 14, squareSize* 10);
       for(int i = 0; i < 13; i++) {
    	   for(int j = 0; j < 10; j++) {
//    		   if(i < 1){
    			   g2.setColor(new Color(198, 165, 48));
    			   g2.fillRoundRect(97 + squareSize*i, 72 + squareSize*j, 7, 7, 3, 3);  			   
    			   g2.setColor(Color.BLACK);
    			   g2.fillRoundRect(98 + squareSize*i, 73 + squareSize*j, 4, 4, 3, 3);
//    		   }
//    		   if(j < 8){
//    			   g2.setColor(new Color(198, 165, 48));
//    			   g2.fillRoundRect(72 + squareSize*i, 97 + squareSize*j, 7, 7, 3, 3);
//    			   g2.setColor(Color.BLACK);
//    			   g2.fillRoundRect(73 + squareSize*i, 98 + squareSize*j, 4, 4, 3, 3);
//    		   }
    	   }
       }
       for (Node n : nodes)
    	   n.draw(g2);

       for (Edge e : edges)
    	   e.draw(g2);

   }

   /**
      Removes a node and all edges that start or end with that node
      @param n the node to remove
   */
   public void removeNode(Node n)
   {
      for (int i = edges.size() - 1; i >= 0; i--)
      {
         Edge e =  edges.get(i);
         if (e.getStart() == n || e.getEnd() == n)
            edges.remove(e);
      }
      nodes.remove(n);
   }

   /**
      Removes an edge from the graph.
      @param e the edge to remove
   */
   public void removeEdge(Edge e)
   {
      edges.remove(e);
   }

   /**
      Gets the smallest rectangle enclosing the graph
      @param g2 the graphics context
      @return the bounding rectangle
   */
   public Rectangle2D getBounds(Graphics2D g2)
   {
      Rectangle2D r = null;
      for (Node n : nodes)
      {
         Rectangle2D b = n.getBounds();
         if (r == null) r = b;
         else r.add(b);
      }
      for (Edge e : edges)
         r.add(e.getBounds(g2));
      return r == null ? new Rectangle2D.Double() : r;
   }

   /**
      Gets the node types of a particular graph type.
      @return an array of node prototypes
   */
   public abstract Node[] getNodePrototypes();

   /**
      Gets the edge types of a particular graph type.
      @return an array of edge prototypes
   */
   public abstract Edge[] getEdgePrototypes();

   /**
      Gets the nodes of this graph.
      @return an unmodifiable list of the nodes
   */
    public List<Node> getNodes()
    {
    	return Collections.unmodifiableList(nodes); 
    }

    /**
       Gets the edges of this graph.
       @return an unmodifiable list of the edges
    */
    public List<Edge> getEdges()
    {
        return Collections.unmodifiableList(edges);
    }
   
    
    public JTextArea getText() {
    	return text;
    }
        
    public void setupText() {
    	text.setBackground(Color.YELLOW);
    	text.setLineWrap(true);
    	text.setEditable(false);
    	text.setFont(new Font("Segoe Script", Font.BOLD, 20));
    }
    
    public void updateText() {
 	   	text.setText("ShoppingList: \n\n\n" 
 	   				+ resistorAmount + "x Resistor\n"
 	   			    + capacitorAmount + "x Capacitor\n"
 	   				+ inductorAmount + "x Inductor\n"
 	   				+ potAmount + "x Potentiometer\n\n\n\n\n\n\n\n\n"
 	   				+ "total cost: \n" + getPrice()+"$");
    }
    
    public int getResistorAmount(){
 	    return this.resistorAmount;
    }

    public int getComponentAmount(){  	
  	   	 int componentAmount = resistorAmount + capacitorAmount + inductorAmount;
  	   	 return componentAmount;
     }
    
    public void setResistorAmount(int amount) {
 	    this.resistorAmount = amount;
    }

    public int getCapacitorAmount(){
 	    return this.capacitorAmount;
    }
    
    
    public void setCapacitorAmount(int amount) {
 	    this.capacitorAmount = amount;
    }
    
    public int getInductorAmount(){
    	 return this.inductorAmount;
     }
     
     
     public void setInductorAmount(int amount) {
  	     this.inductorAmount = amount;
     }
      
     public void setAmount() { 
 	    int resistor = 0; 
 	    int capacitor = 0; 
 	    int inductor = 0; 
 	    int potentiometer = 0; 
 	    for (Node n : nodes)
 	    {
 	    	if(n.getComponent() == "Resistor") {
 	    		resistor++;
 	    	}
 	    	else if(n.getComponent() == "Capacitor") {
 	    		capacitor++;
 	    	}
 	    	else if(n.getComponent() == "Inductor") {
 	    		inductor++;
 	    	}
 	    }     
 	    resistorAmount = resistor;
 	    capacitorAmount = capacitor;
 	    inductorAmount = inductor;
 	    potAmount = potentiometer;
     }
     
     public void addAmount(String component) {
     	if(component == "Resistor"){
     		resistorAmount++;
     	}
     	else if(component == "Capacitor"){
     		capacitorAmount++;
     	}
     	else if(component == "Inductor"){
     		inductorAmount++;
     	}
     	else if(component == "Potentiometer"){
     		potAmount++;
     	}	
     }
     
    
    public String getPrice() {
    	String price = "";
    	if(total < 0.1) {
    		return "0.00";
    	}
    	else {
    		price = df.format(total);
    		return price;
    	}	
    }
    
    
    public void updatePrice() {
    	double price = 0.0; 
	    for (Node n : nodes)
	    {
	    	if(n.getComponent() == "Resistor" 
	    			|| n.getComponent() == "Capacitor" 
	    			|| n.getComponent() == "Inductor" 
	    			|| n.getComponent() == "Potentiometer") {
	    		
	    		price = price + n.getPrice();
	    	}
	    }  
	    total = price;
    }
    public void setOccupied(int a, int b, boolean c){
    	occupied[a][b]= c;
    }
    public boolean getOccupied(int a, int b){
   	 return occupied[a][b];
    }
    
    private double total = 0.0;
    private int resistorAmount = 0;
    private int capacitorAmount = 0;
    private int inductorAmount = 0;
    private int potAmount = 0;
    
    private boolean occupied [][]= new boolean[14][10];
    private DecimalFormat df = new DecimalFormat("#.00");
    private JTextArea text = new JTextArea(5,10); 
    private int squareSize = 50;
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
}