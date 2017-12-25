/*
 * TestGameBoard class tests the following methods from Class GameBoard:
 * 
 * constructors GameBoard(int numRows, int numCols), GameBoard(int size), and GameBoard(int[][] initialState)
 * Other methods: toString(), getRows(), getCols(), getPiece(), setPiece(), removePiece(), isValid(), isOccupied()
 */

public class TestGameBoard {
	/*
	 * Class generateRandomBoard(int numRows, int numCols) returns int[][] board with given number of rows and columns
	 * All values in the array are randomly generated (contains integers between 0 and 9, inclusive)
	 * 
	 * Parameters:
	 * 		int numRows = given number of rows 
	 * 		int numCols = give number of columns
	 */
	public static int[][] generateRandomBoard(int numRows, int numCols){
		int[][] board = new int[numRows][numCols];
		for(int row = 0; row < board.length; row++){
			for(int col = 0; col<board[row].length; col++){
				board[row][col] = (int)(Math.random()*10); //adds ints 0-9 inclusive
			}
		}
		return board;
	}
	public static void main(String[] args){
		GameBoard b1 = new GameBoard(3,5);
		GameBoard b2 = new GameBoard(4);
		GameBoard b3 = new GameBoard(new int[3][2]);
		GameBoard b4 = new GameBoard(generateRandomBoard(2,4));
		GameBoard b5 = new GameBoard(generateRandomBoard(3,6));


		
        /*
         * TEST toString()
         */
        System.out.println("**TEST toString()**");
        System.out.println("board b1\n" + b1.toString() );
		System.out.println("board b2\n" + b2.toString());
		System.out.println("board b3\n" + b3.toString());
		System.out.println("board b4\n" + b4.toString());
		System.out.println("board b5\n" + b5.toString());

		/*
		 * TEST getRows()
		 */
		System.out.println("**TEST getRows()**");
		System.out.println(b1.getRows());
		System.out.println(b2.getRows());
		System.out.println(b3.getRows());
		System.out.println();
		
		/*
		 * TEST getCols()
		 */
		System.out.println("**TEST getCols()**");
		System.out.println(b1.getCols());
		System.out.println(b2.getCols());
		System.out.println(b3.getCols());
		System.out.println();
		
		/*
		 * Test getPiece()
		 */
		System.out.println("**TEST getPiece()**");
		System.out.println("The piece on board b1 at row 2, column 3 is " + b1.getPiece(2,3));
		System.out.println("The piece on board b2 at row 3, column 3 is " + b2.getPiece(3,3));
		System.out.println("The piece on board b3 at row 2, column 3 is " + b3.getPiece(2,3)); //should return BLAH
		System.out.println("The piece on board b4 at row 2, column 3 is " + b4.getPiece(2,3)); //should return BLAH
		System.out.println("The piece on board b4 at row 1, column 3 is " + b4.getPiece(1,3));
		System.out.println();


		/*
		 * Test setPiece()
		 */
		System.out.println("**TEST setPiece()**");
		System.out.println("The piece on board b1 at row 2, column 3 was " + b1.setPiece(2,3,-1) + ", but is now " + b1.getPiece(2,3));
		System.out.println("The piece on board b2 at row 3, column 3 was " + b2.setPiece(3,3, -1) + ", but is now " +  b2.getPiece(3,3));
		System.out.println("The piece on board b3 at row 2, column 3 was " + b3.setPiece(2,3,-1) + ", but is now " +  b3.getPiece(2,3)); //should return BLAH
		System.out.println("The piece on board b4 at row 2, column 3 was " + b4.setPiece(2,3,-1) + ", but is now " + b4.getPiece(2,3)); //should return BLAH
		System.out.println("The piece on board b4 at row 1, column 3 was " + b4.setPiece(1,3,-1) + ", but is now " + b4.getPiece(1,3));
		System.out.println();
		System.out.println("new board b1\n" + b1.toString() );
		System.out.println("new board b2\n" + b2.toString());
		System.out.println("new board b3\n" + b3.toString());
		System.out.println("new board b4\n" + b4.toString());
		
		/*
		 * Test removePiece()
		 */
		System.out.println("**TEST removePiece()**");
		System.out.println("The piece " + b1.removePiece(2,3) + " was removed from board b1 row 2, column 3"); 
		System.out.println("The piece " + b2.removePiece(3,3) + " was removed from board b2 row 3, column 3");
		System.out.println("The piece " + b3.removePiece(0,0) + " was removed from board b3 row 0, column 0");
		System.out.println("The piece " + b4.removePiece(0,0) + " was removed from board b4 row 0, column 0");
		System.out.println("The piece " + b4.removePiece(10,0) + " was removed from board b4 row 10, column 0"); //should return BLAH
		System.out.println("The piece " + b4.removePiece(1,2) + " was removed from board b4 row 1, column 2");
		System.out.println();
		System.out.println("new board b1\n" + b1.toString() );
		System.out.println("new board b2\n" + b2.toString());
		System.out.println("new board b3\n" + b3.toString());
		System.out.println("new board b4\n" + b4.toString());
		
		/*
		 * isValid() tested through other method tests because isValid() is used within those methods
		 */
		
		/*
		 * Test isOccupied()
		 */
		System.out.println("**TEST isOccupied()**");
		System.out.println(b1.isOccupied(0,0)); //false
		System.out.println(b2.isOccupied(0,0)); //false
		System.out.println(b3.isOccupied(2,1)); //false
		System.out.println(b4.isOccupied(2,3)); //false bc not valid location
		System.out.println(b4.isOccupied(1,3)); //random, so don't know
		
		System.out.println(b4.isOccupied(1,3,2)); //random, so don't know, check bottom right corner of b4
		System.out.println(b4.isOccupied(1,2,2)); //random, so don't know, check value directly left of bottom right corner of b4

	}
	
}
