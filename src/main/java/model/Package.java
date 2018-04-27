package model;

import model.device.MacAddress;
import model.device.roles.Sniffer;

public class Package {

	private TimeStamp timeStamp;
	private MacAddress macAddress;
	private Sniffer sniffer;

	public Sniffer getSniffer() {
		return sniffer;
	}

	public void setSniffer(Sniffer sniffer) {
		this.sniffer = sniffer;
	}

	public Package(TimeStamp timeStamp) {
	
		this.timeStamp = timeStamp;
		
	}

	public Package(TimeStamp timeStamp, MacAddress mac) {
		
		this.timeStamp = timeStamp;
		this.macAddress = mac;
		
	}

	public TimeStamp getTimeStamp() {
		return timeStamp;
	}

	public MacAddress getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(MacAddress macAddress) {
		this.macAddress = macAddress;
	}
}
