package pipeAndFilter.impl;

import pipeAndFilter.Pipe;
import pipeAndFilter.Sink;

public abstract class SinkImpl<I> implements Sink<I>{

	protected Pipe<I> inputPipe;
	
	
	public SinkImpl(Pipe<I> inputPipe) {
		this.inputPipe = inputPipe;
	}



	@Override
	public void process() {
		
	        
		take(inputPipe);
		
	}

}
