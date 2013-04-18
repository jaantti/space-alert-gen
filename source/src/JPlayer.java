import javax.swing.*;


public class JPlayer extends JLabel {
	int location, colour;
	
	JPlayer(ImageIcon icon, int colour, int offset){
		super(icon);
		location = 1;
		this.colour = colour;
		Graphics.raam.add(this);
		this.setBounds(570+colour*25, 160, 25, 30);
	}
	JPlayer(int location, int offset){
		super((Icon) null);
		this.location = location;
		colour = -1;
		Graphics.raam.add(this);
		switch (location){
			case 0:{
				this.setBounds(400+offset*25, 210, 25, 30);
				break;
			}
			case 1:{
				this.setBounds(570+offset*25, 160, 25, 30);
				break;
			}
			case 2:{
				this.setBounds(720+offset*25, 210, 25, 30);
				break;
			}
			case 3:{
				this.setBounds(405+offset*25, 360, 25, 30);
				break;
			}
			case 4:{
				this.setBounds(525+offset*25, 385, 25, 30);
				break;
			}
			case 5:{
				this.setBounds(735+offset*25, 360, 25, 30);
				break;
			}
		}
	}
	JPlayer(ImageIcon icon){
		super(icon);
	}
	
	void moveRed(){
		if (location != 0 && location != 3){
			location--;
			Graphics.swapList(Graphics.playerLocs[colour], Graphics.playerLocs[colour], this, Graphics.playerLocs[colour][location]);
		}
	}
	void moveBlue(){
		if (location != 2 && location != 5){
			location++;
			Graphics.swapList(Graphics.playerLocs[colour], Graphics.playerLocs[colour], this, Graphics.playerLocs[colour][location]);
		}
	}
	void changeDeck(){
		if (location == 0 || location == 1 || location == 2) location += 3;
		else location -= 3;
		Graphics.swapList(Graphics.playerLocs[colour], Graphics.playerLocs[colour], this, Graphics.playerLocs[colour][location]);
	}
	void useA(){
		switch (location){
			case 0:{
				for (int i = 0; i < Graphics.reactorR.length; i++){
					if (Graphics.reactorR[i].getIcon() != null) {
						Graphics.raam.remove(Graphics.reactorR[i]);
						Graphics.reactorR[i] = new JEnergy(null, 3, i);
						break;
					}
				}
				break;
			}
			case 1:{
				for (int i = 0; i < Graphics.reactorW.length; i++){
					if (Graphics.reactorW[i].getIcon() != null) {
						Graphics.raam.remove(Graphics.reactorW[i]);
						Graphics.reactorW[i] = new JEnergy(null, 4, i);
						break;
					}
				}
				break;
			}
			case 2:{
				for (int i = 0; i < Graphics.reactorB.length; i++){
					if (Graphics.reactorB[i].getIcon() != null) {
						Graphics.raam.remove(Graphics.reactorB[i]);
						Graphics.reactorB[i] = new JEnergy(null, 5, i);
						break;
					}
				}
				break;
			}
			case 3:{
				break;
			}
			case 4:{
				for (int i = 0; i < Graphics.reactorW.length; i++){
					if (Graphics.reactorW[i].getIcon() != null) {
						Graphics.raam.remove(Graphics.reactorW[i]);
						Graphics.reactorW[i] = new JEnergy(null, 4, i);
						break;
					}
				}
				break;
			}
			case 5:{
				break;
			}
		}
	}
	void useB(){
		switch (location) {
			case 0:{
				JEnergy.charge(Graphics.shieldR, Graphics.reactorR);
				break;
			}
			case 1:{
				JEnergy.charge(Graphics.shieldW, Graphics.reactorW);
				break;
			}
			case 2:{
				JEnergy.charge(Graphics.shieldB, Graphics.reactorB);
				break;
			}
			case 3:{
				JEnergy.charge(Graphics.reactorR, Graphics.reactorW);
				break;
			}
			case 4:{
				for (int j = 0; j < Graphics.energyBarrel.length; j++){
					if (Graphics.energyBarrel[j] != null){
						for (int i = 0; i < Graphics.reactorW.length; i++){
							if (Graphics.reactorW[i].getIcon() == null){
								Graphics.reactorW[i] = new JEnergy(Graphics.icon8, 4, i);
							}
						}
						Graphics.raam.remove(Graphics.energyBarrel[j]);
						Graphics.energyBarrel[j] = null;
						break;
					}
				}
				break;
			}
			case 5:{
				JEnergy.charge(Graphics.reactorB, Graphics.reactorW);
				break;
			}
		}
		
	}
	void useC(){
		
	}
	void useBot(){
		
	}
	
}
