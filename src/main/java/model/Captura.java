package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Captura {
	
	private AccessPoint accessPoint;
	private LocalDateTime inicio;
	private LocalDateTime fin;
	private ArrayList<Package> paquetes;
	
	public Captura( AccessPoint accessPoint, LocalDateTime inicio, LocalDateTime fin){
		
		this.setAccessPoint(accessPoint);
		this.setInicio(inicio);
		this.setFin(fin);
	}
	
	private void addPackages(Package paquete){
		paquetes.add(paquete);
		return;
	}

	public AccessPoint getAccessPoint() {
		return accessPoint;
	}

	public void setAccessPoint(AccessPoint accessPoint) {
		this.accessPoint = accessPoint;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public LocalDateTime getFin() {
		return fin;
	}

	public void setFin(LocalDateTime fin) {
		this.fin = fin;
	}

}
