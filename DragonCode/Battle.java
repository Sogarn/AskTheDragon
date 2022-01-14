// battle function
// last updated 5-1-15
// Core function starts around line 67

import java.util.*;
public class Battle{
  static int killCount, unconsciousCount, deathCount, healTotal; // stores statistics that will be added on to player list at the end
  static boolean playersAlive, enemiesAlive; // end condition
  static Player curPlayer; // for turns, refers to current player
  static Enemy curEnemy; // for turns, refers to current enemy
  static int index; // for misc index things
  static Attack preferredAttack; // current preferred attack to use
  static int targetIndex; // potential target
  static Player pTarget; // potential target
  static Enemy eTarget; // potential target
  static int randWeight; // weight for selecting tanks
  static int diceSet = 10; // dice we are rolling for weights
  static int weightSet = 7; // what we are comparing to (this or greater = true)
  
  public static LinkedList<Player> battle(LinkedList<Player> players, LinkedList<Enemy> enemies){
    killCount = 0;
    unconsciousCount = 0;
    deathCount = 0;
    healTotal = 0;
    playersAlive = true;
    enemiesAlive = true;
    int round = 0;
    int playerCounter; // how many players have gone
    int enemyCounter; // how many enemies have gone
    int max; // for comparisons
    int min; // for comparisons
    int secondaryTarget; // for extra indices
    boolean attackPicked = false;
    String currentRoundString; // current round as a string
    
    int rows = 15; // default grid rows
    int columns = 15; // default columns
    
    // store coordinate location in proper spot and in [row,column]
    int[][] pLocations = new int[players.size()][2];
    int[][] eLocations = new int[enemies.size()][2];
    
    // figure out everyone's initiatives and sort
    for (int bt = 0; bt < players.size(); bt++){
      players.get(bt).addInitiative();
    }
    Collections.sort(players);
    
    for (int bt = 0; bt < enemies.size(); bt++){
      enemies.get(bt).addInitiative();
    }
    Collections.sort(enemies);
    
// pre fight   
// ----------------------------------------------------------------------------------------------------   
// fight
    
    // set turn order as one big list
    LinkedList<Character> turnOrder = new LinkedList<Character>();
    for (int kt = 0; kt < players.size(); kt++){
      turnOrder.add(players.get(kt));
    }
    for (int kt = 0; kt < enemies.size(); kt++){
      turnOrder.add(enemies.get(kt));
    }
    Collections.sort(turnOrder);
//----------    
    // loop through fight
    while (playersAlive && enemiesAlive){
      round += 1; // increment counter by 1
      currentRoundString = Integer.toString(round);
      playerCounter = 0; // how many players have gone
      enemyCounter = 0; // how many enemies have gone
      
      // go through turn order list
      for (int trn = 0; trn < turnOrder.size(); trn++){
        if (playersAlive && enemiesAlive){ // test to see if we should skadoodle
          Character test = turnOrder.get(trn); // what character should be getting their turn right now
          max = 0; // for heals (find highest missing health)
          min = 0; // for damage (find lowest health)
          preferredAttack = new Attack(); // what attack we will end up using
          pTarget = new Player(); // what target we'll end up selecting (if player)
          eTarget = new Enemy(); // what target we'll end up selecting (if enemy)
          secondaryTarget = 0; // for index tracking of secondary stuff
          targetIndex = 0; // index of target so we can update player/enemy list
          attackPicked = false; // lets us know if we have an attack in mind
          
//----------         
          // if player matches current turn taker, work with them
          if (turnOrder.get(trn).identifier == 1){
            // get next player from list
            curPlayer = players.get(playerCounter);
            curPlayer.newTurn();
            if (curPlayer.alive && !curPlayer.unconscious){ // if they're alive and can take a turn
              
              // all players try to second wind if they are somewhere between 30% and 70% of their max hp
              // and have enough surges and haven't used their second wind this fight
              int minCompareHP = (int)(curPlayer.maxHP * 0.2);
              int maxCompareHP = (int)(curPlayer.maxHP * 0.7);
              if (curPlayer.missingHP() > minCompareHP && curPlayer.missingHP() < maxCompareHP &&
                  curPlayer.surgeCount > 0 && !curPlayer.secondWind.used){
                preferredAttack = curPlayer.secondWind;
                pTarget = curPlayer;
                targetIndex = playerCounter;
                curPlayer.surgeCount -= 1;
                curPlayer.secondWind.used = true;
                
                // also gives +2 to all defenses, buffs don't do debilitated
                curPlayer.debuffed = true;
                curPlayer.ac += 2;
                curPlayer.fortitude += 2;
                curPlayer.reflex += 2;
                curPlayer.will += 2;
              }else{
                switch(curPlayer.classID){
                  case "D": // ardent
                    /*
                     - Ardent Surge *
                     3 Implanted Suggestion 
                     2 Impatient Strike 
                     1 Energizing Strike *
                     0 Demoralizing Strike *
                     */
                    
                    // test ardent Surge, heal whoever is missing the most hp if we have charges
                    if((curPlayer.ardentSurge.charges > 0) && (curPlayer.surgeCount > 0)){
                    // must heal at least 20, can heal anybody who is alive
                    targetIndex = healParty(players, 20, false);
                    if (targetIndex > 0){ // healParty returns -1 if it can't find a good target
                      attackPicked = true;
                      // if heal target is the heal user
                      if (players.get(targetIndex).name.equals(curPlayer.name)){
                        pTarget = curPlayer; // since curPlayer is only a dummy variable, need to target it
                      }else{ // otherwise pick target normally
                        pTarget = players.get(targetIndex);
                      }
                      
                      preferredAttack = curPlayer.ardentSurge;
                      curPlayer.ardentSurge.charges -= 1; // consume an ability charge
                      curPlayer.surgeCount -= 1; // consume a healing surge
                    }
                  }
                    
                    // use energizing strike if somebody bloodied
                    if (!attackPicked && Misc.partyBloodied(players)){
                      preferredAttack = curPlayer.abilities.get(1);
                      
                      // attack enemy with lowest hp
                      targetIndex = minEnemy(enemies);
                      eTarget = enemies.get(targetIndex);
                      
                      // give temp hp to player who is alive, conscious, and has the least health
                      min = players.get(0).hp;
                      for (int ik = 0; ik < players.size(); ik++){
                        if (players.get(ik).hp < min && players.get(ik).alive && !players.get(ik).unconscious){
                          min = players.get(ik).hp;
                          secondaryTarget = ik;
                        }
                      }
                      players.get(secondaryTarget).tempHP += 7;
                      if (players.get(secondaryTarget).name.compareToIgnoreCase(curPlayer.name) == 0){ // check target isn't self
                        curPlayer.tempHP += 7;
                        healTotal += 7; // update "healing"
                      }
                      attackPicked = true;
                    }
                    
                    // default demoralizing strike
                    if(!attackPicked){
                      preferredAttack = curPlayer.abilities.get(0);
                      targetIndex = minEnemy(enemies);
                      eTarget = enemies.get(targetIndex);
                      
                      // debuff target with -2 to all defenses
                      eTarget.ac -= 2;
                      eTarget.fortitude -= 2;
                      eTarget.reflex -= 2;
                      eTarget.will -= 2;
                      eTarget.debilitated += 1;
                      eTarget.debuffed = true;
                      attackPicked = true;
                    }
                    break;
                    
                  case "A": // assassin
                    /*
                     4 Targeted For Death 
                     3 Cloaking Mist *
                     2 Smothering Shadow *
                     1 Shadow Storm *
                     0 Executioner's Noose
                     */
                    
                    // do cloaking mist if haven't done it yet
                    if (!curPlayer.abilities.get(3).used){
                    preferredAttack = curPlayer.abilities.get(3);
                    // attack enemy with lowest hp
                    targetIndex = minEnemy(enemies);
                    eTarget = enemies.get(targetIndex);
                    
                    // lock encounter ability for fight
                    curPlayer.abilities.get(3).used = true;
                    curPlayer.locked[3] = true;
                    attackPicked = true;
                  }
                    
                    // do smothering shadow if haven't done it yet
                    if (!curPlayer.abilities.get(2).used && !attackPicked){
                      
                      preferredAttack = curPlayer.abilities.get(2);
                      
                      // attack enemy with lowest hp
                      targetIndex = minEnemy(enemies);
                      eTarget = enemies.get(targetIndex);
                      
                      // lock encounter ability for fight
                      curPlayer.abilities.get(2).used = true;
                      curPlayer.locked[2] = true;
                      attackPicked = true;
                    }
                    
                    // do shadow storm by default
                    if (!attackPicked){
                      
                      preferredAttack = curPlayer.abilities.get(1);
                      
                      // attack enemy with lowest hp
                      targetIndex = minEnemy(enemies);
                      eTarget = enemies.get(targetIndex);
                      
                      attackPicked = true;
                    }
                    
                    break;
                  case "B": // barbarian
                    /*
                     3 Swift Panther Rage
                     2 Daring Charge *
                     1 Avalanche Strike *
                     0 Howling Strike *
                     */
                    
                    // do daring charge if haven't yet
                    if (!curPlayer.abilities.get(2).used){
                    
                    preferredAttack = curPlayer.abilities.get(2);
                    
                    // attack enemy with lowest hp
                    targetIndex = minEnemy(enemies);
                    eTarget = enemies.get(targetIndex);
                    
                    // lock encounter ability for fight
                    curPlayer.abilities.get(2).used = true;
                    curPlayer.locked[2] = true;
                    attackPicked = true;
                  }
                    
                    // do avalanche strike if haven't
                    if (!curPlayer.abilities.get(1).used && !attackPicked){
                      
                      preferredAttack = curPlayer.abilities.get(1);
                      
                      // attack enemy with lowest hp
                      targetIndex = minEnemy(enemies);
                      eTarget = enemies.get(targetIndex);
                      
                      // lock encounter ability for fight
                      curPlayer.abilities.get(1).used = true;
                      curPlayer.locked[1] = true;
                      attackPicked = true;
                    }
                    
                    // do howling strike by default
                    if (!attackPicked){
                      
                      preferredAttack = curPlayer.abilities.get(0);
                      
                      // attack enemy with lowest hp
                      targetIndex = minEnemy(enemies);
                      eTarget = enemies.get(targetIndex);
                      attackPicked = true;
                    }
                    break;
                    
                  case "C": // cleric
                    /*
                     - Healing Word *
                     4 Moment of Glory 
                     3 Hammering Wind 
                     2 Sun Burst *
                     1 Singing Strike *
                     0 Brand of the Sun *
                     */
                    
                    // try to heal the buds
                    if ((curPlayer.healingWord.charges > 0) && (curPlayer.surgeCount > 0)){
                    // must heal at least 20, can heal anybody who is alive
                    targetIndex = healParty(players, 20, false);
                    if (targetIndex > 0){ // healParty returns -1 if it can't find a good target
                      attackPicked = true;
                      // if heal target is the heal user
                      if (players.get(targetIndex).name.equals(curPlayer.name)){
                        pTarget = curPlayer; // since curPlayer is only a dummy variable, need to target it
                      }else{ // otherwise pick target normally
                        pTarget = players.get(targetIndex);
                      }
                      
                      preferredAttack = curPlayer.healingWord;
                      curPlayer.healingWord.charges -= 1; // consume an ability charge
                      curPlayer.surgeCount -= 1; // consume a healing surge
                    }
                  } 
                    
                    // otherwise do sun burst once per encounter
                    if (!curPlayer.abilities.get(2).used && !attackPicked){
                      
                      preferredAttack = curPlayer.abilities.get(2);
                      
                      // attack enemy with lowest hp
                      targetIndex = minEnemy(enemies);
                      eTarget = enemies.get(targetIndex);
                      
                      // give party temp health
                      for (int ik = 0; ik < players.size(); ik++){
                        players.get(ik).tempHP += 5;
                        curPlayer.tempHP += 5; // has to include self
                      }
                      healTotal += 5 * players.size(); // update "healing"
                      
                      // update used and locked
                      curPlayer.abilities.get(2).used = true;
                      curPlayer.locked[2] = true;
                      attackPicked = true;
                    }
                    
                    // otherwise if people are debuffed, do brand of the sun
                    if(!attackPicked && Misc.partyDebuffed(players)){
                      
                      preferredAttack = curPlayer.abilities.get(0);
                      
                      // attack enemy with lowest hp
                      targetIndex = minEnemy(enemies);
                      eTarget = enemies.get(targetIndex);
                      
                      // help out debilitation
                      players.get(Misc.findDebuffed(players)).debilitated -= 1;
                      attackPicked = true;
                    }
                    
                    // default singing strike
                    if(!attackPicked){
                      
                      preferredAttack = curPlayer.abilities.get(1);
                      
                      // attack enemy with lowest hp
                      targetIndex = minEnemy(enemies);
                      eTarget = enemies.get(targetIndex);
                      
                      // debuff target with -2 to attack
                      eTarget.atkPenalty += 2;
                      eTarget.debilitated += 1;
                      eTarget.debuffed = true;
                      attackPicked = true;
                    }
                    break;
                    
                  case "P": // paladin
                    /*
                     - Lay on Hands *
                     4 Majestic Halo 
                     3 Strength From Valor 
                     2 Righteous Smite *
                     1 Valorous Smite *
                     0 Virtuous Strike *
                     */
                    // heal allies, but they do not get more charges between encounters so more stingy. Only heal to revive.
                    if ((curPlayer.layOnHands.charges > 0) && (curPlayer.surgeCount > 0)){
                    // heal unconscious targets only
                    targetIndex = healParty(players, 0, true);
                    if (targetIndex > 0){ // healParty returns -1 if it can't find a good target
                      attackPicked = true;
                      pTarget = players.get(targetIndex);
                      
                      preferredAttack = curPlayer.layOnHands;
                      curPlayer.layOnHands.charges -= 1; // consume an ability charge
                      curPlayer.surgeCount -= 1; // consume a healing surge
                    }
                  }
                    
                    // otherwise do righteous smite once per encounter
                    if (!curPlayer.abilities.get(2).used && !attackPicked){
                      
                      preferredAttack = curPlayer.abilities.get(2);
                      
                      // attack enemy with lowest hp
                      targetIndex = minEnemy(enemies);
                      eTarget = enemies.get(targetIndex);
                      
                      // give party temp health
                      for (int ik = 0; ik < players.size(); ik++){
                        players.get(ik).tempHP += 8;
                        curPlayer.tempHP += 8; // has to include self
                      }
                      healTotal += 8 * players.size(); // update "healing"
                      
                      // update used and locked
                      curPlayer.abilities.get(2).used = true;
                      curPlayer.locked[2] = true;
                      attackPicked = true;
                    }
                    
                    // do valorous smite if haven't
                    if (!curPlayer.abilities.get(1).used && !attackPicked){
                      
                      preferredAttack = curPlayer.abilities.get(1);
                      
                      // attack enemy with lowest hp
                      targetIndex = minEnemy(enemies);
                      eTarget = enemies.get(targetIndex);
                      
                      // update used and locked
                      curPlayer.abilities.get(1).used = true;
                      curPlayer.locked[1] = true;
                      attackPicked = true;
                    }
                    // default virtuous strike
                    if (!attackPicked){
                      
                      preferredAttack = curPlayer.abilities.get(0);
                      
                      // attack enemy with lowest hp
                      targetIndex = minEnemy(enemies);
                      eTarget = enemies.get(targetIndex);
                    }
                    break;
                    
                  case "R": // ranger
                    /*
                     3 Split The Tree 
                     2 Shadow Wasp Strike *
                     1 Two Fanged Strike *
                     0 Twin Strike *
                     */
                    
                    // do shadow wasp strike if haven't yet
                    if (!curPlayer.abilities.get(2).used){
                    
                    preferredAttack = curPlayer.abilities.get(2);
                    
                    // attack enemy with lowest hp
                    targetIndex = minEnemy(enemies);
                    eTarget = enemies.get(targetIndex);
                    
                    // update used and locked
                    curPlayer.abilities.get(2).used = true;
                    curPlayer.locked[2] = true;
                    attackPicked = true;
                  }
                    
                    // do two fanged strike if haven't
                    if (!curPlayer.abilities.get(1).used && !attackPicked){
                      
                      preferredAttack = curPlayer.abilities.get(1);
                      
                      // attack enemy with lowest hp
                      targetIndex = minEnemy(enemies);
                      eTarget = enemies.get(targetIndex);
                      
                      // update used and locked
                      curPlayer.abilities.get(1).used = true;
                      curPlayer.locked[1] = true;
                      attackPicked = true;
                    }
                    
                    // do twin strike by default
                    if (!attackPicked){
                      
                      preferredAttack = curPlayer.abilities.get(0);
                      
                      // attack enemy with lowest hp
                      targetIndex = minEnemy(enemies);
                      eTarget = enemies.get(targetIndex);
                      
                      attackPicked = true;
                    }
                    break;
                    
                  case "S": // swordmage
                    /*
                     4 Dimensional Thunder 
                     3 Dimensional Vortex *
                     2 Sword of Sigils *
                     1 Sword Burst 
                     0 Booming Blade *
                     */
                    
                    // do dimensional vortex if possible
                    if (!curPlayer.abilities.get(3).used){
                    
                    preferredAttack = curPlayer.abilities.get(3);
                    
                    // attack enemy with lowest hp
                    targetIndex = minEnemy(enemies);
                    eTarget = enemies.get(targetIndex);
                    
                    // updated used and locked
                    curPlayer.abilities.get(3).used = true;
                    curPlayer.locked[3] = true;
                    attackPicked = true;
                  }
                    
                    // do sword of sigils if it hasn't been used
                    if (!curPlayer.abilities.get(2).used && !attackPicked){
                      
                      preferredAttack = curPlayer.abilities.get(2);
                      
                      // attack enemy with lowest hp
                      targetIndex = minEnemy(enemies);
                      eTarget = enemies.get(targetIndex);
                      
                      // updated used and locked
                      curPlayer.abilities.get(2).used = true;
                      curPlayer.locked[2] = true;
                      attackPicked = true;
                    }
                    
                    // do booming blade by default
                    if (!attackPicked){
                      
                      preferredAttack = curPlayer.abilities.get(0);
                      
                      // attack enemy with lowest hp
                      targetIndex = minEnemy(enemies);
                      eTarget = enemies.get(targetIndex);
                      
                      attackPicked = true;
                    }
                    break;
                    
                  case "Z": // wizard
                    /*
                     8 Magic Missile 
                     7 Fountain of Flame 
                     6 Flaming Sphere 
                     5 Shock Sphere 
                     4 Fire Shroud *
                     3 Force Orb *
                     2 Burning Hands *
                     1 Freezing Burst
                     0 Arc Lightning * 
                     */
                    // if any enemy has 8 or less health, do magic missile
                    if (enemies.get(minEnemy(enemies)).hp <= 8){
                    
                    // attack enemy with lowest hp
                    targetIndex = minEnemy(enemies);
                    eTarget = enemies.get(targetIndex);
                    
                    attackPicked = true;
                  }
                    
                    // do fire shroud if possible
                    if (!curPlayer.abilities.get(4).used && !attackPicked){
                      
                      preferredAttack = curPlayer.abilities.get(4);
                      
                      // attack enemy with lowest hp
                      targetIndex = minEnemy(enemies);
                      eTarget = enemies.get(targetIndex);
                      
                      // update used and locked
                      curPlayer.abilities.get(4).used = true;
                      curPlayer.locked[4] = true;
                      attackPicked = true;
                    }
                    // do force orb if possible
                    if (!curPlayer.abilities.get(3).used && !attackPicked){
                      
                      preferredAttack = curPlayer.abilities.get(3);
                      
                      // attack enemy with lowest hp
                      targetIndex = minEnemy(enemies);
                      eTarget = enemies.get(targetIndex);
                      
                      // updated used and locked
                      curPlayer.abilities.get(3).used = true;
                      curPlayer.locked[3] = true;
                      attackPicked = true;
                    }
                    
                    // do burning hands if possible
                    if (!curPlayer.abilities.get(2).used && !attackPicked){
                      
                      preferredAttack = curPlayer.abilities.get(2);
                      
                      // attack enemy with lowest hp
                      targetIndex = minEnemy(enemies);
                      eTarget = enemies.get(targetIndex);
                      
                      // update used and locked
                      curPlayer.abilities.get(2).used = true;
                      curPlayer.locked[2] = true;
                      attackPicked = true;
                    }
                    
                    // do arc lightning by default
                    if (!attackPicked){
                      
                      preferredAttack = curPlayer.abilities.get(0);
                      
                      // attack enemy with lowest hp
                      targetIndex = minEnemy(enemies);
                      eTarget = enemies.get(targetIndex);
                      
                      attackPicked = true;
                    }
                    break;
                }
              }
              // do updates to stats and such depending on what happened
              if (preferredAttack.help){ // if a heal
                int healAmt = preferredAttack.damage();
                if (pTarget.unconscious){ // if target is unconscious, set their hp to the heal amount
                  pTarget.hp = healAmt; // set hp to heal amount
                }else{ // otherwise
                  pTarget.hp += healAmt;
                  if (pTarget.hp > pTarget.maxHP){ // can't exceed max hp
                    pTarget.hp = pTarget.maxHP;
                  }
                }
                healTotal += healAmt; // statistics
                pTarget = Misc.damagePackage(pTarget); // alive? bloodied?
                players.set(targetIndex,pTarget);
              }else{  //alternative is not being a heal, attack numHits times
                for (int ko = 1; ko <= preferredAttack.numHits; ko++){
                  switch(preferredAttack.attack(eTarget)){// hit status
                    case 0: // if it misses
                      if (preferredAttack.daily){ // dailies still do half damage
                      eTarget.hp -= (preferredAttack.dailyMiss() - eTarget.tempHP);
                      eTarget.tempHP = 0;
                    }
                      break;
                    case 1: // regular hit, tempHP absorbs damage if possible
                      eTarget.hp -= (preferredAttack.damage() - eTarget.tempHP);
                      eTarget.tempHP = 0;
                      break;
                    case 2: // crit
                      eTarget.hp -= (preferredAttack.critDamage() - eTarget.tempHP);
                      eTarget.tempHP = 0;
                      break;
                  }
                }
                // update alive / bloodied
                if(eTarget.hp <= 0){
                  eTarget.aliveCheck();
                  killCount += 1;
                }
                eTarget.bloodied();
                
                enemies.set(targetIndex,eTarget);
              }
              
              // update player who did the turn
              players.set(playerCounter,curPlayer);
              playerCounter += 1;
              
            }
            //if unconscious and can't really participate yet or just straight dead
            else if (curPlayer.unconscious && !curPlayer.stabilized && curPlayer.alive){ // stabilization not yet implemented
              // roll!
              int roll = DiceRoll.randRoll();
              // if they got healed to a positive value earlier or rolled a natural 20
              if ((curPlayer.hp > 0) || (roll >= 20)){
                curPlayer.unconscious = false;
                curPlayer.hp = curPlayer.surgeValue;
              }else if (roll < 10){ // fails death saving throw
                curPlayer.deathRoll += 1;
              }
              if (curPlayer.deathRoll >= 3){ // dies if too many failures
                curPlayer.alive = false;
                curPlayer.bloodied = false;
                deathCount += 1;
              }
              players.set(playerCounter,curPlayer);
              playerCounter += 1;
            }
          }
//----------        
          //if enemy matches current turn taker, work with them
          if (turnOrder.get(trn).identifier == 2){
            
            // get next enemy from list
            curEnemy = enemies.get(enemyCounter);
            curEnemy.newTurn();
            
            if (curEnemy.alive){ // if they're alive and can take a turn
              switch (curEnemy.classID){
                case "DK": // dragon king
                  /* 1 Flame Breath
                   * 0 Basic Melee
                   */
                  // summon a drakeling
                  Enemy temp = new Drakeling(("Drakeling" + currentRoundString));
                  enemies.add(temp);
                  turnOrder.add(temp);
                  
                  // flame breath
                  if (!curEnemy.abilities.get(1).used){
                    
                    preferredAttack = curEnemy.abilities.get(1);
                    
                    // find alive player with least health
                    targetIndex = minPlayer(players);
                    pTarget = players.get(targetIndex);
                    
                    // mark down that we have used it
                    curEnemy.abilities.get(1).used = true;
                    curEnemy.locked[1] = true;
                    attackPicked = true;
                  }
                  
                  // basic attack from a savage being, will devestate unconscious enemies
                  if (!attackPicked){
                    
                    preferredAttack = curEnemy.abilities.get(0);
                    
                    // find alive player with least health
                    targetIndex = minPlayer(players);
                    pTarget = players.get(targetIndex);
                  }
                  break;
                  
                case "K":
                  // drakelings become more furious if the target is bloodied, target lowest health unless tank check
                  preferredAttack = curEnemy.abilities.get(0);
                  
                  // find random player target (weighted toward tanks);
                  targetIndex = randPlayerTarget(players);
                  pTarget = players.get(targetIndex); 
                  
                  if (pTarget.bloodied){ // if they're bloodied, get way stronger
                    curEnemy.debuffed = true;
                    curEnemy.debilitated += 1;
                    curEnemy.abilities.get(0).attackBonus *= 2;
                    curEnemy.abilities.get(0).bonusDamage *= 2;
                    curEnemy.abilities.get(0).numHits *= 2;
                  }
                  attackPicked = true;
                  preferredAttack = curEnemy.abilities.get(0);
                  break;
                  
                case "H": // hydra, difference from default is that # of attacks goes down as health goes down, flat damage up
                  preferredAttack = curEnemy.abilities.get(0);
                  
                  // find random player target (weighted toward tanks);
                  targetIndex = randPlayerTarget(players);
                  pTarget = players.get(targetIndex); 
                  
                  // every 30 hp missing decreases heads by 1
                  curEnemy.abilities.get(0).numHits = (1 + (int)(curEnemy.hp / 30)); 
                  // every 30 hp missing increases flat damage by 1 / hit
                  curEnemy.abilities.get(0).bonusDamage = (int)(curEnemy.maxHP / 30);
                  break;
                  
                case "WH": // witch, basic attack, but gives massive debuffs that stack
                  preferredAttack = curEnemy.abilities.get(0);
                  
                  // find random player target (weighted toward tanks);
                  targetIndex = randPlayerTarget(players);
                  pTarget = players.get(targetIndex); 
                  
                  // do debilitating effects
                  pTarget.debuffed = true;
                  pTarget.debilitated += 1;
                  pTarget.will -= 2;
                  pTarget.atkPenalty += 2;
                  pTarget.dmgPenalty += 2;
                  break;
                  
                default: // anything that's nonspecial basically
                  //basic attack, attacks random target, weighted toward tanks (paladin & swordmage)
                  preferredAttack = curEnemy.abilities.get(0);
                  
                  targetIndex = randPlayerTarget(players);
                  pTarget = players.get(targetIndex);
                  break;
              }
              
              // attack as many times as attack states
              for (int ko = 1; ko <= preferredAttack.numHits; ko++){
                int attackAttempt; // since players can sometimes be unconscious, have to seperate
                if (pTarget.unconscious){ // autocrit
                  attackAttempt = 2;
                }else{
                  attackAttempt = preferredAttack.attack(pTarget); // regular attack
                }
                switch(attackAttempt){// hit status
                  case 0: // if it misses
                    if (preferredAttack.daily){ // dailies still do half damage
                    pTarget.hp -= (preferredAttack.dailyMiss() - pTarget.tempHP);
                    pTarget.tempHP = 0;
                  }
                    break;
                  case 1: // regular hit, tempHP absorbs damage if possible
                    pTarget.hp -= (preferredAttack.damage() - pTarget.tempHP);
                    pTarget.tempHP = 0;
                    break;
                  case 2: // crit
                    pTarget.hp -= (preferredAttack.critDamage() - pTarget.tempHP);
                    pTarget.tempHP = 0;
                    break;
                }
              }
              // instakill when health is (- bloodied value) aka (health missing > maxHP + bloodiedvalue)
              if (pTarget.missingHP() > (pTarget.maxHP + pTarget.bloodiedValue)){
                deathCount += 1;
                pTarget.alive = false;
                pTarget.bloodied = false;
              }else if(pTarget.hp < 0){ // maybe just unconscious
                unconsciousCount += 1;
                pTarget.unconscious = true;
              }
              players.set(targetIndex,pTarget);
            }
            
            // update enemy who did the turn
            enemies.set(enemyCounter,curEnemy);
            enemyCounter += 1;
          }
          
          // test if all players are dead
          if (Misc.tpkCheck(players)){
            playersAlive = false;
          }
          // test if all enemies are dead
          if (enemyLossCheck(enemies)){
            enemiesAlive = false;
          }
        }
      }
    }
// ----------------------------------------------------------------------------------------------------
// post fight
    players = sortName(players);
// remember to do turnStart at start of each alive person's turn. Also returns alphabetical list with statistics at end
    Player statGuy = new Player(killCount,unconsciousCount,deathCount, healTotal);
    players.add(statGuy);
    return players;
  }
  
// ----------------------------------------------------------------------------------------------------
// target function
// finds index of enemy with min hp:
  public static int minEnemy (LinkedList<Enemy> enemies){
    Enemy tempPrefTarget = new Enemy();
    tempPrefTarget = enemies.get(0);
    int tempMin = enemies.get(0).hp;
    int tempIndex = 0;
    for (int opi = 0; opi < enemies.size(); opi++){
      if ((enemies.get(opi).hp <= tempMin && enemies.get(opi).alive) || !tempPrefTarget.alive){
        tempPrefTarget = enemies.get(opi);
        tempMin = enemies.get(opi).hp;
        targetIndex = opi;
      }
    }
    return targetIndex;
  }
  
// finds index of player with min hp:
  public static int minPlayer (LinkedList<Player> players){
    Player tempPrefTarget = new Player();
    tempPrefTarget = players.get(0);
    int tempMin = players.get(0).hp;
    int tempIndex = 0;
    for (int opi = 0; opi < players.size(); opi++){
      if ((players.get(opi).hp <= tempMin && players.get(opi).alive) || !tempPrefTarget.alive){
        tempPrefTarget = players.get(opi);
        tempMin = players.get(opi).hp;
        targetIndex = opi;
      }
    }
    return targetIndex;
  }
  
// finds random player to attack, weighted to attack tanks
  public static int randPlayerTarget (LinkedList<Player> players){
    int tempTargetIndex;
    
    // check to see if we are attacking tanks
    randWeight = DiceRoll.randRoll(diceSet);
    
    // if we are, call to see what tank we are attacking
    if (randWeight >= weightSet){
      tempTargetIndex = highTank(players);
    }else{ // otherwise, do regular random roll
      tempTargetIndex = randPlayerTargetNoTank(players);
    }
    
    return tempTargetIndex;
  }
  
// attacks tanks with highest health, otherwise does default random roll
  public static int highTank (LinkedList<Player> players){
    // create baselines
    int tankIndex = 0;
    int curMax = 0;
    boolean found = false; // if stays false, default to regular random roll
    // loop through players
    for (int hiu = 0; hiu < players.size(); hiu++){
      // if tank and the tank is alive
      if (players.get(hiu).role == 1 && players.get(hiu).alive){
        // target tank with highest health
        if (players.get(hiu).hp > curMax){
          curMax = players.get(hiu).hp;
          tankIndex = hiu;
          // set comparison if found already true
          if (found){
            curMax = players.get(hiu).hp;
          }
        }
        found = true;
      }
    }
    
    if (!found){ // if we couldn't find a tank, regular roll
      tankIndex = randPlayerTargetNoTank(players);
    }
    return tankIndex;
  }
  
  // finds a random player target index without checking for tanks
  public static int randPlayerTargetNoTank(LinkedList<Player> players){
    
    // create list of alive players
    LinkedList<Player> possibleTargets = Misc.alivePlayers(players);
    
    // roll a random number to find target
    int tempTargetIndex = DiceRoll.randRoll(possibleTargets.size() - 1);
    // in case target tries to attack something again when party already dead, skip loop
    if (possibleTargets.size() != 0){
      // store name of target from alive list
      String nameCompare = possibleTargets.get(tempTargetIndex).name;
      
      // match alive player index to index on list of players
      for(int hlk = 0; hlk < players.size(); hlk++){
        if (players.get(hlk).name.equals(nameCompare)){
          tempTargetIndex = hlk;
        }
      }
    }
    return tempTargetIndex;
  }
  
// tries to heal someone in party, healthRequirement is how much health should be missing
  public static int healParty (LinkedList<Player> players, int healthRequirement, boolean onlyUnconscious){
    int healIndex = -1; // returns -1 if no suitable target
    int maxHeal = 0; // choose target missing the most health
    for (int ilk = 0; ilk < players.size(); ilk++){
      // if heal should only be used on unconscious, set up healthRequirement appropriately
      if (onlyUnconscious){
        healthRequirement = players.get(ilk).maxHP;
      }
      // if player missing enough hp, missing most hp, and alive
      if ((players.get(ilk).missingHP() >= healthRequirement) && 
          (players.get(ilk).missingHP() > maxHeal) && (players.get(ilk).alive)){
        
        healIndex = ilk;
        maxHeal = players.get(ilk).missingHP();
      }
    }
    return healIndex;
  }
  
// check if enemies are dead
  public static boolean enemyLossCheck(LinkedList<Enemy> enemies){
    int counter = 0;
    for (int t = 0; t < enemies.size(); t++){
      if (enemies.get(t).alive){ // if someone alive, add to counter
        counter += 1;
      }
    }
    if (counter == 0){ // if nobody alive, return true
      return true;
    }
    return false; // otherwise, return false
  }
  
// re-sort everyone afterward
  public static LinkedList<Player> sortName(LinkedList<Player> players){
    LinkedList<Player> temp = new LinkedList<Player>();
    int length = players.size(); // max player size
    int compareLength = players.size(); // current player size
    Player currentMin = players.get(0);
    int minIndex = 0;
    while (temp.size() < length){ // until we have enough entries
      for (int st = 0; st < compareLength; st++){ // loop through players
        if ((players.get(st).name.compareToIgnoreCase(currentMin.name)) < 0){
          // earlier alphabetical name becomes new min
          currentMin = players.get(st);
          minIndex = st;
        }
      }
      // add current min to temp, removes it from players
      compareLength -= 1;
      temp.add(players.get(minIndex));
      players.remove(minIndex);
      // get new base min if applicable
      if (players.size() > 0){
        currentMin = players.get(0);
        minIndex = 0;
      }
    }
    return temp;
  }
}