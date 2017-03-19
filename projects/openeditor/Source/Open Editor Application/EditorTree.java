import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/*
 * Created by Bryan Saunders
 * Created on Nov 24, 2004
 * 
 */

public class EditorTree extends JScrollPane{

	private static final long serialVersionUID = 1L;
	// Create Tag Tree
	private TagCreator tagC;
	private JTextArea textArea;
	private static DefaultMutableTreeNode top = new DefaultMutableTreeNode("XHTML Transitional");
	private static JTree tree = new JTree(top);
	private Font arial = new Font("SansSerif",0,12);
	
	public EditorTree(TagCreator tc,JTextArea ta) {
		super(tree);
		//this.add(tree);
		this.textArea = ta;
		this.tagC = tc;
		populateTree(top);
		
		// Create Renderer
		DefaultTreeCellRenderer dtcr = (DefaultTreeCellRenderer)tree.getCellRenderer();
		dtcr.setBackground(new Color(160,200,220));
		dtcr.setBackgroundNonSelectionColor(new Color(160,200,220));
		dtcr.setBackgroundSelectionColor(new Color(150,200,255));
		dtcr.setTextSelectionColor(Color.BLACK);
		//dtcr.setOpenIcon(new ImageIcon("images/tagset_closed.gif"));
		//dtcr.setClosedIcon(new ImageIcon("images/tagset_closed.gif"));
		dtcr.setLeafIcon(new ImageIcon("images/tag.gif"));
		tree.setCellRenderer(dtcr);
		tree.setBackground(new Color(160,200,220));
		tree.setToolTipText("Choose a Tag");
		tree.expandRow(0);
		
		 // Create Mouse Listener for Tree
        MouseListener ml = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int selRow = tree.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
                if(selRow != -1) {
                	if(e.getClickCount() == 2) {
                		handleDoubleClick(selPath.getLastPathComponent().toString());
                    }
                }
            }
        };
        
        // Setup Tree
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addMouseListener(ml);
        tree.setFont(arial);
	}
	
	public void updateSize(){
		tree.setSize(this.getSize());
	}
	
    private void handleDoubleClick(String tag){
    	int carotPos = textArea.getCaretPosition();
    	if(tag.equals("DOCTYPE")){
    		tagC.makeDTD();
    	}else if(tag.equals("html")){
    		tagC.doNonAttrTag("html");
    	}else if(tag.equals("body")){
    		tagC.doNonAttrTag("body");
    	}else if(tag.equals("h1 to h6")){
    		tagC.doHeader();
    	}else if(tag.equals("p")){
    		tagC.doNonAttrTag("p");
    	}else if(tag.equals("br")){
    		tagC.doSingleTag("br");
    	}else if(tag.equals("hr")){
    		tagC.doSingleTag("hr");
    	}else if(tag.equals("Comment")){
    		tagC.doComment();
    	}else if(tag.equals("b")){
    		tagC.doNonAttrTag("b");
    	}else if(tag.equals("strong")){
    		tagC.doNonAttrTag("strong");
    	}else if(tag.equals("i")){
    		tagC.doNonAttrTag("i");
    	}else if(tag.equals("em")){
    		tagC.doNonAttrTag("em");
    	}else if(tag.equals("u")){
    		tagC.doNonAttrTag("u");
    	}else if(tag.equals("big")){
    		tagC.doNonAttrTag("big");
    	}else if(tag.equals("small")){
    		tagC.doNonAttrTag("small");
    	}else if(tag.equals("sup")){
    		tagC.doNonAttrTag("sup");
    	}else if(tag.equals("sub")){
    		tagC.doNonAttrTag("sub");
    	}else if(tag.equals("strike")){
    		tagC.doNonAttrTag("strike");
    	}else if(tag.equals("font - face")){
    		tagC.doSingleAttrTag("font","face");
    	}else if(tag.equals("font - color")){
    		tagC.doSingleAttrTag("font","color");
    	}else if(tag.equals("font - size")){
    		tagC.doSingleAttrTag("font","size");
    	}else if(tag.equals("acronym")){
    		tagC.doAcronym();
    	}else if(tag.equals("address")){
    		tagC.doNonAttrTag("address");
    	}else if(tag.equals("blockquote")){
    		tagC.doNonAttrTag("blockquote");
    	}else if(tag.equals("center")){
    		tagC.doNonAttrTag("center");
    	}else if(tag.equals("q")){
    		tagC.doNonAttrTag("q");
    	}else if(tag.equals("cite")){
    		tagC.doNonAttrTag("cites");
    	}else if(tag.equals("a - href")){
    		tagC.doLink();
    	}else if(tag.equals("a - bookmark")){
    		tagC.doBookMark();
    	}else if(tag.equals("link")){
    		tagC.doCSSLink();
    	}else if(tag.equals("button")){
    		tagC.doInput("button");
    	}else if(tag.equals("hidden")){
    		tagC.doInput("hidden");
    	}else if(tag.equals("text")){
    		tagC.doInput("text");
    	}else if(tag.equals("checkbox")){
    		tagC.doInput("checkbox");
    	}else if(tag.equals("radio")){
    		tagC.doInput("radio");
    	}else if(tag.equals("submit")){
    		tagC.doFormButton("submit");
    	}else if(tag.equals("reset")){
    		tagC.doFormButton("reset");
    	}else if(tag.equals("select")){
    		tagC.doSelect();
    	}else if(tag.equals("optgroup")){
    		tagC.doOptGroup();
    	}else if(tag.equals("option")){
    		tagC.doOption();
    	}else if(tag.equals("textarea")){
    		tagC.doTextArea();
    	}else if(tag.equals("form")){
    		tagC.doForm();
    	}else if(tag.equals("ol")){
    		tagC.doNonAttrTag("ol");
    	}else if(tag.equals("ul")){
    		tagC.doNonAttrTag("ul");
    	}else if(tag.equals("li")){
    		tagC.doNonAttrTag("li");
    	}else if(tag.equals("dl")){
    		tagC.doNonAttrTag("dl");
    	}else if(tag.equals("dt")){
    		tagC.doNonAttrTag("dt");
    	}else if(tag.equals("dd")){
    		tagC.doNonAttrTag("dd");
    	}else if(tag.equals("normal")){
    		tagC.doTable();
    	}else if(tag.equals("border")){
    		tagC.doTable("border");
    	}else if(tag.equals("border,align")){
    		tagC.doTable("border","align");
    	}else if(tag.equals("caption")){
    		tagC.doNonAttrTag("caption");
    	}else if(tag.equals("th")){
    		tagC.doNonAttrTag("th");
    	}else if(tag.equals("td")){
    		tagC.doNonAttrTag("td");
    	}else if(tag.equals("tr")){
    		tagC.doNonAttrTag("tr");
    	}else if(tag.equals("img")){
    		tagC.doImage();
    	}else if(tag.equals("map")){
    		tagC.doDoubleAttrTag("map","id","name");
    	}else if(tag.equals("area")){
    		tagC.doArea();
    	}else if(tag.equals("style")){
    		tagC.doSingleAttrTag("style","type");
    	}else if(tag.equals("div")){
    		tagC.doSingleAttrTag("div","id");
    	}else if(tag.equals("span")){
    		tagC.doSingleAttrTag("span","class");
    	}else if(tag.equals("script")){
    		tagC.doScript();
    	}else if(tag.equals("noscript")){
    		tagC.doNonAttrTag("noscript");
    	}else if(tag.equals("php")){
    		tagC.doPHP();
    	}else if(tag.equals("head")){
    		tagC.doNonAttrTag("head");
    	}else if(tag.equals("title")){
    		tagC.doNonAttrTag("title");
    	}else if(tag.equals("meta")){
    		tagC.doMetaTag("name","content");
    	}
    	textArea.requestFocus();
    	textArea.setCaretPosition(carotPos);
    }
    
	private void populateTree(DefaultMutableTreeNode top){
		DefaultMutableTreeNode category;
		DefaultMutableTreeNode subcategory;
		DefaultMutableTreeNode tag;
		
		/*category = new DefaultMutableTreeNode("Text Formatting");
		category.add(new DefaultMutableTreeNode("Strong"));
		category.add(new DefaultMutableTreeNode("Emphasize"));
		category.add(new DefaultMutableTreeNode("Underline"));
		top.add(category);
		
		category = new DefaultMutableTreeNode("Text Alignment");
		category.add(new DefaultMutableTreeNode("Center"));
		category.add(new DefaultMutableTreeNode("Left"));
		category.add(new DefaultMutableTreeNode("Right"));
		category.add(new DefaultMutableTreeNode("Justify"));
		top.add(category);
		
		category = new DefaultMutableTreeNode("Miscellaneous");
		category.add(new DefaultMutableTreeNode("Image"));
		category.add(new DefaultMutableTreeNode("Link"));
		category.add(new DefaultMutableTreeNode("Horiz. Rule"));
		top.add(category);*/
		
		category = new DefaultMutableTreeNode("Basic Tags");
		category.add(new DefaultMutableTreeNode("DOCTYPE"));
		category.add(new DefaultMutableTreeNode("html"));
		category.add(new DefaultMutableTreeNode("body"));
		category.add(new DefaultMutableTreeNode("h1 to h6"));
		category.add(new DefaultMutableTreeNode("p"));
		category.add(new DefaultMutableTreeNode("br"));
		category.add(new DefaultMutableTreeNode("hr"));
		category.add(new DefaultMutableTreeNode("Comment"));
		top.add(category);
		
		category = new DefaultMutableTreeNode("Char Format");
		category.add(new DefaultMutableTreeNode("b"));
		category.add(new DefaultMutableTreeNode("strong"));
		category.add(new DefaultMutableTreeNode("i"));
		category.add(new DefaultMutableTreeNode("em"));
		category.add(new DefaultMutableTreeNode("u"));
		category.add(new DefaultMutableTreeNode("big"));
		category.add(new DefaultMutableTreeNode("small"));
		category.add(new DefaultMutableTreeNode("sup"));
		category.add(new DefaultMutableTreeNode("sub"));
		category.add(new DefaultMutableTreeNode("strike"));
		category.add(new DefaultMutableTreeNode("font - face"));
		category.add(new DefaultMutableTreeNode("font - color"));
		category.add(new DefaultMutableTreeNode("font - size"));
		top.add(category);
		
		category = new DefaultMutableTreeNode("Output");
		category.add(new DefaultMutableTreeNode("pre"));
		category.add(new DefaultMutableTreeNode("code"));
		category.add(new DefaultMutableTreeNode("tt"));
		category.add(new DefaultMutableTreeNode("samp"));
		category.add(new DefaultMutableTreeNode("var"));
		category.add(new DefaultMutableTreeNode("dfn"));
		category.add(new DefaultMutableTreeNode("kbd"));
		top.add(category);
		
		category = new DefaultMutableTreeNode("Blocks");
		category.add(new DefaultMutableTreeNode("acronym"));
		category.add(new DefaultMutableTreeNode("address"));
		category.add(new DefaultMutableTreeNode("blockquote"));
		category.add(new DefaultMutableTreeNode("center"));
		category.add(new DefaultMutableTreeNode("q"));
		category.add(new DefaultMutableTreeNode("cite"));
		top.add(category);
		
		category = new DefaultMutableTreeNode("Links");
		category.add(new DefaultMutableTreeNode("a - href"));
		category.add(new DefaultMutableTreeNode("a - bookmark"));
		top.add(category);
		
		category = new DefaultMutableTreeNode("Forms");
		category.add(new DefaultMutableTreeNode("form"));
		category.add(new DefaultMutableTreeNode("submit"));
		category.add(new DefaultMutableTreeNode("reset"));
		category.add(new DefaultMutableTreeNode("textarea"));
		category.add(new DefaultMutableTreeNode("select"));
		category.add(new DefaultMutableTreeNode("option"));
		category.add(new DefaultMutableTreeNode("optgroup"));
		subcategory = new DefaultMutableTreeNode("Input");
		subcategory.add(new DefaultMutableTreeNode("button"));
		subcategory.add(new DefaultMutableTreeNode("radio"));
		subcategory.add(new DefaultMutableTreeNode("checkbox"));
		subcategory.add(new DefaultMutableTreeNode("text"));
		subcategory.add(new DefaultMutableTreeNode("hidden"));
		category.add(subcategory);
		top.add(category);
		
		category = new DefaultMutableTreeNode("Lists");
		category.add(new DefaultMutableTreeNode("ol"));
		category.add(new DefaultMutableTreeNode("ul"));
		category.add(new DefaultMutableTreeNode("li"));
		category.add(new DefaultMutableTreeNode("dl"));
		category.add(new DefaultMutableTreeNode("dt"));
		category.add(new DefaultMutableTreeNode("dd"));
		top.add(category);
		
		category = new DefaultMutableTreeNode("Images");
		category.add(new DefaultMutableTreeNode("img"));
		category.add(new DefaultMutableTreeNode("map"));
		category.add(new DefaultMutableTreeNode("area"));
		top.add(category);
		
		category = new DefaultMutableTreeNode("Tables");
		subcategory = new DefaultMutableTreeNode("Table");
		subcategory.add(new DefaultMutableTreeNode("normal"));
		subcategory.add(new DefaultMutableTreeNode("border"));
		subcategory.add(new DefaultMutableTreeNode("border,align"));
		category.add(subcategory);
		category.add(new DefaultMutableTreeNode("caption"));
		category.add(new DefaultMutableTreeNode("th"));
		category.add(new DefaultMutableTreeNode("tr"));
		category.add(new DefaultMutableTreeNode("td"));
		top.add(category);
		
		category = new DefaultMutableTreeNode("Style Tags");
		category.add(new DefaultMutableTreeNode("link"));
		category.add(new DefaultMutableTreeNode("style"));
		category.add(new DefaultMutableTreeNode("div"));
		category.add(new DefaultMutableTreeNode("span"));
		top.add(category);
		
		category = new DefaultMutableTreeNode("Meta Info");
		category.add(new DefaultMutableTreeNode("head"));
		category.add(new DefaultMutableTreeNode("title"));
		category.add(new DefaultMutableTreeNode("meta"));
		top.add(category);
		
		category = new DefaultMutableTreeNode("Programming");
		category.add(new DefaultMutableTreeNode("script"));
		category.add(new DefaultMutableTreeNode("noscript"));
		category.add(new DefaultMutableTreeNode("php"));
		top.add(category);
	
	}
	

}
