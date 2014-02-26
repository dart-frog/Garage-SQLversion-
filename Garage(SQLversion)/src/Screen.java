import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Screen extends JFrame {
	
	JPanel create;
	JPanel home;
	JPanel masterPanel;
	CardLayout cl;
	public static void main(String[] args) {
		Screen scr = new Screen();
		scr.setUp();
	}
	public void setUp(){
		Screen scr = new Screen();
		scr.pack();
		scr.setVisible(true);
	}
	public Screen(){
		cl = new CardLayout();
		masterPanel = new JPanel(cl);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        create = new JPanel();
	}
}
