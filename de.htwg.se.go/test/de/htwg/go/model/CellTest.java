package de.htwg.go.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CellTest {

	Cell testCell1;
	Cell testCell2;

	@Before
	public void setUp() throws Exception {
		testCell1 = new Cell(0, 0);
		testCell2 = new Cell(0, 0, "w");
	}

	@Test
	public void testSetStatus() {
		testCell1.setStatus("w");
		assertEquals(1, testCell1.getStatus());

		testCell1.setStatus("b");
		assertEquals(2, testCell1.getStatus());

		testCell1.setStatus("0");
		assertEquals(0, testCell1.getStatus());
	}

}
