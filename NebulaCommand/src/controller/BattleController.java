package controller;

import entities.Fleet;
import entities.Spaceship;
import entities.enums.BattleStatus;
import service.BattleService;

public class BattleController {

	private final BattleService battleService;
	private final Fleet playerFleet;
	private final Fleet enemyFleet;
	private BattleStatus status;

	public BattleController(BattleService battleService, Fleet playerFleet, Fleet enemyFleet) {
		this.battleService = battleService;
		this.playerFleet = playerFleet;
		this.enemyFleet = enemyFleet;
		this.status = BattleStatus.NOT_STARTED;
	}

	public void startBattle() {
		if (status != BattleStatus.NOT_STARTED) {
			return;
		}

		if (playerFleet.isDestroyed() || enemyFleet.isDestroyed()) {
			return;
		}

		status = BattleStatus.IN_PROGRESS;
	}

	public void executeAttack(String attackerName, String targetName) {

		if (status != BattleStatus.IN_PROGRESS) {
			return;
		}

		Spaceship attacker = playerFleet.findSpaceshipByName(attackerName);
		Spaceship target = enemyFleet.findSpaceshipByName(targetName);

		if (attacker == null || target == null) {
			return;
		}

		battleService.attack(attacker, target);

		checkBattleStatus();
	}

	public BattleStatus checkBattleStatus() {
		if (playerFleet.isDestroyed() && enemyFleet.isDestroyed()) {
			status = BattleStatus.DRAW;
		} else if (enemyFleet.isDestroyed()) {
			status = BattleStatus.VICTORY;
		} else if (playerFleet.isDestroyed()) {
			status = BattleStatus.DEFEAT;
		}

		return status;
	}

	public Fleet getPlayerFleet() {
		return playerFleet;
	}

	public Fleet getEnemyFleet() {
		return enemyFleet;
	}

	public BattleStatus getStatus() {
		return status;
	}

}
