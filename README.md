# AskTheDragon
A D&amp;D 4E simulation made for a Discrete System Modeling/Simulation final in university

This is the first simulation I ever made and really taught me how much I enjoy statistics and programming. <br />

Main file is Simulation.java <br />

Introduction from final report: <br />

## Motivations and Simulated Problem Description ##
Dungeons and Dragons is a tabletop role-playing game originally released in 1974 by Wizards of the Coast. Notable is its dice system, where every action with risk must be accompanied by a 20 sided dice roll in order to declare it a success or failure. On average, a new edition with updated rules is released every 7 years, with the newest version being 5th edition. Our group has the most experience with 4th edition, and there is no pressing need to update versions, so we decided to focus on the 4th. A unique characteristic to 4th edition is that the combat system is rigid, turn based, and emphasizes use of a grid rather than abstractions for distance. This ruleset gave us the idea that we may be able to simulate the combat, but then the question became “What could we gain by it?” <br />

Dungeons and Dragons 4th edition was released in 2008, and due to its age, there are an endless number of guides online created by experienced users for what abilities to get, what feats to get, and what items to buy in order to create a character who is optimized for combat. To find an optimal character configuration, all it takes is searching “(Class) guide D&D 4E” with an existing character class to get instant answers. Even with this documentation, however, there are no tools online to figure out how well these characters work together – and with how party-centric Dungeons and Dragons is, this seemed like a great problem to investigate with a simulation. <br />

The simulation we created simulates a party of 5 Dungeons and Dragons characters traveling to a decrepit castle with the intent on defeating the Dragon King. The background of this quest could be that the players heard a rumor that the Dragon King has a large trove of treasure, or that the players are selflessly volunteering to protect the countryside from his onslaught. The exact reason, and enemy, is arbitrary, but it seems in-line with what a Dungeons and Dragons party would be doing. <br />

Along the way, the party fights various enemies, such as giant spiders, and do a variety of skill challenges such as jumping rock-to-rock across a river of lava. Failing combat leads to death, failing skill challenges leads to penalties up to and including death. <br />

The goal of the simulation is to find the party that is most likely to defeat the Dragon King. <br />

Statistics we will collect include: <br />
* Win rate: How often the party wins
* Kills: How many enemies the party kills on an average run
* Heal Total: How much healing is done in-combat on average (Average health = 45)
* Unconscious: How many times a party members becomes unconscious on average
* Deaths: How many times a party member dies on average
* Skill Passes: How many times an individual succeeded at a skill challenge
* Skill Failures: How many times an individual failed at a skill challenge
* Surge Usage Ratio: How many healing surges used as a % of total party surges
