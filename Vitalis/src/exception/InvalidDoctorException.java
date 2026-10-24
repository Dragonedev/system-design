package exception;

public class InvalidDoctorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidDoctorException(String message) {
		super(message);
	}

}
