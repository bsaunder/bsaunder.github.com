//Import Java libraries
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.PrintGraphics;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.io.EOFException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JTextArea;

class Print {

	   // Variable for storing print properties
	   Properties p = new Properties(); 
	   private Font userFont;

	   // constructor
	   public Print(JFrame parent, final JTextArea textArea,Font uf) {

	      // get tool kit
	      Toolkit toolkit = parent.getToolkit();
	      
	      // Set User Font
	      int newSize = uf.getSize()-2;
	      String face = uf.getFontName();
	      userFont = new Font(face,0,newSize);

	      // Set print properties
	      PrintJob pjob = toolkit.getPrintJob(parent, "OpenEditor", p);

	      if (pjob != null) {  // Does a print job exist?
	         Graphics pg = pjob.getGraphics();  // Get graphics environment
	         if (pg != null) {  // Print next page
	            String s = textArea.getText(); 
	            printLongString (pjob, pg, s);
	            pg.dispose();  // Dispose of current page
	         }   
	         pjob.end();  // End of print job
	      }
	   }
	   
	   // Function printLongString
	   // Print string to graphics via printjob
	   // Does not deal with word wrap or tabs
	   private  void printLongString (PrintJob pjob, Graphics pg, String s) {
	      int pageNum = 1;  // Page number
	      int linesForThisPage = 0; // Number lines for current page
	      int linesForThisJob = 0;  // Number lines for current job

	      // Note: String is immutable so won't change while printing.
	      if (!(pg instanceof PrintGraphics)) {
		throw new IllegalArgumentException
		  ("Graphics context not PrintGraphics");
	      }

	      // create StringReader and LineNumberReader objects
	      StringReader sr = new StringReader (s);
	      LineNumberReader lnr = new LineNumberReader (sr);
	      String nextLine;

	      // get the page height
	      int pageHeight = pjob.getPageDimension().height;

	      // set the font to print in
	      //Font helv = new Font("Helvetica", Font.PLAIN, 12);
	      //have to set the font to get any output
	      pg.setFont (userFont);

	      // get the dimensions of the font
	      FontMetrics fm = pg.getFontMetrics(userFont);
	      int fontHeight = fm.getHeight();
	      int fontDescent = fm.getDescent();
	      int curHeight = 0;

	      try {
	         do {
		     // get the next line from the text area
	             nextLine = lnr.readLine();
	             if (nextLine != null) {
		       // if we are over the page, create a new one
	               if ((curHeight + fontHeight) > pageHeight) {
	               // New Page
	               pageNum++;
	               linesForThisPage = 0;
	               pg.dispose();
	               pg = pjob.getGraphics();
	               if (pg != null) {
	                  pg.setFont(userFont);
	               }
	               curHeight = 0;
	            }
	            curHeight += fontHeight;
	            if (pg != null) {
		       // draw the line to the printer
	               pg.setColor(Color.black);
	               pg.drawString (nextLine, 20, curHeight - fontDescent+20);
	               linesForThisPage++;
	               linesForThisJob++;
	            } else {
	               System.out.println ("pg null");
	            }
	          }
	         } while (nextLine != null);
	         } catch (EOFException eof) {
	         // Fine, ignore
	         } catch (Exception t) { // Anything else
	            t.printStackTrace();
	         }
	   }
	}