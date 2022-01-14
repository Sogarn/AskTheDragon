// ardent
// last edited 4-25-15
import java.util.*;
public class Ardent extends Player{

  public Ardent(String identity){
    classID = "D";
    maxHP = 47;
    hp = maxHP;
    ac = 21;
    defaultAC = ac;
    fortitude = 17;
    defaultFortitude = fortitude;
    reflex = 14;
    defaultReflex = reflex;
    will = 19;
    defaultWill = will;
    initiative = 3;
    defaultInitiative = initiative;
    speed = 5;
    perception = 3;
    passivePerception = perception + 10;
    bloodied = false;
    bloodiedValue = (float) Math.floor(maxHP / 2);
    name = identity;
    
    // second wave
    athletics = 9;
    acrobatics = 3;
    diplomacy = 14;
    endurance = 10;
    heal = 7;
    stealth = 5;
    surgeCount = 10;
    surgeValue = 12;
    
    // create attacks. Heals are unique in the sense that they are created once.
    abilitiesUpdate(0,0, locked);
    ardentSurge = ArdentAttack.ardentSurge();
    secondWind = PlayerAttack.secondWind(surgeValue);
    abilities.add(ardentSurge);
    Collections.sort(abilities);
  }
  
  public void abilitiesUpdate(int atkPenalty, int dmgPenalty, boolean lock[]){
    Attack demoralizingStrike = ArdentAttack.demoralizingStrike(atkPenalty,dmgPenalty);
    Attack energizingStrike = ArdentAttack.energizingStrike(atkPenalty,dmgPenalty);
    Attack impatientStrike = ArdentAttack.impatientStrike(atkPenalty,dmgPenalty);
    Attack implantedSuggestion = ArdentAttack.implantedSuggestion(atkPenalty,dmgPenalty, lock[3]);
    
    abilities = new LinkedList<Attack>(); 
    abilities.add(demoralizingStrike);
    abilities.add(energizingStrike);
    abilities.add(impatientStrike);
    abilities.add(implantedSuggestion);
    Collections.sort(abilities);
  }
}