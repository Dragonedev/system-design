package weapons;

public interface Weapon {

	public String getName();

	public Integer getDamage();

	public Integer getEnergyCost();

	public Integer calculateDamage();
}
