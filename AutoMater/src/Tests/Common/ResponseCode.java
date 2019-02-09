package Tests.Common;

import java.net.HttpURLConnection;
import java.net.URL;

public class ResponseCode
{
	public static int Get(String href) throws Exception
	{
		int responseCode;
		
		URL url = new URL(href);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        responseCode = connection.getResponseCode();
        
		return responseCode;
	}
}