/**
 *  Window that pop-up when the button restart is pressed
 *  It set a boolean true if the player want to restart the game
 *  Othniel KONAN
 *  2014/09/09
 */



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RestartWindow extends JFrame implements ActionListener{
   private boolean restart=false;
   public void restartWindow(){
         // Set the frame
         setTitle("CHECKER");
         setSize(310,100);
         setAlwaysOnTop(true);
         setLocationRelativeTo(null);
         setVisible(true);
         setLayout(new BorderLayout());

         // Set the Buttons and panels for the Frame
         JButton yes = new JButton("YES");
         JButton no = new JButton("NO");
         JLabel confirmation = new JLabel("ARE YOU SURE YOU WANT TO RESTART THE GAME?");
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
         // If the player want to restart, the resartWindow is closed and restart is set to true
         if(e.getActionCommand().equals("YES")){restart=true;dispose();}
         // Else the restartWindow is closed
         else if(e.getActionCommand().equals("NO"))dispose();
      }
      
      /**
       * Accessor to the variable restart
       */
      public boolean getRestart(){
         return restart;
      }
      /**
       * Mutator of the variable restart
       */
      public void resetRestart(){
         restart=false;
      }
      
}