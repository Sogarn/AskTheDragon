// Paladin attacks
// Last edited 4-25-15

/* List
 * Virtuous Strike: At will, melee
 *   Use as default
 * Valorous Smite: Encounter, melee
 *   Use against stronger enemies, treat as burst 3
 * Righteous Smite: Encounter, melee, self and all allies within 5 squares gain 8 temp hp
 *   Use when at least 1 ally within 5 squares missing health
 * Lay on Hands: At will, melee, 3 charges, heals target
 *   Use when an ally missing at least 14 hp, prioritize bloodied targets
 * Majestic Halo: Daily, melee
 *   Use against bosses, big damage
 */

public class PaladinAttack extends Attack{
  
  // melee at will, technically doesn't have a basic melee
  public static Attack virtuousStrike(int atkPenalty, int dmgPenalty){
    
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.priority = 0;
    atk.defenseTarget = 1;
    atk.attackBonus += 12;
    atk.damageDice1 = 10;
    atk.damageDice2 = 0;
    atk.bonusDice = 0;
    atk.bonusDamage += 6;
    atk.bonusCrit = 6;
    atk.numHits = 1;
    atk.name = "Virtuous Strike";
    
    return atk;
  }
  
  // ranged burst 3 encounter
  public static Attack valorousSmite(int atkPenalty, int dmgPenalty, boolean lock){
    
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.encounter = true;
    atk.priority = 1;
    atk.defenseTarget = 1;
    atk.maxRange = 3;
    atk.burst = true;
    atk.attackBonus += 12;
    atk.damageDice1 = 10;
    atk.damageDice2 = 10;
    atk.bonusDice = 0;
    atk.bonusDamage += 6;
    atk.bonusCrit = 6;
    atk.numHits = 1;
    atk.name = "Valorous Smite";
    
    return atk;
  }
  
  // burst 5 encounter
  public static Attack righteousSmite(int atkPenalty, int dmgPenalty, boolean lock){
    
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.encounter = true;
    atk.priority = 2;
    atk.defenseTarget = 1;
    atk.help = false;
    atk.attackBonus += 12;
    atk.damageDice1 = 10;
    atk.damageDice2 = 10;
    atk.bonusDice = 0;
    atk.bonusDamage += 6;
    atk.bonusCrit = 6;
    atk.numHits = 1;
    atk.name = "Righteous Smite";
    
    return atk;
  }
  // melee at will heal with charges
  public static Attack layOnHands(){
    
    Attack atk = new Attack();
    
    atk.atWill = true;
    atk.hasCharges = true;
    atk.maxCharges = 4;
    atk.charges = 3;
    atk.priority = 5;
    atk.defenseTarget = 0;
    atk.help = true;
    atk.attackBonus = 0;
    atk.damageDice1 = 0;
    atk.damageDice2 = 0;
    atk.bonusDice = 0;
    atk.bonusDamage = 14;
    atk.bonusCrit = 0;
    atk.numHits = 1;
    atk.name = "Lay on Hands";
    
    return atk;
  }
  // melee daily
  public static Attack majesticHalo(int atkPenalty, int dmgPenalty, boolean lock){
    
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.daily = true;
    atk.priority = 4;
    atk.defenseTarget = 1;
    atk.attackBonus += 12;
    atk.damageDice1 = 10;
    atk.damageDice2 = 10;
    atk.bonusDice = 10;
    atk.bonusDamage += 6;
    atk.bonusCrit = 6;
    atk.numHits = 1;
    atk.name = "Majestic Halo";
    
    return atk;
  }
  
  // melee burst daily
  public static Attack strengthFromValor(int atkPenalty, int dmgPenalty, boolean lock){
    
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.encounter = true;
    atk.priority = 3;
    atk.defenseTarget = 2;
    atk.burst = true;
    atk.attackBonus += 8;
    atk.damageDice1 = 10;
    atk.damageDice2 = 0;
    atk.bonusDice = 0;
    atk.bonusDamage += 4;
    atk.bonusCrit = 6;
    atk.numHits = 1;
    atk.name = "Strength From Valor";
    
    return atk;
  }
}