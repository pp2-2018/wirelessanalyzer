package pipeAndFilter.filters.test;

import pipeAndFilter.Pipe;
import pipeAndFilter.impl.SimpleFilterImpl;
import pipeAndFilter.parameters.Parametrized;

public class TestFilter extends SimpleFilterImpl<String,String> {



    @Parametrized
    public TestFilter() {
        super(null, null);
    }


    @Override
    public void transform(Pipe<String> input, Pipe<String> output) {

        String string = input.retireve();
        output.accept(string.toUpperCase());

    }

}
