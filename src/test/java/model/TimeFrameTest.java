package model;

import java.time.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TimeFrameTest {
	
	TimeFrame timeFrame;
	
	LocalDateTime antesDeFechaInicio;
	LocalDateTime despuesDeFechaInicio;
	
	LocalDateTime unMsAntes;
	LocalDateTime unMsDespues;
	
	LocalDateTime inicio;
	LocalDateTime fin;
	
    @Before
    public void setUp(){
    	
    	inicio = LocalDateTime.of(2014, Month.JANUARY, 1, 10, 10, 30);
    	fin = LocalDateTime.of(2015, Month.JANUARY, 1, 10, 10, 30);
    	
    	timeFrame = new TimeFrame(inicio, fin);
    	
    	antesDeFechaInicio = LocalDateTime.of(2013, Month.JANUARY, 1, 10, 10, 30);
    	despuesDeFechaInicio = LocalDateTime.of(2016, Month.JANUARY, 1, 10, 10, 30);
    	
    	unMsAntes = LocalDateTime.of(2014, Month.JANUARY, 1, 10, 10, 29);
    	unMsDespues = LocalDateTime.of(2015, Month.JANUARY, 1, 10, 11, 35);
    	
    }


	@Test
	public void estaEnIntervaloLocalDateTime() {
		
		Assert.assertFalse(timeFrame.contains(antesDeFechaInicio));
		Assert.assertFalse(timeFrame.contains(despuesDeFechaInicio));
		
		Assert.assertFalse(timeFrame.contains(unMsAntes));
		Assert.assertFalse(timeFrame.contains(unMsDespues));

		Assert.assertTrue(timeFrame.contains(inicio));
		Assert.assertTrue(timeFrame.contains(fin));
		
	}

	@Test
	public void estaEnIntervaloTimeStamp() {
		Instant instant = Instant.now(); //can be LocalDateTime
		ZoneId systemZone = ZoneId.systemDefault(); // my timezone
		ZoneOffset currentOffsetForMyZone = systemZone.getRules().getOffset(instant);

		Assert.assertFalse(timeFrame.contains(new TimeStamp(antesDeFechaInicio.toInstant(currentOffsetForMyZone))));

		Assert.assertFalse(timeFrame.contains(new TimeStamp(despuesDeFechaInicio.toInstant(currentOffsetForMyZone))));

		Assert.assertFalse(timeFrame.contains(new TimeStamp(unMsAntes.toInstant(currentOffsetForMyZone))));
		Assert.assertFalse(timeFrame.contains(new TimeStamp(unMsDespues.toInstant(currentOffsetForMyZone))));

		Assert.assertTrue(timeFrame.contains(new TimeStamp(inicio.toInstant(currentOffsetForMyZone))));
		Assert.assertTrue(timeFrame.contains(new TimeStamp(fin.toInstant(currentOffsetForMyZone))));

	}


}
