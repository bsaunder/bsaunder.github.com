import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
/*
 * Created by Bryan Saunders
 * Created on Dec 3, 2004
 */

public class EditorToolBar extends JToolBar implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JButton btnStrong, btnEm, btnUnderline, btnRule;
    private JButton btnImg, btnHref;
    private JButton btnR, btnL, btnC, btnJ, btnUndo, btnRedo;
    private JButton btnCut, btnCopy, btnPaste, btnOpen, btnSave;
	
    private FileEditor fg = new FileEditor();
    private TagCreator tagC;
    private UndoRedo edit;
	private JTextArea textArea;
	
	public EditorToolBar(String name,JTextArea ta,UndoRedo ur,TagCreator tc){
        // Setup Toolbar
		super(name);
        this.textArea = ta;
        this.tagC = tc;
        this.edit = ur;
        
        // Configure & Add Toolbar Buttons
        makeToolbarButtons();
        addToolbarButtons();
	}
	
	public void actionPerformed(ActionEvent e)
    {
		int carotPos = textArea.getCaretPosition();
        if(e.getSource() == btnOpen){
        	fg.open();
        }else if(e.getSource() == btnSave){
        	fg.save();
        }else if(e.getSource() == btnCopy){
        	textArea.copy();
        }else if(e.getSource() == btnCut){
        	textArea.cut();
        }else if(e.getSource() == btnPaste){
        	textArea.paste();
        }else if(e.getSource() == btnUndo){
			edit.doUndo();
        }else if(e.getSource() == btnRedo){
        	edit.doRedo();
        }else if(e.getSource() == btnStrong){
        	tagC.doNonAttrTag("b");
        }else if(e.getSource() == btnEm){
        	tagC.doNonAttrTag("i");
        }else if(e.getSource() == btnUnderline){
        	tagC.doNonAttrTag("u");
        }else if(e.getSource() == btnR){
        	tagC.doTextAlignment("right");
        }else if(e.getSource() == btnL){
        	tagC.doTextAlignment("left");
        }else if(e.getSource() == btnC){
        	tagC.doTextAlignment("center");
        }else if(e.getSource() == btnJ){
        	tagC.doTextAlignment("justify");
        }else if(e.getSource() == btnRule){
        	tagC.doSingleTag("hr");
        }else if(e.getSource() == btnImg){
        	tagC.doImage();
        }else if(e.getSource() == btnHref){
        	tagC.doLink();
        }
        textArea.requestFocus();
        textArea.setCaretPosition(carotPos);
    }
	
	private void makeToolbarButtons(){
        btnStrong = makeToolBarButton("strong","bold","Makes Text Bold");
		btnEm = makeToolBarButton("emphasize","italic","Makes Text Italics");
		btnUnderline = makeToolBarButton("underline","underline","Makes Text Underlined");
		btnRule = makeToolBarButton("rule","rule","Makes Horizontal Rule");
        btnImg = makeToolBarButton("image","image","Inserts an Image");
		btnHref = makeToolBarButton("href","link","Creates a Hyperlink");
        btnR = makeToolBarButton("right","right","Right Aligns Text");
		btnL = makeToolBarButton("left","left","Left Aligns Text");
		btnC = makeToolBarButton("center","center","Center Aligns Text");
		btnJ = makeToolBarButton("justify","justify","Justifys Text");
        btnCut = makeToolBarButton("cut","cut","Move Selected Text to Clipboard");
		btnCopy = makeToolBarButton("copy","copy","Copy Selected Text to Clipboard");
		btnPaste = makeToolBarButton("paste","paste","Insert Text from Clipboard");
		btnOpen = makeToolBarButton("open","open","Opens a File");
		btnSave = makeToolBarButton("save","save","Save the Current File");
		btnUndo = makeToolBarButton("undo","undo","Undo Last Action");
		btnRedo = makeToolBarButton("redo","redo","Redo Last Action");
	}
	
    private JButton makeToolBarButton(String name, String image, String tooltip){
    	// Create and initialize the button.
        JButton button = new JButton();
        button.setToolTipText(tooltip);
        button.addActionListener(this);

        if (image != null) {
            button.setIcon(new ImageIcon("images/"+image+".gif", tooltip));
        } else {                                     //no image found
            button.setText(name.substring(0,2));
            System.err.println("Resource not found: " + image+".gif");
        }

        return button;
    }
	
	private void addToolbarButtons(){
		this.add(btnOpen);
		this.add(btnSave);
		this.addSeparator(new Dimension(9,0));
		this.add(btnCut);
		this.add(btnCopy);
		this.add(btnPaste);
		this.addSeparator(new Dimension(9,0));
		this.add(btnUndo);
		this.add(btnRedo);
		this.addSeparator(new Dimension(9,0));
		this.add(btnStrong);
		this.add(btnEm);
		this.add(btnUnderline);
		this.addSeparator(new Dimension(9,0));
		this.add(btnL);
		this.add(btnC);
		this.add(btnR);
		this.add(btnJ);
		this.addSeparator(new Dimension(9,0));
		this.add(btnRule);
		this.add(btnImg);
		this.add(btnHref);
	}
	
}
