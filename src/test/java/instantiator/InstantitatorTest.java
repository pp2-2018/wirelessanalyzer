package instantiator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import pipeAndFilter.filters.PackageBuilderFilter.PackageBuilderNormalizer;

import static org.junit.Assert.*;
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
	@Ignore
	public void instantiateMacFilterTest() throws ClassNotFoundException, InstantiationException {
		Assert.fail();
	}
}
