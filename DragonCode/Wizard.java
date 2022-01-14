// Wizard
// Last updated 4-25-15

import java.util.*;

public class Wizard extends Player{
  
  public Wizard(String identity){
    classID = "Z";
    maxHP = 42;
    hp = maxHP;
    ac = 20;
    defaultAC = ac;
    fortitude = 17;
    defaultFortitude = fortitude;
    reflex = 19;
    defaultReflex = reflex;
    will = 17;
    defaultWill = will;
    initiative = 2;
    defaultInitiative = initiative;
    speed = 6;
    perception = 14;
    passivePerception = perception + 10;
    bloodied = false;
    bloodiedValue = (float) Math.floor(maxHP / 2);
    
    // second wave
    athletics = 1+10; // has an encounter power that will basically always be up
    acrobatics = 2;
    diplomacy = 3;
    endurance = 5;
    heal = 3;
    stealth = 4;
    surgeCount = 9;
    surgeValue = 11;
    name = identity;
    
    secondWind = PlayerAttack.secondWind(surgeValue);
    abilitiesUpdate(0,0,locked);
  }
  public void abilitiesUpdate(int atkPenalty, int dmgPenalty, boolean lock[]){
    // add abilities
    Attack magicMissile = WizardAttack.magicMissile(atkPenalty, dmgPenalty);
    Attack freezingBurst = WizardAttack.freezingBurst(atkPenalty, dmgPenalty);
    Attack arcLightning = WizardAttack.arcLightning(atkPenalty, dmgPenalty);
    Attack burningHands = WizardAttack.burningHands(atkPenalty, dmgPenalty, locked[2]);
    Attack forceOrb = WizardAttack.forceOrb(atkPenalty, dmgPenalty, locked[3]);
    Attack fireShroud = WizardAttack.fireShroud(atkPenalty, dmgPenalty, locked[4]);
    Attack shockSphere = WizardAttack.shockSphere(atkPenalty, dmgPenalty, locked[5]);
    Attack flamingSphere = WizardAttack.flamingSphere(atkPenalty, dmgPenalty, locked[6]);
    Attack fountainOfFlame = WizardAttack.fountainOfFlame(atkPenalty, dmgPenalty, locked[7]);
    
    abilities = new LinkedList<Attack>(); 
    abilities.add(magicMissile);
    abilities.add(freezingBurst);
    abilities.add(arcLightning);
    abilities.add(burningHands);
    abilities.add(forceOrb);
    abilities.add(fireShroud);
    abilities.add(shockSphere);
    abilities.add(flamingSphere);
    abilities.add(fountainOfFlame);
    Collections.sort(abilities);
  }
  
}