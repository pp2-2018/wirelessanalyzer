package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.device.roles.Sniffer;

public class HistoriaMacAddress {
	
	private LocalDateTime fechaHora;
	private List<Sniffer> sniffers;
	
	public HistoriaMacAddress(){
		this.sniffers = new ArrayList<Sniffer>();
		
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaHora == null) ? 0 : fechaHora.hashCode());
		result = prime * result + ((sniffers == null) ? 0 : sniffers.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HistoriaMacAddress other = (HistoriaMacAddress) obj;
		if (fechaHora == null) {
			if (other.fechaHora != null)
				return false;
		} else if (!fechaHora.equals(other.fechaHora))
			return false;
		if (sniffers == null) {
			if (other.sniffers != null)
				return false;
		} else if (!sniffers.equals(other.sniffers))
			return false;
		return true;
	}
	
	 @Override
	    public String toString() {
	        return " (Historia: "+ fechaHora + " " + sniffers + ")" ;
	    }

	
}
