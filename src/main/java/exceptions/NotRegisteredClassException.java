package exceptions;

public class NotRegisteredClassException extends IllegalArgumentException{

	
	public NotRegisteredClassException(Class<?> clazz) {
		
		super("Class " + clazz.getName() +"not registered in mapper");
		
	}
	
}
