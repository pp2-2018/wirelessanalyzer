package model.device.roles;

import org.junit.Assert;
import org.junit.Test;

import model.device.Device;
import model.device.MacAddress;

public class AccessPointTest {

	@Test
	public void test() {

		Device d = new Device(new MacAddress(new byte[] {0x01}));
		
		AccessPoint ap = new AccessPoint(d, "WIFI123");
		
		Assert.assertEquals(d, ap.getDevice());
		Assert.assertEquals("WIFI123", ap.getSSID());
		
	}
	
}
