import java.awt.CardLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;



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
		this.setTitle("Garage");
		cl = new CardLayout();
		masterPanel = new JPanel(cl);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        
        create = new JPanel(new MigLayout());
        
        home = new JPanel();
        display = new JPanel();
        
        masterPanel.add(home, "Home");
        home.add(new JLabel("home"));
        JButton addButton = new JButton("Add New Vehicle");
        addButton.addActionListener( new SwitchStateButton(this, State.CREATE));
        JTextArea garage = new JTextArea("garage");
        home.add(garage);
        home.add(addButton);
        masterPanel.add(create, "Create");
        create.add(new JLabel("create"), "cell 0 0, center align");
        JTextField makeField = new JTextField("make");
        create.add(makeField, "cell 0 1, w 100%");
        JTextField modelField = new JTextField("model");
        create.add(modelField, "cell 0 2, w 100%");
        JTextField yearField = new JTextField("year");
        create.add(yearField, "cell 0 3, w 100%");
		ButtonGroup bGroup = new ButtonGroup();
		JRadioButton boatButton = new JRadioButton("Boat");
		//boatButton.addActionListener(this);
		bGroup.add(boatButton);
		create.add(boatButton, "cell 0 4");
		
		JRadioButton electricCarButton = new JRadioButton("Electric Car");
		//electricCarButton .addActionListener(this);
		bGroup.add(electricCarButton );
		create.add(electricCarButton );
		
		JRadioButton GasCarButton = new JRadioButton("Gas Car");
		//GasCarButton.addActionListener(this);
		bGroup.add(GasCarButton);	
		create.add(GasCarButton);
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
