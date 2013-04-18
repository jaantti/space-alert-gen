
public class seriousThreat extends Threat{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3033135359512729383L;

	seriousThreat(int time) {
		super(time, true);
		int ahv = Random.randint(0, 3);
		if (ahv == 3){
			this.declare = Library.events[3];
			this.internal = true;
			this.id = Library.sinthreat.remove(Graphics.ahv.nextInt(Library.sinthreat.size()));
		}
		else {
			this.declare = Library.events[2];
			this.lane = Library.lanes[ahv];
			this.internal = false;
			this.id = Library.sthreat.remove(Graphics.ahv.nextInt(Library.sthreat.size()));
		}
	}
	
	@Override
	public void kartulivott(int threat_H, int threat_V){
		if (internal){
			Graphics.vthreat[threat_H*2+threat_V] = new JMonster(
					Graphics.sinthreatCards_v[id],
					Graphics.sinthreatCards_v[id].getDescription(),
					threat_V, threat_H);
			Graphics.skaart[threat_H*2+threat_V] = new JMonster(
					Graphics.sinthreatCards[id],
					Graphics.sinthreatCards[id].getDescription());
			
		}
		else{
			Graphics.vthreat[threat_H*2+threat_V] = new JMonster(
					Graphics.sthreatCards_v[id],
					Graphics.sthreatCards_v[id].getDescription(),
					threat_V, threat_H);
			Graphics.skaart[threat_H*2+threat_V] = new JMonster(
					Graphics.sthreatCards[id],
					Graphics.sthreatCards[id].getDescription());
			
		}
	}
	
}
