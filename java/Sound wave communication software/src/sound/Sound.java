package sound;

import javax.swing.JFrame;

public class Sound extends JFrame {
	
	public Sound() { 
		setTitle("Sound wave communication software");
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
