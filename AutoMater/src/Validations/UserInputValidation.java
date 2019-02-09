package Validations;

import java.awt.Frame;
import java.net.URL;

import javax.swing.JOptionPane;

import Application.AutoMater;

public class UserInputValidation
{
	public static Boolean ValidateInputs()
	{
		Boolean result = true; // false = invalid inputs
		
		// Create a new StringBuilder() to house the results
    	StringBuilder sb = new StringBuilder();
		
		// Check that the Site to Test JTextField has some value
		if (AutoMater.text_SinglePageUrl.getText().isEmpty())
		{
			sb.append("Please enter a Site to Test.");
			result = false;
		}
		else
		{
			// Check that the Site to Test can be cast as a URL
			if (ValidateSiteToTest() == false)
			{
				sb.append("The Site to Test cannot be resolved as a URL.");
				sb.append(System.getProperty("line.separator"));
				sb.append("Example: http://www.google.com");
				result = false;
			}
		}
		
		// If there were errors, display them to the user
		if (result == false)
		{
			JOptionPane.showMessageDialog(new Frame(),
			    sb.toString(),
			    "Test Setup Error",
			    JOptionPane.ERROR_MESSAGE);
		}
		
		return result;
	}
	
	public static Boolean ValidateSiteToTest()
	{
		Boolean result = true; // false = invalid inputs
		
		// Get user input
		String url = AutoMater.text_SinglePageUrl.getText();
		
		try
		{
			// Convert string to URL
			AutoMater.url_SiteToTest = new URL(url);
		}
		catch (Exception ex)
		{
			// Malformed URL
			result = false;
		}
		
		return result;
	}
}