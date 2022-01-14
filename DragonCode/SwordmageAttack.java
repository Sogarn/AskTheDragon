// swordmage attacks
// last updated 4-25-15

/* List:
 * Booming Blade: At will, melee, does bonus damage if target moves away
 *   Damage already accounted for, default melee attack
 * Sword Burst: At will, burst 1
 *   Use if at least 2 targets within 1 space of swordmage
 * Sword of Sigils: Encounter, burst 1
 *   Use if at least 2 bigger targets within 1 space of swordmage
 * Dimensional Vortex: Encounter, ranged 10
 *   Use if a big enemy within 10 spaces
 * Dimensional Thunder: Daily, melee
 *   Use against boss
 */

public class SwordmageAttack extends Attack{
  
  // melee attack
  public static Attack boomingBlade(int atkPenalty, int dmgPenalty){
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.priority = 0;
    atk.defenseTarget = 1;
    atk.attackBonus += 12;
    atk.damageDice1 = 12;
    atk.damageDice2 = 8;
    atk.bonusDice = 6;
    atk.bonusDamage += 8+3;
    atk.bonusCrit = 6;
    atk.numHits = 1;
    atk.name = "Booming Blade";
    
    return atk;
  }
  
  // melee burst at will
  public static Attack swordBurst(int atkPenalty, int dmgPenalty){
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.priority = 1;
    atk.defenseTarget = 3;
    atk.burst = true;
    atk.attackBonus += 9;
    atk.damageDice1 = 6;
    atk.damageDice2 = 0;
    atk.bonusDice = 0;
    atk.bonusDamage += 8;
    atk.bonusCrit = 6;
    atk.numHits = 1;
    atk.name = "Sword Burst";
    
    return atk;
  }
  
  // melee burst encounter
  public static Attack swordOfSigils(int atkPenalty, int dmgPenalty, boolean lock){
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.encounter = true;
    atk.priority = 2;
    atk.defenseTarget = 1;
    atk.burst = true;
    atk.attackBonus += 12;
    atk.damageDice1 = 12;
    atk.damageDice2 = 0;
    atk.bonusDice = 0;
    atk.bonusDamage += 8+5;
    atk.bonusCrit = 6;
    atk.numHits = 1;
    atk.name = "Sword of Sigils";
    
    return atk;
  }
  // ranged 10 encounter
  public static Attack dimensionalVortex(int atkPenalty, int dmgPenalty, boolean lock){
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.encounter = true;
    atk.priority = 3;
    atk.defenseTarget = 4;
    atk.ranged = true;
    atk.maxRange = 10;
    atk.attackBonus += 12;
    atk.damageDice1 = 12;
    atk.damageDice2 = 0;
    atk.bonusDice = 0;
    atk.bonusDamage += 8+3;
    atk.bonusCrit = 6;
    atk.numHits = 1;
    atk.name = "Dimensional Vortex";
    
    return atk;
  }
  
  //melee daily, technically burst 1
  public static Attack dimensionalThunder(int atkPenalty, int dmgPenalty, boolean lock){
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.daily = true;
    atk.priority = 4;
    atk.defenseTarget = 2;
    atk.burst = true;
    atk.attackBonus += 12;
    atk.damageDice1 = 12;
    atk.damageDice2 = 12;
    atk.bonusDice = 0;
    atk.bonusDamage += 8+5;
    atk.bonusCrit = 6;
    atk.numHits = 1;
    atk.name = "Dimensional Thunder";
    
    return atk;
  }
}