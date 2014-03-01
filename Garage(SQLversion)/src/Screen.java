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
	Connect stream = new Connect();
	JPanel create;
	JPanel createCar;
	JPanel createBoat;
	JPanel home;
	JPanel display;
	JPanel masterPanel;
	CardLayout cl;
	public static void main(String[] args) {
		Screen scr = new Screen();
		scr.setUp();
	}
	public void setUp(){
		this.pack();
		this.setVisible(true);
	}
	public Screen(){
		stream.getConnection();
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
        Connect stream = new Connect();
        JTextArea garage = new JTextArea(stream.retrieve());
        home.add(garage);
        home.add(addButton);
        masterPanel.add(create, "Create");
        create.add(new JLabel("Would you like to add a Car or Boat"), "cell 0 0, center align");
        masterPanel.add(createCar, "CreateCar");
        masterPanel.add(createBoat, "Createboat");
        JButton carSelect = new JButton("Car");
        carSelect.addActionListener(new SwitchStateButton(this, State.CREATECAR));
        
        JButton boatSelect = new JButton();
		
        masterPanel.add(display, "Display");
        display.add(new JLabel("display"));
        
        add(masterPanel);
        
	}
	public void addCreateComponents(JPanel jp){
		JTextField makeField = new JTextField("make");
		jp.add(makeField, "cell 0 1, w 100%");
        JTextField modelField = new JTextField("model");
        jp.add(modelField, "cell 0 2, w 100%");
        JTextField yearField = new JTextField("year");
        jp.add(yearField, "cell 0 3, w 100%");
		ButtonGroup bGroup = new ButtonGroup();
		JRadioButton boatButton = new JRadioButton("Boat");
		//boatButton.addActionListener(this);
		bGroup.add(boatButton);
		jp.add(boatButton, "cell 0 4");
		
		JRadioButton electricCarButton = new JRadioButton("Electric Car");
		//electricCarButton .addActionListener(this);
		bGroup.add(electricCarButton );
		jp.add(electricCarButton );
		
		JRadioButton gasCarButton = new JRadioButton("Gas Car");
		//GasCarButton.addActionListener(this);
		bGroup.add(gasCarButton);	
		jp.add(gasCarButton);
		JButton inputButton = new JButton("Add");
		inputButton.addActionListener(new PrintButtonActionListener(makeField, modelField, yearField, boatButton, electricCarButton, gasCarButton)); 
		jp.add(inputButton);
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
			case CREATECAR:
				((CardLayout) masterPanel.getLayout()).show(masterPanel, "CreateCar");
				break;
			case CREATEBOAT:
				((CardLayout) masterPanel.getLayout()).show(masterPanel, "CreateBoat");
			default:
				throw new IllegalArgumentException();
		}
	}
	public static enum State {
		HOME,
		CREATE,
		DISPLAY,
		CREATECAR,
		CREATEBOAT
	}
}