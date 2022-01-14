// guard attacks
// last updated 5-2-15

public class KoboldGuardAttack extends Attack{
  
    public static Attack rangedBasic(int atkPenalty, int dmgPenalty){
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.ranged = true;
    atk.maxRange = 20;
    atk.priority = 0;
    atk.defenseTarget = 1;
    atk.attackBonus += 11;
    atk.damageDice1 = 10;
    atk.damageDice2 = 0;
    atk.bonusDice = 0;
    atk.bonusDamage += 8;
    atk.bonusCrit = 0;
    atk.name = "Ranged Basic";
    
    return atk;
  }
}