package pipeAndFilter.filters;

import pipeAndFilter.Pipe;
import pipeAndFilter.impl.SimpleFilterImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringParameterFilter extends SimpleFilterImpl<String,String> {
    private final String parethesisRegex = "\\(\\s*.+\\s*\\)";
    Pattern r;
    public StringParameterFilter(Pipe<String> inputPipe, Pipe<String> outputPipe) {
        super(inputPipe, outputPipe);
        this.r = Pattern.compile(parethesisRegex);

    }


    @Override
    public void transform(Pipe<String> input, Pipe<String> output) {
        if(input.canRetrieve()) {
            String str = input.retireve();
            Matcher m = r.matcher(str);
            if (m.find()){
                output.accept(str.substring(m.start()+1,m.end()-1).trim());
            }        }
    }


}
