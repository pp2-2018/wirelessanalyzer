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
    		return;
    	}

    	if(cola.peek().getTimeStamp().equals(paquete.getTimeStamp())){
    		
    		cola.add(paquete);
    		return;
    	}
    	
    	if(!cola.peek().getTimeStamp().equals(paquete.getTimeStamp())){

    		output.accept(triangular(cola));
    		cola.clear();
    		cola.add(paquete);
    		return;
    	} 
    }
}

public Coordinates triangularDeTres(Queue<Package> cola){
	return null;
	
}


public Coordinates triangular(Queue<Package> cola){	
	
	if(cola.size()==1){
		Coordinates coord = configurationMap.getCoordinates(cola.peek().getSniffer());
		return coord;
	}
	
	if(cola.size()==2){
		
		Object[] array = cola.toArray();
		Package packageA = (Package) array[0];
		Package packageB = (Package) array[1];

		double coordenadaX = getCoordenadaX(packageA, packageB); 
		double coordenadaY = getCoordenadaY(packageA, packageB);

		return(new Coordinates(coordenadaX, coordenadaY));
	}
	
	if(cola.size()==3){
		
		Object[] array = cola.toArray();
		Package packageA = (Package) array[0];
		Package packageB = (Package) array[1];
		Package packageC = (Package) array[2];
		
		double coordenadaX;
		double coordenadaY;
		
		if(getInterseccionX(packageA, packageB)<= getInterseccionX(packageA, packageC)){
			coordenadaX = getCoordenadaX(packageA, packageB);
		}else{
			coordenadaX = getCoordenadaX(packageA, packageC);
		}
		
		if(getInterseccionY(packageA, packageB)<= getInterseccionY(packageA, packageC)){
			coordenadaY = getCoordenadaY(packageA, packageB);
		}else{
			coordenadaY = getCoordenadaY(packageA, packageC);
		}
		return(new Coordinates(coordenadaX, coordenadaY));
		
	}
	return null;
}



public double getInterseccionX(Package packageA, Package packageB){
	
	double sumaRangos = packageA.getSniffer().getRangeInMeters() + packageB.getSniffer().getRangeInMeters();
	double distanciaX = Math.sqrt(Math.pow((configurationMap.getCoordinates(packageB.getSniffer()).getLat()
			- configurationMap.getCoordinates(packageA.getSniffer()).getLat()), 2));	
	
	if(distanciaX==0){
		return(0);
	}
	double interseccionX = (sumaRangos - distanciaX)/2;
	return interseccionX;
	
}


public double getCoordenadaX(Package packageA, Package packageB){
	
	double interseccionX = getInterseccionX(packageA, packageB);
	double coordenadaX;
	
	if(interseccionX == 0){
		return configurationMap.getCoordinates(packageB.getSniffer()).getLat();
	}

	if(configurationMap.getCoordinates(packageB.getSniffer()).getLat() <= configurationMap.getCoordinates(packageA.getSniffer()).getLat()){
		coordenadaX = configurationMap.getCoordinates(packageB.getSniffer()).getLat() + packageB.getSniffer().getRangeInMeters() - interseccionX;	
	}
	else {
		coordenadaX = configurationMap.getCoordinates(packageA.getSniffer()).getLat() + packageA.getSniffer().getRangeInMeters() - interseccionX;	
	}
	return coordenadaX;
}



public double getInterseccionY(Package packageA, Package packageB){
	
	double sumaRangos = packageA.getSniffer().getRangeInMeters() + packageB.getSniffer().getRangeInMeters();

	double distanciaY = Math.sqrt(Math.pow((configurationMap.getCoordinates(packageB.getSniffer()).getLng()
			- configurationMap.getCoordinates(packageA.getSniffer()).getLng()), 2));	

	if(distanciaY==0){
		return(0);
	}
	double interseccionY = (sumaRangos - distanciaY)/2;
	return interseccionY;	
}



public double getCoordenadaY(Package packageA, Package packageB){
	
	double interseccionY = getInterseccionY(packageA, packageB);
	double coordenadaY;
	
	if(interseccionY == 0){
		return configurationMap.getCoordinates(packageB.getSniffer()).getLng();
	}

	if(configurationMap.getCoordinates(packageB.getSniffer()).getLng() <= configurationMap.getCoordinates(packageA.getSniffer()).getLng()){
		coordenadaY = configurationMap.getCoordinates(packageB.getSniffer()).getLng() +  packageB.getSniffer().getRangeInMeters() - interseccionY;	
	}
	else {
		coordenadaY = configurationMap.getCoordinates(packageA.getSniffer()).getLng() + packageA.getSniffer().getRangeInMeters() - interseccionY;	
	}
	return coordenadaY;
	}
}
