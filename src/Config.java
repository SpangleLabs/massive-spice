import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Config {
	private String last_inbox_message = null;
	private ArrayList<HashMap<String,String>> messages = new ArrayList<HashMap<String,String>>();
	private ArrayList<HashMap<String,String>> giveaways = new ArrayList<HashMap<String,String>>();
	
	public Config() {
		this.read_xml_messages();
		this.save_xml_messages();
	}
	
	private void generate_config_dir() {
		/* *
		 * Creates the base config directory, if it doesn't exist.
		 */
		File dir = new File("config");
		dir.mkdirs();
	}
	
	private void save_file(String filename, String data) {
		/* *
		 * Saves given data to given filename.
		 */
		FileWriter file = null;
		try {
			file = new FileWriter(filename);
			file.write(data);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void save_xml_messages() {
		/* *
		 * Saves the messages array to an xml file in the config directory.
		 */
		this.generate_config_dir();
		String xml_dump = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\r\n<Messages>\r\n";
		for(HashMap<String,String> message : messages) {
			xml_dump += " <Message>\r\n";
			xml_dump += "  <Title>"+message.get("title")+"</Title>\r\n";
			xml_dump += "  <Regex>"+message.get("regex")+"</Regex>\r\n";
			xml_dump += "  <Reply>"+message.get("reply")+"</Reply>\r\n";
			xml_dump += " </Message>\r\n";
		}
		xml_dump += "</Messages>";
		this.save_file("config/messages.xml",xml_dump);
	}
	
	public void read_xml_messages() {
		try {
			ArrayList<HashMap<String,String>> new_messages = new ArrayList<HashMap<String,String>>();
			File file = new File("config/messages.xml");
			DocumentBuilder DocBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = DocBuilder.parse(file);
			Element doc_element = doc.getDocumentElement();
			NodeList doc_messages = doc_element.getElementsByTagName("Message");
			for(int temp = 0; temp<doc_messages.getLength(); temp++) {
				HashMap<String,String> new_message = new HashMap<String,String>();
				Node node_message = doc_messages.item(temp);
				Element element_message = (Element) node_message;
				new_message.put("title",element_message.getElementsByTagName("Title").item(0).getTextContent());
				new_message.put("regex",element_message.getElementsByTagName("Regex").item(0).getTextContent());
				new_message.put("reply",element_message.getElementsByTagName("Reply").item(0).getTextContent());
				new_messages.add(new_message);
			}
			this.messages = new_messages;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void read_xml() {
		/* *
		 * Reads the messages array from an xml file in the config directory.
		 */
		String data = null;
		ArrayList<HashMap<String,String>> new_messages = new ArrayList<HashMap<String,String>>(); 
		Pattern pattern_messages = Pattern.compile("<Messages>([\\s\\S]*?)<\\/Messages>");
		Pattern pattern_message = Pattern.compile("<Message>([\\s\\S]*?)<\\/Message>");
		Pattern pattern_title = Pattern.compile("<Title>([\\s\\S]*?)<\\/Title>");
		Pattern pattern_regex = Pattern.compile("<Regex>([\\s\\S]*?)<\\/Regex>");
		Pattern pattern_reply = Pattern.compile("<Reply>([\\s\\S]*?)<\\/Reply>");
		Matcher matcher_messages = pattern_messages.matcher(data);
		if(!matcher_messages.find()) {
			System.out.println("Failed to load XML.");
			return;
		}
		String data_messages = matcher_messages.group(1);
		Matcher matcher_message = pattern_message.matcher(data_messages);
		while(matcher_message.find()) {
			String data_message = matcher_message.group(1);
			HashMap<String,String> new_message = new HashMap<String,String>();
			Matcher matcher_title = pattern_title.matcher(data_message);
			Matcher matcher_regex = pattern_regex.matcher(data_message);
			Matcher matcher_reply = pattern_reply.matcher(data_message);
			String string_title = "";
			String string_regex = "";
			String string_reply = "";
			if(matcher_title.find()) {
				string_title = matcher_title.group(1);
			}
			if(matcher_regex.find()) {
				string_regex = matcher_regex.group(1);
			}
			if(matcher_reply.find()) {
				string_reply = matcher_reply.group(1);
			}
			new_message.put("title",string_title);
			new_message.put("regex",string_regex);
			new_message.put("reply",string_reply);
			new_messages.add(new_message);
		}
	}
	
	public static void read_xml2() {
	    try {
	    	File file = new File("config/messages.xml");
	    	DocumentBuilder DocBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	    	Document doc = DocBuilder.parse(file);
	    	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	    	if (doc.hasChildNodes()) {
	    		read_xml2node(doc.getChildNodes());
	    	}
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	    }
	}
	
	public static void read_xml2node(NodeList nodeList) {
		    for (int count = 0; count < nodeList.getLength(); count++) {
		    	Node tempNode = nodeList.item(count);
		    	// make sure it's element node.
		    	if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
		    		// get node name and value
		    		System.out.println("Node Name =" + tempNode.getNodeName() + " [OPEN]");
		    		System.out.println("Node Value =" + tempNode.getTextContent());
		    		if (tempNode.hasAttributes()) {
		    			// get attributes names and values
		    			NamedNodeMap nodeMap = tempNode.getAttributes();
		    			for (int i = 0; i < nodeMap.getLength(); i++) {
		    				Node node = nodeMap.item(i);
		    				System.out.println("attr name : " + node.getNodeName());
		    				System.out.println("attr value : " + node.getNodeValue());
		    			}
		    		}
		    		if (tempNode.hasChildNodes()) {
		    			// loop again if has child nodes
		    			read_xml2node(tempNode.getChildNodes());
		    		}
		    		System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
		    	}
		    }
	}
	
	public void set_last_inbox_message(String last_message) {
		/* *
		 * Sets the reddit fullname of the last message seen in inbox.
		 */
		this.last_inbox_message = last_message;
	}
	
	public String get_last_inbox_message() {
		/* *
		 * Returns the reddit fullname of the last message seen in inbox.
		 */
		return this.last_inbox_message;
	}
	
	public ArrayList<HashMap<String,String>> get_message_list() {
		/* *
		 * Returns the full list of messages.
		 */
		return this.messages;
	}

	public void load_user(String username) {
		// TODO Auto-generated method stub
		if(username == null) {
			giveaways = new ArrayList<HashMap<String,String>>();
		}
		//else, load up giveaways from some file. Construct a giveaway object first.
	}

}
