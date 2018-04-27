package rawPacageFactory;

import java.util.List;

import model.RawPackage;

public class RawPacageFactory {

	public RawPackage getFromBytes(byte[] bytes) {
		return new RawPackage(bytes);
	}
	
	public RawPackage getFromList(List<Byte> bytes) {
		
		byte[] array = new byte[bytes.size()];
		
		for(int i = 0; i < bytes.size(); i++)
			array[i] = bytes.get(i);
		
		return this.getFromBytes(array);
	}
	
}
