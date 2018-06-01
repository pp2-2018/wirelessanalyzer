package instantiator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;



import pipeAndFilter.Pipe;
import pipeAndFilter.impl.QueuePipe;
import pipeAndFilter.parameters.Parametrized;

public class Instantiator {

	private ParameterMapper mapper;
	
	
	public Instantiator() {
	
		mapper = new ParameterMapper("pipeAndFilter.parameters");
		
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
		catch ( SecurityException | IllegalAccessException | InvocationTargetException e) {
			
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

		Class<?>[] constrParams = constructor.getParameterTypes();
		if(constrParams.length == 0)
			throw new InstantiationException("Constructor with parameters must have at least one");


		Class<?> parameter = constrParams[0];
		
		return mapper.map(params, parameter);
		
	}
	
}
