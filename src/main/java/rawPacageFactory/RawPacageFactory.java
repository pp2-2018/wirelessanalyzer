package rawPacageFactory;

import model.RawPackage;

public class RawPacageFactory {

	public RawPackage getFromBytes(byte[] bytes) {
		return new RawPackage(bytes);
	}
	
}
