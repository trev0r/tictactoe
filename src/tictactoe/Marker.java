package tictactoe;
public enum Marker {
	X("X"), O("O"), BLANK(" ");
	private final String symbol;

	Marker(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return symbol;
	}
}
