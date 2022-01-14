// Paladin
// Last edited 4-25-15

import java.util.*;

public class Paladin extends Player{
  
  public Paladin(String identity){
    classID = "P";
    maxHP = 53; // from encounter power that will be used permanently
    hp = maxHP;
    ac = 23;
    defaultAC = ac;
    fortitude = 17;
    defaultFortitude = fortitude;
    reflex = 18;
    defaultReflex = reflex;
    will = 20;
    defaultWill = will;
    initiative = 3;
    defaultInitiative = initiative;
    speed = 5;
    perception = 6;
    passivePerception = perception + 10;
    bloodied = false;
    bloodiedValue = (float) Math.floor(maxHP / 2);
    name = identity;
    role = 1; // tank
    
    // second wave
    athletics = -2;
    acrobatics = -1;
    diplomacy = 12;
    endurance = 5;
    heal = 10;
    stealth = -1;
    surgeCount = 12;
    surgeValue = 14;

    layOnHands = PaladinAttack.layOnHands();
    secondWind = PlayerAttack.secondWind(surgeValue);
    abilitiesUpdate(0,0, locked);
    abilities.add(layOnHands);
    Collections.sort(abilities);
    

  }
  public void abilitiesUpdate(int atkPenalty, int dmgPenalty, boolean lock[]){
    // Create the rangedbasic attack from the RangerAttack class
    Attack virtuousStrike = PaladinAttack.virtuousStrike(atkPenalty, dmgPenalty);
    Attack valorousSmite = PaladinAttack.valorousSmite(atkPenalty, dmgPenalty, lock[1]);
    Attack righteousSmite = PaladinAttack.righteousSmite(atkPenalty, dmgPenalty, lock[2]);
    Attack majesticHalo = PaladinAttack.majesticHalo(atkPenalty, dmgPenalty, lock[4]);
    Attack strengthFromValor = PaladinAttack.strengthFromValor(atkPenalty, dmgPenalty, lock[3]);
    
    // someday sort the ability list with priority
    // add it to abilities list
    abilities = new LinkedList<Attack>(); 
    abilities.add(virtuousStrike);
    abilities.add(valorousSmite);
    abilities.add(righteousSmite);
    abilities.add(strengthFromValor);
    abilities.add(majesticHalo);
    Collections.sort(abilities);
  }
}