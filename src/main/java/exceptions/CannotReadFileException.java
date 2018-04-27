package exceptions;

public class CannotReadFileException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CannotReadFileException(String fileUri) {
		
		super(fileUri);
		
	}

}
