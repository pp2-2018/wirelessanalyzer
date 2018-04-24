package pipeAndFilter;

import java.util.List;

public interface SimpleFilter<I,O> extends Processable {

    public void transform(Pipe<I> input, Pipe<O> output);

}
