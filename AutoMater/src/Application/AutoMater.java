package Application;
import java.io.File;
import java.net.URL;

import javax.swing.*;

public class AutoMater
{
	// Publicly Accessible Variables
	// Begin Window Frames, Panes, Panels, etc.
	public static JFrame frame;
		public static JTabbedPane tabbedPane;
			public static JPanel panel_Home;
				public static JPanel panel_Url;
					public static JPanel panel_SinglePageUrl;
						public static JTextField text_SinglePageUrl;
				public static JPanel panel_Log;
					public static JPanel panel_LogFolder;
						public static JTextField text_LogLocation;
			public static JPanel panel_Accessibility;
				public static JPanel panel_AccessilibityTests;
					public static JCheckBox chckbx_CheckAnchorTitle;
					public static JCheckBox chckbx_CheckImageAlt;
			public static JPanel panel_BrokenLinks;
				public static JPanel panel_BrokenLinksTests;
					public static JCheckBox chckbx_CheckAnchorHref;
			public static JPanel panel_Compatibility;
				public static JPanel panel_CompatibilityTests;
					public static JCheckBox chckbx_CheckCompatibility;
			public static JPanel panel_JavaScript;
				public static JPanel panel_JavaScriptTests;
					public static JCheckBox chckbx_CheckReferencedScripts;
			public static JPanel panel_Seo;
				public static JPanel panel_SeoTests;
					public static JCheckBox chckbx_CheckSeoTitle;
					public static JCheckBox chckbx_CheckSeoDescription;
					public static JCheckBox chckbx_CheckXmlSitemap;
			public static JPanel panel_W3c;
				public static JPanel panel_W3cTests;
					public static JCheckBox chckbx_CheckHtmlMarkup;
					public static JCheckBox chckbx_CheckCss;
		public static JPanel panel_Submit;
	// Begin variables for tests
	public static URL url_SiteToTest;
	
	// Create the GUI and show it. For thread safety, this method should be invoked from the event-dispatching thread.
	private static void createAndShowGUI()
    {
        // Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        // Create and set up the main window.
        frame = MainFrame.CreateJFrame();

	        // Create tabs and add to the main window
	        tabbedPane = TabbedPane.CreateTabbedPanes();
	        frame.getContentPane().add(tabbedPane);
	        
	        // Add Submit panel to the main window
	        panel_Submit = SubmitPanel.CreateJPanel_Submit();
	        frame.getContentPane().add(panel_Submit);
	        
	        // DEBUGGING - Saves time entering values
	        text_SinglePageUrl.setText("http://www.google.com");
	        text_LogLocation.setText(System.getProperty("user.home")+File.separator+"Documents/AutoMater/Logs");
	        
        // Display the main window
	    // frame.pack();
        frame.setVisible(true);
    }
	
	public static void main(String[] args)
	{
        //Schedule a job for the event-dispatching thread: creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater
		(
			new Runnable()
			{
				public void run()
				{
					createAndShowGUI();
				}
			}
		);
    }
}