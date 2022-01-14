// Barbarian
// Last edited 4-25-15

import java.util.*;

public class Barbarian extends Player{
  
  public Barbarian(String identity){
    classID = "B";
    maxHP = 53;
    hp = maxHP;
    ac = 19;
    defaultAC = ac;
    fortitude = 20;
    defaultFortitude = fortitude;
    reflex = 16;
    defaultReflex = reflex;
    will = 15;
    defaultWill = will;
    initiative = 4;
    defaultInitiative = initiative;
    speed = 6;
    perception = 8;
    passivePerception = perception + 10;
    bloodied = false;
    bloodiedValue = (float) Math.floor(maxHP / 2);
    
    // second wave
    athletics = (int)(13*1.4); // to compensate for double rolls
    acrobatics = (int)(8*1.4);
    diplomacy = 3;
    endurance = 4;
    heal = 2;
    stealth = 3;
    surgeCount = 11;
    surgeValue = 13;
    name = identity;
    
    secondWind = PlayerAttack.secondWind(surgeValue);
    abilitiesUpdate(0,0,locked);
    
  }
  
  public void abilitiesUpdate(int atkPenalty, int dmgPenalty, boolean lock[]){
    // add attacks
    Attack howlingStrike = BarbarianAttack.howlingStrike(atkPenalty, dmgPenalty);
    Attack avalancheStrike = BarbarianAttack.avalancheStrike(atkPenalty, dmgPenalty, lock[1]);
    Attack daringCharge = BarbarianAttack.daringCharge(atkPenalty, dmgPenalty, lock[2]);
    Attack swiftPantherRage = BarbarianAttack.swiftPantherRage(atkPenalty, dmgPenalty, lock[3]);
    
    abilities = new LinkedList<Attack>(); 
    abilities.add(howlingStrike);
    abilities.add(avalancheStrike);
    abilities.add(daringCharge);
    abilities.add(swiftPantherRage);
    Collections.sort(abilities);
  }
}