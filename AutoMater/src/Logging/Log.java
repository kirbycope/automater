package Logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Application.AutoMater;

public class Log
{
	public static void Write(String content)
	{
		try
		{
			// Get UNIX time for file name
			long unixTime = System.currentTimeMillis() / 1000L;
			
			// Convert long to string
			String datePart =  ""+unixTime;
			
			// Create file path of new text file
			String pathName = AutoMater.text_LogLocation.getText() + "/" + datePart + ".txt";
			
			// Instantiate a new File object with the given file path
			File file = new File(pathName);
			
			// if file doesn't exists, then create it
			if (!file.exists())
			{
				file.createNewFile();
			}
			
			// Write content to new file
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}