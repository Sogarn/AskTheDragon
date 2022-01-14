// drakeling, needed for 2nd floor somewhere
// last edited 4-25-15

// minions
import java.util.*;
public class Drakeling extends Enemy{
  
  public Drakeling(String identity){
    classID = "K";
    maxHP = 1;
    hp = maxHP;
    ac = 18;
    defaultAC = ac;
    fortitude = 16;
    defaultFortitude = fortitude;
    reflex = 16;
    defaultReflex = reflex;
    will = 12;
    defaultWill = will;
    tempHP = 0;
    initiative = 6;
    defaultInitiative = initiative;
    speed = 7;
    perception = 3;
    passivePerception = perception + 10;
    type = 0;
    name = identity;
    abilitiesUpdate(0,0, locked);
  }
    public void abilitiesUpdate(int atkPenalty, int dmgPenalty, boolean lock[]){
      Attack claw = DrakelingAttack.claw(atkPenalty, dmgPenalty);
      abilities = new LinkedList<Attack>(); 
      abilities.add(claw);
    }
  }