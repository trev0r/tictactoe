package tictactoe;
/**
 * A dumb AI that just finds an open spot and makes its move there.
 * 
 * @author Trevor Reid
 * 
 */
class DumbPlayer extends AbstractTicTacToePlayer {

	private TicTacToeController controller;

	public DumbPlayer(Marker mark, TicTacToeController controller) {
		super(mark);
		this.controller = controller;
	}

	public void makeMove() {
		// Just find the first open spot and go there.
		Marker[] currentState = controller.getBoardState();
		for (int i = 0; i < currentState.length; i++) {
			if (currentState[i] == Marker.BLANK) {
				setChanged();
				notifyObservers(new Move(i + 1, this.getMarker()));
				break;
			}

		}
	}

}