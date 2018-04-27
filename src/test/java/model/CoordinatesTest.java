package model;

import org.junit.Assert;
import org.junit.Test;

public class CoordinatesTest {

	@Test
	public void test() {
		
		Coordinates c = new Coordinates(10, 20);
		
		Assert.assertEquals(10, c.getLat(), 0.1);
		Assert.assertEquals(20, c.getLng(), 0.1);
		
	}
	
	@Test
	public void equalsTest() {
		
		Coordinates c = new Coordinates(10, 20);
		Coordinates cClone = new Coordinates(10, 20);
		Coordinates c1 = new Coordinates(10, 10);
		Coordinates c2 = new Coordinates(9, 20);
		
		Assert.assertEquals(c, c);
		Assert.assertEquals(c, cClone);
		Assert.assertNotEquals(c, c1);
		Assert.assertNotEquals(c, c2);
		Assert.assertNotEquals(c, null);
		Assert.assertNotEquals(c, new Object());
		
	}
	
	@Test
	public void toStringTest() {

		Coordinates c = new Coordinates(10, 20);
		
		Assert.assertEquals("{lat=" + c.getLat() + ", lng=" + c.getLng() +"}", c.toString());
	}
	
	@Test
	public void hashCodeTest() {
		Coordinates c = new Coordinates(10, 20);
		Coordinates cClone = new Coordinates(10, 20);
		Coordinates c1 = new Coordinates(10, 10);
		
		Assert.assertEquals(c.hashCode(), c.hashCode());
		Assert.assertEquals(c.hashCode(), cClone.hashCode());
		Assert.assertNotEquals(c.hashCode(), c1.hashCode());
	}
	
}
