import javax.swing.*;


public class JLane extends JLabel {
	int length, x, lane;
	int[] y;

	public JLane(ImageIcon icon, int lane, int length, int x, int[] y) {
		super(icon);
		this.length = length;
		this.x = x;
		this.lane = lane;
		this.y = y;
		Graphics.raam.add(this);
		this.setBounds(5 + lane*55, 5, 50, 588);
	}
	
}
