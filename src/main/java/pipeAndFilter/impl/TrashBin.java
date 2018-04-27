package pipeAndFilter.impl;

import pipeAndFilter.Pipe;

public class TrashBin<T> extends SinkImpl<T>{

	public TrashBin(Pipe<T> inputPipe) {
		super(inputPipe);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void take(Pipe<T> input) {
		
		while (input.canRetrieve()) {

			T t = input.retireve();
			t = null;
		}
		
		
	}

}
