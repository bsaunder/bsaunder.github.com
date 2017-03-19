/*
 * Created by Bryan Saunders
 * Created on Dec 4, 2004
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.prefs.Preferences;

import javax.swing.JOptionPane;

public class OELM {
	
	// Dev Key: DEV15-11111-ORION-11111-CCU04
	// Trial Key: TRIAL-11111-TIMEL-11111-CCU04
	
	private static final String trialKey = "TRIAL-11111-TIMEL-11111-CCU04";
	
	private static final String user = "bryansrem";
	private static final String pass = "abc123";
	private static final String url = "65.17.233.160";
	private static final String db = "security1";
	
	private static final String secNode = "OpenEditor/oelm/sec";
	private Preferences prefs = Preferences.userRoot().node(secNode);
	
	private String sysName;
	
	private Connection con = null;

	public OELM(String name){ sysName = name; }
	
	private boolean isActivated(){
		return prefs.getBoolean("active",false);
	}
	
	public void attemptRun(){
		if(isActivated()){
			// Instantiate Editor
			System.out.println("ACTIVATION FOUND");
			Editor main = new Editor(sysName); // Construct Editor
		}else if(isTrial()){
			System.out.println("TRIAL FOUND");
			Calendar current = Calendar.getInstance();
			Calendar start = Calendar.getInstance();
			start.set(Calendar.DAY_OF_YEAR,prefs.getInt("s_day",99));
			start.set(Calendar.YEAR,prefs.getInt("s_year",99));
			Calendar end = Calendar.getInstance();
			end.set(Calendar.DAY_OF_YEAR,prefs.getInt("e_day",99));
			end.set(Calendar.YEAR,prefs.getInt("e_year",99));
			if(current.after(start) && current.before(end)){
				JOptionPane.showMessageDialog(null,"Starting OpenEditor Trial");
				Editor main = new Editor(sysName); // Construct Editor
			}else{
				promptTrialEnd();
				deactivate();
				System.exit(0);
			}

		}else{
			// Program Not Activated
			switch(promptInvalid()){
				case 0:
					// Enter Key
					try{
						String key = promptForKey();
						if(key == null){System.exit(0);}
						Class.forName("com.mysql.jdbc.Driver").newInstance();
				    	con = DriverManager.getConnection("jdbc:mysql://"+url+"/"+db, user, pass);
				    	System.out.println("FOUND ACTIVATION SERVER");
						if(key.equals(trialKey)){
							doTrialRun();
							incrementKeyUses(key);
							con.close();
							Editor main = new Editor(sysName); // Construct Editor
						}else{
					    	doKeyCheck(key);
						}
					}catch(Exception e){
						promptActivationError();
						e.printStackTrace();
						System.exit(0);
					}
					break;
				case 1:
					// Purchase
					try{
						BrowserLauncher.openURL("http://www.openeditor.org/order.html");
					}catch(IOException e){
						System.exit(0);
					}
					System.exit(0);
					break;
				default:
					// Quit
					System.exit(0);
			}
		}
	}
	
	private void doTrialRun(){
		promptTrialStart();
		prefs.putBoolean("trial",false);
		prefs.putBoolean("trial",true);
		// Write Start Date
		Calendar current = Calendar.getInstance();
		int curDay = current.get(Calendar.DAY_OF_YEAR);
		int curYear = current.get(Calendar.YEAR);
		prefs.putInt("s_day",curDay);
		prefs.putInt("s_year",curYear);
		int endDay = curDay + 10;
		int endYear = curYear;
		if(endDay > 365){
			endYear++;
			endDay = endDay - 365;
		}
		prefs.putInt("e_day",endDay);
		prefs.putInt("e_year",endYear);
	}
	
	private boolean isTrial(){
		return prefs.getBoolean("trial",false);
	}
		
	private void doKeyCheck(String key) throws SQLException{
		if(isKeyValid(key)){
			if(canUseKey(key)){
				if(incrementKeyUses(key)){
					activate(key);
					System.out.println("CLOSING CONNECTION");
					con.close();
					Editor main = new Editor(sysName); // Construct Editor
				}else{
					System.out.println("CLOSING CONNECTION");
					con.close();
					System.exit(0);
				}
			}else{
				promptMaxUses();
				System.out.println("CLOSING CONNECTION");
				con.close();
				System.exit(0);
			}
		}else{
			System.out.println("CLOSING CONNECTION");
			con.close();
			System.exit(0);
		}
	}
	
	public void deactivate(){
		prefs.putBoolean("active",false);
		prefs.putBoolean("trial",false);
		prefs.put("key","");
	}
	
	private void activate(String key){
		System.out.println("ACTIVATING PROGRAM");
		prefs.putBoolean("active",true);
		prefs.put("key",key);
	}
	
	private int promptInvalid(){
		String invalid = "To Use OpenEditor, you Must Activate it. Please Choose an Option Below";
		return JOptionPane.showOptionDialog(null,invalid,"OpenEditor Not Activated",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,new Object[]{"Enter Key","Purchase","Quit"},"Purchase");
	}
	
	private String promptForKey(){
		return JOptionPane.showInputDialog(null,"Please Enter your 25 Digit Activation Code with Dashes","Enter Activation Key",JOptionPane.QUESTION_MESSAGE);
	}
	
	private boolean isKeyValid(String key){
		System.out.println("CHECKING KEY: '"+key+"'");
	    try {
    		Statement sql = con.createStatement();
    		ResultSet results = sql.executeQuery("SELECT * FROM licenses WHERE license = '"+key+"'");
    		ResultSetMetaData metaData = results.getMetaData();
    		results.last();
    		int rows = results.getRow(); // Return Row Number
	        if(rows == 1){
	        	System.out.println("KEY VALID");
	        	return true;
	        }else{
	        	System.out.println("KEY NOT FOUND");
	        	promptInvalidKey();
	        	return false;
	        }
	    }catch(Exception e){
	    	JOptionPane.showMessageDialog(null,"Error Occured While Activating Key. Key Not Activated","Error",JOptionPane.ERROR_MESSAGE);
	    	e.printStackTrace();
	    	return false;
	    }
	}
	
	private boolean canUseKey(String key){
		System.out.println("CHECKING KEY USES...");
	    try {
    		Statement sql = con.createStatement();
    		ResultSet results = sql.executeQuery("SELECT * FROM licenses WHERE license = '"+key+"'");
    		ResultSetMetaData metaData = results.getMetaData();
    		results.last();
    		int rows = results.getRow(); // Return Row Number
	        if(rows == 1){
	        	int uses = results.getInt(4);
	        	int allo = results.getInt(5);
	        	System.out.println("KEY STATS: "+uses+" - "+allo);
	        	int left = allo - uses;
	        	if(left >= 1){
	        		System.out.println("KEY USE ALLOWED");
	        		return true;
	        	}else{
	        		System.out.println("CAN NOT USE KEY");
	        		return false;
	        	}
	        }else{
	        	System.out.println("KEY NOT FOUND");
	        	promptInvalidKey();
	        	return false;
	        }
	    }catch(Exception e){
	    	JOptionPane.showMessageDialog(null,"Error Occured While Activating Key. Key Not Activated","Error",JOptionPane.ERROR_MESSAGE);
	    	e.printStackTrace();
	    	return false;
	    }
	}
	
	private boolean incrementKeyUses(String key){
		System.out.println("INCREMENTING KEY USES...");
	    try {
    		Statement sql = con.createStatement();
    		int results = sql.executeUpdate("UPDATE licenses SET used = used+1 WHERE license = '"+key+"'");
    		if(results == 1){
    			System.out.println("KEY INCREMENTED");
        		return true;
    		}else{
    			System.out.println("COULD NOT INCREMENT");
    			throw new SQLException();
    		}
	    }catch(Exception e){
	    	promptActivationError();
	    	e.printStackTrace();
	    	return false;
	    }
	}
	
	private void promptInvalidKey(){
		JOptionPane.showMessageDialog(null,"The Key you Entered was Invalid","Error",JOptionPane.ERROR_MESSAGE);
	}
	
	private void promptActivationError(){
		JOptionPane.showMessageDialog(null,"Error Occured While Activating Key. Key Not Activated","Error",JOptionPane.ERROR_MESSAGE);
	}
	
	private void promptMaxUses(){
		JOptionPane.showMessageDialog(null,"This Key has already been Activated.\nPlease Purchase a New Key or Contact Support to Have your Key Reset","Error",JOptionPane.ERROR_MESSAGE);
	}
	
	private void promptTrialStart(){
		JOptionPane.showMessageDialog(null,"Your 10 Day OpenEditor Trial has been Acitvated.","Trial Activation",JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void promptTrialEnd(){
		JOptionPane.showMessageDialog(null,"Your 10 Day OpenEditor Trial has Expired.","Trial Expired",JOptionPane.ERROR_MESSAGE);
	}
}
