package packageBuilder;

import java.nio.ByteBuffer;

import model.Package;
import model.RawPackage;
import model.TimeStamp;

public class PackageBuilder {

	//TODO pasar a una clase PackageBuildingConfiguration
	public ByteSegment timeStampSegment;
	
	
	public PackageBuilder(ByteSegment timeStampSegment) {
		this.timeStampSegment = timeStampSegment;
	}
	
	public Package build(RawPackage rawPackage) {
		
		ByteBuffer byteBuffer = ByteBuffer.wrap(rawPackage.getSegment(timeStampSegment));
		
		long unixTime = byteBuffer.getLong();
		TimeStamp timeStamp = new TimeStamp(unixTime);
		
		
		return new Package(timeStamp);
		
	}
	
}