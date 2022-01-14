// all assassin attacks
// Last edited 4-25-15

/* List:
 * Executioner's Noose: At will, ranged 5
 *   Should be used if nothing is within melee range after moving
 * Shadow Storm: At will, melee, does +1 damage per adjacent entity, 
 *   Default melee attack, can skip bonus effect
 * Smothering Shadow: Encounter, melee
 *   Tons of damage, use against bigger enemies
 * Cloaking Mist: Encounter, blast 3, increase own defenses by 2 for rest of encounter
 *   Use if multiple targets can be hit
 * Targeted For Death: Daily, ranged 5
 *   Use against bosses, will do massive damage
 */

public class AssassinAttack extends Attack{
  
  // melee at will
  public static Attack shadowStorm(int atkPenalty, int dmgPenalty){
    
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.priority = 1;
    atk.defenseTarget = 1;
    atk.attackBonus += 12;
    atk.damageDice1 = 12;
    atk.damageDice2 = 0;
    atk.bonusDice = 8;
    atk.bonusDamage += 6+2;
    atk.bonusCrit = 6;
    atk.name = "Shadow Storm";
    
    return atk;
  }
  
  // ranged 5 at will
  public static Attack executionersNoose(int atkPenalty, int dmgPenalty){
    
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.priority = 0;
    atk.defenseTarget += 2;
    atk.ranged = true;
    atk.maxRange = 5;
    atk.attackBonus += 8;
    atk.damageDice1 = 6;
    atk.damageDice2 = 0;
    atk.bonusDice = 8;
    atk.bonusDamage += 6;
    atk.bonusCrit = 6;
    atk.name = "Executioner's Noose";
      
    return atk;
  }
  
  // melee encounter
  public static Attack smotheringShadow(int atkPenalty, int dmgPenalty, boolean lock){
    
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.encounter = true;
    atk.priority = 2;
    atk.defenseTarget = 1;
    atk.attackBonus += 12;
    atk.damageDice1 = 12;
    atk.damageDice2 = 12;
    atk.bonusDice = 8;
    atk.bonusDamage += 9+3;
    atk.bonusCrit = 6;
    atk.name = "Smothering Shadow";
    
    return atk;
  }
  
  // blast 3 encounter
  public static Attack cloakingMist(int atkPenalty, int dmgPenalty, boolean lock){
    
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.encounter = true;
    atk.priority = 3;
    atk.defenseTarget = 2;
    atk.maxRange = 3;
    atk.blast = true;
    atk.attackBonus += 8;
    atk.damageDice1 = 6;
    atk.damageDice2 = 6;
    atk.bonusDice = 8;
    atk.bonusDamage += 6;
    atk.bonusCrit = 6;
    atk.numHits = 2;
    atk.name = "Cloaking Mist";
    
    return atk;
  }
  
  // ranged 5 daily
  // this move is abstracted because coding double shrouds would be a crazy endeavor
  public static Attack targetedForDeath(int atkPenalty, int dmgPenalty, boolean lock){
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.daily = true;
    atk.priority = 4;
    atk.defenseTarget = 4;
    atk.help = false;
    atk.ranged = true;
    atk.maxRange = 5;
    atk.attackBonus += 8;
    atk.damageDice1 = 8;
    atk.damageDice2 = 8;
    atk.bonusDice = 8;
    atk.bonusDamage += 6+8+8+8+8;
    atk.bonusCrit = 6;
    atk.numHits = 1;
    atk.name = "Targeted For Death";
    
    return atk;
  }
}