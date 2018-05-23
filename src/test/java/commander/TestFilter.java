package commander;

import pipeAndFilter.Pipe;
import pipeAndFilter.SimpleFilter;
import pipeAndFilter.impl.SimpleFilterImpl;
import pipeAndFilter.parameters.Parametrized;

public class TestFilter extends SimpleFilterImpl {

    @Parametrized
    public TestFilter() {
        super(null, null);
    }

    @Override
    public void transform(Pipe input, Pipe output) {

    }
}
