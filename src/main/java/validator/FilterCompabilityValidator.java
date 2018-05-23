package validator;

import pipeAndFilter.Processable;

import java.lang.reflect.*;
import java.util.Arrays;
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
            else
                return filterOutputType.equals(getFilterInput(filterInput));

        }

            //Si es de tipo Generator Impl -> Si es generator, error. Si es Filter ->
        if(filterTypeValidator.isGenerator(filterOutput)){
            Type generatorOutputType = getGeneratorOutput(filterOutput);

            if(filterTypeValidator.isSink(filterInput))
                return generatorOutputType.equals(getSinkInput(filterInput));
            else
                return generatorOutputType.equals(getFilterInput(filterInput));
        }

        throw new IllegalArgumentException();
    }

    private Type getGeneratorOutput(Processable generator) {
        return getFilterInput(generator);
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

        return parameters.get(0).getParameterizedType();
    }

    private List<Parameter> getParameters(Processable filter) {
        Class c = filter.getClass();

        //Obtengo los constructores de la clase c
        List<Constructor> constructors = Arrays.asList(c.getDeclaredConstructors());
        //Obtengo los parámetros del constructor
        //TODO Qué pasa si el filtro el día de mañana tiene más de un constructor??
        return Arrays.asList(constructors.get(0).getParameters());
    }

}
