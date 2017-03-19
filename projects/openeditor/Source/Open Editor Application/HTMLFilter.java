import java.io.File;

import javax.swing.filechooser.FileFilter;
/*
 * Created by Bryan Saunders
 * Created on Nov 23, 2004
 */

public class HTMLFilter extends FileFilter {
	
	private String[] validTypes = new String[4];
	private String description;

	public HTMLFilter() {
		super();
		validTypes[0] = ".html";
		validTypes[1] = ".htm";
		validTypes[2] = ".shtml";
		validTypes[3] = ".jhtml";
		description = "HTML files (";
		for(int i = 0; i<validTypes.length ; i++){
			description += "*"+validTypes[i]+",";
		}
		description = description.substring(0,description.length()-1);
		description += ")";
	}

	public boolean accept(File f) {
		if(f.isDirectory()){
			return true;
		}else{
			String name = f.getName().toLowerCase();
			for(int i = 0; i<validTypes.length ; i++){
				if(name.endsWith(validTypes[i])){
					return true;
				}
			}
		}
		return false;
	}

	public String getDescription() {
		return description;
	}

}
