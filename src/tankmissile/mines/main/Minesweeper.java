package tankmissile.mines.main;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Minesweeper {
	public static final String WINDOW_TITLE = "Minesweeper";
	public static final String GAME_TITLE = "Minesweeper";
	public static final int DEFAULT_WIDTH = 20;
	public static final int DEFAULT_HEIGHT = 20;
	public static final int DEFAULT_MINES = 50;
	
	Board b;
	
	private static void createWindow() {
        //Create and set up the window.
        JFrame frame = new JFrame(WINDOW_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel(GAME_TITLE);
        frame.getContentPane().add(label);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	public static void main(String[] args) {
		Minesweeper m = new Minesweeper();
		m.b = new Board(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_MINES);
		
		//create the window
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createWindow();
            }
        });
	}

}
