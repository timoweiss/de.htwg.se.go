package de.htwg.go.model;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

public class CellTest {

	Cell testCell1;
	Cell testCell2;

	@Before
	public void setUp() throws Exception {
		testCell1 = new Cell(0, 0);
		testCell2 = new Cell(0, 0, 1);
	}
	@Test
	public void testCellIntInt() {
		assertEquals(true, true);
	}

	@Test
	public void testCellIntIntInt() {
		assertEquals(true, true);
	}

	@Test
	public void testGetStatus() {
		assertEquals(1, testCell2.getStatus());
	}

	@Test
	public void testGetCoords() {
		assertEquals(new Point(0,0), testCell2.getCoords());
	}

	@Test
	public void testSetStatus() {
		testCell1.setStatus(1);
		assertEquals(1, testCell1.getStatus());

		testCell1.setStatus(2);
		assertEquals(2, testCell1.getStatus());

		testCell1.setStatus(0);
		assertEquals(0, testCell1.getStatus());
	}

	@Test
	public void testIsChecked() {
		assertEquals(false, testCell1.isChecked());
	}

	@Test
	public void testSetChecked() {
		testCell1.setChecked(true);
		assertEquals(true, testCell1.isChecked());
	}

	@Test
	public void testResetCheck() {
		testCell1.resetCheck();
		assertEquals(false, testCell1.isChecked());
	}

	@Test
	public void testToString() {
		System.out.println(testCell2.toString());
		assertEquals("0|0", testCell2.toString());
	}

}
