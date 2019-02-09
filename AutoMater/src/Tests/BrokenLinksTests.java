package Tests;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import Application.AutoMater;
import Logging.Log;
import Logging.TestTimer;
import Tests.Common.ResponseCode;

public class BrokenLinksTests
{
	public static void CheckSelections()
	{
		// If "Check <a> 'href=' attribute(s)" is selected
		if (AutoMater.chckbx_CheckAnchorHref.isSelected())
		{
			// Run test
			CheckAnhorHref();
		}
	}
	
	private static void CheckAnhorHref()
	{
		// Start the test timer
		TestTimer.Start();
		
		// Create a WebClient using HtmlUnit (a headless browser)
		WebClient webClient = new WebClient();
		
    	// Create a new StringBuilder() for the log
    	StringBuilder sb = new StringBuilder();
    	sb.append("Check Anchor Href - Results:");
    	
    	try
    	{
    		// Get the HtmlPage
			HtmlPage page = webClient.getPage(AutoMater.url_SiteToTest);
			
			// Extract every <a> instance
	        List<DomElement> anchorList = page.getElementsByTagName("a");
	        
	        // Create a dictionary to hold the <a> and its href
	        LinkedHashMap<HtmlAnchor, String> dictionary = new LinkedHashMap<HtmlAnchor, String>();
	        
	        // For each <a> in anchorList
	        for (int i = 0; i < anchorList.size(); i++)
	        {
	        	// Get the current <a>
	        	HtmlAnchor anchor = (HtmlAnchor) anchorList.get(i);
	        	
	        	// Get the href attribute of the <a>
	        	String href = anchor.getHrefAttribute();
	        	
	        	// If the href starts with a '/' character
	        	if (href.startsWith("/"))
	        	{
	        		// Prepend site to test url to the href
	        		href = AutoMater.url_SiteToTest + href;
	        	}
	        	
	        	// Add <a> with valid href to dictionary
	        	if (href.startsWith("http"))
	        	{
	        		dictionary.put(anchor, href);
	        	}
	        }
	        
	        // Transform dictionary values (href) to an array
	        List<String> values = new ArrayList<String>(dictionary.values());
	        
	        // Transform dictionary keys (<a>) to an array
	        List<HtmlAnchor> keys = new ArrayList<HtmlAnchor>(dictionary.keySet());
	    	
	        // For each row in the dictionary...
	        for (int i = 0; i < dictionary.size(); i++)
	        {
	        	// Get the current href
	        	String href = values.get(i);
	        	
	        	// Navigate to the href and save its response code
	    		int statusCode = ResponseCode.Get(href);
	    		
	    		// If the status code is not 200 (OK)
	    		if (statusCode != 200)
	    		{
	    			// Add result to string builder
	    			sb.append(System.getProperty("line.separator"));
	    			sb.append("FAIL ("  + statusCode + "): " + keys.get(i));
	    			sb.append(System.getProperty("line.separator"));
	    			sb.append("\t" + "URL Tested: " + href);
	    		}
	        }

    	}
    	catch (Exception ex)
		{
			// Write exception to string builder
    		sb.append(System.getProperty("line.separator"));
			sb.append("Exception: " + ex);
		}
		finally
		{
			// Close the web client
	 		webClient.closeAllWindows();
	 		
		 	// Add the test execution information to the end of the log
	 		sb.append(TestTimer.Result());
	 		
	 		// Write the results to the log
	    	Log.Write(sb.toString());
		}
	}
}