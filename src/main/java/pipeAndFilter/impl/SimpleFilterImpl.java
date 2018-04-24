package pipeAndFilter.impl;

import pipeAndFilter.Pipe;
import pipeAndFilter.SimpleFilter;

public abstract class SimpleFilterImpl<I, O> implements SimpleFilter<I, O>{
    
    protected Pipe<I> inputPipe;
    protected Pipe<O> outputPipe;
    
    public SimpleFilterImpl(Pipe<I> inputPipe, Pipe<O> outputPipe){
        
        this.inputPipe = inputPipe;
        this.outputPipe = outputPipe;
        
    }
    
    
    public abstract O transform(I input);

    public void process(){
        
        if(this.inputPipe.canRetrieve()){
        
            O output = transform(this.inputPipe.retireve());
        
            this.outputPipe.accept(output);
        }
        
        else{
            this.outputPipe.closeForWritting();
        }
        
    }
    
}
