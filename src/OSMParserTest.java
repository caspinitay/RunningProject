import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class OSMParserTest {
	
	private OSMParser testParser;

	/*
	 * Constructor Tests ----------------------------------------------
	 */
	
	@Test(expected=IllegalArgumentException.class)
	public void constructorNullTest() {
		try {
			testParser = new OSMParser(null);
		} catch (IOException e) {
			fail();
		}
	}
	
	@Test(expected=IOException.class)
	public void constructorNonexistentFileTest() throws IOException {
		testParser = new OSMParser("xyz.geojson");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void constructorNonGeoJSONFileTest() {
		try {
			testParser = new OSMParser("roads-gen0.txt");
		} catch (IOException e) {
			fail();
		}
	}
	
	@Test
	public void constructorTest() {
		try {
			testParser = new OSMParser("test.geojson");
		} catch (IOException e) {
			fail();
		}
	}
	
	/*
	 * Set Up ---------------------------------------------------------
	 */
	
	@Before
	public void setUp() throws IOException {
		testParser = new OSMParser("test.geojson");
	}
	
	/*
	 * NextLine() Tests ----------------------------------------------
	 */
	
	@Test
	public void nextLineTest() {
		try {
		String line = testParser.nextLine();
		assertEquals('{', line.charAt(0));
		assertEquals("},", line.substring(line.length()-2));
		} catch (IOException e) {
			fail();
		}
	}
	
	@Test
	public void nextLineEOFTest() {
		try {
			String line = "";
			for (int i = 0; i < 10; i++) {
				line = testParser.nextLine();
			}
			assertNull(line);
		} catch (IOException e) {
			fail();
		}
	}
	
	/*
	 * IsRoad() Tests ---------------------------------------------------
	 */
	
	@Test
	public void isRoadNullTest() {
		assertFalse(testParser.isRoad(null));
	}
	
	@Test
	public void isRoadRandomStringTest() {
		assertFalse(testParser.isRoad("abcdefg"));
	}
	
	@Test
	public void isRoadSubstringPartialTest() {
		assertFalse(testParser.isRoad("tye:\": \""));
	}
	
	@Test
	public void isRoadSubstringWrongGroupTest() {
		assertFalse(testParser.isRoad("type\": \"highway"));
	}
	
	@Test
	public void isRoadSubstringPrimaryTest() {
		assertTrue(testParser.isRoad("type\": \"primary"));
	}
	
	@Test
	public void isRoadSubstringSecondaryTest() {
		assertTrue(testParser.isRoad("type\": \"secondary"));
	}
	
	@Test
	public void isRoadSubstringTertiaryTest() {
		assertTrue(testParser.isRoad("type\": \"tertiary"));
	}
	
	@Test
	public void isRoadFullFileTest() {
		try {
			OSMParser p = new OSMParser("roads_gen0.geojson");
			assertFalse(p.isRoad(p.nextLine()));
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void isRoadFullFileTrueTest() {
		try {
			OSMParser p = new OSMParser("roads_gen0.geojson");
			int ctr = 0;
			while (!p.isRoad(p.nextLine())) {
				ctr++;
			}
			assertEquals(5, ctr);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/*
	 * ParseRoad() Tests ---------------------------------------------------
	 */
	
	
	

}
