// Ardent attacks
// last edit 4-25-15

/* List:
 * Demoralizing Strike: At will, melee, Target takes -2 penalty to all defenses, 
 *   should be used as default at will if nobody is missing health
 * Energizing Strike: At will, melee, one ally within 5 squares gains 7 temp hp
 *   should be used if an ally is bloodied within 5 squares
 * Impatient Strike: At will, melee, one ally within 2 squares gains +2 damage till we reset stats
 *   very niche, demoralizing strike should be used instead
 * Ardent Surge: Encounter, burst 5, 2 charges, 1 target, heals target
 *   should be used if target is missing >= 18 hp, prioritize bloodied allies
 * Implanted Suggestion: Daily, melee, tons of damage, enemy loses his turn
 *   use for tons of damage or against bosses
 */

public class ArdentAttack extends Attack{
  // melee at will
  public static Attack demoralizingStrike(int atkPenalty, int dmgPenalty){
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.priority = 0;
    atk.defenseTarget = 1;
    atk.attackBonus += 12;
    atk.damageDice1 = 12;
    atk.damageDice2 = 0;
    atk.bonusDice = 0;
    atk.bonusDamage += 6;
    atk.bonusCrit = 6;
    atk.name = "Demoralizing Strike";
    
    return atk;
  }
  
  // melee at will
  public static Attack energizingStrike(int atkPenalty, int dmgPenalty){
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.priority = 1;
    atk.defenseTarget = 1;
    atk.attackBonus += 12;
    atk.damageDice1 = 12;
    atk.damageDice2 = 0;
    atk.bonusDice = 0;
    atk.bonusDamage += 6;
    atk.bonusCrit = 6;
    atk.name = "Energizing Strike";
    
    return atk;
  }
  
  // melee at will
  public static Attack impatientStrike(int atkPenalty, int dmgPenalty){
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.priority = 2;
    atk.defenseTarget = 1;
    atk.attackBonus += 12;
    atk.damageDice1 = 12;
    atk.damageDice2 = 0;
    atk.bonusDice = 0;
    atk.bonusDamage += 6;
    atk.bonusCrit = 6;
    atk.name = "Impatient Strike";
    
    return atk;
  }
  // burst 5 heal single target
  public static Attack ardentSurge(){
    Attack atk = new Attack();
    
    atk.encounter = true;
    atk.priority = 4;
    atk.defenseTarget = 0;
    atk.maxCharges = 2;
    atk.charges = 2;
    atk.hasCharges = true;
    atk.help = true;
    atk.ranged = true;
    atk.maxRange = 5;
    atk.damageDice1 = 6;
    atk.bonusDamage = 12;
    atk.bonusCrit = 0;
    atk.name = "Ardent Surge";
    
    return atk;
  }
  // melee daily
  public static Attack implantedSuggestion(int atkPenalty, int dmgPenalty, boolean lock){
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.daily = true;
    atk.priority = 3;
    atk.defenseTarget = 1;
    atk.attackBonus += 12;
    atk.damageDice1 = 12;
    atk.damageDice2 = 12;
    atk.bonusDice = 12;
    atk.bonusDamage += 6+8;
    atk.bonusCrit = 6;
    atk.name = "Implanted Suggestion";
    
    return atk;
  }
}