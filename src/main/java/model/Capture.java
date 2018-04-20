package model;

import model.device.roles.Sniffer;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Capture {
	
	private Sniffer Sniffer;
	private LocalDateTime start;
	private LocalDateTime end;
	private ArrayList<Package> packages;
	
	public Capture(Sniffer Sniffer, LocalDateTime start, LocalDateTime end){
		
		this.setSniffer(Sniffer);
		this.setStart(start);
		this.setEnd(end);
		this.packages = new ArrayList<Package>();
	}
	
	public void addPackages(Package paquete){
		packages.add(paquete);
		return;
	}
	
	public ArrayList<Package> getPackages(){
		return this.packages;
	}

	public Sniffer getSniffer() {
		return Sniffer;
	}

	public void setSniffer(Sniffer Sniffer) {
		this.Sniffer = Sniffer;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

}
