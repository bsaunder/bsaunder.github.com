/*
 * Created by Bryan Saunders
 * Created on Dec 4, 2004
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
public class KeyManager {
	
	// Dev Key: DEV15-11111-ORION-11111-CCU04
	
	private static final long serialVersionUID = 1L;
	private static final String user = "bryansrem";
	private static final String pass = "abc123";
	private static final String url = "65.17.233.160";
	private static final String db = "security1";
	
	private JFrame parent;
	private JTextArea textArea;

	private Random r = new Random();
	
	private Connection con = null;
	
	private final int SET_SIZE = 5;
	private final int KEY_SETS = 5;
	
	public KeyManager(JFrame par,JTextArea ta){ 
		this.parent = par;
		this.textArea = ta;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
	    	con = DriverManager.getConnection("jdbc:mysql://"+url+"/"+db, user, pass);
	    	textArea.append("CONNECTED TO DATABASE");
		}catch(Exception e){
			textArea.append("COULD NOT CONNECT TO DATABASE");
			promptConnError(e.getClass().toString(),e.getStackTrace());
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	
	public void isKeyValid(){
		String key = promptForKey();
		if(key != null){
			if(isKeyValid(key)){
				textArea.append("\nKEY VALID");
			}else{
				textArea.append("\nKEY INVALID");
			}
		}
	}
	
	public void canUseKey(){
		String key = promptForKey();
		if(key != null){
			if(canUseKey(key)){
				textArea.append("\nCAN USE KEY");
			}else{
				textArea.append("\nMAX USES REACHED");
			}
		}
	}
	
	public void resetKey(){
		String key = promptForKey();
		if(key != null){
			if(resetKey(key)){
				textArea.append("\nKEY RESET");
			}else{
				textArea.append("\nERROR RESETING");
			}
		}
	}
	
	public void viewKey(){
		String key = promptForKey();
		if(key != null){
			if(viewKey(key)){
				textArea.append("\nVIEW COMPLETE");
			}else{
				textArea.append("\nERROR VIEWING KEY");
			}
		}
	}
	
	public void deleteKey(){
		String key = promptForKey();
		if(key != null){
			if(deleteKey(key)){
				textArea.append("\nKEY DELETED");
			}else{
				textArea.append("\nERROR DELETING");
			}
		}
	}
	
	public boolean addSingleKey(){
		String name = promptForName();
		if(name != null){
			String email = promptForEmail();
			if(email != null){
				String key = makeUniqueKey();
				textArea.append("\nADDING KEY: '"+key+"'");
			    try {
			    	Statement sql = con.createStatement();
		    		int results = sql.executeUpdate("INSERT INTO licenses VALUES('','"+key+"',NOW(),0,1,'"+name+"','"+email+"')");
			        if(results == 1){
			        	return true;
			        }else{
			        	return false;
			        }
			    }catch(Exception e){
			    	textArea.append("\nDATABASE ACCESS ERROR");
			    	JOptionPane.showMessageDialog(parent,"Error Occured While Checking Key.","Error",JOptionPane.ERROR_MESSAGE);
			    	e.printStackTrace();
			    	return false;
			    }
			}
		}
		return false;
	}
	
	public boolean addSpecialKey(){
		String name = promptForKeyName();
		if(name != null){
			String email = promptForEmail();
			if(email != null){
				String key = promptForKey();
				if(key != null){
					int uses = promptForAllowed();
					if(uses <= 2){
						uses = 2;
					}
					textArea.append("\nADDING SPECIAL KEY: '"+key+"'");
				    try {
				    	Statement sql = con.createStatement();
			    		int results = sql.executeUpdate("INSERT INTO licenses VALUES('','"+key+"',NOW(),0,"+uses+",'"+name+"','"+email+"')");
				        if(results == 1){
				        	return true;
				        }else{
				        	return false;
				        }
				    }catch(Exception e){
				    	textArea.append("\nDATABASE ACCESS ERROR");
				    	JOptionPane.showMessageDialog(parent,"Error Occured While Checking Key.","Error",JOptionPane.ERROR_MESSAGE);
				    	e.printStackTrace();
				    	return false;
				    }
				}
			}
		}
		return false;
	}
	
	public boolean addBulkKey(){
		String name = promptForGroupName();
		if(name != null){
			String email = promptForEmail();
			if(email != null){
				int numKeys = promptForNumKeys();
				if(numKeys <= 1){
					numKeys = 1;
				}
				String key;
				for(int i = 0; i<numKeys;i++){
					key = makeUniqueKey();
				    try {
				    	Statement sql = con.createStatement();
			    		int results = sql.executeUpdate("INSERT INTO licenses VALUES('','"+key+"',NOW(),0,2,'"+name+"','"+email+"')");
				        if(results == 1){
				        	textArea.append("\nADDING SPECIAL KEY: '"+key+"'");
				        }else{
				        	textArea.append("\nINSERT ERROR");
				        	throw new SQLException();
				        }
				    }catch(Exception e){
				    	textArea.append("\nDATABASE ACCESS ERROR");
				    	JOptionPane.showMessageDialog(parent,"Error Occured While Checking Key.","Error",JOptionPane.ERROR_MESSAGE);
				    	e.printStackTrace();
				    	return false;
				    }
				    
				}
				return true;
			}
		}
		return false;
	}
	
	private boolean resetKey(String key){
		textArea.append("\nRESETING KEY: '"+key+"'");
	    try {
	    	Statement sql = con.createStatement();
    		int results = sql.executeUpdate("UPDATE licenses SET used=0 WHERE license = '"+key+"'");
	        if(results == 1){
	        	promptKeyReset();
	        	return true;
	        }else{
	        	promptInvalidKey();
	        	return false;
	        }
	    }catch(Exception e){
	    	textArea.append("\nDATABASE ACCESS ERROR");
	    	JOptionPane.showMessageDialog(parent,"Error Occured While Checking Key.","Error",JOptionPane.ERROR_MESSAGE);
	    	e.printStackTrace();
	    	return false;
	    }
	}
	
	private boolean deleteKey(String key){
		textArea.append("\nDELETING KEY: '"+key+"'");
	    try {
	    	Statement sql = con.createStatement();
    		int results = sql.executeUpdate("DELETE FROM licenses WHERE license = '"+key+"'");
	        if(results == 1){
	        	promptKeyDeleted();
	        	return true;
	        }else{
	        	promptInvalidKey();
	        	return false;
	        }
	    }catch(Exception e){
	    	textArea.append("\nDATABASE ACCESS ERROR");
	    	JOptionPane.showMessageDialog(parent,"Error Occured While Checking Key.","Error",JOptionPane.ERROR_MESSAGE);
	    	e.printStackTrace();
	    	return false;
	    }
	}
	
	private String makeUniqueKey(){
		String tempKey = makeKey();
		while(!isKeyUnique(tempKey)){
			tempKey = makeKey();
		}
		return tempKey;
	}
	
	private String makeKey() {
		String key = "";
		for(int i=0;i<KEY_SETS;i++){
			key += makeSet();
			if(i != KEY_SETS-1){
				key += "-";
			}
		}
		return key;
	}
	
	private String makeSet(){
		String set = "";
		r.setSeed(r.nextLong());
		for(int i=0;i<SET_SIZE;i++){
			if(r.nextInt()%2 == 0){
				set += makeRandomInt();
			}else{
				set += makeRandomChar();
			}
		}
		return set;
	}
	
	private Integer makeRandomInt(){
		r = new Random();
	    long range = (long)9 - (long)1 + 1;
	    long fraction = (long)(range * r.nextDouble());
	    return new Integer((int)(fraction + 1));

	}
	
	private Character makeRandomChar(){
		r = new Random();
	    long range = (long)90 - (long)65 + 1;
	    long fraction = (long)(range * r.nextDouble());
	    Integer ran = new Integer((int)(fraction + 65));
	    char letter = (char)ran.intValue();
		return new Character(letter);
	}
	
	private boolean isKeyValid(String key){
		textArea.append("\nVALIDATING KEY: '"+key+"'");
	    try {
	    	Statement sql = con.createStatement();
    		ResultSet results = sql.executeQuery("SELECT * FROM licenses WHERE license = '"+key+"'");
    		ResultSetMetaData metaData = results.getMetaData();
    		results.last();
    		int rows = results.getRow(); // Return Row Number
	        if(rows == 1){
	        	promptValidKey();
	        	return true;
	        }else{
	        	promptInvalidKey();
	        	return false;
	        }
	    }catch(Exception e){
	    	JOptionPane.showMessageDialog(parent,"Error Occured While Checking Key.","Error",JOptionPane.ERROR_MESSAGE);
	    	e.printStackTrace();
	    	return false;
	    }
	}
	
	private boolean canUseKey(String key){
	    try {
    		Statement sql = con.createStatement();
    		ResultSet results = sql.executeQuery("SELECT * FROM licenses WHERE license = '"+key+"'");
    		ResultSetMetaData metaData = results.getMetaData();
    		results.last();
    		int rows = results.getRow(); // Return Row Number
	        if(rows == 1){
	        	int uses = results.getInt(4);
	        	int allo = results.getInt(5);
	        	int left = allo - uses;
	        	if(left >= 1){
	        		promptCanUse(left);
	        		return true;
	        	}else{
	        		promptMaxUses();
	        		return false;
	        	}
	        }else{
	        	promptInvalidKey();
	        	return false;
	        }
	    }catch(Exception e){
	    	JOptionPane.showMessageDialog(parent,"Error Occured While Checking Key.","Error",JOptionPane.ERROR_MESSAGE);
	    	e.printStackTrace();
	    	return false;
	    }
	}
	
	private boolean viewKey(String key){
		 try {
    		Statement sql = con.createStatement();
    		ResultSet results = sql.executeQuery("SELECT * FROM licenses WHERE license = '"+key+"'");
    		ResultSetMetaData metaData = results.getMetaData();
    		results.last();
    		int rows = results.getRow(); // Return Row Number
	        if(rows == 1){
	        	String date = results.getString(3);
	        	int uses = results.getInt(4);
	        	int allo = results.getInt(5);
	        	String name = results.getString(6);
	        	String email = results.getString(7);
	        	promptShowKeyData(key,date,uses,allo,name,email);
	        	return true;
	        }else{
	        	promptInvalidKey();
	        	return false;
	        }
	    }catch(Exception e){
	    	JOptionPane.showMessageDialog(parent,"Error Occured While Checking Key.","Error",JOptionPane.ERROR_MESSAGE);
	    	e.printStackTrace();
	    	return false;
	    }
	}
	
	private boolean isKeyUnique(String key){
		textArea.append("\nIS KEY UNIQUE: '"+key+"'");
	    try {
	    	Statement sql = con.createStatement();
    		ResultSet results = sql.executeQuery("SELECT * FROM licenses WHERE license = '"+key+"'");
    		ResultSetMetaData metaData = results.getMetaData();
    		results.last();
    		int rows = results.getRow(); // Return Row Number
	        if(rows == 1){
	        	return false;
	        }else{
	        	return true;
	        }
	    }catch(Exception e){
	    	e.printStackTrace();
	    	return false;
	    }
	}
	
	private String promptForKey(){
		return JOptionPane.showInputDialog(parent,"Please Enter 25 Digit Key with Dashes","Enter Activation Key",JOptionPane.QUESTION_MESSAGE);
	}
	
	private void promptInvalidKey(){
		JOptionPane.showMessageDialog(parent,"The Key you Entered was Invalid","Error",JOptionPane.ERROR_MESSAGE);
	}
	
	private void promptConnError(String et, StackTraceElement[] e){
		String error = et+"\n";
		for(int i=0;i<e.length;i++){
			error += e[i].toString()+"\n";
		}
		JOptionPane.showMessageDialog(parent,"Error Connecting to Database.\n"+error,"Error",JOptionPane.ERROR_MESSAGE);
	}
	
	private void promptValidKey(){
		JOptionPane.showMessageDialog(parent,"The Key you Entered is Valid","Success",JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void promptKeyReset(){
		JOptionPane.showMessageDialog(parent,"The Key Has been Reset.","Success",JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void promptKeyDeleted(){
		JOptionPane.showMessageDialog(parent,"The Key Has been Deleted.","Success",JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void promptCanUse(int rem){
		JOptionPane.showMessageDialog(parent,"The Key you Entered Has "+rem+" Uses Remaining.","Success",JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void promptMaxUses(){
		JOptionPane.showMessageDialog(parent,"This Key has Reached its Maximum Number of Uses.","Error",JOptionPane.ERROR_MESSAGE);
	}
	
	private String promptForName(){
		return JOptionPane.showInputDialog(parent,"Enter User Name","Enter License Information",JOptionPane.QUESTION_MESSAGE);
	}
	
	private String promptForKeyName(){
		return JOptionPane.showInputDialog(parent,"Enter Key Owner's Name","Enter License Information",JOptionPane.QUESTION_MESSAGE);
	}
	
	private String promptForEmail(){
		return JOptionPane.showInputDialog(parent,"Enter User Email","Enter License Information",JOptionPane.QUESTION_MESSAGE);
	}
	
	private int promptForAllowed(){
		return Integer.parseInt(JOptionPane.showInputDialog(parent,"Enter Allowed Uses","Enter License Information",JOptionPane.QUESTION_MESSAGE));
	}
	
	private int promptForNumKeys(){
		return Integer.parseInt(JOptionPane.showInputDialog(parent,"Enter Number of Keys","Enter License Information",JOptionPane.QUESTION_MESSAGE));
	}
	
	private String promptForGroupName(){
		return JOptionPane.showInputDialog(parent,"Enter Group Name","Enter License Information",JOptionPane.QUESTION_MESSAGE);
	}
	
	private void promptShowKeyData(String key,String date,int uses,int allo,String name,String email){
		String mesg = "Key: "+key+"\n";
		mesg += "Added On: "+date+"\n";
		mesg += "Usage: "+uses+" Out Of "+allo+"\n";
		mesg += "Registered To: "+name+"\n";
		mesg += "Contact Email: "+email;
		textArea.append("\n"+mesg);
		JOptionPane.showMessageDialog(parent,mesg,"View License",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void clearLog(){
		int ch = JOptionPane.showConfirmDialog(parent,"Are You Sure you Want to Clear the Log?","Clear Log",JOptionPane.YES_NO_OPTION);
		if(ch == 0){
			textArea.setText("");
		}
	}
}
