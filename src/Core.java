import java.util.HashMap;


public class Core {

	public static void main(String[] args) {
		//get login information
		String username = "deer-spangle";
		String password = "deercockyay";
		HashMap<String,String> login_data = new HashMap<String,String>();
		login_data.put("user",username);
		login_data.put("passwd",password);
		login_data.put("api_type","json");
		String login_string = Web.build_query_string(login_data,"POST");
		String login_response = Web.post_page("http://www.reddit.com/api/login",login_string);
		System.out.println(login_response);
		//fire login command, get ['json']['data']['modhash']
		
	}
}
