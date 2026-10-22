package weapons;

public class PlasmaWeapon implements Weapon {
	private Integer damage;
	private Integer energyCost;

	public PlasmaWeapon() {
		this.damage = 40;
		this.energyCost = 20;
	}

	public String getName() {
		return "Plasma";
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
