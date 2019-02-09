package TabbedPaneComponents;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SeoPanel
{
	public static  JPanel CreateJPanel_Seo()
	{
		// SEO Panel
		JPanel panel_Seo = new JPanel();
		
		return panel_Seo;
	}
	
	public static JPanel CreateJPanel_Tests()
	{
		// SEO Tests Panel
		JPanel panel_SeoTests = new JPanel();
		panel_SeoTests.setLayout(new BoxLayout(panel_SeoTests, BoxLayout.Y_AXIS));
		
		// Label: "SEO Tests"
		JLabel label_SeoTests = new JLabel("SEO Tests");
		panel_SeoTests.add(label_SeoTests);
	
	return panel_SeoTests;
	}
	
	public static JCheckBox CreateJCheckBox_SeoTitle()
	{
		// CheckBox: "Check for <meta> title"
		JCheckBox chckbx_CheckSeoTitle = new JCheckBox("Check for <meta> title");
		
		return chckbx_CheckSeoTitle;
	}
	
	public static JCheckBox CreateJCheckBox_SeoDescription()
	{
		// CheckBox: "Check for <meta> title"
		JCheckBox chckbx_CheckSeoDescription = new JCheckBox("Check for <meta> description");
		
		return chckbx_CheckSeoDescription;
	}
	
	public static JCheckBox CreateJCheckBox_XmlSitemap()
	{
		// Checkbox: "Check for XML sitemap"
		JCheckBox chckbx_CheckXmlSitemap = new JCheckBox("Check for XML sitemap");
		
		return chckbx_CheckXmlSitemap;
	}
}