package pipeAndFilter;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import model.Coordinates;
import model.Package;
import model.TimeStamp;
import model.device.ConfigurationMap;
import model.device.Device;
import model.device.MacAddress;
import model.device.roles.Sniffer;
import negocio.ConfigurationManager;
import pipeAndFilter.Pipe;
import pipeAndFilter.filters.rawPackageFilter.TriangulatorBySniffer;
import pipeAndFilter.impl.QueuePipe;

public class TriangulatorBySnifferTest {
	
	    Package packageA;
	    Package packageB;
	    Package packageC;
	    Package packageD;
	    Package packageE;
	    
	    ConfigurationMap configurationMap;
	
	@Before
	public void setUp(){
		
		configurationMap = new ConfigurationMap();
			
		byte [] mac01= {(byte)0XAB,(byte)0XCD,(byte)0XEF,(byte)0XAB,(byte)0XCD,(byte)0XEF};
        byte [] mac02= {(byte)0XAA,(byte)0XBB,(byte)0XCC,(byte)0XDD,(byte)0XEE,(byte)0XFF};
        byte [] mac03= {(byte)0XAA,(byte)0XBC,(byte)0XCC,(byte)0XDD,(byte)0XEE,(byte)0XFF};  
        byte [] mac04= {(byte)0XAA,(byte)0XBF,(byte)0XCC,(byte)0XDD,(byte)0XEE,(byte)0XFF};
        byte [] mac05= {(byte)0XAA,(byte)0XBE,(byte)0XCC,(byte)0XDD,(byte)0XEE,(byte)0XFF};
        
        Coordinates coords01 = new Coordinates(2, 5);
        Coordinates coords02 = new Coordinates(5, 8);
        Coordinates coords03 = new Coordinates(-1, 1);
        Coordinates coords04 = new Coordinates(3, 1);
        Coordinates coords05 = new Coordinates(4, 1);
        
        Device dev01 = new Device(new MacAddress(mac01));
        Device dev02 = new Device(new MacAddress(mac02));
        Device dev03 = new Device(new MacAddress(mac03));
        Device dev04 = new Device(new MacAddress(mac04));
        Device dev05 = new Device(new MacAddress(mac05));

        Sniffer ap01 = new Sniffer(dev01, 100);
        Sniffer ap02 = new Sniffer(dev02, 150);
        Sniffer ap03 = new Sniffer(dev03, 200);
        Sniffer ap04 = new Sniffer(dev04, 250);
        Sniffer ap05 = new Sniffer(dev05, 300);
        
        configurationMap.register(ap01,coords01);
        configurationMap.register(ap02,coords02);
        configurationMap.register(ap03,coords03);
        configurationMap.register(ap04,coords04);
        configurationMap.register(ap05,coords05);
        
        ap01.setRangeInMeters(3);
        ap02.setRangeInMeters(3);
        ap03.setRangeInMeters(3);
        ap04.setRangeInMeters(3);
        ap05.setRangeInMeters(3);

        packageA = new Package(new TimeStamp(LocalDateTime.of(2000, Month.JANUARY, 2, 13, 30, 00, 00)));
        packageB = new Package(new TimeStamp(LocalDateTime.of(2000, Month.JANUARY, 2, 13, 30, 00, 03)));
        packageC = new Package(new TimeStamp(LocalDateTime.of(2000, Month.JANUARY, 2, 13, 35, 00, 00)));
        packageD = new Package(new TimeStamp(LocalDateTime.of(2000, Month.JANUARY, 2, 13, 35, 00, 00)));
        packageE = new Package(new TimeStamp(LocalDateTime.of(2000, Month.JANUARY, 2, 13, 35, 00, 00)));
        
        packageA.setSniffer(ap01);
        packageB.setSniffer(ap02);
        packageC.setSniffer(ap03);
        packageD.setSniffer(ap04);
        packageE.setSniffer(ap05);
        
        ConfigurationManager.save(configurationMap);
     
	}
	
	
	@Test
	public void triangularTest() {
		
		Pipe<Package> input = new QueuePipe<>();
        Pipe<Coordinates> output = new QueuePipe<>();
		
		TriangulatorBySniffer triangulador = new TriangulatorBySniffer(input, output);
		
		Queue<Package> cola = new LinkedList<Package>();
		cola.add(packageA);
		cola.add(packageB);
		
		Coordinates expected = new Coordinates(3.5, 6.5);
		
		Assert.assertEquals(expected , triangulador.triangular(cola));
	}
	
	@Test
	public void transform(){
		
		Pipe<Package> input = new QueuePipe<>();
	    Pipe<Coordinates> output = new QueuePipe<>();
		
		TriangulatorBySniffer triangulator = new TriangulatorBySniffer(input, output);
		input.accept(packageA);
		input.accept(packageB);
		input.accept(packageC); 

		
		while(!input.isEmpty()) {
			triangulator.process();
		}
		
		Coordinates expected1 = new Coordinates(3.5, 6.5);
		Coordinates expected2 = new Coordinates(-1, 1);
		Assert.assertEquals(expected1 ,output.retireve());
		Assert.assertEquals(expected2 ,output.retireve());
		Assert.assertEquals(null ,output.retireve());
	}
	
	@Test
	public void transformDeTresPuntos(){
		
		Pipe<Package> input = new QueuePipe<>();
	    Pipe<Coordinates> output = new QueuePipe<>();
		
		TriangulatorBySniffer triangulator = new TriangulatorBySniffer(input, output);
		input.accept(packageC);
		input.accept(packageD);
		input.accept(packageE);
		input.accept(packageA);

		
		while(!input.isEmpty()) {
			triangulator.process();
		}
		
		Coordinates expected1 = new Coordinates(1.5, 1);
		Coordinates expected2 = new Coordinates(2, 5);

		Assert.assertEquals(expected1 ,output.retireve());
		Assert.assertEquals(expected2 ,output.retireve());
		Assert.assertEquals(null ,output.retireve());
	}
	
	
	@Test
	public void TriangulatorMethods(){
		
		Pipe<Package> input = new QueuePipe<>();
	    Pipe<Coordinates> output = new QueuePipe<>();
		
		TriangulatorBySniffer triangulator = new TriangulatorBySniffer(input, output);
		input.accept(packageA);
		input.accept(packageB);
		input.accept(packageE);

		triangulator.process();
		Assert.assertTrue(triangulator.estaEnRangoDeTiempo(packageB));
		Assert.assertFalse(triangulator.estaEnRangoDeTiempo(packageE));
		triangulator.process();
		triangulator.process();
		triangulator.process();
		triangulator.process();
		
		Coordinates expected = new Coordinates(3.5, 6.5);
		Coordinates expected2 = new Coordinates(4, 1);
	
		Assert.assertEquals(expected , output.retireve());
		Assert.assertEquals(expected2 , output.retireve());
		Assert.assertEquals(null , output.retireve());
		
		Double expectedX = 3.5;
		Double expectedY = 6.5;
		
		Assert.assertEquals(expectedX, (Double)triangulator.getCoordenadaX(packageA, packageB));
		Assert.assertEquals(expectedY, (Double)triangulator.getCoordenadaY(packageA, packageB));
		
		Double interseccionX = 1.5;
		Double interseccionY = 1.5;
		

		Assert.assertEquals(interseccionX, (Double)triangulator.getInterseccionX(packageA, packageB));
		Assert.assertEquals(interseccionY, (Double)triangulator.getInterseccionY(packageA, packageB));
	

	}
	

}
