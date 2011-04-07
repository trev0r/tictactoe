package tictactoe;
import java.util.ArrayList;
import java.util.List;

public class TicTacToeModel {
	private int size;
	private Marker[] boardState;
	private Marker winnerMark = Marker.BLANK;

	public TicTacToeModel(int size) {
		this.size = size;
		boardState = new Marker[size * size];
		for (int i = 0; i < boardState.length; i++) {
			boardState[i] = Marker.BLANK;
		}

	}

	public Marker[] getBoardState() {
		return boardState.clone();

	}

	public void updateBoard(Move move) {
		boardState[move.getPosition() - 1] = move.getMarker();

	}

	public boolean gameOver() {
		// Checks rows,cols, and diagonals. Assumes that you need a full
		// row/column/diagonal to win.
		for (int i = 0; i < size; i++) {
			if (rowEqual(i))
				return true;
			if (colEqual(i))
				return true;
		}
		if (diagonalsEqual())
			return true;
		if (allOccupied())
			return true;

		return false;

	}

	private boolean allOccupied() {
		for(Marker m : boardState){
			if ( m.equals(Marker.BLANK))
				return false;
		}
		return true;
	}

	private boolean rowEqual(int rowIndex) {
		List<Marker> marks = new ArrayList<Marker>();
		for (int i = 0; i < size; i++) {
			marks.add(boardState[rowIndex * size + i]);
		}
		return allEqual(marks);
	}

	private boolean colEqual(int colIndex) {
		List<Marker> marks = new ArrayList<Marker>();
		for (int i = 0; i < size; i++) {
			marks.add(boardState[colIndex + i * size]);
		}
		return allEqual(marks);
	}

	private boolean diagonalsEqual() {
		List<Marker> diagOne = new ArrayList<Marker>();
		List<Marker> diagTwo = new ArrayList<Marker>();
		for (int i = 0; i < size; i++) {
			diagOne.add(boardState[i*(size+1)]);
			diagTwo.add(boardState[(i+1)*(size-1)]);
		}
		return (allEqual( diagOne)|| allEqual(diagTwo));
	}

	private boolean allEqual(List<Marker> marks) {
		Marker first = marks.get(0);
		if (first.equals(Marker.BLANK))
			return false;
		for (Marker m : marks) {
			if (!m.equals(first)) {
				return false;
			}
		}
		winnerMark = first;
		return true;
	}
	
	public Marker getWinner(){
		return winnerMark;
	}

	public boolean validMove(Move move) {
		int position = move.getPosition() - 1;
		if ((position >= 0) && (position < size * size)) {
			return boardState[position] == Marker.BLANK;
		}
		return false;
	}

}
