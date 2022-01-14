// Kraken slappin'
// Last edited 4-25-15

public class KrakenTentacleAttack extends Attack{
    public static Attack meleeBasic(int atkPenalty, int dmgPenalty){
    Attack atk = new Attack(atkPenalty, dmgPenalty);
    
    atk.atWill = true;
    atk.priority = 0;
    atk.defenseTarget = 1;
    atk.attackBonus += 9;
    atk.damageDice1 = 8;
    atk.damageDice2 = 0;
    atk.bonusDice = 0;
    atk.bonusDamage += 4;
    atk.bonusCrit = 0;
    atk.name = "Melee Basic";
    
    return atk;
  }
}