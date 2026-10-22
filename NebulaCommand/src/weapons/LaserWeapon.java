package weapons;

public class LaserWeapon implements Weapon {
	private Integer damage;
	private Integer energyCost;

	public LaserWeapon() {
		this.damage = 25;
		this.energyCost = 10;
	}
	
	public String getName() {
		return "Laser";
	}

	public Integer getDamage() {
		return damage;
	}

	public Integer getEnergyCost() {
		return energyCost;
	}

	public Integer calculateDamage() {
		return damage;
	}



}
