package pipeAndFilter.impl;

import java.util.ArrayList;
import java.util.List;

import pipeAndFilter.Pipe;

public class PipeSystem {

	private List<Pipe<?>> pipes;
	
	public PipeSystem() {
		
		this.pipes = new ArrayList<>();
		
	}
	
	public void addPipe(Pipe<?> pipe) {
		this.pipes.add(pipe);
	}
	
	public boolean canRetrieveForSomeone() {
		boolean toRet = false;
		
		for (Pipe<?> pipe : pipes) {
			toRet = pipe.canRetrieve()||toRet;
		}
		
		return toRet;
		
	}
	
}
