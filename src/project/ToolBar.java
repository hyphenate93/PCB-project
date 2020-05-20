package project;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

/**
   A tool bar that contains node and edge prototype icons.
   Exactly one icon is selected at any time.
*/
@SuppressWarnings("serial")
public class ToolBar extends JPanel
{
    /**
      Constructs a tool bar with no icons.
     */
	public ToolBar(Graph graph)
	{
		group = new ButtonGroup();
		tools = new ArrayList<Object>();
		setLayout(new FlowLayout(FlowLayout.CENTER));
      
		JToggleButton grabberButton = new JToggleButton(new Icon(){
           
			public int getIconHeight() { return BUTTON_SIZE; }
            public int getIconWidth() { return BUTTON_SIZE; }
            public void paintIcon(Component c, Graphics g, int x, int y) {
            	Graphics2D g2 = (Graphics2D) g;
            	GraphPanel.drawGrabber(g2, x + OFFSET, y + OFFSET);
            	GraphPanel.drawGrabber(g2, x + OFFSET, y + BUTTON_SIZE - OFFSET);
            	GraphPanel.drawGrabber(g2, x + BUTTON_SIZE - OFFSET, y + OFFSET);
            	GraphPanel.drawGrabber(g2, x + BUTTON_SIZE - OFFSET, y + BUTTON_SIZE - OFFSET);
            }
        });
		
		grabberButton.setToolTipText("Move component");
		group.add(grabberButton);
		add(grabberButton);
		grabberButton.setSelected(true);
		tools.add(null);
      
      
		JToggleButton rotateButton = new JToggleButton(new Icon() {
			
			public int getIconHeight() { return BUTTON_SIZE; }
			public int getIconWidth() { return BUTTON_SIZE; }
			public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g2 = (Graphics2D) g;
				GraphPanel.rotateArrow(g2, x + 2, y + 2);   
			}	    
		});
    	      
		rotateButton.setToolTipText("Rotate component");
		group.add(rotateButton);
		add(rotateButton);
		rotateButton.setSelected(false);
		tools.add("rotate");
        
		
		JToggleButton connectButton = new JToggleButton(new Icon() {
			
			public int getIconHeight() { return BUTTON_SIZE; }
			public int getIconWidth() { return BUTTON_SIZE; }
			public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g2 = (Graphics2D) g;
				GraphPanel.rotateArrow(g2, x + 2, y + 2);   
			}	    
		});
    	      
		connectButton.setToolTipText("Check connections");
		group.add(connectButton);
		add(connectButton);
		connectButton.setSelected(false);
		tools.add("connection");
      

		Node[] components = (Node[]) graph.getNodePrototypes();
		for (Node n : components)
			add(n, "add " + n.getComponent());
		}

	/**
      Gets the node or edge prototype that is associated with
      the currently selected button
      @return a Node or Edge prototype
	 */
	public Object getSelectedTool()
	{
		int i = 0;
		for (Object o : tools)
		{
			JToggleButton button = (JToggleButton) getComponent(i++);
			if (button.isSelected()) return o;
		}
		return null;
	}

	/**
      Adds a node to the tool bar.
      @param n the node to add
	 */
	public void add(final Node n, String tooltip)
	{
		JToggleButton button = new JToggleButton(new Icon() {
            public int getIconHeight() { return BUTTON_SIZE; }
            public int getIconWidth() { return BUTTON_SIZE; }
            public void paintIcon(Component c, Graphics g, int x, int y) {
             
            	double width = n.getBounds().getWidth();
            	double height = n.getBounds().getHeight();
            	Graphics2D g2 = (Graphics2D) g;
            	double scaleX = (BUTTON_SIZE - OFFSET)/ width;
            	double scaleY = (BUTTON_SIZE - OFFSET)/ height;
            	double scale = Math.min(scaleX, scaleY);

            
            	AffineTransform oldTransform = g2.getTransform();
            	g2.translate(x, y);
            	g2.scale(scale, scale);
            	g2.translate(Math.max((height - width) / 2, 0), Math.max((width - height) / 2, 0));
            	g2.setColor(Color.black);
            	n.draw(g2);
            	g2.setTransform(oldTransform);
            } 
		});
		button.setToolTipText(tooltip);
		group.add(button);
		add(button);
		tools.add(n);
	}


	public Dimension getPreferredSize() {
		return new Dimension(100, 100);
	}
   
   
	private ButtonGroup group;
	private ArrayList<Object> tools;

	private static final int BUTTON_SIZE = 25;
	private static final int OFFSET = 4;
}
