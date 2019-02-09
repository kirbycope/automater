package TabbedPaneComponents;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class W3cPanel
{
	public static JPanel CreateJPanel_W3c()
	{
		// W3c Panel
		JPanel panel_W3c = new JPanel();
		
		return panel_W3c;
	}
	
	public static JPanel CreateJPanel_Tests()
	{
		// W3c Tests Panel
		JPanel panel_W3cTests = new JPanel();
		panel_W3cTests.setLayout(new BoxLayout(panel_W3cTests, BoxLayout.Y_AXIS));
		
			// Label: "W3c Tests"
			JLabel label_W3cTests = new JLabel("W3c Tests");
			panel_W3cTests.add(label_W3cTests);
		
		return panel_W3cTests;
	}
	
	public static JCheckBox CreateJCheckBox_HtmlValidator()
	{
		// CheckBox: "Check HTML Markup"
		JCheckBox chckbx_CheckHtmlMarkup = new JCheckBox("Check HTML Markup");
		
		return chckbx_CheckHtmlMarkup;
	}
	
	public static JCheckBox CreateJCheckBox_CssValidator()
	{
		// CheckBox: "Check CSS"
		JCheckBox chckbx_CheckCss = new JCheckBox("Check CSS");
		
		return chckbx_CheckCss;
	}
}