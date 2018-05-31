package instantiator;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.rmi.activation.Activator;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.reflections.Reflections;

import exceptions.NotRegisteredClassException;
import pipeAndFilter.parameters.FileListParameter;
import pipeAndFilter.parameters.FilterParameter;


public class ParameterMapper {

	private Map<Type, Function<String, Object>> dictionary;
	
	public ParameterMapper(String packageUri) {
		
		dictionary = new HashMap<>();
	
		dictionary.put(Integer.class, s -> Integer.parseInt(s));
		dictionary.put(Boolean.class, s -> Boolean.parseBoolean(s));
		
		fillDicctionary(packageUri);
		
	}
	
	private void fillDicctionary(String packageUri) {
		
		Reflections reflections = new Reflections(packageUri);
		
		Set<Class<? extends FilterParameter>> allClasses = 
		     reflections.getSubTypesOf(FilterParameter.class);
		
		for (Class<? extends FilterParameter> paramClazz : allClasses) {
			
			try {
				FilterParameter<?> instance = paramClazz.newInstance();
				
				Method method = paramClazz.getMethod("fromString", String.class);
				
				dictionary.put(method.getGenericReturnType(), s -> instance.fromString(s));
				
			} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public <T> T map(String stringParam, Type type) {
		
		if(!dictionary.containsKey(type))
			throw new NotRegisteredClassException(type);
		
		return (T) dictionary.get(type).apply(stringParam);
		
	}
	
	/*
	@SuppressWarnings("unchecked")
	public <T, P> T map(String stringParam, Class<T> clazz, Class<P> generic)  {
		
		String name = clazz.getName() + "<" + generic.getName() +">";
		
		if(!dictionary.containsKey(name))
			throw new NotRegisteredClassException(clazz);
		
		return (T) dictionary.get(name).apply(stringParam);
		
	}*/
	
	
	
}
