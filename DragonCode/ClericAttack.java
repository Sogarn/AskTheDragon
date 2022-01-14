// cleric attacks
// last updated 4-26-15

/* List:
 * Brand of the Sun: At will, melee, one ally gets -1 to debilitating
 *   Use if someone is debilitated and singing strike wouldn't be great
 * Singing Strike: At will, melee, target takes -2 penalty to attacks
 *   Use by default
 * Sun Burst: Encounter, melee, self and allies within 5 squares gain 5 temp hp
 *   Use when more than 1 ally missing hp
 * Hammering Wind: Encounter, melee, target loses their turn
 *   Use against bigger enemies
 * Moment of Glory: Daily, blast 5, hits targets lose their turn
 *   Use against bosses, 3+ enemies in the 5x5 block
 * Healing Word: Encounter, ranged 5, heals target
 *   Use if target missing 15+ hp or bloodied, prioritize bloodied targets
 */

public class ClericAttack extends Attack{
  
  public static Attack brandOfTheSun(int atkPenalty, int dmgPenalty){
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.priority = 0;
    atk.defenseTarget = 1;
    atk.attackBonus += 12;
    atk.damageDice1 = 10;
    atk.damageDice2 = 0;
    atk.bonusDice = 0;
    atk.bonusDamage += 7+2;
    atk.bonusCrit = 6;
    atk.name = "Brand of the Sun";
    
    return atk;
  }
  
  public static Attack singingStrike(int atkPenalty, int dmgPenalty){
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.priority = 1;
    atk.defenseTarget = 1;
    atk.attackBonus += 12;
    atk.damageDice1 = 10;
    atk.damageDice2 = 0;
    atk.bonusDice = 0;
    atk.bonusDamage += 7;
    atk.bonusCrit = 6;
    atk.name = "Singing Strike";
    
    return atk;
  }
  // encounter
    public static Attack sunBurst(int atkPenalty, int dmgPenalty, boolean lock){
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.encounter = true;
    atk.priority = 2;
    atk.defenseTarget = 1;
    atk.attackBonus += 12;
    atk.damageDice1 = 10;
    atk.damageDice2 = 0;
    atk.bonusDice = 0;
    atk.bonusDamage += 7+2;
    atk.bonusCrit = 6;
    atk.name = "Sun Burst";
    
    return atk;
  }
    
    public static Attack hammeringWind(int atkPenalty, int dmgPenalty, boolean lock){
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.encounter = true;
    atk.priority = 3;
    atk.defenseTarget = 1;
    atk.attackBonus += 12;
    atk.damageDice1 = 10;
    atk.damageDice2 = 0;
    atk.bonusDice = 0;
    atk.bonusDamage += 7+2;
    atk.bonusCrit = 6;
    atk.name = "Hammering Wind";
    
    return atk;
  }
    
    // daily melee
    public static Attack momentOfGlory(int atkPenalty, int dmgPenalty, boolean lock){
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.daily = true;
    atk.priority = 4;
    atk.defenseTarget = 4;
    atk.burst = true;
    atk.maxRange = 5;
    atk.attackBonus += 9;
    atk.damageDice1 = 0;
    atk.damageDice2 = 0;
    atk.bonusDice += 0;
    atk.bonusDamage = 0+2;
    atk.bonusCrit = 0;
    atk.name = "Moment of Glory";
    
    return atk;
  }
    // 2 charges per encounter, heals
    public static Attack healingWord(){
    Attack atk = new Attack();
    
    atk.encounter = true;
    atk.priority = 5;
    atk.defenseTarget = 0;
    atk.hasCharges = true;
    atk.help = true;
    atk.maxCharges = 2;
    atk.charges = 2;
    atk.ranged = true;
    atk.maxRange = 5;
    atk.attackBonus = 0;
    atk.damageDice1 = 0;
    atk.damageDice2 = 0;
    atk.bonusDice = 6;
    atk.bonusDamage = 12+5;
    atk.bonusCrit = 0;
    atk.name = "Healing Word";
    
    return atk;
  }
}