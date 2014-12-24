package DataObjects;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
		Pattern CodePattern = Pattern.compile("\\{\\{Pok[^m]{1,2}mon Infobox([\\w\\W]*?)}}[^|]{0,3}\n");
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
			if(this.Type2 != null) {
				Element Type2Element = Doc.createElement("Type2");
				Type2Element.appendChild(Doc.createTextNode(this.Type2));
				RootElement.appendChild(Type2Element);
			}
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
	
	public static PokemonSpecies loadFromXML(Integer dexNumber) {
		PokemonSpecies PokemonObject = new PokemonSpecies();
		try {
			String filename = "config/pokemon/"+String.format("%04d",dexNumber)+".xml";
			File file = new File(filename);
			DocumentBuilder DocBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = DocBuilder.parse(file);
			Element rootElement = doc.getDocumentElement();
			PokemonObject.setSpeciesName(rootElement.getElementsByTagName("SpeciesName").item(0).getTextContent());
			PokemonObject.setDexNational(Integer.parseInt(rootElement.getElementsByTagName("DexNational").item(0).getTextContent()));
			PokemonObject.setType1(rootElement.getElementsByTagName("Type1").item(0).getTextContent());
			PokemonObject.setType2(rootElement.getElementsByTagName("Type2").item(0).getTextContent());
			PokemonObject.setEggCycles(Integer.parseInt(rootElement.getElementsByTagName("EggCycles").item(0).getTextContent()));
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

	public static void main(String[] args) {
		List<String> pokemonlist = Arrays.asList("Bulbasaur","Ivysaur","Venusaur","Charmander","Charmeleon","Charizard","Squirtle","Wartortle","Blastoise","Caterpie","Metapod","Butterfree","Weedle","Kakuna","Beedrill","Pidgey","Pidgeotto","Pidgeot","Rattata","Raticate","Spearow","Fearow","Ekans","Arbok","Pikachu","Raichu","Sandshrew","Sandslash","Nidoran♀","Nidorina","Nidoqueen","Nidoran♂","Nidorino","Nidoking","Clefairy","Clefable","Vulpix","Ninetales","Jigglypuff","Wigglytuff","Zubat","Golbat","Oddish","Gloom","Vileplume","Paras","Parasect","Venonat","Venomoth","Diglett","Dugtrio","Meowth","Persian","Psyduck","Golduck","Mankey","Primeape","Growlithe","Arcanine","Poliwag","Poliwhirl","Poliwrath","Abra","Kadabra","Alakazam","Machop","Machoke","Machamp","Bellsprout","Weepinbell","Victreebel","Tentacool","Tentacruel","Geodude","Graveler","Golem","Ponyta","Rapidash","Slowpoke","Slowbro","Magnemite","Magneton","Farfetch\'d","Doduo","Dodrio","Seel","Dewgong","Grimer","Muk","Shellder","Cloyster","Gastly","Haunter","Gengar","Onix","Drowzee","Hypno","Krabby","Kingler","Voltorb","Electrode","Exeggcute","Exeggutor","Cubone","Marowak","Hitmonlee","Hitmonchan","Lickitung","Koffing","Weezing","Rhyhorn","Rhydon","Chansey","Tangela","Kangaskhan","Horsea","Seadra","Goldeen","Seaking","Staryu","Starmie","Mr. Mime","Scyther","Jynx","Electabuzz","Magmar","Pinsir","Tauros","Magikarp","Gyarados","Lapras","Ditto","Eevee","Vaporeon","Jolteon","Flareon","Porygon","Omanyte","Omastar","Kabuto","Kabutops","Aerodactyl","Snorlax","Articuno","Zapdos","Moltres","Dratini","Dragonair","Dragonite","Mewtwo","Mew","Chikorita","Bayleef","Meganium","Cyndaquil","Quilava","Typhlosion","Totodile","Croconaw","Feraligatr","Sentret","Furret","Hoothoot","Noctowl","Ledyba","Ledian","Spinarak","Ariados","Crobat","Chinchou","Lanturn","Pichu","Cleffa","Igglybuff","Togepi","Togetic","Natu","Xatu","Mareep","Flaaffy","Ampharos","Bellossom","Marill","Azumarill","Sudowoodo","Politoed","Hoppip","Skiploom","Jumpluff","Aipom","Sunkern","Sunflora","Yanma","Wooper","Quagsire","Espeon","Umbreon","Murkrow","Slowking","Misdreavus","Unown","Wobbuffet","Girafarig","Pineco","Forretress","Dunsparce","Gligar","Steelix","Snubbull","Granbull","Qwilfish","Scizor","Shuckle","Heracross","Sneasel","Teddiursa","Ursaring","Slugma","Magcargo","Swinub","Piloswine","Corsola","Remoraid","Octillery","Delibird","Mantine","Skarmory","Houndour","Houndoom","Kingdra","Phanpy","Donphan","Porygon2","Stantler","Smeargle","Tyrogue","Hitmontop","Smoochum","Elekid","Magby","Miltank","Blissey","Raikou","Entei","Suicune","Larvitar","Pupitar","Tyranitar","Lugia","Ho-Oh","Celebi","Treecko","Grovyle","Sceptile","Torchic","Combusken","Blaziken","Mudkip","Marshtomp","Swampert","Poochyena","Mightyena","Zigzagoon","Linoone","Wurmple","Silcoon","Beautifly","Cascoon","Dustox","Lotad","Lombre","Ludicolo","Seedot","Nuzleaf","Shiftry","Taillow","Swellow","Wingull","Pelipper","Ralts","Kirlia","Gardevoir","Surskit","Masquerain","Shroomish","Breloom","Slakoth","Vigoroth","Slaking","Nincada","Ninjask","Shedinja","Whismur","Loudred","Exploud","Makuhita","Hariyama","Azurill","Nosepass","Skitty","Delcatty","Sableye","Mawile","Aron","Lairon","Aggron","Meditite","Medicham","Electrike","Manectric","Plusle","Minun","Volbeat","Illumise","Roselia","Gulpin","Swalot","Carvanha","Sharpedo","Wailmer","Wailord","Numel","Camerupt","Torkoal","Spoink","Grumpig","Spinda","Trapinch","Vibrava","Flygon","Cacnea","Cacturne","Swablu","Altaria","Zangoose","Seviper","Lunatone","Solrock","Barboach","Whiscash","Corphish","Crawdaunt","Baltoy","Claydol","Lileep","Cradily","Anorith","Armaldo","Feebas","Milotic","Castform","Castform","Castform","Castform","Kecleon","Shuppet","Banette","Duskull","Dusclops","Tropius","Chimecho","Absol","Wynaut","Snorunt","Glalie","Spheal","Sealeo","Walrein","Clamperl","Huntail","Gorebyss","Relicanth","Luvdisc","Bagon","Shelgon","Salamence","Beldum","Metang","Metagross","Regirock","Regice","Registeel","Latias","Latios","Kyogre","Groudon","Rayquaza","Jirachi","Deoxys","Deoxys","Deoxys","Deoxys","Turtwig","Grotle","Torterra","Chimchar","Monferno","Infernape","Piplup","Prinplup","Empoleon","Starly","Staravia","Staraptor","Bidoof","Bibarel","Kricketot","Kricketune","Shinx","Luxio","Luxray","Budew","Roserade","Cranidos","Rampardos","Shieldon","Bastiodon","Burmy","Burmy","Burmy","Wormadam","Wormadam","Wormadam","Mothim","Combee","Vespiquen","Pachirisu","Buizel","Floatzel","Cherubi","Cherrim","Shellos","Shellos","Gastrodon","Gastrodon","Ambipom","Drifloon","Drifblim","Buneary","Lopunny","Mismagius","Honchkrow","Glameow","Purugly","Chingling","Stunky","Skuntank","Bronzor","Bronzong","Bonsly","Mime Jr.","Happiny","Chatot","Spiritomb","Gible","Gabite","Garchomp","Munchlax","Riolu","Lucario","Hippopotas","Hippowdon","Skorupi","Drapion","Croagunk","Toxicroak","Carnivine","Finneon","Lumineon","Mantyke","Snover","Abomasnow","Weavile","Magnezone","Lickilicky","Rhyperior","Tangrowth","Electivire","Magmortar","Togekiss","Yanmega","Leafeon","Glaceon","Gliscor","Mamoswine","Porygon-Z","Gallade","Probopass","Dusknoir","Froslass","Rotom","Rotom","Rotom","Rotom","Rotom","Rotom","Uxie","Mesprit","Azelf","Dialga","Palkia","Heatran","Regigigas","Giratina","Giratina","Cresselia","Phione","Manaphy","Darkrai","Shaymin","Shaymin","Arceus","Victini","Snivy","Servine","Serperior","Tepig","Pignite","Emboar","Oshawott","Dewott","Samurott","Patrat","Watchog","Lillipup","Herdier","Stoutland","Purrloin","Liepard","Pansage","Simisage","Pansear","Simisear","Panpour","Simipour","Munna","Musharna","Pidove","Tranquill","Unfezant","Unfezant","Blitzle","Zebstrika","Roggenrola","Boldore","Gigalith","Woobat","Swoobat","Drilbur","Excadrill","Audino","Timburr","Gurdurr","Conkeldurr","Tympole","Palpitoad","Seismitoad","Throh","Sawk","Sewaddle","Swadloon","Leavanny","Venipede","Whirlipede","Scolipede","Cottonee","Whimsicott","Petilil","Lilligant","Basculin","Basculin","Sandile","Krokorok","Krookodile","Darumaka","Darmanitan","Darmanitan","Maractus","Dwebble","Crustle","Scraggy","Scrafty","Sigilyph","Yamask","Cofagrigus","Tirtouga","Carracosta","Archen","Archeops","Trubbish","Garbodor","Zorua","Zoroark","Minccino","Cinccino","Gothita","Gothorita","Gothitelle","Solosis","Duosion","Reuniclus","Ducklett","Swanna","Vanillite","Vanillish","Vanilluxe","Deerling","Sawsbuck","Emolga","Karrablast","Escavalier","Foongus","Amoonguss","Frillish","Frillish","Jellicent","Jellicent","Alomomola","Joltik","Galvantula","Ferroseed","Ferrothorn","Klink","Klang","Klinklang","Tynamo","Eelektrik","Eelektross","Elgyem","Beheeyem","Litwick","Lampent","Chandelure","Axew","Fraxure","Haxorus","Cubchoo","Beartic","Cryogonal","Shelmet","Accelgor","Stunfisk","Mienfoo","Mienshao","Druddigon","Golett","Golurk","Pawniard","Bisharp","Bouffalant","Rufflet","Braviary","Vullaby","Mandibuzz","Heatmor","Durant","Deino","Zweilous","Hydreigon","Larvesta","Volcarona","Cobalion","Terrakion","Virizion","Tornadus","Thundurus","Reshiram","Zekrom","Landorus","Kyurem","Keldeo","Meloetta","Meloetta","Genesect","Chespin","Quilladin","Chesnaught","Fennekin","Braixen","Delphox","Froakie","Frogadier","Greninja","Bunnelby","Diggersby","Fletchling","Fletchinder","Talonflame","Scatterbug","Spewpa","Vivillon","Litleo","Pyroar","Flabébé","Floette","Florges","Skiddo","Gogoat","Pancham","Pangoro","Furfrou","Espurr","Meowstic","Honedge","Doublade","Aegislash","Spritzee","Aromatisse","Swirlix","Slurpuff","Inkay","Malamar","Binacle","Barbaracle","Skrelp","Dragalge","Clauncher","Clawitzer","Helioptile","Heliolisk","Tyrunt","Tyrantrum","Amaura","Aurorus","Sylveon","Hawlucha","Dedenne","Carbink","Goomy","Sliggoo","Goodra","Klefki","Phantump","Trevenant","Pumpkaboo","Gourgeist","Bergmite","Avalugg","Noibat","Noivern","Xerneas","Yveltal","Zygarde","Diancie");
		for (String pokemon : pokemonlist) {
			PokemonSpecies PokemonObject = new PokemonSpecies();
			PokemonObject.loadFromBulbapedia(pokemon.replace(" ","_"));
			PokemonObject.saveToXML();
		}
	}
}
