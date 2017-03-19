import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
/*
 * Created by Bryan Saunders
 * Created on Dec 5, 2004
 */

public class KeyInfo extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private KeyManager km;

	private JLabel lblName = new JLabel("Name");
	private JLabel lblAdd = new JLabel("Address");
	private JLabel lblCity = new JLabel("City");
	private JLabel lblState = new JLabel("State");
	private JLabel lblZip = new JLabel("Zip");
	private JLabel lblPhone = new JLabel("Phone");
	private JLabel lblEmail = new JLabel("Email");
	private JLabel lblAllo = new JLabel("Allowed");
	private JLabel lblKey = new JLabel("Key");
	
	private JTextField txtName = new JTextField();
	private JTextField txtAdd = new JTextField();
	private JTextField txtCity = new JTextField();
	private JTextField txtState = new JTextField();
	private JTextField txtZip = new JTextField();
	private JTextField txtPhone = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JTextField txtAllo = new JTextField("2");
	private JTextField txtKey = new JTextField();
	
	private JFrame parent;
	
	public KeyInfo(KeyManager k,JFrame par){
		parent = par;
		km = k;
		this.setResizable(false);
		this.setSize(250,230);
		//this.setLocation(100,100);
		this.setLayout(null);
	}
	
	public void showAddSingle(){
		this.setTitle("Add Single Key");
		
		lblName.setBounds(5,5,50,20);
		txtName.setBounds(60,5,180,20);
		this.add(txtName);
		this.add(lblName);
		
		lblAdd.setBounds(5,30,50,20);
		txtAdd.setBounds(60,30,180,20);
		this.add(txtAdd);
		this.add(lblAdd);
		
		lblCity.setBounds(5,55,50,20);
		txtCity.setBounds(60,55,80,20);
		this.add(txtCity);
		this.add(lblCity);
		
		lblState.setBounds(5,80,50,20);
		txtState.setBounds(60,80,80,20);
		this.add(txtState);
		this.add(lblState);
		
		lblZip.setBounds(5,105,50,20);
		txtZip.setBounds(60,105,50,20);
		this.add(txtZip);
		this.add(lblZip);
		
		lblPhone.setBounds(5,130,50,20);
		txtPhone.setBounds(60,130,80,20);
		this.add(txtPhone);
		this.add(lblPhone);
		
		lblEmail.setBounds(5,155,50,20);
		txtEmail.setBounds(60,155,180,20);
		this.add(txtEmail);
		this.add(lblEmail);
		
		
		this.setVisible(true);
	}

}
