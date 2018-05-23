package pipeAndFilter.impl;

import java.util.ArrayList;
import java.util.List;

import pipeAndFilter.Pipe;
import pipeAndFilter.Processable;

public class FilterSystem {

	private List<Processable> filters;
	private PipeSystem<Object> pipeSystem;
	
	public FilterSystem() {
		filters = new ArrayList<>();
		pipeSystem = new PipeSystem<>();
		
	}
	
	@SuppressWarnings("unchecked")
	public void addFilter(Processable filter) {
		this.filters.add(filter);
		this.pipeSystem.addPipes((Pipe<Object>[]) filter.getPipes());
		
	}
	
	public void run() {
		while(pipeSystem.canRetrieveForSomeone()) {
			for (Processable processable : filters) {
				processable.process();
			}
		}
	}
	
	
}
