package pipeAndFilter.impl;

import java.util.LinkedList;
import java.util.Queue;

import exceptions.PipeClosedException;
import pipeAndFilter.Pipe;

public class QueuePipe<T> implements Pipe<T>{

    
    private Queue<T> queue;
    private boolean isClosed;
    
    public QueuePipe(){
        this.queue = new LinkedList<T>();
        this.isClosed = false;
    }
    
    public void closeForWritting(){
        this.isClosed = true;
    }
    
    public void accept(T t){
        
        if (this.isClosed)
            throw new PipeClosedException();
            
        this.queue.add(t);
        
    }

    
    public T retireve(){
        
    	if (!this.canRetrieve())
            throw new PipeClosedException();
    	
        return this.queue.poll();
        
    }
    
    public boolean canRetrieve(){
        if(this.isClosed && this.queue.isEmpty())
            return false;
        return true;
    }
    
    public boolean isClosed(){
        return this.isClosed;
    }

	@Override
	public boolean isEmpty() {
		return this.queue.isEmpty();
	}
    
}
