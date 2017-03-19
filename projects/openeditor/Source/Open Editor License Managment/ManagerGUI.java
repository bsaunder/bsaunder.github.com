import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
/*
 * Created by Bryan Saunders
 * Created on Dec 5, 2004
 */

public class ManagerGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JTextArea textArea = new JTextArea();
    private JScrollPane console = new JScrollPane(textArea);
    
    private KeyManager km = new KeyManager(this,textArea);
	private ManagerMenu menu = new ManagerMenu(km,this);
	
	public ManagerGUI(){
		super();
		this.setTitle("OpenEditor License Manager");
		this.setSize(450,300);
		this.setResizable(false);
		this.setJMenuBar(menu);
		
		Container content = getContentPane();
        content.setBackground(Color.DARK_GRAY);
        content.setLayout(null);
        
        Font userFont = new Font("Monospaced",0,12);
        textArea.setEditable(false);
        textArea.setFont(userFont);
        textArea.setMargin(new Insets(2,2,2,2));
        textArea.setSelectionColor(new Color(65,80,90));
        console.setBorder(new LineBorder(Color.BLACK));
        console.setSize(430,240);
        console.setLocation(7,5);
        content.add(console);
		
		this.setVisible(true);
	}
}
