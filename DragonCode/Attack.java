// Basis for attacks
// Last edited 4-25-15

// Required params from heirarchy (default values)
/*  
    atWill = false;
    encounter = false;
    daily = false;
    recharge = false;
    rechargeValue = 0;
    priority = 0;
    defenseTarget = 1;
    help = false;
    ranged = false;
    maxRange = 0;
    attackBonus = 0;
    damageDice1 = 0;
    damageDice2 = 0;
    bonusDice = 0;
    bonusDamage = 0;
    bonusCrit = 0;
    hasCharges = false;
    maxCharges = 0;
    charges = 0;
    numHits = 1;
    used = false;
    burst = false;
    blast = false;
    attackPenalty = 0;
    damagePenalty = 0;
    */

public class Attack implements Comparable<Attack>{
  boolean atWill, encounter, daily, recharge; // if it is at will, encounter, recharge power, or daily
  int rechargeValue; // what needs to be rolled (or higher) to recharge attack (6 sided dice)
  int attackBonus, damageDice1, damageDice2, bonusDice, bonusDamage, bonusCrit; // damage stuff
  int charges; // max uses (per fight)?
  boolean hasCharges; // care about uses? ie lay on hands
  int maxCharges; // for resetting purposes for charges
  boolean ranged ; // character is ranged?
  int defenseTarget; // what defense does it target? 0 for heal, 1 for AC, 2 for fort, 3 for reflex, 4 for will
  int maxRange; // max range of attack
  int priority; // for sorting attacks
  boolean help; // help allies, or hurt enemies?
  int numHits; // how many times can we attack with this?
  boolean used; // relevant for encounter and dailies
  boolean burst; // attack is burst?
  boolean blast; // attack is blast?
  int radius; // how big is radius of attack? default to 1.
  int attackPenalty; // debilitated via attack
  int damagePenalty; // debilitated via damage
  String name; // name
  
  public Attack(int atkPenalty, int dmgPenalty){
    atWill = false;
    encounter = false;
    daily = false;
    recharge = false;
    rechargeValue = 0;
    priority = 0;
    defenseTarget = 1;
    help = false;
    ranged = false;
    maxRange = 0;
    attackBonus = 0;
    damageDice1 = 0;
    damageDice2 = 0;
    bonusDice = 0;
    bonusDamage = 0;
    bonusCrit = 0;
    hasCharges = false; // if true, care about charges. Mostly for heals.
    maxCharges = 0;
    charges = 0;
    numHits = 1;
    radius = 1;
    used = false;
    burst = false;
    blast = false;
    attackPenalty = -1*atkPenalty;
    damagePenalty = -1*dmgPenalty;
    name = "";
  }
  
  // baseline
    public Attack(){
    atWill = false;
    encounter = false;
    daily = false;
    recharge = false;
    rechargeValue = 0;
    priority = 0;
    defenseTarget = 1;
    help = false;
    ranged = false;
    maxRange = 0;
    attackBonus = 0;
    damageDice1 = 0;
    damageDice2 = 0;
    bonusDice = 0;
    bonusDamage = 0;
    bonusCrit = 0;
    hasCharges = false; // if true, care about charges. Mostly for heals.
    maxCharges = 0;
    charges = 0;
    numHits = 1;
    radius = 1;
    used = false;
    burst = false;
    blast = false;
    attackPenalty = 0;
    damagePenalty = 0;
    name = "";
  }
  
      public Attack(int atkPenalty, int dmgPenalty, boolean lock){
    atWill = false;
    encounter = false;
    daily = false;
    recharge = false;
    rechargeValue = 0;
    priority = 0;
    defenseTarget = 1;
    help = false;
    ranged = false;
    maxRange = 0;
    attackBonus = 0;
    damageDice1 = 0;
    damageDice2 = 0;
    bonusDice = 0;
    bonusDamage = 0;
    bonusCrit = 0;
    hasCharges = false; // if true, care about charges. Mostly for heals.
    maxCharges = 0;
    charges = 0;
    numHits = 1;
    radius = 1;
    used = lock; // only difference -- for encounters and dailies
    burst = false;
    blast = false;
    attackPenalty = -1*atkPenalty;
    damagePenalty = -1*dmgPenalty;
    name = "";
  }
  // accepts a character target, if it crits, returns 2. If hits, 1. If misses, 0.
  // where we call this, we would do a for loop from i = 1 to numHits
  public int attack(Character c){
    int roll = DiceRoll.randRoll();
    if (roll == 20){ // crit
      return 2;
    }
      
    int attackRoll = roll + attackBonus; // get our attack roll
    int defense; // initialize their defense
    
    switch (defenseTarget){ // find their corresponding defense value
      case 0:
        defense = 0; // always hits for whatever reason (heal, can't miss, etc)
        break;
      case 1:
        defense = c.ac;
        break;
      case 2:
        defense = c.fortitude;
        break;
      case 3:
        defense = c.reflex;
        break;
      case 4:
        defense = c.will;
        break;
      default:
        defense = c.ac;
        break;
    }
    
    if (attackRoll > defense || defense == 0){ // final check
      return 1; // hits
    }else{
      return 0;
    }
  }
  
  // if attack roll works and is noncrit, return damage
  public int damage(){
    return DiceRoll.randRoll(damageDice1) + DiceRoll.randRoll(damageDice2) + DiceRoll.randRoll(bonusDice) + bonusDamage;
  }
  
  // if it was a daily that missed, call this instead
  public int dailyMiss(){
    return (DiceRoll.randRoll(damageDice1) + DiceRoll.randRoll(damageDice2) + DiceRoll.randRoll(bonusDice) + bonusDamage)/2;
  }
  // if attack roll crits, return max damage
  public int critDamage(){
    return damageDice1 + damageDice2 + bonusDice + bonusDamage + bonusCrit;
  }
  
  public int compareTo(Attack a1){
    return(priority - a1.priority);
  }
}