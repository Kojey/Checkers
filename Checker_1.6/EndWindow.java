/**
 *  Window that pop-up when the game ends (Win or Draw)
 *  Show picture of wins according to the player who won 
 *  Othniel KONAN
 *  2014/09/09
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EndWindow extends JFrame implements ActionListener{
   private boolean restart=false;
   public void endWindow(int stat){
         // Set the frame
         setTitle("CHECKER");
         setSize(400,200);
         setAlwaysOnTop(true);
         setLocationRelativeTo(null);
         setVisible(true);
         setLayout(new BorderLayout());
         
         // Load image
         ImageIcon draw = new ImageIcon("12draw.png");
         ImageIcon one = new ImageIcon("Player1won.png");
         ImageIcon two = new ImageIcon("Player2won.png");
         // Create button
         JButton yes = new JButton("RESTART");
         JButton no = new JButton("QUIT");
         // Create Labels for different images
         JLabel dr = new JLabel(draw);
         JLabel w1 = new JLabel(one);
         JLabel w2 = new JLabel(two);
         // Add Labels according to who won
         if(stat==1)add(w1, BorderLayout.CENTER);
         else if(stat==2)add(w2, BorderLayout.CENTER);
         else add(dr, BorderLayout.CENTER);
         
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
         // If the player want to restart, the resartWindow is closed and restart is set to true
         if(e.getActionCommand().equals("RESTART")){dispose();restart=true;}
         // Else the restartWindow is closed
         else if(e.getActionCommand().equals("QUIT"))System.exit(0);
      }
      
      /**
       * Accessor to the variable restart
       * @return the value of restart
       */
      public boolean getRestart(){
         return restart;
      }
      
      /**
       * Mutator of the variable draw
       */
      public void resetRestart(){
         restart=false;
      }     
}