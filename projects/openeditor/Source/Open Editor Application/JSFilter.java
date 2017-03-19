import java.io.File;

import javax.swing.filechooser.FileFilter;
/*
 * Created by Bryan Saunders
 * Created on Nov 23, 2004
 */

public class JSFilter extends FileFilter {
	
	private String[] validTypes = new String[2];
	private String description;

	public JSFilter() {
		super();
		validTypes[0] = ".js";
		validTypes[1] = ".jscript";
		description = "JavaScript files (";
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
