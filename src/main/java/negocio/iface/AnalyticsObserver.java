package negocio.iface;

import pipeAndFilter.impl.SinkImpl;
import pipeAndFilter.sink.Test.TestSink;
import pipeAndFilter.sink.snifferDetectedMac.SnifferDetectedMac;

public interface AnalyticsObserver extends Observer{

	public void update(SnifferDetectedMac observable);
}
