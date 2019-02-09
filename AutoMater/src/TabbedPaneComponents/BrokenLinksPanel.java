package TabbedPaneComponents;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BrokenLinksPanel
{
	public static JPanel CreateJPanel_BrokenLinks()
	{
		// Broken Links Panel
		JPanel panel_BrokenLinks = new JPanel();
		
		return panel_BrokenLinks;
	}
	
	public static JPanel CreateJPanel_Tests()
	{
		// Broken Links Tests Panel
		JPanel panel_BrokenLinksTests = new JPanel();
		panel_BrokenLinksTests.setLayout(new BoxLayout(panel_BrokenLinksTests, BoxLayout.Y_AXIS));
		
			// Label: "Broken Links Tests"
			JLabel label_AccessilibityTests = new JLabel("Broken Links Tests");
			panel_BrokenLinksTests.add(label_AccessilibityTests);
		
		return panel_BrokenLinksTests;
	}
	
	public static JCheckBox CreateJCheckBox_AnchorHref()
	{
		// CheckBox: "Check <a> href= attribute(s)"
		JCheckBox chckbx_CheckAnchorHref = new JCheckBox("Check <a> href= attribute(s)");
		
		return chckbx_CheckAnchorHref;
	}
}