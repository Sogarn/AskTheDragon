// misc functions
// last updated 4-30-15

import java.util.*;
public class Misc{
  
  // uses surges if possible
  public static Player surgeCheck(Player p){
    // will surge if missing at least surgeValue hp, have surges, and are alive
    while (((p.missingHP() >= (int)(0.8*p.surgeValue)) && p.surgeCount > 0) && p.alive){
      p.hp += p.surgeValue; // use surge
      p.surgeCount -= 1; // update surge count
    }
    return p;
  }
  
  // checks if total party kill
  public static boolean tpkCheck(LinkedList<Player> party){
    int counter = 0;
    for (int t = 0; t < party.size(); t++){
      if (party.get(t).alive && !party.get(t).unconscious){ // if someone alive and conscious, add to counter
        counter += 1;
      }
    }
    if (counter == 0){ // if nobody alive, return true
      return true;
    }
    return false; // otherwise, return false
  }
  
  // checks if heal attempt succeeds on target. 0 does nothing, 1 revives, 2 stabilizes.
  public static int healCheck(Player saint){
    int check = saint.heal + DiceRoll.randRoll();
    if(check >= 20){
      return 2;
    }else if(check >= 10){
      return 1;
    }
    return 0;
  }
  
  //does full suite of alive? surge? bloodied? for players
  public static Player playerDamagePackage(Player p){
    p.aliveCheck();
    p = surgeCheck(p);
    p.bloodied();
    return p;
  }
  
  //checks alive? bloodied? for general usage
  public static Character damagePackage(Character c){
    c.aliveCheck();
    c.bloodied();
    return c;
  }
  
  //checks alive? bloodied? for players
  public static Player damagePackage(Player p){
    p.aliveCheck();
    p.bloodied();
    return p;
  }
  
  //checks alive? bloodied? for enemies
  public static Enemy damagePackage(Enemy e){
    e.aliveCheck();
    e.bloodied();
    return e;
  }
  
  // resets things post battle
  public static LinkedList<Player> postBattle(LinkedList<Player> players){
    for (int l = 0; l < players.size(); l++){
      players.get(l).unconscious = false;
      players.set(l ,playerDamagePackage(players.get(l))); // reset health / heal
      players.get(l).deathRoll = 0;
      players.get(l).initiative = players.get(l).defaultInitiative;
      players.get(l).tempHP = 0;
      players.get(l).secondWind.used = false;
      
      players.get(l).ardentSurge.charges = players.get(l).ardentSurge.maxCharges; // special snowflakes
      players.get(l).healingWord.charges = players.get(l).healingWord.maxCharges; // get more charges
      if (players.get(l).debuffed){ // if debuffed
        players.get(l).debilitated = 0;
        players.get(l).debuffed = false;
        
        players.get(l).atkPenalty = 0;
        players.get(l).dmgPenalty = 0;
        players.get(l).abilitiesUpdate(0, 0, players.get(l).locked);
        
        players.get(l).ac = players.get(l).defaultAC;
        players.get(l).fortitude = players.get(l).defaultFortitude;
        players.get(l).reflex = players.get(l).defaultReflex;
        players.get(l).will = players.get(l).defaultWill;
        players.get(l).initiative = players.get(l).defaultInitiative;
        
      }
      
      for (int mj = 0; mj < players.get(l).abilities.size(); mj++){ // reset abilities
        if (!players.get(l).abilities.get(mj).daily){ // not daily
          players.get(l).abilities.get(mj).used = false;
          players.get(l).locked[mj] = false;
        }
      }
    }
    return players;
  }
  
  // checks if anyone in party is debilitated
  public static boolean partyDebuffed(LinkedList<Player> players){
    for(int iky = 0; iky < players.size(); iky++){
      // if their debilitated is 1, it'll just reset next turn anyway (It'll -= 1 to 0)
      if ((players.get(iky).debuffed) && (players.get(iky).debilitated >= 1)){
        return true;
      }
    }
    return false;
  }
  
  // finds index of debilitated party member
    public static int findDebuffed(LinkedList<Player> players){
      int minDebil = 1;
      for(int iky = 0; iky < players.size(); iky++){
      if ((players.get(iky).debilitated) >= minDebil){
        return iky;
      }
    }
    return 0;
  }
    
    // unconscious check
    // checks if anyone in party is debilitated
    public static boolean partyUnconscious(LinkedList<Player> players){
      for(int iky = 0; iky < players.size(); iky++){
        if (players.get(iky).unconscious){
          return true;
        }
      }
      return false;
    }
    
    // checks if anyone in party is bloodied
    public static boolean partyBloodied(LinkedList<Player> players){
      for(int iky = 0; iky < players.size(); iky++){
        if (players.get(iky).bloodied){
          return true;
        }
      }
      return false;
    }

    // finds index of bloodied party member
    public static int bloodiedPlayer(LinkedList<Player> players){
      for(int iky = 0; iky < players.size(); iky++){
        if ((players.get(iky).bloodied) && players.get(iky).alive){
          return iky;
        }
      }
      return 0;
    }
    
    // converts list of players into list of alive players
    public static LinkedList<Player> alivePlayers(LinkedList<Player> players){
      LinkedList<Player> alives = new LinkedList<Player>();
      for (int iop = 0; iop < players.size(); iop++){
        // if player alive, add to list of alive players
        if (players.get(iop).alive){
          alives.add(players.get(iop));
        }
      }
      return alives;
    }
}