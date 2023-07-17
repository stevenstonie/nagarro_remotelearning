package classes;

import main.java.com.nagarro.remotelearning.GameUtils;

public class Entity {
	private int healthPoints;
	private int strength;
	private float defense;
	private String name;

	private int strengthBuffDebuff;
	private int roundsLeftStrengthBuffDebuff;

	private int origHealthPoints;
	private int origStrength;
	private float origDefense;

	Entity(String name, int healthPoints, int strength) {
		this.healthPoints = healthPoints;
		this.strength = strength;
		this.defense = 1;
		this.name = name;

		this.strengthBuffDebuff = 0;
		this.roundsLeftStrengthBuffDebuff = 0;
		this.origHealthPoints = this.healthPoints;
		this.origStrength = this.strength;
		this.origDefense = this.defense;
	}

	public void attack(Entity target) {
		System.out.println(this.name + " is attacking " + target.name + "!");

		float percentageOfDeflection = GameUtils.log2(target.defense) * 8;
		float deflectedDamage = (percentageOfDeflection / 100) * strength;
		float damageDealt = this.strength - deflectedDamage;
		target.healthPoints -= damageDealt;
	}

	public void printStatus() {
		System.out.println(
				"healthPoints: " + this.healthPoints + " | strength: " + this.strength + " | defense: " + this.defense);
	}

	public void battleCry() {
		System.out.println("I am an entity..");
	}

	public int getHp() {
		return this.healthPoints;
	}

	public int getStrength() {
		return this.strength;
	}

	public float getDefense() {
		return this.defense;
	}

	public String getName() {
		return this.name;
	}

	public int getStrengthBuffDebuff() {
		return this.strengthBuffDebuff;
	}

	public int getRoundsLeftStrengthBuffDebuff() {
		return this.roundsLeftStrengthBuffDebuff;
	}

	public int getOrigHp() {
		return this.origHealthPoints;
	}

	public int getOrigStrength() {
		return this.origStrength;
	}

	public float getOrigDefense() {
		return this.origDefense;
	}

	// setters

	public void setHp(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public void setDefense(float defense) {
		this.defense = defense;
	}

	public void setStrengthBuffDebuff(int strengthBuffDebuff) {
		this.strengthBuffDebuff = strengthBuffDebuff;
	}

	public void setRoundsLeftStrengthBuffDebuff(int roundsLeftStrengthBuffDebuff) {
		this.roundsLeftStrengthBuffDebuff = roundsLeftStrengthBuffDebuff;
	}

	public void setOrigHp(int orinHealthPoints) {
		this.origHealthPoints = orinHealthPoints;
	}

	public void setOrigStrength(int origStrength) {
		this.origStrength = origStrength;
	}

	public void setOrigDefense(float origDefense) {
		this.origDefense = origDefense;
	}
}
