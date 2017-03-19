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
 * Created on Dec 1, 2004
 */

public class Finder extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private String lastFind;
	private String lastReplace;
	private int func;
	private int lastPos;
	private JTextArea textArea = new JTextArea();
	private JTextField wordToFind = new JTextField();
	private JTextField wordToReplace = new JTextField();
	private JButton bttn;

	public Finder(JTextArea ta){
		super();
		this.lastPos = 0;
		this.textArea = ta;
		this.lastFind = "";
		this.setIconImage(new ImageIcon("openeditor.gif").getImage());
		this.setLocation(150,150);
		this.setLayout(null);
	}
	
	public void showFind(){
		this.func = 0;
		this.setTitle("Find..");
		wordToFind.setText(lastFind);
		bttn = new JButton("Find..");
		wordToFind.setBounds(10,35,180,20);
		JLabel label = new JLabel("Enter Text to Find");
		label.setBounds(10,5,250,25);
		bttn.setBounds(200,35,80,20);
		bttn.addActionListener(this);
		this.add(bttn);
		this.add(wordToFind);
		this.add(label);
		this.setSize(300,100);
		this.setVisible(true);
	}
	
	public void showFindReplace(){
		this.func = 1;
		this.setTitle("Find & Replace");
		JLabel lblFind = new JLabel("Find:");
		JLabel lblRepl = new JLabel("Repl:");
		lblFind.setBounds(5,35,30,20);
		lblRepl.setBounds(5,65,30,20);
		wordToFind.setText(lastFind);
		wordToReplace.setText(lastReplace);
		bttn = new JButton("Replace..");
		wordToFind.setBounds(32,35,160,20);
		wordToReplace.setBounds(32,65,160,20);
		JLabel label = new JLabel("Enter Text to Find & Replace");
		label.setBounds(10,5,250,25);
		bttn.setBounds(200,65,80,20);
		bttn.addActionListener(this);
		this.add(bttn);
		this.add(wordToReplace);
		this.add(label);
		this.add(lblFind);
		this.add(lblRepl);
		this.add(wordToFind);
		this.setSize(300,130);
		this.setVisible(true);
	}
	
	public void findNext(){
		if(lastFind != ""){
			int start = textArea.getText().indexOf(wordToFind.getText(),lastPos+1);
			lastPos = start;
			int stop = start + wordToFind.getText().length();
			textArea.requestFocus();
			textArea.setSelectionStart(start);
			textArea.setSelectionEnd(stop);
		}else{
			showFind();
		}
	}

	public void actionPerformed(ActionEvent arg0) {
			if(func == 0){
				// Find
				this.setVisible(false);
				lastFind = wordToFind.getText();
				int start = textArea.getText().indexOf(wordToFind.getText());
				lastPos = start;
				int stop = start + wordToFind.getText().length();
				textArea.requestFocus();
				textArea.setSelectionStart(start);
				textArea.setSelectionEnd(stop);
			}else if(func == 1){
				// Find & Replace
				this.setVisible(false);
				int carPos = textArea.getCaretPosition();
				lastFind = wordToFind.getText();
				lastFind = wordToReplace.getText();
				String code = textArea.getText();
				code = code.replaceAll(wordToFind.getText(),wordToReplace.getText());
				textArea.setText(code);
				textArea.requestFocus();
				textArea.setCaretPosition(carPos);
			}
	}
	
	
}
