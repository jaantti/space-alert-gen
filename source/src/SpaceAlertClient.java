
import java.awt.Font;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JLabel;

public class SpaceAlertClient implements Runnable{

	// Declaration section
	// clientClient: the client socket
	// oos: the output stream
	// ois: the input stream

	static Socket clientSocket = null;
	static ObjectOutputStream oos = null;
	static ObjectInputStream ois = null;
	static BufferedReader inputLine = null;
	static boolean closed = false;
	
	static int playerId;
	static int phase = -1;

	public static void main(String[] args) {
		
		// The default port	

		int port_number=20000;
		String host="localhost";

		if (args.length < 2)
		{
			System.out.println("Usage: java MultiThreadChatClient  \n"+
					"Now using host="+host+", port_number="+port_number);
		} else {
			host=args[0];
			port_number=Integer.valueOf(args[1]).intValue();
		}
		// Initialization section:
		// Try to open a socket on a given host and port
		// Try to open input and output streams
		try {
			clientSocket = new Socket(host, port_number);
			//inputLine = new BufferedReader(new InputStreamReader(System.in));
			oos = new ObjectOutputStream(clientSocket.getOutputStream());
			ois = new ObjectInputStream(clientSocket.getInputStream());
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host "+host);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to the host "+host);
		}

		// If everything has been initialized then we want to write some data
		// to the socket we have opened a connection to on port port_number 

		if (clientSocket != null && oos != null && ois != null) {
			
			
			try {
				
				playerId = ois.readInt();
				System.out.println(playerId);
				// Create a thread to read from the server
				Graphics.GUI();


				new Thread(new SpaceAlertClient()).start();
				

				while (!closed) {
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//oos.println(inputLine.readLine()); 
				}

				// Clean up:
				// close the output stream
				// close the input stream
				// close the socket

				oos.close();
				ois.close();
				clientSocket.close();   
			} catch (IOException e) {
				System.err.println("IOException:  " + e);
				e.printStackTrace();
			}
		}
	}           

	public void run() {		
		Object fromServer;
		int threat_H = 0, threat_V = 0;
		
		// Keep on reading from the socket till we receive the "Bye" from the server,
		// once we received that then we want to break.
		try{
			while ((fromServer = ois.readObject()) != null) {
				
				String fromSvr = fromServer.toString();
				//System.out.println(fromSvr);
				
				try{
					Graphics.actionAll = ((int[][]) fromServer);
					Graphics.updateField();
				}
				catch(ClassCastException e){
					
				}
				
				if (fromSvr.indexOf("ended") != -1 || fromSvr.indexOf("Mission starting") != -1 || fromSvr.indexOf("Mission complete") != -1){
					if (fromSvr.indexOf("Mission complete") == -1) JActionCard.giveCards(5);
					phase++;
					
					switch (phase) {
					case 1:{
						for (int i = 0; i < 3; i++){
							Graphics.action.get(i).removeMouseListener((MouseListener) Graphics.action.get(i));
						}
						if (Graphics.action.indexOf(Graphics.activeAction) < 3) Graphics.activeAction = null;
						break;
					}
					case 2:{
						for (int i = 3; i < 7; i++){
							Graphics.action.get(i).removeMouseListener((MouseListener) Graphics.action.get(i));
						}
						if (Graphics.action.indexOf(Graphics.activeAction) < 7) Graphics.activeAction = null;
						break;
					}
					case 3:{
						for (int i = 7; i < 12; i++){
							Graphics.action.get(i).removeMouseListener((MouseListener) Graphics.action.get(i));
						}
						if (Graphics.action.indexOf(Graphics.activeAction) < 12) Graphics.activeAction = null;
						break;
					}
					}
				}
				else if (fromSvr.indexOf("Incoming") != -1){
					JActionCard.giveCards(1);
				}
				
				try {
					int sec = (int)fromServer;
					try {
						Graphics.raam.remove(Graphics.clock);
					} catch (NullPointerException e){}
					
					Graphics.clock = new JLabel((int)(sec/60) + ":" + (sec%60 == 0 ? "00" : (sec%60 < 10 ? "0" + sec%60 : sec%60)));
					Graphics.raam.add(Graphics.clock);
					Graphics.clock.setBounds(590, 690, 100, 20);
					Graphics.clock.setFont(new Font("", 1, 24));
				}
				catch (ClassCastException e){
				}
				
				try {
					Graphics.raam.remove(Graphics.event);
					Graphics.event = new JLabel(((Event)fromServer).toString());
					Graphics.raam.add(Graphics.event);
					Graphics.event.setBounds(5, 690, 750, 20);
				} catch (ClassCastException e1) {
				}
				//Graphics.lastEvent = (Event)fromServer;
				if (fromServer.toString().indexOf("threat") != -1){
					if (fromServer.toString().indexOf("Serious") != -1){
						((seriousThreat)fromServer).kartulivott(threat_H, threat_V);
					}
					else{
						((Threat)fromServer).kartulivott(threat_H, threat_V);
					}
					
					int lane;
					if (fromSvr.indexOf("Red") != -1){
						lane = 0;
					}
					else if (fromSvr.indexOf("White") != -1){
						lane = 1;
					}
					else if (fromSvr.indexOf("Blue") != -1){
						lane = 2;
					}
					else {
						lane = 3;
					}
					JThreatToken.new_token(((Threat)fromServer).tPluss-1, (JLane)(Graphics.lane[lane]), (JMonster)(Graphics.vthreat[threat_V+threat_H*2]));
					
					threat_V++;
					if (threat_V > 1){
						threat_V = 0;
						threat_H++;
					}
				}

			//if (endcondition) break;
			}
			//closed=true;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
}