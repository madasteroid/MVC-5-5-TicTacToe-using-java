import java.util.Scanner;

class TicTacToeModel {
	 static int X=1;
	 static int O = -1;
	int[][] matrix = new int[5][5];
	boolean retVal = false;

	public boolean hasWon() {
		

		// Check for horizontal win
		for (int row = 0; row < matrix.length; row++) {
			int sum = 0;
			for (int col = 0; col < matrix[0].length; col++) {
				sum += matrix[row][col];
			}
			// Check to see if the sum of that row was 5 or -5, a win...
			if (sum == 5) {
				System.out.println("X wins.");
				retVal = true;
			} else if (sum == -5) {
				System.out.println("O wins.");
				retVal = true;
			}
		}

		// Check for vertical win
		for (int col = 0; col < matrix[0].length; col++) {
			int sum = 0;
			for (int row = 0; row < matrix.length; row++) {
				sum += matrix[row][col];
			}
			// Check to see if the sum of that column was 5 or -5, a win...
			if (sum == 5) {
				System.out.println("X wins.");
				retVal = true;
			} else if (sum == -5) {
				System.out.println("O wins.");
				retVal = true;
			}
		}

		// Check for diagonal win
		if ((matrix[0][0] + matrix[1][1] + matrix[2][2] + matrix[3][3] + matrix[4][4] ==5)) {
			System.out.println("X wins.");
			retVal = true;
		} else if ((matrix[0][0] + matrix[1][1] + matrix[2][2] + matrix[3][3] + matrix[4][4]) == -5) {
			System.out.println("O wins.");
			retVal = true;
		}
		if ((matrix[0][4] + matrix[3][1] + matrix[4][0] + matrix[1][3] + matrix[2][2]) == 5) {
			System.out.println("X wins.");
			retVal = true;
		} else if ((matrix[0][4] + matrix[3][1] + matrix[4][0] + matrix[1][3] + matrix[2][2]) == -5) {
			System.out.println("O wins.");
			retVal = true;
		}

		// Check for cat game
		boolean foundSpace = false;
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				if (matrix[row][col] == 0)
					foundSpace = true;
			}
		}
		if (foundSpace == false) {
			System.out.println("Ends in tie.");
			retVal = true;
		}

		return retVal;

	}

	public void checkingTheValueAtTheSpot(int row, int col) {

		if (matrix[row][col] == 0) {
			matrix[row][col] = 'X';
		}

	}
}

class TicTacToeView {
	TicTacToeModel m;
	public void printBoard(int[][] matrix) {
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				// Uses the "global" constants to print out appropriate letter.
				if (matrix[row][col] == m.X)
					System.out.print("X ");
				else if (matrix[row][col] == m.O)
					System.out.print("O ");
				else
					System.out.print(". ");
			}
			// Goes to new line after printing each row
			System.out.println("");
		}
	}



}

class TicTacToeController {
	TicTacToeModel model;
	TicTacToeView view;

	TicTacToeController(TicTacToeView view, TicTacToeModel model) {
		this.view = view;
		this.model = model;
		UserChoice();
	}

	public void UserChoice() {
		Scanner input = new Scanner(System.in);

		while (model.hasWon() == false) {

			System.out.print("X, enter row: ");
			int row = input.nextInt();
			System.out.print("X, enter column: ");
			int col = input.nextInt();
			
			if (model.matrix[row][col] == 0)
				model.matrix[row][col] = model.X;
				
			view.printBoard(this.model.matrix);

			// Check to see if X's move won the game. If so, break out of game loop
			if (model.hasWon() == true)
				break;

			// Get the O player input and make the change if not taken.

			System.out.print("O, enter row: ");
			row = input.nextInt();
			System.out.print("O, enter column: ");
			col = input.nextInt();
			if (model.matrix[row][col] == 0)
				model.matrix[row][col] = model.O;
			view.printBoard(this.model.matrix);

		}

		System.out.println("Game over.");
	}

}

public class mvcpattern {

	public static void main(String[] args) {

		TicTacToeModel model = new TicTacToeModel();
		TicTacToeView view = new TicTacToeView();
		TicTacToeController controller = new TicTacToeController(view, model);

	}

}