// scorpion attacks
// last updated 5-2-15

public class ScorpionAttack extends Attack{
  
    public static Attack pincer(int atkPenalty, int dmgPenalty){
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.priority = 0;
    atk.defenseTarget = 1;
    atk.attackBonus += 11;
    atk.damageDice1 = 10;
    atk.damageDice2 = 0;
    atk.bonusDice = 6;
    atk.bonusDamage += 8;
    atk.bonusCrit = 0;
    atk.numHits = 2;
    atk.name = "Pincer";
    
    return atk;
  }
}