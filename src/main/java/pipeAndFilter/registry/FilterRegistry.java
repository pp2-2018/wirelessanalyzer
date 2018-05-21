package pipeAndFilter.registry;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import exceptions.NotRegisteredFilter;
import pipeAndFilter.Generator;
import pipeAndFilter.Pipe;
import pipeAndFilter.Processable;
import pipeAndFilter.SimpleFilter;
import pipeAndFilter.filters.fileReader.PcapFileInputStreamGenerator;
import pipeAndFilter.impl.QueuePipe;

public class FilterRegistry {
	
	private static FilterRegistry instance;
	private Properties classMapsProps;
	
	public static FilterRegistry getInstance(String classmap) {
		if(instance == null)
			instance = new FilterRegistry(classmap);
		return instance;
	}
	
	private FilterRegistry(String classMap) {
		this.classMapsProps = new Properties();
		try {
			this.classMapsProps.load(new FileReader("config" + File.separator + classMap + ".properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public Processable get(String string) {
		
		if(string == null || string == "")
			throw new IllegalArgumentException("Invalid string: " + string);
		
		String[] descompose = string.split(":");

		String classname = this.classMapsProps.getProperty(descompose[0]);
		
		if(classname == null)
			throw new NotRegisteredFilter(descompose[0]);
		
		if(descompose.length == 1)
			return internalGet(classname);
		if(descompose.length == 2)
			return internalGet(classname, descompose[1]);
		
		return null;
	}
	
	private Processable internalGet(String classname, String arg) {
		
	
		try {
			Class<?> clazz = Class.forName(classname);

			Constructor<?> constructor = clazz.getConstructor(Pipe.class, String.class);
			
			return (Processable) constructor.newInstance(new QueuePipe<>(), arg);
			
		}catch(ClassNotFoundException ex) { 
		
			throw new NotRegisteredFilter(classname);
		}
		catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;

		
	}
	
	private Processable internalGet(String classname) {

		try {
			Class<?> clazz = Class.forName(classname);

			Constructor<?> constructor = clazz.getConstructor(Pipe.class, Pipe.class);
			
			return (Processable) constructor.newInstance(new QueuePipe<>(), new QueuePipe<>());
			
		} catch(ClassNotFoundException ex) { 
		
			throw new NotRegisteredFilter(classname);
		}catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;

		
	
	}


}
