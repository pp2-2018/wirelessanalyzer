package pipeAndFilter.impl;

import java.util.ArrayList;
import java.util.List;

import pipeAndFilter.Funnel;
import pipeAndFilter.Pipe;

public abstract class FunnelImpl<I, O> implements Funnel<I, O>{

	private PipeSystem<I> inputPipes; 
	private Pipe<O> outputPipe;
	
	public FunnelImpl(PipeSystem<I> inputPipes, Pipe<O> outputPipe) {
		super();
		this.inputPipes = inputPipes;
		this.outputPipe = outputPipe;
	}

	@Override
	public void process() {
		
		if(inputPipes.canRetrieveForSomeone()) {
			
			for (Pipe<I> pipe : inputPipes) {

				if(pipe.canRetrieve())collect(pipe, outputPipe);	
			}
			
		}
		
		else {
			this.outputPipe.closeForWritting();
		}
	}

	@Override
	public void addInputPipe(Pipe<I> pipe) {
		
		this.inputPipes.addPipe(pipe);
		
	}


}
