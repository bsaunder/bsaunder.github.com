import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;


/*
 * Created by Bryan Saunders
 * Created on Nov 25, 2004
 */

public class EditorMenu extends JMenuBar implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JMenu menuFile = new JMenu("File");
	private JMenu jmNew = new JMenu("New");
	private JMenuItem jmNewBlank = new JMenuItem("Blank");
	private JMenuItem jmNewSite = new JMenuItem("Site");
	private JMenuItem jmNewSimple = new JMenuItem("Simple");
    private JMenuItem jmOpen = new JMenuItem("Open");
    private JMenuItem jmSave = new JMenuItem("Save");
    private JMenuItem jmSaveAs = new JMenuItem("Save As..");
    private JMenuItem jmPrint = new JMenuItem("Print");
    private JMenuItem jmExit = new JMenuItem("Exit");
    
    private JMenu menuEdit = new JMenu("Edit");
    private JMenuItem jmCut = new JMenuItem("Cut");
    private JMenuItem jmCopy = new JMenuItem("Copy");
    private JMenuItem jmPaste = new JMenuItem("Paste");
    private JMenuItem jmUndo = new JMenuItem("Undo");
    private JMenuItem jmRedo = new JMenuItem("Redo");
    
    private JMenu menuFind = new JMenu("Find");
    private JMenuItem jmFind = new JMenuItem("Find..");
    private JMenuItem jmFindNext = new JMenuItem("Find Next..");
    private JMenuItem jmFindReplace = new JMenuItem("Find & Replace..");
    
    private JMenu menuTools = new JMenu("Tools");
    private JMenuItem jmPage = new JMenuItem("Page Properties");
    private JMenuItem jmDTD = new JMenuItem("Edit DTD");
    private JMenuItem jmFTP = new JMenuItem("Launch FTP");
    private JMenuItem jmW3C = new JMenuItem("Open Validator");
    
    private JMenu menuInsert = new JMenu("Insert...");
    private JMenuItem jmColor = new JMenuItem("Color Code");
    private JMenuItem jmTable = new JMenuItem("Table");
    
    private JMenu menuScripts = new JMenu("Meta Scripts");
    private JMenuItem jmMetaRefresh = new JMenuItem("Page Refresh");
    private JMenuItem jmPageRedirect = new JMenuItem("Page Redirect");
    
    private JMenu menuOptions = new JMenu("Options");
    private JCheckBoxMenuItem jmWrap = new JCheckBoxMenuItem("Word Wrap",false);
    private JMenuItem jmEditFont = new JMenuItem("Edit Font/Size");
    
    private JMenu menuPrev = new JMenu("Preview");
    private JMenuItem jmPrevFrame = new JMenuItem("OE Viewer");
    private JMenuItem jmPrevIE = new JMenuItem("Default Browser");
    
    private JMenu menuWindow = new JMenu("Window");
    private JMenuItem jmOrigSize = new JMenuItem("Original Size");
    
    private JMenu menuHelp = new JMenu("Help");
    private JMenuItem jmAbout = new JMenuItem("About");
    private JMenuItem jmSimple = new JMenuItem("Simple Page");
    private JMenuItem jmHelp = new JMenuItem("Help");
    private JMenuItem jmHome = new JMenuItem("Visit Website");
    
    private TextFinder find;
    private PageProps pp;
    private EditorPrefs ep;
    private TagCreator tagC;
    private JTextArea textArea;
    private FileEditor fg;
    private UndoRedo edit;
    private String title;
    private JFrame parent;
    private SiteManager sm;
    private EditorAbout about = new EditorAbout();
    
    public JPopupMenu popup = new JPopupMenu();
    
    public EditorMenu(JFrame par,TagCreator tc, JTextArea ta,FileEditor file,UndoRedo ur,String name,SiteManager sitetree){
    	this.tagC = tc;
    	this.textArea = ta;
    	this.fg = file;
    	this.edit = ur;
    	this.title = name;
    	this.sm = sitetree;
    	this.find = new TextFinder(textArea);
    	this.ep = new EditorPrefs(textArea);
    	this.pp = new PageProps(textArea);
    	this.parent = par;
    	
        this.add(menuFile);
        menuFile.add(jmNew);
        jmNew.add(jmNewSite);
        jmNew.addSeparator();
        jmNew.add(jmNewBlank);
        jmNew.add(jmNewSimple);
        menuFile.addSeparator();
        menuFile.add(jmOpen);
        menuFile.add(jmSave);
        menuFile.add(jmSaveAs);
        menuFile.addSeparator();
        menuFile.add(jmPrint);
        menuFile.addSeparator();
        menuFile.add(jmExit);
        //jmPrint.setEnabled(false);
        
        this.add(menuEdit);
        menuEdit.add(jmCut);
        menuEdit.add(jmCopy);
        menuEdit.add(jmPaste);
        menuEdit.addSeparator();
        menuEdit.add(jmUndo);
        menuEdit.add(jmRedo);
        
        this.add(menuFind);
        menuFind.add(jmFind);
        menuFind.add(jmFindNext);
        menuFind.add(jmFindReplace);
        
        this.add(menuTools);
        menuTools.add(jmPage);
        menuTools.add(jmDTD);
        menuTools.addSeparator();
        menuTools.add(jmFTP);
        menuTools.addSeparator();
        menuTools.add(jmW3C);
        jmFTP.setEnabled(false);
        
        this.add(menuInsert);
        menuInsert.add(jmColor);
        menuInsert.add(jmTable);
        menuInsert.addSeparator();
        menuInsert.add(menuScripts);
        menuScripts.add(jmMetaRefresh);
        menuScripts.add(jmPageRedirect);

        
        this.add(menuPrev);
        //menuPrev.add(jmPrevFrame);
        menuPrev.add(jmPrevIE);
        
        this.add(menuWindow);
        menuWindow.add(jmOrigSize);
        
        this.add(menuOptions);
        menuOptions.add(jmWrap);
        menuOptions.add(jmEditFont);
        jmWrap.setSelected(false);
        
        this.add(menuHelp);
        menuHelp.add(jmSimple);
        menuHelp.addSeparator();
        menuHelp.add(jmHelp);
        menuHelp.addSeparator();
        menuHelp.add(jmAbout);
        menuHelp.add(jmHome);
        jmHelp.setEnabled(false);
        
        jmAbout.addActionListener(this);
        jmOpen.addActionListener(this);
		jmSave.addActionListener(this);
		jmExit.addActionListener(this);
		jmCut.addActionListener(this);
		jmCopy.addActionListener(this);
		jmPaste.addActionListener(this);
		jmSimple.addActionListener(this);
		jmUndo.addActionListener(this);
		jmRedo.addActionListener(this);
		jmWrap.addActionListener(this);
		jmColor.addActionListener(this);
		jmPage.addActionListener(this);
		jmDTD.addActionListener(this);
		jmPrint.addActionListener(this);
		jmPrevFrame.addActionListener(this);
		jmHelp.addActionListener(this);
		jmPrevIE.addActionListener(this);
		jmEditFont.addActionListener(this);
		jmMetaRefresh.addActionListener(this);
        jmPageRedirect.addActionListener(this);
        jmTable.addActionListener(this);
        jmFTP.addActionListener(this);
        jmHome.addActionListener(this);
        jmW3C.addActionListener(this);
        jmSaveAs.addActionListener(this);
        jmFind.addActionListener(this);
		jmFindNext.addActionListener(this);
		jmFindReplace.addActionListener(this);
		jmNew.addActionListener(this);
		jmNewBlank.addActionListener(this);
		jmNewSimple.addActionListener(this);
		jmNewSite.addActionListener(this);
		jmOrigSize.addActionListener(this);
		
		popup.add(jmSave);
		popup.addSeparator();
		popup.add(jmCut);
		popup.add(jmCopy);
		popup.add(jmPaste);
		popup.addSeparator();
		//popup.add(jmPrevIE);
		popup.add(jmColor);
		
		textArea.addMouseListener(new MousePopListener());
		textArea.setComponentPopupMenu(popup);
    }
    
    public void resetSize(){
    	parent.setSize(650,600);
    }
    
    class MousePopListener extends MouseAdapter{
    	public void mousePressed(MouseEvent e){ checkPopup(e); }
    	public void mouseReleased(MouseEvent e){ checkPopup(e); }
    	public void mouseClicked(MouseEvent e){ checkPopup(e); }
    	
    	public void checkPopup(MouseEvent e){
    		if(e.isPopupTrigger()){
    			popup.setInvoker(textArea);
    			popup.setLocation(e.getX(),e.getY());
    			popup.setVisible(true);
    		}
    	}
    }

	public void actionPerformed(ActionEvent e) {
		int carotPos = textArea.getCaretPosition();
		if(e.getSource() == jmAbout){
        	about.open();
        }else if(e.getSource() == jmOpen){
        	fg.open();
        }else if(e.getSource() == jmSave){
        	fg.save();
        }else if(e.getSource() == jmSaveAs){
        	fg.saveAs();
        }else if(e.getSource() == jmCopy){
        	textArea.copy();
        }else if(e.getSource() == jmCut){
        	textArea.cut();
        }else if(e.getSource() == jmPaste){
        	textArea.paste();
        }else if(e.getSource() == jmUndo){
			edit.doUndo();
        }else if( e.getSource() == jmRedo){
        	edit.doRedo();
        }else if(e.getSource() == jmExit){
        	this.setVisible(false);
        }else if(e.getSource() == jmSimple){
        	tagC.makeSimplePage();
        	tagC.makeDTDXHTMLTrans();
        }else if(e.getSource() == jmWrap){
        	System.out.println(jmWrap.getState());
        	if(jmWrap.getState()){
        		textArea.setLineWrap(true);
        		textArea.setWrapStyleWord(true);
        	}else{
        		textArea.setLineWrap(false);
        		textArea.setWrapStyleWord(false);
        	}
        }else if(e.getSource() == jmColor){
        	tagC.insertColor();
        }else if(e.getSource() == jmPrint){
        	Print pm = new Print(parent,textArea,textArea.getFont());
        }else if(e.getSource() == jmPrevFrame){
        	PreviewPane prev = new PreviewPane("Page Preview",textArea.getText());
        }else if(e.getSource() == jmDTD){
        	tagC.makeDTD();
        }else if(e.getSource() == jmPrevIE){
        	try{
        		String fname = fg.makeTemp();
        		if(fname != null){
        			BrowserLauncher.openURL(fname);
            		fg.delTemp();
        		}else{
        			JOptionPane.showMessageDialog(null,"Must Save File First.","ERROR",JOptionPane.ERROR_MESSAGE);
        		}
        	}catch(IOException ex){
        		System.err.println("Browser Error");
        		ex.printStackTrace();
        	}
        }else if(e.getSource() == jmW3C){
        	try{
        		BrowserLauncher.openURL("http://validator.w3.org/");
        	}catch(IOException ex){
        		System.err.println("Browser Error");
        		ex.printStackTrace();
        	}
        }else if(e.getSource() == jmHome){
        	try{
        		BrowserLauncher.openURL("http://www.openeditor.org/");
        	}catch(IOException ex){
        		System.err.println("Browser Error");
        		ex.printStackTrace();
        	}
        }else if(e.getSource() == jmMetaRefresh){
        	tagC.insertRefresh();
        }else if(e.getSource() == jmPageRedirect){
        	tagC.insertRedirect();
        }else if(e.getSource() == jmFind){
        	find.showFind();
        }else if(e.getSource() == jmFindNext){
        	find.findNext();
        }else if(e.getSource() == jmFindReplace){
        	find.showFindReplace();
        }else if(e.getSource() == jmEditFont){
        	ep.open();
        }else if(e.getSource() == jmPage){
        	pp.open();
        }else if(e.getSource() == jmNewSimple){
        	if(fg.makeNew()){
        		tagC.makeSimplePage();
        		tagC.makeDTDXHTMLTrans();
        	}
        }else if(e.getSource() == jmNewBlank){
        	if(fg.makeNew()){
        		textArea.setText("");
        	}
        }else if(e.getSource() == jmTable){
        	tagC.createTable();
        }else if(e.getSource() == jmNewSite){
        	if(!fg.isFirstSave()){
        		fg.save();
        	}
        	
        	String name = JOptionPane.showInputDialog(null,"Enter The Site Name","New Site...",JOptionPane.QUESTION_MESSAGE);
    		if(name != null){
    			fg.newSite(name);
    			tagC.makePage(name,"index.html");
    			String path = fg.saveSiteIndex(name);
    			JOptionPane.showMessageDialog(null,"Site Created in "+path,"Site Created",JOptionPane.INFORMATION_MESSAGE);
    		}
    		
    		sm.addSite(new File("C:\\OpenEditor\\My Sites\\"+name));
        }else if(e.getSource() == jmOrigSize){
        	resetSize();
        }
        textArea.requestFocus();
        textArea.setCaretPosition(carotPos);
	}

}
