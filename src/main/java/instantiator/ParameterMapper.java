package instantiator;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.reflections.Reflections;

import exceptions.NotRegisteredClassException;
import pipeAndFilter.parameters.FileListParameter;
import pipeAndFilter.parameters.FilterParameter;

import static org.reflections.ReflectionUtils.*;

public class ParameterMapper {

	private Map<Class<?>, Function<String, Object>> dictionary;
	
	public ParameterMapper() {
		
		dictionary = new HashMap<>();
	
		dictionary.put(Integer.class, s -> Integer.parseInt(s));
		dictionary.put(Boolean.class, s -> Boolean.parseBoolean(s));
		
		fillDicctionary();
		
	}
	
	private void fillDicctionary() {
		
		Reflections reflections = new Reflections("pipeAndFilter.parameters");
		

		Set<Class<? extends FilterParameter>> allClasses = 
		     reflections.getSubTypesOf(FilterParameter.class);
		
		for (Class<? extends FilterParameter> paramClazz : allClasses) {
			
			try {
				FilterParameter<?> instance = paramClazz.newInstance();
				
				Method method = paramClazz.getMethod("fromString", String.class);
				
				dictionary.put(method.getReturnType(), s -> instance.fromString(s));
			} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			
			 
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public <T> T map(String stringParam, Class<T> clazz) {
		
		if(!dictionary.containsKey(clazz))
			throw new NotRegisteredClassException(clazz);
		
		return (T) dictionary.get(clazz).apply(stringParam);
		
	}
	
	
	
}
