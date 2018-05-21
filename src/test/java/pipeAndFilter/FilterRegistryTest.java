package pipeAndFilter;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exceptions.NotRegisteredFilter;
import pipeAndFilter.filters.PackageBuilderFilter.PackageBuilderNormalizer;
import pipeAndFilter.filters.fileReader.PcapFileInputStreamGenerator;
import pipeAndFilter.registry.FilterRegistry;

public class FilterRegistryTest {

	
	public FilterRegistry registry;
	private String pcapBeacon = "test-files"+File.separatorChar+"beacon-frame.pcap";
	
	@Before
	public void before() {
		registry = FilterRegistry.getInstance("classmap");
	}
	
	@Test
	public void getGeneratorTest() {
		Processable filter = registry.get("pcapReader:" + pcapBeacon);
		Assert.assertEquals(filter.getClass(), PcapFileInputStreamGenerator.class);
		Assert.assertNotEquals(filter.getClass(), String.class);
	}
	
	@Test
	public void getFilterTest() {
	
		Processable filter = registry.get("packetBuilder");
		Assert.assertEquals(filter.getClass(), PackageBuilderNormalizer.class);
		
	}
	
	@Test(expected = NotRegisteredFilter.class)
	public void notRegisteredFilterTest() {
	
		Processable filter = registry.get("notRegisteredFilter");
		
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void emptyStringTest() {
	
		Processable filter = registry.get("");
		
		
	}
	
}
