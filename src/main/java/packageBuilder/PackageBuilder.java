package packageBuilder;

import java.nio.ByteBuffer;
import java.util.List;

import model.Package;
import model.RawPackage;
import model.TimeStamp;

public class PackageBuilder {

	List<ConfigurationBuilder> configuration;
	public TimeStamp timestamp;
	
	
	public PackageBuilder(List<ConfigurationBuilder> configuration) {
		this.configuration = configuration;
	}
	
	public Package build(RawPackage rawPackage) {
		
		configuration.stream()
		.map(c -> c.build(rawPackage))
		.forEach(c -> c.configure(this));
		
		return new Package(timestamp);
		
	}
	
	public void configure(TimeStampConfiguration c) {
		this.timestamp = c.getTimestamp();
	}
	
}
