package instantiator;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import exceptions.NotRegisteredClassException;
import pipeAndFilter.parameters.FileListParameter;

public class MapperTest {
	
	ParameterMapper mapper;
	
	@Before
	public void before() {

		mapper = new ParameterMapper("pipeAndFilter.parameters");
		
	}
	
	@Test
	public void integerTest() {
		
		Integer number123 = mapper.map("123", Integer.class);
		Integer number987 = mapper.map("987", Integer.class);
		
		Assert.assertEquals(123, number123.intValue());
		Assert.assertEquals(987, number987.intValue());
	}
	
	@Test
	public void booleanTest() {
		
		Boolean tru = mapper.map("true", Boolean.class);
		Boolean fal = mapper.map("false", Boolean.class);
		
		Assert.assertTrue(tru);
		Assert.assertFalse(fal);

	}
	
	@Test(expected=NotRegisteredClassException.class)
	public void typeNotRegisteredTest() {
		
		Date date = mapper.map("1/1/1990", Date.class);
		
	}
	
	public List<File> type;
	
	@Test
	public void dinamicLoadedClassTest() throws NoSuchFieldException, SecurityException {
		
		Type fileList = getClass().getField("type").getGenericType();
		
		List<File> param = mapper.map("test-files" + File.separatorChar + "beacon-frame.pcap", fileList);
		
		Assert.assertEquals(1 , param.size());
		Assert.assertEquals("beacon-frame.pcap", param.get(0).getName());
		
	}
	
}
