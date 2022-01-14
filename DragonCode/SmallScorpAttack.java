// scorpion attacks
// last updated 5-2-15

public class SmallScorpAttack extends Attack{
  
    public static Attack pincer(int atkPenalty, int dmgPenalty){
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.priority = 0;
    atk.defenseTarget = 1;
    atk.attackBonus += 9;
    atk.damageDice1 = 0;
    atk.damageDice2 = 0;
    atk.bonusDice = 4;
    atk.bonusDamage += 5;
    atk.bonusCrit = 0;
    atk.numHits = 1;
    atk.name = "Pincer";
    
    return atk;
  }
}