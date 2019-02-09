package Application;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class MainFrame
{
	// Main Window (frame)
	public static JFrame CreateJFrame()
	{
		// Create and set up the window
		JFrame frame = new JFrame();
        frame.setTitle("AutoMater");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        
        return frame;
	}
}