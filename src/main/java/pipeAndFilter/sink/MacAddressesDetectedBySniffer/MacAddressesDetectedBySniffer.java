package pipeAndFilter.sink.MacAddressesDetectedBySniffer;

import java.util.ArrayList;
import java.util.List;

import model.Capture;
import model.Package;
import model.device.MacAddress;
import model.device.roles.Sniffer;
import negocio.iface.Obserbable;
import pipeAndFilter.Pipe;
import pipeAndFilter.impl.SinkImpl;

public class MacAddressesDetectedBySniffer extends SinkImpl<Capture> implements Obserbable<negocio.iface.AnalyticsObserver>{
	
	private List<Capture> buffer;
	private List<MacAddress> macs;
	private Sniffer sniffer;
	private List<negocio.iface.AnalyticsObserver> observers;
	
	public MacAddressesDetectedBySniffer(Pipe<Capture> inputPipe, Sniffer sniffer) {
		super(inputPipe);
		
		buffer = new ArrayList<>();
		this.sniffer = sniffer;
		this.macs = new ArrayList<MacAddress>();
		this.observers = new ArrayList<>();
	}

	@Override
	public void take(Pipe<Capture> input) {
		
		if(input.canRetrieve()) {
			buffer.add(input.retireve());
			createMacsList();
		}
		else {
			createMacsList();
		}
		
	}
	
	private void createMacsList() {
		for (Capture c : buffer){
			
			for (Package p : c.getPackages()){
				if (c.getSniffer().equals(sniffer)){
					macs.add(p.getMacAddress());
					
				}
			}
		}
		
		//this.notifyAllObservers();
	}
	
	public List<MacAddress> getMacs() {
		return macs;
	}

	@Override
	public void notifyAllObservers() {
		
		//throw new RuntimeException("NOT IMPLMENTED");
		
	}

	@Override
	public void addObserver(negocio.iface.AnalyticsObserver o) {
		
		//this.observers.add(o);
		
	}
}
