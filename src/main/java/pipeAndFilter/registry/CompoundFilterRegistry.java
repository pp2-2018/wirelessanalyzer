package pipeAndFilter.registry;

import exceptions.NotRegisteredFilter;
import pipeAndFilter.Generator;
import pipeAndFilter.Processable;
import pipeAndFilter.Sink;
import pipeAndFilter.impl.FilterSystem;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CompoundFilterRegistry {


    private static CompoundFilterRegistry instance;
    private static final String filename = "myFilters" ;
    
    private Properties classMapsProps;
    private FilterRegistry simpleRegistry;

    public static CompoundFilterRegistry getInstance(String filename) {
        if(instance == null)
            instance = new CompoundFilterRegistry(filename);
        return instance;
    }
        
    public static CompoundFilterRegistry getInstance() {
        return CompoundFilterRegistry.getInstance(filename);
    }

    private CompoundFilterRegistry(String  filename) {
        this.classMapsProps = new Properties();
        this.simpleRegistry = FilterRegistry.getInstance("classmap");
        
        try {
            this.classMapsProps.load(new FileReader("config" + File.separator +  filename + ".properties"));
        } catch (IOException e) {
            try{
                File properties = new File("config" + File.separator +  filename + ".properties");
                if(!properties.exists()){
                    boolean newFile=properties.createNewFile();
                    if(!newFile){
                        throw new IOException("Error Creating Properties File");
                    }
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }



    public void set(String name,String compoundFilters) {

        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Invalid key " + name);

        this.classMapsProps.setProperty(name, compoundFilters);
        try {
            classMapsProps.store
                    (new FileWriter("config" + File.separator +  filename + ".properties"),"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void remove(String name) {

        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Invalid key");

        Object o = this.classMapsProps.remove(name);
        if (o == null){
            System.err.println("Compound Filter \"" + name + "\" not found.");
        return;
        }

        try {
            classMapsProps.store
                    (new FileWriter("config" + File.separator +  filename + ".properties"),"");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public String getFilterString(String string) {

        if(string == null || string.isEmpty())
            throw new IllegalArgumentException("Invalid string: " + string);
        String compoundFilter = this.classMapsProps.getProperty(string);
        if(compoundFilter == null)
            throw new NotRegisteredFilter("Compound Filter Not Registered: " + string);


        return compoundFilter;
    }

    //Awful code
    public List<Processable> get(String name) {
    	
    	ArrayList<Processable> toRet = new ArrayList<>();
    	
    	try {
        	String filtersString = getFilterString(name);
        	
        	String[] filters = filtersString.split(" ");
        	
        	for (String f : filters) toRet.addAll(get(f));
        		
        }catch (NotRegisteredFilter e) {
				toRet.add(findInSimpleRegistry(name));
		}
    	
    	return toRet;
    	
    }
    
    private Processable findInSimpleRegistry(String name) {
    	try {
			
			return simpleRegistry.get(name);
			
		} catch (NotRegisteredFilter  e2) {
			throw e2;
		}
    }
    
}
