package pipeAndFilter.filters.rawPackageFilter;

import exceptions.IllegalPipeAndFilterCompositionException;
import pipeAndFilter.Pipe;
import pipeAndFilter.Processable;
import pipeAndFilter.impl.SimpleFilterImpl;
import validator.FilterCompabilityValidator;

import java.util.ArrayDeque;
import java.util.Queue;

public class FiltersCompatibilityFilter extends SimpleFilterImpl<Processable, Processable> {

    private FilterCompabilityValidator compabilityValidator;
    private Queue<Processable> queueFilters;


    public FiltersCompatibilityFilter(Pipe<Processable> inputPipe, Pipe<Processable> outputPipe) {
        super(inputPipe, outputPipe);
        compabilityValidator = new FilterCompabilityValidator();
        queueFilters = new ArrayDeque<>();
    }

    @Override
    public void transform(Pipe<Processable> input, Pipe<Processable> output) {
        Processable entryFilter = input.retireve();
        Processable headFilter;



        if(queueFilters.isEmpty()){
            queueFilters.add(entryFilter);
            return;
        }

        queueFilters.add(entryFilter);

        headFilter = queueFilters.poll();

        if(!compabilityValidator.validateCompability(headFilter,entryFilter))
            throw new IllegalPipeAndFilterCompositionException("Output type of " + headFilter.getClass()+ " class it is not " +
                    "equals to input type of " + entryFilter.getClass() + " class");

        output.accept(headFilter);

    }

}

