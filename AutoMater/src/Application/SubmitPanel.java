package Application;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Tests.AccessibilityTests;
import Tests.BrokenLinksTests;
import Tests.CompatibilityTests;
import Tests.JavaScriptTests;
import Tests.SeoTests;
import Tests.W3cTests;

public class SubmitPanel
{
	public static JPanel CreateJPanel_Submit()
	{
		// Submit Panel
		JPanel panel_Submit = new JPanel();
		
			// Submit button
			JButton btn_Submit = new JButton("Submit");
			panel_Submit.add(btn_Submit);
			// Add an event listener to the JButton
			btn_Submit.addActionListener
			(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						// Validate minimum inputs
						Boolean result = Validations.UserInputValidation.ValidateInputs();
						
						if (result)
						{
							try
							{
								// Run selected Accessibility test(s)
								AccessibilityTests.CheckSelections();
								
								// Run selected Broken Links test(s)
								BrokenLinksTests.CheckSelections();
								
								// Run selected Compatibility test(s)
								CompatibilityTests.CheckSelections();
								
								// Run selected JavaScript test(s)
								JavaScriptTests.CheckSelections();
								
								// Run selected SEO test(s)
								SeoTests.CheckSelections();
								
								// Run selected W3C test(s)
								W3cTests.CheckSelections();
								
								// TODO: Run other tests (after they are proofed out)
								
								// TODO: Implement Progress Monitoring
								JOptionPane.showMessageDialog(new Frame(),
									    "Your selected tests have finished running.",
									    "Test Complete",
									    JOptionPane.PLAIN_MESSAGE);
							}
							catch (Exception ex)
							{
								// Display Error(s) to User
								JOptionPane.showMessageDialog(new Frame(),
										"Exception: " + ex,
										"Errors Occurred!",
									    JOptionPane.PLAIN_MESSAGE);
							}
						}
					}
				}
			);
			
		return panel_Submit;
	}
}