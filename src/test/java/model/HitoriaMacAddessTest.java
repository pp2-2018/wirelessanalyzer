package model;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import model.device.Device;
import model.device.MacAddress;
import model.device.roles.Sniffer;

public class HitoriaMacAddessTest {

	@Test
	public void test() {
		
		LocalDateTime dateTime = LocalDateTime.now();
		Sniffer sniffer = new Sniffer(new Device(new MacAddress(new byte[] {0x01})), 10);
		
		
		HistoriaMacAddress historia = new HistoriaMacAddress();
		historia.addSniffer(sniffer);
		historia.setFechaHora(dateTime);
		
		Assert.assertEquals(dateTime, historia.getFechaHora());
		Assert.assertEquals(1, historia.getSniffers().size());
		Assert.assertEquals(sniffer, historia.getSniffers().get(0));
		
	}
	
	@Test
	public void equalsTest() {
		
		LocalDateTime dateTime = LocalDateTime.now();
		LocalDateTime dateTime2 = LocalDateTime.now().plusHours(12);
		Sniffer sniffer = new Sniffer(new Device(new MacAddress(new byte[] {0x01})), 10);
		Sniffer sniffer2 = new Sniffer(new Device(new MacAddress(new byte[] {0x02})), 11);
		
		
		HistoriaMacAddress historia = new HistoriaMacAddress();
		HistoriaMacAddress historiaClon = new HistoriaMacAddress();
		HistoriaMacAddress historia2 = new HistoriaMacAddress();
		
		historia.addSniffer(sniffer);
		historia.setFechaHora(dateTime);
		
		historiaClon.addSniffer(sniffer);
		historiaClon.setFechaHora(dateTime);
		
		historia2.addSniffer(sniffer2);
		historia2.setFechaHora(dateTime2);
		
		Assert.assertEquals(historia, historia);
		Assert.assertEquals(historia, historiaClon);
		Assert.assertNotEquals(historia, historia2);
		Assert.assertNotEquals(historia, new Object());
		Assert.assertNotEquals(historia, null);
		
	}
	
	@Test
	public void hashCodeTest() {
		LocalDateTime dateTime = LocalDateTime.now();
		LocalDateTime dateTime2 = LocalDateTime.now().plusHours(12);
		Sniffer sniffer = new Sniffer(new Device(new MacAddress(new byte[] {0x01})), 10);
		Sniffer sniffer2 = new Sniffer(new Device(new MacAddress(new byte[] {0x02})), 11);
		
		
		HistoriaMacAddress historia = new HistoriaMacAddress();
		HistoriaMacAddress historiaClon = new HistoriaMacAddress();
		HistoriaMacAddress historia2 = new HistoriaMacAddress();
		
		historia.addSniffer(sniffer);
		historia.setFechaHora(dateTime);
		
		historiaClon.addSniffer(sniffer);
		historiaClon.setFechaHora(dateTime);
		
		historia2.addSniffer(sniffer2);
		historia2.setFechaHora(dateTime2);
		
		Assert.assertEquals(historia.hashCode(), historia.hashCode());
		Assert.assertEquals(historia.hashCode(), historiaClon.hashCode());
		Assert.assertNotEquals(historia.hashCode(), historia2.hashCode());
	}
	
	@Test
	public void toStringTest() {
		LocalDateTime dateTime = LocalDateTime.now();
		Sniffer sniffer = new Sniffer(new Device(new MacAddress(new byte[] {0x01})), 10);
		
		
		HistoriaMacAddress historia = new HistoriaMacAddress();
		
		historia.addSniffer(sniffer);
		historia.setFechaHora(dateTime);
		
		Assert.assertEquals(" (Historia: "
		+ historia.getFechaHora().toString() 
		+ " " 
		+ historia.getSniffers().toString() 
		+ ")", historia.toString());
	}
	
}
