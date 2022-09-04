package hexAPawn;

// useful imports to read input from user
import java.util.Scanner;

public class HumanPlayer implements Player {

	// instance variables
	public char color;

	public HumanPlayer(char color) {
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

        // if the state is a winning state for the opponent or there aren't any moves
        if (node.board.win(HexBoard.opponent(color)) || node.board.moves(color).size() <= 1) {
        	// return the opponent
        	return opponent;
        } else { // else
        	 // Print all available moves in the form of "1: Move from [1,1] to [2,1]." etc.
        	for (int i = 1; i < node.board.moves(color).size(); i++) {
        		System.out.println(i + ": "+ (node.children.get(i).m.toString()));
        	}
        	// Ask the user for which move they have chosen
        	Scanner userInput = new Scanner(System.in);
        	System.out.println("Please choose a move");
        	
        	int userMove = Integer.valueOf(userInput.nextLine());  
        	
        	// Find the GameTree child that corresponds to the chosen move (be careful if you have printed the moves starting at 1) 
        	// return opponent.play on the chosen child with "this" player as the opponent
        	return opponent.play(node.children.get(userMove), this);

        }
	}

	public String toString() {
		return color == HexBoard.BLACK ? "black" : "white";
	}

	public static void main(String s[]) {
		HumanPlayer h1 = new HumanPlayer(HexBoard.WHITE);
		HumanPlayer h2 = new HumanPlayer(HexBoard.BLACK);
		System.out.println(
			h1.play(new GameTree(new HexBoard(), HexBoard.WHITE), h2)+" wins");
	}
}
