package exception;

public class InvalidVehicleException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidVehicleException(String message) {
		super(message);
	}
}
