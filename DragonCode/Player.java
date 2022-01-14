// Player character extension
// Last edited 4-13-15

public class Player extends Character{
  int athletics, acrobatics, diplomacy, endurance, heal, stealth; // more skills
  int deathRoll, surgeCount, surgeValue; // survival stuff
  boolean unconscious; // first step before death for players, have 3 death saving throws (deathRoll) before death. 20 saves them with 25% hp.
  boolean stabilized; // no longer has to do death rolls
  int role; // tank, support, Rdamage, Mdamage, only initialized if needed somewhere in code (like tanks)
  // sort of cheating with battle statistics
  int killCount, unconsciousCount, deathCount, healTotal;
  // what a nuisance
  Attack layOnHands, healingWord, ardentSurge, secondWind;
  
  
  public Player(){
    athletics = 0;
    acrobatics = 0;
    diplomacy = 0;
    endurance = 0;
    heal = 0;
    stealth = 0;
    deathRoll = 0;
    surgeCount = 0;
    surgeValue = 0;
    identifier = 1; // from character class, 1 = player, 2 = enemy
    unconscious = false;
    role = 0;
    layOnHands = PaladinAttack.layOnHands();
    healingWord = ClericAttack.healingWord();
    ardentSurge = ArdentAttack.ardentSurge();
  }
  // trick to store statistics
  public Player(int kills, int unconsciouses, int deaths, int heals){
    athletics = 0;
    acrobatics = 0;
    diplomacy = 0;
    endurance = 0;
    heal = 0;
    stealth = 0;
    deathRoll = 0;
    surgeCount = 0;
    surgeValue = 0;
    identifier = 1; // from character class, 1 = player, 2 = enemy
    unconscious = false;
    killCount = kills;
    unconsciousCount = unconsciouses;
    deathCount = deaths;
    healTotal = heals;
  }
}