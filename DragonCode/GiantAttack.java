// giant attack(s)
// last updated 4-25-15

/* List
 * Basic melee attack
 *   Kill
 */

public class GiantAttack extends Attack{
  public static Attack meleeBasic(int atkPenalty, int dmgPenalty){
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.priority = 0;
    atk.defenseTarget = 1;
    atk.attackBonus = 14;
    atk.damageDice1 = 12;
    atk.damageDice2 = 12;
    atk.bonusDice = 8;
    atk.bonusDamage = 10;
    atk.bonusCrit = 0;
    atk.numHits = 2;
    atk.name = "Melee Basic";
    
    return atk;
  }
}