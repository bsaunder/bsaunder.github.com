import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
/*
 * Created by Bryan Saunders
 * Created on Dec 1, 2004
 */

public class EditorPrefs extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	private JButton set = new JButton("Apply");
	private JLabel lblPrev = new JLabel("Sample");
	
	private static final String[] fonts = {"Serif","SansSerif","Monospaced"};
	private static final String[] sizes = {"8","10","12","14","16","18"};
	
	private JComboBox font = new JComboBox(fonts);
	private JComboBox size = new JComboBox(sizes);
	
	private static final String prefNode = "OpenEditor/ui/prefs";
	private Preferences prefs = Preferences.userRoot().node(prefNode);

	public EditorPrefs(JTextArea ta){
		super("Change Font / Size");
		this.textArea = ta;
		this.setIconImage(new ImageIcon("openeditor.gif").getImage());
		this.setSize(220,150);
		this.setLayout(null);
		this.setLocation(150,150);
		JLabel label = new JLabel("Choose new Font & Size");
		label.setBounds(10,5,250,25);
		JLabel lblFind = new JLabel("Font:");
		JLabel lblRepl = new JLabel("Size:"); 
		lblFind.setBounds(5,35,30,20);
		lblRepl.setBounds(5,65,30,20);
		
		lblPrev.setBounds(5,95,65,20);
		lblPrev.setBorder(new LineBorder(Color.BLACK));
		
		set.setBounds(85,95,100,20);
		font.setBounds(32,35,180,20);
		size.setBounds(32,65,180,20);
		this.add(font);
		this.add(size);	
		this.add(lblFind);
		this.add(lblPrev);
		this.add(set);
		this.add(lblRepl);
		this.add(label);
		set.addActionListener(this);
		font.addActionListener(this);
		size.addActionListener(this);
	}
	
	private void setCurrentFont(){
		Font temp = textArea.getFont();
		String fName = temp.getFontName();
		fName = fName.substring(0,fName.indexOf("."));
		font.setSelectedItem(fName);
		size.setSelectedItem(String.valueOf(temp.getSize()));
		lblPrev.setFont(temp);
	}
	
	private void storePrefs(){
		Font temp = lblPrev.getFont();
		String fName = temp.getFontName();
		fName = fName.substring(0,fName.indexOf("."));
		prefs.put("font",fName);
		prefs.putInt("size",temp.getSize());
	}
	
	public void open(){
		this.setVisible(true);
		setCurrentFont();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == set){
			textArea.setFont(lblPrev.getFont());
			storePrefs();
			this.setVisible(false);
		}else if(e.getSource() == font || e.getSource() == size){
			String tFont = font.getSelectedItem().toString();
			int tSize = Integer.parseInt(size.getSelectedItem().toString());
			lblPrev.setFont(new Font(tFont,0,tSize));
		}
	}
	
}
