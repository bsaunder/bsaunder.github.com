import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/*
 * Created by Bryan Saunders
 * Created on Nov 28, 2004
 */

public class DTDCreator {
	
	private String htmlTrans = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \n\"http://www.w3.org/TR/html4/strict.dtd\">\n\n";
	private String htmlStrict = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \n\"http://www.w3.org/TR/html4/loose.dtd\">\n\n";
	private String htmlFrame = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Frameset//EN\" \n\"http://www.w3.org/TR/html4/frameset.dtd\">\n\n";
	private String xhtmlTrans = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \n\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> \n\n";
	private String xhtmlStrict = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \n\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"> \n\n";
	private String xhtmlFrame = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Frameset//EN\" \n\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd\"> \n\n";

	private JTextArea textArea;
	
	public DTDCreator(JTextArea ta) {
		this.textArea = ta;
		makeXHTMLTrans();
	}
	
	public void makeXHTMLTrans(){
		if(prepDTD()){
			textArea.insert(xhtmlTrans,0);
		}else{
			showError();
		}		
	}
	
	public void makeXHTMLStrict(){
		if(prepDTD()){
			textArea.insert(xhtmlStrict,0);
		}else{
			showError();
		}		
	}
	
	public void makeXHTMLFrame(){
		if(prepDTD()){
			textArea.insert(xhtmlFrame,0);
		}else{
			showError();
		}		
	}
	
	public void makeHTMLTrans(){
		if(prepDTD()){
			textArea.insert(htmlTrans,0);
		}else{
			showError();
		}		
	}
	
	public void makeHTMLStrict(){
		if(prepDTD()){
			textArea.insert(htmlStrict,0);
		}else{
			showError();
		}		
	}
	
	public void makeHTMLFrame(){
		if(prepDTD()){
			textArea.insert(htmlFrame,0);
		}else{
			showError();
		}		
	}
	
	private boolean prepDTD(){
		if(hasDTD()){
			// Remove DTD
			int start,stop,html;
			start = textArea.getText().indexOf("<!DOCTYPE");
			stop = textArea.getText().indexOf(">",start);
			html = textArea.getText().indexOf("<html>");
			if(start < html){
				// Remove & Return True
				String code = textArea.getText().substring(html);
				textArea.setText(code);
				return true;
			}else{
				return false;
			}
		}else{
			return true;
		}
	}
	
	private boolean hasDTD(){
		String temp = textArea.getText();
		return temp.contains("<!DOCTYPE");
	}
	
	private void showError(){
		JOptionPane.showMessageDialog(null,"Invalid DTD, Please Remove DTD By Hand, Then Try Again.","ERROR",JOptionPane.ERROR_MESSAGE);
	}

}
