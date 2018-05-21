package pipeAndFilter.filters.rawPackageFilter;

import java.util.LinkedList;
import java.util.Queue;

import model.Coordinates;
import model.Package;
import model.device.ConfigurationMap;
import pipeAndFilter.Pipe;
import pipeAndFilter.impl.SimpleFilterImpl;

public class TriangulatorBySniffer extends SimpleFilterImpl<Package,Coordinates> { //que tipo devuelve?
	
	private Queue<Package> cola;
	private ConfigurationMap configurationMap;
	
	public TriangulatorBySniffer(Pipe<Package> inputPipe, Pipe<Coordinates> outputPipe) {
		
		super(inputPipe, outputPipe);
		cola = new LinkedList<Package>();
	}

	
@Override
public void transform(Pipe<Package> input, Pipe<Coordinates> output) {
	
	Package paquete = input.retireve();
    if (paquete != null){
    	
    	if(cola.isEmpty()){
    		cola.add(paquete);
    	}
    	
    	if(cola.peek().getTimeStamp().equals(paquete.getTimeStamp())){		//debería tener un rango de aceptación de tiempo?
    		cola.add(paquete);
    	}
    	
    	if(!cola.peek().getTimeStamp().equals(paquete.getTimeStamp())){

    		output.accept(triangular(cola));
    		cola.clear();
    	}
    
    }
}

public Coordinates triangular(Queue<Package> cola){
	
	if(cola.size()==1){
		Coordinates coord = configurationMap.getCoordinates(cola.peek().getSniffer());
		return coord;
	}
	
	Package packageA = cola.poll();
	Package packageB = cola.poll();
	double sumaRangos = packageA.getSniffer().getRangeInMeters() + packageB.getSniffer().getRangeInMeters();
	
	//
	
	double distanciaX = Math.sqrt(Math.pow((configurationMap.getCoordinates(packageB.getSniffer()).getLat()
			- configurationMap.getCoordinates(packageA.getSniffer()).getLat()), 2));	
			
	double coordX = (sumaRangos - distanciaX)/2;
	
	//

	double distanciaY = Math.sqrt(Math.pow((configurationMap.getCoordinates(packageB.getSniffer()).getLng()
			- configurationMap.getCoordinates(packageA.getSniffer()).getLng()), 2));	
			
	double coordY = (sumaRangos - distanciaY)/2;
	
	return(new Coordinates(coordX, coordY));
	
	}
}
