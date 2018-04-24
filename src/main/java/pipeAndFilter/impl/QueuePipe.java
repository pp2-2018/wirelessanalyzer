package pipeAndFilter.impl;

import java.util.LinkedList;
import java.util.Queue;

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
            throw new RuntimeException("Pipe closed.");
            
        this.queue.add(t);
        
    }

    
    public T retireve(){
        
        return this.queue.poll();
        
    }
    
    public boolean canRetrieve(){
        if(this.isClosed && this.queue.element() == null)
            return false;
        return true;
    }
    
    public boolean isClosed(){
        return this.isClosed;
    }
    
}
