// Drakeling Attacks

/* List:
 * Basic Melee: At will, melee
 *   Kill
 */

public class DrakelingAttack extends Attack{
  
  public static Attack claw(int atkPenalty, int dmgPenalty){
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.priority = 0;
    atk.defenseTarget = 1;
    atk.attackBonus += 9;
    atk.damageDice1 = 0;
    atk.damageDice2 = 0;
    atk.bonusDice = 0;
    atk.bonusDamage += 5;
    atk.bonusCrit = 0;
    atk.name = "Claw";
    
    return atk;
  }
}