package Tests;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Application.AutoMater;
import Logging.Log;
import Logging.TestTimer;

public class W3cTests
{
	public static void CheckSelections()
	{
		// If "Check HTML Markup" is selected
		if (AutoMater.chckbx_CheckHtmlMarkup.isSelected())
		{
			// Run test
			CheckHtmlMarkup();
		}
		
		// If "Check CSS" is selected
		if (AutoMater.chckbx_CheckCss.isSelected())
		{
			// Run test
			CheckCss();
		}
	}
	
	private static void CheckHtmlMarkup()
	{
		// Build URL for W3C HTML Markup Validator API
		String siteToTest = "http://validator.w3.org/check?output=soap12&uri=" + AutoMater.url_SiteToTest;
		
		// Get the response from W3C
		Document doc = GetValidationResultXML(siteToTest);
		
		if (doc != null)
		{
			// Parse the XML and write the results to the log
			ParseAndLog(doc, "Check HTML Markup");
		}
	}
	
	private static void CheckCss()
	{
		// Build URL for W3C CSS Validator API
		String siteToTest = "http://jigsaw.w3.org/css-validator/validator?output=soap12&profile=css3&uri=" + AutoMater.url_SiteToTest;
		
		// Get the response from W3C
		Document doc = GetValidationResultXML(siteToTest);
		
		if (doc != null)
		{
			// Parse the XML and write the results to the log
			ParseAndLog(doc, "Check CSS");
		}
	}
	
	private static Document GetValidationResultXML(String siteToTest)
	{
		// Create a Document to hold the result
		Document doc = null;
		
		try
		{
			// Send request and save the response
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        doc = db.parse(new URL(siteToTest).openStream());
	        doc.normalize();
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
		
		return doc;
	}
	
	private static void ParseAndLog(Document doc, String testName)
	{
		// Start the test timer
		TestTimer.Start();
		
		// Create a new StringBuilder() to house the results
    	StringBuilder sb = new StringBuilder();
    	sb.append(testName + " - Results:");
    	
    	try
    	{
	    	// Get <m:errorcount> and write to log
	        NodeList errorCountNode = doc.getElementsByTagName("m:errorcount");
	        int errorCount = Integer.parseInt(errorCountNode.item(0).getTextContent());
	        sb.append(System.getProperty("line.separator"));
	        sb.append("Error Count: " + errorCount);
	        
	        // Get <m:warningcount> and write to log
	        NodeList warningCountNode = doc.getElementsByTagName("m:warningcount");
	        int warningCount = Integer.parseInt(warningCountNode.item(0).getTextContent());
	        sb.append(System.getProperty("line.separator"));
	        sb.append("Warning Count: " + warningCount);
	        
	        // Get errors
	        NodeList errorNodeList = doc.getElementsByTagName("m:error");
	        
	        // For each <m:error> in <m:errors>
	        for (int i = 0; i < errorCount; i++)
	        {
	        	// Write Error to log
	        	sb.append(System.getProperty("line.separator"));
				sb.append("Error " + (i+1) + ":");
				
				// Get current error node
	        	Node currentErrorNode = errorNodeList.item(i);
	        	
	        	// Get current error node's children
	        	NodeList children = currentErrorNode.getChildNodes();
	        	
	        	// Iterate through the children to get the <m:{propertyName}> values
	        	for (int j = 0; j < children.getLength(); j++)
	        	{
	        		// Get current child
	        		Node child = children.item(j);
	        		
	        		// Get the child's details
	        		String properties = GetResultProperties(child);
	        		
	        		// Add the child's details to the log
	        		sb.append(properties);
	        	}
	        }
	        
	        // Get warnings
	        NodeList warningNodeList = doc.getElementsByTagName("m:warning");
	        
	        // For each <m:warning> in <m:warnings>
	        for (int i = 0; i < warningNodeList.getLength(); i++)
	        {
	        	// Write warning to log
	        	sb.append(System.getProperty("line.separator"));
				sb.append("Warning " + (i+1) + ":");
				
				// Get current warning node
	        	Node currentWarningNode = warningNodeList.item(i);
	        	
	        	// Get current warning node's children
	        	NodeList children = currentWarningNode.getChildNodes();
	        	
	        	// Iterate through the children to get the <m:{propertyName}> values
	        	for (int j = 0; j < children.getLength(); j++)
	        	{
	        		// Get current child
	        		Node child = children.item(j);
	        		
	        		// Get the child's details
	        		String properties = GetResultProperties(child);
	        		
	        		// Add the child's details to the log
	        		sb.append(properties);
	        	}
	        }
    	}
    	catch (Exception ex)
    	{
    		// Write result to log
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
	
	private static String GetResultProperties(Node child)
	{
		// Create a new StringBuilder() to house the results
    	StringBuilder sb = new StringBuilder();
    	
		// Get the current child's name
		String childName = child.getNodeName();
		
		// Write attributes to console
		if (childName == "m:line")
		{
			sb.append(System.getProperty("line.separator"));
			sb.append("\t" + "Line: " + child.getTextContent());
		}
		else if (childName == "m:col")
		{
			sb.append(System.getProperty("line.separator"));
			sb.append("\t" + "Column: " + child.getTextContent());
		}
		else if (childName == "m:message")
		{
			sb.append(System.getProperty("line.separator"));
			sb.append("\t" + "Message: " + child.getTextContent());
		}
		else if (childName == "m:messageid")
		{
			sb.append(System.getProperty("line.separator"));
			sb.append("\t" + "Message ID: " + child.getTextContent());
		}
		else if (childName == "m:explanation")
		{
			sb.append(System.getProperty("line.separator"));
			sb.append("\t" + "Explanation: " + child.getTextContent());
		}
		else if (childName == "m:source")
		{
			sb.append(System.getProperty("line.separator"));
			sb.append("\t" + "Source: " + child.getTextContent());
		}
		
		return sb.toString();
	}
}