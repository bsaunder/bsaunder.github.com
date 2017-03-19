import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.prefs.Preferences;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;


public class Editor extends JFrame implements ComponentListener 
{
	// Added By Eclipse
    private static final long serialVersionUID = 1L;
    // Create Editor Area
    private JTextArea textArea = new JTextArea();
    private JScrollPane editor = new JScrollPane(textArea);
    private UndoRedo edit = new UndoRedo(textArea);
    // Create Tag Creator
    private TagCreator tagC = new TagCreator(textArea);
    // Create File Chooser
	private FileEditor fg = new FileEditor(textArea,this);
	//Create Tree
	private EditorTree treePane = new EditorTree(tagC,textArea);
	// Create Toolbar
	private EditorToolBar toolbar = new EditorToolBar("Common Commands",textArea,edit,tagC);
    // Create Menu
    private EditorMenu menu;
    // Create Site Manager
    private SiteManager sm = new SiteManager(fg);
    
    private Dimension lastSize,maxSize,minSize;
    private static final String prefNode = "OpenEditor/ui/prefs";
	private Preferences prefs = Preferences.userRoot().node(prefNode);
	
    
    // Constructor
    public Editor(String title)
    {
    	// setBounds(int X, int Y, int W, int H)
        super(title);// Call Super Constructor
        menu = new EditorMenu(this,tagC,textArea,fg,edit,title,sm);
        
        // Set Up Content Pane
        Container content = getContentPane();
        content.setBackground(Color.DARK_GRAY);
        content.setLayout(null);
        
        // Configuring Editor
        this.setIconImage(new ImageIcon("openeditor.gif").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(50,50);
        minSize = new Dimension(650,600);
        this.setMinimumSize(minSize);
        this.setPreferredSize(minSize);
        this.setState(Frame.MAXIMIZED_BOTH);
        maxSize = this.getSize();
        this.setState(Frame.NORMAL);
        lastSize = new Dimension(650,600);
        this.setSize(lastSize);
        this.setResizable(true);
        this.setJMenuBar(menu);
        this.addComponentListener(this);
               
        // Setup Toolbar
        toolbar.setFloatable(false);
        toolbar.setMargin(new Insets(5,5,5,5));
        toolbar.setBounds(0,0,650,25);
        content.add(toolbar);
        
        // Setup Editor
        Font userFont = getUserFont();
        textArea.setFont(userFont);
        textArea.setMargin(new Insets(2,2,2,2));
        textArea.setSelectionColor(new Color(65,80,90));
        editor.setBorder(new LineBorder(Color.BLACK));
        editor.setSize(470,520);
        editor.setLocation(170,28);
        content.add(editor);
        
        // Setup Tree
        treePane.setBorder(new LineBorder(Color.BLACK));
        treePane.setSize(150,342);
        treePane.setLocation(10,206);
        content.add(treePane);
        
        // Setup File Tree
        sm.setBorder(new LineBorder(Color.BLACK));
        sm.setLocation(10,28);
        sm.setSize(150,170);
        sm.refresh();
        content.add(sm);
               
        // Display Editor Frame
        this.setVisible(true);
        textArea.requestFocus();
        tagC.makeMainPage();
        tagC.makeDTDXHTMLTrans();
    }
    
    private Font getUserFont(){
    	String name = prefs.get("font","Monospaced");
    	int size = prefs.getInt("size",12);
    	return new Font(name,0,size);
    }
  	
	public void componentResized(ComponentEvent e) {
		Dimension temp = e.getComponent().getSize();
		if(!temp.equals(maxSize)){
			if(temp.height < minSize.height || temp.width < minSize.width){
				// Tried To Size Below Min Size
				this.setSize(minSize);
			}else{
				// Normal Resize
				lastSize = temp;
				
				// Resize Components
				editor.setSize(temp.width-185,temp.height-80);
				textArea.setSize(editor.getSize());
				
				treePane.setSize(treePane.getWidth(),temp.height-258);
				treePane.updateSize();
				
				toolbar.setSize(temp.width,toolbar.getHeight());
				
				this.doLayout();
			}
		}
	}

	public void componentMoved(ComponentEvent e) {
		
	}

	public void componentShown(ComponentEvent e) {
		
	}

	public void componentHidden(ComponentEvent e) {
		
	}
}