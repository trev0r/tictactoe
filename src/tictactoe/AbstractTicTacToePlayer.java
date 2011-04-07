package tictactoe;
import java.util.Observable;

/**
 * Abstract class for players in Tic-Tac-Toe. Can be human, some type
 * of AI, some networked player, etc. 
 * 
 * @author Trevor Reid
 * 
 * 
 */
public abstract class AbstractTicTacToePlayer extends Observable {
	private Marker mark;

	/**
	 * 
	 * @param mark
	 *            Marker (typically "X" or "O") to represent this player's side.
	 */
	public AbstractTicTacToePlayer(Marker mark) {
		this.mark = mark;
	}

	/**
	 * Called by GameController when it is this TicTacToePlayer's turn to go.
	 * GameController should observe all players so call "notifyObservers()"
	 * when done calculating move.
	 */
	public abstract void makeMove();

	public Marker getMarker() {
		return mark;
	}

}
