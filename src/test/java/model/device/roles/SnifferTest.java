package model.device.roles;

import org.junit.Assert;
import org.junit.Test;

import model.device.Device;
import model.device.MacAddress;

public class SnifferTest {

	@Test
	public void test() {
		
		Device d = new Device(new MacAddress(new byte[] {0x01}));
		
		Sniffer s = new Sniffer(d, 10);
		
		Assert.assertEquals(d, s.getDevice());
		
	}
	
	@Test
	public void equalsTest() {
		
		Device d1 = new Device(new MacAddress(new byte[] {0x01}));
		Device d2 = new Device(new MacAddress(new byte[] {0x02}));
		
		Sniffer s1 = new Sniffer(d1, 10);
		Sniffer s1Clone = new Sniffer(d1, 10);
		Sniffer s2 = new Sniffer(d2, 10);
		Sniffer s3 = new Sniffer(d1, 12);
		Sniffer s4 = new Sniffer(null, 10);
		

		Assert.assertEquals(s1, s1);
		Assert.assertEquals(s1, s1Clone);
		Assert.assertNotEquals(s1, s2);
		Assert.assertNotEquals(s1, s3);
		Assert.assertNotEquals(s1, s4);
		Assert.assertNotEquals(s1, null);
	}
	
	@Test
	public void hashCodeTest() {
		Device d1 = new Device(new MacAddress(new byte[] {0x01}));
		Device d2 = new Device(new MacAddress(new byte[] {0x02}));
		
		Sniffer s1 = new Sniffer(d1, 10);
		Sniffer s1Clone = new Sniffer(d1, 10);
		Sniffer s2 = new Sniffer(d2, 10);
		
		Assert.assertEquals(s1.hashCode(), s1Clone.hashCode());
		Assert.assertNotEquals(s1.hashCode(), s2.hashCode());
	}
	
	@Test
	public void toStringTest() {
		Device d = new Device(new MacAddress(new byte[] {0x01}));
		
		Sniffer s = new Sniffer(d, 10);
		
		Assert.assertEquals("Sniffer: "+d.toString(), s.toString());
	}
	
}
