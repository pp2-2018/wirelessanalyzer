package pipeAndFilter.filters.test;

import pipeAndFilter.Pipe;
import pipeAndFilter.impl.GeneratorImpl;
import pipeAndFilter.impl.SimpleFilterImpl;
import pipeAndFilter.parameters.Parametrized;

import java.util.UUID;

public class TestGenerator extends GeneratorImpl<String> {

    @Parametrized
    public TestGenerator() {
        super(null);
    }

    public TestGenerator(Pipe<String> outputPipe) {
        super(outputPipe);
    }


    @Override
    public void put(Pipe<String> outputPipe) {

        outputPipe.accept(UUID.randomUUID().toString());

    }

    @Override
    public boolean canGenerate() {
        return true;
    }
}
