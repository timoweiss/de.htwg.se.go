package de.htwg.go.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

	Player unknownPlayer;
	Player knownPlayer;

	@Before
	public void setUp() throws Exception {
		unknownPlayer = new Player();
		knownPlayer = new Player("known");
	}

	@Test
	public void testGetName() {
		assertEquals("unknown", unknownPlayer.getName());
		assertEquals("known", knownPlayer.getName());
	}

	@Test
	public void testaddScore() {
		unknownPlayer.addPoints(500);
		unknownPlayer.addPoints(5);

		knownPlayer.addPoints(501);
		knownPlayer.addPoints(9);

		assertEquals(505, unknownPlayer.addPoints(0));
		assertEquals(510, knownPlayer.addPoints(0));
	}

	@Test
	public void testgetScore() {
		unknownPlayer.addPoints(500);
		unknownPlayer.addPoints(5);

		knownPlayer.addPoints(501);
		knownPlayer.addPoints(9);

		assertEquals(505, unknownPlayer.getScore());
		assertEquals(510, knownPlayer.getScore());
	}

}
