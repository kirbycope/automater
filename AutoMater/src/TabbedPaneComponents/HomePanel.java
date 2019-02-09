package TabbedPaneComponents;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Application.AutoMater;

public class HomePanel
{
	public static JPanel CreateJPanel_Home()
	{
		// Home Panel
		JPanel panel_Home = new JPanel();
		panel_Home.setLayout(new GridLayout(2,1));
		
		return panel_Home;
	}
	
	public static JPanel CreateJPanel_Url()
	{
		// URL Panel
		JPanel panel_Url = new JPanel();
			
			// Label: "Site to Test"
			JLabel label_Site = new JLabel("Site to Test:");
			panel_Url.add(label_Site);
		
		return panel_Url;
	}
	
	public static JPanel CreateJPanel_SinglePageUrl()
	{
		// Single Page URL Panel
		JPanel panel_SinglePageUrl = new JPanel();
		
		return panel_SinglePageUrl;
	}
	
	public static JTextField CreateTextJField_URL()
	{
		// Input field for Single Page URL
		JTextField text_SinglePageUrl = new JTextField();
		text_SinglePageUrl.setColumns(31);
		
		return text_SinglePageUrl;
	}
	
	public static JPanel CreateJPanel_Log()
	{
		// Log Panel
		JPanel panel_Log = new JPanel();
		
			// Label: "Logging Location"
			JLabel label_log = new JLabel("Log Location:");
			panel_Log.add(label_log);
						
		return panel_Log;
	}
	
	public static JPanel CreateJPanel_LogFolder()
	{
		// Folder Location Panel
		JPanel panel_LogFolder = new JPanel();
		panel_LogFolder.setLayout(new FlowLayout());
		
		return panel_LogFolder;
	}
	
	public static JTextField CreateJTextField_Log()
	{
		// Input field for Log Location
		JTextField text_LogLocation = new JTextField();
		text_LogLocation.setColumns(20);
		text_LogLocation.setEnabled(false);
		
		return text_LogLocation;
	}
	
	public static JButton CreateJButton_ChooseFolder()
	{
		JButton btn_ChooseFolder = new JButton("Choose Folder");
			// Add an event listener to the JButton
			btn_ChooseFolder.addActionListener
			(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						// Open file chooser
						JFileChooser chooser = new JFileChooser();
						chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						int returnValue = chooser.showOpenDialog(null);
						
						// Return value if approve (yes, ok) is chosen.
						if (returnValue == JFileChooser.APPROVE_OPTION)
						{
			                java.io.File f = chooser.getSelectedFile();
			                AutoMater.text_LogLocation.setText(f.getPath());
			            }
					}
				}
			);
		
		return btn_ChooseFolder;
	}
}