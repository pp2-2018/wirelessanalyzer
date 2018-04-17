package model;

public class AccessPoint {

	private Byte[] macAp;
	
	public AccessPoint( Byte[] macAp){
		this.setMacAp(macAp);
	}

	public Byte[] getMacAp() {
		return macAp;
	}

	public void setMacAp(Byte[] macAp) {
		this.macAp = macAp;
	}
	
	
	
}
