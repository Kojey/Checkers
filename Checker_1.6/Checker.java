/**
 *  Heart Class of the game that contain the constructor for the main window and the main method
 *  Othniel KONAN
 *  2014/09/09
 */

// Import of library
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Checker extends JFrame implements ActionListener{
   
   // Defintion of some constants
   //public static final ActionEvent ACTION_PERFORMED;
   private static final int RIGHT=0;
   private static final int WIDTH = /*518*/885;
   private static final int HEIGHT = /*540*/542;
   
   // The two representation of the grid
   private JButton [][] butGrid = new JButton[8][8];
   private char [][] grid = new char[8][8];
   
   // The button used in the Frame
   public JButton help = new JButton("HELP");
   public JButton restart = new JButton("RESTART");
   public JButton quit = new JButton("QUIT");
   public JButton drawBut = new JButton("OFFER A DRAW");

   //Upload the piece for the platformPanel
   ImageIcon blackPiece = new ImageIcon("blackPiece.png");
   ImageIcon blackPieceSelected = new ImageIcon("blackPieceSelected.png");
   ImageIcon blackPieceJumped = new ImageIcon("blackPieceJumped.png");
   ImageIcon redPiece = new ImageIcon("redPiece.png");
   ImageIcon redPieceSelected = new ImageIcon("redPieceSelected.png");
   ImageIcon redPieceJumped = new ImageIcon("redPieceJumped.png");
   ImageIcon kingBlackPiece = new ImageIcon("kingBlackPiece.png");
   ImageIcon kingBlackPieceSelected = new ImageIcon("kingBlackPieceSelected.png");
   ImageIcon kingBlackPieceJumped = new ImageIcon("kingBlackPieceJumped.png");
   ImageIcon kingRedPiece = new ImageIcon("kingRedPiece.png");
   ImageIcon kingRedPieceSelected = new ImageIcon("kingRedPieceSelected.png");
   ImageIcon kingRedPieceJumped = new ImageIcon("kingRedPieceJumped.png");
   ImageIcon black = new ImageIcon("black.png");
   ImageIcon blackPointed = new ImageIcon("blackPointed.png");    
   ImageIcon white = new ImageIcon("white.png");
   
   
   
   
   
   //Upload the images in the Frame
   ImageIcon play1 = new ImageIcon("player1green.png");
   ImageIcon play2 = new ImageIcon("player2red.png");
   ImageIcon play3 = new ImageIcon("player1red.png");
   ImageIcon play4 = new ImageIcon("player2green.png");
   ImageIcon dr = new ImageIcon("draw.png");
   ImageIcon info = new ImageIcon("help.png");
   ImageIcon rest = new ImageIcon("restart.png");
   ImageIcon qu = new ImageIcon("quit.png");
   ImageIcon redP = new ImageIcon("redP.png");
   ImageIcon blackP = new ImageIcon("blackP.png");
   
   // The five different panel used
   private JPanel platformPanel = new JPanel();
   private JPanel menuPanel = new JPanel();
   private JPanel playerPanel = new JPanel();
   private JPanel topPanel = new JPanel();
   private JPanel bottomPanel = new JPanel();
   
   // Variable used
   private int i=0,j=0,k=0;
   private int side=-1,count_1=12,count_2=12;
   private int[] list ;
   private int count=0,x=0,y=0,fx=0,fy=0,cx,cy;
   private String value;
   private String trial="",start="",end="",test1="",test2=""; 
   boolean player_1_win = false, player_2_win = false, draw = false,pi=false;

   // Definition of the labels used
   private JLabel player1 = new JLabel();
   private JLabel player2 = new JLabel();
   private JLabel coun1;
   private JLabel coun2;
   
   // The classes use for the motion and analysis of the moves
   GridPlatform pF = new GridPlatform();
   NormalPiece nP = new NormalPiece();
   KingPiece kP = new KingPiece();
   Needed nD = new Needed();
   
   // the classes use for the different pop-up windows
   RestartWindow rW = new RestartWindow();
   DrawWindow dW = new DrawWindow();
   HelpWindow hW = new HelpWindow();
   QuitWindow qW = new QuitWindow();
   EndWindow eW = new EndWindow();
   
   
   // The constructor of the frame
   public Checker(){
      // Set the frame
      super("CHECKERS");
      setSize(WIDTH,HEIGHT);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      setUndecorated(true);
      setLayout(new BorderLayout());
      
      // Set the image and the counter of each player (those at the left of the Frame)
      test1 = test1.valueOf(count_1);
      test2 = test2.valueOf(count_2);
      coun1 = new JLabel(test1+" REMAINING",blackP,RIGHT);
      coun2 = new JLabel(test2+" REMAINING",redP,RIGHT);
      
      // Create a space for the platform of the Frame
      platformPanel.setLayout(new GridLayout(8,8));
      // Create and set the menu (items at the right of the Frame) 
      menuPanel.setLayout(new GridLayout(3,1));
      help.setIcon(info);
      help.addActionListener(this);
      menuPanel.add(help);
      restart.setIcon(rest);
      restart.addActionListener(this);
      menuPanel.add(restart);
      quit.setIcon(qu);
      quit.addActionListener(this);
      menuPanel.add(quit);
      // Create layout and construct the panel at the right of the screen
      playerPanel.setLayout(new GridLayout(5,1));
      player1.setIcon(play1);
      player2.setIcon(play2);
      playerPanel.add(player1);;
      playerPanel.add(coun1);
      playerPanel.add(player2);
      playerPanel.add(coun2);
      drawBut.setIcon(dr);
      drawBut.addActionListener(this);
      playerPanel.add(drawBut);
     
      // Initialisation of the platform
      for(int j=0; j<8; j++){ for(int i=0; i<8; i++){
            butGrid[j][i] = new JButton();
            k=j+i;
            value = value.valueOf(i)+value.valueOf(j);
            // Set coordinate of each block of the platform as the actionCommand of each button 
            butGrid[j][i].setActionCommand(value);
            butGrid[j][i].addActionListener(this);
            butGrid[j][i].setMargin(new Insets(0,0,0,0));
            platformPanel.add(butGrid[j][i]);
      }}
      // Initialise the grid
      pF.iniGrid(grid);
      // update the platform
      updateGrid(grid,butGrid);
      // Set position of the panels
      add(topPanel,BorderLayout.NORTH);
      add(bottomPanel,BorderLayout.SOUTH);
      add(menuPanel,BorderLayout.EAST);
      add(playerPanel,BorderLayout.WEST);
      add(platformPanel,BorderLayout.CENTER);

   }
   
   
    public void actionPerformed(ActionEvent e){
      
      // Check if a button on the grid is touched 
      if(Character.isDigit(e.getActionCommand().charAt(0))&& !rW.getRestart() && !eW.getRestart() && !dW.getDraw()){
         pF.removeMarkers(grid); 
         trial=e.getActionCommand();
         x=Integer.valueOf(trial.substring(0,1)).intValue();
         y=Integer.valueOf(trial.substring(1,2)).intValue();
         
         // Check if the correct piece is selected(the one of the player who needs to play)
         if(count==0 && pF.checkPlayer(side,x,y,grid))count++;// If the count is zero and the correct pece were selected -> count++
         //ELSE REMOVE MARKER (change grid=>if grid[j][i]=markerpiece then grid[j][i]=pieceWithoutMarker//
         //CALL FUNCTION REMOVE MARKER AND ADD MARKER FOR MUSTJUMP
         /* We do that in order to improve the gameplay in a way that if a correct piece is clicked then 
          the second click on the platform will be the final position selected*/  
         // if the final position is selectedd 
         if(count==2){
            
            end=e.getActionCommand(); // Savevthe position
            // Take coordinates (x,y) and (fx,fy)
            x=Integer.valueOf(start.substring(0,1)).intValue();
            y=Integer.valueOf(start.substring(1,2)).intValue();
            fx=Integer.valueOf(end.substring(0,1)).intValue();
            fy=Integer.valueOf(end.substring(1,2)).intValue();
            
            pF.removeMarkers(grid); // Clear the platform from the marker
            // If the (initial,final) position are correct !!!check class Needed to understand the purpose off the method
            if(nD.checkPosition(side,x,y,fx,fy,cx,cy,grid)){
            // Move and check if there is a combo
               int combo=0;
               // The move is made
               list = nD.theMove(side,count_1,count_2,x,y,fx,fy,grid); 
               // the combo and the counts are updated
               combo = list[0];
               count_1=list[1];
               count_2=list[2];
               if(combo==0){side*=-1;cx=0;cy=0;} // If no possible jumps, we change the player
               // if the player jumped a piece and reach the king row and the previous state of
               // the piece was not to be a normal piece then combo is true
               //else {cx=fx;cy=fy;}
               else if(combo ==1 && fy == 0 && grid[y][x]!= 'O'){cx=fx;cy=fy;}
               else if(combo ==1 && fy == 7 && grid[y][x]!= 'X'){cx=fx;cy=fy;}
               // If there was a jump eslewhere in the grid, combo is true
               else if(combo == 1 && fy<7 && fy>0){cx=fx;cy=fy;}
               else {side*=-1;cx=0;cy=0;}
               // The variable are updated to display the count on the Frame
               test1 = test1.valueOf(count_1);
               test2 = test2.valueOf(count_2);
               coun1.setText(test1+" REMAINING");
               coun2.setText(test2+" REMAINING");
            }
            // Change the indicators to show to the user who needs to play
            if(side==-1){
               player1.setIcon(play1);
               player2.setIcon(play2);
            }
            else {
               player1.setIcon(play3);
               player2.setIcon(play4);
            }
         // REMOVE MARKER AFTER MOVE(change grid=>if grid[j][i]=markerpiece then grid[j][i]=pieceWithoutMarker////CALL FUNCTION REMOVE MARKER
            pF.removeMarkers(grid); // Clear the platform from the marker
            // Check for draw or win
            // Player loose if he/she does not have any more pieces
            if(count_1 == 0)player_2_win = true;
            if(count_2 == 0)player_1_win = true;
            // Player loose if he/she cannot make any legal moves
            // if none of the player can move it is a draw 
     //       if(!pF.stillMove(-1,'O',"XW",grid,x,y) && !pF.stillMove(-1,'Q',"XW",grid,x,y)
     //          && !pF.stillMove(1,'X',"OQ",grid,x,y) && !pF.stillMove(11,'W',"OQ",grid,x,y))draw=true;
            // if the player one cannot move he/she looses 
            else if(!pF.stillMove(-1,'O',"XW",grid,x,y) && !pF.stillMove(-1,'Q',"XW",grid,x,y))player_2_win = true;
            // if the player two cannot move he/she looses
            else if(!pF.stillMove(1,'X',"OQ",grid,x,y) && !pF.stillMove(1,'W',"OQ",grid,x,y))player_1_win = true;   
            // launch the pop-up windowsaccording to who won
            //updateGrid(grid,butGrid);
            if(draw) {eW.endWindow(3);}
            if(player_1_win){eW.endWindow(1);}
            if(player_2_win){eW.endWindow(2);}
            // Reset count to zero to retake couples of inputs
            count=0;
            // The platform is updated after a move
            pF.markAllJump(side,grid);// Mark all the piece than can be jumped
            updateGrid(grid,butGrid);
         }
         //

         // if count is one we save the initial position
         //else if(count==1 && !pF.checkJumpPiece(grid));
         else if(count==1 && !pF.notEmptyAllList(side,grid)){ // If count is one and ther is no piece to be jumped
            //pF.removeMarkers(grid); // Clear the platform from the marker
            start=e.getActionCommand();
            x=Integer.valueOf(start.substring(0,1)).intValue();
            y=Integer.valueOf(start.substring(1,2)).intValue();
            count++;
            pF.markJump(side,grid,x,y); // Mark the jump first as it is compulsory
            pF.markMove(side,grid,x,y); /* Mark the smple move after the jump; note that if there is a jump; 
             the markMove will not display will not display anything*/  
            
         }/*DISPLAY MARKER ON GRID FOR POSSIBLE MOVE AND JUMP FOR THAT PIECE*/
         /* EMPTY BLOCK ON*/
         else if(count==1 && pF.notEmptyAllList(side,grid) && !pF.emptyList(side,grid,x,y) 
            && cx== 0 && cy==0){ /* If count is one and there are piece to jump and t
           the piece selected is one that can jump and */
            start=e.getActionCommand();
            x=Integer.valueOf(start.substring(0,1)).intValue();
            y=Integer.valueOf(start.substring(1,2)).intValue();
            count++;
            pF.markJump(side,grid,x,y); // Mark the jump first as it is compulsory
            pF.markMove(side,grid,x,y); /* Mark the smple move after the jump; note that if there is a jump; 
             the markMove will not display will not display anything*/  
            
         }
         else if(count==1 && pF.notEmptyAllList(side,grid) && !pF.emptyList(side,grid,x,y) 
            && (cx!= 0 || cy!=0) && cx==x && cy==y){ /* If count is one and there are piece to jump and t
           the piece selected is one that can jump and */
            start=e.getActionCommand();
            x=Integer.valueOf(start.substring(0,1)).intValue();
            y=Integer.valueOf(start.substring(1,2)).intValue();
            count++;
            pF.markJump(side,grid,x,y); // Mark the jump first as it is compulsory
            pF.markMove(side,grid,x,y); /* Mark the smple move after the jump; note that if there is a jump; 
             the markMove will not display will not display anything*/  
            
         }
         pF.markAllJump(side,grid);// Mark all the piece than can be jumped
         updateGrid(grid,butGrid);// Update the grid
      }
      
      // If something other than the platform was clicked 
      else {count=0;// count is reseted
         //ELSE REMOVE MARKER (change grid=>if grid[j][i]=markerpiece then grid[j][i]=pieceWithoutMarker////CALL FUNCTION REMOVE MARKER
         // Launch windows according to the button pressed 
         if(e.getActionCommand().equals("RESTART")){rW.restartWindow();}
         else if(e.getActionCommand().equals("OFFER A DRAW"))dW.drawWindow();
         else if(e.getActionCommand().equals("HELP"))hW.helpWindow();
         else if(e.getActionCommand().equals("QUIT"))qW.quitWindow();
      }
      // Check if the game need to be restarted    
      if(rW.getRestart() || eW.getRestart()){
         count_1=12;
         count_2=12;
         coun1.setText("12 REMAINING");
         coun2.setText("12 REMAINING");
         side=-1;
         player1.setIcon(play1);
         player2.setIcon(play2);
         draw=false;
         player_1_win=false;
         player_2_win=false;
         pF.iniGrid(grid);
         updateGrid(grid,butGrid);
         rW.resetRestart();
         eW.resetRestart();
     }
     // Check if the opponent accepted the offer
     if(dW.getDraw()){
      EndWindow eWi = new EndWindow();
          eWi.endWindow(3);
          dW.resetDraw();
     }
   }
   
   /**
    * VOID Correspond every value in an array of character to the platform
    * Each value correspond to a specific piece
    * @param grid, the array of character representing the nonseen platform
    * @param butGrid the 'seen' platform 
    */
   public void updateGrid(char [][]grid, JButton[][] butGrid){
      for(int j=0; j<8; j++){ for(int i=0; i<8; i++){
         if(grid[j][i]=='O'){butGrid[j][i].setIcon(blackPiece);}
         else if(grid[j][i]=='X'){butGrid[j][i].setIcon(redPiece);}   
         else if(grid[j][i]=='W'){butGrid[j][i].setIcon(kingRedPiece);}
         else if(grid[j][i]=='Q'){butGrid[j][i].setIcon(kingBlackPiece);}
         else if(grid[j][i]=='o')butGrid[j][i].setIcon(blackPieceSelected);
         else if(grid[j][i]=='x')butGrid[j][i].setIcon(redPieceSelected);
         else if(grid[j][i]=='w')butGrid[j][i].setIcon(kingRedPieceSelected);
         else if(grid[j][i]=='q')butGrid[j][i].setIcon(kingBlackPieceSelected);
         else if(grid[j][i]=='D')butGrid[j][i].setIcon(blackPieceJumped);
         else if(grid[j][i]=='Y')butGrid[j][i].setIcon(redPieceJumped);
         else if(grid[j][i]=='P')butGrid[j][i].setIcon(kingBlackPieceJumped);
         else if(grid[j][i]=='V')butGrid[j][i].setIcon(kingRedPieceJumped);
         else if(grid[j][i]=='~')butGrid[j][i].setIcon(black);
         else if(grid[j][i]=='^')butGrid[j][i].setIcon(blackPointed);
         else butGrid[j][i].setIcon(white);
      }}
   }
   
}