import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;


public class PrintButtonActionListener implements ActionListener {
	private JTextField makeField;	
	private JTextField modelField;
	private JTextField yearField;
	private JRadioButton boatButton;
	private JRadioButton electricCarButton;
	private JRadioButton gasCarButton;
	
	public PrintButtonActionListener(JTextField makeField, JTextField modelField, JTextField yearField, JRadioButton boatButton, JRadioButton electricCarButton, JRadioButton gasCarButton){
		this.makeField = makeField;
		this.modelField = modelField;
		this.yearField = yearField;
		this.boatButton = boatButton;
		this.electricCarButton = electricCarButton;
		this.gasCarButton = gasCarButton;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		 String make = makeField.getText();
		 String model = modelField.getText();
		 int year = Integer.parseInt(yearField.getText());
		 int type = getType();
		 Connect stream = new Connect();
		 stream.addNewVehicle(make,model,year,type);
		
	}
	public int getType(){
		int type = 0;
		if (boatButton.isSelected()){
			type = 1;
		}
		if (electricCarButton.isSelected()){
			type =2;
		}
		if (gasCarButton.isSelected()){
			type =3;
		}
		return type;
	}

}
