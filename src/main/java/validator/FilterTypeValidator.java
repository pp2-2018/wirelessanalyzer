package validator;

import pipeAndFilter.Processable;

public class FilterTypeValidator {


    private Class classToCompare;

    public FilterTypeValidator(Class toCompare){
        this.classToCompare = toCompare;
    }

    public boolean validate(Processable sink) {

        Class sinkClass = sink.getClass();

        Class superClass = sinkClass.getSuperclass();

        return classToCompare.equals(superClass);

    }
}
