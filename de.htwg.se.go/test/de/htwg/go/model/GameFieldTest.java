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
		assertTrue(testField instanceof GameField);
	}

	@Test
	public void testGetNext() {
		String testNext = testField.getNext();
		assertEquals(testNext, testField.getNext());
		
		testField.setStone(1, 1);
		
		if (testNext.equals("white")) {
			assertEquals("black", testField.getNext());
		} else {
			assertEquals("white", testField.getNext());
		}
		
		
		assertEquals("white", testNext);	
	}

	@Test
	public void testSetStoneIntInt() {
		
		assertEquals(true, testField.setStone(5, 2));
		assertEquals(false, testField.setStone(100, 100));
	}

	@Test
	public void testSetStoneIntIntInt() {
		
		//assertEquals(true, testField.setStone(5, 2, 1));
		
		testField.setStone(5, 2, 2);
		assertEquals(2, testField.getCellStatus(5, 2));
	}

	@Test
	public void testGetCellStatus() {
		assertEquals(0, emptyTestField.getCellStatus(0, 0));
		assertEquals(1, testField.getCellStatus(2, 2));
	}

	
	@Test
	public void testResetAllChecks() {
		boolean checkvar = false;
		testField.resetAllChecks();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (testField.checked(i, j)) {
					checkvar = true;
					fail("all Cells should be unchecked");
				}
			}
		}
		assertFalse(checkvar);
		
	}

	@Test
	public void testFenced() {
		assertFalse(testField.fenced(3, 3));
		testField.setStone(2, 3, 1);
		assertTrue(testField.fenced(3, 3));
	}

}
