package exceptions;

public class NoAvailableSpotException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NoAvailableSpotException() {
		super("Sem vagas disponíveis.");
	}

}
