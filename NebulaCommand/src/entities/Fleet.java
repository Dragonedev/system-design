package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fleet {

	private String name;
	private List<Spaceship> spaceships;
	private Integer maxCapacity;

	public Fleet(String name, Integer maxCapacity) {
		this.name = name;
		this.spaceships = new ArrayList<>();
		this.maxCapacity = maxCapacity;
	}

	public void addSpaceship(Spaceship spaceship) {
		if (spaceship == null) {
			return;
		}

		if (spaceships.size() >= maxCapacity) {
			return;
		}

		spaceships.add(spaceship);
	}

	public void removeSpaceship(String name) {
		Spaceship spaceship = findSpaceshipByName(name);

		if (spaceship != null) {
			spaceships.remove(spaceship);
		}
	}

	public Spaceship findSpaceshipByName(String name) {
		for (Spaceship spaceship : spaceships) {
			if (spaceship.getName().equalsIgnoreCase(name)) {
				return spaceship;
			}
		}

		return null;
	}

	public Integer getTotalHealth() {
		int totalHealth = 0;

		for (Spaceship ss : spaceships) {
			totalHealth += ss.getHealth();
		}

		return totalHealth;
	}

	public boolean isDestroyed() {
		for (Spaceship ss : spaceships) {
			if (!ss.isDestroyed()) {
				return false;
			}
		}

		return true;
	}

	public String getName() {
		return name;
	}

	public List<Spaceship> getSpaceships() {
		return Collections.unmodifiableList(spaceships);
	}

	public Integer getMaxCapacity() {
		return maxCapacity;
	}

}
