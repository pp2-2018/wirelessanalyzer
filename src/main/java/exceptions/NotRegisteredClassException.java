package exceptions;

import java.lang.reflect.Type;

public class NotRegisteredClassException extends IllegalArgumentException{

	
	public NotRegisteredClassException(Type clazz) {
		
		super("Class " + clazz.getTypeName() +"not registered in mapper");
		
	}
	
}
