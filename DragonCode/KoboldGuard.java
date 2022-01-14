// Ranged guard
// Last updated 5-2-15

import java.util.*;
public class KoboldGuard extends Enemy{
  
  public KoboldGuard(String identity){
    classID = "K";
    maxHP = 44;
    hp = maxHP;
    ac = 16;
    defaultAC = ac;
    fortitude = 15;
    defaultFortitude = fortitude;
    reflex = 17;
    defaultReflex = reflex;
    will = 15;
    defaultWill = will;
    tempHP = 0;
    initiative = 6;
    defaultInitiative = initiative;
    speed = 6;
    perception = 4;
    passivePerception = perception + 10;
    bloodied = false;
    bloodiedValue = (float)Math.floor(maxHP / 2);
    type = 1;
    name = identity;
    
    abilitiesUpdate(0,0, locked);
  }
  public void abilitiesUpdate(int atkPenalty, int dmgPenalty, boolean lock[]){
    Attack rangedBasic = KoboldGuardAttack.rangedBasic(atkPenalty, dmgPenalty);
    abilities = new LinkedList<Attack>(); 
    abilities.add(rangedBasic);
  }
}