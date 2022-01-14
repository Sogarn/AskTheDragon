// The main function!
// v1.0.1
/* Patch notes:
 *   Functionality:
 *     Battle.java now neater and easier to read!
 *     Added the "Heal Total" statistic
 *   Bug fixes:
 *    Fixed a bug where wizards were dying too easily and crashing program
 *    Fixed a bug where rangers were not using their encounter powers
 * v1.0
 * Patch notes:
 *   Official release!
 */
// Combinations (# of parties) / replications around line 31
// Castle function starts around line 74.
// Most seemingly random functions are either somewhere in this class, or in the Misc.java class
// Statistics heirachy is: individual run -> replications -> party output

import java.util.*;
import java.lang.Math;
import java.text.DecimalFormat;

public class Simulation{
  static int killCount, unconsciousCount, deathCount, skillSuccesses, skillFailures, healTotal; // individual run stats
  static double surgesUsed, surgesMax, surgesLeft; // healTotal only refers to in-battle healing including temp hp
  static double avgWins, avgKillCount, avgUnconsciousCount, avgDeathCount, avgSkillSuccesses;
  static double avgSkillFails, avgSurgePct, avgHeals; // statistics for average
  static boolean victory, tpk; // for if they win or lose, and if at least 1 player is still alive
  static LinkedList<Player> playerList; // enemy list only needed for battles, player list sort of global
  static LinkedList<Enemy> enemyList; // current set of enemies
  static Player statGuy; // stores battle statistics
  static int wins[]; // stores replication data
  static int kills[], unconscious[], deaths[], skillPasses[], skillFails[], heals[];
  static double surgePcts[]; // stores statistics of individual runs
  static final int MEMBERS = 5; // number of party members
  static final int MAXCLASSES = 8; // number of characters total
  
  public static void main(String args[]){
    int combinations = 56; // precalculated number of combinations of party members, default 56
    double replications = 2420; // number of replications per party, default 1680
    
    // finds all combinations of characters
    Character[] elements = new Character[MAXCLASSES];
    LinkedList<String> order = Combination.combination(elements, MEMBERS);
    
    // for future rounding
    DecimalFormat df = new DecimalFormat("#.0000");
    
    // dummy check
    if (combinations > 56){
      combinations = 56;
    }
    if (combinations < 1){
      combinations = 1;
    }
    
// ----------------------------------------------------------------------------------------------------     
    // each party
    for (int i = 0; i < combinations; i++){
      double[] surgePcts = new double[(int)replications];
      int[] wins = new int[(int)replications];
      int[] kills = new int[(int)replications];
      int[] unconscious = new int[(int)replications];
      int[] deaths = new int[(int)replications];
      int[] skillPasses = new int[(int)replications];
      int[] skillFails = new int[(int)replications];
      int[] heals = new int[(int)replications];
      // replications per party
      for (int j = 0; j < replications; j++){
        // get fresh batch of players
        surgesLeft = 0;
        surgesUsed = 0;
        surgesMax = 0;
        killCount = 0;
        unconsciousCount = 0;
        deathCount = 0;
        skillSuccesses = 0;
        skillFailures = 0;
        healTotal = 0;
        
        playerList = initialization(order, i);
        // find total # of surges for party
        surgeTotal(playerList);
        
// castle function begins ----------------------------------------------------------------------------------------------------       
        
        while (!victory && !tpk){ // while both are false
          // call castle functions here, beating dragon king or full party wipe ends simulation
          
          //skillChallengeSkill(playerList, number to beat, damage if fail)
          
          // begin outside
//-------------
          // fight spiders in forest
          enemyList = new LinkedList<Enemy>();
          enemyList.add(new Spider("Spider1"));
          enemyList.add(new Spider("Spider2"));
          enemyList.add(new Spider("Spider3"));
          enemyList.add(new Spider("Spider4"));
          enemyList.add(new Spider("Spider5"));
          playerList = Battle.battle(playerList, enemyList);
          // update statistics
          updateStatistics(playerList.removeLast());
          // update tpk
          tpk = Misc.tpkCheck(playerList);
          // post-battle rejuvenation
          playerList = Misc.postBattle(playerList);
//-------------            
          if (!tpk){
            // fork in road
            if(skillChallengeDiplomacy(playerList,14)){   
              // enter the abyss, swim through whirlpool
              playerList = skillChallengeEndurance(playerList, 16, 8);
              // updates tpk
              tpk = Misc.tpkCheck(playerList);
              
              if (!tpk){
                // fight kraken's 8 tentacles to reach cavern wall
                enemyList = new LinkedList<Enemy>();
                enemyList.add(new KrakenTentacle("T1"));
                enemyList.add(new KrakenTentacle("T2"));
                enemyList.add(new KrakenTentacle("T3"));
                enemyList.add(new KrakenTentacle("T4"));
                enemyList.add(new KrakenTentacle("T5"));
                enemyList.add(new KrakenTentacle("T6"));
                enemyList.add(new KrakenTentacle("T7"));
                enemyList.add(new KrakenTentacle("T8"));
                playerList = Battle.battle(playerList, enemyList);
                updateStatistics(playerList.removeLast());
                tpk = Misc.tpkCheck(playerList);
                playerList = Misc.postBattle(playerList);  
                
                if (!tpk){ // climb wall, enter basement
                  playerList = skillChallengeAthletics(playerList, 12, 15);
                  tpk = Misc.tpkCheck(playerList);
                  
                  if (!tpk){
                    // in the basement, try to sneak past hydra, if failure, fight
                    // failure very likely (~90%)
                    enemyList = new LinkedList<Enemy>();
                    enemyList.add(new Hydra("Hydra"));
                    if (!skillChallengePerception(enemyList, playerList)){
                      playerList = Battle.battle(playerList,enemyList);
                      updateStatistics(playerList.removeLast());
                      tpk = Misc.tpkCheck(playerList);
                      playerList = Misc.postBattle(playerList);  
                    }
                  }
                }
                // emerge on second floor
              }
//------------- other fork
            }else{
              // jump across lava field
              playerList = skillChallengeAthletics(playerList, 15, 10);
              // updates tpk
              tpk = Misc.tpkCheck(playerList);
              // fight huge scorpion and tiny ones in wasteland near castle
              if(!tpk){
                enemyList = new LinkedList<Enemy>();
                enemyList.add(new Scorpion("BigScorp"));
                enemyList.add(new SmallScorp("SmallScorp1"));
                enemyList.add(new SmallScorp("SmallScorp2"));
                enemyList.add(new SmallScorp("SmallScorp3"));
                playerList = Battle.battle(playerList, enemyList);
                updateStatistics(playerList.removeLast());
                tpk = Misc.tpkCheck(playerList);
                playerList = Misc.postBattle(playerList);  
                
                // balance over bridge
                if (!tpk){
                  playerList = skillChallengeAcrobatics(playerList, 14, 10);
                  tpk = Misc.tpkCheck(playerList);
                  // emerge in garden
                  
                  if (!tpk){
                    // try to sneak past guards, if they fail, fight them + extra
                    enemyList = new LinkedList<Enemy>();
                    enemyList.add(new Guard("Guard1"));
                    enemyList.add(new Guard("Guard2"));
                    enemyList.add(new Guard("Guard3"));
                    enemyList.add(new Guard("Guard4"));
                    if (!skillChallengePerception(enemyList, playerList)){
                      enemyList.add(new KoboldGuard("Kobold1"));
                      enemyList.add(new KoboldGuard("Kobold2"));
                      enemyList.add(new KoboldGuard("Kobold3"));
                      playerList = Battle.battle(playerList,enemyList);
                      updateStatistics(playerList.removeLast());
                      tpk = Misc.tpkCheck(playerList);
                      playerList = Misc.postBattle(playerList);  
                    }
                    
                    // climb wall onto second floor
                    if (!tpk){
                      playerList = skillChallengeAthletics(playerList, 15, 14);
                      tpk = Misc.tpkCheck(playerList);
                    }
                  }
                }
              }
            }
          }
          // we meet up at the second floor
          if (!tpk){
            // fight 3 witches in an alchemy room
            enemyList = new LinkedList<Enemy>();
            enemyList.add(new Witch("Witch1"));
            enemyList.add(new Witch("Witch2"));
            enemyList.add(new Witch("Witch3"));
            enemyList.add(new Witch("Witch4"));
            playerList = Battle.battle(playerList,enemyList);
            updateStatistics(playerList.removeLast());
            tpk = Misc.tpkCheck(playerList);
            playerList = Misc.postBattle(playerList); 
            if (!tpk){
              // sleeping giant
              enemyList = new LinkedList<Enemy>();
              enemyList.add(new Giant("Giant"));
              if (!skillChallengePerception(enemyList, playerList)){
                playerList = Battle.battle(playerList,enemyList);
                updateStatistics(playerList.removeLast());
                tpk = Misc.tpkCheck(playerList);
                playerList = Misc.postBattle(playerList); 
              }
              // move past giant into dragon's lair
              if (!tpk){
                // dragon king fight here
                enemyList = new LinkedList<Enemy>();
                enemyList.add(new DragonKing("DragonKing"));
                playerList = Battle.battle(playerList,enemyList);
                updateStatistics(playerList.removeLast());
                tpk = Misc.tpkCheck(playerList);
              }
            }
          }
//-------------            
          // final line
          if (!tpk){
            victory = true;
          }
        }
        
// castle function ends ----------------------------------------------------------------------------------------------------   
        
        // record trial data to cumulative count     
        surgeUsage(playerList); // find how many surges are left
        surgesUsed = surgesMax - surgesLeft;
        
        // sanity check
        if (tpk){
          deathCount = 5;
        }
        
        if (victory){
          wins[j] = 1;
        }
        kills[j] = killCount;
        unconscious[j] = unconsciousCount;
        deaths[j] = deathCount;
        skillPasses[j] = skillSuccesses;
        skillFails[j] = skillFailures;
        surgePcts[j] = surgesUsed / surgesMax;
        heals[j] = healTotal;
      }
      
      // statistics time
      
      // print party
      conversion(playerList, i);
      
      // combination statistics
      double avgWins = 0;
      double avgKills = 0;
      double avgUnconscious = 0;
      double avgDeaths = 0;
      double avgSkillPasses = 0;
      double avgSkillFailures = 0;
      double avgSurgePct = 0;
      double avgHeals = 0;
      
      // averages across replications
      for (int x = 0; x < replications; x++){
        avgWins += (double)(wins[x] / replications);
        avgKills += (double)(kills[x] / replications);
        avgUnconscious += (double)(unconscious[x] / replications);
        avgDeaths += (double)(deaths[x] / replications);
        avgSkillPasses += (double)(skillPasses[x] / replications);
        avgSkillFailures += (double)(skillFails[x] / replications);
        avgSurgePct += (double)(surgePcts[x] / replications);
        avgHeals += (double)(heals[x] / replications);
      }
      
      // variances
      double varWins = 0;
      double varKills = 0;
      double varUnconscious = 0;
      double varDeaths = 0;
      double varSkillPasses = 0;
      double varSkillFailures = 0;
      double varSurgePct = 0;
      double varHeals = 0;
      
      for (int w = 0; w < replications; w++){
        varKills += Math.pow((kills[w] - avgKills),2)/replications;
        varUnconscious += Math.pow((unconscious[w] - avgUnconscious),2)/(replications-1);
        varDeaths += Math.pow((deaths[w] - avgDeaths),2)/(replications-1);
        varSkillPasses += Math.pow((skillPasses[w] - avgSkillPasses),2)/(replications-1);
        varSkillFailures += Math.pow((skillFails[w] - avgSkillFailures),2)/(replications-1);
        varSurgePct += Math.pow((surgePcts[w] - avgSurgePct),2)/(replications-1);
        varHeals += Math.pow((heals[w] - avgHeals),2)/(replications-1);
      }
      
      // proportion is a single calculation
      varWins = (avgWins*(1-avgWins)/(replications-1));
      
      // half widths for confidence interval
      // alpha = 0.05, two sided
      double tValue = 1.96138635; // approximately normal anyway
//      double tValue = 2.80961193; // for pairwise top 5, df = 2419
      double sqrReps = Math.sqrt(replications);
      double halfWins = tValue*Math.sqrt(varWins)/sqrReps;
      double halfKills = tValue*Math.sqrt(varKills)/sqrReps;
      double halfUnconscious = tValue*Math.sqrt(varUnconscious)/sqrReps;
      double halfDeaths = tValue*Math.sqrt(varDeaths)/sqrReps;
      double halfSkillPasses = tValue*Math.sqrt(varSkillPasses)/sqrReps;
      double halfSkillFailures = tValue*Math.sqrt(varSkillFailures)/sqrReps;
      double halfSurgePct = tValue*Math.sqrt(varSurgePct)/sqrReps;
      double halfHeals = tValue*Math.sqrt(varHeals)/sqrReps;

      System.out.println("Win Rate: " + df.format(avgWins) + " +- " + df.format(halfWins));
      System.out.println("Kills: " + df.format(avgKills) + " +- " + df.format(halfKills));
      System.out.println("Heal Total: " + df.format(avgHeals) + " +- " + df.format(halfHeals));
      System.out.println("Unconscious: " + df.format(avgUnconscious) + " +- " + df.format(halfUnconscious));
      System.out.println("Deaths: " + df.format(avgDeaths) + " +- " + df.format(halfDeaths));
      System.out.println("Skill Passes: " + df.format(avgSkillPasses) + " +- " + df.format(halfSkillPasses));
      System.out.println("Skill Failures: " + df.format(avgSkillFailures) + " +- " + df.format(halfSkillFailures));
      System.out.println("Surge Usage Ratio: " + df.format(avgSurgePct) + " +- " + df.format(halfSurgePct) + "\n");
    }
  }
  
// ----------------------------------------------------------------------------------------------------
  // initialize/reset individual run data
  public static LinkedList<Player> initialization(LinkedList<String> order, int index){
    victory = false;
    tpk = false;  
    // use combinations to figure out what is the next party to test
    return nextOrder(order, index);
  }
  
  // print list of players in a nicer format
  public static void conversion(LinkedList<Player> party, int comboNum){
    System.out.print("#" + (comboNum + 1) + " ");
    for (int v = 0; v < party.size(); v++){
      System.out.print(party.get(v).toString() + " ");
    }
    System.out.print("\n");
  }
  
  // find max # of surges for party, done before run starts
  public static void surgeTotal(LinkedList<Player> party){
    for (int t = 0; t < party.size(); t++){
      surgesMax += party.get(t).surgeCount;
    }
  }
  
  // find total surges left, done after run is over
  public static void surgeUsage(LinkedList<Player> party){
    for (int t = 0; t < party.size(); t++){
      surgesLeft += party.get(t).surgeCount;
    }
  }
  // update battle stats
  public static void updateStatistics(Player stats){
    killCount += stats.killCount;
    unconsciousCount += stats.unconsciousCount;
    deathCount += stats.deathCount;
    healTotal += stats.healTotal;
  }
  
// ---------------------------------------------------------------------------------------------------- 
  // skill challenge Acrobatics, same general format for all skill challenges
  public static LinkedList<Player> skillChallengeAcrobatics(LinkedList<Player> players, int threshold, int damage){
    // go through all members
    for (int c = 0; c < MEMBERS; c++){
      // until the player passes the challenge or dies
      while ((players.get(c).acrobatics + DiceRoll.randRoll() < threshold) && players.get(c).alive){
        // add to skill failures
        skillFailures += 1;
        // lose up to (damage) hp per failure
        players.get(c).hp -= DiceRoll.randRoll(damage);
        // do full suite of "is player still alive? can they surge? are they bloodied?"
        players.set(c, Misc.playerDamagePackage(players.get(c)));
        // update death statistics if dead
        if (!players.get(c).alive){
          deathCount += 1;
        }
      } // otherwise player is out of luck
      // player has passed
      if (players.get(c).alive){
        skillSuccesses += 1;
      }
    }
    // once all have passed or died
    return players;
  }
  
  // skill challenge Athletics
  public static LinkedList<Player> skillChallengeAthletics(LinkedList<Player> players, int threshold, int damage){
    // go through all members
    for (int c = 0; c < MEMBERS; c++){
      // until the player passes the challenge
      while ((players.get(c).athletics + DiceRoll.randRoll() < threshold) && players.get(c).alive){
        // add to skill failures
        skillFailures += 1;
        // lose up to (damage) hp per failure
        players.get(c).hp -= DiceRoll.randRoll(damage);
        // do full suite of "is player still alive? can they surge? are they bloodied?"
        players.set(c, Misc.playerDamagePackage(players.get(c)));
        // update death statistics if dead
        if (!players.get(c).alive){
          deathCount += 1;
        }
      } // otherwise player is out of luck
      // player has passed or died
      if (players.get(c).alive){
        skillSuccesses += 1;
      }
    }
    // once all have passed or died
    return players;
  }
  
  // skill challenge Endurance
  public static LinkedList<Player> skillChallengeEndurance(LinkedList<Player> players, int threshold, int damage){
    // go through all members
    for (int c = 0; c < MEMBERS; c++){
      // until the player passes the challenge
      while ((players.get(c).endurance + DiceRoll.randRoll() < threshold) && players.get(c).alive){
        // add to skill failures
        skillFailures += 1;
        // lose up to (damage) hp per failure
        players.get(c).hp -= DiceRoll.randRoll(damage);
        // do full suite of "is player still alive? can they surge? are they bloodied?"
        players.set(c, Misc.playerDamagePackage(players.get(c)));
        // update death statistics if dead
        if (!players.get(c).alive){
          deathCount += 1;
        }
      } 
      // otherwise player is out of luck
      // player has passed or died
      if (players.get(c).alive){
        skillSuccesses += 1;
      }
    }
    // once all have passed or died
    return players;
  }
  
  // skill challenge Perception, slightly different since it is a test of perception vs stealth
  // returns true if the hiders succeed
  public static Boolean skillChallengePerception(LinkedList<Enemy> spotters, LinkedList<Player> hiders){
    // how many hiders need to pass (60% in this case) ie 4 hiders, 5 spotters: 4 * 5 = 20 total checks * 0.6 = 12+ need to pass.
    double threshold = (hiders.size() * spotters.size() * 0.60);
    // number of hiders who have passed
    int counter = 0;
    // stores spotter rolls
    int[] spotterValues = new int[spotters.size()];
    // stores hider rolls
    int[] hiderValues = new int[hiders.size()];
    
    // get perception values
    for (int c = 0; c < spotters.size(); c++){
      spotterValues[c] = spotters.get(c).perception + DiceRoll.randRoll();
    }
    
    // get stealth values
    for (int c = 0; c < hiders.size(); c++){
      hiderValues[c] = hiders.get(c).stealth + DiceRoll.randRoll();
    }
    
    // may god help you
    for (int c = 0; c < spotterValues.length; c++){
      for (int s = 0; s < hiderValues.length; s++){
        // run the cards, dealer
        if (hiderValues[s] > spotterValues[c]){
          counter += 1;
        }
      }
    }
    // is somewhat weighted for tracking purposes, passing helps more than failing
    if (counter >= threshold){
      skillSuccesses += counter;
      return true;
    }else{
      skillFailures += hiders.size();
      return false;
    }
  }
  
  // skill challenge Diplomacy, no damage, just pass or fail, average needs to be past threshold
  public static boolean skillChallengeDiplomacy(LinkedList<Player> players, int threshold){
    int average = 0;
    // go through all members, add their weighted diplomacy roll
    for (int c = 0; c < MEMBERS; c++){
      average += ((players.get(c).diplomacy + DiceRoll.randRoll()) / players.size());
    }
    // once all are done
    if (average >= threshold){
      skillSuccesses += 1;
      return true;
    }
    skillFailures += 1;
    return false;
  }
  
  // create party of fresh recruits based on our combination function and current index
  public static LinkedList<Player> nextOrder(LinkedList<String> order, int index){
    // create fresh party list
    playerList = new LinkedList<Player>(); 
    // create fresh recruits
    Ardent ardent = new Ardent("Ardent");
    Assassin assassin = new Assassin("Assassin");
    Barbarian barbarian = new Barbarian("Barbarian");
    Cleric cleric = new Cleric("Cleric");
    Paladin paladin = new Paladin("Paladin");
    Ranger ranger = new Ranger("Ranger");
    Swordmage swordmage = new Swordmage("Swordmage");
    Wizard wizard = new Wizard("Wizard");
    
    for (int z = 0; z < MEMBERS; z++){
      // gets next character to be added to player list, char is weird and doesn't like starting at 0
      switch((int)order.get(index).charAt(z) - 48){
        case 0:
          playerList.add(ardent);
          break;
        case 1:
          playerList.add(assassin);
          break;
        case 2:
          playerList.add(barbarian);
          break;
        case 3:
          playerList.add(cleric);
          break;
        case 4:
          playerList.add(paladin);
          break;
        case 5:
          playerList.add(ranger);
          break;
        case 6:
          playerList.add(swordmage);
          break;
        case 7:
          playerList.add(wizard);
          break;
        default:
          break;
      }
    }
    return playerList;
  }
}