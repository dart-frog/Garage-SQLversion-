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
        createCar = new JPanel(new MigLayout());
        createBoat = new JPanel(new MigLayout());
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
        create.add(carSelect);
        JButton boatSelect = new JButton("Boat");
		boatSelect.addActionListener(new SwitchStateButton(this, State.CREATEBOAT));
		create.add(boatSelect);
		
		//create car
		createCar = new JPanel(new MigLayout());
		createCar.add(new JLabel("Make"));
		JTextField makeField = new JTextField();
		createCar.add(makeField, "w 100%");
        createCar.add(new JLabel("Model"), "cell 0 2");
		JTextField modelField = new JTextField();
        createCar.add(modelField, "w 100%");
        createCar.add(new JLabel("Year"), "cell 0 3");
        JTextField yearField = new JTextField();
        createCar.add(yearField, "w 100%");
        ButtonGroup bGroup = new ButtonGroup();
		JRadioButton electricCarButton = new JRadioButton("Electric Car");
		bGroup.add(electricCarButton);
		createCar.add(electricCarButton, "cell 0 6"  );
		JRadioButton gasCarButton = new JRadioButton("Gas Car");
		bGroup.add(gasCarButton);	
		createCar.add(gasCarButton);
		createCar.add(new JLabel("efficiency"), "cell 0 4");
		JTextField efficiencyField = new JTextField();
		createCar.add(efficiencyField, "w 100%");
		createCar.add(new JLabel("Capacity"), "cell 0 5");
		JTextField capacityField = new JTextField();
		createCar.add(capacityField, "w 100%");
		JButton inputButton = new JButton("Add");
		inputButton.addActionListener(new CarActionListener(makeField, modelField, yearField, electricCarButton, gasCarButton,efficiencyField,capacityField)); 
		createCar.add(inputButton, "cell 0 7");
		masterPanel.add(createCar,"CreateCar");
		//Create boat
		createBoat.add(new JLabel("Make"), "cell 0 1");
		JTextField makeBField = new JTextField("");
		createBoat.add(makeBField, "w 100%");
		createBoat.add(new JLabel("Model"), "cell 0 2");
		JTextField modelBField = new JTextField("");   
		createBoat.add(modelBField, "w 100%");
		createBoat.add(new JLabel("Year"),"cell 0 3");
        JTextField yearBField = new JTextField("");
        createBoat.add(yearBField, " w 100%");
        createBoat.add(new JLabel("Range"), "cell 0 4");
        JTextField rangeField = new JTextField();
        createBoat.add(rangeField, "w 100%");
		JButton inputB = new JButton("Add");
		inputB.addActionListener(new BoatActionListener(makeBField, modelBField, yearBField, rangeField));
		createBoat.add(inputB);
		masterPanel.add(createBoat, "CreateBoat");
		masterPanel.add(display, "Display");
        display.add(new JLabel("display"));
        JButton deleteButton = new JButton("Delete");
        add(masterPanel);
        
	}
	public void addCreateComponents(JPanel jp){
		
        
        
        
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
				break;
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