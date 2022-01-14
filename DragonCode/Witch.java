// Witch
// Last updated 4-25-15

// normal
import java.util.*;
public class Witch extends Enemy{
  
  public Witch(String identity){
    classID = "WH";
    maxHP = 68;
    hp = maxHP;
    ac = 18;
    defaultAC = ac;
    fortitude = 15;
    defaultFortitude = fortitude;
    reflex = 13;
    defaultReflex = reflex;
    will = 18;
    defaultWill = will;
    tempHP = 0;
    initiative = 3;
    defaultInitiative = initiative;
    speed = 6;
    perception = 8;
    passivePerception = perception + 10;
    bloodied = false;
    bloodiedValue = (float)Math.floor(maxHP / 2);
    type = 1;
    name = identity;
    
    abilitiesUpdate(0,0,locked);
  }
  public void abilitiesUpdate(int atkPenalty, int dmgPenalty, boolean lock[]){
    // abilities
    Attack debilitatingRay = WitchAttack.debilitatingRay(atkPenalty, dmgPenalty);
    abilities = new LinkedList<Attack>(); 
    abilities.add(debilitatingRay);
  }
}