import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
/*
 * Created by Bryan Saunders
 * Created on Dec 1, 2004
 */

public class EditorAbout extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	public EditorAbout(){
		super("About");
		this.setIconImage(new ImageIcon("openeditor.gif").getImage());
		this.setSize(400,270);
		this.setLocation(150,150);
		this.setLayout(null);
		
		String about = "OpenEditor is an XHTML being developed for use in Beginner Level Web Design & " +
				"Development courses in both the College & High School Level. It is aimed at helping " +
				"the students learn the knowledge that is critical to building a strong foundation in " +
				"Web Design & Development.";
		
		Icon icon = new ImageIcon("oe_logo.gif");
		
		JLabel title = new JLabel("<OpenEditor>",JLabel.CENTER);
		title.setBounds(180,0,200,30);
		title.setFont(new Font("Monospaced",Font.BOLD,24));
		
		JLabel version = new JLabel("Version 1.0 Beta",JLabel.CENTER);
		version.setBounds(180,30,200,20);
		version.setFont(new Font("SansSerif",Font.ITALIC,12));
		
		JLabel logo = new JLabel("");
		logo.setIcon(icon);
		logo.setBounds(10,2,230,210);
		
		JTextArea text = new JTextArea(about);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setEditable(false);
		text.setBorder(new LineBorder(Color.BLACK));
		text.setBounds(180,60,200,149);
		text.setFont(new Font("SansSerif",0,10));
		
		JButton close = new JButton("Close");
		close.setBounds(280,215,100,20);
		close.addActionListener(this);
		close.requestFocus();
		
		JLabel copyright = new JLabel("Copyright 2004 Bryan Saunders. All Rights Reserved.");
		copyright.setBounds(10,215,270,20);
		copyright.setFont(new Font("SansSerif",Font.ITALIC,10));
		
		this.add(close);
		this.add(copyright);
		this.add(title);
		this.add(version);
		this.add(logo);
		this.add(text);
	}
	
	public void open(){
		this.setVisible(true);
	}
	
	public void close(){
		this.setVisible(false);
	}

	public void actionPerformed(ActionEvent arg0) {
		close();
	}
}
