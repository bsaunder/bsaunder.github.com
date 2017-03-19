import java.util.Calendar;
import java.util.prefs.Preferences;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

/*
 * Created on Dec 8, 2004
 * Created by Bryan Saunders
 */

public class CCURegHack {

	private static final String secNode = "OpenEditor/oelm/sec";
	private static Preferences prefs = Preferences.userRoot().node(secNode);
	
	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		prefs.putBoolean("active",true);
		prefs.putBoolean("trial",true);
		
		//Calendar today = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		end.set(Calendar.YEAR,Calendar.MAY,1);
		int endDay = end.get(Calendar.DAY_OF_YEAR);
		int endYear = end.get(Calendar.YEAR);
		
		//System.out.println("Today: "+today.get(Calendar.DAY_OF_YEAR)+"| May: "+endDay);
		
		prefs.putInt("e_day",endDay);
		prefs.putInt("e_year",endYear);
		prefs.put("key","CCUREGHACK");
		JOptionPane.showMessageDialog(null,"OpenEditor Trial Extended to May 1st, 2005","OpenEditor",JOptionPane.INFORMATION_MESSAGE);
	}
}
