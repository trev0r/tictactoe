package tictactoe;
/**
 * A Tic-Tac-Toe playing applications.
 * Starts game with:
 * 	3x3 Board
 * 	CLI Interface
 * 	X - Human Player
 *  0 - AI Player
 * @author Trevor Reid
 *
 */
public class Main {

	public static void main(String[] args) {
		int size = 3;

		AbstractBoard view = new CLIBoard(size);
		TicTacToeModel model = new TicTacToeModel(size);
		TicTacToeController controller = new TicTacToeController(view, model);

		controller.addPlayer(new HumanPlayer(Marker.X, view));
		controller.addPlayer(new DumbPlayer(Marker.O, controller));

		controller.startGame();

	}
}





