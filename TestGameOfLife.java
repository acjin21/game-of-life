/*
 * Class TestGameOfLife tests methods from the GameOfLife class:
 * 
 * Constructors GameOfLife(int numRows, int numCols), GameOfLife(int size), and GameOfLife(int[][] initialState)
 * Other methods: toString(), getRows(), getCols(), getGenNum(), resetGenNum(),  getCell(), setCell(), countNeighbors(), 
 * isAlive(), willLive(), nextGeneration(), countAlive(), randomizeBoard(), and clearBoard()
 *  
 */
public class TestGameOfLife {
	
	public static void main(String[] args){
		int[][] arr = {{0,0,1},{0,1,0},{1,1,1}};
		GameOfLife life1 = new GameOfLife(4,4);
		life1.randomizeBoard();

		GameOfLife life2 = new GameOfLife(arr);
		
		GameOfLife life3 = new GameOfLife(5);
		
		System.out.println(life1.toString());
		System.out.println("has " + life1.getRows() + " rows and " + life1.getCols() + " columns");
		
		System.out.println(life2.toString());
		System.out.println("has " + life2.getRows() + " rows and " + life2.getCols() + " columns");

		System.out.println(life3.toString());
		System.out.println("has " + life2.getRows() + " rows and " + life2.getCols() + " columns");

		
		System.out.println(life1.getGenNum());
		
		System.out.println(life1.getCell(4, 4)); //invalid
		System.out.println(life1.getCell(1, 0));
		
		System.out.println(life1.setCell(1, 0, 1));
		
		System.out.println(life1.toString());
		
		System.out.println(life1.countNeighbors(1,1)); //returns 5
		
		System.out.println(life1.isAlive(0, 0)); //false
		
		System.out.println(life1.willLive(0, 0)); //false EXACTLY 2 NEIGHBORS
		System.out.println(life1.willLive(1,1));//false
		System.out.println(life1.willLive(0, 2)); //false
		System.out.println(life1.willLive(1, 0)); //true
		
		System.out.println(life1.toString());
		System.out.println("Generation: " + life1.getGenNum());
		life1.nextGeneration();
		
		System.out.println(life1.toString());
		System.out.println("Generation: " + life1.getGenNum());
		life1.nextGeneration();
		
		System.out.println(life1.toString());
		System.out.println("Generation: " + life1.getGenNum());
		life1.nextGeneration();
		
		System.out.println(life1.toString());
		System.out.println("Generation: " + life1.getGenNum());
		
		life1.resetGenNum();
		System.out.println("Generation: " + life1.getGenNum()); //should return 0

		
		/*
		 * Remaining methods tested when making the GameOfLife Display
		 */
		
	}
}
