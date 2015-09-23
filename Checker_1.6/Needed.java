/*
   Subclass Normalpiece of Piece
   Othniel KONAN
   2014/09/03 
*/

public class Needed{

   GridPlatform pF = new GridPlatform();
   NormalPiece nP = new NormalPiece();
   KingPiece kP = new KingPiece();
   private char piece = 's';
   private String opiece="";
   
   
   /**
    * BOOLEAN Check the move one want to make is valid
    * The conditions are:
    *    position must be in a range
    *    correct piece choose (according to who has to play)
    *    final position should not be an empty legal block(black block)
    *    there is a possibility to jump and the final position is in the list of possible jump and the correct piece is chosen 
    *  @param side, the player
    *  @prarm x and y, the initial coordinates of the piece
    *  @param fx and fy, the final coordinates of the piece
    *  @param cx and cy, the coordinates of the piece that needs to be jumped
    *  @param grid, the grid of character
    *  @return, true if the conditions are satified, false if not
    */
   public boolean checkPosition(int side, int x, int y, int fx, int fy,int cx, int cy, char[][] grid){
      piece = grid[y][x]; 
      boolean check = true;
      if(piece=='O' || piece=='Q')opiece="XW";
      if(piece=='X' || piece=='W')opiece="OQ";
      // Check if the initial or final position is empty
      if(grid[y][x]==' ' || grid[y][x]=='~' || grid[fy][fx]==' ')check=false;
      else if(piece=='O' || piece=='X') {
        // Check the condition stated above
        if(!nP.checkLegalPosition(side,grid,x,y,fx,fy) || !pF.checkPlayer(side,x,y,grid) || grid[fy][fx]!='~'
               || (pF.notEmptyAllList(side,grid) && !pF.isInList(side,opiece,grid,fx,fy)) 
               || (pF.notEmptyAllList(side,grid) && pF.isInList(side,opiece,grid,fx,fy) 
                    && !nP.rightJumped(grid,piece,opiece,x,y,fx,fy,cx,cy)))check=false;
      }
      else if(piece=='Q' || piece=='W') {
         // Check the condition stated above
        if(!kP.checkLegalPosition(side,grid,x,y,fx,fy) || !pF.checkPlayer(side,x,y,grid) || grid[fy][fx]!='~' 
               || (pF.notEmptyAllList(side,grid) && !pF.isInList(side,opiece,grid,fx,fy))
               || (pF.notEmptyAllList(side,grid) && pF.isInList(side,opiece,grid,fx,fy) 
                    && !kP.rightJumped(grid,piece,opiece,x,y,fx,fy,cx,cy)))check=false;
      }
      return check;
   }   
   
   
   /**
    * INT [] Make the move and return a list containing respectively the parameter, combo, count_1, count_2
    *  @param side, the player
    *  @param count_1, the number of player1 pieces left
    *  @param count_2, the number of player2 pieces left
    *  @prarm x and y, the initial coordinates of the piece
    *  @param fx and fy, the final coordinates of the piece
    *  @param grid, the grid of character
    *  @return, true if the conditions are satified, false if not
    */
   public int[] theMove(int side, int count_1, int count_2, int x, int y, int fx, int fy, char[][] grid){
      piece = grid[y][x];
      int [] list = new int[3];
      if(piece=='O' || piece=='Q')opiece="XW";
      if(piece=='X' || piece=='W')opiece="OQ";
      // make copy of the count in order to check if a piece was jumped
      int copy_1=count_1, copy_2=count_2;
      // initialise check
      int check = 1;
      
      if(piece=='O' || piece=='Q'){
         if(piece=='O'){
            count_2 = nP.move(side,count_2,piece,opiece,grid,x,y,fx,fy);
             // Check the piece has reached the king row
            //Normal piece just reached king row and become kng so we stop the move
             if(fy==0){
               grid[fy][fx]='Q';
               check = 0;
              }
         }
         else if(piece=='Q')count_2 = kP.move(side,count_2,piece,opiece,grid,x,y,fx,fy);
         if(pF.emptyList(side,grid,fx,fy))check=0;// no Combo if list is empty 
         else if(copy_2 == count_2) check=0;// No combo if list is not empty and count_2 did not changed(didn't jump already)
         
      }
      else if(piece=='X' || piece=='W'){
         if(piece=='X'){
            count_1 = nP.move(side,count_1,piece,opiece,grid,x,y,fx,fy);
            // Check the piece has reached the king row
            //Normal piece just reached king row and become kng so we stop the move
            if(fy==7){
               grid[fy][fx]='W';
               check = 0;
              }
         }
         else if(piece=='W')count_1 = kP.move(side,count_1,piece,opiece,grid,x,y,fx,fy);
         if(pF.emptyList(side,grid,fx,fy))check=0;// no Combo if list is empty 
         else if(copy_1 == count_1)check=0;// No combo if list is not empty and count_2 did not changed(didn't jump already)
         
      }
      list[0]=check; list[1]=count_1; list[2]=count_2;
      return list; 
   }


}