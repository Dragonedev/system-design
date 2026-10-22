package entities;

import weapons.Weapon;

public class Spaceship {

	private String name;
	private Integer health;
	private Integer shield;
	private Integer energy;
	private Weapon weapon;

	public Spaceship(String name, Integer health, Integer shield, Integer energy, Weapon weapon) {
		this.name = name;
		this.health = health;
		this.shield = shield;
		this.energy = energy;
		this.weapon = weapon;
	}

	public void takeDamage(Integer damage) {
		if (damage == null || damage <= 0) {
			return;
		}

		if (damage <= shield) {
			shield -= damage;
		} else {
			int remainDamage = damage - shield;
			shield = 0;
			health = Math.max(0, health - remainDamage);
		}

	}

	public boolean useEnergy(Integer amount) {
		if (amount == null || amount <= 0 || energy < amount) {
			return false;
		}

		energy -= amount;
		return true;
	}

	public boolean isDestroyed() {
		return health <= 0;
	}

	public String getName() {
		return name;
	}

	public Integer getHealth() {
		return health;
	}

	public Integer getShield() {
		return shield;
	}

	public Integer getEnergy() {
		return energy;
	}

	public Weapon getWeapon() {
		return weapon;
	}

}
