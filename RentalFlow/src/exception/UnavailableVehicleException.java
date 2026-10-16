package exception;

public class UnavailableVehicleException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnavailableVehicleException(String message) {
		super(message);
	}

}
