package platinum;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * This project will take input from the user on a Pokemon and it's level,
 * returning the moveset of that Pokemon in a wild encounter.
 * 
 * Information on Pokemon and movesets hard-coded in as "platinumDexMovesets.txt".
 */
public class PokemonEncounterCalc {

	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		ArrayList<Pokemon> pokeList = new ArrayList<Pokemon>();
		ArrayList<String> strList = new ArrayList<String>();
		ArrayList<String> lvlList;
		ArrayList<String> moveList;
		String name = null;
		String fileName = "./src/platinum/platinumDexMovesets.txt";
		Scanner scnr;
		
		int dexNum = -1;
		String pokeName = null;
		int level = 0;
		String move1 = null;
		String move2 = null;
		String move3 = null;
		String move4 = null;
		
		// Introduction dialogue
		System.out.println("Welcome to the Pokemon Platinum Moveset Calculator!");
		Thread.sleep(400);
		System.out.println("Given a Pokemon and it's level,");
		Thread.sleep(400);
		System.out.println("We will return the exact moveset of it in a wild encounter.\n");
		Thread.sleep(400);
		
		// Open file for scanner
		File file = new File(fileName);
		scnr = new Scanner(file);
		
		// Scan in Pokemon/Movesets from file
		while(scnr.hasNextLine()) {
			strList.add(scnr.nextLine());
		}
		// Parse Pokemon Name, Level/Move
		for(int i = 0; i < strList.size(); i++) {
			name = strList.get(i).split(",")[0];
			lvlList = new ArrayList<String>();
			moveList = new ArrayList<String>();
			for(int j = 0; j < strList.get(i).split(",").length / 2; j++) {
				lvlList.add(strList.get(i).split(",")[j * 2 + 1]);
				moveList.add(strList.get(i).split(",")[j * 2 + 2]);
			}
			Pokemon mon = new Pokemon(name, lvlList, moveList);
			pokeList.add(mon);
		}
		
		while(true) {
			// Prompt for Pokemon name and level
			while(pokeName == null) {
				scnr = new Scanner(System.in);
				System.out.println("Enter a Pokemon's name (\"Exit\" to quit): ");
				String tempName = scnr.nextLine();
				// Exit Case
				if(tempName.toLowerCase().equals("exit")) {
					scnr.close();
					System.exit(0);
				}
				// Verify pokemon name
				for(int i = 0; i < pokeList.size(); i++) {
					if(tempName.toLowerCase().equals(pokeList.get(i).getName().toLowerCase())) {
						pokeName = tempName;
						dexNum = i;
						break;
					}
					else
						continue;
				}
				if(pokeName == null)
					System.out.println("Name Error: Could not find \"" + tempName + "\"");
			}
			// Verify level (range 1-100)
			while(level == 0) {
				System.out.println("What is " + pokeName + "'s level? (1 - 100)");
				String tempStr = scnr.nextLine();
				int tempInt = 0;
				if(isNumeric(tempStr)) {
					tempInt = Integer.parseInt(tempStr);
					if(tempInt >= 1 && tempInt <= 100) {
						level = tempInt;
						break;
					}
					else {
						System.out.println("Out of Range Error: Level must be (1 - 100)");
						continue;
					}
				}
				else {
					System.out.println("Non-Numeral Error: Level must be number");
				}
			}
			// Search platinumDexMovesets.txt for pokemon and moveset
			int count = 0;
			for(int i = 0; i < pokeList.get(dexNum).levels.size(); i++) {
				if(!isNumeric(pokeList.get(dexNum).levels.get(i)))
					continue;
				if(level >= Integer.parseInt(pokeList.get(dexNum).levels.get(i))) {
					switch(count) {
						case 0:
							move1 = pokeList.get(dexNum).moves.get(i);
							break;
						case 1:
							move2 = pokeList.get(dexNum).moves.get(i);
							break;
						case 2:
							move3 = pokeList.get(dexNum).moves.get(i);
							break;
						case 3:
							move4 = pokeList.get(dexNum).moves.get(i);
							break;
					}
				}
				count = (count + 1) % 4;
			}
			
			System.out.println("Moveset for a level " + level + " " + pokeName + ":");
			System.out.println("Move 1: " + move1);
			System.out.println("Move 2: " + move2);
			System.out.println("Move 3: " + move3);
			System.out.println("Move 4: " + move4);
			
			pokeName = null;
			level = 0;
		}
	}
	
	// Check if given string is a number
	public static boolean isNumeric(String str) { 
		try {  
			Double.parseDouble(str);  
			return true;
		}
		catch(NumberFormatException e){  
			return false;  
		}  
	}

}
