// basic idea found online

import java.util.*;
public class Combination {
  
  public static LinkedList<String> combination(Character[]  elements, int K){
    int N = elements.length;
    if(K > N){
      System.out.println("Invalid input, K > N");
      LinkedList<String> lList = new LinkedList<String>();
      return lList;
    }
    int combination[] = new int[K];
    int r = 0;      
    int index = 0;
    LinkedList<String> lList = new LinkedList<String>();
    StringBuilder sb = new StringBuilder();
    while(r >= 0){
      // possible indexes for 1st position "r=0" are "0,1,2" --> "A,B,C"
      // possible indexes for 2nd position "r=1" are "1,2,3" --> "B,C,D"
      
      // for r = 0 ==> index < (4+ (0 - 2)) = 2
      if(index <= (N + (r - K))){
        combination[r] = index;
        
        // if we are at the last position print and increase the index
        if(r == K-1){
          
          //do something with the combination e.g. add to list or print
          for(int i: combination){
            sb.append(i);
          }
          index++;
          String strI = sb.toString();
          lList.add(strI);
          sb = new StringBuilder();
        }
        else{
          // select index for next position
          index = combination[r]+1;
          r++;                                        
        }
      }
      else{
        r--;
        if(r > 0)
          index = combination[r]+1;
        else
          index = combination[0]+1;   
      }           
    }
    return lList;
  }
}