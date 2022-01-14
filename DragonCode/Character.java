// The baseline for all characters in the D&D Simulation
// Last edited 4-25-15

import java.util.*;

public class Character implements Comparable<Character>{
  int maxHP, hp, ac, fortitude, reflex, will; // defensive stuff
  int tempHP; // temporary health
  int defaultAC, defaultFortitude, defaultReflex, defaultWill; // original traits in case of change
  int initiative, speed; // misc battle stuff
  int defaultInitiative; // original trait
  int perception, passivePerception, stealth; // skills
  boolean bloodied; // whether or not they are below half health
  float bloodiedValue; // for future bloody comparison
  boolean alive; // dead
  LinkedList<Attack> abilities; // list of abilities;
  int identifier; // ally or enemy
  int regen; // amount healed at start of each turn
  String name; // name of object
  boolean debuffed; // whether debilitated should be checked or not. Should be incremented by 2 by an attack
  int debilitated; // +1 if hit by an effect that ends a turn later, -1 each turn; if negative, set debuffed = false and reset stats
  int atkPenalty; // current attack penalty
  int dmgPenalty; // current damage penalty
  String classID; // refers to the type ie Paladin
  boolean[] locked; // meta tracking of encounter and ability usage
  boolean hasRecharge; // character has an ability that recharges
  
  public Character(){
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
    bloodiedValue = (float)Math.floor(maxHP / 2); // this doesn't really need to be here but eh
    alive = true;
    identifier = 0;
    regen = 0;
    debilitated = 0;
    atkPenalty = 0;
    dmgPenalty = 0;
    debuffed = false;
    classID = "";
    locked = new boolean[10]; // default to false
    hasRecharge = false;
  }
  
  // for sorting purposes
  public int compareTo(Character c1){
    return (c1.initiative - initiative);
  }
  
  // returns true if target is an enemy, false if ally.
  public boolean opponent(Character c1){
    if (c1.identifier != identifier){
      return true;
    }
    return false;
  }
  
  // check if bloodied
  public void bloodied(){
    if ((hp <= bloodiedValue) && (hp > 0)){
      bloodied = true;
    }else{
      bloodied = false;
    }
  }
  // checks/updates alive status
  public void aliveCheck(){
    if (hp <= 0){
      alive = false;
    }
  }
  
  // to string
  public String toString(){
    return name;
  }
  
  // going to be overloaded later anyway, allows for more cool debuffs
  public void abilitiesUpdate(int atk, int dmg, boolean locked[]){
  }
  
  // called at start of character's turn
  public void newTurn(){
    if (missingHP() > regen){ // test for regen
      hp += regen;
    }else if(missingHP() <= regen){
      hp = maxHP;
    }
    
    if (hasRecharge){ // if they have a recharge ability
      for (int ill = 0; ill < abilities.size(); ill++){
        int roll = DiceRoll.randRoll(6);
        if (abilities.get(ill).recharge){ // if the ability can recharge
          if (roll >= abilities.get(ill).rechargeValue){ // try to recharge
            abilities.get(ill).used = false; // recharge ability
            locked[ill] = false;
          }
        }
      }
    }
    
    // if suffered debilitating effects, subtract 1 to debilitated, reset if applicable
    if (debuffed = true){
      abilitiesUpdate(atkPenalty, dmgPenalty, locked);
      debilitated -= 1;
    if (debilitated < 0){ // if it is time to reset stats (1 turn, 1->0, isn't enough, needs one full cycle 1->0->-1)
                            // or else their debuff will clear before their first turn with it
        ac = defaultAC;
        fortitude = defaultFortitude;
        reflex = defaultReflex;
        will = defaultWill;
        // You may be thinking "But Filipe, if we reset their initiative midfight, won't everything get crazy?"
        // No, because the list itself is sorted before the fight starts. Afterwards, initiative doesn't really matter.
        initiative = defaultInitiative;
        atkPenalty = 0;
        dmgPenalty = 0;
        abilitiesUpdate(atkPenalty, dmgPenalty, locked);
        debuffed = false;
      }
    }
  }
  
  // add to everyone's initiatives
  public void addInitiative(){
    initiative += DiceRoll.randRoll();
  }
  
  // displays abilities of a character object in their order
  public void displayAbilities(){
    for (int iky = 0; iky < abilities.size(); iky ++){
      System.out.println(abilities.get(iky).name);
    }
  }
  
  // returns how much hp is missing
  public int missingHP(){
    return (maxHP - hp);
  }
}