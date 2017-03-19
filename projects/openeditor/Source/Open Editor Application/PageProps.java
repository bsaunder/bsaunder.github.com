import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/*
 * Created by Bryan Saunders
 * Created on Dec 2, 2004
 */

public class PageProps extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private String title;
	private String lastText,lastBG,lastALink,lastVLink,lastLink;
	private JTextArea textArea = new JTextArea();
	private JTextField colorText = new JTextField();
	private JTextField colorBG = new JTextField();
	private JTextField colorLink = new JTextField();
	private JTextField colorALink = new JTextField();
	private JTextField colorVLink = new JTextField();
	private JTextField pageTitle = new JTextField();
	private JButton bttn = new JButton("Apply Changes");
	
	public PageProps(JTextArea ta){
		super("Page Properties");
		this.textArea = ta;
		this.lastText = "";
		this.lastBG = "";
		this.lastALink = "";
		this.lastVLink = "";
		this.lastLink = "";
		this.setIconImage(new ImageIcon("openeditor.gif").getImage());
		this.setLocation(150,150);
		this.setSize(290,180);
		this.setLayout(null);
	}
	
	public void open(){
		JLabel label = new JLabel("Enter the New Values for your Page Properties");
		JLabel lblTitle = new JLabel("Title:");
		JLabel lblText = new JLabel("Text:");
		JLabel lblBG = new JLabel("BackGr:");
		JLabel lblLink = new JLabel("Link:");
		JLabel lblALink = new JLabel("A Link:");
		JLabel lblVLink = new JLabel("V Link:");
		
		label.setBounds(5,5,300,20);
		
		lblTitle.setBounds(5,35,50,20);
		pageTitle.setBounds(55,35,215,20);
		
		lblText.setBounds(145,65,50,20);
		colorText.setBounds(195,65,75,20);
		lblBG.setBounds(145,95,50,20);
		colorBG.setBounds(195,95,75,20);
		bttn.setBounds(165,125,110,20);
		
		lblLink.setBounds(5,65,50,20);
		colorLink.setBounds(55,65,75,20);
		lblALink.setBounds(5,95,50,20);
		colorALink.setBounds(55,95,75,20);
		lblVLink.setBounds(5,125,50,20);
		colorVLink.setBounds(55,125,75,20);
		
		this.add(label);
		this.add(lblTitle);
		this.add(lblText);
		this.add(lblBG);
		this.add(lblLink);
		this.add(lblALink);
		this.add(lblVLink);
		this.add(pageTitle);
		this.add(colorText);
		this.add(colorBG);
		this.add(colorLink);
		this.add(colorALink);
		this.add(colorVLink);
		this.add(bttn);
		
		bttn.addActionListener(this);
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		title = pageTitle.getText();
		if(!title.equals("")){
			// Title Has Text, So Find it and Change
			//System.out.println("-"+title+"-");
			int start = textArea.getText().indexOf("<title>");
			int stop = textArea.getText().indexOf("</title>")+8;
			textArea.replaceRange("<title>"+title+"</title>",start,stop);
		}
		
		String newBody = "<body";
		lastText = colorText.getText();
		if(!lastText.equals("")){
			newBody += " text=\""+lastText+"\"";
		}
		
		lastBG = colorBG.getText();
		if(!lastBG.equals("")){
			newBody += " bgcolor=\""+lastBG+"\"";
		}
		
		lastLink = colorLink.getText();
		if(!lastLink.equals("")){
			newBody += " link=\""+lastLink+"\"";
		}
		
		lastALink = colorALink.getText();
		if(!lastALink.equals("")){
			newBody += " alink=\""+lastALink+"\"";
		}
		
		lastVLink = colorVLink.getText();
		if(!lastVLink.equals("")){
			newBody += " vlink=\""+lastVLink+"\"";
		}
		
		newBody += ">";
		
		int start = textArea.getText().indexOf("<body");
		int stop = textArea.getText().indexOf(">",start+1)+1;
		textArea.replaceRange(newBody,start,stop);
		
		textArea.requestFocus();
		this.setVisible(false);
	}
}
