import javax.swing.ImageIcon;

public class JBot extends JPlayer {
	boolean standing;
	
	JBot(ImageIcon icon, int offset) {
		super(icon, -1, offset);
		switch (location){
			case 0:{
				this.setBounds(400+offset*50, 210, icon.getIconHeight(), icon.getIconWidth());
				break;
			}
			case 1:{
				this.setBounds(570+offset*25, 160, icon.getIconHeight(), icon.getIconWidth());
				break;
			}
			case 2:{
				this.setBounds(720+offset*25, 210, icon.getIconHeight(), icon.getIconWidth());
				break;
			}
			case 3:{
				this.setBounds(405+offset*25, 360, icon.getIconHeight(), icon.getIconWidth());
				break;
			}
			case 4:{
				this.setBounds(525+offset*25, 385, icon.getIconHeight(), icon.getIconWidth());
				break;
			}
			case 5:{
				this.setBounds(735+offset*25, 360, icon.getIconHeight(), icon.getIconWidth());
				break;
			}
		}
	}
}
