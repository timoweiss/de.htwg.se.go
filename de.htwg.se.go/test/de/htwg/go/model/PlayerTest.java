package de.htwg.go.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.go.model.impl.Player;

public class PlayerTest {

	Player unknownPlayer;
	Player knownPlayer;

	@Before
	public void setUp() throws Exception {
		unknownPlayer = new Player();
	}

	@Test
	public void testGetName() {
		assertEquals("unknown", unknownPlayer.getName());
	}

	@Test
	public void testaddScore() {
		unknownPlayer.addScore(500);
		assertEquals(500, unknownPlayer.getScore());
		assertEquals(505, unknownPlayer.addScore(5));
		
	}

	@Test
	public void testgetScore() {
		unknownPlayer.addScore(500);
		assertEquals(500, unknownPlayer.getScore());
		assertEquals(505, unknownPlayer.addScore(5));
	}
	


}
