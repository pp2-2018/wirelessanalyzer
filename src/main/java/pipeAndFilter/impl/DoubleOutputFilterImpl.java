package pipeAndFilter.impl;

import pipeAndFilter.DoubleOutputFilter;
import pipeAndFilter.Pipe;

public abstract class DoubleOutputFilterImpl<I, OA, OB> implements DoubleOutputFilter<I, OA, OB>{

	private Pipe<I> inputPipe;
	private Pipe<OA> outputPipeA;
	private Pipe<OB> outputPipeB;
	
	
	public DoubleOutputFilterImpl(Pipe<I> inputPipe, Pipe<OA> outputPipeA, Pipe<OB> outputPipeB) {
		super();
		this.inputPipe = inputPipe;
		this.outputPipeA = outputPipeA;
		this.outputPipeB = outputPipeB;
	}

	@Override
	public void process() {
		if(this.inputPipe.canRetrieve()) {
			transform(inputPipe, outputPipeA, outputPipeB);
		}
		
		else {
			this.outputPipeA.closeForWritting();
			this.outputPipeB.closeForWritting();
		}
		
	}

}
