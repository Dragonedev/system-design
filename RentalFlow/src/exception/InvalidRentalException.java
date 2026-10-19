package exception;

public class InvalidRentalException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidRentalException(String message) {
		super(message);
	}
}
