import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.LinkedList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/*
 * Created by Bryan Saunders
 * Created on Nov 24, 2004
 */

public class FileEditor {
	
	private String sourceCode;
	private static JFileChooser fileGrab = new JFileChooser();
	private static JTextArea textArea;
	private JFrame parent;
	private static File lastFile = null, lastTemp = null;
	
	private static LinkedList<String> validExts = new LinkedList<String>();
	
	public FileEditor(JTextArea ta, JFrame par){
		textArea = ta;
		parent = par;
        fileGrab.removeChoosableFileFilter(fileGrab.getAcceptAllFileFilter());
        fileGrab.setFileFilter(new PHPFilter());
        fileGrab.setFileFilter(new JSFilter());
        fileGrab.setFileFilter(new HTMLFilter());
        lastFile = null;
        
		
		validExts.add(".html");
		validExts.add(".htm");
		validExts.add(".shtml");
		validExts.add(".jhtml");
		validExts.add(".txt");
		validExts.add(".php");
		validExts.add(".php3");
		validExts.add(".inc");
		validExts.add(".js");
		validExts.add(".css");
		validExts.add(".cfm");
	}
	
	public FileEditor(){}

	public void open(){
		openCode();
	}
	
	public void openSpecific(File file){
		if(file.isFile()){
			String fileName = file.getName();
			String ext = fileName.substring(fileName.indexOf("."),fileName.length());
			if(validExts.contains(ext)){
				lastFile = file;
				sourceCode = parseFile(file);
				if(sourceCode != null){
					textArea.setText(sourceCode);
				} else {
					JOptionPane.showMessageDialog(null,"Error Occured While Opening. Could not Read File.","ERROR",JOptionPane.ERROR_MESSAGE);
				}
			}else{
				System.out.println("Tried to Open Invalid File Format: "+ext);
			}
		}else{
			System.out.println("Can Not Open Directory");
		}
	}
	
	public void save(){
		saveCode();
	}
	
	public void saveAs(){
		saveCodeAs();
	}
	
	public boolean makeNew(){
		return saveCode();
	}
		
	public boolean moveImage(File img){
		try{
			if(lastFile != null){
				String dir = lastFile.getPath();
				dir = dir.substring(0,dir.lastIndexOf("\\")+1);
				dir += "images\\";
				File imageDir = new File(dir);
				if(imageDir.exists()){
					String name = img.getName();
					File temp = new File(dir+name);
					copyFile(img,temp);
				}else{
					imageDir.mkdir();
					String name = img.getName();
					File temp = new File(dir+name);
					copyFile(img,temp);
				}
				System.out.println("Image Copied");
				return true;
			}else{
				JOptionPane.showMessageDialog(null,"Page Must be Saved to Import Image, Please Save.","ERROR",JOptionPane.ERROR_MESSAGE);
				saveCodeAs();
				return moveImage(img);
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Image Copy Failed");
			return false;
		}
	}
	
	private void copyFile(File source, File dest) throws IOException {
	     FileChannel in = null, out = null;
	     try {          
	          in = new FileInputStream(source).getChannel();
	          out = new FileOutputStream(dest).getChannel();
	          long size = in.size();
	          MappedByteBuffer buf = in.map(FileChannel.MapMode.READ_ONLY, 0, size);
	          out.write(buf);
	     } finally {
	          if(in != null) in.close();
	          if(out != null) out.close();
	     }
	}
	
	public void delTemp(){
		if(lastTemp != null){
			if(lastTemp.delete()){
				System.out.println("Temp Cleared");
			}else{
				System.out.println("Couldnt Clear Temp");
			}
		}
	}
	
	private boolean openCode(){
		String code = "";
		String fileName = "";
		int c = fileGrab.showOpenDialog(textArea);
		if(c == JFileChooser.APPROVE_OPTION){
			// File Selected
			fileName = fileGrab.getSelectedFile().getName();
			lastFile = fileGrab.getSelectedFile();
			sourceCode = parseFile(fileGrab.getSelectedFile());
			if(sourceCode != null){
				textArea.setText(sourceCode);
				return true;
			} else {
				JOptionPane.showMessageDialog(null,"Error Occured While Opening. Could not Read File.","ERROR",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			// File Dialog Cancelled
			System.out.println("Code Open Cancelled");
			return false;
		}
	}
	
	private String parseFile(File f){
		String code = "";
		try{
			BufferedReader fin = new BufferedReader(new FileReader(f));
			code += fin.readLine()+"\n";
			while(fin.ready()){
				code += fin.readLine()+"\n";
			}
			return code;
		}catch(IOException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Error Occured While Opening. Could not Read File.","ERROR",JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
	
	public String saveSiteIndex(String site){
		try{
			File tempFile = new File("C:\\OpenEditor\\My Sites\\"+site+"\\index.html");
			String fileName = tempFile.getName();
			lastFile = tempFile;
			PrintWriter fout = new PrintWriter(new FileWriter(tempFile));
			fout.print(textArea.getText());
			fout.close();
			//JOptionPane.showMessageDialog(null,"Code Saved to "+fileName+".","File Saved",JOptionPane.PLAIN_MESSAGE);
			System.out.println(fileName+" Saved.");
			String dir = tempFile.getPath();
			dir = dir.substring(0,dir.lastIndexOf("\\")+1);
			return dir;
		}catch(IOException e){
			JOptionPane.showMessageDialog(null,"Error Occured While Saving. Code Not Saved.","ERROR",JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	public boolean isFirstSave(){
		return (lastFile == null);
	}
	
	private boolean saveCodeAs(){
		String fileName;
		String fileDir;
		int c = fileGrab.showSaveDialog(textArea);
		if(c == JFileChooser.APPROVE_OPTION){
			// File Selected
			try{
				fileName = fileGrab.getSelectedFile().getName();
				if(!fileName.contains(".")){
					fileName = fileName+".html";
					fileGrab.setSelectedFile(new File(fileGrab.getCurrentDirectory()+"/"+fileName));
				}
				fileDir = fileGrab.getCurrentDirectory().getCanonicalPath();
				lastFile = fileGrab.getSelectedFile();
				PrintWriter fout = new PrintWriter(new FileWriter(lastFile));
				fout.print(textArea.getText());
				fout.close();
				JOptionPane.showMessageDialog(null,"Code Saved to "+fileName+".","File Saved",JOptionPane.PLAIN_MESSAGE);
				System.out.println(fileName+" Saved.");
				return true;
			}catch(IOException e){
				JOptionPane.showMessageDialog(null,"Error Occured While Saving. Code Not Saved.","ERROR",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			// File Dialog Cancelled
			System.out.println("Code Save Cancelled");
			return false;
		}
	}
	
	private boolean saveCode(){
		if(lastFile == null){
			return saveCodeAs();
		}else{
			try{
				String fileName = lastFile.getName();
				PrintWriter fout = new PrintWriter(new FileWriter(lastFile));
				fout.print(textArea.getText());
				fout.close();
				JOptionPane.showMessageDialog(null,"Code Saved to "+fileName+".","File Saved",JOptionPane.PLAIN_MESSAGE);
				System.out.println(fileName+" Saved.");
				return true;
			}catch(IOException e){
				JOptionPane.showMessageDialog(null,"Error Occured While Saving. Code Not Saved.","ERROR",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
	}
	
	public String makeTemp()throws IOException{
		try{
			String dir = lastFile.getPath();
			dir = dir.substring(0,dir.lastIndexOf("\\")+1);
			String name = "oe_temp.html";
			File temp = new File(dir+name);
			PrintWriter fout = new PrintWriter(new FileWriter(temp));
			fout.print(textArea.getText());
			fout.close();
			lastTemp = temp;
			return temp.getPath();
		}catch(IOException e){
			JOptionPane.showMessageDialog(null,"Error Creating Temporary File.","ERROR",JOptionPane.ERROR_MESSAGE);
			return null;
		}catch(NullPointerException e){
			saveCodeAs();
			if(lastFile != null){
				return lastFile.getPath();
			}else{
				return null;
			}
			
		}
		//return "http://www.openeditor.org";
	}
	
	public void newSite(String name){
		File mysites = new File("C:\\OpenEditor\\My Sites\\");
		if(mysites.exists()){
			File site = new File("C:\\OpenEditor\\My Sites\\"+name+"\\");
			if(site.mkdir()){
				System.out.println("Site "+name+" Created");
			}else{
				System.out.println("Couldnt Make Site");
			}
		}else{
			if(mysites.mkdir()){
				System.out.println("My Sites Folder Created");
				newSite(name);
			}else{
				System.out.println("Couldnt Make My Sites Folder");
			}
		}
	}

}
