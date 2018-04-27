package pipeAndFilter.sink.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import negocio.iface.AnalyticsObserver;
import negocio.iface.Obserbable;
import negocio.iface.TestObserver;
import pipeAndFilter.Pipe;
import pipeAndFilter.Sink;
import pipeAndFilter.impl.SinkImpl;

public class TestSink<T> extends SinkImpl<T> implements Obserbable<TestObserver>{

    private Function<T, Boolean> toStringFunction;
    private List<TestObserver> observers;
    boolean result = false;

    public TestSink(Pipe<T> inputPipe, Function<T, Boolean> toStringFunction) {
        super(inputPipe);
        this.toStringFunction = toStringFunction;
        this.observers = new ArrayList<>();


    }

    @Override
    public void take(Pipe<T> input) {

        T inputObj = input.retireve();
        result = toStringFunction.apply(inputObj);
        notifyAllObservers();
    }

    public boolean isResult() {
        return result;
    }


    @Override
    public void addObserver(TestObserver o) {
        this.observers.add(o);
    }

    @Override
    public void notifyAllObservers() {

        for (TestObserver o : observers) {
            o.update(this);
        }

    }

}
