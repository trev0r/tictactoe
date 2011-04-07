package tictactoe;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

/**
 * Controller for a game of Tic-Tac-Toe. Manages players and keeps track of
 * turns.
 * 
 * @author Trevor Reid
 * 
 */
class TicTacToeController implements Observer {
	private AbstractBoard view;
	private TicTacToeModel model;
	private Queue<AbstractTicTacToePlayer> players = new LinkedList<AbstractTicTacToePlayer>();
	private AbstractTicTacToePlayer currentPlayer;

	/**
	 * 
	 * @param size
	 *            the size of the game board.
	 * @param view
	 *            the current view for the game.
	 * @param model
	 *            the model for the game.
	 */
	public TicTacToeController(AbstractBoard view, TicTacToeModel model) {
		this.view = view;
		this.model = model;

	}

	public Marker[] getBoardState() {
		return model.getBoardState();
	}

	public void endGame() {
		if (model.getWinner().equals(Marker.BLANK)) {
			view.endTie();
		} else
			view.endWin(model.getWinner());

	}

	public void startGame() {
		view.initialize();
		view.update(getBoardState());
		nextMove();

	}

	/**
	 * Called whenever a new move is ready to be processed.
	 * 
	 * @param arg
	 *            the move to be processed.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Move) {
			Move move = (Move) arg;
			if (validMove(move) && isCorrectTurn(move)) {
				model.updateBoard(move);
				view.showMove(move);
				view.update(getBoardState());
				if (model.gameOver())
					endGame();
				else
					nextMove();
			} else {
				// If invalid move, prompts the view and allows current player
				// to go again.
				view.badMove();
				currentPlayer.makeMove();
			}
		}

	}

	/**
	 * Adds a new player to the game. Order that players are added dictate their
	 * turn order (1st player added goes first). Also starts observing the
	 * player.
	 * 
	 * @param player
	 *            is a TicTacToePlayer that should be playing.
	 */
	public void addPlayer(AbstractTicTacToePlayer player) {
		player.addObserver(this);
		players.offer(player);

	}

	/**
	 * Checks if it is the turn of the current move.
	 * 
	 * @param move
	 *            is the current Move being processed.
	 * @return true iff the Move's marker and the current player's marker are
	 *         equivalent.
	 */
	private boolean isCorrectTurn(Move move) {
		return move.getMarker().equals(currentPlayer.getMarker());
	}

	private boolean validMove(Move move) {
		return model.validMove(move);

	}

	/**
	 * Tells the next player it is their turn to move.
	 */
	private void nextMove() {
		currentPlayer = nextPlayer();
		currentPlayer.makeMove();
	}

	/**
	 * Pops the next player off the player's queue and then adds them to end.
	 * 
	 * @return the next player.
	 */
	private AbstractTicTacToePlayer nextPlayer() {
		AbstractTicTacToePlayer nextPlayer = players.remove();
		players.offer(nextPlayer);
		return nextPlayer;
	}

}
