package project;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/**
 * This frame shows the tool bar and the board.
 */
@SuppressWarnings("serial")
public class Framework extends JFrame {
	private static JTextArea text;
	private BreadBoard board;
	private BreadBoardInteractions panel;
	private JScrollPane scrollPane;
	private ToolBar toolBar;

	public static final int FRAME_WIDTH = 1200;
	public static final int FRAME_HEIGHT = 700;

	/**
	 * Constructs a board frame that displays a given board.
	 * 
	 * @param capacity the maximum capacity of the queue
	 * @precondition board != null
	 * @param board the board to display
	 */
	public Framework(final BreadBoard board) {
		assert board != null : "Violated precondition, board is set to null";
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.board = board;

		constructFrameComponents();
		// set up menus

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		JMenuItem openItem = new JMenuItem("Open");
		openItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				openFile();
			}
		});
		fileMenu.add(openItem);

		JMenuItem saveItem = new JMenuItem("Save");
		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				saveFile();
			}
		});
		fileMenu.add(saveItem);

		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		fileMenu.add(exitItem);

		JMenuItem deleteItem = new JMenuItem("Delete");
		deleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				panel.removeSelected();
			}
		});

		JMenu editMenu = new JMenu("Edit");
		editMenu.add(deleteItem);
		menuBar.add(editMenu);

	}

	/**
	 * @postcondition this.scrollPane != null 
	 * Constructs the tool bar and board panel.
	 */
	private void constructFrameComponents() {
		toolBar = new ToolBar(board);
		panel = new BreadBoardInteractions(toolBar, board);
		scrollPane = new JScrollPane(panel);
		text = new JTextArea(5, 10);
		setupText();
		updateText();
		this.add(text, BorderLayout.EAST);
		this.add(toolBar, BorderLayout.WEST);
		this.add(scrollPane, BorderLayout.CENTER);
		assert this.scrollPane != null : "Violated postCondition, nothing added to scrollPane";
	}

	/**
	 * Asks the user to open a board file.
	 */
	private void openFile() {
		// let user select file

		JFileChooser fileChooser = new JFileChooser();
		int r = fileChooser.showOpenDialog(this);
		if (r == JFileChooser.APPROVE_OPTION) {
			// open the file that the user selected
			try {
				File file = fileChooser.getSelectedFile();
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
				board = (BreadBoard) in.readObject();
				in.close();
				this.remove(scrollPane);
				this.remove(toolBar);
				this.remove(text);
				constructFrameComponents();
				board.setAmount();
				updateText();
				revalidate();
				repaint();

			} catch (IOException exception) {
				JOptionPane.showMessageDialog(null, exception);
			} catch (ClassNotFoundException exception) {
				JOptionPane.showMessageDialog(null, exception);
			}
		}
	}

	/**
	 * Saves the current board in a file.
	 */
	private void saveFile() {
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			try {
				File file = fileChooser.getSelectedFile();
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
				out.writeObject(board);

				out.close();
			} catch (IOException exception) {
				JOptionPane.showMessageDialog(null, exception);
			}
		}
	}

	public void setupText() {

		text.setBackground(Color.YELLOW);
		text.setLineWrap(true);
		text.setEditable(false);
		text.setFont(new Font("Segoe Script", Font.BOLD, 20));
	}

	public void updateText() {
		text.setText("ShoppingList: \n\n\n" + board.getResistorAmount() + "x Resistor\n" + board.getCapacitorAmount()
				+ "x Capacitor\n" + board.getInductorAmount() + "x Inductor\n" + board.getPotAmount()
				+ "x Potentiometer\n" + board.getWireAmount() + "x Wire\n\n\n\n\n\n\n\n\n" + "total cost: \n"
				+ board.getPrice() + "$");
	}

	public static JTextArea getText() {
		return text;
	}

	public void setBoardText(String s) {
		text.setText(s);
	}

}
