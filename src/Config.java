
public class Config {
	private String last_inbox_message = null;
	
	
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

}
