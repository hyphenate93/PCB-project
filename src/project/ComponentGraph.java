package project;

import java.awt.Color;
import java.awt.geom.*;



public class ComponentGraph extends Graph
{
/*
   public boolean connect(Edge e, Point2D p1, Point2D p2)
   {
      Node n1 = findNode(p1);
      Node n2 = findNode(p2);
      if (n1 == n2) return false;
      return super.connect(e, p1, p2);
   }
*/
   public Node[] getNodePrototypes()
   {
	   Node[] components =
	         {
	            new Resistor(new Color(255, 100, 50)),
	            new Capacitor(Color.WHITE),
	            new Potentiometer(Color.BLUE),
	            new Inductor(Color.WHITE),
	            new Wire(Color.WHITE)};  

      return components;
   }
/*
   public Edge[] getEdgePrototypes()
   {
      ClassRelationshipEdge[] e = new ClassRelationshipEdge[1];

      e[0] = new ClassRelationshipEdge();
//      e[0].setBentStyle(BentStyle.HVH);
//      e[0].setEndArrowHead(ArrowHead.NONE);

      return e;
  }
*/

}





