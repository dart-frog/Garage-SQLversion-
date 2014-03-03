import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;


public class CarActionListener implements ActionListener {
	private JTextField makeField;	
	private JTextField modelField;
	private JTextField yearField;
	private JTextField efficiencyField;
	private JTextField capacityField;
	private JRadioButton electricCarButton;
	private JRadioButton gasCarButton;
	
	public CarActionListener(JTextField makeField, JTextField modelField, JTextField yearField, JRadioButton electricCarButton, JRadioButton gasCarButton, JTextField efficiencyField, JTextField capacityField){
		this.makeField = makeField;
		this.modelField = modelField;
		this.yearField = yearField;
		this.electricCarButton = electricCarButton;
		this.gasCarButton = gasCarButton;
		this.efficiencyField = efficiencyField;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		 String make = makeField.getText();
		 String model = modelField.getText();
		 int year = Integer.parseInt(yearField.getText());
		 int type = getType();
		 double efficiency = Double.parseDouble(efficiencyField.getText());
		 double capacity = Double.parseDouble(efficiencyField.getText());
		 Connect stream = new Connect();
		 stream.addNewCar(make,model,year,type,efficiency,capacity);
		 
		
	}
	public int getType(){
		int type = 0;
		if (electricCarButton.isSelected()){
			type =2;
		}
		if (gasCarButton.isSelected()){
			type =3;
		}
		return type;
	}

}
