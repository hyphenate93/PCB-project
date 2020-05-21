package project;

import javax.swing.JFrame;


public class PCB
{
	
	public static void main(String[] args) {
		JFrame frame = new GraphFrame(new ComponentGraph());
		frame.setVisible(true);
	}
}