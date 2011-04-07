package tictactoe;
/**
 * 
 * @author Trevor Reid
 *
 *Represents a single move in tic-tac-toe.
 */
public class Move {
	private int position;
	private Marker mark;
	/**
	 * 
	 * @param position position of movement. Should be in range (1, # of Movements). No validation is done.
	 * @param mark the symbol (typically "X" or "O") representing this move. 
	 */
	public Move(int position, Marker mark){
		this.position = position;
		this.mark = mark;
	}
	public int getPosition(){
		return position;
	}
	public Marker getMarker(){
		return mark;
	}
}
