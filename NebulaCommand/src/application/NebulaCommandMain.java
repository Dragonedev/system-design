package application;

import controller.BattleController;
import entities.Fleet;
import entities.Spaceship;
import service.BattleService;
import weapons.LaserWeapon;
import weapons.PlasmaWeapon;

public class NebulaCommandMain {

	public static void main(String[] args) {

		// Weapons
		LaserWeapon laser = new LaserWeapon();
		PlasmaWeapon plasma = new PlasmaWeapon();

		// Player fleet
		Fleet playerFleet = new Fleet("Celestial Order", 3);

		playerFleet.addSpaceship(new Spaceship("Darkstar", 100, 50, 100, laser));

		playerFleet.addSpaceship(new Spaceship("Nyx", 120, 40, 80, plasma));

		// Enemy fleet
		Fleet enemyFleet = new Fleet("Oblivion Fleet", 3);

		enemyFleet.addSpaceship(new Spaceship("Malakor", 150, 60, 100, plasma));

		enemyFleet.addSpaceship(new Spaceship("Vex-09", 80, 30, 120, laser));

		// Service and controller
		BattleService battleService = new BattleService();

		BattleController battleController = new BattleController(battleService, playerFleet, enemyFleet);

		// Start battle
		battleController.startBattle();

		System.out.println("=== NEBULA COMMAND ===");
		System.out.println("Battle status: " + battleController.getStatus());

		// Test attack
		battleController.executeAttack("Darkstar", "Malakor");

		System.out.println("\n=== AFTER ATTACK ===");
		System.out.println("Malakor health: " + enemyFleet.findSpaceshipByName("Malakor").getHealth());
		System.out.println("Malakor shield: " + enemyFleet.findSpaceshipByName("Malakor").getShield());
		System.out.println("Darkstar energy: " + playerFleet.findSpaceshipByName("Darkstar").getEnergy());

		battleController.executeAttack("Darkstar", "Malakor");

		System.out.println("\n=== AFTER ATTACK ===");
		System.out.println("Malakor health: " + enemyFleet.findSpaceshipByName("Malakor").getHealth());
		System.out.println("Malakor shield: " + enemyFleet.findSpaceshipByName("Malakor").getShield());
		System.out.println("Darkstar energy: " + playerFleet.findSpaceshipByName("Darkstar").getEnergy());

		System.out.println("\nBattle status: " + battleController.getStatus());
	}

}
