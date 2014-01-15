package de.htwg.go.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.go.model.impl.GameField;
import de.htwg.go.model.impl.Player;

public class GameFieldTest {

	GameField testField;
	GameField testField2;
	GameField emptyTestField;
	GameField smallTestField;

	@Before
	public void setUp() throws Exception {
		testField = new GameField(9);
		testField.setStone(2, 2, 1);
		testField.setStone(3, 2, 1);
		testField.setStone(4, 2, 1);
		testField.setStone(4, 3, 1);
		testField.setStone(4, 4, 1);
		testField.setStone(4, 5, 1);
		testField.setStone(3, 5, 1);
		testField.setStone(2, 5, 1);
		testField.setStone(2, 4, 1);

		testField2 = new GameField(9);
		testField2.setStone(6, 6, 2);
		testField2.setStone(5, 7, 2);
		testField2.setStone(4, 6, 2);
		testField2.setStone(5, 5, 2);
		testField2.setStone(5, 6, 1);

		emptyTestField = new GameField(9);
		
		smallTestField = new GameField(5);
	}

	@Test
	public void testGameField() {
		assertTrue(testField instanceof GameField);
	}

	@Test
	public void testGetNext() {
		String testNext = testField.getNext();
		testField.setStone(8, 8);

		if (testNext.equals("white")) {
			assertEquals("black", testField.getNext());
		} else if (testNext.equals("black")) {
			assertEquals("white", testField.getNext());
		}
	}

	@Test
	public void testSetStoneIntInt() {
		assertEquals(true, testField.setStone(5, 2));
		assertEquals(false, testField.setStone(100, 100));
		assertFalse(testField.setStone(5, 2));

		assertFalse(testField.setStone(100, 1));
		assertFalse(testField.setStone(1, 100));
	}

	@Test
	public void testSetStoneIntIntInt() {
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
		testField.setStone(3, 3, 1);

		testField.setStone(3, 3, 2);
		assertEquals(-2, testField.getCellStatus(3, 3));

		assertFalse(testField2.fenced(1, 1));
		assertEquals(-1, testField2.getCellStatus(5, 6));

	}

	@Test
	public void testGetwhitePlayer() {
		assertTrue(testField.getwhitePlayer() instanceof Player);
	}

	@Test
	public void testGetblackPlayer() {
		assertTrue(testField.getblackPlayer() instanceof Player);
	}

	@Test
	public void testtoString() {
		testField = new GameField(9);

		assertEquals(
				"    0 1 2 3 4 5 6 7 8\n    _ _ _ _ _ _ _ _ _\n0  |0 0 0 0 0 0 0 0 0 \n1  |0 0 0 0 0 0 0 0 0 \n2  |0 0 0 0 0 0 0 0 0 \n3  |0 0 0 0 0 0 0 0 0 \n4  |0 0 0 0 0 0 0 0 0 \n5  |0 0 0 0 0 0 0 0 0 \n6  |0 0 0 0 0 0 0 0 0 \n7  |0 0 0 0 0 0 0 0 0 \n8  |0 0 0 0 0 0 0 0 0 \n",
				testField.toString());
		
		System.out.println(smallTestField.toString());
		assertEquals("    0 1 2 3 4\n    _ _ _ _ _\n0  |0 0 0 0 0 \n1  |0 0 0 0 0 \n2  |0 0 0 0 0 \n3  |0 0 0 0 0 \n4  |0 0 0 0 0 \n", smallTestField.toString());
		
	}

	@Test
	public void testpass() {
		testField.pass();
		assertTrue(testField.pass());
		assertFalse(testField2.pass());

	}
	

}
