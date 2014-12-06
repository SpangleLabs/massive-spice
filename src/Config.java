import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Config {
	private String last_inbox_message = null;
	private ArrayList<HashMap<String,String>> messages = new ArrayList<HashMap<String,String>>();
	
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
	
	public void save_xml() {
		/* *
		 * Saves the messages array to an xml file in the config directory.
		 */
		this.generate_config_dir();
		String xml_dump = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\r\n <Messages>\r\n";
		for(HashMap<String,String> message : messages) {
			xml_dump += "  <Message>\r\n";
			xml_dump += "   <Title>"+message.get("title")+"</Title>\r\n";
			xml_dump += "   <Regex>"+message.get("regex")+"</Regex>\r\n";
			xml_dump += "   <Reply>"+message.get("reply")+"</Reply>\r\n";
			xml_dump += "  </Message>\r\n";
		}
		xml_dump += " </Messages>";
		this.save_file("config/messages.xml",xml_dump);
	}
	
	public void read_xml() {
		/* *
		 * Reads the messages array from an xml file in the config directory.
		 */
		String data = null;
		ArrayList<HashMap<String,String>> new_messages = new ArrayList<HashMap<String,String>>(); 
		Pattern pattern_messages = Pattern.compile("<Messages>[\\s\\S]*?<\\/Messages>");
		Pattern pattern_message = Pattern.compile("<Message>[\\s\\S]*?<\\/Message>");
		Pattern pattern_title = Pattern.compile("<Title>[\\s\\S]*?<\\/Title>");
		Pattern pattern_regex = Pattern.compile("<Regex>[\\s\\S]*?<\\/Regex>");
		Pattern pattern_reply = Pattern.compile("<Reply>[\\s\\S]*?<\\/Reply>");
		Matcher matcher_messages = pattern_messages.matcher(data);
		boolean found = false;
		while(matcher_messages.find()) {
			found = true;
		}
		if(!found) {
			System.out.println("Failed to load XML.");
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

}
