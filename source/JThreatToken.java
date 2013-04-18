import javax.swing.*;


public class JThreatToken extends JLabel {
	
	int number, pos;
	JLane lane;
	
	JThreatToken(int number, JLane lane){
		super(Graphics.tokenIcons[number]);
		this.lane = lane;
		Graphics.raam.add(this);
		this.setBounds(5+55*lane.lane+10, 5+15*number, 30, 30);
		Graphics.raam.remove(lane);
		Graphics.raam.add(lane);
	}
	JThreatToken(JMonster threat, int number){
		super(Graphics.tokenIcons[number]);
		Graphics.raam.add(this);
		this.setBounds(threat.getX()+35, threat.getY()+120, 30, 30);
		Graphics.raam.remove(threat);
		Graphics.raam.add(threat);
	}
	
	static public void new_token(int number, JLane lane, JMonster threat) {
		new JThreatToken(number, lane);
		new JThreatToken(threat, number);
	}
}
