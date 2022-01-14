// spider attacks
// last updated 4-26-15

public class SpiderAttack extends Attack{
  
    public static Attack bite(int atkPenalty, int dmgPenalty){
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.priority = 0;
    atk.defenseTarget = 1;
    atk.attackBonus += 9;
    atk.damageDice1 = 6;
    atk.damageDice2 = 6;
    atk.bonusDice = 0;
    atk.bonusDamage += 3;
    atk.bonusCrit = 0;
    atk.name = "Bite";
    
    return atk;
  }
}