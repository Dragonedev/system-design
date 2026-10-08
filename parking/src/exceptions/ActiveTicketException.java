package exceptions;

public class ActiveTicketException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ActiveTicketException() {
        super("O veículo já possui um ticket ativo.");
    }
}