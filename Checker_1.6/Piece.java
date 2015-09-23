/*
   Subclass for the pieces 
   Othniel KONAN
   2014/09/03
*/

abstract class Piece {
   // Set variables
   protected char empty = '~';
   protected int K=1,WIDTH=8;
   
   // Creation of abstract methods for the sub-classes
   public abstract boolean checkLegalPosition(int side, char [][] grid, int x, int y, int fx, int fy);
   public abstract int [][] possibleJump(int side,String opiece, char [][] grid, int x, int y);
   public abstract int [][] allPossibleJump(int side,char piece,String opiece, char [][] grid);
   public abstract boolean possibleMove(int side, String opiece, char [][] grid, int x, int y);
   public abstract int[][] positionMove(int side, char [][] grid, int x, int y);
   
   /**
    *  Make the move of the piece, it can either be a simple move or a jump move
    *  @param side, the player
    *  @param count, the number of the opponent's pieces left
    *  @param piece, the player's piece
    *  @param opiece, the opponent's pieces
    *  @param grid, the grid of character
    *  @prarm x and y, the initial coordinates of the piece
    *  @param fx and fy, the final coordinates of the piece
    *  @return, the number of the opponent's pieces left   
    */
   public int move(int side, int count,char piece, String opiece, char [][] grid, int x, int y, int fx, int fy){
      //(A simple move)
      if(Math.abs(fx-x)==1 && grid[fy][fx]==empty){ // If final position is the next diagonal block and the block is empty
            // We move
            grid[fy][fx] = piece; // Place the piece to the final position
            grid[y][x] = empty; // remove the piece at its original position
            
      }
      //(A jump move)
      else{
         // Check first of all if the piece in between is the opponent's piece and the final position is empty
         if(isPiece(opiece,grid,(int)((x+fx)/2),(int)((y+fy)/2)) && grid[fy][fx] == empty){
            // We jump
            grid[fy][fx] = piece; // Place the piece to the final position
            grid[y][x] = empty; // remove the piece at its original position
            grid[(int)((y+fy)/2)][(int)((x+fx)/2)] = empty;// remove the piece at that is jumped
            count--; // Decrease the count of the opponent
         }
      }
      return count;  
   } 
   
   /**
    *  BOOLEAN Check if the piece choosed is one of those which must jump
    *  @param grid, the grid of character
    *  @param piece, the player's piece
    *  @param opiece, the opponent's pieces
    *  @prarm x and y, the initial coordinates of the piece
    *  @param fx and fy, the final coordinates of the piece
    *  @param cx and cy, the coordinate of the piece which previously jumped
    *  @return, true if it's a right jump, false if not  
    */
   public boolean rightJumped(char [][] grid,char piece, String opiece, int x, int y, int fx, int fy,int cx, int cy){
      boolean check = false;
      // If the previous move was not a jump
      if(cx == 0 && cy == 0){
         // If If the three place are such that the initial position is a piece, the middle piece is an opponent piece,
         // the final position is empty 
         if(grid[fy][fx]==empty && isPiece(opiece,grid,(int)((x+fx)/2),(int)((y+fy)/2)) && grid[y][x]==piece)check=true;
      }
      // If previous jump was a move
      else{
         // Check if it satify a jump and if the piece is the one which made the previous jump
         if(grid[fy][fx]==empty && isPiece(opiece,grid,(int)((x+fx)/2),(int)((y+fy)/2)) && grid[y][x]==piece && cx==x && cy==y )check=true;}
         
      return check;
   }
   
   
   /**
    *  BOOLEAN Check if a character is in a list
    *  @param opiece, the opponent's pieces
    *  @param grid, the grid of character
    *  @prarm x and y, the initial coordinates of the piece
    *  @return, true if the character is the list, else it return false  
    */
   public boolean isPiece(String opiece, char [][] grid, int x, int y){
      boolean check = false;
      // Loop in the list
      for(int i=0; i<opiece.length(); i++){
         // If the character is in the list
         if(grid[y][x]==opiece.charAt(i)){check=true;break;}
      }
      return check;
   }
   
   
   /*********************************************************************************************************************************
    *  The Following function and of BOOLEAN type and work the same way......                                                       *
    *  Note the following definition:                                                                                               *
    *  U = UP, D = DOWN, L = LEFT, R = RIGHT, M = MOVE, J = JUMP                                                                    *
    *  A combination of any two or three of them is a method that satisfy each of them                                              *
    *  e.g. UML refers to the method UP-MOVE-LEFT which check if a piece can move diagonaly to its up-left side                     *
    *  e.g. UJ refers  to the method UP-JUMP which check if a piece can either jump diagonaly to its up-right or up-left side       *
    *  e.g. JR refers  to the method JUMP-RIGHT which check if a piece can either jump diagonaly to its down-right or up-right side *
    *  @param grid, the grid of character                                                                                           *
    *  @param opiece, the opponent's pieces                                                                                         *
    *  @prarm x and y, the initial coordinates of the piece                                                                         *
    *  @return, true if combination is satisfied, else it return false                                                                             *
    *********************************************************************************************************************************/
   /* FOR THE JUMP*/
   public boolean UJR(int x,int y,String opiece,char[][]grid){
      if(isPiece(opiece,grid,x+1,y-1) && grid[y-2][x+2]=='~')return true;
      else return false;
   }
   public boolean UJL(int x,int y,String opiece,char[][]grid){
      if(isPiece(opiece,grid,x-1,y-1) && grid[y-2][x-2]=='~')return true;
      else return false;
   }
   public boolean DJR(int x,int y,String opiece,char[][]grid){
      if(isPiece(opiece,grid,x+1,y+1) && grid[y+2][x+2]=='~')return true;
      else return false;
   }    
   public boolean DJL(int x,int y,String opiece,char[][]grid){
      if(isPiece(opiece,grid,x-1,y+1) && grid[y+2][x-2]=='~')return true;
      else return false;
   }
   public boolean JR(int x,int y,String opiece,char[][]grid){
      if(DJR(x,y,opiece,grid) || DJR(x,y,opiece,grid))return true;
      else return false;
   }
   public boolean JL(int x,int y,String opiece,char[][]grid){
      if(DJL(x,y,opiece,grid) || DJL(x,y,opiece,grid))return true;
      else return false;
   }
   public boolean DJ(int x,int y,String opiece,char[][]grid){
      if(DJL(x,y,opiece,grid) || DJR(x,y,opiece,grid))return true;
      else return false;
   }
   public boolean UJ(int x,int y,String opiece,char[][]grid){
      if(UJL(x,y,opiece,grid) || UJR(x,y,opiece,grid))return true;
      else return false;
   }
   
   /* FOR THE MOVE*/
   public boolean UMR(int x,int y,char[][]grid){
      if(grid[y-1][x+1]=='~')return true;
      else return false; 
   }
   public boolean UML(int x,int y,char[][]grid){
      if(grid[y-1][x-1]=='~')return true;
      else return false;
   }
   public boolean DMR(int x,int y,char[][]grid){
      if(grid[y+1][x+1]=='~')return true;
      else return false;
   }
   public boolean DML(int x,int y,char[][]grid){
      if(grid[y+1][x-1]=='~')return true;
      else return false;
   }
   public boolean ML(int x,int y,char[][]grid){
      if(DML(x,y,grid) || UML(x,y,grid))return true;
      else return false;
   }
   public boolean MR(int x,int y,char[][]grid){
      if(DMR(x,y,grid) || UMR(x,y,grid))return true;
      else return false;
   }
   public boolean DM(int x,int y,char[][]grid){
      if(DMR(x,y,grid) || DMR(x,y,grid))return true;
      else return false;
   }
   public boolean UM(int x,int y,char[][]grid){
      if(UMR(x,y,grid) || UML(x,y,grid))return true;
      else return false;
   }
}