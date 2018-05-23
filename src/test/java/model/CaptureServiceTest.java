package model;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.*;


import negocio.AnalyticsObserver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.device.Device;
import model.device.MacAddress;
import model.device.roles.Sniffer;
import negocio.CaptureService;
import pipeAndFilter.Pipe;
import pipeAndFilter.Processable;
import pipeAndFilter.TestObserver;
import pipeAndFilter.filters.rawPackageFilter.CaptureToPackageFilter;
import pipeAndFilter.filters.rawPackageFilter.PackageByMacAddressFilter;
import pipeAndFilter.filters.rawPackageFilter.PackageByTimeFrameFilter;
import pipeAndFilter.filters.rawPackageFilter.PackageToSnifferFilter;
import pipeAndFilter.impl.PipeSystem;
import pipeAndFilter.impl.QueuePipe;
import pipeAndFilter.impl.SinkImpl;
import pipeAndFilter.sink.Test.TestSink;
import pipeAndFilter.sink.snifferDetectedMac.SnifferDetectedMac;

public class CaptureServiceTest {
	
	ArrayList<Capture> captures;
	ArrayList<Capture> capturas2;
	ArrayList<Capture> capturas3;
	ArrayList<Capture> capturas4;
	
	Sniffer a;
	Sniffer b;
	Sniffer c;
	Sniffer d;
	Sniffer e;
	
	MacAddress addra;
	MacAddress addrc;
	MacAddress addre;
	MacAddress addrd;

	LocalDateTime start = LocalDateTime.of(2000, Month.JANUARY, 2, 13, 30, 0);
	LocalDateTime end = LocalDateTime.of(2000, Month.MARCH, 1, 13, 30, 00);
	
	@Before
    public void setUp(){
    		
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
    	
 
    	Package packageA = new Package(null);				//paquetes
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
    	
    	captures = new ArrayList<Capture>();					//array de capturas 1
    	
    	captures.add(capA);
    	captures.add(capB);
    	captures.add(capD);
    	
    	
    	//////////////////////////////////////
    	
    	
    	capturas2 = new ArrayList<Capture>();					//array de capturas 2
    	
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
    	
    	///////////////////

    	capturas3 = new ArrayList<Capture>();				//array de capturas 3


    	
    	Capture capUno = new Capture(a, start, end);
    	
    	Package packageUno = new Package(new TimeStamp(start.plusDays(7)));		//timestamp = 01/01/2000 23:59:59
    	packageUno.setMacAddress(addra);
    	
    	Package packageDos = new Package(new TimeStamp(start.plusDays(14)));		//timestamp = 02/03/2000 00:00:01
    	packageDos.setMacAddress(addrc);
    	
    	Package packageTres = new Package(new TimeStamp(start.plusDays(20)));	//timestamp = 02/01/2000 13:31:00
    	packageTres.setMacAddress(addre);
    	
    	Package packageCuatro = new Package(new TimeStamp(start.plusDays(25)));	//timestamp = 01/03/2000 13:29:00
    	packageCuatro.setMacAddress(addrd);
    	
    	capUno.addPackages(packageUno);
    	capUno.addPackages(packageDos);
    	capUno.addPackages(packageTres);
    	capUno.addPackages(packageCuatro);

    	capturas3.add(capUno);
    	
    	
    	////////////////////////
    	
    	capturas4 = new ArrayList<Capture>();
    	
    	Capture cap0 = new Capture(a, start, end);
    	
    	Package package00 = new Package(new TimeStamp(start.plusDays(7)));
    	package00.setMacAddress(addra);
    	
    	Package package01 = new Package(new TimeStamp(start.plusDays(14)));
    	package01.setMacAddress(addrc);
    	
    	cap0.addPackages(package00);
    	cap0.addPackages(package01);
    	
    	Capture cap00 = new Capture (b, start, end);
    	
    	Package package02 = new Package(new TimeStamp(start.plusDays(7)));
    	package02.setMacAddress(addra);
    	
    	Package package03 = new Package(new TimeStamp(start.plusDays(8)));
    	package03.setMacAddress(addra);
    	
    	cap00.addPackages(package02);
    	cap00.addPackages(package03);
    	
    	capturas4.add(cap0);
    	capturas4.add(cap00);

    }
	
	@Test
	public void getRegistroDeCapturas(){
		
		CaptureService capturas = new CaptureService(capturas4);
 		
		HistoriaMacAddress h1 = new HistoriaMacAddress();
		h1.addSniffer(a);
		h1.addSniffer(b);
		h1.setFechaHora(LocalDateTime.ofInstant(new TimeStamp(start.plusDays(7)).toInstant(), ZoneOffset.UTC));
		
		HistoriaMacAddress h3 = new HistoriaMacAddress();
		h3.addSniffer(b);
		h3.setFechaHora(LocalDateTime.ofInstant(new TimeStamp(start.plusDays(8)).toInstant(), ZoneOffset.UTC));
		
		ArrayList<HistoriaMacAddress> historico = new ArrayList<HistoriaMacAddress>();
		historico.add(h1);
		historico.add(h3);
		
//		System.out.println("expected" + historico );
//		System.out.println("result" + capturas.getRegistroDeCapturas(addra, new TimeFrame(start, end)));
		
		Assert.assertEquals(historico, capturas.getRegistroDeCapturas(addra, new TimeFrame(start, end)));
		
		ArrayList<HistoriaMacAddress> historicoVacio = new ArrayList<HistoriaMacAddress>();
		Assert.assertEquals(historicoVacio, capturas.getRegistroDeCapturas(addrd, new TimeFrame(start, end)));
		
//		System.out.println("expected" + historicoVacio );
//		System.out.println("result" + capturas.getRegistroDeCapturas(addrd, new TimeFrame(start, end)));
	}
	
	
	@Test
	public void amountOfDetectionsPerSniffer() {
	    ArrayList<Capture> test = new ArrayList<>(capturas2);
	    test.addAll(capturas3);
		CaptureService service = new CaptureService(test);

        ArrayList<Pair<Sniffer,Integer>> expected = new ArrayList<>();
        expected.add(new Pair<>(a,3));
        expected.add(new Pair<>(b,2));
        expected.add(new Pair<>(e,2));
        expected.add(new Pair<>(c,1));

        ArrayList<Pair<Sniffer,Integer>> result = service.amountOfDetectionsPerSniffer();
        Assert.assertEquals(expected,result);
        
	}

	@Test
	public void getMacAddressesDetectedBy() {

		CaptureService capturasTest = new CaptureService(captures);
		
		List<MacAddress> arrayb = Arrays.asList(addra, addrc, addre);
		Assert.assertTrue(capturasTest.getMacAddressesDetectedBy(b).equals(arrayb));
//		System.out.println("Access Point 1/B: " + capturasTest.getMacAddressesDetectedBy(b));
		
		List<MacAddress> arrayD = Arrays.asList(addrd);
		Assert.assertTrue(capturasTest.getMacAddressesDetectedBy(d).equals(arrayD));
//		System.out.println("Access Point 2/D: " + capturasTest.getMacAddressesDetectedBy(d));
		
		ArrayList<MacAddress> arrayA = new ArrayList<MacAddress>();
		Assert.assertTrue(capturasTest.getMacAddressesDetectedBy(a).equals(arrayA));
//		System.out.println("Access Point 3/A: " + capturasTest.getMacAddressesDetectedBy(a));
	
	}
	
	@Test
	public void getSniffersThatDetectedThis(){
		
		CaptureService capturasTest2 = new CaptureService(capturas2);
		
		List<Sniffer> array1 = Arrays.asList(a, b, c, e);
		Assert.assertTrue(capturasTest2.getSniffersThatDetectedThis(addra).equals(array1));
		
		List<Sniffer> array2 = Arrays.asList(b, e);
		Assert.assertTrue(capturasTest2.getSniffersThatDetectedThis(addrc).equals(array2));
		
		List<Sniffer> array3 = new ArrayList<Sniffer>();
		Assert.assertTrue(capturasTest2.getSniffersThatDetectedThis(addrd).equals(array3));
		
	}
	@Test
	public void  getSniffersThatDetectedThisOnTimeFramePiped(){


		TimeFrame timeFrame = new TimeFrame(start,end);



		PipeSystem pipeSystem = new PipeSystem();

		Pipe<Capture> pipe1 = new QueuePipe<>();
		Pipe<Package> pipe2 = new QueuePipe<>();
		Pipe<Package> pipe3 = new QueuePipe<>();
		Pipe<Package> pipe4 = new QueuePipe<>();
		Pipe<Sniffer> pipe5 = new QueuePipe<>();

		pipeSystem.addPipe(pipe1);
		pipeSystem.addPipe(pipe2);
		pipeSystem.addPipe(pipe3);
		pipeSystem.addPipe(pipe4);
		pipeSystem.addPipe(pipe5);

		List<Processable> procesabbles = new ArrayList<>();

		CaptureToPackageFilter captureToPackageFilter = new CaptureToPackageFilter(pipe1,pipe2);

		PackageByTimeFrameFilter packageByTimeFrameFilter = new PackageByTimeFrameFilter
				(pipe2,pipe3,timeFrame);

		PackageByMacAddressFilter packageByMacAddressFilter = new PackageByMacAddressFilter
				(pipe3,pipe4,addra);

		PackageToSnifferFilter packageToSnifferFilter = new PackageToSnifferFilter
				(pipe4,pipe5);


		TestSink<Sniffer> testSink = new TestSink<>(pipe5,s -> s.equals(a));
		testSink.addObserver(new TestObserver());

		procesabbles.add(captureToPackageFilter);
		procesabbles.add(packageByTimeFrameFilter);
		procesabbles.add(packageByMacAddressFilter);
		procesabbles.add(packageToSnifferFilter);
		procesabbles.add(testSink);


		for (Capture cap :
				capturas3) {
			pipe1.accept(cap);

		}
		pipe1.closeForWritting();
		while(pipeSystem.canRetrieveForSomeone()) {
			for (Processable processable : procesabbles) {
				processable.process();
			}
		}


	}


	@Test
	public void getSniffersDetectedByThisTimeFrame(){


		TimeFrame timeframe = new TimeFrame(start,end);
		CaptureService service = new CaptureService(capturas3);
		ArrayList<Sniffer> sniffersThatDetected = new ArrayList<Sniffer>(service.getSniffersThatDetectedThisOnTimeFrame(addra,timeframe));
		Assert.assertEquals(sniffersThatDetected,Collections.singletonList(a));

		TimeFrame timeframe2 = new TimeFrame(start.plusYears(1),end.plusYears(1));
		ArrayList<Sniffer> sniffersThatDetected2 = new ArrayList<Sniffer>(service.getSniffersThatDetectedThisOnTimeFrame(addra,timeframe2));

		Assert.assertNotEquals(sniffersThatDetected2,Collections.singletonList(a));
	
	}
	
	@Test
	public void getLocalizaciones(){
		
		byte d4[] = {  0x4d, 0xa, 0x57, 0x4c, 0x4c, 0x05 };	
    	MacAddress m4 = new MacAddress(d4);
    	Device dev4 = new Device(m4);
    	Sniffer snifferA = new Sniffer(dev4, 00000);
    	snifferA.setCoord(new Coordinates(2, 5));
    	snifferA.setRangeInMeters(3);

    	byte d5[] = {  0x4d, 0xa, 0x57, 0x4c, 0x4c, 0x06 };	
    	MacAddress m5 = new MacAddress(d5);
    	Device dev5 = new Device(m5);
    	Sniffer snifferB = new Sniffer(dev5, 00000);
    	snifferB.setCoord(new Coordinates( 8, 11));
    	snifferB.setRangeInMeters(3);
		
		HistoriaMacAddress historia = new HistoriaMacAddress();
		historia.setFechaHora(LocalDateTime.of(2000, Month.JANUARY, 2, 13, 30, 0));
		historia.addSniffer(snifferA);
		historia.addSniffer(snifferB);
		
		ArrayList<HistoriaMacAddress> historias = new ArrayList<HistoriaMacAddress>();
		historias.add(historia);
		
		//5,8
		CaptureService service = new CaptureService(capturas3);
		ArrayList<Coordinates> coordenadas = new ArrayList<Coordinates>();
		coordenadas.add(new Coordinates(5,8));
		
//		System.out.println("coordenadas " + service.getLocalizaciones(historias));
		
		Assert.assertEquals(coordenadas , service.getLocalizaciones(historias));

	}
	
}
