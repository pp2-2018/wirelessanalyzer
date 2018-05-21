package exceptions;

public class NotRegisteredFilter extends RuntimeException{

	public NotRegisteredFilter(String filterName) {
		super("Not registered filter " + filterName);
	}
	
}
