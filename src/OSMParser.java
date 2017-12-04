import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses GeoJSON file format.
 * @author nitaycaspi
 *
 */
public class OSMParser {
	private BufferedReader reader;
	public static List<String> roadNames = new LinkedList<String>();
	
	/**
	 * Create new OSMParser object, set up file parsing
	 * @param filename name of file to parse
	 */
	public OSMParser(String filename) throws IOException {
		
		this.reader = new BufferedReader(new FileReader(filename)) ;
		
	}
	
	/**
	 * Reads next line from geojson file
	 * @return
	 */
	public String nextLine() throws IOException {
		return reader.readLine();
	}
	
	/**
	 * Checks if str represents a road segment;
	 * 
	 * @param str
	 * @return true if line represents a true road segment
	 */
	public boolean isRoad(String str) {
		
		if (str == null) {
			return false;
		}
		
		// Set up regex pattern to search for "type": "(road type)"
		Pattern p = Pattern.compile("([type]{4})(\\p{Punct}{2}\\s\\p{Punct})([a-z]+)");
		Matcher m = p.matcher(str);
		
		if (!m.find()) {
			return false;
		}
		
		if (m.groupCount() < 3) {
			return false;
		}
		
		String type = m.group(3);
		
		switch (type) {
		
		case "primary" :
			return true;
			
		case "secondary" :
			return true;
			
		case "tertiary" :
			return true;
			
		default :
			return false;
		}
	}
	
	/**
	 * Read in line from GeoJSON file, return Road object that
	 * is represented in that line.
	 * 
	 * @return Road object
	 */
	public Road parseRoad(String roadStr) throws IOException {
		
		String nameReg = "([0-9]+[a-z]+)*([a-zA-Z]+)*((\\s[a-zA-Z]+)|(\\s[0-9]+[a-z]+))*";
		Pattern pName = Pattern.compile("([name]{4})(\\p{Punct}{2}\\s\\p{Punct})(" + nameReg + ")");
		Matcher mName = pName.matcher(roadStr);
		
		boolean foundName = mName.find();
		if (foundName && !(mName.group(3) == null)) {
			roadNames.add(mName.group(3));
		} else {
			return null;
		}
		return null;
	}
	
	/**
	 * Test client
	 */
	public static void main(String[] args) throws IOException {
		
		OSMParser parser = new OSMParser("roads_gen0.geojson");
		String line = parser.nextLine();
		
		while (line != null) {
			if (parser.isRoad(line)) {
				parser.parseRoad(line);
			}
			line = parser.nextLine();
		}
		
		Collections.sort(roadNames);
		for (int i = 0; i < 100; i++) {
			System.out.println(roadNames.get(i));
		}
		
	}

}
