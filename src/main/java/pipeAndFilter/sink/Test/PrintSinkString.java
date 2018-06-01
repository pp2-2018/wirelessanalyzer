package pipeAndFilter.sink.Test;

import negocio.iface.Obserbable;
import negocio.iface.TestObserver;
import pipeAndFilter.Pipe;
import pipeAndFilter.impl.SinkImpl;
import pipeAndFilter.parameters.Parametrized;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PrintSinkString extends SinkImpl<String> {

    @Parametrized
    public PrintSinkString() {
        super(null);

    }

    @Override
    public void take(Pipe<String> input) {
        String inputObj = input.retireve();
        System.out.println(inputObj);

    }

    

}
