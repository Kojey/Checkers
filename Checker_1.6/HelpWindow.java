/**
 *  Window that pop-up when the button help is pressed
 *  Show to the player information about how to play the game
 *  Othniel KONAN
 *  2014/09/09
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HelpWindow extends JFrame {
      public void helpWindow(){
         // load image
         ImageIcon test = new ImageIcon("test.png");
         // Add image to theTest label
         JLabel theTest = new JLabel(test);
         
         // Set the frame
         setTitle("CHECKER");
         setSize(500,500);
         setAlwaysOnTop(true);
         setLocationRelativeTo(null);
         setVisible(true);
         setLayout(new BorderLayout());
         // Add the label to the frame
         add(theTest);
       }           
}