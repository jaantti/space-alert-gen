
public class MiscEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8847266323761395690L;

	MiscEvent(int time) {
		super(time);
		this.declare = Library.events[Random.randint(4,5)];
	}
	
	public MiscEvent(String string, int time) {
		super(time);
		this.declare = string;
	}

	public MiscEvent() {
		this.declare = "";
	}

	public String toString() { //misc event string
		if (time%60==0) {
			return (int)time/60 + ":" + "0" + time%60 + " - " + declare;
		}
		else {
			return (int)time/60 + ":" + time%60 + " - " + declare;
		}
	}
	
	public boolean tPluss(int[] a, int b) {
		return false;
	}
}
