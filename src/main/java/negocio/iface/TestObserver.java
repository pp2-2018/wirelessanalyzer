package negocio.iface;

import pipeAndFilter.sink.Test.TestSink;

public interface TestObserver extends Observer{

	public void update (TestSink<?> observable);
}
