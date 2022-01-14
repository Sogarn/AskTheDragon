// guard attacks
// last updated 5-2-15

public class GuardAttack extends Attack{
  
    public static Attack meleeBasic(int atkPenalty, int dmgPenalty){
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.priority = 0;
    atk.defenseTarget = 1;
    atk.attackBonus += 10;
    atk.damageDice1 = 8;
    atk.damageDice2 = 0;
    atk.bonusDice = 0;
    atk.bonusDamage += 5;
    atk.bonusCrit = 0;
    atk.name = "Melee Basic";
    
    return atk;
  }
}