package pipeAndFilter;

import exceptions.NotRegisteredFilter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pipeAndFilter.filters.PackageBuilderFilter.PackageBuilderNormalizer;
import pipeAndFilter.filters.fileReader.PcapFileInputStreamGenerator;
import pipeAndFilter.registry.CompoundFilterRegistry;
import pipeAndFilter.registry.FilterRegistry;

import java.io.File;

public class CompoundFilterRegistryTest {


	public CompoundFilterRegistry registry;
	private String pcapBeacon = "test-files"+File.separatorChar+"beacon-frame.pcap";
	
	@Before
	public void before() {
		registry = CompoundFilterRegistry.getInstance("test");

	}
	
	@Test
	public void getAndSetTest() {
		registry.set("test","filter");
		Assert.assertEquals("filter",registry.get("test"));
	}

	@Test(expected = NotRegisteredFilter.class)
	public void remove() {
		registry.set("test","filter");
		registry.remove("test");
		registry.get("test");

	}
	


	@Test(expected = NotRegisteredFilter.class)
	public void notRegisteredFilterTest() {
	
		registry.get("notRegisteredFilter");
		
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void emptyStringTest() {
	
		registry.get("");
		
		
	}
	
}
