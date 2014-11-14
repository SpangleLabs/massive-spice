import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class Web_int {
	private String modhash;
	
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
		this.modhash = (String) login_reply_data.get("modhash");
		return true;
	}

}
