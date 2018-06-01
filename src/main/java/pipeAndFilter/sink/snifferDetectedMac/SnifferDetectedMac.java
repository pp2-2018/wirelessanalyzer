package pipeAndFilter.sink.snifferDetectedMac;

import java.util.ArrayList;
import java.util.List;

import model.Capture;
import model.Package;
import model.device.MacAddress;
import model.device.roles.Sniffer;
import negocio.AnalyticsObserver;
import negocio.iface.Obserbable;
import negocio.iface.Observer;
import pipeAndFilter.Pipe;
import pipeAndFilter.impl.SinkImpl;
import pipeAndFilter.parameters.Parametrized;

public class SnifferDetectedMac extends SinkImpl<Capture> implements Obserbable<negocio.iface.AnalyticsObserver>{

	private List<Capture> buffer;
	private List<Sniffer> sniffer;
	private MacAddress macAddress;
	private List<negocio.iface.AnalyticsObserver> observers;
	
	public SnifferDetectedMac(Pipe<Capture> inputPipe, MacAddress macAddress) {
		super(inputPipe);
		
		buffer = new ArrayList<>();
		this.macAddress = macAddress;
		this.sniffer = new ArrayList<Sniffer>();
		this.observers = new ArrayList<>();
	}
	
	@Parametrized
	public SnifferDetectedMac(MacAddress macAddress) {
		this(null, macAddress);
	}

	@Override
	public void take(Pipe<Capture> input) {

		if(input.canRetrieve()) {
			buffer.add(input.retireve());
		}
		else {
			createSnifferList();
		}
		
	}
	
	private void createSnifferList() {
		for (Capture c : buffer){
			
			for (Package p : c.getPackages()){
				if (p.getMacAddress().equals(macAddress)){
					sniffer.add(c.getSniffer());
					
				}
			}
		}
		
		this.notifyAllObservers();
	}

	public List<Sniffer> getSniffers() {
		return sniffer;
	}

	@Override
	public void addObserver(negocio.iface.AnalyticsObserver o) {
		
		this.observers.add(o);
		
	}

	@Override
	public void notifyAllObservers() {
		
		for (negocio.iface.AnalyticsObserver o : observers) {
			o.update(this);
		}
		
	}

}
