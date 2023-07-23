package classes;

public class Monster extends Entity {
	private boolean isMonstruous;
	private byte nextRoundDefenseDebuff;
	private byte nextRoundStrengthBuff;
	private byte nextRoundDefenseBuff;

	public Monster(String name, boolean isMonstruous) {
		super(name, 160, 30);
		this.isMonstruous = isMonstruous;
		if (isMonstruous) {
			this.setHp(220);
			this.setStrength(13);
			this.setOrigHp(this.getHp());
			this.setOrigStrength(this.getStrength());
			this.setOrigDefense(this.getDefense());
		}
		nextRoundDefenseDebuff = 0;
		nextRoundStrengthBuff = 0;
		nextRoundDefenseBuff = 0;

		battleCry();
	}

	public void checkStatusMonster() {
		if (isMonstruous) {
			if (nextRoundStrengthBuff == 2) {
				setStrength((int) (getStrength() * 2.25f));
				nextRoundStrengthBuff--;
			} else if (nextRoundStrengthBuff == 1) {
				setStrength(getOrigStrength());
				nextRoundStrengthBuff--;
			}

			if (nextRoundDefenseBuff == 2) {
				setDefense(getDefense() + 55);
				nextRoundDefenseBuff--;
			} else if (nextRoundDefenseBuff == 1) {
				setDefense(getOrigDefense());
				nextRoundDefenseBuff--;
			}

		} else {
			if (nextRoundDefenseDebuff == 2) {
				setDefense(0.01f);
				nextRoundDefenseDebuff--;
			} else if (nextRoundDefenseDebuff == 1) {
				setDefense(getOrigDefense());
				nextRoundDefenseDebuff--;
			}
		}
	}

	public void attack(Entity target) {
		super.attack(target);
		if (!isMonstruous) {
			setHp(getHp() + 5);
		}
	}

	public void attackWithMoreStrength(Entity target) {
		System.out.println(getName() + " attacks with more strength!");

		int origStrength = this.getStrength();
		setStrength((int) (getStrength() * 1.25f));

		attack(target);

		setStrength(origStrength);

		nextRoundDefenseDebuff = 2;
		// maybe cooldown?
	}

	public void channelStrength() {
		nextRoundStrengthBuff = 2;
		// doesnt attack (exhausted)
		System.out.println(getName() + " became angry and will deal more damage next round!");
		// maybe cooldown?
	}

	public void channelDefense() {
		nextRoundDefenseBuff = 2;
		// doesnt attack (exhausted)
		System.out.println(getName() + " became defensive and will take less damage next round!");
		// try to see how to decrease the defense logaritmically
		// also maybe cooldown?
	}

	public void battleCry() {
		System.out.print(getName() + " just appeared: ");
		if (isMonstruous) {
			System.out.println("Blargh! I am a monster!");
		} else {
			System.out.println("Yum yum! I am a human! I will eat you! I am being forced to say this! blah blah blah!");
		}
	}

	public void printStatus() {
		System.out.print(getName() + ": ");
		super.printStatus();
	}

	// extends entities
	// if humanoid 160 hp 30 attack
	// // (passively gain health from hitting ???)
	// // attack with 25% more strength but get more damage on next round 
	// if monstruous 220 hp 13 attack
	// // channel strength to +125% on next round 
	// // channel to increase defense by an amount
}
