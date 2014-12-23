package DataObjects;

public class Pokemon extends PokemonSpecies {
	private Integer CoordBox = null;
	private Integer CoordRow = null;
	private Integer CoordCol = null;
	private Integer IVHP = null;
	private Integer IVAtk = null;
	private Integer IVDef = null;
	private Integer IVSpA = null;
	private Integer IVSpD = null;
	private Integer IVSpe = null;
	private String Move1 = null;
	private String Move2 = null;
	private String Move3 = null;
	private String Move4 = null;
	private String Pokeball = null;
	private String ESV = null;
	private String Ability = null;
	private String Gender = null;
	private Boolean Egg = null;
	
	public String getSpecies() {
		/* *
		 * Returns the species name
		 */
		return Species;
	}
	public void setSpecies(String species) {
		/* *
		 * Sets the pokemon's species
		 */
		Species = species;
	}
	public Integer getCoordBox() {
		/* *
		 * Returns the box the pokemon is in.
		 */
		return CoordBox;
	}
	public void setCoordBox(Integer coordBox) {
		/* *
		 * Sets the box the pokemon is in
		 */
		CoordBox = coordBox;
	}
	public Integer getCoordRow() {
		/* *
		 * Returns the row the pokemon is in
		 */
		return CoordRow;
	}
	public void setCoordRow(Integer coordRow) {
		/* *
		 * Sets the row the pokemon is on. Can be 0-4.
		 */
		if(coordRow<=4 && coordRow>=0) {
			CoordRow = coordRow;
		}
	}
	public Integer getCoordCol() {
		/* *
		 * Returns the column the pokemon is in.
		 */
		return CoordCol;
	}
	public void setCoordCol(Integer coordCol) {
		/* *
		 * Sets the column the pokemon is in.
		 */
		if(coordCol<=5 && coordCol>=0) {
			CoordCol = coordCol;
		}
	}
	public Integer getIVHP() {
		/* *
		 * Returns the HP IV of the pokemon
		 */
		return IVHP;
	}
	public void setIVHP(Integer iVHP) {
		/* *
		 * Sets the HP IV of the pokemon.
		 */
		if(iVHP>=0 && iVHP<=31) {
			IVHP = iVHP;
		}
	}
	public Integer getIVAtk() {
		/* *
		 * Returns the attack IV of the pokemon.
		 */
		return IVAtk;
	}
	public void setIVAtk(Integer iVAtk) {
		/* *
		 * Sets the attack IV of the pokemon.
		 */
		if(iVAtk>=0 && iVAtk<=31) {
			IVAtk = iVAtk;
		}
	}
	public Integer getIVDef() {
		/* *
		 * Returns the Defence IV of the pokemon.
		 */
		return IVDef;
	}
	public void setIVDef(Integer iVDef) {
		/* *
		 * Sets the defence IV of the pokemon.
		 */
		if(iVDef>=0 && iVDef<=31) {
			IVDef = iVDef;
		}
	}
	public Integer getIVSpA() {
		/* *
		 * Returns the special attack IV of the pokemon.
		 */
		return IVSpA;
	}
	public void setIVSpA(Integer iVSpA) {
		/* *
		 * Sets the special attack IV of the pokemon.
		 */
		if(iVSpA>=0 && iVSpA<=31) {
			IVSpA = iVSpA;
		}
	}
	public Integer getIVSpD() {
		/* *
		 * Returns the special defence of the pokemon.
		 */
		return IVSpD;
	}
	public void setIVSpD(Integer iVSpD) {
		/* *
		 * Sets the special defence of the pokemon.
		 */
		if(iVSpD>=0 && iVSpD<=31) {
			IVSpD = iVSpD;
		}
	}
	public Integer getIVSpe() {
		/* *
		 * Returns the speed IV of the pokemon.
		 */
		return IVSpe;
	}
	public void setIVSpe(Integer iVSpe) {
		/* *
		 * Sets the speed IV of the pokemon.
		 */
		if(iVSpe>=0 && iVSpe<=31) {
			IVSpe = iVSpe;
		}
	}
	public String getIVString() {
		/* *
		 * Returns the IV string.
		 */
		String IVString = IVHP.toString()+".";
		IVString += IVAtk.toString()+".";
		IVString += IVDef.toString()+".";
		IVString += IVSpA.toString()+".";
		IVString += IVSpD.toString()+".";
		IVString += IVSpe.toString();
		return IVString;
	}
	public String getIVStringBold() {
		/* *
		 * Returns the IV string, with 31s in bold.
		 */
		String IVString = this.getIVString();
		IVString = IVString.replace("31", "**31**");
		IVString = IVString.replace("**.**",".");
		return IVString;
	}
	public void setIVString(String IVString) {
		/* *
		 * Sets all the IVs at once, using standard format IV strings.
		 */
		if(IVString.length()!=17) {
			return;
		}
		this.IVHP = Integer.parseInt(IVString.substring(0,2));
		this.IVAtk = Integer.parseInt(IVString.substring(3,5));
		this.IVDef = Integer.parseInt(IVString.substring(6,8));
		this.IVSpA = Integer.parseInt(IVString.substring(9,11));
		this.IVSpD = Integer.parseInt(IVString.substring(12,13));
		this.IVSpe = Integer.parseInt(IVString.substring(15,17));
	}
	public String getHiddenPowerType() {
		/* *
		 * Returns the type of the pokemon's hidden power.
		 * It is calculated from the pokemon's IVs
		 */
		Integer TypeValue = IVHP%2+(2*(IVAtk%2))+(4*(IVDef%2))+(8*(IVSpe%2))+(16*(IVSpA%2))+(32*(IVSpD));
		TypeValue = (TypeValue*15)/63;
		switch (TypeValue) {
			case 0: return "Fighting";
			case 1: return "Flying"; 
			case 2: return "Posion";
			case 3: return "Ground";
			case 4: return "Rock";
			case 5: return "Bug";
			case 6: return "Ghost";
			case 7: return "Steel";
			case 8: return "Fire";
			case 9: return "Water";
			case 10: return "Grass";
			case 11: return "Electric";
			case 12: return "Psychic";
			case 13: return "Ice";
			case 14: return "Dragon";
			case 15: return "Dark";
			default: return null;
		}
	}
	public String getMove1() {
		/* *
		 * Returns the first move.
		 */
		return Move1;
	}
	public void setMove1(String move1) {
		/* *
		 * Sets the first move
		 */
		Move1 = move1;
	}
	public String getMove2() {
		/* *
		 * Returns the second move.
		 */
		return Move2;
	}
	public void setMove2(String move2) {
		/* *
		 * Sets the second move
		 */
		Move2 = move2;
	}
	public String getMove3() {
		/* *
		 * Returns the third move.
		 */
		return Move3;
	}
	public void setMove3(String move3) {
		/* *
		 * Sets the third move
		 */
		Move3 = move3;
	}
	public String getMove4() {
		/* *
		 * Returns the forth move.
		 */
		return Move4;
	}
	public void setMove4(String move4) {
		/* *
		 * Sets the forth move
		 */
		Move4 = move4;
	}
	public String getPokeball() {
		/* *
		 * Returns the type of pokeball the pokemon is in.
		 */
		return Pokeball;
	}
	public void setPokeball(String pokeball) {
		/* *
		 * Sets the type of pokeball the pokemon is in.
		 */
		Pokeball = pokeball;
	}
	public String getESV() {
		/* *
		 * Returns the Egg Shiny Value.
		 */
		return ESV;
	}
	public void setESV(String eSV) {
		/* *
		 * Sets the Egg Shiny Value. Pads to 4 chars if shorter.
		 */
		if(eSV.length()<4) {
			//TODO: padding
			ESV = eSV;
		} else if(eSV.length()==4) {
			ESV = eSV;
		}
	}
	public String getAbility() {
		/* *
		 * Returns the pokemon's ability.
		 */
		return Ability;
	}
	public void setAbility(String ability) {
		/* *
		 * Sets the pokemon's ability
		 */
		Ability = ability;
	}
	public String getGender() {
		/* *
		 * Returns the pokemon's gender
		 */
		return Gender;
	}
	public void setGender(String gender) {
		/* *
		 * Sets the pokemon's gender. Either M, F, or -
		 */
		String genderUpper = gender.toUpperCase();
		if(genderUpper=="M" || genderUpper=="F" || genderUpper=="-") {
			Gender = genderUpper;
		}
	}
	public Boolean getEgg() {
		/* *
		 * Returns a boolean representing whether this pokemon is an egg (true) or hatched (false).
		 */
		return Egg;
	}
	public void setEgg(Boolean egg) {
		/* *
		 * Sets a boolean representing whether this pokemon is an egg (true) or hatched (false).
		 */
		Egg = egg;
	}
	
	
}
