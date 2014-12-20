package DataObjects;

public class Giveaway {
	private String title = null;
	private String message = null;
	private String link = null;
	
	public String get_title() {
		/* *
		 * Returns the giveaway title.
		 */
		return this.title;
	}
	
	public void set_title(String input_title) {
		/* *
		 * Sets the giveaway title.
		 */
		this.title = input_title;
	}
	
	public String get_message() {
		/* *
		 * Returns the giveaway message.
		 */
		return this.message;
	}
	
	public void set_message(String input_message) {
		/* *
		 * Sets the giveaway message.
		 */
		this.message = input_message;
	}
	
	public String get_link() {
		/* *
		 * Returns the reddit giveaway link.
		 */
		return this.link;
	}
	
	public void set_link(String input_link) {
		/* *
		 * Sets the reddit giveaway link.
		 */
		this.link = input_link;
	}
}
