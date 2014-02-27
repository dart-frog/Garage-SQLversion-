

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwitchStateButton  implements ActionListener{
	Screen scr;
	Screen.State state;
	
	public SwitchStateButton(Screen s, Screen.State state){
		this.state = state;
		scr = s;
	}
	
	public void actionPerformed(ActionEvent e) { 
		scr.switchToCard(state); 
	}
}