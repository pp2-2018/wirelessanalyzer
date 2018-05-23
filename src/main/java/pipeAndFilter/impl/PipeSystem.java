package pipeAndFilter.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import pipeAndFilter.Pipe;

public class PipeSystem<T> implements Iterable<Pipe<T>>{

	private List<Pipe<T>> pipes;
	
	public PipeSystem() {
		
		this.pipes = new ArrayList<>();
		
	}
	
	public void addPipe(Pipe<T> pipe) {
		this.pipes.add(pipe);
	}
	
	public void addPipes(Pipe<T>[] pipes) {
		for (Pipe<T> pipe : pipes) {
			addPipe(pipe);
		}
	}
	
	public boolean canRetrieveForSomeone() {
		boolean toRet = false;
		
		for (Pipe<T> pipe : pipes) {
			toRet = pipe.canRetrieve()||toRet;
		}
		
		return toRet;
		
	}

	@Override
	public Iterator<Pipe<T>> iterator() {
		return this.pipes.iterator();
	}
	
}
