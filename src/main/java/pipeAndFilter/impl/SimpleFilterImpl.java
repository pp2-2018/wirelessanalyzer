package pipeAndFilter.impl;

import java.util.List;

import pipeAndFilter.Pipe;
import pipeAndFilter.SimpleFilter;

public abstract class SimpleFilterImpl<I, O> implements SimpleFilter<I, O>{
    
    protected Pipe<I> inputPipe;
    protected Pipe<O> outputPipe;
    
    public SimpleFilterImpl(Pipe<I> inputPipe, Pipe<O> outputPipe){
        
        this.inputPipe = inputPipe;
        this.outputPipe = outputPipe;
        
    }

    public void process(){
        
        if(this.inputPipe.canRetrieve()){
        
            this.transform(inputPipe, outputPipe);
        }
        
        else{
            this.outputPipe.closeForWritting();
        }
        
    }

    public Class getInputType(){
        return inputPipe.getClass();
    }

    public Class getOutputType(){
        return outputPipe.getClass();
    }

}
