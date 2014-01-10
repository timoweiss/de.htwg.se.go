package de.htwg.go.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.go.controller.impl.GoController;

public class ControllerTest {
	GoController controller;

	@Before
	public void setUp() throws Exception {
		controller = new GoController();
	}

	@Test
	public void testCreateField() {
		controller.createField();
		assertEquals(true, controller != null);
		assertNotNull(controller);
	}

	@Test
	public void testTuiToString() {
		controller.createField();
		assertEquals(
				"    0 1 2 3 4 5 6 7 8\n    _ _ _ _ _ _ _ _ _\n0  |0 0 0 0 0 0 0 0 0 \n1  |0 0 0 0 0 0 0 0 0 \n2  |0 0 0 0 0 0 0 0 0 \n3  |0 0 0 0 0 0 0 0 0 \n4  |0 0 0 0 0 0 0 0 0 \n5  |0 0 0 0 0 0 0 0 0 \n6  |0 0 0 0 0 0 0 0 0 \n7  |0 0 0 0 0 0 0 0 0 \n8  |0 0 0 0 0 0 0 0 0 \n",
				controller.tuiToString());

	}

	@Test
	public void testSetStoneIntInt() {
		controller.createField();
		assertEquals(true, controller.setStone(5, 2));
		assertEquals(false, controller.setStone(100, 100));
		assertFalse(controller.setStone(5, 2));
	}

	@Test
	public void testSetStoneIntIntInt() {
		controller.createField();

		controller.setStone(5, 5, 1);
		assertEquals(1, controller.getCellStatus(5, 5));

		controller.setStone(5, 5, 2);
		assertEquals(2, controller.getCellStatus(5, 5));

	}

	@Test
	public void testGetStatus() {
		// assertEquals(true, controller.getStatus().charAt(5) instanceof char);
	}

	@Test
	public void testGetwhitePlayerScore() {
		controller.createField();
		assertEquals(0, controller.getwhitePlayerScore());
	}

	@Test
	public void testGetblackPlayerScore() {
		controller.createField();
		assertEquals(0, controller.getblackPlayerScore());
	}

	@Test
	public void testGetCellStatus() {
		controller.createField();

		controller.setStone(5, 5, 1);
		assertEquals(1, controller.getCellStatus(5, 5));

		controller.setStone(5, 5, 2);
		assertEquals(2, controller.getCellStatus(5, 5));
	}

	@Test
	public void testGetNext() {
		controller.createField();
		String testNext = controller.getNext();
		controller.setStone(8, 8);

		if (testNext.equals("white")) {
			assertEquals("black", controller.getNext());
		} else if (testNext.equals("black")) {
			assertEquals("white", controller.getNext());
		}
	}

}
