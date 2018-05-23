package validator;

import pipeAndFilter.Processable;

public class ComponentTypeValidator {


    private Class classToCompare;

    public ComponentTypeValidator(Class toCompare){
        this.classToCompare = toCompare;
    }

    public boolean validate(Processable sink) {

        Class sinkClass = sink.getClass();

        Class superClass = sinkClass.getSuperclass();

        return classToCompare.equals(superClass);

    }
}
