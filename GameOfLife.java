/*
 * Class GameOfLife simulates John Conway's Game Of Life game with various rules based on how the game is played and 
 * methods that carry out functions specific to the Game of Life.
 * 
 * Contains constructors GameOfLife(int numRows, int numCols), GameOfLife(int size), and GameOfLife(int[][] initialState)
 * Other methods: toString(), getRows(), getCols(), getGenNum(), resetGenNum(),  getCell(), setCell(), countNeighbors(), 
 * isAlive(), willLive(), nextGeneration(), countAlive(), randomizeBoard(), and clearBoard()
 */
public class GameOfLife {
	private GameBoard board;
	private int numRows;
	private int numCols;
	private int genNum = 0; 
	public final static int ALIVE = 1;
	public final static int DEAD = 0;
	
	/*
	 * Constructor that takes in the number of rows and number of columns
	 * Constructs GameOfLife object simulating a rectangular Game Of Life game board with given dimensions 
	 * 
	 * Parameters:
	 * 		int numRows = number of rows on game board, represents height of game board
	 * 		int numCols = number of columns on game board, represents width of game board
	 */
	public GameOfLife(int numRows, int numCols){
		board = new GameBoard(numRows,numCols);
		this.numRows = numRows;
		this.numCols = numCols;
	}
	
	/*
	 * Constructor that takes in one integer
	 * Constructs GameOfLife object simulating a square Game Of Life game board with given side length 
	 * 
	 * Parameters:
	 * 		int size = length of side
	 */
	public GameOfLife(int size){
		board = new GameBoard(size);
		numRows = size;
		numCols = size;
	}
	
	/*
	 * Constructor based on an initial game board state
	 * Constructs GameOfLife object simulating a Game Of Life game board with an initialState from user
	 * 
	 * Parameters:
	 * 		int[][] initialState = 2D array representing Game Of Life game board in initial state
	 */
	public GameOfLife(int[][] initialState){
		board = new GameBoard(initialState);
		numRows = board.getRows();
		numCols = board.getCols();
	}
	
	/*
	 * Returns a String representation of a GameOfLife object in form of a matrix, 
	 * with alive cells represented by "x" and dead cells represented by "."
	 */
	public String toString(){
		String boardStr = "Generation " + genNum + ": \n";
		for(int row = 0; row < numRows; row++ ){
			for(int col = 0; col < numCols; col++){ 
				if(board.getPiece(row,col) == ALIVE){
					boardStr += "x ";
				}
				else{
					boardStr += ". ";
				}
			}
			boardStr += "\n";
		}	
		return boardStr;
	}
	
	/*
	 * Getter method for number of rows 
	 * Returns:
	 * 		int numRows = number of rows in Game Of Life game board
	 */
	public int getRows(){
		return numRows;
	}
		
	/*
	 * Getter method for number of columns 
	 * Returns:
	 * 		int numCols = number of columns in Game Of Life game board
	 */	public int getCols(){
		return numCols;
	}
	
	 /*
	  * Getter method for generation number
	  *  Returns:
	  *  	int genNum = current generation number 
	  */
	public int getGenNum(){
		return genNum;
	}
	/*
	 * Method that resets the generation number (by setting it to 0)
	 */
	public void resetGenNum(){
		genNum = 0;
	}
	
	/*
	 * Method getCell(int row, int col) returns the current state of 
	 * the cell at given row and column on the game board
	 * 
	 * Parameters:
	 * 		int row = given row on game board
	 * 		int col = given column on game board
	 */	
	public int getCell(int row, int col){
		return board.getPiece(row,col); //Will return 0 if not valid location
	}

	/*
	 * Method setCell(int row, int col, int state) sets the state of the cell
	 * at the given row and column to the given value and 
	 * returns the integer value of the cell originally at that location
	 * 
	 * Parameters:
	 * 		int row = given row on game board
	 * 		int col = given column on game board
	 * 		int state = given piece that is replacing previous value at gameBoard[row][col] 
	 */
	public int setCell(int row, int col, int state){ 
		return board.setPiece(row, col, state);
	}	
	
	/*
	 * Method returns int numNeighbors, the number of living neighbors adjacent to the cell at the given location
	 * 
	 * Parameters:
	 * 		int row = given row on game board
	 * 		int col = given column on game board
	 */
	public int countNeighbors(int row, int col){
		int numNeighbors = 0;
		for(int r = row-1; r< row + 2; r++){
			for(int c = col-1; c<col + 2; c++){
				if(board.isValid(r,c)){ 
					if(board.getPiece(r, c) == ALIVE){
						numNeighbors++;
					}
				}
			}
		}
		if(getCell(row,col) == ALIVE){
			numNeighbors--;
		}
		return numNeighbors;
	}

	/*
	 * Method returns whether a cell at the given location is currently alive
	 * 
	 * Parameters:
	 * 		int row = given row on game board
	 * 		int col = given column on game board
	 * 
	 * Returns: 
	 *		boolean isAlive = whether or not the cell is alive
	 */
	public boolean isAlive(int row, int col){
		if(getCell(row,col) == ALIVE){
			return true;
		}
		return false;
	}
	/*
	 * Method determines the fate of the cell for the next generation 
	 * and returns whether or not the cell will live
	 * 
	 * Parameters:
	 * 		int row = given row on game board
	 * 		int col = given column on game board
	 * 
	 * Returns: 
	 *		(boolean) whether or not the cell will live in the next generation
	 */
	public boolean willLive(int row, int col){
		if(countNeighbors(row,col) == 3 || countNeighbors(row,col) == 2 && isAlive(row,col) ){ //if less than 2 neighbors or more than 3 neighbors
			return true;
		}
		else{ //if exactly 3 neighbors
			return false;
		}
	}
	/*
	 * Method calculates the next generation according to the Game Of Life rules and 
	 * creates a new GameBoard object representing the state of the board in the next generation
	 */
	public void nextGeneration(){
		genNum++;
		GameBoard newGameBoard = new GameBoard(numRows,numCols);
		for(int row = 0; row < numRows; row++ ){
			for(int col = 0; col < numCols; col++){ 
				if(countNeighbors(row,col) == 2){ //stays same 
					newGameBoard.setPiece(row,col,getCell(row,col)); 
				}
				else if(willLive(row,col)){ //will live
					newGameBoard.setPiece(row,col,ALIVE);
				}
				else{ //will die
					newGameBoard.setPiece(row,col,DEAD);
				}
			}
		}	
		board = newGameBoard;
	}

	/*
	 * Method returns the number of living cells in the current generation
	 * 
	 * Returns:
	 * 		int numAlive = number of cells alive in current generation
	 */

	public int countAlive(){
		int numAlive = 0;
		for(int row = 0; row < numRows; row++ ){
			for(int col = 0; col < numCols; col++){ 
				if(isAlive(row,col)){
					numAlive++;
				}
			}
		}
		return numAlive;
	}

	/*
	 * Method randomizes, or scrambles, the current GameOfLife board to generate a random board state
	 */
	public void randomizeBoard(){
		resetGenNum();
		for(int row = 0; row < getRows(); row++){
			for(int col = 0; col< getCols(); col++){
				setCell(row, col, (int)(Math.random()*2)) ; //adds ints 0-1 inclusive
			}
		}
	}
	
	/*
	 * Method clears the current GameOfLife board, which removes all alive cells
	 */
	public void clearBoard(){
		resetGenNum();
		for(int row = 0; row < getRows(); row++){
			for(int col = 0; col< getCols(); col++){
				setCell(row, col, 0); 
			}
		}
	}
}
