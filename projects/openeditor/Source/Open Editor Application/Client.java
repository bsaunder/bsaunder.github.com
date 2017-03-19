/*
 * Created by Bryan Saunders
 * Created on Nov 17, 2004
 */
import javax.swing.UIManager;

public class Client {

	public static void main(String[] args) {
		// Set Look & Feel to System Default
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		// Security Check
		OELM s = new OELM("OpenEditor");
		//s.deactivate();
		s.attemptRun();
		
	}
}
