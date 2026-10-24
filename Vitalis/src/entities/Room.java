package entities;

public class Room {

	private int floor;
	private String room;

	public Room(int floor, String room) {
		this.floor = floor;
		this.room = room;
	}

	public int getFloor() {
		return floor;
	}

	public String getRoom() {
		return room;
	}

	@Override
	public String toString() {
		return "Room [floor=" + floor + ", room=" + room + "]";
	}
	
	
}