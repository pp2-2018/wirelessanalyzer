package negocio;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import model.device.roles.Sniffer;
import pipeAndFilter.sink.snifferDetectedMac.SnifferDetectedMac;

public class AnalyticsObserver implements Observer{

	@Override
	public void update(Observable obs, Object arg1) {
		
		
		
	}

	public void putData(List<Sniffer> sniffers) {
		
		for (Sniffer sniffer : sniffers) {
			System.out.println(sniffer.getDevice().getMac().toString());
		}
		
	}

}
