import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;


public class Graphics{
	static Random ahv = new Random();
	static JActionCard activeAction;
	//static Event lastEvent = new MiscEvent();
		
	static ImageIcon icon1 = new ImageIcon("src/img/bg.jpg");
	static ImageIcon icon2 = new ImageIcon("src/img/lane1.png");
	//static ImageIcon icon3 = new ImageIcon("src/img/bot_standing.png");
	//static ImageIcon icon4 = new ImageIcon("src/img/bot_down.png");
	static ImageIcon icon5 = new ImageIcon("src/img/cube_grey.png");
	static ImageIcon icon6 = new ImageIcon("src/img/slot1.png");
	static ImageIcon icon8 = new ImageIcon("src/img/cube.png");
	static ImageIcon icon10 = new ImageIcon("src/img/energyBarrel.png");
	
	static ImageIcon[] actionCards = new ImageIcon[24];
	
	static ImageIcon[] playerIcons = new ImageIcon[5];
	static ImageIcon[] tokenIcons = new ImageIcon[8];
	static ImageIcon[] glowIcons = new ImageIcon[5];
	
	static ImageIcon[] threatCards = new ImageIcon[17];
	static ImageIcon[] threatCards_v = new ImageIcon[17];
	static ImageIcon[] inthreatCards = new ImageIcon[13];
	static ImageIcon[] inthreatCards_v = new ImageIcon[13];
	static ImageIcon[] sthreatCards = new ImageIcon[10];
	static ImageIcon[] sthreatCards_v = new ImageIcon[10];
	static ImageIcon[] sinthreatCards = new ImageIcon[11];
	static ImageIcon[] sinthreatCards_v = new ImageIcon[11];
	
	static ArrayList<JLabel> action = new ArrayList<JLabel>();
	static ArrayList<JLabel> hand = new ArrayList<JLabel>();
	static int[][] actionAll = new int[5][12];
	
	static JLabel event = new JLabel();
	
	static JLabel[] shieldR = new JLabel[2];
	static JLabel[] shieldW = new JLabel[3];
	static JLabel[] shieldB = new JLabel[2];
	static JLabel[] reactorR = new JLabel[3];
	static JLabel[] reactorW = new JLabel[5];
	static JLabel[] reactorB = new JLabel[3];
	static JLabel[] energyBarrel = new JLabel[3]; 
	static JLabel[] vthreat = new JLabel[8];
	static JLabel[] skaart = new JLabel[8];
	static JLabel[] lane = new JLabel[4];
	
	static JLabel[] glows = new JLabel[5];
	static JLabel[] player0Loc = new JLabel[6];
	static JLabel[] player1Loc = new JLabel[6];
	static JLabel[] player2Loc = new JLabel[6];
	static JLabel[] player3Loc = new JLabel[6];
	static JLabel[] player4Loc = new JLabel[6];
	static JLabel[][] playerLocs = {player0Loc, player1Loc, player2Loc, player3Loc, player4Loc};
	
	static JLabel[] bot0Loc = new JLabel[6];
	static JLabel[] bot1Loc = new JLabel[6];
	static JLabel[][] botLocs = {bot0Loc, bot1Loc};
	
	static JLabel nextPage;
	static JLabel previousPage;
	static JLabel clock;

	static JLabel red;
	static JLabel bg;
	
	static JFrame raam;
	
	public static void GUI(){

		//Every player's action list default values

		for (int i = 0; i < 5; i++){
			for (int j = 0; j < 12; j++){
				actionAll[i][j] = -1;
			}
		}

		raam = new JFrame("Raam");		
		raam.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		raam.setSize(1280, 800);
		raam.setLayout(null);
		
		//actionCard-ide icon-id
		for (int i = 0; i < 24; i++){
			actionCards[i] = new ImageIcon((String)("src/img/action" + i + ".png"));
		}
		
		iconsFromFolder(threatCards, "src/img/spaceAlertPictures/threats/external");
		iconsFromFolder(threatCards_v, "src/img/spaceAlertPictures/threats/external/v");
		iconsFromFolder(inthreatCards, "src/img/spaceAlertPictures/threats/internal");
		iconsFromFolder(inthreatCards_v, "src/img/spaceAlertPictures/threats/internal/v");
		iconsFromFolder(sthreatCards, "src/img/spaceAlertPictures/threats/sExternal");
		iconsFromFolder(sthreatCards_v, "src/img/spaceAlertPictures/threats/sExternal/v");
		iconsFromFolder(sinthreatCards, "src/img/spaceAlertPictures/threats/sInternal");
		iconsFromFolder(sinthreatCards_v, "src/img/spaceAlertPictures/threats/sInternal/v");
		
		iconsFromFolder(playerIcons, "src/img/spaceAlertPictures/players");
		iconsFromFolder(glowIcons, "src/img/spaceAlertPictures/glow");
		
		iconsFromFolder(tokenIcons, "src/img/spaceAlertPictures/Token");
		
		for (int i = 0; i < 5; i++) {
			glows[i] = new JLabel(glowIcons[i]);
			glows[i].setBounds(-100, -100, 50, 80);
			raam.add(glows[i]);
		}
		
		bg = new JLabel(icon1);
		
		
		nextPage = new JLabel(new ImageIcon("src/img/nextPage.png"));
		nextPage.setBounds(710 + 9*55, 630, 30, 30);
		nextPage.addMouseListener(new PageListener());
		raam.add(nextPage);
		
		previousPage = new JLabel(new ImageIcon("src/img/previousPage.png"));
		previousPage.setBounds(710, 630, 30, 30);
		previousPage.addMouseListener(new PageListener());
		raam.add(previousPage);
		
		//lane-id vasakule
		for (int i = 0; i < 4; i++){
			lane[i] = new JLane(icon2, i, 15, 4, new int[]{8, 11});
			raam.add(lane[i]);
			lane[i].setBounds(5 + i*55, 5, 50, 588);
		}
		
		//slotid alla
		int phase = 1;
		for (int i = 0; i < 12; i++){
			if (i == 3 || i == 7) phase += 1;
			action.add(i, new ActionSlot(icon6, phase, i));
		}
		
		updateField();
		
		raam.add(new JLabel());
		raam.add(bg);
		bg.setBounds(230, 5, 800, 588);
		raam.setVisible(true);
		
	}
	
	public static void updateField(){
		
		if (shieldR[0] != null){
			purge(shieldR);
			purge(shieldW);
			purge(shieldB);
			purge(reactorR);
			purge(reactorW);
			purge(reactorB);
			for (int i = 0; i < playerLocs.length; i++){
				purge(playerLocs[i]);
			}
		}
		
		//playeri slotid ja playerid
		
		for (int i = 0; i < playerLocs.length; i++){
			for (int j = 0; j < playerLocs[i].length; j++){
				if (j != 1) playerLocs[i][j] = new JPlayer(j, i);
				else playerLocs[i][j] = new JPlayer(playerIcons[i], i, 0);
			}
		}
		//bottide slotid ja botid
		/*for (int i = 0; i < botLocs.length; i++){
			for (int j = 0; j < botLocs[i].length; j++){
				if (j != 1) botLocs[i][j] = new JPlayer(j, i);
				else botLocs[i][j] = new JPlayer(playerIcons[i], i, 0);
			}
		}*/
		
		
		shieldR[0] = new JEnergy(icon8, 0, 0);
		shieldR[1] = new JEnergy(null, 0, 1);
		
		shieldW[0] = new JEnergy(icon8, 1, 0);
		shieldW[1] = new JEnergy(null, 1, 1);
		shieldW[2] = new JEnergy(null, 1, 2);
		
		shieldB[0] = new JEnergy(icon8, 2, 0);
		shieldB[1] = new JEnergy(null, 2, 1);
		
		reactorR[0] = new JEnergy(icon8, 3, 0);
		reactorR[1] = new JEnergy(icon8, 3, 1);
		reactorR[2] = new JEnergy(null, 3, 2);
		
		reactorW[0] = new JEnergy(icon8, 4, 0);
		reactorW[1] = new JEnergy(icon8, 4, 1);
		reactorW[2] = new JEnergy(icon8, 4, 2);
		reactorW[3] = new JEnergy(null, 4, 3);
		reactorW[4] = new JEnergy(null, 4, 4);
		
		reactorB[0] = new JEnergy(icon8, 5, 0);
		reactorB[1] = new JEnergy(icon8, 5, 1);
		reactorB[2] = new JEnergy(null, 5, 2);

		for (int i = 0; i < 3; i++) {
			energyBarrel[i] = new JLabel(icon10);
			raam.add(energyBarrel[i]);
			energyBarrel[i].setBounds(685+16*i, 360, 15, 30);
		}
		
		for (int j = 0; j < action.size(); j++){
			
			boolean[][] checklist = new boolean[6][3];
			/*for (int i = 0; i < 6; i++){
				for (int k = 0; k < 3; k++){
					checklist[i][k] ;
				}
			}*/
			
			
			for (int i = 0; i < playerLocs.length; i++){
			
				int m = 0;
				for (int k = 0; k < playerLocs[i].length; k++) {
					if (((JPlayer)playerLocs[i][k]).colour != -1) {
						m = k;
						break;
					}
				}
				switch (actionAll[i][j]){
					case 0:{
						((JPlayer)(playerLocs[i][m])).moveRed();
						break;
					}
					case 1:{
						((JPlayer)(playerLocs[i][m])).moveBlue();
						break;
					}
					case 2:{
						((JPlayer)(playerLocs[i][m])).changeDeck();
						break;
					}
					case 3:{
						if (!checklist[m][0]){
							((JPlayer)(playerLocs[i][m])).useA();
							checklist[m][0] = true;
						}
						break;
					}
					case 4:{
						if (!checklist[m][1]){
							((JPlayer)(playerLocs[i][m])).useB();
							checklist[m][1] = true;
						}
						break;
					}
					case 5:{
						if (!checklist[m][2]){
							((JPlayer)(playerLocs[i][m])).useC();
							checklist[m][2] = true;
						}
						break;
					}
					case 6:{
						((JPlayer)(playerLocs[i][m])).useBot();
						break;
					}
				}
			}
		}
		raam.remove(bg);
		raam.add(bg);
		raam.repaint();

	}
	static void purge(JLabel[] kill){
		for (int i = 0; i < kill.length; i++){
			raam.remove(kill[i]);
			(kill[i]) = null;

		}
	}
	
	static void swapList(JLabel[] lista, JLabel[] listb, JLabel a, JLabel b){
		if (!(a.equals(b))){
			int x = a.getX();
			int y = a.getY();
			a.setLocation(b.getX(), b.getY());
			b.setLocation(x, y);
					
			x = -1;
			y = -1;
			
			for (int i = 0; i < lista.length; i++){
				if (lista[i] == a){
					x = i;				
				}
				else if (lista[i] == b){
					y = i;
				}
			}
			if (x != -1) {
				lista[x] = b;
			}
			else {
				for (int i = 0; i < listb.length; i++){
					if (listb[i] == a){
						listb[i] = b;
					}
				}
			}
			if (y != -1){
				lista[y] = a;
			}
			else {
				for (int i = 0; i < listb.length; i++){
					if (listb[i] == b){
						listb[i] = a;
					}
				}
			}
		}
	}
	static void swapListAction(ArrayList<JLabel> lista, ArrayList<JLabel> listb, JLabel a, JLabel b){
		if (!(a.equals(b))){
			int x = a.getX();
			int y = a.getY();
			a.setLocation(b.getX(), b.getY());
			b.setLocation(x, y);
					
			x = -1;
			y = -1;

			x = lista.indexOf(a);
			
			if (x == -1) {
				x = listb.indexOf(a);
				y = lista.indexOf(b);
				if (y == -1) {
					y = listb.indexOf(b);
					listb.add(x, b);
					listb.remove(x+1);
					listb.add(y, a);
					listb.remove(y+1);
				}
				else {
					listb.add(x, b);
					listb.remove(x+1);
					lista.add(y, a);
					lista.remove(y+1);
				}
			}
			else {
				y = lista.indexOf(b);
				if (y == -1) {
					y = listb.indexOf(b);
					lista.add(x, b);
					lista.remove(x+1);
					listb.add(y, a);
					listb.remove(y+1);
				}
				else {
					lista.add(x, b);
					lista.remove(x+1);
					lista.add(y, a);
					lista.remove(y+1);
				}
			}
		}
	}
	public static void iconsFromFolder(ImageIcon[] threatCards, String path){
		ArrayList<File> threatFolder = new ArrayList<File>();
		threatFolder.addAll(Arrays.asList(new File(path).listFiles()));
		for (int i = 0; i < threatCards.length; i++){
			if (threatFolder.get(i).isDirectory()) threatFolder.remove(i);
			threatCards[i] = new ImageIcon(threatFolder.get(i).getPath(), threatFolder.get(i).getName());
		}
	}

	
	
}
