
public class Random {
	public static int randint(int min, int max) {
		int random = min+(int)(Math.random()*(max-min)*1000)%(max-min+1);
		return random;
	}
	
	static int randAeg(int minAeg, int maxAeg) { //aegade korduse vältimine
		int time;
		do {
			int x = Random.randint(minAeg, maxAeg);
			time = Library.getAeg()[x];
			Library.setAeg(x, -1);
		} while (time == -1);
		return time;
	}
}
