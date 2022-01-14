// Giant (Sleeping giant)
// Last edited 4-25-15

// elite
import java.util.*;
public class Giant extends Enemy{
  
  public Giant(String identity){
    classID = "G";
    maxHP = 188;
    hp = maxHP;
    ac = 20;
    defaultAC = ac;
    fortitude = 21;
    defaultFortitude = fortitude;
    reflex = 15;
    defaultReflex = reflex;
    will = 13;
    defaultWill = will;
    tempHP = 0;
    initiative = 5;
    defaultInitiative = initiative;
    speed = 5;
    perception = 8 - 5; // sleeping
    passivePerception = perception + 10;
    bloodied = false;
    bloodiedValue = (float)Math.floor(maxHP / 2);
    type = 2;
    name = identity;
    
    abilitiesUpdate(0,0,locked);
  }
  
  public void abilitiesUpdate(int atkPenalty, int dmgPenalty, boolean lock[]){
    Attack meleeBasic = GiantAttack.meleeBasic(atkPenalty, dmgPenalty);
    abilities = new LinkedList<Attack>(); 
    abilities.add(meleeBasic);
  }
}