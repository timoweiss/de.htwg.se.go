package de.htwg.go.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameFieldTest {

	GameField testField;

	@Before
	public void setUp() throws Exception {
		testField = new GameField();
		testField.setStone(2, 2, "w");
		testField.setStone(3, 2, "w");
		testField.setStone(4, 2, "w");
		testField.setStone(4, 3, "w");
		testField.setStone(4, 4, "w");
		testField.setStone(4, 5, "w");
		testField.setStone(3, 5, "w");
		testField.setStone(2, 5, "w");
		testField.setStone(2, 4, "w");
		
	}

	@Test
	public void testSetStone() {
		testField.setStone(5, 2, "w");
		assertEquals(1, testField.getCellStatus(5, 2));
	}
	
	@Test
	public void testIsForm() {
		//testField.setStone(2, 3, "w");
		assertEquals(true, testField.isForm(2, 3));
		
	}

}
