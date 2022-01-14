// wizard attacks
// last edited 4-25-15

/* List:
 * Magic Missile: At will, ranged 20, always hits
 *   Use if there is a non-minion enemy within 20 spaces that has 8 or less HP.
 * Freezing Burst: At will, ranged 10, area burst 1
 *   Use if more than 1 enemy within a 3x3 block of the grid within 10 spaces
 * Arc Lightning: At will, ranged 20
 *   Use by default
 * Burning Hands: Encounter, blast 5
 *   Use if more than 1 enemy within a 5x5 block adjacent to the wizard
 * Force Orb: Encounter, ranged 20
 *   Use if a big enemy within 20 squares
 * Fire Shroud: Encounter, burst 3
 *   Use if at least 2 enemies within 3 squares
 * Shock Sphere: Encounter, ranged 10, area burst 2
 *   Use if at least 2 bigger enemies within a 5x5 block within 10 spaces
 * Flaming Sphere: Daily, ranged 10
 *   Use if a boss within 10 spaces
 * Fountain of Flame: Daily, ranged 10, area burst 1
 *   Use if a boss and 1 more enemy within a 3x3 block within 10 spaces
 */
public class WizardAttack extends Attack{
  
  // ranged 20 at will, can't miss
  public static Attack magicMissile(int atkPenalty, int dmgPenalty){
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.priority = 8;
    atk.defenseTarget = 0;
    atk.ranged = true;
    atk.maxRange = 20;
    atk.attackBonus = 0;
    atk.damageDice1 = 0;
    atk.damageDice2 = 0;
    atk.bonusDice = 0;
    atk.bonusDamage += 8;
    atk.bonusCrit = 0;
    atk.name = "Magic Missile";
    
    return atk;
  }
  
  // ranged 20 at will
  public static Attack arcLightning(int atkPenalty, int dmgPenalty){
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.priority = 0;
    atk.defenseTarget = 3;
    atk.ranged = true;
    atk.maxRange = 20;
    atk.attackBonus += 9+1;
    atk.damageDice1 = 6;
    atk.damageDice2 = 0;
    atk.bonusDice = 0;
    atk.bonusDamage += 6;
    atk.bonusCrit = 6;
    atk.name = "Arc Lightning";
    
    return atk;
  }
  
  // at will ranged 10 area burst 1
  public static Attack freezingBurst(int atkPenalty, int dmgPenalty){
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.priority = 1;
    atk.defenseTarget = 3;
    atk.ranged = true;
    atk.burst = true;
    atk.maxRange = 10;
    atk.attackBonus += 9+1;
    atk.damageDice1 = 6;
    atk.damageDice2 = 0;
    atk.bonusDice = 0;
    atk.bonusDamage += 6;
    atk.bonusCrit = 6;
    atk.radius = 1;
    atk.name = "Freezing Burst";
    
    return atk;
  }
  
  // ranged blast 5 encounter
  public static Attack burningHands(int atkPenalty, int dmgPenalty, boolean lock){
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.encounter = true;
    atk.priority = 2;
    atk.defenseTarget = 3;
    atk.blast = true;
    atk.maxRange = 5;
    atk.attackBonus += 10+1;
    atk.damageDice1 = 6;
    atk.damageDice2 = 6;
    atk.bonusDice = 0;
    atk.bonusDamage += 8;
    atk.bonusCrit = 6;
    atk.numHits = 3; // approximate blast 5
    atk.name = "Burning Hands";
    
    return atk;
  }
  
  // ranged 20 encounter
  public static Attack forceOrb(int atkPenalty, int dmgPenalty, boolean lock){
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.encounter = true;
    atk.priority = 3;
    atk.defenseTarget = 3;
    atk.ranged = true;
    atk.maxRange = 20;
    atk.attackBonus += 9+1;
    atk.damageDice1 = 8;
    atk.damageDice2 = 8;
    atk.bonusDice = 10;
    atk.bonusDamage += 8+6;
    atk.bonusCrit = 6;
    atk.name = "Force Orb";
    
    return atk;
  }
  
  // ranged burst 3 encounter
  public static Attack fireShroud(int atkPenalty, int dmgPenalty, boolean lock){
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.encounter = true;
    atk.priority = 4;
    atk.defenseTarget = 2;
    atk.ranged = false;
    atk.burst = true;
    atk.maxRange = 3;
    atk.attackBonus += 10+1;
    atk.damageDice1 = 8;
    atk.damageDice2 = 0;
    atk.bonusDice = 0;
    atk.bonusDamage += 8;
    atk.bonusCrit = 6;
    atk.numHits = 2; // approximate burst 3
    atk.name = "Fire Shroud";
    
    return atk;
  }
  
  // ranged 10 area burst 2 encounter
  public static Attack shockSphere(int atkPenalty, int dmgPenalty, boolean lock){
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.encounter = true;
    atk.priority = 5;
    atk.defenseTarget = 3;
    atk.ranged = true;
    atk.burst = true;
    atk.maxRange = 10;
    atk.attackBonus += 9+1;
    atk.damageDice1 = 6;
    atk.damageDice2 = 6;
    atk.bonusDice = 0;
    atk.bonusDamage += 6;
    atk.bonusCrit = 6;
    atk.radius = 2;
    atk.numHits = 2; // approximate burst 2
    atk.name = "Shock Sphere";
    
    return atk;
  }
  
  // ranged 10 daily
    public static Attack flamingSphere(int atkPenalty, int dmgPenalty, boolean lock){
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.daily = true;
    atk.priority = 6;
    atk.defenseTarget = 3;
    atk.ranged = true;
    atk.maxRange = 10;
    atk.attackBonus += 10+1;
    atk.damageDice1 = 6;
    atk.damageDice2 = 6;
    atk.bonusDice = 0;
    atk.bonusDamage += 8;
    atk.bonusCrit = 6;
    atk.name = "Flaming Sphere";
    
    return atk;
    }
    
    // ranged 10 daily area burst 1
    public static Attack fountainOfFlame(int atkPenalty, int dmgPenalty, boolean lock){
      Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
      
      atk.daily = true;
      atk.priority = 7;
      atk.defenseTarget = 3;
      atk.ranged = true;
      atk.burst = true;
      atk.maxRange = 10;
      atk.attackBonus += 10+1;
      atk.damageDice1 = 8;
      atk.damageDice2 = 8;
      atk.bonusDice = 8;
      atk.bonusDamage += 8;
      atk.bonusCrit = 6;
      atk.numHits = 2; // approximates its bonus damage by hitting twice
      atk.radius = 1;
      atk.name = "Fountain of Flame";
      
      return atk;
    }
}