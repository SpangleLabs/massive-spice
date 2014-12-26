package DataObjects;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Web.Web;

public class PokemonSpecies {
	private String SpeciesName = null;
	private Integer DexNational = null;
	private String Type1 = null;
	private String Type2 = null;
	private Integer EggCycles = null;
	
	public void loadFromBulbapedia(String species) {
		/* *
		 * Loads a pokemon's species information from bulbapedia.
		 */
		String url = "http://bulbapedia.bulbagarden.net/w/index.php?title="+species+"_%28Pok%C3%A9mon%29&action=edit";
		String code = Web.get_page(url);
		Pattern CodePattern = Pattern.compile("\\{\\{Pok[^m]{1,2}mon Infobox([\\w\\W]*)}}[^|}]{0,3}");
		Matcher CodeMatcher = CodePattern.matcher(code);
		CodeMatcher.find();
		code = CodeMatcher.group(1);
		Pattern SpeciesNamePattern = Pattern.compile("\\Wname=([^|]*)");
		Matcher SpeciesNameMatcher = SpeciesNamePattern.matcher(code);
		SpeciesNameMatcher.find();
		this.SpeciesName = SpeciesNameMatcher.group(1).trim();
		Pattern DexNationalPattern = Pattern.compile("\\Wndex=([^|]*)");
		Matcher DexNationalMatcher = DexNationalPattern.matcher(code);
		DexNationalMatcher.find();
		this.DexNational = Integer.parseInt(DexNationalMatcher.group(1).trim());
		Pattern Type1Pattern = Pattern.compile("\\Wtype1=([^|]*)");
		Matcher Type1Matcher = Type1Pattern.matcher(code);
		Type1Matcher.find();
		this.Type1 = Type1Matcher.group(1).trim();
		Pattern Type2Pattern = Pattern.compile("\\Wtype2=([^|]*)");
		Matcher Type2Matcher = Type2Pattern.matcher(code);
		if(Type2Matcher.find()) {
			this.Type2 = Type2Matcher.group(1).trim();
		}
		Pattern EggCyclesPattern = Pattern.compile("\\Weggcycles=([^|]*)");
		Matcher EggCyclesMatcher = EggCyclesPattern.matcher(code);
		EggCyclesMatcher.find();
		this.EggCycles = Integer.parseInt(EggCyclesMatcher.group(1).trim());
	}
	
	public static PokemonSpecies loadFromXML(Integer dexNumber) {
		PokemonSpecies PokemonObject = new PokemonSpecies();
		try {
			String filename = "config/pokemon/"+String.format("%04d",dexNumber)+".xml";
			File file = new File(filename);
			DocumentBuilder DocBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = DocBuilder.parse(file);
			Element rootElement = doc.getDocumentElement();
			NodeList pokemonList = rootElement.getElementsByTagName("Pokemon");
			Integer numPokemon = pokemonList.getLength();
			for(Integer i = 0 ; i < numPokemon ; i++) {
				Element pokemonElement = (Element) pokemonList.item(i);
				Integer thisDex = Integer.parseInt(pokemonElement.getElementsByTagName("DexNational").item(0).getTextContent());
				if(thisDex==dexNumber) {
					PokemonObject.setSpeciesName(pokemonElement.getElementsByTagName("SpeciesName").item(0).getTextContent());
					PokemonObject.setDexNational(Integer.parseInt(pokemonElement.getElementsByTagName("DexNational").item(0).getTextContent()));
					PokemonObject.setType1(pokemonElement.getElementsByTagName("Type1").item(0).getTextContent());
					PokemonObject.setType2(pokemonElement.getElementsByTagName("Type2").item(0).getTextContent());
					PokemonObject.setEggCycles(Integer.parseInt(pokemonElement.getElementsByTagName("EggCycles").item(0).getTextContent()));					
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return PokemonObject;
	}
	
	public String getSpeciesName() {
		/* *
		 * Returns the species name.
		 */
		return SpeciesName;
	}
	public void setSpeciesName(String speciesName) {
		/* *
		 * Sets species name
		 */
		SpeciesName = speciesName;
	}
	public Integer getDexNational() {
		/* *
		 * Returns national pokedex number
		 */
		return DexNational;
	}
	public void setDexNational(Integer dexNational) {
		/* *
		 * Sets the national pokedex number.
		 */
		DexNational = dexNational;
	}
	public String getType1() {
		/* *
		 * Returns the pokemon's primary type.
		 */
		return Type1;
	}
	public void setType1(String type1) {
		/* *
		 * Sets the pokemon's primary type.
		 */
		Type1 = type1;
	}
	public String getType2() {
		/* *
		 * Returns the pokemon's secondary type or null.
		 */
		return Type2;
	}
	public void setType2(String type2) {
		/* *
		 * Sets the pokemon's secondary type.
		 */
		Type2 = type2;
	}
	public Integer getEggCycles() {
		/* *
		 * Returns the number of egg cycles it takes to hatch an egg.
		 * One cycle is 255 steps.
		 */
		return EggCycles;
	}
	public void setEggCycles(Integer eggCycles) {
		/* *
		 * Sets the number of egg cycles required to hatch an egg.
		 */
		EggCycles = eggCycles;
	}
}
