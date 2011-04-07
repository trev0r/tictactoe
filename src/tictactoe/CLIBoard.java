package tictactoe;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;




/**
 * A CLI interface for Tic-Tac-Toe
 * @author Trevor Reid
 *
 */
public class CLIBoard extends AbstractBoard{
	public int boardSize;
	public String[] movementKey;
		private static final String SEPERATOR = Messages.getString("CLIBoard.seperator"); //$NON-NLS-1$
		private static final String SPACE = Messages.getString("CLIBoard.space"); //$NON-NLS-1$
		private static final String HEADER = Messages.getString("CLIBoard.header"); //$NON-NLS-1$
		private static final String WELCOME_MESSAGE = Messages.getString("CLIBoard.welcomeMessage"); //$NON-NLS-1$
		private static final String MOVE_PROMPT = Messages.getString("CLIBoard.movePrompt"); //$NON-NLS-1$
		private static final String CLOSING_MESSAGE = Messages.getString("CLIBoard.closingMessage"); //$NON-NLS-1$
		private static final String INVALID_MOVE = Messages.getString("CLIBoard.invalidMove"); //$NON-NLS-1$
		private static final String ERROR = Messages.getString("CLIBoard.error"); //$NON-NLS-1$
		private static final String MOVES_TO_POSITION = Messages.getString("CLIBoard.movesToPosition"); //$NON-NLS-1$
		private static final String WINS = Messages.getString("CLIBoard.wins"); //$NON-NLS-1$
		private static final String CATS_GAME = Messages.getString("CLIBoard.catsGame"); //$NON-NLS-1$
		
		/**
		 * Sets the board size and creates the movement key.
		 * @param boardSize is the size of the game board.
		 */
		public CLIBoard(int boardSize) {
			this.boardSize = boardSize;
			this.movementKey = new String[boardSize];
			
			for(int i = 0; i < this.boardSize; i++){
				StringBuilder sb = new StringBuilder();
				for(int k = 0; k < this.boardSize; k++){
					sb.append(this.boardSize*i+k+1);
					if (k < boardSize-1){ sb.append(SEPERATOR); }								
				}
				this.movementKey[i] = sb.toString();
			}
		}

		@Override
		public void update(Marker[] boardState) {
			StringBuilder sb = new StringBuilder();
			sb.append(HEADER);
			sb.append('\n');
			for(int i = 0; i < boardSize; i++){
				for(int k = 0; k < boardSize; k++){
					String val = tokenize(boardState[boardSize*i+k]);
					sb.append(val);
					if (k < boardSize-1){sb.append(SEPERATOR);}
				}
				sb.append(SPACE);
				sb.append(movementKey[i]);
				sb.append('\n');
			}
			System.out.println(sb.toString());
			
		}
		
		

		private String tokenize(Marker m) {
			return m.toString();
		}

		@Override
		public void initialize() {
			System.out.println(WELCOME_MESSAGE);
			
		}

	

		@Override
		public void endWin(Marker m) {
			System.out.println(tokenize(m)+WINS);
			System.out.println(CLOSING_MESSAGE);
			
		}

		@Override
		public void promptForMove(Marker m) {
			//Waits for user input and notifies observers when it has been received. 
			System.out.print(MOVE_PROMPT);
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			try {
				int position = Integer.parseInt(in.readLine());
				setChanged();
				notifyObservers(new Move(position, m));
			} catch (IOException e) {
				//Hopefully will never happen.
				quit();
			}
			catch (NumberFormatException e){
				setChanged();
				notifyObservers(null);
				
			}
			
			
		}

		private void quit() {
			System.out.println(ERROR);
			System.exit(1);
			
		}

		@Override
		public void badMove() {
			System.out.println(INVALID_MOVE);
			
		}

		@Override
		public void showMove(Move move) {
			StringBuilder sb = new StringBuilder();
			sb.append(tokenize(move.getMarker()));
			sb.append(MOVES_TO_POSITION);
			sb.append(move.getPosition());
			System.out.println(sb);
			
			// TODO Auto-generated method stub
			
		}

		@Override
		public void endTie() {
			System.out.println(CATS_GAME);
			System.out.println(CLOSING_MESSAGE);
			
		}
		
	}
