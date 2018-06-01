package instantiator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import model.device.MacAddress;
import pipeAndFilter.filters.PackageBuilderFilter.PackageBuilderNormalizer;
import pipeAndFilter.sink.snifferDetectedMac.SnifferDetectedMac;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import javax.xml.bind.DatatypeConverter;

import static org.hamcrest.CoreMatchers.*;

public class InstantitatorTest {

	Instantiator instantiator;
	
	@Before
	public void before() {
		instantiator = new Instantiator();
	}
	
	@Test
	public void singleClassTest() throws ClassNotFoundException, InstantiationException {
		Object instance = instantiator.instantiate("pipeAndFilter.filters.PackageBuilderFilter.PackageBuilderNormalizer");
		assertThat(instance, is(PackageBuilderNormalizer.class));
	
	}
	
	@Test(expected = InstantiationException.class)
	public void parameterTest() throws ClassNotFoundException, InstantiationException {
		Object instance = instantiator.instantiate("pipeAndFilter.filters.PackageBuilderFilter.PackageBuilderNormalizer", "1");
		assertThat(instance, is(PackageBuilderNormalizer.class));
	
	}
	
	@Test
	public void instantiateMacFilterTest() throws ClassNotFoundException, InstantiationException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Object instance = instantiator.instantiate("pipeAndFilter.sink.snifferDetectedMac.SnifferDetectedMac", "aa:aa:aa:aa:aa:aa");
		assertThat(instance, is(SnifferDetectedMac.class));
		
		assertArrayEquals(getMacOf((SnifferDetectedMac)instance).getAddress(), getBytesOf("aa:aa:aa:aa:aa:aa"));
	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void instantiateInvalidMacFilterTest() throws ClassNotFoundException, InstantiationException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Object instance = instantiator.instantiate("pipeAndFilter.sink.snifferDetectedMac.SnifferDetectedMac", "g:aa:aa:aa:aa:aa");
		
	}
	
	private MacAddress getMacOf(SnifferDetectedMac filter) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		Field macField = filter.getClass().getDeclaredField("macAddress");
		macField.setAccessible(true);
		return (MacAddress)macField.get(filter);
		
	}
	
	private byte[] getBytesOf(String s) {
		return DatatypeConverter.parseHexBinary(s.replace(":", ""));
	}
}
