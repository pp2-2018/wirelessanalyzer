package negocio;

import java.util.ArrayList;
import java.util.List;

import model.Captura;
import model.device.MacAddress;
import model.device.roles.Sniffer;
import model.Package;

public class CapturasService { /*TODO Esta clase me parece que no está bien. Habría que ver una forma de poder
    recuperar todas las cosas que tenemos propuestas en las Users Stories. Podríamos llamarlos criterios de
    búsqueda?? Lo dudo por el hecho de que estamos buscando distintas cosas.*/
	
	private List<Captura> capturas; //esto está mal pero ehm
	
	public CapturasService(ArrayList<Captura> capturas){
		
		this.capturas=capturas;
	}
	
	public List<MacAddress> getMacAddressesDetectedBy(Sniffer sniffer){
		
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

	public List<Sniffer> getAPsQueDetectaron( MacAddress macAddress){
		
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
