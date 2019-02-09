package TabbedPaneComponents;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JavaScriptPanel
{
	public static JPanel CreateJPanel_JavaScript()
	{
		// JavaScript Panel
		JPanel panel_JavaScript = new JPanel();
		
		return panel_JavaScript;
	}
	
	public static JPanel CreateJPanel_Tests()
	{
		// JavaScript Tests Panel
		JPanel panel_JavaScriptTests = new JPanel();
		panel_JavaScriptTests.setLayout(new BoxLayout(panel_JavaScriptTests, BoxLayout.Y_AXIS));
		
			// Label: "JavaScript Tests"
			JLabel label_JavaScriptTests = new JLabel("JavaScript Tests");
			panel_JavaScriptTests.add(label_JavaScriptTests);
			
		return panel_JavaScriptTests;
	}
	
	public static JCheckBox CreateJCheckBox_JsLint()
	{
		// CheckBox: "Check referenced <script>(s) against JSLint"
		JCheckBox chckbx_CheckReferencedScripts = new JCheckBox("Check referenced <script>(s) against JSLint");
		
		return chckbx_CheckReferencedScripts;
	}
}