package exceptions;

public class RawPackageNotHandled extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public RawPackageNotHandled() {
		super("Not a configured RawPackage");
		
		
	}
	
}
