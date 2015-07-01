package DataObjects;

import java.util.ArrayList;
import java.util.List;

public class Box {

	private String mName;
	private List<List<Pokemon>> mPokemonGrid;
	
	public Box() {
		/**
		 * Constructor for pokemon box, creates the pokemon grid.
		 */
		this.mPokemonGrid = new ArrayList<List<Pokemon>>(5);
		//Loop through rows, making each
		for(int row = 0; row < 5; row++) {
			//Create row
			List<Pokemon> gridRow = new ArrayList<Pokemon>(6);
			for(int col = 0; col < 6; col++) {
				gridRow.add(null);
			}
			this.mPokemonGrid.add(gridRow);
		}
	}
	
	public Pokemon get(int row, int col) {
		/**
		 * Returns the pokemon at the specified position in this box.
		 */
		Pokemon outputPokemon = this.mPokemonGrid.get(row).get(col);
		return outputPokemon;
	}
	
	public void set(int row, int col, Pokemon newPokemon) {
		/**
		 * Sets a pokemon to a specified position in the box.
		 */
		this.mPokemonGrid.get(row).set(col, newPokemon);
	}
	
	public String getName() {
		/**
		 * Returns the name of the box.
		 */
		return this.mName;
	}
	
	public void setName(String name) {
		/**
		 * Sets the name of the box.
		 */
		this.mName = name;
	}
}
