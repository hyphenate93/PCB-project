package project;

import java.awt.*;
import java.awt.geom.*;

/**
   An inivisible node that is used in the toolbar to draw an
   edge.
*/
public class PointNode implements Node
{
   /**
      Constructs a point node with coordinates (0, 0)
   */
   public PointNode()
   {
      point = new Point2D.Double();
   }

   public void draw(Graphics2D g2)
   {
   }

   public void translate(double dx, double dy)
   {
      point.setLocation(point.getX() + dx,
         point.getY() + dy);
   }

   public boolean contains(Point2D p)
   {
      return false;
   }

   public Rectangle2D getBounds()
   {
      return new Rectangle2D.Double(point.getX(), 
         point.getY(), 0, 0);
   }

   public Point2D getConnectionPoint(Point2D other)
   {
      return point;
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
   
   @Override
   public String getComponent() {
   	// TODO Auto-generated method stub
   	return null;
   }
   
   
   @Override
   public double getPrice() {
   	// TODO Auto-generated method stub
   	return 0.0;
   }
   

   private Point2D point;


@Override
public double getX() {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public double getY() {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public void setX(double a) {
	// TODO Auto-generated method stub
	
}

@Override
public void setY(double a) {
	// TODO Auto-generated method stub
	
}

@Override
public void setOrientation(String s) {
	// TODO Auto-generated method stub
	
}   

@Override
public String getOrientation() {
	return null;
}   

}
