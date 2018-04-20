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
import negocio.CaptureService;

public class CaptureServiceTest {
	
	ArrayList<Capture> captures;
	ArrayList<Capture> capturas2;
	
	Sniffer a;
	Sniffer b;
	Sniffer c;
	Sniffer d;
	Sniffer e;
	
	MacAddress addra;
	MacAddress addrc;
	MacAddress addre;
	MacAddress addrd;
	
	@Before
    public void setUp(){
    	
    	captures = new ArrayList<Capture>();
    		
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
    	
    	byte d4[] = {  0x4d, 0xa, 0x57, 0x4c, 0x4c, 0x05 };	
    	MacAddress m4 = new MacAddress(d4);
    	Device dev4 = new Device(m4);
    	e = new Sniffer(dev4, 00000);
    	
    	byte d5[] = {  0x4d, 0xa, 0x57, 0x4c, 0x4c, 0x06 };	
    	MacAddress m5 = new MacAddress(d5);
    	Device dev5 = new Device(m5);
    	c = new Sniffer(dev5, 00000);
    	
 
    	Package packageA = new Package(null);			//paquetes
    	packageA.setMacAddress(addra);
    	Package packageC = new Package(null);
    	packageC.setMacAddress(addrc);
    	Package packageE = new Package(null);
    	packageE.setMacAddress(addre);
    	Package packageD = new Package(null);
    	packageD.setMacAddress(addrd);
    	
    	Capture capA = new Capture(a, null, null);				//capturas
    	Capture capB = new Capture(b, null, null);
    	Capture capD = new Capture(d, null, null);
    	
    	capB.addPackages(packageA);
    	capB.addPackages(packageC);
    	capB.addPackages(packageE);
    	capD.addPackages(packageD);
    	
    	captures.add(capA);
    	captures.add(capB);
    	captures.add(capD);
    	
    	
    	//////////////////////////////////////
    	
    	
    	capturas2 = new ArrayList<Capture>();
    	
    	Capture cap1 = new Capture(a, null, null);
    	Capture cap2 = new Capture(b, null, null);
    	Capture cap3 = new Capture(c, null, null);
    	Capture cap4 = new Capture(e, null, null);
    	
    	cap1.addPackages(packageA);
    	cap2.addPackages(packageA);
    	cap3.addPackages(packageA);
    	cap4.addPackages(packageA);
    	
    	cap2.addPackages(packageC);
    	cap4.addPackages(packageC);
    	
    	capturas2.add(cap1);
    	capturas2.add(cap2);
    	capturas2.add(cap3);
    	capturas2.add(cap4);
    }

	@Test
	public void getMacAddressesDetectedBy() {
		
		CaptureService capturasTest = new CaptureService(captures);
		
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
	
	@Test
	public void getAPsQueDetectaron(){
		
		CaptureService capturasTest2 = new CaptureService(capturas2);
		
		List<Sniffer> array1 = Arrays.asList(a, b, c, e);
		Assert.assertTrue(capturasTest2.getSniffersThatDetectedThis(addra).equals(array1));
		
		List<Sniffer> array2 = Arrays.asList(b, e);
		Assert.assertTrue(capturasTest2.getSniffersThatDetectedThis(addrc).equals(array2));
		
		List<Sniffer> array3 = new ArrayList<Sniffer>();
		Assert.assertTrue(capturasTest2.getSniffersThatDetectedThis(addrd).equals(array3));
		
	}
	
	

	
}