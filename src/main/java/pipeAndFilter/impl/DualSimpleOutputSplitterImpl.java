package pipeAndFilter.impl;

import pipeAndFilter.Pipe;
import pipeAndFilter.simpleOutputSplitter;

public class DualSimpleOutputSplitterImpl<T> implements simpleOutputSplitter<T> {

    protected Pipe<T> inputPipe;
    protected Pipe<T> outputPipe;
    protected Pipe<T> outputPipe2;

    public DualSimpleOutputSplitterImpl(Pipe<T> inputPipe, Pipe<T> outputPipe, Pipe<T> outputPipe2){

            this.inputPipe = inputPipe;

            this.outputPipe2 = outputPipe;

    }
    @Override
    public void transport(Pipe<T> input, Pipe<T>... outputs) {
        if(this.inputPipe.canRetrieve()){
            T object = input.retireve();
            for (Pipe<T> pipe: outputs
                 ) {
                pipe.accept(object);
            }
        }

        else{
            this.outputPipe.closeForWritting();
        }


    }



}
