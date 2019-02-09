package Tests;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import com.googlecode.jslint4java.Issue;
import com.googlecode.jslint4java.JSLint;
import com.googlecode.jslint4java.JSLintBuilder;
import com.googlecode.jslint4java.JSLintResult;

import Application.AutoMater;
import Logging.Log;
import Logging.TestTimer;

public class JavaScriptTests
{
	public static void CheckSelections()
	{
		// If "Check referenced <script>(s) against JSLint" is selected
		if (AutoMater.chckbx_CheckReferencedScripts.isSelected())
		{
			// Run test
			CheckReferencedScripts();
		}
	}
	
	private static void CheckReferencedScripts()
	{
		// Get a list of internal script references
		ArrayList<String> list = GetReferencesList();
        
		// Resolve URLs
		list = ResolveURL(list);
		
		// Do JSLint scan of each file
		JSLintTest(list);
	}
	
	private static ArrayList<String> GetReferencesList()
	{
		// Create a list to hold the reference to each internal script
        ArrayList<String> list = new ArrayList<String>();
        
		try
		{
			// Get the Site To Test URL
			String siteToTest = AutoMater.url_SiteToTest.toString();
        	
			// Remove the HTTP/HTTPS protocol
        	if (siteToTest.startsWith("http://"))
        	{
        		// HTTP protocol
        		siteToTest = siteToTest.substring(7);
        	}
        	else if (siteToTest.startsWith("https://"))
        	{
        		// HTTPS protocol
        		siteToTest = siteToTest.substring(8);
        	}
        	
			// Create a new instance of a BufferedReader
			BufferedReader br = new BufferedReader
			(
				new InputStreamReader
				(
						AutoMater.url_SiteToTest.openStream()
				)
			);
			
			// Create string to hold each line of source code as it comes in
	        String inputLine;
	        
	        // Check inputLine
	        while ((inputLine = br.readLine()) != null)
	        {
	        	// If the source code line contains a script reference...
	        	if (inputLine.contains("<script type='text/javascript' src='"))
	        	{	
	        		if (
	        				(inputLine.contains("src='/"))		// If it starts with "/"
	        				&&									// and
	        				(!inputLine.contains("src='//"))	// not does not start with "//"
        				)
	        		{
	        			// Internal Reference
	        			list.add(inputLine);
	        		}
	        		else if (inputLine.contains("src='//" + siteToTest)) // "//" should only be used for external references (best practice), but caught here just the same
	        		{
	        			// Internal Reference
	        			list.add(inputLine);
	        		}
	        		else if (inputLine.contains("src=http://'" + siteToTest))
	        		{
	        			// Internal Reference
	        			list.add(inputLine);
	        		}
	        		else if (inputLine.contains("src=https://'" + siteToTest))
	        		{
	        			// Internal Reference
	        			list.add(inputLine);
        			}
	        	}
	        }

	        // Close the buffered reader
	        br.close();
		}
		catch (Exception ex)
		{
			// DEBUGGING
			System.out.println(ex);
		}

		return list;
	}
	
	public static ArrayList<String> ResolveURL(ArrayList<String> list)
	{
        // Create a new list as the passed in one is immutable
		ArrayList<String> newList = new ArrayList<String>();
		
		// For each String in the ArrayList
        for (int i = 0; i < list.size(); i++)
        {
        	// Extract a substring starting after the 'src=' and before the closing script tag
        	String srcURL = list.get(i).substring(36, list.get(i).indexOf("'>"));
        	
        	// Append the url_SiteToTest if needed
        	if (srcURL.startsWith("/"))
        	{
        		srcURL = AutoMater.url_SiteToTest + srcURL;
        	}
        	
        	// Add the script reference (fully qualified) the the new list
        	newList.add(srcURL);
        }
		
		return newList;
	}
	
	public static void JSLintTest(ArrayList<String> list)
	{
		// Start the test timer
		TestTimer.Start();
		
		// Create a new StringBuilder() to house the results
    	StringBuilder sb = new StringBuilder();
    	sb.append("Referenced Scripts JSLint - Results:");
    	
		try
		{
			// For each string in the list...
			for (int i = 0; i < list.size(); i++)
			{
				// Write the current script location to log
	    		sb.append(System.getProperty("line.separator"));
				sb.append("Script: " + list.get(i));
				
				// Get the string and save as a URL
				URL url = new URL(list.get(i));
				
				// Get the file content of the referenced script
				BufferedReader br = new BufferedReader
				(
					new InputStreamReader
					(
						url.openStream()
					)
				);
				
				// Construct a new JSLint instance
				JSLintBuilder builder = new JSLintBuilder();
				
				// Utility class to check for problems
				JSLint jsLint = builder.fromDefault();
				
				// The result of JSLint
				JSLintResult result = jsLint.lint("file", br);
				
				// Print results
	            for (Issue issue : result.getIssues())
	            {
	            	sb.append(System.getProperty("line.separator"));
            		sb.append(issue.toString());
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