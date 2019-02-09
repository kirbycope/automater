package Tests;

import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import Application.AutoMater;
import Logging.Log;
import Logging.TestTimer;

public class AccessibilityTests
{
	public static void CheckSelections()
	{
		// If "Check <a> 'title=' attribute(s)" is selected
		if (AutoMater.chckbx_CheckAnchorTitle.isSelected())
		{
			// Run test
			CheckAnchorTitle();
		}
		
		// If "Check <img> 'alt=' attribute(s)" is selected
		if (AutoMater.chckbx_CheckImageAlt.isSelected())
		{
			// Run test
			CheckImageAlt();
		}
	}
	
	private static void CheckAnchorTitle()
    {
		// Start the test timer
		TestTimer.Start();
		
		// Create a WebClient using HtmlUnit (a headless browser)
		WebClient webClient = new WebClient();
		
    	// Create a new StringBuilder() for the log
    	StringBuilder sb = new StringBuilder();
    	sb.append("Check Anchor Title - Results:");
    	
		try
		{	
			// Get the HtmlPage
			HtmlPage page = webClient.getPage(AutoMater.url_SiteToTest);
			
			// Extract every <a> instance
	        final List<DomElement> anchorList = page.getElementsByTagName("a");
	        
	        // For each <a> in anchorList
	        for( int i = 0; i < anchorList.size(); i++ )
	        {	
	        	// Get the current <a>
	        	HtmlAnchor anchor = (HtmlAnchor) anchorList.get(i);
	        	
	        	// See if the title='' attribute is present with the <a>
	        	if ( anchor.getAttribute("title").isEmpty() )
	        	{
	        		// Write exception to string builder
	        		sb.append(System.getProperty("line.separator"));
	        		sb.append("Missing Title: " + anchor);
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
	
	private static void CheckImageAlt()
	{
		// Start the test timer
		TestTimer.Start();
		
		// Create a WebClient using HtmlUnit (a headless browser)
		WebClient webClient = new WebClient();
		
    	// Create a new StringBuilder() for the log
    	StringBuilder sb = new StringBuilder();
    	sb.append("Check Image Alt - Results:");
    	
    	try
    	{
    		// Get the HtmlPage
            final HtmlPage page = webClient.getPage(AutoMater.url_SiteToTest);
            
            // Extract every <img> instance
            final List<DomElement> imageList = page.getElementsByTagName("img");
            
            // For each <img> in imageList
            for(int i = 0; i < imageList.size(); i++)
            {
            	// Get the current <img>
            	HtmlImage image = (HtmlImage) imageList.get(i);
            	
            	// See if the alt='' attribute is present with the <img>
            	if (image.getAttribute("alt").isEmpty())
            	{
            		sb.append(System.getProperty("line.separator"));
            		sb.append("Missing Alt Text: " + image);
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