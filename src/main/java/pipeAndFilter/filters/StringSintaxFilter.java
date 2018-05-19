package pipeAndFilter.filters;

import pipeAndFilter.Pipe;
import pipeAndFilter.impl.SimpleFilterImpl;


public class StringSintaxFilter extends SimpleFilterImpl<String,String> {
    private final String sintaxRegex = "\\s*(\\w+\\s*(\\(\\s*.+\\s*\\)\\s+)?)+";
    public StringSintaxFilter(Pipe<String> inputPipe, Pipe<String> outputPipe) {
        super(inputPipe, outputPipe);
    }

    @Override
    public void transform(Pipe<String> input, Pipe<String> output) {
        if(input.canRetrieve()) {
            String str = input.retireve();
            if ((str+" ").matches(sintaxRegex))//Para que haga match el sintaxRegex se agrega un espacio
                output.accept(str);
        }
    }


}
