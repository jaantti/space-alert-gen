
public class Threat extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6822935361030933641L;
	String lane, name;
	int tPluss, id;
	boolean internal;
	
	Threat(int time, boolean kana) {
		super(time);
	}
	Threat(int time) {
		super(time);
		int ahv = Random.randint(0, 3);
		if (ahv == 3){
			this.declare = Library.events[1];
			this.internal = true;
			this.id = Library.inthreat.remove(Graphics.ahv.nextInt(Library.inthreat.size()));
		}
		else {
			this.declare = Library.events[0];
			this.lane = Library.lanes[ahv];
			this.internal = false;
			this.id = Library.threat.remove(Graphics.ahv.nextInt(Library.threat.size()));
		}
	}
	public String toString() { //threat string
		if (internal){
		return (int)time/60 + ":" + (time%60==0 ? "0" + time%60 : time%60) + " - " + "Time T+" + tPluss + ",  " +
				declare;
		}
		else{
			return (int)time/60 + ":" + (time%60==0 ? "0" + time%60 : time%60) + " - " + "Time T+" + tPluss + ",  " +
					declare + ", Zone " + lane;
		}
	}
	public int gettPluss() {
		return tPluss;
	}

	public boolean tPluss(int[] tPluss, int muutuja) {
		this.tPluss = tPluss[muutuja];
		return true;
	}
	
	public void kartulivott(int threat_H, int threat_V){
		if (internal){
			Graphics.vthreat[threat_H*2+threat_V] = new JMonster(
					Graphics.inthreatCards_v[id],
					Graphics.inthreatCards_v[id].getDescription(),
					threat_V, threat_H);
			Graphics.skaart[threat_H*2+threat_V] = new JMonster(
					Graphics.inthreatCards[id],
					Graphics.inthreatCards[id].getDescription());
			
		}
		else{
			Graphics.vthreat[threat_H*2+threat_V] = new JMonster(
					Graphics.threatCards_v[id],
					Graphics.threatCards_v[id].getDescription(),
					threat_V, threat_H);
			Graphics.skaart[threat_H*2+threat_V] = new JMonster(
					Graphics.threatCards[id],
					Graphics.threatCards[id].getDescription());
			
		}
	}
}
