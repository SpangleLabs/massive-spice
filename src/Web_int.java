import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class Web_int {
	private String modhash;
	private String username;

	public Web_int(String username,String password) {
		this.username = username;
		this.login(username, password);
	}
	
	public boolean login(String username,String password) {
		/* *
		 * Logs into reddit and stores the modhash in class variables.
		 */
		HashMap<String,String> login_data = new HashMap<String,String>();
		login_data.put("user",username);
		login_data.put("passwd",password);
		login_data.put("api_type","json");
		String login_string = Web.build_query_string(login_data,"POST");
		String login_response = Web.post_page("http://www.reddit.com/api/login",login_string);
		JSONObject login_reply_obj = (JSONObject) JSONValue.parse(login_response);
		JSONObject login_reply_objj = (JSONObject) login_reply_obj.get("json");
		JSONArray login_errors = (JSONArray) login_reply_objj.get("errors");
		if(login_errors.isEmpty()) {
			return false;
		}
		JSONObject login_reply_data = (JSONObject) login_reply_objj.get("data");
		try {
			this.modhash = (String) login_reply_data.get("modhash");
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public JSONArray list_subreddit(String subreddit) {
		/* *
		 * Gets the new post list from a specified subreddit.
		 */
		String list_response = Web.get_page("http://www.reddit.com/r/"+subreddit+"/new.json");
		JSONObject list_obj = (JSONObject) JSONValue.parse(list_response);
		JSONObject list_data = (JSONObject) list_obj.get("data");
		this.modhash = (String) list_data.get("modhash");
		return (JSONArray) list_data.get("children");
	}
	
	public JSONArray list_inbox() {
		HashMap<String,String> data_send = new HashMap<String,String>();
		data_send.put("mark","false");
		String data_string = Web.build_query_string(data_send, "GET");
		String inbox_response = Web.get_page("http://www.reddit.com/message/inbox.json"+data_string,this.modhash);
		JSONObject inbox_obj = (JSONObject) JSONValue.parse(inbox_response);
		JSONObject inbox_data = (JSONObject) inbox_obj.get("data");
		this.modhash = (String) inbox_data.get("modhash");
		return (JSONArray) inbox_data.get("children");
	}

}
