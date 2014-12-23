package DataObjects;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


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
		code = code.split("{{Pok�mon Infobox")[1];
		code = code.split("}}")[0];
		Pattern SpeciesNamePattern = Pattern.compile("\\Wname=([^|]*)");
		Matcher SpeciesNameMatcher = SpeciesNamePattern.matcher(code);
		this.SpeciesName = SpeciesNameMatcher.group(1).trim();
		Pattern DexNationalPattern = Pattern.compile("\\Wndex=([^ |]*)");
		Matcher DexNationalMatcher = DexNationalPattern.matcher(code);
		this.DexNational = Integer.parseInt(DexNationalMatcher.group(1).trim());
		Pattern Type1Pattern = Pattern.compile("\\Wtype1=([^ |]*)");
		Matcher Type1Matcher = Type1Pattern.matcher(code);
		this.Type1 = Type1Matcher.group(1).trim();
		Pattern Type2Pattern = Pattern.compile("\\Wtype2=([^ |]*)");
		Matcher Type2Matcher = Type2Pattern.matcher(code);
		this.Type2 = Type2Matcher.group(1).trim();
		Pattern EggCyclesPattern = Pattern.compile("\\Weggcycles=([^ |]*)");
		Matcher EggCyclesMatcher = EggCyclesPattern.matcher(code);
		this.EggCycles = Integer.parseInt(EggCyclesMatcher.group(1).trim());
	}
	
	public void saveToXML() {
		/* *
		 * Saves pokemon species information to XML.
		 */
		try {
			DocumentBuilderFactory DocFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder DocBuilder = DocFactory.newDocumentBuilder();
			//build root element
			Document Doc = DocBuilder.newDocument();
			Element RootElement = Doc.createElement("Pokemon");
			Doc.appendChild(RootElement);
			//add Name element
			Element NameElement = Doc.createElement("SpeciesName");
			NameElement.appendChild(Doc.createTextNode(this.SpeciesName));
			RootElement.appendChild(NameElement);
			//add national dex number
			Element DexElement = Doc.createElement("DexNational");
			DexElement.appendChild(Doc.createTextNode(this.DexNational.toString()));
			RootElement.appendChild(DexElement);
			//add type1
			Element Type1Element = Doc.createElement("Type1");
			Type1Element.appendChild(Doc.createTextNode(this.Type1));
			RootElement.appendChild(Type1Element);
			//add type2?
			Element Type2Element = Doc.createElement("Type2");
			Type2Element.appendChild(Doc.createTextNode(this.Type2));
			RootElement.appendChild(Type2Element);
			//add egg cycles
			Element EggElement = Doc.createElement("EggCycles");
			EggElement.appendChild(Doc.createTextNode(this.EggCycles.toString()));
			RootElement.appendChild(EggElement);
			//save the xml to file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(Doc);
			File dir = new File("config/pokemon");
			dir.mkdirs();
			String filename = "config/pokemon/"+String.format("%04d",this.DexNational)+".xml";
			StreamResult result = new StreamResult(new File(filename));
			transformer.transform(source, result);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
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