import java.util.ArrayList;


public class Commons {
	/**
	 * A class to hold a few miscellaneous static functions with nowhere to go.
	 */
		public static String join_list(ArrayList<String> list, String delimiter) {
			/* *
			 * Joins together a list into a single string, with elements delimited by delimiter. 
			 */
			String output = "";
			for(int i=0; i<list.size(); i++) {
				output += list.get(i) + delimiter;
			}
			return output.replaceAll(delimiter+"$","");
		}

}
