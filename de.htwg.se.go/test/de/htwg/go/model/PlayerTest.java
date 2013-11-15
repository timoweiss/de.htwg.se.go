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

}
