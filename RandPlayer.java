package hexAPawn;

// Any useful imports to pick random choice
import java.util.Random;

public class RandPlayer implements Player {

	// Any instance variables
	public char color;

	public RandPlayer(char color) {
		this.color = color;
	}

	/**
	 * makes move until there is a winner
	 * 
	 * @param node GameTree corresponding to some configuration of the board
	 * @param opponent player
	 * @return player who won the game
	 */
	public Player play(GameTree node, Player opponent) {

        // Print current board
		System.out.println(node.toString());

        // if the state is a winning state for the opponent or there aren't any moves:
		if (node.board.win(HexBoard.opponent(color)) || node.board.moves(color).size() <= 1) {
			 //return the opponent
			 return opponent;
		 } else { // else:    
			 // Pick a random GameTree child
			 Random rand = new Random();
			 int int_random = rand.nextInt(node.numChildren());
			 // return opponent.play on the chosen random child with "this" player as the opponent
			 return opponent.play(node.children.get(int_random), this);
		 }
	}

	public String toString() {
		return color == HexBoard.BLACK ? "black" : "white";
	}

	public static void main(String s[]) {
		RandPlayer h1 = new RandPlayer(HexBoard.WHITE);
		HumanPlayer h2 = new HumanPlayer(HexBoard.BLACK);
		System.out.println(
			h1.play(new GameTree(new HexBoard(), HexBoard.WHITE), h2)+" wins");
	}
}
