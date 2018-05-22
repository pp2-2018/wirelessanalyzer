package pipeAndFilter.filters.rawPackageFilter;

import java.util.LinkedList;
import java.util.Queue;

import model.Coordinates;
import model.Package;
import model.device.ConfigurationMap;

import negocio.ConfigurationManager;
import pipeAndFilter.Pipe;
import pipeAndFilter.impl.SimpleFilterImpl;

public class TriangulatorBySniffer extends SimpleFilterImpl<Package,Coordinates> {
	
	private Queue<Package> cola;
	private ConfigurationMap configurationMap;
	
	public TriangulatorBySniffer(Pipe<Package> inputPipe, Pipe<Coordinates> outputPipe) {
		
		super(inputPipe, outputPipe);
		cola = new LinkedList<Package>();
		configurationMap = ConfigurationManager.load();
	}


	
@Override
public void transform(Pipe<Package> input, Pipe<Coordinates> output) {
	
	Package paquete = input.retireve();
    if (paquete != null){
    	
    	if(cola.isEmpty()){
    		cola.add(paquete);
    	}
    	
    	if(cola.peek().getTimeStamp().equals(paquete.getTimeStamp())){
    		cola.add(paquete);
    	}
    	
    	if(!cola.peek().getTimeStamp().equals(paquete.getTimeStamp())){

    		output.accept(triangular(cola));
    		cola.clear();
    		cola.add(paquete);
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

	double coordenadaX = getCoordenadaX(packageA, packageB); 
	double coordenadaY = getCoordenadaY(packageA, packageB);

	return(new Coordinates(coordenadaX, coordenadaY));
	
	}



public double getCoordenadaX(Package packageA, Package packageB){
	
	double sumaRangos = packageA.getSniffer().getRangeInMeters() + packageB.getSniffer().getRangeInMeters();
	double distanciaX = Math.sqrt(Math.pow((configurationMap.getCoordinates(packageB.getSniffer()).getLat()
			- configurationMap.getCoordinates(packageA.getSniffer()).getLat()), 2));	
	
	double interseccionX = (sumaRangos - distanciaX)/2;
	
	double coordenadaX;

	if(configurationMap.getCoordinates(packageB.getSniffer()).getLat() <= configurationMap.getCoordinates(packageA.getSniffer()).getLat()){
		coordenadaX = configurationMap.getCoordinates(packageB.getSniffer()).getLat() + interseccionX;	
	}
	else {
		coordenadaX = configurationMap.getCoordinates(packageA.getSniffer()).getLat() + interseccionX;	
	}
	return coordenadaX;
}



public double getCoordenadaY(Package packageA, Package packageB){
	
	double sumaRangos = packageA.getSniffer().getRangeInMeters() + packageB.getSniffer().getRangeInMeters();
	double distanciaY = Math.sqrt(Math.pow((configurationMap.getCoordinates(packageB.getSniffer()).getLng()
			- configurationMap.getCoordinates(packageA.getSniffer()).getLng()), 2));	
	
	double interseccionY = (sumaRangos - distanciaY)/2;

	double coordenadaY;

	if(configurationMap.getCoordinates(packageB.getSniffer()).getLng() <= configurationMap.getCoordinates(packageA.getSniffer()).getLng()){
		coordenadaY = configurationMap.getCoordinates(packageB.getSniffer()).getLng() + interseccionY;	
	}
	else {
		coordenadaY = configurationMap.getCoordinates(packageA.getSniffer()).getLng() + interseccionY;	
	}
	return coordenadaY;
}

}
