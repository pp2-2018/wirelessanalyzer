package validator;

import pipeAndFilter.Processable;
import pipeAndFilter.impl.GeneratorImpl;
import pipeAndFilter.impl.SimpleFilterImpl;
import pipeAndFilter.impl.SinkImpl;


public class FilterTypeValidator {

    public boolean isSink(Processable sink){
        return isTypeEqualsTo(sink, SinkImpl.class);
    }

    public boolean isFilter(Processable filter){
        return isTypeEqualsTo(filter, SimpleFilterImpl.class);
    }

    public boolean isGenerator(Processable generator) {
        return isTypeEqualsTo(generator, GeneratorImpl.class);
    }

    private boolean isTypeEqualsTo(Processable filter, Class simpleClass) {
        return filter.getClass().getSuperclass().equals(simpleClass);
    }
}
