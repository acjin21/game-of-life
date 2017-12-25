import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/*
 * BoardPanel is a class that extends the JPanel class, adding the functionality
 * of painting the current generation of a Game of Life.
 */
public class BoardPanel extends JPanel {
	private GameOfLife game;
	private int numRows; //y, height
	private int numCols; //x, width
	
	private Color aliveColor = Color.BLACK;
	
	public BoardPanel(GameOfLife g){
		game = g;
		numRows = game.getRows();
		numCols = game.getCols();
	}
	
	/**
	 * Paints the current state of the Game of Life board onto
	 * this panel. This method is invoked for you each time you
	 * call repaint() on either this object or on the JFrame upon
	 * which this panel is placed.
	 */
	public void paintComponent(Graphics g){
	
		Graphics2D g2 = (Graphics2D)g;
		double boxHeight = (double)getHeight()/numRows;
		double boxWidth = (double)getWidth()/numCols;
		for(int row = 0; row < numRows; row++){
			for(int col = 0; col < numCols; col++){
				if(game.isAlive(row,col)){
					g2.setColor(aliveColor);
					g2.fillRect((int)(col*boxWidth), (int)(row*boxHeight), (int)(boxWidth),(int) boxHeight);
				}
				g2.setColor(Color.lightGray);
				g2.drawRect((int)(col*boxWidth), (int)(row*boxHeight), (int)(boxWidth),(int) boxHeight);
			}
		}
	}
	
	/*
	 * setColor(Color c) sets the color of the alive cells to the Color input
	 * Parameters:
	 * 		Color c = color for alive cells
	 */
	public void setColor(Color c) {
		aliveColor = c;
	}

}
