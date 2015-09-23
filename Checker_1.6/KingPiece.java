/*
   Subclass KingPiece of Piece
   Othniel KONAN
   2014/09/03 
*/

public class KingPiece extends Piece {
   
   /**
    *  BOOLEAN check if the {initial,final} position satisfy the condition move of the player
    *  Note that those conditions are simply that :
    *     the king piece can move upward and downward
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
      
      if(Math.abs(fy-y) <= 2 && Math.abs(fx-x)<=2) check = true;
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
   public int [][] possibleJump(int side, String opiece, char [][] grid, int x, int y){
      int [][] list = new int[4][2];
      boolean UR=false,UL=false,DR=false,DL=false;
      // Check if a piece can move 
      // Condition are made according to the position of the piece in the grid
      if(x==1 && y==0 && DJR(x,y,opiece,grid))DR=true;
      if(x==0 && y==1 && DJR(x,y,opiece,grid))DR=true; 
      if(x==0 && y==7 && UJR(x,y,opiece,grid))UR=true;
      if(x==7 && y==0 && DJL(x,y,opiece,grid))DL=true;
      if(x==7 && y==6 && UJL(x,y,opiece,grid))UL=true;
      if(x==6 && y==7 && UJL(x,y,opiece,grid))UL=true;
      if(x==6 && y==1 && DJL(x,y,opiece,grid))DL=true;
      if(x==1 && y==6 && UJR(x,y,opiece,grid))UR=true;
      if(x==0 && (y==3 || y==5) && UJR(x,y,opiece,grid))UR=true;
      if(x==0 && (y==3 || y==5) && DJR(x,y,opiece,grid))DR=true;
      if(x==7 && (y==2 || y==4) && UJL(x,y,opiece,grid))UL=true;
      if(x==7 && (y==2 || y==4) && DJL(x,y,opiece,grid))DL=true;
      if(x==1 && (y==2 || y==4) && UJR(x,y,opiece,grid))UR=true;
      if(x==1 && (y==2 || y==4) && DJR(x,y,opiece,grid))DR=true;
      if(x==6 && (y==3 || y==5) && UJL(x,y,opiece,grid))UL=true;
      if(x==6 && (y==3 || y==5) && DJL(x,y,opiece,grid))DL=true;
      if((x==3 || x==5) && y==0 && DJR(x,y,opiece,grid))DR=true;
      if((x==3 || x==5) && y==0 && DJL(x,y,opiece,grid))DL=true;
      if((x==2 || x==4) && y==7 && UJR(x,y,opiece,grid))UR=true;
      if((x==2 || x==4) && y==7 && UJL(x,y,opiece,grid))UL=true;
      if((x==2 || x==4) && y==1 && DJR(x,y,opiece,grid))DR=true;
      if((x==2 || x==4) && y==1 && DJL(x,y,opiece,grid))DL=true;
      if((x==3 || x==5) && y==6 && UJR(x,y,opiece,grid))UR=true;
      if((x==3 || x==5) && y==6 && UJL(x,y,opiece,grid))UL=true;
      if(x>=2 && x<=5 && y>=2 && y<=5 && UJR(x,y,opiece,grid))UR=true;
      if(x>=2 && x<=5 && y>=2 && y<=5 && UJL(x,y,opiece,grid))UL=true;
      if(x>=2 && x<=5 && y>=2 && y<=5 && DJR(x,y,opiece,grid))DR=true;
      if(x>=2 && x<=5 && y>=2 && y<=5 && DJL(x,y,opiece,grid))DL=true;
      // Add the position of the jump if there is a possibility
      // Note that list[j][i] is set to its defult value 0 if not initialized
      if(UL){list[0][0]=y-2;list[0][1]=x-2;}
      if(UR){list[1][0]=y-2;list[1][1]=x+2;}
      if(DL){list[2][0]=y+2;list[2][1]=x-2;}
      if(DR){list[3][0]=y+2;list[3][1]=x+2;}
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
      int [][] list = new int[48][2];
      int count =0;
      boolean UR=false,UL=false,DR=false,DL=false;
      
      for(int j=0; j<grid.length; j++){for(int i=0; i<grid.length; i++){
         if(grid[j][i] == piece){
         // Check if a piece can move 
         // Condition are made according to the position of the piece in the grid
            if(i==1 && j==0 && DJR(i,j,opiece,grid))DR=true;
            if(i==0 && j==1 && DJR(i,j,opiece,grid))DR=true; 
            if(i==0 && j==7 && UJR(i,j,opiece,grid))UR=true;
            if(i==7 && j==0 && DJL(i,j,opiece,grid))DL=true;
            if(i==7 && j==6 && UJL(i,j,opiece,grid))UL=true;
            if(i==6 && j==7 && UJL(i,j,opiece,grid))UL=true;
            if(i==6 && j==1 && DJL(i,j,opiece,grid))DL=true;
            if(i==1 && j==6 && UJR(i,j,opiece,grid))UR=true;
            if(i==0 && (j==3 || j==5) && UJR(i,j,opiece,grid))UR=true;
            if(i==0 && (j==3 || j==5) && DJR(i,j,opiece,grid))DR=true;
            if(i==7 && (j==2 || j==4) && UJL(i,j,opiece,grid))UL=true;
            if(i==7 && (j==2 || j==4) && DJL(i,j,opiece,grid))DL=true;
            if(i==1 && (j==2 || j==4) && UJR(i,j,opiece,grid))UR=true;
            if(i==1 && (j==2 || j==4) && DJR(i,j,opiece,grid))DR=true;
            if(i==6 && (j==3 || j==5) && UJL(i,j,opiece,grid))UL=true;
            if(i==6 && (j==3 || j==5) && DJL(i,j,opiece,grid))DL=true;
            if((i==3 || i==5) && j==0 && DJR(i,j,opiece,grid))DR=true;
            if((i==3 || i==5) && j==0 && DJL(i,j,opiece,grid))DL=true;
            if((i==2 || i==4) && j==7 && UJR(i,j,opiece,grid))UR=true;
            if((i==2 || i==4) && j==7 && UJL(i,j,opiece,grid))UL=true;
            if((i==2 || i==4) && j==1 && DJR(i,j,opiece,grid))DR=true;
            if((i==2 || i==4) && j==1 && DJL(i,j,opiece,grid))DL=true;
            if((i==3 || i==5) && j==6 && UJR(i,j,opiece,grid))UR=true;
            if((i==3 || i==5) && j==6 && UJL(i,j,opiece,grid))UL=true;
            if(i>=2 && i<=5 && j>=2 && j<=5 && UJR(i,j,opiece,grid))UR=true;
            if(i>=2 && i<=5 && j>=2 && j<=5 && UJL(i,j,opiece,grid))UL=true;
            if(i>=2 && i<=5 && j>=2 && j<=5 && DJR(i,j,opiece,grid))DR=true;
            if(i>=2 && i<=5 && j>=2 && j<=5 && DJL(i,j,opiece,grid))DL=true;
            // Add the position of the jump if there is a possibility
            // Note that list[j][i] is set to its defult value 0 if not initialized 
            if(UL){list[count][0]=j-2;list[count][1]=i-2;count++;}
            if(UR){list[count][0]=j-2;list[count][1]=i+2;count++;}
            if(DL){list[count][0]=j+2;list[count][1]=i-2;count++;}
            if(DR){list[count][0]=j+2;list[count][1]=i+2;count++;}
            
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
      // Check if a piece can move 
      // Condition are made according to the position of the piece in the grid
      if(x==1 && y==0 && (DM(x,y,grid) || DJR(x,y,opiece,grid)))check=true;
      else if(x==0 && y==1 && (MR(x,y,grid) || DJR(x,y,opiece,grid)))check=true; 
      else if(x==0 && y==7 && (UMR(x,y,grid) || UJR(x,y,opiece,grid)))check=true;
      else if(x==7 && y==0 && (DML(x,y,grid) || DJL(x,y,opiece,grid)))check=true;
      else if(x==7 && y==6 && (ML(x,y,grid) || UJL(x,y,opiece,grid)))check=true;
      else if(x==6 && y==7 && (UM(x,y,grid) || UJL(x,y,opiece,grid)))check=true;
      else if(x==6 && y==1 && (MR(x,y,grid) || ML(x,y,grid) || DJL(x,y,opiece,grid)))check=true;
      else if(x==1 && y==6 && (MR(x,y,grid) || ML(x,y,grid) || UJR(x,y,opiece,grid)))check=true;
      else if(x==0 && (y==3 || y==5) && (MR(x,y,grid) || JR(x,y,opiece,grid)))check=true;
      else if(x==7 && (y==2 || y==4) && (ML(x,y,grid) || JL(x,y,opiece,grid)))check=true;
      else if(x==1 && (y==2 || y==4) && (MR(x,y,grid) || ML(x,y,grid) || JR(x,y,opiece,grid)))check=true;
      else if(x==6 && (y==3 || y==5) && (MR(x,y,grid) || ML(x,y,grid) || JL(x,y,opiece,grid)))check=true;
      else if((x==3 || x==5) && y==0 && (DM(x,y,grid) || DJ(x,y,opiece,grid)))check=true;
      else if((x==2 || x==4) && y==7 && (UM(x,y,grid) || UJ(x,y,opiece,grid)))check=true;
      else if((x==2 || x==4) && y==1 && (MR(x,y,grid) || ML(x,y,grid) || DJ(x,y,opiece,grid)))check=true;
      else if((x==3 || x==5) && y==6 && (MR(x,y,grid) || ML(x,y,grid) || UJ(x,y,opiece,grid)))check=true;
      else if(x>=2 && x<=5 && y>=2 && y<=5 && (MR(x,y,grid) || ML(x,y,grid) || JR(x,y,opiece,grid) || JL(x,y,opiece,grid)))check=true;
      return check;
   }
   
   
   /**
    *  INT [][] Check if a piece can move and return the position of the possible move
    *  @param side, the player   
    *  @param grid, the grid of character
    *  @prarm x and y, the initial coordinates of the piece
    *  @return, the list containing the position of all the possible move
    */
  public int [][] positionMove(int side, char [][] grid, int x, int y) {
      int [][] list = new int[4][2];
      boolean UR=false,UL=false,DR=false,DL=false;
      // Check if a piece can move 
      // Condition are made according to the position of the piece in the grid
      if(x==1 && y==0 && DMR(x,y,grid))DR=true;
      if(x==1 && y==0 && DML(x,y,grid))DL=true;
      if(x==0 && y==1 && UMR(x,y,grid))UR=true;
      if(x==0 && y==1 && DMR(x,y,grid))DR=true; 
      if(x==0 && y==7 && UMR(x,y,grid))UR=true;
      if(x==7 && y==0 && DML(x,y,grid))DL=true;
      if(x==7 && y==6 && UML(x,y,grid))UL=true;
      if(x==7 && y==6 && DML(x,y,grid))DL=true;
      if(x==6 && y==7 && UMR(x,y,grid))UR=true;
      if(x==6 && y==7 && UML(x,y,grid))UL=true;
      if(x==6 && y==1 && UMR(x,y,grid))UR=true;
      if(x==6 && y==1 && DMR(x,y,grid))DR=true;
      if(x==6 && y==1 && UML(x,y,grid))UL=true;
      if(x==6 && y==1 && DML(x,y,grid))DL=true;
      if(x==1 && y==6 && UMR(x,y,grid))UR=true;
      if(x==1 && y==6 && DMR(x,y,grid))DR=true;
      if(x==1 && y==6 && UML(x,y,grid))UL=true;
      if(x==1 && y==6 && DML(x,y,grid))DL=true;
      if(x==0 && (y==3 || y==5) && UMR(x,y,grid))UR=true;
      if(x==0 && (y==3 || y==5) && DMR(x,y,grid))DR=true;
      if(x==7 && (y==2 || y==4) && UML(x,y,grid))UL=true;
      if(x==7 && (y==2 || y==4) && DML(x,y,grid))DL=true;
      if(x==1 && (y==2 || y==4) && UMR(x,y,grid))UR=true;
      if(x==1 && (y==2 || y==4) && DMR(x,y,grid))DR=true;
      if(x==1 && (y==2 || y==4) && UML(x,y,grid))UL=true;
      if(x==1 && (y==2 || y==4) && DML(x,y,grid))DL=true;
      if(x==6 && (y==3 || y==5) && UMR(x,y,grid))UR=true;
      if(x==6 && (y==3 || y==5) && DMR(x,y,grid))DR=true;
      if(x==6 && (y==3 || y==5) && UML(x,y,grid))UL=true;
      if(x==6 && (y==3 || y==5) && DML(x,y,grid))DL=true;
      if((x==3 || x==5) && y==0 && DMR(x,y,grid))DR=true;
      if((x==3 || x==5) && y==0 && DML(x,y,grid))DL=true;
      if((x==2 || x==4) && y==7 && UMR(x,y,grid))UR=true;
      if((x==2 || x==4) && y==7 && UML(x,y,grid))UL=true;
      if((x==2 || x==4) && y==1 && UMR(x,y,grid))UR=true;
      if((x==2 || x==4) && y==1 && DMR(x,y,grid))DR=true;
      if((x==2 || x==4) && y==1 && UML(x,y,grid))UL=true;
      if((x==2 || x==4) && y==1 && DML(x,y,grid))DL=true;
      if((x==3 || x==5) && y==6 && UMR(x,y,grid))UR=true;
      if((x==3 || x==5) && y==6 && DMR(x,y,grid))DR=true;
      if((x==3 || x==5) && y==6 && UML(x,y,grid))UL=true;
      if((x==3 || x==5) && y==6 && DML(x,y,grid))DL=true;
      if(x>=2 && x<=5 && y>=2 && y<=5 && UMR(x,y,grid))UR=true;
      if(x>=2 && x<=5 && y>=2 && y<=5 && DMR(x,y,grid))DR=true;
      if(x>=2 && x<=5 && y>=2 && y<=5 && UML(x,y,grid))UL=true;
      if(x>=2 && x<=5 && y>=2 && y<=5 && DML(x,y,grid))DL=true;
      // Add the position of the move if there is a possibility
      // Note that list[j][i] is set to its defult value 0 if not initialized
      if(UL){list[0][0]=y-1;list[0][1]=x-1;}
      if(UR){list[1][0]=y-1;list[1][1]=x+1;}
      if(DL){list[2][0]=y+1;list[2][1]=x-1;}
      if(DR){list[3][0]=y+1;list[3][1]=x+1;}
      return list;
   }
   
}