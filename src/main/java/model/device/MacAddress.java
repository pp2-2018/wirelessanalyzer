package model.device;

import org.pcap4j.util.LinkLayerAddress;


public class MacAddress extends LinkLayerAddress { //TODO Deberíamos revisar esto
	
    public MacAddress(byte[] address){
        super(address);
    }

}
