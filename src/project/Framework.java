package project;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
   A program for editing UML diagrams.
*/
public class Framework
{
   public static void main(String[] args)
   {
      JFrame frame = new GraphFrame(new ComponentGraph());
      frame.setVisible(true);
   }
}