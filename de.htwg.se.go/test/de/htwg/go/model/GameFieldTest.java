package de.htwg.go.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameFieldTest {

	GameField testField;
	GameField emptyTestField;

	@Before
	public void setUp() throws Exception {
		testField = new GameField();
		testField.setStone(2, 2, 1);
		testField.setStone(3, 2, 1);
		testField.setStone(4, 2, 1);
		testField.setStone(4, 3, 1);
		testField.setStone(4, 4, 1);
		testField.setStone(4, 5, 1);
		testField.setStone(3, 5, 1);
		testField.setStone(2, 5, 1);
		testField.setStone(2, 4, 1);
		
		emptyTestField = new GameField();
		
		
	}

	@Test
	public void testGameField() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNext() {
		testField.setStone(1, 2, 1);
		String testNext = testField.getNext();
		assertEquals("white", testNext);
		

		
		
	}

	@Test
	public void testSetStoneIntInt() {
		testField.setStone(5, 2);
		assertEquals(2, testField.getCellStatus(5, 2));
	}

	@Test
	public void testSetStoneIntIntInt() {
		testField.setStone(5, 2, 1);
		assertEquals(1, testField.getCellStatus(5, 2));
		
		testField.setStone(5, 2, 2);
		assertEquals(2, testField.getCellStatus(5, 2));
	}

	@Test
	public void testGetCellStatus() {
		System.out.println(emptyTestField.getCellStatus(0, 0));
		assertEquals(-1, emptyTestField.getCellStatus(0, 0));
		
		assertEquals(1, testField.getCellStatus(2, 2));
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testResetAllChecks() {
		fail("Not yet implemented");
	}

	@Test
	public void testFenced() {
		fail("Not yet implemented");
	}

}
