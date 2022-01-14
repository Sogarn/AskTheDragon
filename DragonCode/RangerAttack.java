// ranger attacks
// last edited 4-25-15

/* List
 * Twin Strike: At will, ranged 20, attacks twice
 *   Use by default
 * Two-fanged Strike: Encounter, ranged 20, attacks twice
 *   Use against bigger enemies
 * Shadow Wasp Strike: Encounter, ranged 20
 *   Use against bigger enemies, one big hit
 * Split the Tree: Daily, ranged 20, attacks twice
 *   Use against bosses
 */
public class RangerAttack extends Attack{
  
  // I choose to include especially important unchanged params, like defenseTarget and priority
  // even though their values are the same, just for confirmation that they are considered
  
  // ranged 20 double attack
  public static Attack twinStrike(int atkPenalty, int dmgPenalty){
    
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.priority = 0;
    atk.defenseTarget = 1;
    atk.ranged = true;
    atk.maxRange = 20;
    atk.attackBonus += 11+1;
    atk.damageDice1 = 12;
    atk.damageDice2 = 0;
    atk.bonusDice = 8;
    atk.bonusDamage += 1+1;
    atk.bonusCrit = 6;
    atk.numHits = 2;
    atk.name = "Twin Strike";
    
    return atk;
  }
  
  // ranged 20 encounter double attack
  public static Attack twoFangedStrike(int atkPenalty, int dmgPenalty, boolean lock){
    
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.encounter = true;
    atk.priority = 1;
    atk.defenseTarget = 1;
    atk.ranged = true;
    atk.maxRange = 20;
    atk.attackBonus += 11+1;
    atk.damageDice1 = 12;
    atk.damageDice2 = 0;
    atk.bonusDice = 8;
    atk.bonusDamage += 6+1+3;
    atk.bonusCrit = 6;
    atk.numHits = 2;
    atk.name = "Two Fanged Strike";
    
    return atk;
  }
  
  public static Attack shadowWaspStrike(int atkPenalty, int dmgPenalty, boolean lock){
    
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.encounter = true;
    atk.priority = 2;
    atk.defenseTarget = 1;
    atk.ranged = true;
    atk.maxRange = 20;
    atk.attackBonus += 11+1;
    atk.damageDice1 = 12;
    atk.damageDice2 = 12;
    atk.bonusDice = 8;
    atk.bonusDamage += 6+1;
    atk.bonusCrit = 6;
    atk.numHits = 1;
    atk.name = "Shadow Wasp Strike";
    
    return atk;
  }
  // ranged 20 daily double attack
  public static Attack splitTheTree(int atkPenalty, int dmgPenalty, boolean lock){
    
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.daily = true;
    atk.priority = 3;
    atk.defenseTarget = 1;
    atk.ranged = true;
    atk.maxRange = 20;
    atk.attackBonus += 11+1;
    atk.damageDice1 = 12;
    atk.damageDice2 = 12;
    atk.bonusDice = 8;
    atk.bonusDamage += 6+1;
    atk.bonusCrit = 6;
    atk.numHits = 2;
    atk.name = "Split The Tree";
    
    return atk;
  }
}