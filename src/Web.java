import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Web {
	private static String user_agent = "/r/SVExchange giveaway helper (massive-spice) by /u/dr-spangle";
	
	public static String post_page(String url, String data) {
		/* *
		 * Downloads a page's contents after sending POST data
		 */
		StringBuffer url_response = new StringBuffer();
		BufferedReader url_in = null;
		String line = null;
		try {
			URL url_obj = new URL(url);
			HttpURLConnection url_con = (HttpURLConnection) url_obj.openConnection();
			url_con.setRequestMethod("POST");
			url_con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
			url_con.setRequestProperty("User-Agent",user_agent);
			url_con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			String url_parameters = data;
			url_con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(url_con.getOutputStream());
			wr.writeBytes(url_parameters);
			wr.flush();
			wr.close();
			int url_response_code = url_con.getResponseCode();
			System.out.println(url_response_code);
			if(url_response_code==200) {
				url_in = new BufferedReader(new InputStreamReader(url_con.getInputStream()));
			} else {
				url_in = new BufferedReader(new InputStreamReader(url_con.getErrorStream()));
			}
			while((line = url_in.readLine())!=null) {
				url_response.append(line);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				url_in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(url_response.toString());
		return url_response.toString();
	}
	
	public static String get_page(String url) {
		/* *
		 * Downloads a page's contents, without sending any data via POST.
		 */
		StringBuffer url_response = new StringBuffer();
		BufferedReader url_in = null;
		try {
			URL url_obj = new URL(url);
			HttpURLConnection url_con = (HttpURLConnection) url_obj.openConnection();
			url_con.setRequestMethod("GET");
			url_con.setRequestProperty("User-Agent:",user_agent);
			url_con.setDoOutput(true);
			int url_response_code = url_con.getResponseCode();
			System.out.println(url_response_code);
			if(url_response_code==200) {
				url_in = new BufferedReader(new InputStreamReader(url_con.getInputStream()));
			} else {
				url_in = new BufferedReader(new InputStreamReader(url_con.getErrorStream()));
			}
			String line;
			while((line = url_in.readLine())!=null) {
				url_response.append(line);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				url_in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(url_response.toString());
		return url_response.toString();
	}
	
	public static String get_page(String url, String modhash) {
		/* *
		 * Downloads a page's contents, sending modhash header, without sending any data via POST.
		 */
		StringBuffer url_response = new StringBuffer();
		BufferedReader url_in = null;
		try {
			URL url_obj = new URL(url);
			HttpURLConnection url_con = (HttpURLConnection) url_obj.openConnection();
			url_con.setRequestMethod("GET");
			url_con.setRequestProperty("User-Agent:",user_agent);
			url_con.setRequestProperty("X-Modhash:",modhash);
			url_con.setDoOutput(true);
			int url_response_code = url_con.getResponseCode();
			System.out.println(url_response_code);
			if(url_response_code==200) {
				url_in = new BufferedReader(new InputStreamReader(url_con.getInputStream()));
			} else {
				url_in = new BufferedReader(new InputStreamReader(url_con.getErrorStream()));
			}
			String line;
			while((line = url_in.readLine())!=null) {
				url_response.append(line);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				url_in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(url_response.toString());
		return url_response.toString();
	}
	
	public static String build_query_string(HashMap<String,String> data, String type) {
		/* *
		 * Collapses hashmaps of the form {value:data} down to query strings for web requests.
		 */
		type = type.toLowerCase();
		if(!type.equals("get") && !type.equals("post")) {
			return null;
		}
		ArrayList<String> parameters = new ArrayList<String>();
		for (Entry<String,String> datum : data.entrySet()) {
			String key = datum.getKey();
			String value = datum.getValue();
			try {
				value = URLEncoder.encode(datum.getValue(),"UTF-8");
				key = URLEncoder.encode(datum.getKey(),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			parameters.add(key+"="+value);
		}
		String querystring = "";
		if(type.equals("get")) {
			querystring = "?";
		}
		querystring += Commons.join_list(parameters, "&");
		return querystring;
	}
}
