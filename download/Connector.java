package download;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Connector {
	
	private String urlStr;
	private URL myURL;
	private HttpURLConnection myConnection;
	private int myResourceLength;
		
	public Connector(String url) {
		urlStr = url;
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void init() throws IOException {
		myURL = new URL(urlStr);
		myConnection = (HttpURLConnection)myURL.openConnection();
		myConnection.setAllowUserInteraction(true);
		myResourceLength = myConnection.getContentLength();
	}
	
	public int getResourceLength() {
		return myResourceLength;
	}
	
	public URLConnection getConnection() {
		return myConnection;
	}
	
	public String getURLString() {
		return urlStr;
	}
	
}
