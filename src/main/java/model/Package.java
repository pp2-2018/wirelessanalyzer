package model;

import model.device.MacAddress;

public class Package {

	private TimeStamp timeStamp;
	private MacAddress macAddress;
	
	
	public Package(TimeStamp timeStamp) {
	
		this.timeStamp = timeStamp;
		
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

	@Override
	public String toString() {
		return "Package{" +
				"timeStamp=" + timeStamp +
				", macAddress=" + macAddress +
				'}';
	}
}
