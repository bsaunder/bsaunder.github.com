import java.util.prefs.Preferences;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

/*
 * Created on Dec 8, 2004
 * Created by Bryan Saunders
 */

public class Deactivator {

	private static final String secNode = "OpenEditor/oelm/sec";
	private static Preferences prefs = Preferences.userRoot().node(secNode);
	
	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		prefs.putBoolean("active",false);
		prefs.putBoolean("trial",false);
		prefs.put("key","");
		JOptionPane.showMessageDialog(null,"OpenEditor Deactivated","OpenEditor",JOptionPane.INFORMATION_MESSAGE);
	}
}
