// Guard
// Last updated 5-2-15

import java.util.*;
public class Guard extends Enemy{
  
  public Guard(String identity){
    classID = "U";
    maxHP = 56;
    hp = maxHP;
    ac = 20;
    defaultAC = ac;
    fortitude = 18;
    defaultFortitude = fortitude;
    reflex = 16;
    defaultReflex = reflex;
    will = 15;
    defaultWill = will;
    tempHP = 0;
    initiative = 6;
    defaultInitiative = initiative;
    speed = 5;
    perception = 3;
    passivePerception = perception + 10;
    bloodied = false;
    bloodiedValue = (float)Math.floor(maxHP / 2);
    type = 1;
    name = identity;
    
    abilitiesUpdate(0,0, locked);
  }
  public void abilitiesUpdate(int atkPenalty, int dmgPenalty, boolean lock[]){
    Attack meleeBasic = GuardAttack.meleeBasic(atkPenalty, dmgPenalty);
    abilities = new LinkedList<Attack>(); 
    abilities.add(meleeBasic);
  }
}