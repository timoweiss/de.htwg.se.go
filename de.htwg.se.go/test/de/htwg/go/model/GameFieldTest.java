package de.htwg.go.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameFieldTest {

	GameField testField;

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
		
	}

	@Test
	public void testSetStone() {
		testField.setStone(5, 2, 1);
		assertEquals(1, testField.getCellStatus(5, 2));
	}
	
	@Test
	public void testIsForm() {
		//testField.setStone(2, 3, 1);
		//assertEquals(true, testField.isForm(2, 3));
		
	}

}
