package main.java.com.nagarro.remotelearning;

import classes.Hero;
import classes.Monster;

public class Game {
	public static void main(String... args) {
		Hero hero = new Hero("marcel");
		hero.printStatus();
		Monster rockGolem = new Monster("rockGolem", true);
		rockGolem.printStatus();
		Monster randomHoboGuy = new Monster("randomHoboGuy", false);
		randomHoboGuy.printStatus();
		System.out.println();

		hero.attack(rockGolem);
		hero.printStatus();
		rockGolem.printStatus();
		randomHoboGuy.printStatus();
		System.out.println();

		rockGolem.attack(hero);
		hero.printStatus();
		rockGolem.printStatus();
		randomHoboGuy.printStatus();
		System.out.println();

		randomHoboGuy.attack(hero);
		hero.printStatus();
		rockGolem.printStatus();
		randomHoboGuy.printStatus();
		System.out.println();

		hero.channelHealthNextRound();
		hero.checkStatusHero();
		hero.printStatus();
		rockGolem.printStatus();
		randomHoboGuy.printStatus();
		System.out.println();

		rockGolem.channelDefense();
		rockGolem.checkStatusMonster();
		hero.printStatus();
		rockGolem.printStatus();
		randomHoboGuy.printStatus();
		System.out.println();

		randomHoboGuy.attackWithMoreStrength(hero);
		randomHoboGuy.checkStatusMonster();
		hero.printStatus();
		rockGolem.printStatus();
		randomHoboGuy.printStatus();
		System.out.println();

		hero.aoeAttack(GameUtils.makeArrayList(rockGolem, randomHoboGuy));
		hero.printStatus();
		rockGolem.printStatus();
		randomHoboGuy.printStatus();

		// do {
		// check for each party's status (buffs, debuffs, etc)
		// hero's turn
		// monster's turn

		// // check if all parties are alive
		// if (hero.getHp() <= 0 || monsters.size() == 0) {
		// 	allPartiesAlive = false;
		// }
		// }while(allPartiesAlive);
	}
}

// - hero attributes: hp, strength, defense, xp, name
// - enemies attributes: hp, strength, defense
// - note: defense points are 1 at start. they are acquired as a temporary buff from certain abilities
// defense points deflect a percentage of the damage being given. its more inneficient as higher it gets because it is calculated as: log2(x) * 8. for example: 
// 6 defense = 20%, 14 defense = 30%, 32 defense = 40%, 77 defense = 50%, 182 defense = 60%, etc.

// cooldown based abilities return a boolean if they are still in cooldown or ready to use

// make a game loop until one group is out. the loop keeps track of each player's defense and buffs.
