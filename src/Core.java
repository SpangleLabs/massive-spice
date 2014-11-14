
public class Core {

	public static void main(String[] args) {
		//get login information
		Web_int web_int = new Web_int();
		String username = "deer-spangle";
		String password = "b;ladf";
		web_int.login(username, password);
		//fire login command, get ['json']['data']['modhash']
		
	}
}
