package TabbedPaneComponents;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CompatibilityPanel
{
	public static JPanel CreateJPanel_Compatibility()
	{
		// Compatibility Panel
		JPanel panel_Compatibility = new JPanel();
		
		return panel_Compatibility;
	}
	
	public static JPanel CreateJPanel_Tests()
	{
		// Compatibility Tests Panel
		JPanel panel_CompatibilityTests = new JPanel();
		panel_CompatibilityTests.setLayout(new BoxLayout(panel_CompatibilityTests, BoxLayout.Y_AXIS));
		
			// Label: "Compatibility Tests"
			JLabel label_CompatibilityTests = new JLabel("Compatibility Tests");
			panel_CompatibilityTests.add(label_CompatibilityTests);
		
		return panel_CompatibilityTests;
	}
	
	public static JCheckBox CreateJCheckBox_Compatibility()
	{
		// CheckBox: "Check source code for HTML5/CSS3 attributes"
		JCheckBox chckbx_Compatibility = new JCheckBox("Check source code for HTML5/CSS3 attributes");
		
		return chckbx_Compatibility;
	}
}