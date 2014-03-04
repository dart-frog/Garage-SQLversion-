import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class DeleteActionListener implements ActionListener{
	JTextField deleteField;
	public DeleteActionListener(JTextField deleteField){
			this.deleteField = deleteField;
		}
		
	public void actionPerformed(ActionEvent e) { 
			int id = Integer.parseInt(deleteField.getText());
			Connect stream = new Connect();
			stream.deleteVehicle(id);
	}
}

