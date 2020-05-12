package project;

import java.awt.BasicStroke;
import java.awt.Stroke;

/**
   This enumeration defines line styles of various shapes.
*/
public enum LineStyle
{
   SOLID;

   /**
      Gets a stroke with which to draw this line style.
      @return the stroke object that strokes this line style
   */
   public Stroke getStroke()
   {
      return SOLID_STROKE;
   }

   private static Stroke SOLID_STROKE = new BasicStroke();
}
