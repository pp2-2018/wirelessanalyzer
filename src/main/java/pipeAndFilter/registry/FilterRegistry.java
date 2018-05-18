package pipeAndFilter.registry;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

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
		
		String[] descompose = string.split(":");
		
		Class<?> clazz;
		String classname = this.classMapsProps.getProperty(descompose[0]);
		try {
			clazz = Class.forName(classname);

			Constructor<?> constructor = clazz.getConstructor(Pipe.class, String.class);
			
			return (Generator<?>) constructor.newInstance(new QueuePipe<>(), descompose[1]);
			
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
