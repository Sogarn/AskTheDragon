// hydra attack(s)
// last edited 4-29-15

/* List
 * Bite: At will, melee
 *   update numhits as hp goes down, should be 1 + floor(hp/25);
 */
public class HydraAttack extends Attack{
  public static Attack bite(int atkPenalty, int dmgPenalty){
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.priority = 0;
    atk.defenseTarget = 1;
    atk.attackBonus += 9;
    atk.damageDice1 = 6;
    atk.damageDice2 = 0;
    atk.bonusDice = 0;
    atk.bonusDamage += 0;
    atk.bonusCrit = 0;
    atk.numHits = 9;
    atk.name = "Bite";
    
    return atk;
  }
}