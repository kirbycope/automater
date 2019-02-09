package Tests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import Application.AutoMater;
import Logging.Log;
import Logging.TestTimer;
import Tests.Common.JsonResponse;

public class CompatibilityTests
{
	public static void CheckSelections()
	{
		// If "Check source code for HTML5/CSS3 attributes" is selected
		if (AutoMater.chckbx_CheckCompatibility.isSelected())
		{
			// Run test
			CheckCompatibility();
		}
	}
	
	/* Begin Test Variables */
		
		// This is the URL of the JSON string containing CanIUse.com's data
		private static String url = "https://raw.githubusercontent.com/Fyrd/caniuse/master/data.json";
		
		// This is the JSONObject to hold the "data" element
		private static JSONObject jsonData;
		
		// This is a dictionary to containing element:keywords
		private static Map<String, String> dictionary = new HashMap<String, String>();
		
		// Create a new StringBuilder() to house the results
    	private static StringBuilder sb = new StringBuilder();
		
	/* End Test Variables */
	
	private static void CheckCompatibility()
	{
		// Start the test timer
		TestTimer.Start();
		
		// Write header to log
		sb.append("Check Compatibility - Results:");
		
    	try
    	{
	    	// Fetch JSON string from URL response and save as a JSONObject
			JSONObject jsonCanIUse = JsonResponse.Get(url);
			
			// Populate dictionary data, containing {data-element:keywords}, using the JSON from CanIUse.com
			CreateElementKeywordsHashMap(jsonCanIUse);
			
			// Check if the source code contains one of the dictionary data-element's keywords
			CheckForElements();
    	}
    	catch (Exception ex)
    	{
    		sb.append(System.getProperty("line.separator"));
    		sb.append("Exception: " + ex);
    	}
    	finally
    	{
    		// Add the test execution information to the end of the log
    		sb.append(TestTimer.Result());
    		
	 		// Write the results to the log
	    	Log.Write(sb.toString());
    	}
	}
	
	private static void CreateElementKeywordsHashMap(JSONObject jsonCanIUse) throws Exception
	{
		// Strip down the JSON to just the 'data' element
		jsonData = (JSONObject) jsonCanIUse.get("data");
		
		// Create an iterator for the new JSONObject
		Iterator<?> keys = jsonData.keys();
		
		// Parse the data
		while( keys.hasNext() )
	    {
			String key = (String)keys.next();
			// Ensure the key returns a JSONObject as its value
	        if( jsonData.get(key) instanceof JSONObject )
	        {
	        	// Get the current JSONObject
	        	JSONObject jsonCurrent = jsonData.getJSONObject(key);
	        	
	        	// Get the value of 'keywords'
	        	String value = jsonCurrent.getString("keywords");
	        	
	        	// Save {key:value} pair to dictionary
	        	dictionary.put(key, value);
	        }
	    }
	}
	
	private static void CheckForElements() throws Exception
	{
		// Create a new instance of a BufferedReader and set it as the stream of the url
		BufferedReader br = new BufferedReader
		(
			new InputStreamReader
			(
					AutoMater.url_SiteToTest.openStream()
			)
		);
		
		// Create string to hold each line of source code as it is read
        String inputLine;
        
        while ( (inputLine = br.readLine()) != null )
        {
			// Split the inputLine up by space characters
			String[] splitTestLine = inputLine.split("\\s+");
			
			// For each phrase in the splitTestLine array...
			for (String phrase: splitTestLine)
			{
				// May need to add more special cases here as they are found
				
				// Special Case: Text after placeholder is dynamic and not needed
				if (phrase.contains("placeholder="))
				{
					phrase = phrase.substring(0, phrase.lastIndexOf("="));
				}
				
				// Create an iterator for the dictionary
				Set<String> mapKeys = dictionary.keySet();
				
				// For each term in the dictionary...
				for (Iterator<String> i = mapKeys.iterator(); i.hasNext();)
				{
					// Get the current key
					String key = (String) i.next();
					
					// Get the current key's value (keywords for a term in the dictionary)
					String value = dictionary.get(key);
					
					// Compare the phrase to the dictionary key's value (keywords)
					if (value.contains(phrase))
					{
						// Split the values up by commas
						String values[] = value.split(",");
						
						// For each keyword in the dictionary's value...
						for (String s: values)
					    {
							// Compare the exact keyword to the exact phrase
					        if (s.equals(phrase))
							{
					        	// Write to log, found a matching keyword.
								sb.append(System.getProperty("line.separator"));
								sb.append("A keyword was found in the source code." );
								sb.append(System.getProperty("line.separator"));
								sb.append("\t" + "Keywords for " + key + ": " + value);
								sb.append(System.getProperty("line.separator"));
								sb.append("\t" + "Keyword Found: " + phrase);
								sb.append(System.getProperty("line.separator"));
								sb.append("\t" + "Source Code: " + System.getProperty("line.separator") + phrase);
								
								// Get the current property (key) JSON data
								JSONObject jsonProperty = jsonData.getJSONObject(key);
								
								// Get the "stats" data-element
								JSONObject jsonStats = jsonProperty.getJSONObject("stats");
								
								// This next section is hard coded, because I don't care to iterate through all browsers
								
								// Get Android Chrome data ("and_chr")
								JSONObject jsonAndroidChrome = jsonStats.getJSONObject("and_chr");
									// Print compatibility
									PrintCompatibility("Android Chrome", jsonAndroidChrome);
								
								// Get Chrome data ("chrome")
								JSONObject jsonChrome = jsonStats.getJSONObject("chrome");
									// Print compatibility
									PrintCompatibility("Chrome", jsonChrome);
								
								// Get Firefox data ("firefox")
								JSONObject jsonFirefox = jsonStats.getJSONObject("firefox");
									// Print compatibility
									PrintCompatibility("Firefox", jsonFirefox);
								
								// Get Internet Explorer data ("ie")
								JSONObject jsonIE = jsonStats.getJSONObject("ie");
									// Print compatibility
									PrintCompatibility("Internet Explorer", jsonIE);
								
								// Get Safari iOS data ("ios_saf")
								JSONObject jsonIosSafari = jsonStats.getJSONObject("ios_saf");
									// Print compatibility
									PrintCompatibility("iOS Safari", jsonIosSafari);
								
								// Get Safari data ("safari")
								JSONObject jsonSafari = jsonStats.getJSONObject("safari");
									// Print compatibility
									PrintCompatibility("Safari", jsonSafari);
								
							}
							
					    }
					}
				}
			}
        }
        
        // Close the buffered reader
        br.close();
	}
	
	private static void PrintCompatibility(String browserName, JSONObject jsonObject) throws Exception
	{
		// Create an iterator for the JSONObject based on its keys
		Iterator<?> keys = jsonObject.keys();
		
		while( keys.hasNext() )
		{
			// Get the key
            String stringKey = (String)keys.next();
            
            // Get the key's value
            String jsonValue = (String) jsonObject.get(stringKey);
            
            // Create null string to hold current iterations compatibility
            String compatibility;
            
            // Change compatibility value from flag to human readable string
            if (jsonValue.equals("n"))
            {
            	compatibility = "none";
            }
            else if (jsonValue.equals("y"))
            {
            	compatibility = "yes";
            }
            else
            {
            	compatibility = "limited";
            }
            
            // Display message only if it's not a "yes"
            if (compatibility != "yes")
            {
            	sb.append(System.getProperty("line.separator"));
            	sb.append("\t" + "\t" + browserName + " Version: " + stringKey + " | Compatibility: " + compatibility);
            }
        }
	}
}