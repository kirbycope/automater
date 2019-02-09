package TabbedPaneComponents;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AccessibilityPanel
{
	public static JPanel CreateJPanel_Accessibility()
	{
		// Accessibility Panel
		JPanel panel_Accessibility = new JPanel();
		
		return panel_Accessibility;
	}
	
	public static JPanel CreateJPanel_Tests()
	{
		// Accessibility Tests Panel
		JPanel panel_AccessilibityTests = new JPanel();
		panel_AccessilibityTests.setLayout(new BoxLayout(panel_AccessilibityTests, BoxLayout.Y_AXIS));
		
			// Label: "Accessibility Tests"
			JLabel label_AccessilibityTests = new JLabel("Accessibility Tests");
			panel_AccessilibityTests.add(label_AccessilibityTests);
		
		return panel_AccessilibityTests;
	}
	
	public static JCheckBox CreateJCheckBox_AnchorTitle()
	{
		// CheckBox: "Check <a> 'title=' attribute(s)"
		JCheckBox chckbx_CheckAnchorTitle = new JCheckBox("Check <a> 'title=' attribute(s)");
		
		return chckbx_CheckAnchorTitle;
	}
	
	public static JCheckBox CreateJCheckBox_ImageAlt()
	{
		// CheckBox: "Check <img> 'alt=' attribute(s)"
			JCheckBox chckbx_CheckImageAlt = new JCheckBox("Check <img> 'alt=' attribute(s)");
			
			return chckbx_CheckImageAlt;
	}
}