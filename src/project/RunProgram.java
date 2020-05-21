package project;

import javax.swing.JFrame;

/**
 * 
 * @author Niklas Andersson
 * @author Andreas Östlin
 *
 * Run application
 */
public class RunProgram
{
	public static void main(String[] args) {
		JFrame frame = new Framework(new BreadBoardComponent());
		frame.setVisible(true);
	}
}