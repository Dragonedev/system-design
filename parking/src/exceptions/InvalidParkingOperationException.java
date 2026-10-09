package exceptions;

public class InvalidParkingOperationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidParkingOperationException(String message) {
	    super(message);
	}

}
