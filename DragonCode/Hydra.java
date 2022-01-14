// Hydra in cavern somewhere
// Last updated 4-27-15

// Has 9 heads, attacks less often with fewer heads (hits = (1 + int(hp / 30)), must be manually done in playbook
// Perception multiplier of 1.55 to compensate for the multiple heads
import java.util.*;
public class Hydra extends Enemy{
  
  public Hydra(String identity){
    classID = "H";
    maxHP = 260;
    hp = maxHP;
    ac = 18;
    defaultAC = ac;
    fortitude = 17;
    defaultFortitude = fortitude;
    reflex = 15;
    defaultReflex = reflex;
    will = 15;
    defaultWill = will;
    tempHP = 0;
    initiative = 3;
    defaultInitiative = initiative;
    speed = 5;
    perception = (int)(8 * 1.55); // to compensate for multiple heads (1.05^9)
    passivePerception = perception + 10;
    bloodied = false;
    bloodiedValue = (float)Math.floor(maxHP / 2);
    type = 3;
    name = identity;
    
    abilitiesUpdate(0,0, locked);
  }
  public void abilitiesUpdate(int atkPenalty, int dmgPenalty, boolean lock[]){
    Attack bite = HydraAttack.bite(atkPenalty, dmgPenalty);
    abilities = new LinkedList<Attack>(); 
    abilities.add(bite);
  }
}