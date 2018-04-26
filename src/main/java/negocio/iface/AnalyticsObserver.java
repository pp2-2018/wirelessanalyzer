package negocio.iface;

import pipeAndFilter.sink.snifferDetectedMac.SnifferDetectedMac;

public interface AnalyticsObserver extends Observer{

	public void update(SnifferDetectedMac observable);
	
}
