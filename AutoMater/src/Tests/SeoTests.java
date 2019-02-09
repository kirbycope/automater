package Tests;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlMeta;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import Application.AutoMater;
import Logging.Log;
import Logging.TestTimer;
import Tests.Common.ResponseCode;

public class SeoTests
{
	public static void CheckSelections()
	{
		// If "Check for <meta> title" is selected
		if (AutoMater.chckbx_CheckSeoTitle.isSelected())
		{
			// Run test
			CheckSeoTitle();
		}
		
		// If "Check for <meta> description" is selected
		if (AutoMater.chckbx_CheckSeoDescription.isSelected())
		{
			// Run test
			CheckSeoDescription();
		}
		
		// If "Check for XML sitemap" is selected
		if (AutoMater.chckbx_CheckXmlSitemap.isSelected())
		{
			// Run test
			CheckXmlSitemap();
		}
	}
	
	private static void CheckSeoTitle()
	{
		// Start the test timer
		TestTimer.Start();
		
		// Create a WebClient using HtmlUnit (a headless browser)
		WebClient webClient = new WebClient();
		
    	// Create a new StringBuilder() for the log
    	StringBuilder sb = new StringBuilder();
    	sb.append("Check Meta Title - Results:");
    	
    	try
    	{
    		// Get the HtmlPage
    		HtmlPage page = webClient.getPage(AutoMater.url_SiteToTest);
    		
    		// Get Title text
    		String title = page.getTitleText();
    		
    		if (title.isEmpty())
    		{
    			// Add result to string builder
    			sb.append(System.getProperty("line.separator"));
    			sb.append("No <meta> title found.");
    		}
    		else
    		{
    			// Add result to string builder
    			sb.append(System.getProperty("line.separator"));
    			sb.append(title);
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
	
	private static void CheckSeoDescription()
	{
		// Start the test timer
		TestTimer.Start();
		
		// Create a WebClient using HtmlUnit (a headless browser)
		WebClient webClient = new WebClient();
		
    	// Create a new StringBuilder() for the log
    	StringBuilder sb = new StringBuilder();
    	sb.append("Check Meta Description - Results:");
    	
    	try
    	{
    		// Get the HtmlPage
    		HtmlPage page = webClient.getPage(AutoMater.url_SiteToTest);
    		
    		// Get the meta tag for description
    		HtmlMeta meta = page.getFirstByXPath("//meta[@name='description']");
    		
    		if (meta == null)
    		{
    			// Add result to string builder
    			sb.append(System.getProperty("line.separator"));
    			sb.append("No <meta> description found.");
    		}
    		else
    		{
    			// Add result to string builder
    			sb.append(System.getProperty("line.separator"));
    			sb.append(meta.toString());
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
	
	private static void CheckXmlSitemap()
	{
		// Start the test timer
		TestTimer.Start();
		
    	// Create a new StringBuilder() for the log
    	StringBuilder sb = new StringBuilder();
    	sb.append("Check XML Sitemap - Results:");
        
    	try
    	{	
			// Check for XML site map in root directory
    		int statusCode = ResponseCode.Get(AutoMater.url_SiteToTest + "/sitemap.xml");
    		
    		if (statusCode == 200)
    		{
    			sb.append(System.getProperty("line.separator"));
    			sb.append("XML Site Map found at: " + AutoMater.url_SiteToTest + "/sitemap.xml");
    		}
    		else
    		{
    			// Check for robots.txt and if found, then parse it for a reference to sitemap.xml
    			statusCode = ResponseCode.Get(AutoMater.url_SiteToTest + "/robots.txt");
    			if (statusCode == 200)
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
    		        
    		        // Create a flag to note if a match was found
    		        Boolean found = false;
    		        
    		        while ( (inputLine = br.readLine()) != null )
    		        {
    		        	// Check for reference to sitemap.xml
    		        	if ( inputLine.contains("sitemap.xml") )
    		        	{
    		        		found = true;
    		        		sb.append(System.getProperty("line.separator"));
    		    			sb.append("XML Site Map reference found in robots.txt: " + inputLine);
    		        	}
    		        }
    		        
    		        if (found == false)
    		        {
    		        	sb.append(System.getProperty("line.separator"));
		    			sb.append("XML Site Map not found in root directory and or robot.txt");
    		        }
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
    		// Add the test execution information to the end of the log
    		sb.append(TestTimer.Result());
			
	 		// Write the results to the log
	    	Log.Write(sb.toString());
    	}
	}
}