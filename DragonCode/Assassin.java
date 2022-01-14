// Assassin
// Last edited 4-25-15

import java.util.*;
public class Assassin extends Player{
// every attribute should have already been implemented, now it's just changing values
  
  public Assassin(String identity){
    classID = "A";
    maxHP = 42;
    hp = maxHP;
    ac = 22;
    defaultAC = ac;
    fortitude = 18;
    defaultFortitude = fortitude;
    reflex = 19;
    defaultReflex = reflex;
    will = 15;
    defaultWill = will;
    initiative = 7;
    defaultInitiative = initiative;
    speed = 6;
    perception = 8;
    passivePerception = perception + 10;
    bloodied = false;
    bloodiedValue = (float) Math.floor(maxHP / 2);
    
    // second wave
    athletics = 7;
    acrobatics = 11;
    diplomacy = 2;
    endurance = 9;
    heal = 2;
    stealth = 11;
    surgeCount = 9;
    surgeValue = 11;
    name = identity;
    
    secondWind = PlayerAttack.secondWind(surgeValue);
    abilitiesUpdate(0,0, locked);
  }
  
  public void abilitiesUpdate(int atkPenalty, int dmgPenalty, boolean lock[]){
    // Creates assassin attacks
    Attack executionersNoose = AssassinAttack.executionersNoose(atkPenalty, dmgPenalty);
    Attack shadowStorm = AssassinAttack.shadowStorm(atkPenalty, dmgPenalty);
    Attack cloakingMist = AssassinAttack.cloakingMist(atkPenalty, dmgPenalty, lock[3]);
    Attack smotheringShadow = AssassinAttack.smotheringShadow(atkPenalty, dmgPenalty, lock[2]);
    Attack targetedForDeath = AssassinAttack.targetedForDeath(atkPenalty, dmgPenalty, lock[4]);
    
    abilities = new LinkedList<Attack>(); 
    // add it to abilities list
    abilities.add(executionersNoose);
    abilities.add(shadowStorm);
    abilities.add(cloakingMist);
    abilities.add(smotheringShadow);
    abilities.add(targetedForDeath);
    
    Collections.sort(abilities);
  }
  
}