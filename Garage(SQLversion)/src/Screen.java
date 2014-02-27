import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Screen extends JFrame {
	
	JPanel create;
	JPanel home;
	JPanel display;
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
        setResizable(true);
        
        create = new JPanel();
        
        home = new JPanel();
        display = new JPanel();
        
        masterPanel.add(home, "Home");
        home.add(new JLabel("home"));
        JButton addButton = new JButton("Add New Vehicle");
        addButton.addActionListener( new SwitchStateButton(this, State.CREATE));
        home.add(addButton);
        masterPanel.add(create, "Create");
        create.add(new JLabel("create"));
        masterPanel.add(display, "Display");
        display.add(new JLabel("display"));
        
        add(masterPanel);
        
	}
	public void switchToCard(State state) {
		if(masterPanel == null) return;
		switch(state) {
			case HOME:
				((CardLayout) masterPanel.getLayout()).show(masterPanel, "Home");
				break;
			case CREATE:
				((CardLayout) masterPanel.getLayout()).show(masterPanel, "Create");
				break;
			case DISPLAY:
				((CardLayout) masterPanel.getLayout()).show(masterPanel, "Display");
				break;
			default:
				throw new IllegalArgumentException();
		}
	}
	public static enum State {
		HOME,
		CREATE,
		DISPLAY
	}
}
