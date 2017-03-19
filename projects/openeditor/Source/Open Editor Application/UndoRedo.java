import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

/*
 * Created by Bryan Saunders
 * Created on Nov 25, 2004
 */

public class UndoRedo {
    private JTextArea textArea;
	private final UndoManager undo = new UndoManager();
    private Document doc;
    
    public UndoRedo(JTextArea ta){
    	this.textArea = ta;
    	doc = textArea.getDocument();
	    doc.addUndoableEditListener(new UndoableEditListener() {
	        public void undoableEditHappened(UndoableEditEvent evt) {
	            undo.addEdit(evt.getEdit());
	        }
	    });
	    makeUndo();
	    makeRedo();
    }
    
    private void makeUndo(){
		textArea.getActionMap().put("Undo",
		    new AbstractAction("Undo") {
				private static final long serialVersionUID = 1L;
				public void actionPerformed(ActionEvent evt) {
		            doUndo();
		        }
		   });
		textArea.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");
    }
    
    private void makeRedo(){
		textArea.getActionMap().put("Redo",
		    new AbstractAction("Redo") {
				private static final long serialVersionUID = 1L;
				public void actionPerformed(ActionEvent evt) {
		        	doRedo();
		        }
		    });
		
		// Bind the redo action to ctl-Y
		textArea.getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Redo");
    }
    
    public void doUndo(){
		try{
            if(undo.canUndo()) {
            	undo.undo();
            }else{
            	JOptionPane.showMessageDialog(null,"Can Not Undo","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        }catch (CannotUndoException arg) {
			System.out.println("Undo Exception");
			arg.printStackTrace();
		}
	}
	
	public void doRedo(){
		try{
            if(undo.canRedo()) {
            	undo.redo();
            }else{
            	JOptionPane.showMessageDialog(null,"Can Not Redo","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        }catch (CannotRedoException arg) {
			System.out.println("Redo Exception");
			arg.printStackTrace();
		}
	}
}
