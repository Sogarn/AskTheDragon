// barb attacks

/* List:
 * Howling Strike: At will, melee, default melee attack
 * Avalanche Strike: Encounter, melee
 *   Use against bigger enemies
 * Daring Charge: Encounter, melee
 *   Use against bigger enemies
 * Swift Panther Rage: Daily, melee
 *   Use against bosses, massive damage
 */
public class BarbarianAttack extends Attack{
  
  // melee at will
  public static Attack howlingStrike(int atkPenalty, int dmgPenalty){
    
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.priority = 0;
    atk.defenseTarget = 1;
    atk.attackBonus += 11+1;
    atk.damageDice1 = 12;
    atk.damageDice2 = 0;
    atk.bonusDice = 6;
    atk.bonusDamage += 6;
    atk.bonusCrit = 6;
    atk.numHits = 1;
    atk.name = "Howling Strike";
    
    return atk;
  }
  
  // melee encounter
  public static Attack avalancheStrike(int atkPenalty, int dmgPenalty, boolean lock){
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.encounter = true;
    atk.priority = 1;
    atk.defenseTarget = 1;
    atk.attackBonus += 11+1;
    atk.damageDice1 = 12;
    atk.damageDice2 = 12;
    atk.bonusDice = 12;
    atk.bonusDamage += 9;
    atk.bonusCrit = 6;
    atk.numHits = 1;
    atk.name = "Avalanche Strike";
    
    return atk;
  }
  
  // melee encounter
  public static Attack daringCharge(int atkPenalty, int dmgPenalty, boolean lock){
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.encounter = true;
    atk.priority = 2;
    atk.defenseTarget = 1;
    atk.attackBonus += 11+1+2+2;
    atk.damageDice1 = 12;
    atk.damageDice2 = 12;
    atk.bonusDice = 0;
    atk.bonusDamage += 6+2+2;
    atk.bonusCrit = 6;
    atk.numHits = 1;
    atk.name = "Daring Charge";
    
    return atk;
  }
  
  // melee daily
  public static Attack swiftPantherRage(int atkPenalty, int dmgPenalty, boolean lock){
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.daily = true;
    atk.priority = 3;
    atk.defenseTarget = 1;
    atk.attackBonus += 11+1;
    atk.damageDice1 = 12;
    atk.damageDice2 = 12;
    atk.bonusDice = 12;
    atk.bonusDamage += 6;
    atk.bonusCrit = 6;
    atk.numHits = 1;
    atk.name = "Swift Panther Rage";
    
    return atk;
  }
}