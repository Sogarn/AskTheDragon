// cleric
// last updated 4-25-15

import java.util.*;
public class Cleric extends Player{

  public Cleric(String identity){
    classID = "C";
    maxHP = 47;
    hp = maxHP;
    ac = 20;
    defaultAC = ac;
    fortitude = 15;
    defaultFortitude = fortitude;
    reflex = 15;
    defaultReflex = reflex;
    will = 20;
    defaultWill = will;
    initiative = 4;
    defaultInitiative = initiative;
    speed = 5;
    perception = 7;
    passivePerception = perception + 10;
    bloodied = false;
    bloodiedValue = (float) Math.floor(maxHP / 2);
    regen = 2;
    
    // second wave
    athletics = 4;
    acrobatics = 3;
    diplomacy = 2;
    endurance = 5;
    heal = 12;
    stealth = 5;
    surgeCount = 12;
    surgeValue = 9;
    name = identity;
    
    healingWord = ClericAttack.healingWord();
    secondWind = PlayerAttack.secondWind(surgeValue);
    abilitiesUpdate(0,0,locked);
    abilities.add(healingWord);
    Collections.sort(abilities);
    
  }
  public void abilitiesUpdate(int atkPenalty, int dmgPenalty, boolean lock[]){
    // attacks
    Attack brandOfTheSun = ClericAttack.brandOfTheSun(atkPenalty, dmgPenalty);
    Attack singingStrike = ClericAttack.singingStrike(atkPenalty, dmgPenalty);
    Attack sunBurst = ClericAttack.sunBurst(atkPenalty, dmgPenalty, lock[2]);
    Attack hammeringWind = ClericAttack.hammeringWind(atkPenalty, dmgPenalty, lock[3]);
    Attack momentOfGlory = ClericAttack.momentOfGlory(atkPenalty, dmgPenalty, lock[4]);
    
    abilities = new LinkedList<Attack>();
    abilities.add(brandOfTheSun);
    abilities.add(singingStrike);
    abilities.add(sunBurst);
    abilities.add(hammeringWind);
    abilities.add(momentOfGlory);
    Collections.sort(abilities);
  }
}