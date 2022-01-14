// Gives random dice roll
// Last edited 4-13-15

import java.util.*;

public class DiceRoll {
  
  // default, no params = 20 sided dice
  public static int randRoll(){
    return randRoll(20);
  }
  
  public static int randRoll(int dice){
    // check that the dice is nonzero, non-negative
    if (dice > 0){
      Random rng = new Random();
      // By default, nextInt gets [0,dice-1] , adding one shifts the interval to [1,dice]
      int randomRoll = rng.nextInt(dice) + 1;
      return randomRoll;
    }else
      return 0;
  }
}