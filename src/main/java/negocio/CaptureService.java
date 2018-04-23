package negocio;

import java.util.ArrayList;
import java.util.List;

import model.Capture;
import model.HistoriaMacAddress;
import model.TimeFrame;
import model.device.MacAddress;
import model.device.roles.Sniffer;
import model.Package;

public class CaptureService { /*TODO Esta clase me parece que no está bien. Habría que ver una forma de poder
    recuperar todas las cosas que tenemos propuestas en las Users Stories. Podríamos llamarlos criterios de
    búsqueda?? Lo dudo por el hecho de que estamos buscando distintas cosas.*/
	
	private List<Capture> captures; //esto está mal pero ehm
	
	public CaptureService(ArrayList<Capture> captures){
		
		this.captures = captures;
	}
	
	public List<MacAddress> getMacAddressesDetectedBy(Sniffer sniffer){
		
		ArrayList<MacAddress> macAddresses = new ArrayList<MacAddress>();
		
		for (Capture c : captures){
			
			if(c.getSniffer().equals(sniffer)){
				
				for(Package p : c.getPackages()){
					
					macAddresses.add(p.getMacAddress());  
				}
			}
		}
		
		return macAddresses;
		
	}

	public List<Sniffer>  getSniffersThatDetectedThisOnTimeFrame(MacAddress macAddress, TimeFrame timeFrame){
		ArrayList<Sniffer> sniffers = new ArrayList<Sniffer>();

		for (Capture c : captures)

			for (Package p : c.getPackages())

				if(timeFrame.contains(p.getTimeStamp()))

					if(p.getMacAddress().equals(macAddress))

						sniffers.add(c.getSniffer());


		return sniffers;

	}
	
	public List<HistoriaMacAddress> getRegistroDeCapturas (MacAddress macAddress, TimeFrame timeFrame){
		
		ArrayList<HistoriaMacAddress> historico = new ArrayList<HistoriaMacAddress>();
		
		for (Capture c : captures){
			
			for(Package p : c.getPackages()){
				
				if(timeFrame.contains(p.getTimeStamp()) && (p.getMacAddress().equals(macAddress))){
					
					for (HistoriaMacAddress h : historico){
							
						if(h.getFechaHora().equals(p.getTimeStamp())){		//parsear?
								
							h.addSniffer(c.getSniffer());
						}
						
						else{
							
							HistoriaMacAddress historia = new HistoriaMacAddress();
//							historia.setFechaHora(p.getTimeStamp().getUnixTime()); 		//parsear
							historia.addSniffer(c.getSniffer());
							historico.add(historia);
						}
					}
				}	
			}
		}
		
	return historico;
}


	public List<Sniffer> getSniffersThatDetectedThis(MacAddress macAddress){
		
		ArrayList<Sniffer> aps = new ArrayList<Sniffer>();
			
			for (Capture c : captures){
				
				for (Package p : c.getPackages()){
					
					if (p.getMacAddress().equals(macAddress)){
						
						aps.add(c.getSniffer());
						
					}
				}
			}
		return aps;
		
	}
}
