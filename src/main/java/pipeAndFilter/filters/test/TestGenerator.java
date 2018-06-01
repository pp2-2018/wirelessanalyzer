package pipeAndFilter.filters.test;

import pipeAndFilter.Pipe;
import pipeAndFilter.impl.GeneratorImpl;
import pipeAndFilter.impl.SimpleFilterImpl;
import pipeAndFilter.parameters.Parametrized;

import java.util.UUID;

public class TestGenerator extends GeneratorImpl<String> {

	private char[] chars;
	private int index;
	
    @Parametrized
    public TestGenerator(String toPut) {
        this(null, toPut);
    }

    public TestGenerator(Pipe<String> outputPipe, String toPut) {
        super(outputPipe);
        
        chars = toPut.toCharArray();
    }


    @Override
    public void put(Pipe<String> outputPipe) {
    	
        outputPipe.accept(chars[index] + "");
        
        index++;
    }

    @Override
    public boolean canGenerate() {
        return index < chars.length;
    }
}
