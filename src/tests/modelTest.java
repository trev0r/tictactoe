package tests;
import tictactoe.Marker;
import tictactoe.Move;
import tictactoe.TicTacToeModel;
import junit.framework.TestCase;


public class modelTest extends TestCase {
	private static final int size = 3;
	private TicTacToeModel model;
	private Marker[] boardState;


	@Override
	protected void setUp() {
	
		model = new TicTacToeModel(size);
		boardState = new Marker[size*size];
		for(int i = -0 ; i < boardState.length; i++){
			boardState[i] = Marker.BLANK;
		}
		

	}
	
	public void testInitialBoard(){
		Marker[] currentBoardState = model.getBoardState();
		for(int i = 0; i < boardState.length; i++){
			assertEquals(boardState[i], currentBoardState[i]);
			
		}
	}
	public void testBoardUpdate(){
		model.updateBoard(new Move(1, Marker.X));
	Marker[] currentBoardState = model.getBoardState();
		boardState[0] = Marker.X;
		
		for(int i = 0; i < boardState.length; i++){
			assertEquals(boardState[i], currentBoardState[i]);
			
		}

	}
	public void testValidMove(){
		Move zeroMove = new Move(0, Marker.X);
		assertFalse(model.validMove(zeroMove));
		for(int i = 1 ; i <= size*size; i++){
			assertTrue(model.validMove(new Move(i, Marker.X)));
			assertTrue(model.validMove(new Move(i, Marker.O)));
		}
		Move largeMove = new Move(size*size+1, Marker.X);
		assertFalse(model.validMove(largeMove));
		
	}
	public void testInitialNoWin(){
		assertEquals(model.getWinner(), Marker.BLANK);
		
	}
	public void testColumnWin(){
		for(int i = 1 ; i <= size; i++){
			model.updateBoard(new Move(i*3,Marker.O));
		}
		assertTrue(model.gameOver());
		assertEquals(model.getWinner(), Marker.O);
		
	}
	public void testRowWin(){
		for(int i = 1 ; i <= size; i++){
			model.updateBoard(new Move(i+3,Marker.X));
		}
		assertTrue(model.gameOver());
		assertEquals(model.getWinner(), Marker.X);
	}
	public void testDiagonalWin(){
		for(int i = 0 ; i < size; i++){
			model.updateBoard(new Move((i*4)+1,Marker.X));
		}
		assertTrue(model.gameOver());
		assertEquals(model.getWinner(), Marker.X);
		
	}
	public void testCatsGame(){
			model.updateBoard(new Move((1),Marker.X));
			model.updateBoard(new Move((2),Marker.O));
			model.updateBoard(new Move((3),Marker.X));
			
			model.updateBoard(new Move((4),Marker.X));
			model.updateBoard(new Move((5),Marker.O));
			model.updateBoard(new Move((6),Marker.X));
			
			
			model.updateBoard(new Move((7),Marker.O));
			model.updateBoard(new Move((8),Marker.X));
			model.updateBoard(new Move((9),Marker.O));
			
			assertTrue(model.gameOver());
			assertEquals(model.getWinner(), Marker.BLANK);
	}


}
