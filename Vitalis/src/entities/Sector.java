package entities;

public class Sector {

	private String name;
	private String code;

	public Sector(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return "Sector [name=" + name + ", code=" + code + "]";
	}

}