import java.awt.Color;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
/*
 * Created by Bryan Saunders
 * Created on Nov 25, 2004
 */

public class PreviewPane extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JEditorPane code = new JEditorPane();
    private JScrollPane preview = new JScrollPane(code);

	public PreviewPane(String name,String html) throws HeadlessException {
		super(name);
		this.setIconImage(new ImageIcon("openeditor.gif").getImage());
        this.setLocation(50,50);
        this.setSize(650, 600);
        this.setResizable(false);
        
        Container content = getContentPane();
        content.setBackground(Color.DARK_GRAY);
        content.setLayout(null);
        
        code.setMargin(new Insets(2,2,2,2));
        code.setEditable(false);
        code.setContentType("text/html");
        html = html.replaceAll("/>",">");
        code.setText(html);
        preview.setBorder(new LineBorder(Color.BLACK));
        preview.setSize(600,560);
        preview.setLocation(25,10);
        content.add(preview);
		
		this.setVisible(true);
	}

}
