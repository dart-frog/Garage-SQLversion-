import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;


public class BoatActionListener implements ActionListener {
	private JTextField makeField;	
	private JTextField modelField;
	private JTextField yearField;
	private JTextField rangeField;
	
	public BoatActionListener(JTextField makeField, JTextField modelField, JTextField yearField, JTextField rangeField){
		this.makeField = makeField;
		this.modelField = modelField;
		this.yearField = yearField;
		this.rangeField = rangeField;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		 String make = makeField.getText();
		 String model = modelField.getText();
		 //int year = Integer.parseInt(yearField.getText());
		 
		 int range = Integer.parseInt(rangeField.getText());
		 Connect stream = new Connect();
		 stream.addNewBoat(make,model,2002,range);
		
	}

}
