package by.htp.spr.service;

public class NewsServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public NewsServiceException() {
		super();
	}

	public NewsServiceException(String message) {
		super(message);
	}

	public NewsServiceException(Exception e) {
		super(e);
	}

	public NewsServiceException(String message, Exception exception) {
		super(message, exception);
	}
}
