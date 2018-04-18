package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.device.Device;
import model.device.MacAddress;
import model.device.roles.Sniffer;
import negocio.CapturasService;

public class CapturaServiceTest {
	
	ArrayList<Captura> capturas;
	
	Sniffer a;
	Sniffer b;
	Sniffer d;
	
	MacAddress addra;
	MacAddress addrc;
	MacAddress addre;
	MacAddress addrd;
	
	@Before
    public void setUp(){
    	
    	capturas = new ArrayList<Captura>();
    		
    	byte b1[] = {  0x4a, 0x08, 0x7f, 0x41, 0x12, 0x47 };	//mac addresses
    	addra = new MacAddress(b1);
    	
    	byte b2[] = {  0x46, 0xa, 0x5b, 0x68, 0x15, 0x4c };
    	addrc = new MacAddress(b2);
    	
    	byte b3[] = {  0x4d, 0xa, 0x57, 0x4c, 0x4c, 0x09 };
    	addre = new MacAddress(b3);
    	
    	byte b4[] = {  0x4d, 0xa, 0x57, 0x4c, 0x4c, 0x09 };
    	addrd = new MacAddress(b4);

    	
    	byte d1[] = {  0x4d, 0xa, 0x57, 0x4c, 0x4c, 0x01 }; 	//sniffers
    	MacAddress m1 = new MacAddress(d1);
    	Device dev1 = new Device(m1);
    	a = new Sniffer(dev1, 00000);
    	
    	byte d2[] = {  0x4d, 0xa, 0x57, 0x4c, 0x4c, 0x02 };	
    	MacAddress m2 = new MacAddress(d2);
    	Device dev2 = new Device(m2);
    	b = new Sniffer(dev2, 00000);
    	
    	byte d3[] = {  0x4d, 0xa, 0x57, 0x4c, 0x4c, 0x03 };	
    	MacAddress m3 = new MacAddress(d3);
    	Device dev3 = new Device(m3);
    	d = new Sniffer(dev3, 00000);
    	
 
    	Package packageA = new Package(null);			//paquetes
    	packageA.setMacAddress(addra);
    	Package packageC = new Package(null);
    	packageC.setMacAddress(addrc);
    	Package packageE = new Package(null);
    	packageE.setMacAddress(addre);
    	Package packageD = new Package(null);
    	packageD.setMacAddress(addrd);
    	
    	Captura capA = new Captura(a, null, null);				//capturas
    	Captura capB = new Captura(b, null, null);
    	Captura capD = new Captura(d, null, null);
    	
    	capB.addPackages(packageA);
    	capB.addPackages(packageC);
    	capB.addPackages(packageE);
    	
    	capD.addPackages(packageD);
    	
    	capturas.add(capA);
    	capturas.add(capB);
    	capturas.add(capD);
    	
    }

	@Test
	public void getMacAddressesDetectedBy() {
		
		CapturasService capturasTest = new CapturasService(capturas);
		
		List<MacAddress> arrayb = Arrays.asList(addra, addrc, addre);
		Assert.assertTrue(capturasTest.getMacAddressesDetectedBy(b).equals(arrayb));
//		System.out.println("Access Point B: " + capturasTest.getMacAddressesDetectedBy(b));
		
		List<MacAddress> arrayD = Arrays.asList(addrd);
		Assert.assertTrue(capturasTest.getMacAddressesDetectedBy(d).equals(arrayD));
//		System.out.println("Access Point D: " + capturasTest.getMacAddressesDetectedBy(d));
		
		ArrayList<MacAddress> arrayA = new ArrayList<MacAddress>();
		Assert.assertTrue(capturasTest.getMacAddressesDetectedBy(a).equals(arrayA));
//		System.out.println("Access Point A: " + capturasTest.getMacAddressesDetectedBy(a));
	
	}
	
	

	
}
