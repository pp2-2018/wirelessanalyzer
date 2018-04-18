package packageBuilder;

import java.nio.ByteBuffer;

import model.RawPackage;
import model.TimeStamp;

public class TimeStampConfigurationBuiler implements ConfigurationBuilder{

	private ByteSegment segment;
	
	public TimeStampConfigurationBuiler(ByteSegment segment) {

		this.segment = segment;
		
	}
	
	@Override
	public PackageBuilderConfiguration build(RawPackage rawPackage) {
		ByteBuffer byteBuffer = ByteBuffer.wrap(rawPackage.getSegment(segment));
		
		long unixTime = byteBuffer.getLong();
		TimeStamp timeStamp = new TimeStamp(unixTime);
		
		return new TimeStampConfiguration(timeStamp);
	}

}
