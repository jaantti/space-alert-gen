import java.util.ArrayList;
import java.util.Arrays;


public class Library {
	static String[] events = {"External threat", "Internal threat", 
			"Serious external threat", "Serious internal threat",
			"Data transfer", "Incoming data", "Communications down", 
			"Data transfer Complete", "Communications restored",
			"First phase has ended", "Second phase has ended", "Mission complete",
			"First phase ends in 1 minute", "First phase ends in 20 seconds",
			"Second phase ends in 1 minute", "Second phase ends in 20 seconds",
			"Mission will end in 1 minute", "Mission will end in 20 seconds", 
			"Mission starting"};
	
	static String[] lanes = {"Red", "White", "Blue"};
	
	static ArrayList<Integer> threat = new ArrayList<Integer>();
	static ArrayList<Integer> sthreat = new ArrayList<Integer>();
	static ArrayList<Integer> inthreat = new ArrayList<Integer>();
	static ArrayList<Integer> sinthreat = new ArrayList<Integer>();
	
	static int[] aeg = new int[61];
	
	public static String[] getEvents() {
		return events;
	}
	
	public static String[] getLanes() {
		return lanes;
	}

	public static int[] getAeg() {
		return aeg;
	}

	public static void setAeg(int koht, int uus) {
		Library.aeg[koht] = uus;
	}
	
}
