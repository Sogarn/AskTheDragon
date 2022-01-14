// Example of a player
// Last edited 4-25-15

// attributes from character
/*
 maxHP = 0;
 hp = maxHP;
 ac = 0;
 defaultAC = ac;
 fortitude = 0;
 defaultFortitude = fortitude;
 reflex = 0;
 defaultReflex = reflex;
 will = 0;
 defaultWill = will;
 tempHP = 0;
 initiative = 0;
 defaultInitiative = initiative;
 speed = 0;
 perception = 0;
 passivePerception = perception + 10;
 bloodied = false;
 bloodiedCheck = (float)Math.floor(maxHP / 4); // this doesn't really need to be here but eh
 alive = true;
 */

// attributes from player
/*
 athletics = 0;
 acrobatics = 0;
 diplomacy = 0;
 endurance = 0;
 heal = 0;
 deathRoll = 0;
 surgeCount = 0;
 surgeValue = 0;
 secondWind = false;
 identifier = 1;
 */
import java.util.*;

public class Ranger extends Player{
// every attribute should have already been implemented, now it's just changing values
  
  public Ranger(String identity){
    classID = "R";
    maxHP = 47;
    hp = maxHP;
    ac = 21;
    defaultAC = ac;
    fortitude = 16;
    defaultFortitude = fortitude;
    reflex = 19;
    defaultReflex = reflex;
    will = 16;
    defaultWill = will;
    initiative = 9;
    defaultInitiative = initiative;
    speed = 7;
    perception = 12;
    passivePerception = perception + 10;
    bloodied = false;
    bloodiedValue = (float) Math.floor(maxHP / 2);
    name = identity;
    
    // second wave
    athletics = 6;
    acrobatics = 11;
    diplomacy = 2;
    endurance = 3;
    heal = 5;
    stealth = 11;
    surgeCount = 8;
    surgeValue = 12;
    
    secondWind = PlayerAttack.secondWind(surgeValue);
    abilitiesUpdate(0,0, locked);
    
  }
  public void abilitiesUpdate(int atkPenalty, int dmgPenalty, boolean lock[]){
    // Create the rangedbasic attack from the RangerAttack class
    Attack twinStrike = RangerAttack.twinStrike(atkPenalty, dmgPenalty);
    Attack twoFangedStrike = RangerAttack.twoFangedStrike(atkPenalty, dmgPenalty, lock[2]);
    Attack shadowWaspStrike = RangerAttack.shadowWaspStrike(atkPenalty, dmgPenalty, lock[3]);
    Attack splitTheTree = RangerAttack.splitTheTree(atkPenalty, dmgPenalty, lock[4]);
    // someday sort the ability list with priority
    // add it to abilities list
    abilities = new LinkedList<Attack>(); 
    abilities.add(twinStrike);
    abilities.add(twoFangedStrike);
    abilities.add(shadowWaspStrike);
    abilities.add(splitTheTree);
    Collections.sort(abilities);
  }
}