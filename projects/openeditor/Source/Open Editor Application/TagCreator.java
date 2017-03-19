import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/*
 * Created by Bryan Saunders
 * Created on Nov 17, 2004
 */

public class TagCreator {
	private JTextArea textArea;
	private DTDCreator dtd;
	private FileEditor fe = new FileEditor(); // All Data Setup in Menu
	private String simplePage = "<html>\n<head>\n<title>Simple Page</title>\n</head>\n" +
			"<body>\n\n<p>Simple XHTML Page</p>\n\n</body>\n</html>";
	
	public TagCreator(JTextArea ta){
		this.textArea = ta;
		dtd = new DTDCreator(ta);
	}
	
	public void makeDTD(){
		Object[] dtds = new Object[] {"XHTML Strict","XHTML Trans","XHTML Frame","HTML Strict","HTML Trans","HTML Frame"};
		Object choice = JOptionPane.showInputDialog(null,"Plase Choose a Header Size","Header",JOptionPane.QUESTION_MESSAGE,null,dtds,"XHTML Trans");
		if(choice.equals("XHTML Strict")){
			dtd.makeXHTMLStrict();
		}else if(choice.equals("XHTML Trans")){
			dtd.makeXHTMLTrans();
		}else if(choice.equals("XHTML Frame")){
			dtd.makeXHTMLFrame();
		}else if(choice.equals("HTML Trans")){
			dtd.makeHTMLTrans();
		}else if(choice.equals("HTML Strict")){
			dtd.makeHTMLStrict();
		}else if(choice.equals("HTML Frame")){
			dtd.makeHTMLFrame();
		}
	}
	
	public void insertRefresh(){
		// Find Position of Head Tag
		int pos = textArea.getText().indexOf("<head>")+6;
		
		// Get Time (in Seconds) then Insert
		String secs = JOptionPane.showInputDialog(null,"Refresh Every ____ Seconds?","Insert Page Refresh",JOptionPane.INFORMATION_MESSAGE);
		if(secs != null){
			String newText = "\n<meta http-equiv=\"refresh\" content=\""+secs+"\" />";
			textArea.insert(newText,pos);
		}
	}
	
	public void insertRedirect(){
		// Find Position of Head Tag
		int pos = textArea.getText().indexOf("<head>")+6;
		
		// Get Time (in Seconds) then Insert
		String secs = JOptionPane.showInputDialog(null,"Redirect After ____ Seconds?","Insert Page Redirect",JOptionPane.INFORMATION_MESSAGE);
		if(secs != null){
			String url = JOptionPane.showInputDialog(null,"What is the Redirect URL?","Insert Page Redirect",JOptionPane.INFORMATION_MESSAGE);
			if(url != null){
				String newText = "\n<meta http-equiv=\"refresh\" content=\""+secs+";url="+url+"\" />";
				textArea.insert(newText,pos);
			}
		}
	}
	
	public void makeDTDXHTMLTrans(){
		dtd.makeXHTMLTrans();
	}
	
	public void doNonAttrTag(String tag){
		String newText;
		if(textArea.getSelectedText() == null){
			newText = JOptionPane.showInputDialog(null,"Please Enter Text","No Text Selelcted",JOptionPane.INFORMATION_MESSAGE);
			if(newText != null){
				newText = "<"+tag+">"+newText+"</"+tag+">";
				textArea.insert(newText,textArea.getCaretPosition());
			}
		} else {
			newText = "<"+tag+">"+textArea.getSelectedText()+"</"+tag+">";
			textArea.replaceSelection(newText);
		}
	}
	
	public void doSingleAttrTag(String tag,String attr1){
		String newText;
		String attrVal = JOptionPane.showInputDialog(null,"Please Enter Value of "+attr1,"Insert "+tag,JOptionPane.INFORMATION_MESSAGE);
		if(attrVal != null){
			attrVal = attrVal.replaceAll(" ","");
			if(textArea.getSelectedText() == null){
				newText = JOptionPane.showInputDialog(null,"Please Enter Text","No Text Selelcted",JOptionPane.INFORMATION_MESSAGE);
				if(newText != null){
					newText = "<"+tag+" "+attr1+"=\""+attrVal+"\">"+newText+"</"+tag+">";
					textArea.insert(newText,textArea.getCaretPosition());
				}
			} else {
				newText = "<"+tag+" "+attr1+"=\""+attrVal+"\">"+textArea.getSelectedText()+"</"+tag+">";
				textArea.replaceSelection(newText);
			}
		}
	}
	
	public void doDoubleAttrTag(String tag,String attr1,String attr2){
		String newText;
		String attrVal = JOptionPane.showInputDialog(null,"Please Enter Value of "+attr1,"Insert Table",JOptionPane.INFORMATION_MESSAGE);
		if(attrVal != null){
			attrVal = attrVal.replaceAll(" ","");
			String attrVal2 = JOptionPane.showInputDialog(null,"Please Enter Value of "+attr2,"Insert Table",JOptionPane.INFORMATION_MESSAGE);
			if(attrVal2 != null){
				attrVal2 = attrVal2.replaceAll(" ","");
				if(textArea.getSelectedText() == null){
					newText = JOptionPane.showInputDialog(null,"Please Enter Text","No Text Selelcted",JOptionPane.INFORMATION_MESSAGE);
					if(newText != null){
						newText = "<"+tag+" "+attr1+"=\""+attrVal+"\" "+attr2+"=\""+attrVal2+"\">"+newText+"</"+tag+">";
						textArea.insert(newText,textArea.getCaretPosition());
					}
				} else {
					newText = "<"+tag+" "+attr1+"=\""+attrVal+"\" "+attr2+"=\""+attrVal2+"\">"+textArea.getSelectedText()+"</"+tag+">";
					textArea.replaceSelection(newText);
				}
			}
		}
	}
	
	public void doMetaTag(String attr1,String attr2){
		String newText;
		String attrVal = JOptionPane.showInputDialog(null,"Please Enter Value of "+attr1,"Insert Table",JOptionPane.INFORMATION_MESSAGE);
		if(attrVal != null){
			attrVal = attrVal.replaceAll(" ","");
			String attrVal2 = JOptionPane.showInputDialog(null,"Please Enter Value of "+attr2,"Insert Table",JOptionPane.INFORMATION_MESSAGE);
			if(attrVal2 != null){
				attrVal2 = attrVal2.replaceAll(" ","");
				if(textArea.getSelectedText() == null){
					newText = "<meta "+attr1+"=\""+attrVal+"\" "+attr2+"=\""+attrVal2+"\" />";
					textArea.insert(newText,textArea.getCaretPosition());				
				} else {
					newText = "<meta "+attr1+"=\""+attrVal+"\" "+attr2+"=\""+attrVal2+"\" />";
					textArea.replaceSelection(newText);
				}
			}
		}
	}
	
	public void doTable(){
		String newText;
		if(textArea.getSelectedText() == null){
			newText = "<table><!-- <tr><td></td></tr> --></table>";
			textArea.insert(newText,textArea.getCaretPosition());
		} else {
			newText = "<table><!-- <tr><td></td></tr> --></table>";
			textArea.replaceSelection(newText);
		}
	}
	
	public void doTable(String attr1){
		String newText;
		String attrVal = JOptionPane.showInputDialog(null,"Please Enter Value of "+attr1,"Insert Table",JOptionPane.INFORMATION_MESSAGE);
		if(attrVal != null){
			attrVal = attrVal.replaceAll(" ","");
			if(textArea.getSelectedText() == null){
				newText = "<table "+attr1+"=\""+attrVal+"\"><!-- <tr><td></td></tr> --></table>";
				textArea.insert(newText,textArea.getCaretPosition());
			} else {
				newText = "<table "+attr1+"=\""+attrVal+"\"><!-- <tr><td></td></tr> --></table>";
				textArea.replaceSelection(newText);
			}
		}
	}
	
	public void doTable(String attr1,String attr2){
		String newText;
		String attrVal = JOptionPane.showInputDialog(null,"Please Enter Value of "+attr1,"Insert Table",JOptionPane.INFORMATION_MESSAGE);
		if(attrVal != null){
			attrVal = attrVal.replaceAll(" ","");
			String attrVal2 = JOptionPane.showInputDialog(null,"Please Enter Value of "+attr2,"Insert Table",JOptionPane.INFORMATION_MESSAGE);
			if(attrVal2 != null){
				attrVal2 = attrVal2.replaceAll(" ","");
				if(textArea.getSelectedText() == null){
					newText = "<table "+attr1+"=\""+attrVal+"\" "+attr2+"=\""+attrVal2+"\"><!-- <tr><td></td></tr> --></table>";
					textArea.insert(newText,textArea.getCaretPosition());
				} else {
					newText = "<table "+attr1+"=\""+attrVal+"\" "+attr2+"=\""+attrVal2+"\"><!-- <tr><td></td></tr> --></table>";
					textArea.replaceSelection(newText);
				}
			}
		}
	}
	
	public void doInput(String type){
		String newText;
		String name = JOptionPane.showInputDialog(null,"Please Enter Field Name","Insert "+type+" Field",JOptionPane.INFORMATION_MESSAGE);
		if(name != null){
			name = name.replaceAll(" ","");
			String value = JOptionPane.showInputDialog(null,"Please Enter "+name+"'s Value","Insert "+type+" Field",JOptionPane.INFORMATION_MESSAGE);
			if(value != null){
				newText = "<input type=\""+type+"\" name=\""+name+"\" value=\""+value+"\" />";
				if(textArea.getSelectedText() == null){
					textArea.insert(newText,textArea.getCaretPosition());
				} else {
					textArea.replaceSelection(newText);
				}
			}
		}
	}
	
	public void doTextArea(){
		String newText = "";
		String name = JOptionPane.showInputDialog(null,"Please Enter Text Area Name","Insert Text Area",JOptionPane.INFORMATION_MESSAGE);
		if(name != null){
			name = name.replaceAll(" ","");
			String rows = JOptionPane.showInputDialog(null,"Please Enter Number of Rows","Insert Text Area",JOptionPane.INFORMATION_MESSAGE);
			if(rows != null){
				String cols = JOptionPane.showInputDialog(null,"Please Enter Number of Columns","Insert Text Area",JOptionPane.INFORMATION_MESSAGE);
				if(cols != null){
					String text = JOptionPane.showInputDialog(null,"Please Enter Number of Rows","Insert Text Area",JOptionPane.INFORMATION_MESSAGE);
					if(text != null){
						newText = "<textarea name=\""+name+"\" rows=\""+rows+"\" cols=\""+cols+"\">"+text+"</textarea>";
						if(textArea.getSelectedText() == null){
							textArea.insert(newText,textArea.getCaretPosition());
						} else {
							textArea.replaceSelection(newText);
						}
					}
				}
			}
		}	
	}
	
	public void doFormButton(String type){
		String newText;
		String name = JOptionPane.showInputDialog(null,"Please Enter Field Name","Insert "+type+" Field",JOptionPane.INFORMATION_MESSAGE);
		if(name != null){
			name = name.replaceAll(" ","");
			String value = JOptionPane.showInputDialog(null,"Please Enter "+name+"'s Value","Insert "+type+" Field",JOptionPane.INFORMATION_MESSAGE);
			if(value != null){
				newText = "<input type=\""+type+"\" name=\""+name+"\" value=\""+value+"\" />";
				if(textArea.getSelectedText() == null){
					textArea.insert(newText,textArea.getCaretPosition());
				} else {
					textArea.replaceSelection(newText);
				}
			}
		}
	}
	
	public void doOption(){
		String newText;
		String name = JOptionPane.showInputDialog(null,"Please Enter Option Name","Insert Option Field",JOptionPane.INFORMATION_MESSAGE);
		if(name != null){
			name = name.replaceAll(" ","");
			String value = JOptionPane.showInputDialog(null,"Please Enter "+name+"'s Value","Insert Option Field",JOptionPane.INFORMATION_MESSAGE);
			if(value != null){
				newText = "<option value=\""+value+"\">"+name+"</option>";
				if(textArea.getSelectedText() == null){
					textArea.insert(newText,textArea.getCaretPosition());
				} else {
					textArea.replaceSelection(newText);
				}
			}
		}
	}
	
	public void doForm(){
		Object[] opt = new Object[] {"post","get"};
		Object method = JOptionPane.showInputDialog(null,"Please Choose Method","Insert Form",JOptionPane.QUESTION_MESSAGE,null,opt,"post");
		String newText = "";
		String form = "";
		if(method != null){
			String action = JOptionPane.showInputDialog(null,"Please Enter URL to Processing Script","Insert Form",JOptionPane.INFORMATION_MESSAGE);
			if(action != null){
				form = "<form method=\""+method+"\" action=\""+action+"\">";
				if(textArea.getSelectedText() == null){
					newText = form+newText+"</form>";
					textArea.insert(newText,textArea.getCaretPosition());
				} else {
					newText = form+textArea.getSelectedText()+"</form>";
					textArea.replaceSelection(newText);
				}
			}
		}
	}
	
	public void doOptGroup(){
		String newText;
		String value = JOptionPane.showInputDialog(null,"Please Enter Group Label","Insert Option Group",JOptionPane.INFORMATION_MESSAGE);
		if(value != null){
			newText = "<optgroup label=\""+value+"\" />";
			if(textArea.getSelectedText() == null){
				textArea.insert(newText,textArea.getCaretPosition());
			} else {
				textArea.replaceSelection(newText);
			}
		}
	}
	
	public void doSelect(){
		String name = JOptionPane.showInputDialog(null,"Enter Select Field Name","Insert Select Field",JOptionPane.INFORMATION_MESSAGE);
		String newText = "";
		if(name != null){
			name = name.replaceAll(" ","");
			if(textArea.getSelectedText() == null){
				newText = "<select name=\""+name+"\"><!-- <option> Tags Here --></select>";
				textArea.insert(newText,textArea.getCaretPosition());
			} else {
				newText = "<select name=\""+name+"\">"+textArea.getSelectedText()+"</select>";
				textArea.replaceSelection(newText);
			}
		}
	}
	
	public void doArea(){
		Object[] shapes = new Object[] {"poly","circ","rect"};
		Object shape = JOptionPane.showInputDialog(null,"Plase Choose a Shape","Insert Area",JOptionPane.QUESTION_MESSAGE,null,shapes,"rect");
		String newText;
		if(shape != null){
			String coords = JOptionPane.showInputDialog(null,"Please Enter Coords for Hotspot","Insert Area",JOptionPane.INFORMATION_MESSAGE);
			if(coords != null){
				String url = JOptionPane.showInputDialog(null,"Please Enter URL for Link","Insert Area",JOptionPane.INFORMATION_MESSAGE);
				if(url != null){
					String altText = JOptionPane.showInputDialog(null,"Please Enter Atlernate Text","Insert Area",JOptionPane.INFORMATION_MESSAGE);
					if(altText != null){
						newText = "<area shape=\""+shape+"\" coords=\""+coords+"\" href=\""+url+"\" alt=\""+altText+"\" />";
						if(textArea.getSelectedText() == null){
							textArea.insert(newText,textArea.getCaretPosition());
						} else {
							textArea.replaceSelection(newText);
						}
					}
				}
			}
		}
	}
	
	public void doScript(){
		Object[] types = new Object[] {"text/ecmascript","text/javascript","text/jscript","text/vbscript","text/vbs","text/xml"};
		Object type = JOptionPane.showInputDialog(null,"Plase Choose a Script Type","Insert Script",JOptionPane.QUESTION_MESSAGE,null,types,"text/javascript");
		String newText;
		if(textArea.getSelectedText() == null){
			newText = "<script type=\""+type+"\">\n<!-- Script Goes Here -->\n</script>";
			textArea.insert(newText,textArea.getCaretPosition());
		} else {
			newText = "<script type=\""+type+"\">\n<!-- Script Goes Here -->\n</script>";
			textArea.replaceSelection(newText);
		}
	}
	
	public void doPHP(){
		String newText = "<?PHP\n//PHP CPDE GOES HERE\n?>";
		if(textArea.getSelectedText() == null){
			textArea.insert(newText,textArea.getCaretPosition());
		} else {
			textArea.replaceSelection(newText);
		}
	}
	
	public void doHeader(){
		Object[] sizes = new Object[] {"1","2","3","4","5","6"};
		Object size = JOptionPane.showInputDialog(null,"Plase Choose a Header Size","Header",JOptionPane.QUESTION_MESSAGE,null,sizes,"1");
		String tag = "h"+size;
		String newText;
		if(textArea.getSelectedText() == null){
			newText = JOptionPane.showInputDialog(null,"Please Enter Text to Format","No Text Selelcted",JOptionPane.INFORMATION_MESSAGE);
			if(newText != null){
				newText = "<"+tag+">"+newText+"</"+tag+">";
				textArea.insert(newText,textArea.getCaretPosition());
			}
		} else {
			newText = "<"+tag+">"+textArea.getSelectedText()+"</"+tag+">";
			textArea.replaceSelection(newText);
		}
	}
	
	public void doAcronym(){
		String title = JOptionPane.showInputDialog(null,"Enter a Title for your Acronym","Insert Acronym",JOptionPane.INFORMATION_MESSAGE);
		String newText;
		if(textArea.getSelectedText() == null){
			newText = JOptionPane.showInputDialog(null,"Please Enter Acronym","No Text Selelcted",JOptionPane.INFORMATION_MESSAGE);
			if(newText != null){
				newText = "<acronym title=\""+title+"\">"+newText+"</acronym>";
				textArea.insert(newText,textArea.getCaretPosition());
			}
		} else {
			newText = "<acronym title=\""+title+"\">"+textArea.getSelectedText()+"</acronym>";
			textArea.replaceSelection(newText);
		}
	}
	
	public void doComment(){
		String newText;
		if(textArea.getSelectedText() == null){
			newText = JOptionPane.showInputDialog(null,"Please Enter Text to Comment","No Text Selelcted",JOptionPane.INFORMATION_MESSAGE);
			if(newText != null){
				newText = "<!-- "+newText+" -->";
				textArea.insert(newText,textArea.getCaretPosition());
			}
		} else {
			newText = "<!-- "+textArea.getSelectedText()+" -->";
			textArea.replaceSelection(newText);
		}
	}
	
	public void doTextAlignment(String align){
		String newText;
		if(textArea.getSelectedText() == null){
			newText = JOptionPane.showInputDialog(null,"Please Enter Text to "+align+" Align","No Text Selected",JOptionPane.INFORMATION_MESSAGE);
			if(newText != null){
				newText = "<p align=\""+align+"\">"+newText+"</p>";
				textArea.insert(newText,textArea.getCaretPosition());
			}
		} else {
			newText = "<p align=\""+align+"\">"+textArea.getSelectedText()+"</p>";
			textArea.replaceSelection(newText);
		}
	}
	
	public void doSingleTag(String tag){
		String newText = "<"+tag+" />";
		if(textArea.getSelectedText() == null){
			textArea.insert(newText,textArea.getCaretPosition());
		} else {
			textArea.replaceSelection(newText);
		}
	}
	
	public void doImage(){
		JFileChooser fg = new JFileChooser();
		fg.setDialogTitle("Select Image to Insert");
		fg.removeChoosableFileFilter(fg.getAcceptAllFileFilter());
        fg.setFileFilter(new ImageFilter());
		String fileName = "";
		int c = fg.showOpenDialog(null);
		if(c == JFileChooser.APPROVE_OPTION){
			// File Selected
			File temp = fg.getSelectedFile();
			fileName = temp.getName();
			String bdrSize = JOptionPane.showInputDialog(null,"Border Size (0-No Border)","0");
			String altText = JOptionPane.showInputDialog(null,"Alternate Text","");
			if(bdrSize == null){ bdrSize = "0"; }
			if(altText == null){ altText = ""; }
			String newText = "<img src=\"images\\"+fileName+"\" border=\""+bdrSize+"\" alt=\""+altText+"\" />";
			if(textArea.getSelectedText() == null){
				textArea.insert(newText,textArea.getCaretPosition());
			} else {
				textArea.replaceSelection(newText);
			}
			fe.moveImage(temp);
		} else {
			// File Dialog Cancelled
			System.out.println("Get Image Cancelled");
		}
	}
	
	public void createTable(){
		try{
			int rows = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter Number of Rows","Create Table",JOptionPane.INFORMATION_MESSAGE));
			int cols = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter Number of Columns","Create Table",JOptionPane.INFORMATION_MESSAGE));
			int width = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter Table Width in Percent (Use 50, not 50%)","Create Table",JOptionPane.INFORMATION_MESSAGE));
			
			String table = "<table width=\""+width+"%\">";
			for(int r=0;r<rows;r++){
				table += "\n<tr>";
				for(int c=0;c<cols;c++){
					table += "\n<td>&nbsp;</td>";
				}
				table += "\n</tr>";
			}
			table += "\n</table>";
			if(textArea.getSelectedText() == null){
				textArea.insert(table,textArea.getCaretPosition());
			} else {
				textArea.replaceSelection(table);
			}
		}catch(NumberFormatException e){
			System.out.println("Table Creation Cancelled");
		}
		
	}
	
	public void doCSSLink(){
		String newText = "";
		String url = JOptionPane.showInputDialog(null,"Please Enter URL for Style Sheet","Insert CSS Link",JOptionPane.INFORMATION_MESSAGE);
		if(url != null){
			newText = "<link rel=\"stylesheet\" type=\"text/css\" href=\""+url+"\" />";
			if(textArea.getSelectedText() == null){
				textArea.insert(newText,textArea.getCaretPosition());
			} else {
				textArea.replaceSelection(newText);
			}
		}		
	}
	
	public void doLink(){
		String newText = "";
		String url = JOptionPane.showInputDialog(null,"Please Enter URL for Link","Insert Hyperlink",JOptionPane.INFORMATION_MESSAGE);
		if(textArea.getSelectedText() == null){
			if(newText != null){
				newText = JOptionPane.showInputDialog(null,"Please Enter Text for Link","No Text Selected",JOptionPane.INFORMATION_MESSAGE);
				newText = "<a href=\""+url+"\">"+newText+"</a>";
				textArea.insert(newText,textArea.getCaretPosition());
			}
		} else {
			newText = "<a href=\""+url+"\">"+textArea.getSelectedText()+"</a>";
			textArea.replaceSelection(newText);
		}
	}
	
	public void doBookMark(){
		String newText = JOptionPane.showInputDialog(null,"Please Enter Name for BookMark","Insert Bookmark",JOptionPane.INFORMATION_MESSAGE);
		newText = newText.replaceAll(" ","");
		if(textArea.getSelectedText() == null){
			if(newText != null){
				newText = "<a name=\""+newText+"\"></a>";
				textArea.insert(newText,textArea.getCaretPosition());
			}
		} else {
			newText = "<a name=\""+newText+"\"></a>";
			textArea.replaceSelection(newText);
		}
	}
	
	public void makeSimplePage(){
		textArea.setText(simplePage);
	}
	
	public void insertColor(){
		Color c = JColorChooser.showDialog(null,"Choose a Color",Color.BLUE);
		String newText = "#"+RGBtoHEX(c.getRed(),c.getGreen(),c.getBlue());
		if(textArea.getSelectedText() == null){
			textArea.insert(newText,textArea.getCaretPosition());
		}else {
			textArea.replaceSelection(newText);
		}
	}
	
	public String RGBtoHEX(int r,int g, int b){
		String HEX = RGBtoHEX(r);
		HEX += RGBtoHEX(g);
		HEX += RGBtoHEX(b);
		return HEX;
	}
	
	private String RGBtoHEX(int c){
		String code = "";
		if(c == 0){
			code = "00";
		}else{
			String HEX = "0123456789ABCDEF";
			c = Math.max(0,c);
			c = Math.min(c,255);
			c = Math.round(c);
			code = HEX.charAt((c-c%16)/16)+ "";
			code += HEX.charAt(c%16);
		}
		return code;
	}
	
	public void makeMainPage(){
		textArea.setText("<html>\n<head>\n<title>Open Editor</title>\n</head>\n" +
			"<body>\n\n<p><b>Welcome to Open Editor<b></p>\n\n</body>\n</html>");
	}
	
	public void makePage(String title,String fname){
		String index = "<html>\n<head>\n<title>"+title+"</title>\n</head>\n" +
		"<body>\n\n<p><b>"+title+" Homepage<b></p>\n\n</body>\n</html>";
		try{
			File page = new File("C:\\OpenEditor\\My Sites\\"+title+"\\"+fname);
			PrintWriter fout = new PrintWriter(new FileWriter(page));
			fout.print(index);
			fout.close();
			textArea.setText(index);
		}catch(IOException e){
			System.err.println("Error Creating Index Page");
			e.printStackTrace();
		}
	}
}
