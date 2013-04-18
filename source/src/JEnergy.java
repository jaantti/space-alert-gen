import javax.swing.*;


public class JEnergy extends JPlayer {

	JEnergy (ImageIcon icon, int location, int offset){
		super(icon);
		this.location = location;
		Graphics.raam.add(this);
		switch (location){
			case 0:{
				this.setBounds(350+offset*15, 145, 15, 15);
				break;
			}
			case 1:{
				this.setBounds(660+offset*15, 90, 15, 15);
				break;
			}
			case 2:{
				this.setBounds(870+offset*15, 145, 15, 15);
				break;
			}
			case 3:{
				this.setBounds(385+offset*15, 360, 15, 15);
				break;
			}
			case 4:{
				this.setBounds(600+offset*15, 375, 15, 15);
				break;
			}
			case 5:{
				this.setBounds(835+offset*15, 360, 15, 15);
				break;
			}
		}
	}

	static void charge (JLabel[] to, JLabel[] from){
		
		for (JLabel j:from){
			if (j.getIcon() == Graphics.icon8){
				for (JLabel i:to){
					if (i.getIcon() == null){
						Graphics.swapList(to, from, i, j);
						break;
					}
				}
			}
		}
	}
}
