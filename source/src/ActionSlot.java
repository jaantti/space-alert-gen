import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.*;


public class ActionSlot extends JLabel implements MouseListener {
	int phase;
	
	ActionSlot(){}
	
	ActionSlot(ImageIcon icon, int phase, int offset){
		super(icon);
		this.phase = phase;
		Graphics.raam.add(this);
		this.setBounds(5+55*offset+(phase-1)*10, 600, 50, 80);
		this.addMouseListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		if (Graphics.activeAction != null){
			Graphics.swapListAction(Graphics.action, Graphics.hand, Graphics.activeAction, (JLabel)e.getComponent());
			Graphics.glows[SpaceAlertClient.playerId].setLocation(Graphics.activeAction.getX(), Graphics.activeAction.getY());
			
			int x = ((ActionSlot)e.getComponent()).phase;
			((ActionSlot)e.getComponent()).phase = Graphics.activeAction.phase;
			Graphics.activeAction.phase = x;
			
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
				SpaceAlertClient.oos.flush();
				SpaceAlertClient.oos.reset();
				
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
	
	
}
