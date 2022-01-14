// Witch attack(s)
// Last updated 4-25-15

/* List:
 * Debilitating Ray: At will, ranged 10
 *   Damage
 */
public class WitchAttack extends Attack{
  
  public static Attack debilitatingRay(int atkPenalty, int dmgPenalty){
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.priority = 0;
    atk.defenseTarget = 4;
    atk.ranged = true;
    atk.maxRange = 10;
    atk.attackBonus += 8;
    atk.damageDice1 = 10;
    atk.damageDice2 = 0;
    atk.bonusDice = 0;
    atk.bonusDamage += 7;
    atk.bonusCrit = 0;
    atk.name = "Debilitating Ray";
    
    return atk;
  }
}