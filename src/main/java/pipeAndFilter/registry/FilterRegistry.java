package pipeAndFilter.registry;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import org.junit.runners.Parameterized.Parameter;

import exceptions.NotRegisteredFilter;
import instantiator.Instantiator;
import pipeAndFilter.Generator;
import pipeAndFilter.Pipe;
import pipeAndFilter.Processable;
import pipeAndFilter.SimpleFilter;
import pipeAndFilter.filters.fileReader.PcapFileInputStreamGenerator;
import pipeAndFilter.impl.QueuePipe;
import pipeAndFilter.parameters.FilterParameter;
import pipeAndFilter.parameters.Parametrized;

public class FilterRegistry {
	
	private static FilterRegistry instance;
	private Properties classMapsProps;
	private Instantiator instantiator;
	private static final String stockName = "classmap";
	
	public static FilterRegistry getInstance(String classmap) {
		if(instance == null)
			instance = new FilterRegistry(classmap);
		return instance;
	}
	public static FilterRegistry getInstance() {
		if(instance == null)
			instance = new FilterRegistry(stockName);
		return instance;
	}
	
	private FilterRegistry(String classMap) {
		this.classMapsProps = new Properties();
		this.instantiator = new Instantiator();
		
		try {
			this.classMapsProps.load(new FileReader("config" + File.separator + classMap + ".properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public Processable get(String string) {
		
		if(string == null || string.isEmpty())
			throw new IllegalArgumentException("Invalid string: " + string);
		
		String[] descompose = string.replace(")", "").split("[(]");

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
			return (Processable)instantiator.instantiate(classname, arg);
		} catch(ClassNotFoundException ex) { 
			throw new NotRegisteredFilter(classname);
			
		}catch ( InstantiationException e1) {
			e1.printStackTrace();
		}
		
		return null;

		
	}
	
	private Processable internalGet(String classname) {

		try {
			return (Processable) instantiator.instantiate(classname);
		} catch (ClassNotFoundException e1) {

			throw new NotRegisteredFilter(classname);
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		}
		
		return null;
	}


}
