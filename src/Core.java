
public class Core {

	public static void main(String[] args) {
		//get login information
		String username = "deer-spangle";
		String password = "b;ladf";
		Web_int web_int = new Web_int(username, password);
		//web_int.login(username, password);
		//fire login command, get ['json']['data']['modhash']
		//wants to check inbox, check submissions, load data, etc
	}
}
