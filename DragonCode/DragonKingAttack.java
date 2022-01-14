// Dragon King Attacks
// Last edited 4-26-15

// Has a giant 2-handed axe, wears a cloak, Dragonkin

/* List:
 * Basic melee: At will, melee, attacks twice
 *   Use by default
 * Flame breath: Recharge 5, blast 5
 *   Use if 2 or more players are in a 5x5 radius for the blast
 *   Recharges on a 5 or 6 from a 6 sided die roll
 * Summon Drakelings: Not written down, summons 2 every turn
 */
public class DragonKingAttack extends Attack{
  
  // melee range, slashes with axe twice
  public static Attack meleeBasic(int atkPenalty, int dmgPenalty){
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.priority = 0;
    atk.defenseTarget = 1;
    atk.attackBonus += 12;
    atk.damageDice1 = 10;
    atk.damageDice2 = 0;
    atk.bonusDice = 4;
    atk.bonusDamage += 8;
    atk.bonusCrit = 12; // very vicious
    atk.numHits = 2;
    atk.name = "Melee Basic";
    
    return atk;
  }
  // breathes flame
  public static Attack flameBreath(int atkPenalty, int dmgPenalty, boolean lock){
    Attack atk = new Attack(atkPenalty, dmgPenalty, lock);
    
    atk.recharge = true;
    atk.rechargeValue = 5;
    atk.priority = 1;
    atk.blast = true;
    atk.maxRange = 5;
    atk.defenseTarget = 2;
    atk.attackBonus += 9;
    atk.damageDice1 = 10;
    atk.damageDice2 = 0;
    atk.bonusDice = 8;
    atk.bonusDamage += 5;
    atk.bonusCrit = 8;
    atk.numHits = 3; // approximate blast
    atk.name = "Flame Breath";
    
    return atk;
  }
}