package project;

import javax.swing.JFrame;


public class Framework
{
	
	public static void main(String[] args) {
		JFrame frame = new GraphFrame(new ComponentGraph());
		frame.setVisible(true);
	}
}