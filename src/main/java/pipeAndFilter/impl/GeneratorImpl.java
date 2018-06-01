package pipeAndFilter.impl;

import pipeAndFilter.Generator;
import pipeAndFilter.Pipe;

public abstract class GeneratorImpl<O> implements Generator<O>{

	protected Pipe<O> outputPipe;

	public GeneratorImpl(Pipe<O> outputPipe) {
		this.outputPipe = outputPipe;
	}
	
	@Override
	public void process() {
		if(this.canGenerate()){
			put(outputPipe);
            
        }
        
        else{
            this.outputPipe.closeForWritting();
        }
		
	}
	

	@Override
	public Pipe<?>[] getPipes() {
		return new Pipe<?>[] { outputPipe };
	}
}
