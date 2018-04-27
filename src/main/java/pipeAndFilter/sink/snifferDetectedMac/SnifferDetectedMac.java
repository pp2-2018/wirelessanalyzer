package pipeAndFilter.sink.snifferDetectedMac;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import model.Capture;
import model.Package;
import model.device.MacAddress;
import model.device.roles.Sniffer;
import negocio.AnalyticsObserver;
import pipeAndFilter.Pipe;
import pipeAndFilter.impl.SinkImpl;

public class SnifferDetectedMac extends SinkImpl<Capture>{

	private List<Capture> buffer;
	private List<Sniffer> sniffer;
	private MacAddress macAddress;
	private Observable observableComposite;
	
	public SnifferDetectedMac(Pipe<Capture> inputPipe, MacAddress macAddress) {
		super(inputPipe);
		
		buffer = new ArrayList<>();
		this.macAddress = macAddress;
		this.sniffer = new ArrayList<Sniffer>();
		this.observableComposite = new Observable();
		
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
		
		this.observableComposite.notifyObservers();
	}
	
	public void addObserver(Observer o) {
		this.observableComposite.addObserver(o);
	}

	public List<Sniffer> getSniffers() {
		return sniffer;
	}
	
	public void retrieveData(AnalyticsObserver observer) {
		
		observer.putData(this.getSniffers());
		
	}

}
