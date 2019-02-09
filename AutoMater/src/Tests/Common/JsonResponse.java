package Tests.Common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONObject;

public class JsonResponse
{
	public static JSONObject Get(String url) throws Exception
	{
		JSONObject jsonObject = null;

		InputStream inputStream = new URL(url).openStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
		String jsonText = readAll(bufferedReader);
	    jsonObject = new JSONObject(jsonText);
	    inputStream.close();
	    
		return jsonObject;
	}
	
	private static String readAll(Reader reader) throws Exception
	{
		StringBuilder stringBuilder = new StringBuilder();
	    int characterPosition;
	    while ( (characterPosition = reader.read()) != -1 )
	    {
	    	stringBuilder.append((char) characterPosition);
	    }
	    return stringBuilder.toString();
	}
}