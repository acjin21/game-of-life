/*
 * Class GameBoard simulates a rectangular game board with rows of individual cells. Each GameBoard object is not specific to 
 * any one board game. This class contains methods that carry out functions generally associated with board games.
 * 
 * Contains constructors GameBoard(int numRows, int numCols), GameBoard(int size), and GameBoard(int[][] initialState)
 * Other methods: toString(), getRows(), getCols(), getPiece(), setPiece(), removePiece(), isValid(), isOccupied()
 * 
 */
public class GameBoard {
	private int[][] gameBoard;
	private int numRows;
	private int numCols;
			
	/*
	 * Constructor that takes in the number of rows and number of columns
	 * Constructs GameBoard object simulating a rectangular game board with given dimensions 
	 * 
	 * Parameters:
	 * 		int numRows = number of rows on game board, represents height of game board
	 * 		int numCols = number of columns on game board, represents width of game board
	 */
	public GameBoard(int numRows, int numCols){
		this.numRows = numRows;
		this.numCols = numCols;
		gameBoard = new int[numRows][numCols];
	}
	
	/*
	 * Constructor that takes in one integer
	 * Constructs GameBoard object simulating a square game board with given side length 
	 * 
	 * Parameters:
	 * 		int size = length of side
	 */
	public GameBoard(int size){
		this.numRows = size;
		this.numCols = size;
		gameBoard = new int[size][size];
	}
	
	/*
	 * Constructor based on an initial game board state
	 * Constructs GameBoard object simulating a game board with an initialState from user
	 * 
	 * Parameters:
	 * 		int[][] initialState = 2D array representing game board in initial state
	 */
	public GameBoard(int[][] initialState){
		this.numRows = initialState.length;
		this.numCols = initialState[0].length; 
		gameBoard = new int[initialState.length][initialState[0].length];
		for(int i = 0; i<numRows; i++){
			for(int j = 0; j<numCols; j++){
				gameBoard[i][j] = initialState[i][j];
			}
		}
	}
	
	/*
	 * Returns a String representation of a GameBoard object in form of a matrix
	 */
	public String toString(){
		String board = "";
		for(int[] array: gameBoard){
			for(int value:array){ 
				board += value + " ";
			}
			board += "\n";
		}
		return board;
	}
	
	/*
	 * Method getRows() returns the number of rows in int[][] gameBoard
	 */
	public int getRows(){
		return gameBoard.length;
	}
	
	/*
	 * Method getCols() returns the number of columns in int[][] gameBoard
	 */
	public int getCols(){
		return gameBoard[0].length;
	}
	
	/*
	 * Method getPiece(int row, int col) returns the piece at given row and column on int[][] gameBoard
	 * Parameters:
	 * 		int row = given row on game board
	 * 		int col = given column on game board
	 */
	public int getPiece(int row, int col){
		if(isValid(row,col)){
			return gameBoard[row][col];
		}
		return 0; 
	}
	
	/*
	 * Method setPiece(int row, int col, int piece) sets the piece at the given row and column to the given value and 
	 * returns the integer value of the piece originally at that location
	 * 
	 * Parameters:
	 * 		int row = given row on game board
	 * 		int col = given column on game board
	 * 		int piece = given piece that is replacing previous value at gameBoard[row][col] 
	 */
	public int setPiece(int row, int col, int piece){
		if(isValid(row,col)){
			int replacedPiece = gameBoard[row][col];
			gameBoard[row][col] = piece;
			return replacedPiece;
		}
		return 0; //WHAT TO RETURN IF NOT VALID
	}
	
	/*
	 * Method removePiece(int row, int col) removes the piece at the given row and column (value of empty spot is 0)
	 * and returns the integer value of that piece. 
	 * 
	 * Parameters:
	 * 		int row = given row on game board
	 * 		int col = given column on game board
	 */
	public int removePiece(int row, int col){
		if(isValid(row,col)){
			return setPiece(row,col,0);
		}
		return 0; 
	}
	
	/*
	 * Method isValid(int row, int col) returns whether the given location is on the board
	 * 
	 * Parameters:
	 * 		int row = given row on game board
	 * 		int col = given column on game board
	 */
	public boolean isValid(int row, int col){
		if(row >= 0 && row < numRows && col >= 0 && col < numCols){
			return true;
		}
		return false;
	}
	
	/*
	 * Overloaded method isOccupied(int row, int col) determines whether the given location is occupied by another piece
	 * 
	 * Parameters:
	 * 		int row = given row on game board
	 * 		int col = given column on game board
	 */
	public boolean isOccupied(int row, int col){
		if(isValid(row,col)){
			if(gameBoard[row][col] != 0){
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Overloaded method isOccupied(int row, int col, int piece) determines whether the given location is occupied by a specific type of piece
	 * 
	 * Parameters:
	 * 		int row = given row on game board
	 * 		int col = given column on game board
	 * 		int pieceType = type of given piece to check for
	 */
	public boolean isOccupied(int row, int col, int pieceType){
		if(isValid(row,col)){
			if(gameBoard[row][col] == pieceType){
				return true;
			}
		}
		return false;
	}
}


