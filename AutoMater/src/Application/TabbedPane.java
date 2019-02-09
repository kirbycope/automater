package Application;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

import TabbedPaneComponents.AccessibilityPanel;
import TabbedPaneComponents.BrokenLinksPanel;
import TabbedPaneComponents.CompatibilityPanel;
import TabbedPaneComponents.HomePanel;
import TabbedPaneComponents.JavaScriptPanel;
import TabbedPaneComponents.SeoPanel;
import TabbedPaneComponents.W3cPanel;

public class TabbedPane
{
	public static JTabbedPane CreateTabbedPanes()
	{
		// TabbedPane
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
			// Panel for Home tab
			AutoMater.panel_Home = HomePanel.CreateJPanel_Home();
			tabbedPane.addTab("Home", null, AutoMater.panel_Home, null);
			
				// Panel for Home - URL
				AutoMater.panel_Url = HomePanel.CreateJPanel_Url();
				AutoMater.panel_Home.add(AutoMater.panel_Url);
					
					// Panel for Single Page URL
					AutoMater.panel_SinglePageUrl = HomePanel.CreateJPanel_SinglePageUrl();
					AutoMater.panel_Url.add(AutoMater.panel_SinglePageUrl);
					
						// Input field for Single Page URL
						AutoMater.text_SinglePageUrl = HomePanel.CreateTextJField_URL();
						AutoMater.panel_Url.add(AutoMater.text_SinglePageUrl);
				
				// Panel for Home - Log
				AutoMater.panel_Log = HomePanel.CreateJPanel_Log();
				AutoMater.panel_Home.add(AutoMater.panel_Log);
					
					// Panel for Home - Log Location
					AutoMater.panel_LogFolder = HomePanel.CreateJPanel_LogFolder();
					AutoMater.panel_Log.add(AutoMater.panel_LogFolder);
					
						// Input field for Log Location
						AutoMater.text_LogLocation = HomePanel.CreateJTextField_Log();
						AutoMater.panel_LogFolder.add(AutoMater.text_LogLocation);
						
						// Button for Log Location
						JButton btn_ChooseFolder = HomePanel.CreateJButton_ChooseFolder();
						AutoMater.panel_LogFolder.add(btn_ChooseFolder);
				
			// Panel for Accessibility tab
			AutoMater.panel_Accessibility = AccessibilityPanel.CreateJPanel_Accessibility();
			tabbedPane.addTab("Accessibility", null, AutoMater.panel_Accessibility, null);
			
				// Panel for Accessibility - Tests
				AutoMater.panel_AccessilibityTests = AccessibilityPanel.CreateJPanel_Tests();
				AutoMater.panel_Accessibility.add(AutoMater.panel_AccessilibityTests);
				
					// CheckBox for "Check <a> 'title=' attribute(s)"
					AutoMater.chckbx_CheckAnchorTitle = AccessibilityPanel.CreateJCheckBox_AnchorTitle();
					AutoMater.panel_AccessilibityTests.add(AutoMater.chckbx_CheckAnchorTitle);
					
					// CheckBox for "Check <img> 'alt=' attribute(s)"
					AutoMater.chckbx_CheckImageAlt = AccessibilityPanel.CreateJCheckBox_ImageAlt();
					AutoMater.panel_AccessilibityTests.add(AutoMater.chckbx_CheckImageAlt);
					
			// Panel for Broken Links
			AutoMater.panel_BrokenLinks = BrokenLinksPanel.CreateJPanel_BrokenLinks();
			tabbedPane.addTab("Broken Links", null, AutoMater.panel_BrokenLinks, null);
			
				// Panel for Broken Links - Tests
				AutoMater.panel_BrokenLinksTests = BrokenLinksPanel.CreateJPanel_Tests();
				AutoMater.panel_BrokenLinks.add(AutoMater.panel_BrokenLinksTests);
				
					// CheckBox for "Check <a> href= attribute(s)"
					AutoMater.chckbx_CheckAnchorHref = BrokenLinksPanel.CreateJCheckBox_AnchorHref();
					AutoMater.panel_BrokenLinksTests.add(AutoMater.chckbx_CheckAnchorHref);
		
			// Panel for Compatibility
			AutoMater.panel_Compatibility = CompatibilityPanel.CreateJPanel_Compatibility();
			tabbedPane.addTab("Compatibility", null, AutoMater.panel_Compatibility);
			
				// Panel for Compatibility - Tests
				AutoMater.panel_CompatibilityTests = CompatibilityPanel.CreateJPanel_Tests();
				AutoMater.panel_Compatibility.add(AutoMater.panel_CompatibilityTests);
				
					// CheckBox for "Check source code for HTML5/CSS3 attributes."
					AutoMater.chckbx_CheckCompatibility = CompatibilityPanel.CreateJCheckBox_Compatibility();
					AutoMater.panel_CompatibilityTests.add(AutoMater.chckbx_CheckCompatibility);
			
			// Panel for JavaScript
			AutoMater.panel_JavaScript = JavaScriptPanel.CreateJPanel_JavaScript();
			tabbedPane.addTab("JavaScript", null, AutoMater.panel_JavaScript, null);
			
				// Panel for JavaScript - Tests
				AutoMater.panel_JavaScriptTests = JavaScriptPanel.CreateJPanel_Tests();
				AutoMater.panel_JavaScript.add(AutoMater.panel_JavaScriptTests);
				
					// CheckBox for "Check referenced <scripts> against JSLint"
					AutoMater.chckbx_CheckReferencedScripts = JavaScriptPanel.CreateJCheckBox_JsLint();
					AutoMater.panel_JavaScriptTests.add(AutoMater.chckbx_CheckReferencedScripts);
					
			// Panel for SEO
			AutoMater.panel_Seo = SeoPanel.CreateJPanel_Seo();
			tabbedPane.addTab("SEO", null, AutoMater.panel_Seo, null);
			
				// Panel for SEO - Tests
				AutoMater.panel_SeoTests = SeoPanel.CreateJPanel_Tests();
				AutoMater.panel_Seo.add(AutoMater.panel_SeoTests);
				
					// CheckBox for "Check for <meta> title"
					AutoMater.chckbx_CheckSeoTitle = SeoPanel.CreateJCheckBox_SeoTitle();
					AutoMater.panel_SeoTests.add(AutoMater.chckbx_CheckSeoTitle);
					
					// CheckBox for "Check for <meta> description"
					AutoMater.chckbx_CheckSeoDescription = SeoPanel.CreateJCheckBox_SeoDescription();
					AutoMater.panel_SeoTests.add(AutoMater.chckbx_CheckSeoDescription);
					
					// AutoMater.chckbx_CheckXmlSitemap
					AutoMater.chckbx_CheckXmlSitemap = SeoPanel.CreateJCheckBox_XmlSitemap();
					AutoMater.panel_SeoTests.add(AutoMater.chckbx_CheckXmlSitemap);
					
			// Panel for W3C
			AutoMater.panel_W3c = W3cPanel.CreateJPanel_W3c();
			tabbedPane.addTab("W3C", null, AutoMater.panel_W3c, null);
			
				// Panel for W3C -Tests
				AutoMater.panel_W3cTests = W3cPanel.CreateJPanel_Tests();
				AutoMater.panel_W3c.add(AutoMater.panel_W3cTests);
				
					// CheckBox for "Check HTML Markup"
					AutoMater.chckbx_CheckHtmlMarkup = W3cPanel.CreateJCheckBox_HtmlValidator();
					AutoMater.panel_W3cTests.add(AutoMater.chckbx_CheckHtmlMarkup);
					
					// CheckBox for "Check CSS"
					AutoMater.chckbx_CheckCss = W3cPanel.CreateJCheckBox_CssValidator();
					AutoMater.panel_W3cTests.add(AutoMater.chckbx_CheckCss);
		
		return tabbedPane;
	}
}