package model;

import java.util.ArrayList;

import org.pcap4j.util.MacAddress;

public class CapturasService {
	
	ArrayList<Captura> capturas;	//esto está mal pero ehm
	
	public CapturasService(ArrayList<Captura> capturas){
		
		this.capturas=capturas;
	}
	
	public ArrayList<MacAddress> getMacAddressesDetectedBy(AccessPoint ap){
		
		ArrayList<MacAddress> macAddresses = new ArrayList<MacAddress>();
		
		for (Captura c : capturas){
			if(c.getAccessPoint().equals(ap)){	
				for(Package p : c.getPaquetes()){
//					macAddresses.add(p.getMacAddress());   falta mergear el objeto package y MacAddress a development
				}
			}
		}
		
		return macAddresses;
		
	}
	
	
	public ArrayList<AccessPoint> getAPsQueDetectaron( MacAddress macAddress){
		
		ArrayList<AccessPoint> aps = new ArrayList<AccessPoint>();
			
			for (Captura c : capturas){
				
				for (Package p : c.getPaquetes()){
					
//					if (p.getMacAddress.equals(macAddress)){     //lo mismo que arriba
						
//						aps.add(c.getAccessPoint());
						
//					}
				}
			}
		return aps;
		
	}
}
