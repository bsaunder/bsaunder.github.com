/*
 * Created by Bryan Saunders
 * Created on Dec 4, 2004
 */
import javax.swing.UIManager;

public class Client {

	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		ManagerGUI mg = new ManagerGUI();
	}
}
