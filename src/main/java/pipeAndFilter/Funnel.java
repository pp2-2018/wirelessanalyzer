package pipeAndFilter;

import java.util.List;

import pipeAndFilter.impl.PipeSystem;

public interface Funnel<I, O> extends Processable{

	void collect(Pipe<I> inputPipe, Pipe<O> outputPipe);
	
	void addInputPipe(Pipe<I> pipe);
	
}
