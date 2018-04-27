package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PairTest {

	@Test
	public void equalTest() {
		
		Pair<Integer, String> p1= new Pair<Integer, String>(1, "a");
		Pair<Integer, String> p2= new Pair<Integer, String>(1, "b");
		Pair<Integer, String> p3= new Pair<Integer, String>(2, "a");
		Pair<Integer, String> p4= new Pair<Integer, String>(1, "a");
		Pair<Integer, String> p5= new Pair<Integer, String>(null, null);
		
		assertEquals(p1, p4);
		assertNotEquals(p2, p1);
		assertNotEquals(p3, p1);
		assertNotEquals(p1, null);
		assertNotEquals(p1, p5);
		assertNotEquals(p1, new Integer(0));
		
	}
	
	@Test
	public void hashTest() {
		Pair<Integer, String> p1= new Pair<Integer, String>(1, "a");
		Pair<Integer, String> p2= new Pair<Integer, String>(1, "b");
		Pair<Integer, String> p3= new Pair<Integer, String>(1, "a");
		
		assertEquals(p1.hashCode(), p3.hashCode());
		assertNotEquals(p1.hashCode(), p2.hashCode());
	}
	
	@Test
	public void getTest() {

		Pair<Integer, String> p1= new Pair<Integer, String>(1, "a");
		assertEquals("a", p1.getValue());
		assertEquals(1, p1.getKey().intValue());
		
	}
	
	@Test
	public void toStringTest() {
		Pair<Integer, String> p1= new Pair<Integer, String>(1, "a");
		
		assertEquals("Pair [key=1, value=a]", p1.toString());
		
		
	}
	
}
