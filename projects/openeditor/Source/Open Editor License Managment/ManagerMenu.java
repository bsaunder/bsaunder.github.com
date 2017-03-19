import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
/*
 * Created by Bryan Saunders
 * Created on Dec 5, 2004
 */

public class ManagerMenu extends JMenuBar implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private JMenu menuFile = new JMenu("File");
	private JMenu menuValidate = new JMenu("Validate");
	private JMenu menuAdd = new JMenu("Add");
	private JMenu menuBrowse = new JMenu("Browse");
	private JMenu menuEdit = new JMenu("Edit");
	private JMenu menuDelete = new JMenu("Delete");
	
	private JMenuItem jmExit = new JMenuItem("Exit");
	private JMenuItem jmClear = new JMenuItem("Clear Log");
	private JMenuItem jmSave = new JMenuItem("Save Log");
	private JMenuItem jmIsValid = new JMenuItem("Is Valid");
	private JMenuItem jmCanUse = new JMenuItem("Can Use");
	private JMenuItem jmAddKey = new JMenuItem("Add Single Key");
	private JMenuItem jmAddBulk = new JMenuItem("Add Bulk Keys");
	private JMenuItem jmAddSpecial = new JMenuItem("Add Multi-Use Key");
	private JMenuItem jmReset = new JMenuItem("Reset Key");
	private JMenuItem jmDelKey = new JMenuItem("Delete Key");
	private JMenuItem jmLstAll = new JMenuItem("List All Keys");
	private JMenuItem jmLstName = new JMenuItem("List Keys for Name");
	private JMenuItem jmViewKey = new JMenuItem("View Single Key");
	
	private KeyManager km;
	//private KeyInfo ki;

	public ManagerMenu(KeyManager k,JFrame par){
		this.km = k;
		//ki = new KeyInfo(km,par); 
		this.add(menuFile);
		//menuFile.add(jmSave);
		menuFile.add(jmClear);
		menuFile.addSeparator();
		menuFile.add(jmExit);
		this.add(menuValidate);
		menuValidate.add(jmIsValid);
		menuValidate.add(jmCanUse);
		this.add(menuAdd);
		menuAdd.add(jmAddKey);
		menuAdd.add(jmAddBulk);
		menuAdd.addSeparator();
		menuAdd.add(jmAddSpecial);
		this.add(menuBrowse);
		menuBrowse.add(jmViewKey);
		//menuBrowse.addSeparator();
		//menuBrowse.add(jmLstAll);
		//menuBrowse.add(jmLstName);
		this.add(menuEdit);
		menuEdit.add(jmReset);
		this.add(menuDelete);
		menuDelete.add(jmDelKey);
		
		jmExit.addActionListener(this);
		jmIsValid.addActionListener(this);
		jmCanUse.addActionListener(this);
		jmAddKey.addActionListener(this);
		jmAddBulk.addActionListener(this);
		jmReset.addActionListener(this);
		jmDelKey.addActionListener(this);
		jmLstAll.addActionListener(this);
		jmLstName.addActionListener(this);
		jmViewKey.addActionListener(this);
		jmAddSpecial.addActionListener(this);
		jmClear.addActionListener(this);
		jmSave.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jmExit){
			System.exit(0);
		}else if(e.getSource() == jmIsValid){
			km.isKeyValid();
		}else if(e.getSource() == jmCanUse){
			km.canUseKey();
		}else if(e.getSource() == jmReset){
			km.resetKey();
		}else if(e.getSource() == jmDelKey){
			km.deleteKey();
		}else if(e.getSource() == jmAddKey){
			km.addSingleKey();
		}else if(e.getSource() == jmAddSpecial){
			km.addSpecialKey();
		}else if(e.getSource() == jmAddBulk){
			km.addBulkKey();
		}else if(e.getSource() == jmClear){
			km.clearLog();
		}else if(e.getSource() == jmViewKey){
			km.viewKey();
		}
	}

}
