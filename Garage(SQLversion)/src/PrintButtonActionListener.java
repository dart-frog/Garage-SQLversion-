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
	private JRadioButton elecrticCarButton;
	private JRadioButton gasCarButton;
	
	public PrintButtonActionListener(JTextField makeField, JTextField modelField, JTextField yearField, JRadioButton boatButton, JRadioButton electricCarButton, JRadioButton gasCarButton){
		this.makeField = makeField;
		this.modelField = modelField;
		this.jTB = jTB;
		this.jTA = jTA;
		this.jRB1=jRB1;
		this.jRB2=jRB2;
		this.jRB3=jRB3;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		 System.out.println("Check Box 1: " + Boolean.toString(jCB1.isSelected()));
		 System.out.println("Check Box 2: " + Boolean.toString(jCB2.isSelected()));
		 System.out.println("Radio " + radio());
		 System.out.println("Toggle Button: " + Boolean.toString(jTB.isSelected()));
		 System.out.println("Text: " + jTA.getText());
	}
	public int radio(){
		int radio = 0;
		if (jRB1.isSelected()){
			radio = 1;
		}
		if (jRB2.isSelected()){
			radio =2;
		}
		if (jRB3.isSelected()){
			radio =3;
		}
		return radio;
	}

}
