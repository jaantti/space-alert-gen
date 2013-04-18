
public class CommDown extends MiscEvent {
	
	private int duration;
	
	CommDown(int time, int duration) {
		super(time);
		this.declare = Library.events[6];
		this.duration = duration;
	}
	
	public String toString() { // comm down string
		if (time%60==0) {
			return (int)time/60 + ":" + "0" + time%60 + " - " + (int)(time+duration)/60 + ":" +
					"0" + (time+duration)%60 + " " + declare;
		}
		else {
			return (int)time/60 + ":" + time%60 + " - " + (int)(time+duration)/60 + ":" +
					(time+duration)%60 + " " + declare;
		}

	}
	public boolean tPluss(int[] a, int b) {
		return false;
	}

}
