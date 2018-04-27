package packageBuilder;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Package;
import model.RawPackage;
import packageBuilder.ByteSegment;
import packageBuilder.ConfigurationBuilder;
import packageBuilder.PackageBuilder;
import packageBuilder.TimeStampConfigurationBuiler;

public class PackageBuilderTest {

	@Test
	public void buildTest() {
		//Timestamp en los primeros 8 bytes, 000000DC6B747780 -> 946695600000 -> 01/01/2000 00:00:00,0000
		RawPackage rawPackage = new RawPackage(new byte[] {0x00, 0x00, 0x00, (byte) 0xDC, 0x6B, 0x74, 0x77, (byte) 0x80, 0x00, 0x00, (byte) 0xff, (byte) 0xff, (byte) 0xfa});
		ByteSegment timeStampSegent = new ByteSegment(0, 8);
		TimeStampConfigurationBuiler timestampBuilder = new TimeStampConfigurationBuiler(timeStampSegent);
		
		List<ConfigurationBuilder> configurationBuilders = Arrays.asList(timestampBuilder);
		
		PackageBuilder builder = new PackageBuilder(configurationBuilders);
		
		Package package1 = builder.build(rawPackage);
		
		Assert.assertEquals(946695600000L, package1.getTimeStamp().getUnixTime());
		
		
	}
	
	@Test
	public void buildNotEqualTest() {
		//Timestamp en los primeros 8 bytes, 000000DC6B747780 -> 946695600000 -> 01/01/2000 00:00:00,0000
		RawPackage rawPackage = new RawPackage(new byte[] {0x00, 0x00, 0x00, (byte) 0xDC, 0x6B, 0x74, 0x77, (byte) 0x80, 0x00, 0x00, (byte) 0xff, (byte) 0xff, (byte) 0xfa});
		ByteSegment timeStampSegent = new ByteSegment(0, 8);		
		TimeStampConfigurationBuiler timestampBuilder = new TimeStampConfigurationBuiler(timeStampSegent);
		
		List<ConfigurationBuilder> configurationBuilders = Arrays.asList(timestampBuilder);
		
		PackageBuilder builder = new PackageBuilder(configurationBuilders);
		
		Package package1 = builder.build(rawPackage);
		
		Assert.assertNotEquals(946695600001L, package1.getTimeStamp().getUnixTime());
		
		
	}
	
	@Test
	public void bundaryTest() {
		//Timestamp en los primeros 8 bytes, 000000DC6B747780 -> 946695600000 -> 01/01/2000 00:00:00,0000
		//Solo hay 8 bytes para leer
		RawPackage rawPackage = new RawPackage(new byte[] {0x00, 0x00, 0x00, (byte) 0xDC, 0x6B, 0x74, 0x77,  (byte) 0x80});
		ByteSegment timeStampSegent = new ByteSegment(0, 8);		
		TimeStampConfigurationBuiler timestampBuilder = new TimeStampConfigurationBuiler(timeStampSegent);
		
		List<ConfigurationBuilder> configurationBuilders = Arrays.asList(timestampBuilder);
		
		PackageBuilder builder = new PackageBuilder(configurationBuilders);
		
		Package package1 = builder.build(rawPackage);
		
		Assert.assertNotEquals(946695600001L, package1.getTimeStamp().getUnixTime());
	}
	
	@Test
	public void timestampNotInFirstBytes() {
		//Timestamp en los bytes 2 al 10, 000000DC6B747780 -> 946695600000 -> 01/01/2000 00:00:00,0000
		RawPackage rawPackage = new RawPackage(new byte[] {0x00, 0x00, (byte) 0xff, 0x00, 0x00, 0x00, (byte) 0xDC, 0x6B, 0x74, 0x77, (byte) 0x80,(byte) 0xff, (byte) 0xfa});
		ByteSegment timeStampSegent = new ByteSegment(2, 8);		
		TimeStampConfigurationBuiler timestampBuilder = new TimeStampConfigurationBuiler(timeStampSegent);
		
		List<ConfigurationBuilder> configurationBuilders = Arrays.asList(timestampBuilder);
		
		PackageBuilder builder = new PackageBuilder(configurationBuilders);
		
		Package package1 = builder.build(rawPackage);
		
		Assert.assertNotEquals(946695600001L, package1.getTimeStamp().getUnixTime());
		
		
	}
	
}
