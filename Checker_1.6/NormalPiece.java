/*
   Subclass Normalpiece of Piece
   Othniel KONAN
   2014/09/03 
*/

public class NormalPiece extends Piece {
   /**
    *  BOOLEAN check if the {initial,final} position satisfy the condition move of the player
    *  Note that those conditions are simply that :
    *     the normal piece of the player 1 can only move upward
    *     the normal piece of the player 2 can only move downward
    *  @param side, the player
    *  @param grid, the grid of character
    *  @prarm x and y, the initial coordinates of the piece
    *  @param fx and fy, the final coordinates of the piece
    *  @return, true if it satifies the conditions, false if not
    */
   public boolean checkLegalPosition(int side, char [][] grid, int x, int y, int fx, int fy){
      //  range is define such that it is the square of center (x,y) 
      // and of diagonal (x-2,y-2)_(x+2,y+2))
      boolean check = false;
      // Check for jump legal position
      if(fy == y + 2*side && Math.abs(fx-x)==2) check = true;
      // Check for move legal position
      else if(fy == y + side && Math.abs(fx-x)== 1) check = true;
      return check;
   }
   
    /**
    *  INT[][] Check if a piece can jump and return where it can
    *  @param side, the player
    *  @param opiece, the opponent's piece    
    *  @param grid, the grid of character
    *  @prarm x and y, the initial coordinates of the piece
    *  @return, the list containing the position of possible jump 
    */
    public int [][] possibleJump(int side,String opiece, char [][] grid, int x, int y){
      int [][] list = new int[2][2]; // A maximum of two possibility for a normal piece
      boolean R=false,L=false;
      
      if(side == -1){//Player at the buttom of the platform
      // Check if a piece can move 
      // Condition are made according to the position of the piece in the grid
         if(y>=2 && x==0 && UJR(x,y,opiece,grid))R = true;
         if(y>=2 && x==7 && UJL(x,y,opiece,grid))L = true;
         if(x==1 && y>=2 && UJR(x,y,opiece,grid)) R = true;
         if(x==6 && y>=2 && UJL(x,y,opiece,grid)) L = true;
         if(x>=2 && x<=5 && y>=2 && UJR(x,y,opiece,grid))R = true;
         if(x>=2 && x<=5 && y>=2 && UJL(x,y,opiece,grid))L = true; 
         // Add the position of the jump if there is a possibility
         // Note that list[j][i] is set to its defult value 0 if not initialized
         if(L){list[0][0]=y-2;list[0][1]=x-2;}
         if(R){list[1][0]=y-2;list[1][1]=x+2;}  
      }
      
      if(side == 1){//Player at the top of the platform
      // Check if a piece can move 
      // Condition are made according to the position of the piece in the grid
         if(y<=5 && x==0 && DJR(x,y,opiece,grid))R = true;
         if(y<=5 && x==7 && DJL(x,y,opiece,grid))L = true;
         if(x==1 && y<=5 && DJR(x,y,opiece,grid)) R = true;
         if(x==6 && y<=5 && DJL(x,y,opiece,grid)) L = true;
         if(x>=2 && x<=5 && y<=5 && DJR(x,y,opiece,grid))R = true;    
         if(x>=2 && x<=5 && y<=5 && DJL(x,y,opiece,grid))L = true;
         // Add the position of the jump if there is a possibility
         // Note that list[j][i] is set to its defult value 0 if not initialized
         if(L){list[0][0]=y+2;list[0][1]=x-2;}
         if(R){list[1][0]=y+2;list[1][1]=x+2;}
      }
      return list;
   }
   
  
   /**
    *  INT[][] Check if some pieces can jump and return where they can
    *  @param side, the player
    *  @param piece, the player's piece 
    *  @param opiece, the opponent's piece    
    *  @param grid, the grid of character
    *  @return, the list containing the position of all the possible jump 
    */ 
   public int [][] allPossibleJump(int side,char piece,String opiece, char [][] grid){
      int [][] list = new int[24][2]; // A maximum of 2 possibilities for a normal piece and 12 pieces per player so 24 places for that
      int count =0;
      boolean R=false,L=false;
      for(int j=0; j<grid.length; j++){for(int i=0; i<grid.length; i++){
         if(grid[j][i] == piece){
            if(side == -1){//Player at the buttom of the platform
            // Check if a piece can move 
            // Condition are made according to the position of the piece in the grid
               opiece="XW";
               if(j>=2 && i==0 && UJR(i,j,opiece,grid))R = true;
               if(j>=2 && i==7 && UJL(i,j,opiece,grid))L = true;
               if(i==1 && j>=2 && UJR(i,j,opiece,grid)) R = true;
               if(i==6 && j>=2 && UJL(i,j,opiece,grid)) L = true;
               if(i>=2 && i<=5 && j>=2 && UJR(i,j,opiece,grid))R = true;
               if(i>=2 && i<=5 && j>=2 && UJL(i,j,opiece,grid))L = true;
               // Add the position of the jump if there is a possibility
               // Note that list[j][i] is set to its defult value 0 if not initialized 
               if(L){list[count][0]=j-2;list[count][1]=i-2;count++;}
               if(R){list[count][0]=j-2;list[count][1]=i+2;count++;}
            }
      
            if(side == 1){//Player at the top of the platform
            // Check if a piece can move 
            // Condition are made according to the position of the piece in the grid
               opiece="OQ";
               if(j<=5 && i==0 && DJR(i,j,opiece,grid))R = true;
               if(j<=5 && i==7 && DJL(i,j,opiece,grid))L = true;
               if(i==1 && j<=5 && DJR(i,j,opiece,grid)) R = true;
               if(i==6 && j<=5 && DJL(i,j,opiece,grid)) L = true;
               if(i>=2 && i<=5 && j<=5 && DJR(i,j,opiece,grid))R = true;    
               if(i>=2 && i<=5 && j<=5 && DJL(i,j,opiece,grid))L = true;
               // Add the position of the jump if there is a possibility
               // Note that list[j][i] is set to its defult value 0 if not initialized
               if(L){list[count][0]=j+2;list[count][1]=i-2;count++;}
               if(R){list[count][0]=j+2;list[count][1]=i+2;count++;}
            }
         }
      }}
      return list;
   }
   
   
   /**
    *  BOOLEAN Check if A pieces can make a move
    *  @param side, the player
    *  @param opiece, the opponent's piece    
    *  @param grid, the grid of character
    *  @prarm x and y, the initial coordinates of the piece
    *  @return, true if the piece can still move, false if not
    */
    public boolean possibleMove(int side, String opiece, char [][] grid, int x, int y) {
      
      boolean check = false;
      
      if(side == -1){//Player at the buttom of the platform
      // Check if a piece can move 
      // Condition are made according to the position of the piece in the grid
         if (y==1 && x==0 && UMR(x,y,grid))check = true;
         else if(y==1 && x!=0 && (UMR(x,y,grid) || UML(x,y,grid)))check = true;
         else if(y>=2 && x==0 && (UMR(x,y,grid) || UJR(x,y,opiece,grid)))check = true;
         else if(y>=2 && x==7 && (UML(x,y,grid)||UJL(x,y,opiece,grid)))check = true;
         else if(x==1 && y>=2 && (UM(x,y,grid) || UJR(x,y,opiece,grid))) check = true;
         else if(x==6 && y>=2 && (UM(x,y,grid)|| UJL(x,y,opiece,grid))) check = true;
         else if(x>=2 && x<=5 && y>=2 && (UM(x,y,grid) || UJ(x,y,opiece,grid)))check = true;
      }
      
      if(side == 1){//Player at the top of the platform
      // Check if a piece can move 
      // Condition are made according to the position of the piece in the grid
         if (y==6 && x==7 && DML(x,y,grid))check = true;
         else if(y==6 && x!=7 && (DMR(x,y,grid) || DML(x,y,grid)))check = true;
         else if(y<=5 && x==0 && (DMR(x,y,grid)|| DJR(x,y,opiece,grid)))check = true;
         else if(y<=5 && x==7 && (DML(x,y,grid)||DJL(x,y,opiece,grid)))check = true;
         else if(x==1 && y<=5 && (DM(x,y,grid) || DJR(x,y,opiece,grid))) check = true;
         else if(x==6 && y<=5 && (DM(x,y,grid) || DJL(x,y,opiece,grid))) check = true;
         else if(x>=2 && x<=5 && y<=5 && (DM(x,y,grid)|| DJ(x,y,opiece,grid)))check = true;    
      }
      return check;
      
   }
   
   /**
    *  INT [][] Check if a piece can move and return the position of the possible move
    *  @param side, the player   
    *  @param grid, the grid of character
    *  @prarm x and y, the initial coordinates of the piece
    *  @return, the list containing the position of all the possible move
    */
   public int[][] positionMove(int side, char [][] grid, int x, int y) {
      int [][] list = new int[2][2];
      boolean R=false,L=false;
      
      if(side == -1){//Player at the buttom of the platform
      // Check if a piece can move 
      // Condition are made according to the position of the piece in the grid
         if (y==1 && x==0 && UMR(x,y,grid))R = true;
         if(y==1 && x!=0 && UMR(x,y,grid))R = true;
         if(y==1 && x!=0 && UML(x,y,grid))L = true;
         if(y>=2 && x==0 && UMR(x,y,grid))R = true;
         if(y>=2 && x==7 && UML(x,y,grid))L = true;
         if(x==1 && y>=2 && UMR(x,y,grid)) R = true;
         if(x==1 && y>=2 && UML(x,y,grid)) L = true;
         if(x==6 && y>=2 && UMR(x,y,grid)) R = true;
         if(x==6 && y>=2 && UML(x,y,grid)) L = true;
         if(x>=2 && x<=5 && y>=2 && UMR(x,y,grid))R = true;  
         if(x>=2 && x<=5 && y>=2 && UML(x,y,grid))L = true;
         // Add the position of the move if there is a possibility
         // Note that list[j][i] is set to its defult value 0 if not initialized
         if(L){list[0][0]=y-1;list[0][1]=x-1;}
         if(R){list[1][0]=y-1;list[1][1]=x+1;} 
  
      }
      
      if(side == 1){//Player at the top of the platform
      // Check if a piece can move 
      // Condition are made according to the position of the piece in the grid
         if (y==6 && x==7 && DML(x,y,grid))L = true;
         if(y==6 && x!=7 && DMR(x,y,grid))R = true;
         if(y==6 && x!=7 && DML(x,y,grid))L = true;
         if(y<=5 && x==0 && DMR(x,y,grid))R = true;
         if(y<=5 && x==7 && DML(x,y,grid))L = true;
         if(x==1 && y<=5 && DMR(x,y,grid)) R = true;
         if(x==1 && y<=5 && DML(x,y,grid)) L = true;
         if(x==6 && y<=5 && DMR(x,y,grid)) R = true;
         if(x==6 && y<=5 && DML(x,y,grid)) L = true;
         if(x>=2 && x<=5 && y<=5 && DMR(x,y,grid))R = true; 
         if(x>=2 && x<=5 && y<=5 && DML(x,y,grid))L = true;
         // Add the position of the move if there is a possibility
         // Note that list[j][i] is set to its defult value 0 if not initialized
         if(L){list[0][0]=y+1;list[0][1]=x-1;}
         if(R){list[1][0]=y+1;list[1][1]=x+1;}    
      }
      return list;
   }
}