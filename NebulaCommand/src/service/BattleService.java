package service;

import entities.Spaceship;

public class BattleService {

	public void attack(Spaceship attacker, Spaceship target) {
		if (attacker == null || target == null) {
			return;
		}

		if (attacker.isDestroyed() || target.isDestroyed()) {
			return;
		}

		if (attacker.useEnergy(attacker.getWeapon().getEnergyCost())) {
			Integer damage = attacker.getWeapon().calculateDamage();
			target.takeDamage(damage);
		}
	}

}
