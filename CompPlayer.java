package hexAPawn;

import java.util.Random;
// Any useful imports to pick random choice
import java.util.Scanner;

public class CompPlayer implements Player {

	// Any instance variables
	public char color;

	public CompPlayer(char color) {
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
			// trim tree rooted in node
				// go to node corresponding to parent
        	
        	// if parent is not null
        	if (node.parent != null) {
        		// find grandparent 
        		GameTree grandparent = node.parent.parent;
        		// remove grandparent's child which corresponds to parent
        		grandparent.children.remove(node.parent);
				// print what you removed
        		System.out.println(node.parent);
        	} else { // else
        		// print that the parent is null and that was nothing there for you to remove
        		System.out.println("Parent is null. Nothing to remove.");
        		
        	}
            // return the opponent
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
		Scanner r = new Scanner(System.in);
		GameTree gt = new GameTree(new HexBoard(), HexBoard.WHITE);
		String ans = "yes";
		while (ans.equals( "yes")) {
			Player h1 = new CompPlayer(HexBoard.BLACK);
			Player h2 = new HumanPlayer(HexBoard.WHITE);
			System.out.println(h2 + " plays first.");
			System.out.println(h2.play(gt, h1) + " wins!");
			System.out.println("The tree now has size " + gt.size());
			System.out.println("Play again?  answer yes or no");
			ans = r.next().toLowerCase();
			System.out.println("Your answer was " + ans);
		}
		System.out.println("Thanks for playing!");
		r.close();
	}
}
