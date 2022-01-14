// tiny scorpions
// last udpated 5-2-15

import java.util.*;
public class SmallScorp extends Enemy{
  
  public SmallScorp(String identity){
    classID = "cm";
    maxHP = 1;
    hp = maxHP;
    ac = 18;
    defaultAC = ac;
    fortitude = 14;
    defaultFortitude = fortitude;
    reflex = 12;
    defaultReflex = reflex;
    will = 13;
    defaultWill = will;
    tempHP = 0;
    initiative = 8;
    defaultInitiative = initiative;
    speed = 8;
    perception = 2;
    passivePerception = perception + 10;
    bloodied = false;
    bloodiedValue = (float)Math.floor(maxHP / 2);
    type = 0;
    name = identity;
    
    abilitiesUpdate(0,0, locked);
  }
  public void abilitiesUpdate(int atkPenalty, int dmgPenalty, boolean lock[]){
    Attack pincer = ScorpionAttack.pincer(atkPenalty, dmgPenalty);
    abilities = new LinkedList<Attack>(); 
    abilities.add(pincer);
  }
}