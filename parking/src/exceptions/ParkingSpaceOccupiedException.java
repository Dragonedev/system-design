package exceptions;

public class ParkingSpaceOccupiedException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ParkingSpaceOccupiedException(String message) {
	    super(message);
	}

}
