package pipeAndFilter.filters.rawPackageFilter;

import java.util.ArrayList;
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

    if (paquete != null){									//caso 1: la cola está vacía
    	
    	if(cola.isEmpty()){
    		cola.add(paquete);
    		if(input.isEmpty()){
    			output.accept(triangular(cola));
        		cola.clear();
    		}
    		return;
    	}
    	
    	if(cola.size()==3){									//caso 3: la cola está llena
    		output.accept(triangular(cola));
    		cola.clear();
    		cola.add(paquete);
    		if(input.isEmpty()){
    			output.accept(triangular(cola));
        		cola.clear();
    		}
    		return;
    	}

    	if(estaEnRangoDeTiempo(paquete) && cola.size()<3){	//caso 2: el paquete se encuentra en el rango de tiempo de la cola y esta no esta llena
    		cola.add(paquete);
    		if(input.isEmpty()){
    			output.accept(triangular(cola));
        		cola.clear();
    		}
    		return;
    	}
    	
    	
    	
    	if(!estaEnRangoDeTiempo(paquete)){					//caso 3: el paquete no se encuentra en el rango de tiempo de la cola
    		output.accept(triangular(cola));
    		cola.clear();
    		cola.add(paquete);
    		if(input.isEmpty()){
    			output.accept(triangular(cola));
        		cola.clear();
    		}
    		return;
    	} 
    }
}



public boolean estaEnRangoDeTiempo(Package p){
	
	if(cola.peek().getTimeStamp().equals(p.getTimeStamp())){
		return true;
	}
	if(p.getTimeStamp().toInstant().isAfter(cola.peek().getTimeStamp().toInstant()) &&
			p.getTimeStamp().toInstant().isBefore(cola.peek().getTimeStamp().toInstant().plusMillis(6))){
		return true;
	}
	return false;
}



public Coordinates triangular(Queue<Package> cola){					//por convencion, el maximo soportado para triangulacion es 3
	
	if(cola.size()==1){
		Coordinates coord = configurationMap.getCoordinates(cola.peek().getSniffer());
		return coord;
	}
	
	if(cola.size()==2){
		
		ArrayList<Package> array = new ArrayList<Package>(cola);
		double coordenadaX = getCoordenadaX(array.get(0), array.get(1)); 
		double coordenadaY = getCoordenadaY(array.get(0), array.get(1));

		return(new Coordinates(coordenadaX, coordenadaY));
	}
	
	if(cola.size()==3){
		
		ArrayList<Package> array = new ArrayList<Package>(cola);
		double coordenadaX;
		double coordenadaY;
		
		if(getInterseccionX(array.get(0), array.get(1))<= getInterseccionX(array.get(0), array.get(2))){
			coordenadaX = getCoordenadaX(array.get(0), array.get(1));
		}else{
			coordenadaX = getCoordenadaX(array.get(0), array.get(2));}
		
		if(getInterseccionY(array.get(0), array.get(1))<= getInterseccionY(array.get(0), array.get(2))){
		coordenadaY = getCoordenadaY(array.get(0), array.get(1));
		}else{
			coordenadaY = getCoordenadaY(array.get(0), array.get(2));}
		return(new Coordinates(coordenadaX, coordenadaY));
	}
	else{
		throw new RuntimeException("Triangulación de a más de tres elementos no soportada");
	}
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
