import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.Timer;

/*
 * Displays generations of John Conway's Game of Life.
 * Allows a user of the program to step through one generation
 * at a time or to run the generations based on a timer.
 * The user may also reset the display to a randomly generated state or 
 * change the color of the alive cells.
 */
public class GameOfLifeDisplay extends JFrame {
	
	private JPanel contentPane;
	private BoardPanel boardPanel;
	private JLabel txtGeneration = new JLabel();
	private int delay = 1000;
	Timer timer;
	private GameOfLife g;
	Color aliveColor = Color.BLACK;
	private int width, height = 10;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOfLifeDisplay frame = new GameOfLifeDisplay(); 
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame. Adds a button panel to the frame and
	 * initializes the usage of each button.
	 */
	public GameOfLifeDisplay() {
		userInput();
		g = new GameOfLife(height,width); 
		g.randomizeBoard();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		
		/*
		 * create a JSlider 
		 */
		JPanel sliderPanel = new JPanel();
		sliderPanel.setBackground(Color.lightGray);
		contentPane.add(sliderPanel, BorderLayout.SOUTH);
		JSlider speedSlider = new JSlider(0,1000,0); 
		sliderPanel.add(speedSlider);
		
		/*
		 * Defines what will occur when JSlider is run 
		 */
		speedSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				delay = 1000-speedSlider.getValue(); //small speedSlider value (near left) = longer delay, slower speed
				timer.setDelay(delay);
				
			}
		});
		
		/*
		 * creates a Timer and defines what will occur when
		 * it is run when the user clicks the "start" button
		 */
		
		timer = new Timer(delay, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				g.nextGeneration();
				txtGeneration.setText("Generation " + g.getGenNum());
				repaint();
				
			}
			
		});
		
		
		/*
		 * creates the button panel
		 */
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.lightGray);
		contentPane.add(buttonPanel, BorderLayout.NORTH);

		
		/*
		 * adds a button which allows the user to reset the game and 
		 * begin again with a randomly generated board 
		 */
		
		JButton resetButton = new JButton("Reset to Random Board");
		buttonPanel.add(resetButton);
		resetButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg1) {
				if(resetButton.getText().equals("Reset to Random Board")){
					g.randomizeBoard();
					txtGeneration.setText("Generation 0");
					repaint();
		
				}
				
			}
			
		});
		
	
		/*
		 * adds a button which allows the user to step through
		 * the game one generation at a time
		 */
		JButton nextGenButton = new JButton("Next Gen");
		buttonPanel.add(nextGenButton);
		nextGenButton.addActionListener(new ActionListener(){

			@Override
			//generates and displays the next generation
			public void actionPerformed(ActionEvent e) {
				g.nextGeneration();
				txtGeneration.setText("Generation " + g.getGenNum());
				repaint();
			}
			
		});

		
		/*
		 * creates a button that allows the game to run on 
		 * a timer. The label toggles between "Start" and "Stop"
		 */
		JButton startStopButton = new JButton("Start");
		buttonPanel.add(startStopButton);
		startStopButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(startStopButton.getText().equals("Start")){
					startStopButton.setText("Stop");
					timer.start();
					
				}
				else{
					startStopButton.setText("Start");
					timer.stop();
				}
				
			}
			
		});
	
		/*
		 * creates a button that allows the user to access a JColorChooser 
		 * and choose the color of the alive cells
		 */
		JButton colorButton = new JButton("Choose Alive Color");
		sliderPanel.add(colorButton);
		colorButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg) {
				aliveColor = JColorChooser.showDialog(null, "Color Chooser", aliveColor);
				boardPanel.setColor(aliveColor);
				repaint();
			} 
		});
		
		/*
		 * creates a button that allows the user to set
		 *  an initial board state by clicking on cells
		 */
//		JButton setStateButton = new JButton("Set Initial Board State");
//		sliderPanel.add(setStateButton);
//		setStateButton.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent arg){
//				g.clearBoard();
//				repaint();
				contentPane.addMouseListener(new MouseListener(){
					public void mouseClicked(MouseEvent e){
//						double boxWidth = (double)boardPanel.getWidth()/g.getCols();
//						double boxHeight = (double)boardPanel.getHeight()/g.getRows();
						int cellColIndex = (int)(e.getX()/(boardPanel.getWidth()/g.getCols()));
						int cellRowIndex = (int)(e.getY()/(boardPanel.getHeight()/g.getRows())) ; //Figure this out
						//int cellColIndex = e.getX()*g.getCols()/boardPanel.getWidth();
						//int cellRowIndex = e.getY()*g.getRows()/boardPanel.getHeight();
						if(g.isAlive(cellRowIndex,cellColIndex)){
							g.setCell(cellRowIndex,cellColIndex, 0);
						}
						else{
							g.setCell(cellRowIndex, cellColIndex, 1);
						}
						repaint();
					}

					@Override
					public void mousePressed(MouseEvent e) {
					}
					@Override
					public void mouseReleased(MouseEvent e) {
					}
					@Override
					public void mouseEntered(MouseEvent e) {						
					}
					@Override
					public void mouseExited(MouseEvent e) {
					}
				});
				
			//}
		//});
		
		/*
		 * displays the generation number
		 */
		txtGeneration.setText("Generation 0");
		buttonPanel.add(txtGeneration);
		
		

		/*
		 * adds the panel which displays the Game of Life
		 * board. See the BoardPanel class for details.
		 */
		boardPanel = new BoardPanel(g);
		contentPane.add(boardPanel, BorderLayout.CENTER);
		
	}
	/*
	 * userInput() prompts the user for the width and height of the Game Of Life board. 
	 * 
	 */
	private void userInput(){
		System.out.println("Welcome to A Simulation of John Conway's Game of Life.");
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the width of the board: ");
		width = s.nextInt();
		System.out.println("Enter the height of the board: ");
		height = s.nextInt();
	}

	
}
