package validator;


import pipeAndFilter.SimpleFilter;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterIOTypeValidator {

    public boolean validateTypte(SimpleFilter filterInput, SimpleFilter filterOutput) {

        //Pregunto por el input de salida de filterOutput


        //Pregunto por el input de entrada de filter Input
        //Comparo

        return outputDataType(filterOutput).equals(inputDataType(filterInput));
    }


    public Type outputDataType(SimpleFilter filterOutut){
        List<Parameter> parameters = getParameters(filterOutut);

        /*TODO El criterio de retorno del tipo de parámetro es muy aleatorio. Que pasa si
        el útlimo parámetro no es el output del filter?? Actualmente se está devolviendo el
         último tipo de parámetro de la lista del constructor*/
        return parameters.get(parameters.size() -1).getParameterizedType();
    }

    public Type inputDataType(SimpleFilter filterInput){
        List<Parameter> parameters = getParameters(filterInput);

        return parameters.get(0).getParameterizedType();
    }


    private List<Parameter> getParameters(SimpleFilter filter) {
        Class c = filter.getClass();

        //Obtengo los constructores de la clase c
        List<Constructor> constructors = Arrays.asList(c.getDeclaredConstructors());
        //Obtengo los parámetros del constructor
        //TODO Qué pasa si el filtro el día de mañana tiene más de un constructor??
        return Arrays.asList(constructors.get(0).getParameters());
    }


}
