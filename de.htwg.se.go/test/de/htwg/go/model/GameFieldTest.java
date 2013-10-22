package de.htwg.go.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameFieldTest {

	GameField testField;
	
	@Before
	public void setUp() throws Exception {
		testField = new GameField();
	}

	@Test
	public void testSetStone() {
		testField.setStone(5, 2, "w");
		
	}

	@Test
	public void testPrintField() {
		fail("Not yet implemented");
	}

}
