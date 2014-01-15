package de.htwg.go.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.go.controller.impl.GoController;

public class ControllerTest {
	GoController controller;
	GoController controller2;

	@Before
	public void setUp() throws Exception {
		controller = new GoController();

		controller2 = new GoController();
		controller2.createField(5);

		for (int j = 0; j < 5; j++) {
			for (int i = 0; i < 5; i++) {
				controller2.setStone(i, j);
			}
		}

	}

	@Test
	public void testCreateField() {
		controller.createField(9);
		assertEquals(true, controller != null);
		assertNotNull(controller);
	}

	@Test
	public void testTuiToString() {
		controller.createField(9);
		assertEquals(
				"    0 1 2 3 4 5 6 7 8\n    _ _ _ _ _ _ _ _ _\n0  |0 0 0 0 0 0 0 0 0 \n1  |0 0 0 0 0 0 0 0 0 \n2  |0 0 0 0 0 0 0 0 0 \n3  |0 0 0 0 0 0 0 0 0 \n4  |0 0 0 0 0 0 0 0 0 \n5  |0 0 0 0 0 0 0 0 0 \n6  |0 0 0 0 0 0 0 0 0 \n7  |0 0 0 0 0 0 0 0 0 \n8  |0 0 0 0 0 0 0 0 0 \n",
				controller.tuiToString());

	}

	@Test
	public void testSetStoneIntInt() {
		controller.createField(9);
		assertEquals(true, controller.setStone(5, 2));
		assertEquals(false, controller.setStone(100, 100));
		assertFalse(controller.setStone(5, 2));
		
		assertFalse(controller2.setStone(3, 3));
	}

	@Test
	public void testSetStoneIntIntInt() {
		controller.createField(9);

		controller.setStone(5, 5, 1);
		assertEquals(1, controller.getCellStatus(5, 5));

		controller.setStone(5, 5, 2);
		assertEquals(2, controller.getCellStatus(5, 5));

	}

	@Test
	public void testGetStatus() {
		controller.createField(9);

		assertEquals(
				"Gamefield 9x9 successfully created, \n" + controller.getNext()
						+ " is next", controller.getStatus());
	}

	@Test
	public void testGetwhitePlayerScore() {
		controller.createField(9);
		assertEquals(0, controller.getwhitePlayerScore());
	}

	@Test
	public void testGetblackPlayerScore() {
		controller.createField(9);
		assertEquals(0, controller.getblackPlayerScore());
	}

	@Test
	public void testGetCellStatus() {
		controller.createField(9);

		controller.setStone(5, 5, 1);
		assertEquals(1, controller.getCellStatus(5, 5));

		controller.setStone(5, 5, 2);
		assertEquals(2, controller.getCellStatus(5, 5));
	}

	@Test
	public void testGetNext() {
		controller.createField(9);
		String testNext = controller.getNext();
		controller.setStone(8, 8);

		if (testNext.equals("white")) {
			assertEquals("black", controller.getNext());
		} else if (testNext.equals("black")) {
			assertEquals("white", controller.getNext());
		}
	}

	@Test
	public void testpass() {
		controller.createField(9);
		assertFalse(controller.pass());

		assertTrue(controller.pass());
		
		assertFalse(controller2.pass());
	}

	@Test
	public void testGetGameFieldSize() {
		controller.createField(9);
		assertEquals(9, controller.getGameFieldSize());

		controller.createField(5);
		assertEquals(5, controller.getGameFieldSize());
	}

	@Test
	public void testStop() {
		controller.stop();
		assertFalse(controller.getOperate());
	}
	
	@Test
	public void testGetOperate() {
		controller.createField(5);
		assertTrue(controller.getOperate());
		
		controller.stop();
		assertFalse(controller.getOperate());
	}
}
