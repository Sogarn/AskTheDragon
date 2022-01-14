import java.util.*;
// DND Floor
// SYST 335
////////////////////////////////////////////////////////////////
class Grid<T>{                       // each piece of the floor
  public String data = "";                     // what is in one grid on the floor
  public Grid right;                 // pointer to right grid
  public Grid down;                  // pointer to lower grid
  public Grid left;                  // pointer to left grid
  public Grid up;                    // pointer to upper grid
  public Grid upperLeft;             // pointer to upper left grid
  public Grid upperRight;            // pointer to upper right grid
  public Grid lowerLeft;             // pointer to lower left grid
  public Grid lowerRight;            // pointer to lower right grid
  public int x;                     // pointer to lower right grid
  public int y;                     // pointer to lower right grid
// -------------------------------------------------------------
  public Grid(String s){data = s;}        // constructor given a value in it
// -------------------------------------------------------------
  public Grid(){}                    // constructor given nothing
}  // end class Grid
////////////////////////////////////////////////////////////////
class Floor<T>{                       // the playing field
  private Grid head;                  // reference to the first item
  int row;                            // number of rows
  int col;                            // number of cols
// -------------------------------------------------------------
  public Floor(){head = null;}        // constructor for an empty floor
// -------------------------------------------------------------
  public Floor (int ro,int co){  // create the playing field
    row=ro;                           // initialize the row field
    col=co;                           // initialize the col field
    Grid[][] arrayFloor = new Grid[row][col];// make an array to hold the nodes
    for (int r=0; r<row; r++) {       // go through the rows
      for (int c=0; c<col; c++)       // go through the columns
        arrayFloor[r][c]=new Grid();  // put a node in each element of the array
    }
    for (int r=0; r<row; r++) {       // go throught the rows
      for (int c=0; c<col; c++) {     // go through the columns
        if((c>0)&&(c<col-1)&&(r>0)&&(r<row-1)){// if you are not in the border grid pieces
          arrayFloor[r][c].right = arrayFloor[r][c+1];// link the nodes to the right
          arrayFloor[r][c].left = arrayFloor[r][c-1];// link the nodes to the left
          arrayFloor[r][c].upperLeft = arrayFloor[r-1][c-1];// link the nodes to the upper left
          arrayFloor[r][c].upperRight = arrayFloor[r-1][c+1];// link the nodes to the upper right
          arrayFloor[r][c].lowerLeft = arrayFloor[r+1][c-1];// link the nodes to the lower left
          arrayFloor[r][c].lowerRight = arrayFloor[r+1][c+1];// link the nodes to the lower right
          arrayFloor[r][c].down = arrayFloor[r+1][c];// link the nodes down
          arrayFloor[r][c].up = arrayFloor[r-1][c];// link the nodes up
        }
        if((c>0)&&(c<col-1)&&(r==0)){ // for the grid pieces on the top of the floor that are not corner pieces
          arrayFloor[r][c].right = arrayFloor[r][c+1];//link the nodes to the right
          arrayFloor[r][c].left = arrayFloor[r][c-1];//link the nodes to the left
          arrayFloor[r][c].lowerLeft = arrayFloor[r+1][c-1];//link the nodes to the lower left
          arrayFloor[r][c].lowerRight = arrayFloor[r+1][c+1];//link the nodes to the lower right
          arrayFloor[r][c].down = arrayFloor[r+1][c];//link the nodes down
        }
        if((c>0)&&(c<col-1)&&(r==row-1)){// for the grid pieces on the bottom of the floor that are not corner pieces
          arrayFloor[r][c].right = arrayFloor[r][c+1];// link the nodes to the right
          arrayFloor[r][c].left = arrayFloor[r][c-1];// link the nodes to the left
          arrayFloor[r][c].upperLeft = arrayFloor[r-1][c-1];// link the nodes to the upper left
          arrayFloor[r][c].upperRight = arrayFloor[r-1][c+1];// link the nodes to the upper right
          arrayFloor[r][c].up = arrayFloor[r-1][c];// link the nodes up
        }
        if((c==0)&&(r>0)&&(r<row-1)){ // for the grid pieces on the left of the floor that are not corner pieces
          arrayFloor[r][c].right = arrayFloor[r][c+1];// link the nodes to the right
          arrayFloor[r][c].upperRight = arrayFloor[r-1][c+1];// link the nodes to the upper right
          arrayFloor[r][c].lowerRight = arrayFloor[r+1][c+1];// link the nodes to the lower right
          arrayFloor[r][c].down = arrayFloor[r+1][c];// link the nodes down
          arrayFloor[r][c].up = arrayFloor[r-1][c];// link the nodes up
        }
        if((c==col-1)&&(r>0)&&(r<row-1)){// for the grid pieces on the right of the floor that are not corner pieces
          arrayFloor[r][c].left = arrayFloor[r][c-1];// link the nodes to the left
          arrayFloor[r][c].upperLeft = arrayFloor[r-1][c-1];// link the nodes to the upper right
          arrayFloor[r][c].lowerLeft = arrayFloor[r+1][c-1];// link the nodes to the lower right
          arrayFloor[r][c].down = arrayFloor[r+1][c];// link the nodes down
          arrayFloor[r][c].up = arrayFloor[r-1][c];// link the nodes up
        }
      }
    }
    head = arrayFloor[0][0];          //point to the top left element
    arrayFloor[0][0].right = arrayFloor[0][1];// link top left node right
    arrayFloor[0][0].lowerRight = arrayFloor[1][1];// link top left node to lower right
    arrayFloor[0][0].down = arrayFloor[1][0];// link top left node to below
    
    arrayFloor[0][col-1].left = arrayFloor[0][col-2];// link top right node left
    arrayFloor[0][col-1].lowerLeft = arrayFloor[1][col-2];// link top right node to loower left
    arrayFloor[0][col-1].down =arrayFloor[1][col-1];// link top right node down
    
    arrayFloor[row-1][0].right = arrayFloor[row-1][1];// link bottom left node right
    arrayFloor[row-1][0].upperRight = arrayFloor[row-2][1];// link bottom left node to upper right
    arrayFloor[row-1][0].up =arrayFloor[row-2][0];// link bottom left node up
    
    arrayFloor[row-1][col-1].left = arrayFloor[row-1][col-2];// link bottom right node left
    arrayFloor[row-1][col-1].upperLeft = arrayFloor[row-2][col-2];// link bottom right node to upper left
    arrayFloor[row-1][col-1].up =arrayFloor[row-2][col-1];// link bottom right node up
  }
// -------------------------------------------------------------
  public void assignCell(int r, int c, String dd){// fill one cell w/ given value
    Grid current = head;              // start at beginning
    for(int i = 0; i<r; i++){current = current.down;}// move right to next link
    for(int i = 0; i<c; i++){current = current.right;}// move down to next link
    current.data=dd;                  // change the value
    current.x=c;
    current.y=r;
  }
//--------------------------------------------------------------
  public ArrayList<Integer> check(int r, int c, int range, String target){// given a point and a range check the squares around it
    // and return the coordinates of the nearest object.  If there are no objects
    // near it return its value
    ArrayList<Integer> al= new ArrayList<Integer>();// create an array list that will hold the position of things
    al.add(-1);                     // make the first vaue null
    ArrayList<Integer> finalList = new ArrayList<Integer>();// make another list that will hold the final value
    Grid current = head;              // start at beginning
    Grid current2 = head;             // start at beginning
    Grid current3 = head;             // start at beginning
    for(int i = 0; i<r; i++){
      current = current.down;         // move down to next link
      current2 = current2.down;       // move down to next link
      current3 = current3.down;       // move down to next link
    }
    for(int i = 0; i<c; i++){
      current = current.right;        // move right to next link
      current2 = current2.right;      // move right to next link
      current3 = current3.right;      // move right to next link
    }
    int u = 0;                        // based on range, how far a character can see above them
    int l = 0;                        // based on range, how far a character can see to their left
    int ri = 0;                       // based on range, how far a character can see to their right
    int d = 0;                        // based on range, how far a character can see below them
    for(int i = 0; i<range; i++){
      while(current.up!=null){        // find out how far the character can see above him
        current = current.up;
        current2 = current2.up;
        u--;
        break;
      }
      while(current.left!= null){     // find out how far the character can see to the left of himself
        current = current.left;
        current2 = current2.left;
        l--;
        break;
      }
      while(current3.right!= null){   // find out how far the character can see to the right of himself
        current3 = current3.right;
        ri++;
        break;
      }
      while(current3.down!= null){    // find out how far the character can see below him
        current3 = current3.down;
        d++;
        break;
      }
    }
    for(int i = u; i<d+1; i++){       // go through the grid pieces in the rows that the character can see
      for(int j = l; j<ri+1; j++){    // go through the grid pieces in the columns that the character can see
        if (!current.data.equals("")){    // if there is something there
          if(current.data.equals(target)){//If it is an enemy
            //This could probably be changed to a list of enemy names
            if(al.get(0)==-1)         // if it is the first thing being put in the array list
              al.remove(0);             // make the array list empty
            al.add(j);                  // add the column location
            al.add(i);                  // add the row location
          }
        }
        if(j==ri)                     // break when you get to the end
          break;
        current = current.right;      // move right to next line
      }
      if (i == d)                     // break when you get to the end
        break;
      current = current.down;         // move down to next link
      current2 = current2.down;       // move down to next link
      current = current2;             // move back to the left
    }
    if(al.get(0)== -1){             // if no coordinates were added to the list
      al.remove(0);                   // make it empty
      al.add(r);                      // put the row that the character is in
      al.add(c);                      // and the column that the character is in
      return al;                      // and then return it
    }
    System.out.println(al);
    finalList.add(al.get(0));         // put the first set of coordinates in the 2nd array list
    finalList.add(al.get(1));
    for(int i = 0; i< al.size(); i+=2){// go through all the coordinates in the array list
      if((al.get(i)*al.get(i) + al.get(i+1)*al.get(i+1))<(finalList.get(0)*finalList.get(0) + finalList.get(1)*finalList.get(1))){
        // use the pythagorean theorem to figure out which coordinates are the closest
        finalList = new ArrayList<Integer>();// clear the holder array list
        finalList.add(al.get(i));     // put the new closest coordinates in them
        finalList.add(al.get(i+1));
      }   
    }
    al = new ArrayList<Integer>();    // clear the first array list
    al.add(finalList.get(0) + c);     // put the column of the closest thing in the array list
    al.add(finalList.get(1) + r);     // put the row of the closest thing in the array list
    return al;                        // return those coordinates
  }
  //--------------------------------------------------------------
  public ArrayList<String> burst(int characterRow, int characterColumn, int radius, String target){// given a point and a range do
    //damage to the squares around it
    // and return the coordinates of the nearest object.  If there are no objects
    // near it return its value
    ArrayList<String> al= new ArrayList<String>();// create an array list that will hold the position of things
    al.add("");                     // make the first vaue null
    ArrayList<String> finalList = new ArrayList<String>();// make another list that will hold the final value
    Grid current = head;              // start at beginning
    Grid current2 = head;             // start at beginning
    Grid current3 = head;             // start at beginning
    
    for(int i = 0; i<characterRow-1; i++){
      current = current.down;         // move down to next link
      current2 = current2.down;       // move down to next link
      current3 = current3.down;       // move down to next link
    }
    for(int i = 0; i<characterColumn-1; i++){
      current = current.right;        // move right to next link
      current2 = current2.right;      // move right to next link
      current3 = current3.right;      // move right to next link
    }
    int u = 0;                        // based on range, how far a character can see above them
    int l = 0;                        // based on range, how far a character can see to their left
    int ri = 0;                       // based on range, how far a character can see to their right
    int d = 0;                        // based on range, how far a character can see below them
    for(int i = 0; i<radius; i++){
      while(current.up!=null){        // find out how far the character can see above him
        current = current.up;
        current2 = current2.up;
        u--;
        break;
      }
      while(current.left!= null){     // find out how far the character can see to the left of himself
        current = current.left;
        current2 = current2.left;
        l--;
        break;
      }
      while(current3.right!= null){   // find out how far the character can see to the right of himself
        current3 = current3.right;
        ri++;
        break;
      }
      while(current3.down!= null){    // find out how far the character can see below him
        current3 = current3.down;
        d++;
        break;
      }
    }
    for(int i = u; i<d+1; i++){       // go through the grid pieces in the rows that the character can see
      for(int j = l; j<ri+1; j++){    // go through the grid pieces in the columns that the character can see
        if (!current.data.equals("")){    // if there is something there
          if(current.data.equals(target)){ // If it is an enemy
            if(al.get(0)=="")         // if it is the first thing being put in the array list
              al.remove(0);             // make the array list empty
            al.add(current.data);         // add the thing
          }
        }
        if(j==ri)                     // break when you get to the end
          break;
        current = current.right;      // move right to next line
      }
      if (i == d)                     // break when you get to the end
        break;
      current = current.down;         // move down to next link
      current2 = current2.down;       // move down to next link
      current = current2;             // move back to the left
    }
    if(al.get(0)== ""){             // if no coordinates were added to the list
      al.remove(0);                   // make it empty
      al.add("");                      // put nothing in the list
      return al;                      // and then return it
    }
    return al;                        // return those coordinates
  }
  //--------------------------------------------------------------
  /*         ------------------
   *         |  1   |   2  |  3  |
   *         |  4   |   X  |  5  |
   *         |  6   |   7  |  8  |
   *         ------------------
   */
  public ArrayList<String> blast(int characterRow, int characterColumn, int radius, int square, String target){
// given a point, radius, and which square it is do damage to the squares around it
    // and return the damaged objects.  If there are no objects return an empty string
    ArrayList<String> al= new ArrayList<String>();// create an array list that will hold the position of things
    al.add("");                     // make the first vaue null
    ArrayList<String> finalList = new ArrayList<String>();// make another list that will hold the final value
    Grid current = head;              // start at beginning
    Grid current2 = head;             // start at beginning
    Grid current3 = head;             // start at beginning
      
    for(int i = 0; i<characterRow; i++){
      current = current.down;         // move down to next link
      current2 = current2.down;       // move down to next link
      current3 = current3.down;       // move down to next link
    }
    for(int i = 0; i<characterColumn; i++){
      current = current.right;        // move right to next link
      current2 = current2.right;      // move right to next link
      current3 = current3.right;      // move right to next link
    }
    int u = characterRow;                        // based on range, how far a character can see above them
    int l = characterColumn;                        // based on range, how far a character can see to their left
    int ri = characterColumn;                       // based on range, how far a character can see to their right
    int d = characterRow;                        // based on range, how far a character can see below them
    if((square == 1)||(square == 2)||(square == 3)){// If it is in the top row
      for(int i = 0; i<radius-1; i++){
        while(current.up!=null){                  // And Not empty
          current = current.up;
          current2 = current2.up;
          u--;                                    // Move up
          break;
        }
      }
    }
    if((square == 1)||(square == 4)||(square == 6)){ // If it is in the first col
      for(int i = 0; i<radius-1; i++){
        while(current.left!=null){        // And not empty
          current = current.left;
          current2 = current2.left;
          l--;                              // Move left
          break;
        }
      }
    }
    if((square == 4)||(square == 5)){      // If it is one of the funky middle two
      for(int i = 0; i<radius/2; i++){
        while(current.up!=null){        // And not empty
          current = current.up;
          current2 = current2.up;
          u--;                             // Move up
          d++;
          break;
        }
      }
    }
    if((square == 3)||(square == 5)||(square == 8)){// If it is the last col
      for(int i = 0; i<radius-1; i++){
        while(current.right!=null){        // And not empty
          ri++;                               // Move right
          break;
        }
      }
    }
    if((square == 6)||(square == 7)||(square == 8)){
      for(int i = 0; i<radius-1; i++){
        while(current.down!=null){        // find out how far the character can see above him
          d++;
          break;
        }
      }
    }
    if((square == 2)||(square == 7)){            // If it is the middle top or bottom
      for(int i = 0; i<radius/2; i++){
        while(current.left!=null){        // And not empty
          current = current.left;
          current2 = current2.left;
          l--;                             // Move left
          ri++;
          break;
        }
      }
    }
    for(int i = u; i<d+1; i++){       // go through the grid pieces in the rows that the character can see
      for(int j = l; j<ri+1; j++){    // go through the grid pieces in the columns that the character can see
        if (!current.data.equals("")){    // if there is something there
          if(current.data.equals(target)){ // If it is an enemy
            if(al.get(0)=="")         // if it is the first thing being put in the array list
              al.remove(0);             // make the array list empty
            al.add(current.data);         // add the thing
          }
        }
        if(j==ri+radius)                     // break when you get to the end
          break;
        current = current.right;      // move right to next line
      }
      if (i == d+radius)                     // break when you get to the end
        break;
      current = current.down;         // move down to next link
      current2 = current2.down;       // move down to next link
      current = current2;             // move back to the left
    }
    return al;                        // return those coordinates
  }
  // -------------------------------------------------------------
  public void clearCell(int r, int c){// clear one cell
    Grid current = head;              // start at beginning
    for(int i = 0; i<r; i++){
      current = current.down;         // move down to next link
    }
    for(int i = 0; i<c; i++){
      current = current.right;        // move right to next link
    }
    current.data = "";              // clear the grid piece
  }
// -------------------------------------------------------------
  public void fill(int r1, int c1, int r2, int c2, String s){// fill block of the grid w/given string
    Grid current = head;              // start at beginning
    Grid current2 = head;             // start at beginning   
    for(int i = 0; i<r1; i++){
      current = current.down;         // move down to next link
      current2 = current2.down;       // move down to next link
    }
    for(int i = 0; i<c1; i++){
      current = current.right;        // move right to next link
      current2 = current2.right;      // move right to next link
    }
    int ctemp = c1;                   // make a place holder for the cols
    int rtemp = r1;                   // make a place holder for the rows
    while((rtemp<r2)||(rtemp ==r2)){  // iterate through the rows
      while((ctemp<c2)||(ctemp ==c2)){// iterate through the cols
        current.data = s;             // change the value
        current.x=ctemp;
        current.y=rtemp;
        if(ctemp<c2){
          current = current.right;    // move right to next link
        }
        ctemp++;                      // increase the place holder for the cols
      }
      if(rtemp<r2){
        current2 = current2.down;     // move down to next link
      }
      current=current2;               // jump back to the first node in the row
      rtemp++;                        // increase the place holder for the rows
      ctemp = c1;                     // reset the cols place holder
    }
  }
  
// -------------------------------------------------------------
  public void clearManyCells(int r1, int c1, int r2, int c2){// clear a block of the grid
    Grid current = head;              // start at beginning
    Grid current2 = head;             // start at beginning   
    for(int i = 0; i<r1; i++){
      current = current.down;         // move down to next link
      current2 = current2.down;       // move down to next link
    }
    for(int i = 0; i<c1; i++){
      current = current.right;        // move right to next link
      current2 = current2.right;      // move right to next link
    }
    int ctemp = c1;                   // make a place holder for the cols
    int rtemp = r1;                   // make a place holder for the rows
    while((rtemp<r2)||(rtemp ==r2)){  // iterate through the rows
      while((ctemp<c2)||(ctemp ==c2)){// iterate through the cols
        current.data = "";          // clear the value
        if(ctemp<c2){
          current = current.right;    // move right to next link
        }
        ctemp++;                      // increase the place holder for the cols
      }
      if(rtemp<r2){
        current2 = current2.down;     // move down to next link
      }
      current = current2;             // jump back to the first node in the row
      rtemp++;                        // increase the place holder for the rows
      ctemp = c1;                     // reset the cols place holder
    }
  }
// -------------------------------------------------------------
  public void display(){// show the floor with coordinates
    System.out.print("     ");    // formatting
    for(int j=0; j< col; j++){
      System.out.print(String.format("%10s", "col "+Integer.toString(j)));// print out the column headers
    }
    System.out.println();
    Grid current = head;              // start at beginning
    Grid current2 = head;             // start at the begining
    for(int j = 0; j<row;j++){        // print out the row header
      System.out.print("row " + Integer.toString(j));
      for(int i = 0; i<col; i++){
        //System.out.print(" ");        // formatting
        if(!current.data.equals(""))                  // if there is nothing in the grid
          System.out.print(String.format("%10s", current.data));// treat it like an empty string
        else{System.out.print(String.format("%10s", " "));
        }
        current = current.right;      // move to next link
      }
      current = current2.down;        // move down to next link
      current2= current2.down;        // move down to next link
      
      System.out.println("");         // formatting
    }
    System.out.println("");           // formatting
  }
// -------------------------------------------------------------
}  // end class Floor
////////////////////////////////////////////////////////////////
class DND{
  public static void main(String[] args){
    Floor hydra = new Floor(15, 15 );
    hydra.assignCell(5, 0, "C");
    hydra.assignCell(6, 0, "R");
    hydra.assignCell(5, 1, "D");
    hydra.assignCell(6, 1, "S");
    hydra.assignCell(7, 1, "P");
    hydra.assignCell(10, 10, "H");
    hydra.assignCell(10, 11, "H");
    hydra.assignCell(10, 12, "H");
    hydra.assignCell(11, 10, "H");
    hydra.assignCell(11, 11, "H");
    hydra.assignCell(11, 12, "H");
    hydra.assignCell(12, 10, "H");
    hydra.assignCell(12, 11, "H");
    hydra.assignCell(12, 12, "H");
    hydra.fill(1, 8, 6, 14, "W");
    hydra.fill(11, 3, 14, 6, "W");
    hydra.fill(0, 1, 2, 5, "W");
    
    hydra.display();
  /*  Floor hieroglyphics = new Floor(10, 10);
    hieroglyphics.assignCell(0, 0, "Char 1");
    hieroglyphics.assignCell(0, 1, "Char 2");
    hieroglyphics.assignCell(0, 2, "Char 3");
    hieroglyphics.assignCell(0, 3, "Char 4");
    hieroglyphics.assignCell(0, 4, "Char 5");
    hieroglyphics.assignCell(9, 0, "hieroglyphics");
    hieroglyphics.assignCell(9, 1, "hieroglyphics");
    hieroglyphics.assignCell(9, 2, "hieroglyphics");
    hieroglyphics.assignCell(9, 3, "hieroglyphics");
    hieroglyphics.assignCell(9, 4, "hieroglyphics");
    hieroglyphics.assignCell(9, 5, "hieroglyphics");
    hieroglyphics.assignCell(9, 6, "hieroglyphics");
    hieroglyphics.assignCell(9, 7, "hieroglyphics");
    hieroglyphics.assignCell(9, 8, "hieroglyphics");
    hieroglyphics.assignCell(9, 9, "hieroglyphics");
    hieroglyphics.display();
    Floor crypt = new Floor(10, 10);
    crypt.assignCell(0, 0, "Char 1");
    crypt.assignCell(0, 1, "Char 2");
    crypt.assignCell(0, 2, "Char 3");
    crypt.assignCell(0, 3, "Char 4");
    crypt.assignCell(0, 4, "Char 5");
    for(int i= 1; i<crypt.row;i=i+2){
      for(int j= 0; j<crypt.col;j++){
      crypt.assignCell(i, j, "Nothing");
      }
    }
    crypt.display();
    Floor joker = new Floor(10, 10);
    joker.assignCell(0, 0, "Char 1");
    joker.assignCell(0, 1, "Char 2");
    joker.assignCell(0, 2, "Char 3");
    joker.assignCell(0, 3, "Char 4");
    joker.assignCell(0, 4, "Char 5");
    for(int i= 1; i<joker.row;i++){
      for(int j= 0; j<joker.col;j++){
        joker.assignCell(i, j, "Fog");
      }
    }
    joker.assignCell(5, 5, "Joker");
    joker.display();
    Floor shadow = new Floor(20,5);
    shadow.assignCell(0, 0, "Char 1");
    shadow.assignCell(0, 1, "Char 2");
    shadow.assignCell(0, 2, "Char 3");
    shadow.assignCell(0, 3, "Char 4");
    shadow.assignCell(0, 4, "Char 5");
    shadow.assignCell(5, 0, "Shadow");
    shadow.assignCell(5, 1, "Shadow");
    shadow.assignCell(5, 2, "Shadow");
    shadow.assignCell(5, 3, "Shadow");
    shadow.assignCell(5, 4, "Shadow");
    shadow.display();
 */ }  // end main()
}  // end class Floor
////////////////////////////////////////////////////////////////