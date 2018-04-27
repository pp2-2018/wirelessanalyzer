package negocio;

import java.util.List;

import model.device.roles.Sniffer;
import negocio.iface.Obserbable;
import negocio.iface.Observer;
import pipeAndFilter.impl.SinkImpl;
import pipeAndFilter.sink.Test.TestSink;
import pipeAndFilter.sink.snifferDetectedMac.SnifferDetectedMac;

public class AnalyticsObserver implements negocio.iface.AnalyticsObserver{

	@Override
	public void update(SnifferDetectedMac observable) {
		observable.getSniffers().forEach(s -> System.out.println(s));
		
	}


}
