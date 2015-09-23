/*
   Class that define the platform as a grid
   Othniel KONAN 
   2014/09/03
*/

public class GridPlatform {

   private int i, j, k;
   private char empty = ' ';
   
   /**
    * BOOLEAN Check if the piece of the player who needs to play is selected
    * @param side, the player
    * @param x and y, the initial position
    * @param grid, the grid of character
    * @return, true if the piece of the right player was chosen 
    */
   public boolean checkPlayer(int side, int x, int y, char [][] grid) {
      boolean check = false;
      // if side denotes the player one and one piece of the player one is chosen the condition is satisfied
      if(side == -1 && (grid[y][x] == 'O' || grid[y][x] == 'Q'))check = true;
      else if(side == 1 && (grid[y][x] == 'X' || grid[y][x] == 'W'))check = true;
      return check;
   }
   
   /**
    * VOID Initialise the character grid platform
    * @param grid, the grid of character
    */
   public void iniGrid(char [][] grid){
      int j,i,k;
      // Create the player 2 part of the platform
      for(j=0; j<3; j++){  for(i=0; i<8; i++){k=j+i;
            if((k % 2 ) == 0) grid[j][i]= ' ';else grid[j][i]= 'X';
      }}
      // Create the middle part of the platform
      for(j=3; j<5; j++)for(i=0; i<8; i++){k=i+j;if((k % 2 ) == 0) grid[j][i]=' ';else grid[j][i]='~';}
      
      // Create the player 1 part of the platform
      for(j=5; j<8; j++){ for(i=0; i<8; i++){k=j+i;
            if((k % 2 ) == 0) grid[j][i]=' ';else grid[j][i]='O';
      }}
   }
   
  
   /**
    * BOOLEAN Check if a player can still make a move
    * @param side, the player
    * @param piece, the player's piece
    * @param grid, the grid of character    
    * @param x and y, the initial position
    * @return, true if the player can still make a move
    */
   public boolean stillMove(int side,char piece, String opiece, char [][] grid,int x,int y){
      boolean check = false;
      if(side ==-1){ // Player one
         if(piece == 'O' ){
            NormalPiece P = new NormalPiece();
            for(int j=0; j<8; j++){ for(int i=0; i<8; i++){
               // return true one piece can move
               if(grid[j][i]=='O' && P.possibleMove(side,opiece,grid,i,j))check = true;
            }}
         }
         else if(piece == 'Q' ){
            KingPiece P = new KingPiece();
            for(int j=0; j<8; j++){ for(int i=0; i<8; i++){
               // return true one piece can move
               if(grid[j][i]=='Q' && P.possibleMove(side,opiece,grid,i,j))check = true;
            }}
         }

      }
      else if(side==1){
         if(piece == 'X' ){
            NormalPiece P = new NormalPiece();
            for(int j=0; j<8; j++){ for(int i=0; i<8; i++){
               // return true one piece can move
               if(grid[j][i]=='X' && P.possibleMove(side,opiece,grid,i,j))check = true;
            }}
         }
         else if(piece == 'W' ){
            KingPiece P = new KingPiece();
            for(int j=0; j<8; j++){ for(int i=0; i<8; i++){
               // return true one piece can move
               if(grid[j][i]=='W' && P.possibleMove(side,opiece,grid,i,j))check = true;
            }}
         }
      }
      return check;   
   }
   
   /**
    * BOOLEAN Check if a piece can jump
    * @param side, the player
    * @param grid, the grid of character    
    * @param x and y, the initial position
    * @return, true if the piece can jump
    */
   public boolean emptyList(int side, char [][] grid, int x, int y){
      boolean check = true;
      int[][] list;
      if(grid[y][x]== 'O'){
         NormalPiece P = new NormalPiece();
         list = P.possibleJump(side,"XW",grid,x,y); // Create a list containing the possible jump 
         for(int j=0; j<2; j++){ for(int i=0; i<2; i++){
            if(list[j][i] != 0)check = false; // Check if the list is not empty
         }}
      }
      else if(grid[y][x]== 'X'){
         NormalPiece P = new NormalPiece();
         list = P.possibleJump(side,"OQ",grid,x,y); // Create a list containing the possible jump 
         for(int j=0; j<2; j++){ for(int i=0; i<2; i++){
            if(list[j][i] != 0)check = false;// Check if the list is not empty
         }}
      }
      else if(grid[y][x]== 'Q'){
         KingPiece P = new KingPiece();
         list = P.possibleJump(side,"XW",grid,x,y); // Create a list containing the possible jump
         for(int j=0; j<4; j++){ for(int i=0; i<2; i++){
            if(list[j][i] != 0)check = false; // Check if the list is not empty
         }}  
      }
      else if(grid[y][x]== 'W'){
         KingPiece P = new KingPiece();
         list = P.possibleJump(side,"OQ",grid,x,y); // Create a list containing the possible jump
         for(int j=0; j<4; j++){ for(int i=0; i<2; i++){
            if(list[j][i] != 0)check = false; // Check if the list is not empty
         }}  
      }
      return check; 
   }
   
   
   /**
    * BOOLEAN Check if one piece of the player can jump
    * @param side, the player
    * @param grid, the grid of character
    * @return, true if one piece can jump
    */
   public boolean notEmptyAllList(int side, char [][] grid){
      boolean check = false;
      int[][] list;
      loop:for(int q=0; q<grid.length; q++){for(int p=0; p<grid.length; p++){
         if(side==-1){
            if(grid[q][p]== 'O'){
               NormalPiece P = new NormalPiece();
               list = P.allPossibleJump(side,grid[q][p],"WX",grid); // Create a list containing the possible jump
               for(int j=0; j<2; j++){ for(int i=0; i<2; i++){
                  if(list[j][i] != 0){check = true;break loop;} // Check if the list is not empty
               }}
            }
            else if(grid[q][p]== 'Q'){
               KingPiece P = new KingPiece();
               list = P.allPossibleJump(side,grid[q][p],"XW",grid); // Create a list containing the possible jump
               for(int j=0; j<4; j++){ for(int i=0; i<2; i++){
                  if(list[j][i] != 0){check = true;break loop;} // Check if the list is not empty
               }}  
            }   
         }
         
         else if(side==1){
            if(grid[q][p]== 'X'){
               NormalPiece P = new NormalPiece();
               list = P.allPossibleJump(side,grid[q][p],"OQ",grid); // Create a list containing the possible jump
               for(int j=0; j<2; j++){ for(int i=0; i<2; i++){
                  if(list[j][i] != 0){check = true;break loop;} // Check if the list is not empty
               }}
            }
            else if(grid[q][p]== 'W'){
               KingPiece P = new KingPiece();
               list = P.allPossibleJump(side,grid[q][p],"OQ",grid); // Create a list containing the possible jump
               for(int j=0; j<4; j++){ for(int i=0; i<2; i++){
                  if(list[j][i] != 0){check = true;break loop;} // Check if the list is not empty
               }}  
            }
         }
               
      }}
      return check; 
   }
   
   
   /**
    * BOOLEAN Check if the final position is in the list of all possible jumps
    * @param side, the player
    * @param opiece, the opponent's piece
    * @param grid, the grid of character
    * @param fx and fy, the final position
    * @return, true if the fianl position is in that list
    */
   public boolean isInList(int side, String opiece, char [][] grid, int fx, int fy){
      boolean check = false;
      int[][] list;
      loop:for(int q=0; q<grid.length; q++){for(int p=0; p<grid.length; p++){
         if(grid[q][p]== 'O' || grid[q][p] == 'X'){
            NormalPiece P = new NormalPiece();
            // Create a list containing all the possible jump
            list = P.allPossibleJump(side,grid[q][p],opiece,grid);
            for(int j=0; j<list.length; j++){
               // Check if the final position is in that list
               if(list[j][0] == fy && list[j][1] == fx){check = true;break loop;}
            }
         }else if(grid[q][p]== 'Q' || grid[q][p] == 'W'){
            KingPiece P = new KingPiece();
            // Create a list containing all the possible jump
            list = P.allPossibleJump(side,grid[q][p],opiece,grid);
            for(int j=0; j<list.length; j++){
               // Check if the final position is in that list
               if(list[j][0] == fy && list[j][1] == fx){check = true;break loop;}
            }  
         }
      }}
      return check;
   }
   
    /**
    * VOID remove all the marker placed on the grid
    * @param grid, the grid of character
    */
   public void removeMarkers(char [][] grid){
      for(int j=0; j<8; j++){for(int i=0; i<8; i++){
         // Match piece to its mark
         if(grid[j][i]=='o' || grid[j][i]=='D')grid[j][i]='O';
         else if(grid[j][i]=='x' || grid[j][i]=='Y')grid[j][i]='X';
         else if(grid[j][i]=='q' || grid[j][i]=='P')grid[j][i]='Q';
         else if(grid[j][i]=='w' || grid[j][i]=='V')grid[j][i]='W';
         else if(grid[j][i]=='^')grid[j][i]='~';
      }}
   }
   
   /**
    * VOID Mark the piece clicked and the places where it can moves
    * @param side, the player sho needs to play
    * @param grid, the grid of character
    * @param x and y, the postion of the piece
    */
   public void markMove(int side, char [][] grid, int x, int y){
      int[][] list;
      if(side==-1){
         if(grid[y][x]== 'O'){
            NormalPiece P = new NormalPiece();
            list = P.positionMove(side,grid,x,y);
            loop:for(int j=0; j<2; j++){ for(int i=0; i<2; i++){
               if(list[j][i] != 0){// If the list is not empty
                  // Mark the piece that has to move
                  grid[y][x]='o';
                  // Mark the place where the piece can move
                  grid[list[j][0]][list[j][1]]='^';
               }
            }}
         }
         else if(grid[y][x]== 'Q'){
            KingPiece P = new KingPiece();
            list = P.positionMove(side,grid,x,y);
            loop:for(int j=0; j<list.length; j++){ for(int i=0; i<2; i++){
               System.out.println("Here  "+j+";"+i+" "+list[j][0]+" "+list[j][1]);
               if(list[j][i] != 0){// If the list is not empty
                  // Mark the piece that has to move
                  grid[y][x]='q';
                  // Mark the place where the piece can move
                  grid[list[j][0]][list[j][1]]='^';
               }
            }} 
         }
      }
      else if(side==1){
         if(grid[y][x]== 'X'){
            NormalPiece P = new NormalPiece();
            list = P.positionMove(side,grid,x,y);
            loop:for(int j=0; j<2; j++){ for(int i=0; i<2; i++){
               if(list[j][i] != 0){// If the list is not empty
                  // Mark the piece that has to move
                  grid[y][x]='x';
                  // Mark the place where the piece can move
                  grid[list[j][0]][list[j][1]]='^';
               }
            }}
         }
         else if(grid[y][x]== 'W'){
            KingPiece P = new KingPiece();
            list = P.positionMove(side,grid,x,y);
            loop:for(int j=0; j<4; j++){ for(int i=0; i<2; i++){
               if(list[j][i] != 0){// If the list is not empty
                  // Mark the piece that has to move
                  grid[y][x]='w';
                  // Mark the place where the piece can move
                  grid[list[j][0]][list[j][1]]='^';
               }
            }}
         }
      }
      
   }
   
   
   /**
    * VOID Mark the piece clicked and the places where it can jumped
    * @param side, the player sho needs to play
    * @param grid, the grid of character
    * @param x and y, the postion of the piece
    */
   public void markJump(int side,char [][] grid, int x, int y){
      int[][] list;
      if(side==-1){
         if(grid[y][x]== 'O'){
            NormalPiece P = new NormalPiece();
            list = P.possibleJump(side,"XW",grid,x,y);
            for(int j=0; j<2; j++){ for(int i=0; i<2; i++){
               if(list[j][i] != 0){// If the list is not empty
                  grid[y][x]='o';
                  grid[list[j][0]][list[j][1]]='^';
                  if(grid[(int)(list[j][0]+y)/2][(int)(list[j][1]+x)/2]=='X')grid[(int)(list[j][0]+y)/2][(int)(list[j][1]+x)/2]='Y';
                  else if(grid[(int)(list[j][0]+y)/2][(int)(list[j][1]+x)/2]=='W')grid[(int)(list[j][0]+y)/2][(int)(list[j][1]+x)/2]='V';
               }
            }}
         }
         else if(grid[y][x]== 'Q' || grid[y][x]== 'q'){
            KingPiece P = new KingPiece();
            list = P.possibleJump(side,"XW",grid,x,y);
            for(int j=0; j<4; j++){ for(int i=0; i<2; i++){
               if(list[j][i] != 0){// If the list is not empty
                  grid[y][x]='q';
                  grid[list[j][0]][list[j][1]]='^';
                  if(grid[(int)(list[j][0]+y)/2][(int)(list[j][1]+x)/2]=='X')grid[(int)(list[j][0]+y)/2][(int)(list[j][1]+x)/2]='Y';
                  else if(grid[(int)(list[j][0]+y)/2][(int)(list[j][1]+x)/2]=='W')grid[(int)(list[j][0]+y)/2][(int)(list[j][1]+x)/2]='V';
               }
            }}  
         }
      }
      else if(side == 1){
         if(grid[y][x]== 'X'){
            NormalPiece P = new NormalPiece();
            list = P.possibleJump(side,"OQ",grid,x,y);
            for(int j=0; j<2; j++){ for(int i=0; i<2; i++){
               if(list[j][i] != 0){// If the list is not empty
                  grid[y][x]='x';
                  grid[list[j][0]][list[j][1]]='^';
                  if(grid[(int)(list[j][0]+y)/2][(int)(list[j][1]+x)/2]=='O')grid[(int)(list[j][0]+y)/2][(int)(list[j][1]+x)/2]='D';
                  else if(grid[(int)(list[j][0]+y)/2][(int)(list[j][1]+x)/2]=='Q')grid[(int)(list[j][0]+y)/2][(int)(list[j][1]+x)/2]='P';
               }
            }}
         }
         else if(grid[y][x]== 'W'){
            KingPiece P = new KingPiece();
            list = P.possibleJump(side,"OQ",grid,x,y);
            for(int j=0; j<4; j++){ for(int i=0; i<2; i++){
               if(list[j][i] != 0){// If the list is not empty
                  grid[y][x]='w';
                  grid[list[j][0]][list[j][1]]='^';
                  if(grid[(int)(list[j][0]+y)/2][(int)(list[j][1]+x)/2]=='O')grid[(int)(list[j][0]+y)/2][(int)(list[j][1]+x)/2]='D';
                  else if(grid[(int)(list[j][0]+y)/2][(int)(list[j][1]+x)/2]=='Q')grid[(int)(list[j][0]+y)/2][(int)(list[j][1]+x)/2]='P';
               }
            }}  
         }
      }
    }
   
   /**
    * VOID Mark the all the possible piece that can be jumped by a player in a turn
    * @param side, the player sho needs to play
    * @param grid, the grid of character
    */
   public void markAllJump(int side, char [][] grid){
      int[][] list;
      loop:for(int q=0; q<grid.length; q++){for(int p=0; p<grid.length; p++){
         if(side==-1){
            if(grid[q][p]== 'O'){
               NormalPiece P = new NormalPiece();
               list = P.possibleJump(side,"XW",grid,p,q);
               for(int j=0; j<2; j++){ for(int i=0; i<2; i++){
                  if(list[j][i] != 0){// If the list is not empty
                     if(grid[(int)(list[j][0]+q)/2][(int)(list[j][1]+p)/2]=='X')grid[(int)(list[j][0]+q)/2][(int)(list[j][1]+p)/2]='Y';
                     else if(grid[(int)(list[j][0]+q)/2][(int)(list[j][1]+p)/2]=='W')grid[(int)(list[j][0]+q)/2][(int)(list[j][1]+p)/2]='V';
                  }
               }}
            }
            else if(grid[q][p]== 'Q'){
               KingPiece P = new KingPiece();
               list = P.possibleJump(side,"XW",grid,p,q);
               for(int j=0; j<4; j++){ for(int i=0; i<2; i++){
                  if(list[j][i] != 0){// If the list is not empty
                     if(grid[(int)(list[j][0]+q)/2][(int)(list[j][1]+p)/2]=='X')grid[(int)(list[j][0]+q)/2][(int)(list[j][1]+p)/2]='Y';
                     else if(grid[(int)(list[j][0]+q)/2][(int)(list[j][1]+p)/2]=='W')grid[(int)(list[j][0]+q)/2][(int)(list[j][1]+p)/2]='V';
                  }
               }}  
            }   
         }
         else if(side==1){
            if(grid[q][p]== 'X'){
               NormalPiece P = new NormalPiece();
               list = P.possibleJump(side,"OQ",grid,p,q);
               for(int j=0; j<2; j++){ for(int i=0; i<2; i++){
                  if(list[j][i] != 0){// If the list is not empty
                     if(grid[(int)(list[j][0]+q)/2][(int)(list[j][1]+p)/2]=='O')grid[(int)(list[j][0]+q)/2][(int)(list[j][1]+p)/2]='D';
                     else if(grid[(int)(list[j][0]+q)/2][(int)(list[j][1]+p)/2]=='Q')grid[(int)(list[j][0]+q)/2][(int)(list[j][1]+p)/2]='P';
                  }
               }}
            }
            else if(grid[q][p]== 'W'){
               KingPiece P = new KingPiece();
               list = P.possibleJump(side,"OQ",grid,p,q);
               for(int j=0; j<4; j++){ for(int i=0; i<2; i++){
                  if(list[j][i] != 0){// If the list is not empty
                     if(grid[(int)(list[j][0]+q)/2][(int)(list[j][1]+p)/2]=='O')grid[(int)(list[j][0]+q)/2][(int)(list[j][1]+p)/2]='D';
                     else if(grid[(int)(list[j][0]+q)/2][(int)(list[j][1]+p)/2]=='Q')grid[(int)(list[j][0]+q)/2][(int)(list[j][1]+p)/2]='P';
                  }
               }}  
            }

         }
      }}
   }
   
   
}