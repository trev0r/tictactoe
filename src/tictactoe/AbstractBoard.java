package tictactoe;
import java.util.Observable;

/**
 * An abstract board class for other views to inherit from. This extends
 * observable in order to notify of any user input to the board. As such,
 * "setChange" and "notifyObservers" should be called when this input has been
 * received so that it can be processed.
 * 
 * @author Trevor Reid
 * 
 */
public abstract class AbstractBoard extends Observable {
	/**
	 * Displays the current state of the board.
	 * 
	 * @param boardState
	 *            is the current state of the board.
	 */
	public abstract void update(Marker[] boardState);

	/**
	 * Sets up the board for playing.
	 */
	public abstract void initialize();

	/**
	 * Ends the game in a win. Declares Winner.
	 * @param marker is the Marker of the winner.
	 */
	public abstract void endWin(Marker marker);
	/**
	 * Ends the game in a tie
	 */
	public abstract void endTie();
		
	

	/**
	 * Displays a prompt to the user that it is their turn to move.
	 * @param m is the marker of the user.
	 */
	public abstract void promptForMove(Marker m);

	/**
	 * Displays a prompt to the user that they attempted to make an invalid
	 * move.
	 */
	public abstract void badMove();

	/**
	 * Shows a move that was made.
	 * @param move is the move that occurred.
	 */
	public abstract void showMove(Move move);


}
