package classes;

import java.util.List;

public class Hero extends Entity {
	private int xp;
	private boolean hasChanneledHealth;
	private int cooldownChanneledHealth;

	public Hero(String name) {
		super(name, 200, 40);
		this.xp = 0;
		this.hasChanneledHealth = false;
		this.cooldownChanneledHealth = 0;

		battleCry();
	}

	// called at the start of each round to check if buffs are active
	public void checkStatusHero() {
		if (hasChanneledHealth) {
			int missingHp = getOrigHp() - getHp();

			setHp(getHp() + (int) (0.2 * missingHp));
			hasChanneledHealth = false;

			System.out.println("hero has healed himself for 20% of his missing hp");
		}
		if (cooldownChanneledHealth > 0) {
			cooldownChanneledHealth--;
		}
	}

	public void attack(Entity target, float multiplier) {
		super.attack(target);
		xp += 5;
		if (target.getHp() <= 0) {
			xp += 50;
			setHp(this.getHp() + 20);
		}
	}

	public void channelHealthNextRound() {
		System.out.println("hero channels his inner power to heal himself next round..");
		hasChanneledHealth = true;
	}

	public void aoeAttack(List<Entity> targets) {
		System.out.println(getName() + " attacks all enemies in range");

		int origStrength = getStrength();
		setStrength((int) (getStrength() * 0.3f));

		for (Entity target : targets) {
			attack(target); // check if attack from hero is used. check if xp is increased
		}

		setStrength(origStrength);
	}

	public void battleCry() {
		System.out
				.println(getName()
						+ " is here: Hoho fiend! You are approaching me? My might is beyond your comprehension!");
	}

	public boolean getHasChanneledHealth() {
		return hasChanneledHealth;
	}

	public void setHasChanneledHealth(boolean hasChanneledHealth) {
		this.hasChanneledHealth = hasChanneledHealth;
	}

	public void printStatus() {
		System.out.print(getName() + ": ");
		super.printStatus();
	}

	// extends entities, xp, name. 200 hp 25 strength
	// passively gain health from monster kill, passively gets 5 xp per hit, 
	// channel to gain missing health next round (20% of missing health. ex: 10 -> 48 or 120 -> 136) (cooldown 3 rounds)
	// aoe attack (30% of strength)
}
