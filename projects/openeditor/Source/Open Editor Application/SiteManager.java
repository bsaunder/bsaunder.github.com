import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
/*
 * Created by Bryan Saunders
 * Created on Dec 7, 2004
 * 
 * To Do
 * ---------
 * Empty Folder Look Like Folders
 * 		Do Not Have Blank Node
 */

public class SiteManager extends JScrollPane implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private static DefaultMutableTreeNode top = new DefaultMutableTreeNode("My Sites");
	private static JTree sitetree = new JTree(top);
	
	private FileEditor fe;
	
	private JMenuItem jmOpen = new JMenuItem("Open");
	private JMenu menuNew = new JMenu("New..");
	private JMenuItem jmNewFile = new JMenuItem("File");
	private JMenuItem jmNewFolder = new JMenuItem("Folder");
	private JMenuItem jmDelete = new JMenuItem("Delete");
	private JMenuItem jmRename = new JMenuItem("Rename");
	private JMenuItem jmCopy = new JMenuItem("Copy");
	private JMenuItem jmPaste = new JMenuItem("Paste");
	private JMenuItem jmRefresh = new JMenuItem("Refresh");
	public JPopupMenu popup = new JPopupMenu();
	
	private File copiedFile = null;
	
	public SiteManager(FileEditor f){
		super(sitetree);
		this.fe = f;
		DefaultTreeCellRenderer siterend = (DefaultTreeCellRenderer)sitetree.getCellRenderer();
		siterend.setBackgroundNonSelectionColor(new Color(220,220,220));
		siterend.setBackgroundSelectionColor(new Color(150,200,255));
		siterend.setTextSelectionColor(Color.BLACK);
		//siterend.setOpenIcon(new ImageIcon("images/win_folder_open.gif"));
		//siterend.setClosedIcon(new ImageIcon("images/win_folder_closed.gif"));
		siterend.setLeafIcon(new ImageIcon("images/win_file.gif"));
		sitetree.setCellRenderer(siterend);
		sitetree.setBackground(new Color(220,220,220));
		sitetree.setToolTipText("Site Manager");
		
		 // Create Mouse Listener for Tree
        MouseListener ml = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int selRow = sitetree.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = sitetree.getPathForLocation(e.getX(), e.getY());
                if(selRow != -1) {
                	if(e.getClickCount() == 2) {
                		String path = makePath(selPath.getPath());
                		File tfile = new File("C:\\OpenEditor\\"+path);
                		if(tfile.isFile()){
                			fe.openSpecific(tfile);
                		}
                    }
                }
            }
            
            private String makePath(Object[] p){
            	String temp = "";
        		for(int i=0;i<p.length;i++){
        			if(i != p.length-1){
        				temp += p[i].toString()+"\\";
        			}else{
        				temp += p[i].toString();
        			}
        		}
        		return temp;
            }
        };
        
        // Setup Tree
        sitetree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        sitetree.addMouseListener(ml);
        
        jmOpen.addActionListener(this);
        jmDelete.addActionListener(this);
        jmCopy.addActionListener(this);
        jmPaste.addActionListener(this);
        jmRefresh.addActionListener(this);
        jmNewFile.addActionListener(this);
		jmNewFolder.addActionListener(this);
		jmRename.addActionListener(this);
        
        popup.add(jmOpen);
        popup.add(menuNew);
        menuNew.add(jmNewFile);
        menuNew.add(jmNewFolder);
        popup.addSeparator();
        popup.add(jmDelete);
        popup.add(jmRename);
        popup.addSeparator();
        popup.add(jmCopy);
        popup.add(jmPaste);
        popup.addSeparator();
        popup.add(jmRefresh);
		
        sitetree.addMouseListener(new MousePopListener());
        sitetree.setComponentPopupMenu(popup);
		
	}
	
	public void addSite(File root){
		DefaultMutableTreeNode site;
		DefaultMutableTreeNode folder;
		DefaultMutableTreeNode file;
		
		if(root.isDirectory()){
			site = new DefaultMutableTreeNode(root.getName());
			top.add(site);
			
			File[] list = root.listFiles();
			list = sortList(list);
			if(list.length >= 1){
				for(int i=0;i<list.length;i++){
					if(list[i].isDirectory()){
						// Add Directory
						folder = new DefaultMutableTreeNode(list[i].getName());
						site.add(folder);
						makeDirectory(folder,list[i]);
					}else{
						// Add File
						file = new DefaultMutableTreeNode(list[i].getName());
						site.add(file);
					}
				}
			}else{
				file = new DefaultMutableTreeNode("");
				top.add(file);
			}
		}else{
			System.out.println("ERROR ADDING SITE, ROOT NOT DIRECTORY");
		}
		refresh();
	}
	
	private File[] sortList(File[] oldList){
		File[] temp = new File[oldList.length];
		int index = 0;
		
		for(int d = 0;d<oldList.length;d++){
			if(oldList[d].isDirectory()){
				temp[index++] = oldList[d];
			}
		}
		
		for(int f = 0;f<oldList.length;f++){
			if(oldList[f].isFile()){
				temp[index++] = oldList[f];
			}
		}
		
		return temp;
	}
	
	public void refresh(){
		top.removeAllChildren();
		makeDirectory(top,new File("C:\\OpenEditor\\My Sites"));
		update();
	}
	
	private void update(){
		DefaultTreeModel treeModel = new DefaultTreeModel(top);
		sitetree.setModel(treeModel);
	}
	
	private void makeDirectory(DefaultMutableTreeNode parent,File dir){
		DefaultMutableTreeNode folder;
		DefaultMutableTreeNode file;
		
		if(dir.isDirectory()){
			File[] list = dir.listFiles();
			list = sortList(list);
			if(list.length >= 1){
				for(int i=0;i<list.length;i++){
					if(list[i].isDirectory()){
						// Add Directory
						folder = new DefaultMutableTreeNode(list[i].getName());
						parent.add(folder);
						makeDirectory(folder,list[i]);
					}else{
						// Add File
						file = new DefaultMutableTreeNode(list[i].getName());
						parent.add(file);
					}
				}
			}else{
				file = new DefaultMutableTreeNode("");
				parent.add(file);
			}
		}else{
			System.out.println("ERROR ADDING SITE, ROOT NOT DIRECTORY");
		}
	}
	
	class MousePopListener extends MouseAdapter{
		public void mousePressed(MouseEvent e){ checkPopup(e); }
		public void mouseReleased(MouseEvent e){ checkPopup(e); }
		public void mouseClicked(MouseEvent e){ checkPopup(e); }
		
		public void checkPopup(MouseEvent e){
			if(e.isPopupTrigger()){
				popup.setInvoker(sitetree);
				popup.setLocation(e.getX(),e.getY());
				popup.setVisible(true);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jmOpen){
			try{
				TreePath selPath = sitetree.getSelectionPath();
				String path = makePath(selPath.getPath());
	    		File tfile = new File("C:\\OpenEditor\\"+path);
	    		if(tfile.isFile()){
	    			fe.openSpecific(tfile);
	    		}else{
	    			sitetree.expandPath(selPath);
	    		}
			}catch(Exception ex){
				System.out.println("Site Manager Error");
				ex.printStackTrace();
			}
		}else if(e.getSource() == jmRefresh){
			refresh();
		}else if(e.getSource() == jmDelete){
			try{
				TreePath selPath = sitetree.getSelectionPath();
				String path = makePath(selPath.getPath());
	    		File tfile = new File("C:\\OpenEditor\\"+path);
	    		if(tfile.isDirectory()){
	    			deleteDir(tfile);
	    		}else{
	    			if(tfile.delete()){
		    			System.out.println("File Deleted");
		    			refresh();
		    		}else{
		    			System.out.println("Could Not Delete File");
		    		}
	    		}
			}catch(Exception ex){
				System.out.println("Site Manager Error");
				ex.printStackTrace();
			}
			refresh();
		}else if(e.getSource() == jmRename){
			try{
				TreePath selPath = sitetree.getSelectionPath();
				String path = makePath(selPath.getPath());
	    		File tfile = new File("C:\\OpenEditor\\"+path);
	    		if(tfile.isFile()){
	    			String name = JOptionPane.showInputDialog(null,"Enter New Filename","Rename File",JOptionPane.QUESTION_MESSAGE);
	    			File newFile = new File("C:\\OpenEditor\\"+makeNamelessPath(selPath.getPath())+name);
	    			tfile.renameTo(newFile);
	    		}else{
	    			sitetree.expandPath(selPath);
	    		}
			}catch(Exception ex){
				System.out.println("Site Manager Error");
				ex.printStackTrace();
			}
			refresh();
		}else if(e.getSource() == jmCopy){
			try{
				TreePath selPath = sitetree.getSelectionPath();
				String path = makePath(selPath.getPath());
	    		File tfile = new File("C:\\OpenEditor\\"+path);
	    		if(tfile.isFile()){
	    			copiedFile = tfile;
	    		}
			}catch(Exception ex){
				System.out.println("Site Manager Error");
				ex.printStackTrace();
			}
		}else if(e.getSource() == jmPaste){
			try{
				TreePath selPath = sitetree.getSelectionPath();
				String path = makePath(selPath.getPath());
	    		File tfile = new File("C:\\OpenEditor\\"+path);
	    		if(tfile.isDirectory()){
	    			File newFile = new File(tfile.getPath()+"\\"+copiedFile.getName());
	    			copyFile(copiedFile,newFile);
	    		}
			}catch(Exception ex){
				System.out.println("Site Manager Error");
				ex.printStackTrace();
			}
			refresh();
		}else if(e.getSource() == jmNewFile){
			try{
				TreePath selPath = sitetree.getSelectionPath();
				String path = makePath(selPath.getPath());
	    		File tfile = new File("C:\\OpenEditor\\"+path);
	    		if(tfile.isFile()){
	    			String name = JOptionPane.showInputDialog(null,"Enter New File Name","New File",JOptionPane.QUESTION_MESSAGE);
	    			File newFile = new File("C:\\OpenEditor\\"+makeNamelessPath(selPath.getPath())+name);
	    			newFile.createNewFile();
	    		}else{
	    			String name = JOptionPane.showInputDialog(null,"Enter New File Name","New File",JOptionPane.QUESTION_MESSAGE);
	    			File newFile = new File("C:\\OpenEditor\\"+path+"\\"+name);
	    			newFile.createNewFile();
	    		}
	    		refresh();
			}catch(Exception ex){
				System.out.println("Site Manager Error");
				ex.printStackTrace();
			}
		}else if(e.getSource() == jmNewFolder){
			try{
				TreePath selPath = sitetree.getSelectionPath();
				String path = makePath(selPath.getPath());
	    		File tfile = new File("C:\\OpenEditor\\"+path);
	    		if(tfile.isFile()){
	    			String name = JOptionPane.showInputDialog(null,"Enter New Folder Name","New Folder",JOptionPane.QUESTION_MESSAGE);
	    			File newFile = new File("C:\\OpenEditor\\"+makeNamelessPath(selPath.getPath())+name);
	    			newFile.mkdir();
	    		}else{
	    			String name = JOptionPane.showInputDialog(null,"Enter New Folder Name","New Folder",JOptionPane.QUESTION_MESSAGE);
	    			File newFile = new File("C:\\OpenEditor\\"+path+"\\"+name);
	    			newFile.mkdir();
	    		}
	    		refresh();
			}catch(Exception ex){
				System.out.println("Site Manager Error");
				ex.printStackTrace();
			}
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
	
    private String makePath(Object[] p){
    	String temp = "";
		for(int i=0;i<p.length;i++){
			if(i != p.length-1){
				temp += p[i].toString()+"\\";
			}else{
				temp += p[i].toString();
			}
		}
		return temp;
    }
    
    private String makeNamelessPath(Object[] p){
    	String temp = "";
		for(int i=0;i<p.length;i++){
			if(i != p.length-1){
				temp += p[i].toString()+"\\";
			}else{
				//temp += p[i].toString();
			}
		}
		return temp;
    }
    
    private void deleteDir(File dir){
    	File[] list = dir.listFiles();
    	if(list.length > 0){
    		for(int i=0;i<list.length;i++){
    			if(list[i].isFile()){
    				list[i].delete();
    			}else{
    				deleteDir(list[i]);
    			}
    		}
    	}
    	dir.delete();
    }
}

