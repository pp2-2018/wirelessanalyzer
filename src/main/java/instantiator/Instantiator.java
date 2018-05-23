package instantiator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import pipeAndFilter.Pipe;
import pipeAndFilter.impl.QueuePipe;
import pipeAndFilter.parameters.Parametrized;

public class Instantiator {

	public Instantiator() {
	
	}

	
	public Object instantiate(String className) throws ClassNotFoundException, InstantiationException{
		try {
			Class<?> clazz = Class.forName(className);
	
			Constructor<?> constructor = getParametrizedContructorOf(clazz);
			
			return constructor.newInstance();
		
		} catch (SecurityException  | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
		
			e.printStackTrace();
		}
	
		return null;
	
	}
	
	public Object instantiate(String className, String params) throws ClassNotFoundException, InstantiationException{
		try {
			Class<?> clazz = Class.forName(className);
			Constructor<?> constructor = getParametrizedContructorOf(clazz);
			
			Object paramInstance = getParameterOf(constructor, params);
			
			return constructor.newInstance(paramInstance);
		
		}
		catch (NoSuchMethodException e) {
			throw new InstantiationException(className + " constructor must have a FilterParameter type parameter");
		}
		catch ( SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	private Constructor<?> getParametrizedContructorOf(Class<?> clazz) throws InstantiationException{
		Constructor<?> constructor = null;
		
		for(Constructor<?> c : clazz.getConstructors()) {
			Annotation annotation = c.getDeclaredAnnotation(Parametrized.class);
			if(annotation != null) {
				constructor = c;
				break;
			}
		}
	
		if(constructor == null)
			throw new InstantiationException(clazz.getName() + " constructor must have @Parametrized annotation");
		
		return constructor;
	}
	
	private Object getParameterOf(Constructor<?> constructor, String params) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> parameter = constructor.getParameterTypes()[0];
		
		Constructor<?> paramConstructor = parameter.getConstructor(String.class);
		Object paramInstance = paramConstructor.newInstance(params);
		
		return paramInstance;
	}
	
}
