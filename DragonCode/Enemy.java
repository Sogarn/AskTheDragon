// enemy class

public class Enemy extends Character{
  int type; // 0 is minion, 1 is regular/big enemy, 2 is elite, 3 is boss
  
  public Enemy(){
    identifier = 2;
    type = 0;
  }
  
  public Enemy(String identity){
    identifier = 2;
    type = 0;
    name = identity;
  }
  
}