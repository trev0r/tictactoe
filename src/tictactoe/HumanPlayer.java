package tictactoe;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents a Human player that gets it's moves via user input from the view.
 * 
 * @author Trevor Reid
 * 
 */
class HumanPlayer extends AbstractTicTacToePlayer implements Observer {
	private AbstractBoard view;

	/**
	 * 
	 * @param mark
	 *            is the Marker for the player
	 * @param view
	 *            is the view from which user input is coming.
	 */
	public HumanPlayer(Marker mark, AbstractBoard view) {
		super(mark);
		this.view = view;

		this.view.addObserver(this);

	}

	/**
	 * Asks the view for a move.
	 */
	@Override
	public void makeMove() {
		view.promptForMove(this.getMarker());

	}

	/**
	 * Called by view when a move has been made.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Move) {
			Move move = (Move) arg;
			if (this.getMarker().equals(move.getMarker())) {
				setChanged();
				notifyObservers(move);
			}
		} else {
			view.badMove();
			makeMove();
		}

	}

}
