// lurker (thorn forest)
// last updated 5-2-15

import java.util.*;
public class Spider extends Enemy{
  
  public Spider(String identity){
    classID = "M";
    maxHP = 40;
    hp = maxHP;
    ac = 18;
    defaultAC = ac;
    fortitude = 14;
    defaultFortitude = fortitude;
    reflex = 18;
    defaultReflex = reflex;
    will = 16;
    defaultWill = will;
    tempHP = 0;
    initiative = 10;
    defaultInitiative = initiative;
    speed = 8;
    perception = 9;
    passivePerception = perception + 10;
    bloodied = false;
    bloodiedValue = (float)Math.floor(maxHP / 2);
    type = 1;
    name = identity;
    
    abilitiesUpdate(0,0);
  }
  public void abilitiesUpdate(int atkPenalty, int dmgPenalty){
    Attack bite = SpiderAttack.bite(atkPenalty, dmgPenalty);
    abilities = new LinkedList<Attack>(); 
    abilities.add(bite);
  }
}