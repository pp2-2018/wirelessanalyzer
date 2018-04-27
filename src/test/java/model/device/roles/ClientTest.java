package model.device.roles;

import org.junit.Assert;
import org.junit.Test;

import model.device.Device;
import model.device.MacAddress;

public class ClientTest {

	@Test
	public void test() {
		
		Device d = new Device(new MacAddress(new byte[] {0x01}));
		
		Client c = new Client(d);
		
		Assert.assertEquals(d, c.getDevice());
		
	}
	
	@Test
	public void toStringTest() {
		Device d = new Device(new MacAddress(new byte[] {0x01}));
		
		Client c = new Client(d);
		
		Assert.assertEquals("Client{device=" + d.toString() + '}', c.toString());
	}
	
}
