// Dragon King
// Last edited 4-26-15

import java.util.*;

public class DragonKing extends Enemy{
  
  public DragonKing(String identity){
    classID = "DK";
    maxHP = 255;
    hp = maxHP;
    ac = 20;
    defaultAC = ac;
    fortitude = 18;
    defaultFortitude = fortitude;
    reflex = 16;
    defaultReflex = reflex;
    will = 16;
    defaultWill = will;
    tempHP = 0;
    initiative = 6;
    defaultInitiative = initiative;
    speed = 8;
    perception = 10;
    passivePerception = perception + 10;
    bloodied = false;
    bloodiedValue = (float)Math.floor(maxHP / 2);
    type = 3;
    name = identity;
    hasRecharge = true; // dragon breath
    
    abilitiesUpdate(0,0, locked);
  }
  
  public void abilitiesUpdate(int atkPenalty, int dmgPenalty, boolean lock[]){
    Attack meleeBasic = DragonKingAttack.meleeBasic(atkPenalty, dmgPenalty);
    Attack flameBreath = DragonKingAttack.flameBreath(atkPenalty, dmgPenalty, lock[1]);
    
    abilities = new LinkedList<Attack>(); 
    abilities.add(meleeBasic);
    abilities.add(flameBreath);
    Collections.sort(abilities);
  }
}