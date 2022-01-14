// universal player attacks
// added 5-5-15

public class PlayerAttack extends Attack{
  // everyone gets this heal
  public static Attack secondWind(int surgeValue){
    Attack atk = new Attack();
    atk.encounter = true;
    atk.priority = 99; // always last option in abilities list for easy access
    atk.defenseTarget = 0;
    atk.help = true;
    atk.damageDice1 = 0;
    atk.bonusDamage = surgeValue;
    atk.bonusCrit = 0;
    atk.name = "Second Wind";
    
    return atk;
  }
}