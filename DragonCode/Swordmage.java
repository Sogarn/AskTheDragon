// swordmage
// last updated 4-25-15

import java.util.*;
public class Swordmage extends Player{
  
  public Swordmage(String identity){
    classID = "S";
    maxHP = 53;
    hp = maxHP;
    ac = 22;
    defaultAC = ac;
    fortitude = 16;
    defaultFortitude = fortitude;
    reflex = 18;
    defaultReflex = reflex;
    will = 16;
    defaultWill = will;
    initiative = 4;
    defaultInitiative = initiative;
    speed = 6;
    perception = 3;
    passivePerception = perception + 10;
    bloodied = false;
    bloodiedValue = (float) Math.floor(maxHP / 2);
    role = 1; // tank
    
    // second wave
    athletics = 10;
    acrobatics = 4;
    diplomacy = 1;
    endurance = 10;
    heal = 3;
    stealth = 11;
    surgeCount = 11;
    surgeValue = 14;
    name = identity;
    
    secondWind = PlayerAttack.secondWind(surgeValue);
    abilitiesUpdate(0,0,locked);
  }
  public void abilitiesUpdate(int atkPenalty, int dmgPenalty, boolean lock[]){
    // add attacks
    Attack boomingBlade = SwordmageAttack.boomingBlade(atkPenalty, dmgPenalty);
    Attack swordBurst = SwordmageAttack.swordBurst(atkPenalty, dmgPenalty);
    Attack swordOfSigils = SwordmageAttack.swordOfSigils(atkPenalty, dmgPenalty, lock[2]);
    Attack dimensionalVortex = SwordmageAttack.dimensionalVortex(atkPenalty, dmgPenalty, lock[3]);
    Attack dimensionalThunder = SwordmageAttack.dimensionalThunder(atkPenalty, dmgPenalty, lock[4]);
    
    abilities = new LinkedList<Attack>(); 
    abilities.add(boomingBlade);
    abilities.add(swordBurst);
    abilities.add(swordOfSigils);
    abilities.add(dimensionalVortex);
    abilities.add(dimensionalThunder);
    Collections.sort(abilities);
  }
}