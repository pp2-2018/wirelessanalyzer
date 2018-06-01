package validator;

import pipeAndFilter.Processable;
import pipeAndFilter.parameters.Parametrized;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FilterCompabilityValidator {

    FilterTypeValidator filterTypeValidator;

    public FilterCompabilityValidator() {
        this.filterTypeValidator = new FilterTypeValidator();
    }

    public boolean validateCompability(Processable filterOutput, Processable filterInput) {

        //si input es de tipo GeneratorImpl -> Error
        if(filterTypeValidator.isGenerator(filterInput))
            throw new IllegalArgumentException("Generator could never be an input Filter");

        //si output es de tipo SinkImpl -> Error
        if(filterTypeValidator.isSink(filterOutput))
            throw new IllegalArgumentException("Sink could never be an output Filter");


        //si output es de tipo SimplFilterImpl -> Si input es generator -> Error.  Si es Filter -> comparar el output del filterOutput con el Input del filterInput.
        if(filterTypeValidator.isFilter(filterOutput)){
            Type filterOutputType = getFilterOutput(filterOutput);
            System.out.println(filterOutputType);
            if(filterTypeValidator.isSink(filterInput))
                return filterOutputType.equals(getSinkInput(filterInput));
            if(filterTypeValidator.isFilter(filterInput))
                return filterOutputType.equals(getFilterInput(filterInput));

        }

            //Si es de tipo Generator Impl -> Si es generator, error. Si es Filter ->
        if(filterTypeValidator.isGenerator(filterOutput)){
            Type generatorOutputType = getGeneratorOutput(filterOutput);

            if(filterTypeValidator.isSink(filterInput))
                return generatorOutputType.equals(getSinkInput(filterInput));
            if(filterTypeValidator.isFilter(filterInput))
                return generatorOutputType.equals(getFilterInput(filterInput));
        }

        throw new IllegalArgumentException("Invalid type of Filter");
    }

    private Type getGeneratorOutput(Processable generator) {
    	Type toRet = getFilterInput(generator);
        return toRet; 
    }

    private Type getSinkInput(Processable sink) {
        return getFilterOutput(sink);
    }

    public Type getFilterOutput(Processable filter){
        List<Parameter> parameters = getParameters(filter);

        /*TODO El criterio de retorno del tipo de parámetro es muy aleatorio. Que pasa si
        el útlimo parámetro no es el output del filter?? Actualmente se está devolviendo el
         último tipo de parámetro de la lista del constructor*/
        return parameters.get(parameters.size() -1).getParameterizedType();
    }

    public Type getFilterInput(Processable filterInput){
        List<Parameter> parameters = getParameters(filterInput);
        System.out.println(parameters);
        return parameters.get(0).getParameterizedType();
    }

    private List<Parameter> getParameters(Processable filter) {
        Class c = filter.getClass();
        Constructor parametrizedConstructor = null;
        try {
			 parametrizedConstructor = getParametrizedContructorOf(c);
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
        //Obtengo los constructores de la clase c
        List<Constructor> constructors = new LinkedList<>();
        Constructor[] listOfConstructors = c.getDeclaredConstructors();
        
        for(int i = 0; i < listOfConstructors.length; i++)
        	constructors.add(listOfConstructors[i]);
        		
        //Observo si hay alguno parametrizado y lo saco
        if (constructors != null)
        	constructors.remove(parametrizedConstructor);
        
        //Obtengo los parámetros del constructor
        //TODO Qué pasa si el filtro el día de mañana tiene más de un constructor??
        return Arrays.asList(constructors.get(constructors.size() - 1).getParameters());
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
		
		return constructor;
	}
	

}
