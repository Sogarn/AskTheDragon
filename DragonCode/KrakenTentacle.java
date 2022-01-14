// Kraken (cavern)
// Last updated 4-25-15

// elite, will be coded as 8 tentacles
import java.util.*;
public class KrakenTentacle extends Enemy{
  
  public KrakenTentacle(String identity){
    classID = "T";
    maxHP = 30;
    hp = maxHP;
    ac = 18;
    defaultAC = ac;
    fortitude = 15;
    defaultFortitude = fortitude;
    reflex = 17;
    defaultReflex = reflex;
    will = 12;
    defaultWill = will;
    tempHP = 0;
    initiative = 2;
    defaultInitiative = initiative;
    speed = 1;
    perception = 3;
    passivePerception = perception + 10;
    bloodied = false;
    bloodiedValue = (float)Math.floor(maxHP / 2);
    type = 3;
    name = identity;
    
    abilitiesUpdate(0,0, locked);
  }
  public void abilitiesUpdate(int atkPenalty, int dmgPenalty, boolean lock[]){
    Attack meleeBasic = KrakenTentacleAttack.meleeBasic(atkPenalty, dmgPenalty);
    abilities = new LinkedList<Attack>(); 
    abilities.add(meleeBasic);
  }
}