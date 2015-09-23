/**
 *  Window that pop-up when the button quit is pressed
 *  Get the confirmation that the player want to exit hte game
 *  Othniel KONAN
 *  2014/09/09
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuitWindow extends JFrame implements ActionListener{
   public void quitWindow(){
         // Set the frame
         setTitle("CHECKER");
         setSize(220,100);
         setAlwaysOnTop(true);
         setLocationRelativeTo(null);
         setVisible(true);
         setLayout(new BorderLayout());
         // Set the button and the Panel of the window
         JButton yes = new JButton("YES");
         JButton no = new JButton("NO");
         JLabel confirmation = new JLabel("ARE YOU SURE YOU WANT TO EXIT?");
         add(confirmation, BorderLayout.CENTER);
         JPanel button = new JPanel();
         // Place the panels on the window
         button.setLayout(new FlowLayout());
         yes.addActionListener(this);
         no.addActionListener(this);
         button.add(yes);
         button.add(no);
         add(button, BorderLayout.SOUTH);
         
      }
      /**
       * The action listener of the window
       */
      public void actionPerformed(ActionEvent e){
         // Exit if player want to exit
         if(e.getActionCommand().equals("YES"))System.exit(0);
         // Close the window when the player does not want to quit
         else if(e.getActionCommand().equals("NO"))dispose();
      }
      
}