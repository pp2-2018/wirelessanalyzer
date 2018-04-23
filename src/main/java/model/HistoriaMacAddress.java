package model;

import java.time.LocalDateTime;
import java.util.List;

import model.device.roles.Sniffer;

public class HistoriaMacAddress {
	
	private LocalDateTime fechaHora;
	private List<Sniffer> sniffers;
	
	public HistoriaMacAddress(){
		
	}
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}
	public List<Sniffer> getSniffers() {
		return sniffers;
	}
	public void addSniffer(Sniffer sniffer) {
		this.sniffers.add(sniffer);
	}
}
