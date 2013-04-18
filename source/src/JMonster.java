import java.awt.event.*;
import javax.swing.*;


public class JMonster extends JLabel implements MouseListener, WindowListener{
	String nimi;
	
	JMonster(ImageIcon icon, String nimi, int veerg, int rida){
		super(icon);
		this.nimi = nimi;
		Graphics.raam.add(this);
		this.setBounds(1030+5+veerg*105, 5+rida*(143+5), 100, 143);
		this.addMouseListener(this);
	}
	
	JMonster (ImageIcon icon, String nimi){
		super(icon);
		this.nimi = nimi;
		this.setBounds(0, 0, 356, 600);
	}
	
	

	public String getNimi() {
		return nimi;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		//JFrame raam = new JFrame(((JMonster)(arg0.getComponent())).getNimi());
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (int i = 0; i < 8; i++){
			if ((JLabel)e.getComponent() == Graphics.vthreat[i]){
				JFrame raam = new JFrame(((JMonster)Graphics.skaart[i]).getNimi());
				raam.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
				raam.setSize(356, 630);
				//raam.setLayout(null);
				raam.add(Graphics.skaart[i]);
				raam.addWindowListener(this);
				raam.setVisible(true);
			}
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		((JFrame)e.getComponent()).setVisible(false);
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
