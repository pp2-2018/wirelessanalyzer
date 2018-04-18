package negocio;

import java.util.ArrayList;

import model.Captura;
import model.device.MacAddress;
import model.device.roles.Sniffer;
import model.Package;

public class CapturasService {
	
	ArrayList<Captura> capturas;	//esto está mal pero ehm
	
	public CapturasService(ArrayList<Captura> capturas){
		
		this.capturas=capturas;
	}
	
	public ArrayList<MacAddress> getMacAddressesDetectedBy(Sniffer sniffer){
		
		ArrayList<MacAddress> macAddresses = new ArrayList<MacAddress>();
		
		for (Captura c : capturas){
			
			if(c.getSniffer().equals(sniffer)){
				
				for(Package p : c.getPaquetes()){
					
					macAddresses.add(p.getMacAddress());  
				}
			}
		}
		
		return macAddresses;
		
	}
	
	
	public ArrayList<Sniffer> getAPsQueDetectaron( MacAddress macAddress){
		
		ArrayList<Sniffer> aps = new ArrayList<Sniffer>();
			
			for (Captura c : capturas){
				
				for (Package p : c.getPaquetes()){
					
					if (p.getMacAddress().equals(macAddress)){
						
						aps.add(c.getSniffer());
						
					}
				}
			}
		return aps;
		
	}
}
