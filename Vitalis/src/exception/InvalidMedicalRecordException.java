package exception;

public class InvalidMedicalRecordException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidMedicalRecordException(String message) {
		super(message);
	}

}
