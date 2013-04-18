import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class JActionCard extends JLabel implements MouseListener{
	boolean orientation;
	int actionType, moveType, phase;
	static int page = 0;

	JActionCard(int offset, int moveType, int actionType){
		super(Graphics.actionCards[moveType*4+actionType]);
		this.moveType = moveType;
		this.actionType = actionType;
		this.phase = 0;
		orientation = false;
		Graphics.raam.add(this);
		this.setBounds(50+40+20+25+55*11+5+5+55*offset, 600, 50, 80);
		this.addMouseListener(this);
	}

	public int getActionType() {
		return actionType;
	}

	public boolean getOrientation() {
		return orientation;
	}

	public void setOrientation(boolean orientation) {
		this.orientation = orientation;
	}

	public int getMoveType() {
		return moveType;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 1){

			Graphics.activeAction = (JActionCard)(e.getComponent());
			Graphics.glows[SpaceAlertClient.playerId].setLocation(Graphics.activeAction.getX(), Graphics.activeAction.getY());
		}
		else if (e.getButton() == 3){
			if (((JActionCard)(e.getComponent())).getOrientation()) ((JActionCard)(e.getComponent())).setIcon(Graphics.actionCards[((JActionCard)(e.getComponent())).moveType*4+((JActionCard)(e.getComponent())).actionType]);
			else ((JActionCard)(e.getComponent())).setIcon(Graphics.actionCards[((JActionCard)(e.getComponent())).moveType*4+((JActionCard)(e.getComponent())).actionType+12]);
			((JActionCard)(e.getComponent())).orientation = !(((JActionCard)(e.getComponent())).orientation);
			
			for (int i = 0; i < Graphics.action.size(); i++){
				try {
					
					if (!((JActionCard)Graphics.action.get(i)).orientation){
						Graphics.actionAll[SpaceAlertClient.playerId][i] = ((JActionCard)Graphics.action.get(i)).moveType;
					}
					else Graphics.actionAll[SpaceAlertClient.playerId][i] = ((JActionCard)Graphics.action.get(i)).actionType + 3;
				}
				catch (ClassCastException e1) {
					Graphics.actionAll[SpaceAlertClient.playerId][i] = -1;
				}
			}

			try {
				SpaceAlertClient.oos.writeObject(Graphics.actionAll);
				SpaceAlertClient.oos.reset();
				//SpaceAlertClient.oos.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Graphics.updateField();
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	public static void giveCards(int n){
		int current = Graphics.hand.size();
		for (int i = current; i < current+n; i++){
			int moveType = Graphics.ahv.nextInt(3);
			int actionType = Graphics.ahv.nextInt(4);
			Graphics.hand.add(new JActionCard(i%8, moveType, actionType));
			updatePage();
		}

	}
	public static void previousPage(){

		page = (page == 0 ? 2 : page - 1);
		System.out.println(page);
		updatePage();
		
		
	}
	public static void nextPage(){

		page = (page + 1) % 3;
		System.out.println(page);
		updatePage();
		
		

	}
	public static void updatePage(){

		
		
		if (page == 0){
			try {
				for (int i = 0; i < 8; i++){
					Graphics.hand.get(i).setVisible(true);
				}
			} catch (Exception e) {/*System.err.println(e);*/}

			try {
				for (int i = 8; i < 24; i++){
					Graphics.hand.get(i).setVisible(false);
				}
			} catch (Exception e) {/*System.err.println(e);*/}

		}
		else if (page == 1){

			try {
				for (int i = 8; i < 16; i++){
					Graphics.hand.get(i).setVisible(true);
				}				
			} catch (Exception e) {/*System.err.println(e);*/}

			try {
				for (int i = 0; i < 8; i++){
					Graphics.hand.get(i).setVisible(false);
				}
			} catch (Exception e) {/*System.err.println(e);*/}

			try {
				for (int i = 16; i < 24; i++){
					Graphics.hand.get(i).setVisible(false);
				}
			} catch (Exception e){/*System.err.println(e);*/}
		}
		else if (page == 2){

			try {
				for (int i = 0; i < 16; i++){
					Graphics.hand.get(i).setVisible(false);
				}
			} catch (Exception e) {/*System.err.println(e);*/}

			try {
				for (int i = 16; i < 24; i++){
					Graphics.hand.get(i).setVisible(true);
				}
			} catch (Exception e) {/*System.err.println(e);*/}

		}

	}



}
