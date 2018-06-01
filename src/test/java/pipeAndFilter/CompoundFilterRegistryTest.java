package pipeAndFilter;

import exceptions.NotRegisteredFilter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pipeAndFilter.filters.PackageBuilderFilter.PackageBuilderNormalizer;
import pipeAndFilter.filters.fileReader.PcapFileInputStreamGenerator;
import pipeAndFilter.filters.test.TestGenerator;
import pipeAndFilter.registry.CompoundFilterRegistry;
import pipeAndFilter.registry.FilterRegistry;

import java.io.File;
import java.util.List;

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
		Assert.assertEquals("filter",registry.getFilterString("test"));
	}
	
	@Test
	public void depthTwoFilterTest() {
	
		registry.set("compundFilter","test(asd) test(asd)");
		registry.set("twoDepth", "test(asd) compundFilter");
		
		List<Processable> filters = registry.get("twoDepth");
		
		Assert.assertEquals(filters.size(), 3);
		Assert.assertEquals(filters.get(0).getClass(), TestGenerator.class);
	
	}

	@Test(expected = NotRegisteredFilter.class)
	public void remove() {
		registry.set("test","filter");
		registry.remove("test");
		registry.getFilterString("test");

	}
	


	@Test(expected = NotRegisteredFilter.class)
	public void notRegisteredFilterTest() {
	
		registry.getFilterString("notRegisteredFilter");
		
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void emptyStringTest() {
	
		registry.getFilterString("");
		
		
	}
	
}
