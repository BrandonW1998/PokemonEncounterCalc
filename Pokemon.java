package platinum;
/*
 * Pokemon Object Class
 */

import java.util.ArrayList;

public class Pokemon {
	
	// Pokemon Variables
	String name;
	ArrayList<String> levels;
	ArrayList<String> moves;
	
	Pokemon(String name, ArrayList<String> levels, ArrayList<String> moves) {
		this.setName(name);
		this.setLevels(levels);
		this.setMoves(moves);
	}
	
	// Getter Methods
	public String getName() { return name; }
	public ArrayList<String> getLevels() { return levels; }
	public ArrayList<String> getMoves() { return moves; }

	
	// Setter Methods
	public void setName(String name) { this.name = name; }
	public void setLevels(ArrayList<String> levels) { this.levels = levels; }
	public void setMoves(ArrayList<String> moves) { this.moves = moves; }
	
}
