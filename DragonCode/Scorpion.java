// mega scorpion in wasteland near castle garden
// last updated 5-2-14

import java.util.*;
public class Scorpion extends Enemy{
  
  public Scorpion(String identity){
    classID = "CM";
    maxHP = 220;
    hp = maxHP;
    ac = 20;
    defaultAC = ac;
    fortitude = 17;
    defaultFortitude = fortitude;
    reflex = 15;
    defaultReflex = reflex;
    will = 16;
    defaultWill = will;
    tempHP = 0;
    initiative = 5;
    defaultInitiative = initiative;
    speed = 7;
    perception = 2;
    passivePerception = perception + 10;
    bloodied = false;
    bloodiedValue = (float)Math.floor(maxHP / 2);
    type = 3;
    name = identity;
    
    abilitiesUpdate(0,0,locked);
  }
  public void abilitiesUpdate(int atkPenalty, int dmgPenalty, boolean lock[]){
    Attack pincer = ScorpionAttack.pincer(atkPenalty, dmgPenalty);
    abilities = new LinkedList<Attack>(); 
    abilities.add(pincer);
  }
}