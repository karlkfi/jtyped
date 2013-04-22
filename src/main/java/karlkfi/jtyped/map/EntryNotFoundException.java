package karlkfi.jtyped.map;

public class EntryNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntryNotFoundException() {
		super();
	}

	public EntryNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntryNotFoundException(String message) {
		super(message);
	}

	public EntryNotFoundException(Throwable cause) {
		super(cause);
	}

}
