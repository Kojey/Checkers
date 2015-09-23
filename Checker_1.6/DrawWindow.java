/**
 *  Window that pop-up when the button an offer for a draw is made
 *  It set a boolean true if the player want to accept the offer the game
 *  Othniel KONAN
 *  2014/09/09
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawWindow extends JFrame implements ActionListener{
   private boolean draw=false;
   public void drawWindow(){
         // Set the Frame
         setTitle("CHECKER");
         setSize(220,100);
         setAlwaysOnTop(true);
         setLocationRelativeTo(null);
         setVisible(true);
         setLayout(new BorderLayout());
         
         // Create the button needed
         JButton yes = new JButton("ACCEPT");
         JButton no = new JButton("DECLINE");
         JLabel ask = new JLabel("DO YOU WANT TO TAKE THE OFFER");
         add(ask, BorderLayout.CENTER);
         
         // Place the panels on the window
         JPanel button = new JPanel();
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
         // If the player accpets the offer, the drawWindow is closed and draw is set to true
         if(e.getActionCommand().equals("ACCEPT")){dispose();draw=true;}
         // Else the drawWindow is closed
         else if(e.getActionCommand().equals("DECLINE"))dispose();
      }
      
      /**
       * Accessor to the variable draw
       */
      public boolean getDraw(){
         return draw;
      }
      /**
       * Mutator of the variable draw
       */
      public void resetDraw(){
         draw=false;
      }
}