package negocio;

import java.util.*;
import java.util.stream.Stream;

import model.Capture;
import model.HistoriaMacAddress;
import model.TimeFrame;
import model.device.MacAddress;
import model.device.roles.Sniffer;
import model.Package;
import model.Pair;



public class CaptureService { /*TODO Esta clase me parece que no está bien. Habría que ver una forma de poder
    recuperar todas las cosas que tenemos propuestas en las Users Stories. Podríamos llamarlos criterios de
    búsqueda?? Lo dudo por el hecho de que estamos buscando distintas cosas.*/
	
	private List<Capture> captures; //esto está mal pero ehm
	
	public CaptureService(ArrayList<Capture> captures){
		
		this.captures = captures;
	}




	private HashMap<MacAddress,Integer> getMacsFoundBySniffer(HashMap<MacAddress,Integer> alreadyFoundMacs,Capture capture ){
        HashMap<MacAddress,Integer> ret=(HashMap<MacAddress,Integer>)alreadyFoundMacs.clone();

		for (Package pkg: capture.getPackages()
			 ) {
		    MacAddress mac = pkg.getMacAddress();

			if(!ret.containsKey(mac))
                ret.put(mac,1);

			else {
                ret.put(mac, ret.get(mac) + 1);
            }


		}

		return ret;

	}


	public ArrayList<Pair<Sniffer,Integer>> amountOfDetectionsPerSniffer(){
        ArrayList<Pair<Sniffer,Integer>> ret = new ArrayList<>();
        for (Object uncasted:
                detectionsPerSniffer().toArray()) {
            Map.Entry<Sniffer,HashMap<MacAddress,Integer>>
                    entry= (Map.Entry<Sniffer,HashMap<MacAddress,Integer>>) uncasted;

            ret.add(new Pair<>(entry.getKey(),entry.getValue().size()));


        }
	    return  ret;
    }



	public Stream<Map.Entry<Sniffer,HashMap<MacAddress,Integer>>> detectionsPerSniffer(){
		HashMap<Sniffer,HashMap<MacAddress,Integer>> unsortedMap = new HashMap<>();

        //System.out.println(captures);
		for (Capture capture:captures) {

            Sniffer sniffer = capture.getSniffer();


            if (!unsortedMap.containsKey(sniffer)) {  //If not on Map add and initialize array
          //      System.out.println("New Sniffer!!...adding " + sniffer);
                unsortedMap.put(sniffer, new HashMap<>());
            }
            else{
        //        System.out.print("Sniffer Already Registered!! Old Values= ");
            }
            HashMap<MacAddress,Integer> oldCapturedMacs = unsortedMap.get(sniffer);

        //    System.out.println(oldCapturedMacs);

            HashMap<MacAddress,Integer> oldPlusNewlyFoundMacs =
                    getMacsFoundBySniffer(oldCapturedMacs, capture); //If not on Old Macs array adds new ones

       //     System.out.println("Captured! "+oldPlusNewlyFoundMacs);
            unsortedMap.put(sniffer, oldPlusNewlyFoundMacs);
        }
  //     System.out.println(unsortedMap);
        Stream<Map.Entry<Sniffer, HashMap<MacAddress, Integer>>> sorted;

        sorted = unsortedMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(new Comparator<HashMap<MacAddress, Integer>>() {
                    @Override
                    public int compare(HashMap<MacAddress, Integer> o1, HashMap<MacAddress, Integer> o2) {
                        return o2.size()-o1.size();
                    }
                }));


        return sorted;

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
