
import java.io.*;
import java.net.*;

public class SpaceAlertServer{

	// Declaration section:
	// declare a server socket and a client socket for the server
	// declare an input and an output stream

	static  Socket clientSocket = null;
	static  ServerSocket serverSocket = null;
	static  clientThread t[] = new clientThread[5];
	//static Announce a[] = new Announce[5];

	static Event[] events = Generator.Gen(3);

	// This chat server can accept up to 10 clients' connections

	//static  clientThread t[] = new clientThread[10];           

	public static void main(String args[]) throws IOException {

		// The default port

		int port_number=20000;

		if (args.length < 1)
		{
			System.out.println("Usage: java MultiThreadChatServer \n"+
					"Now using port number="+port_number);
		} else {
			port_number=Integer.valueOf(args[0]).intValue();
		}

		// Initialization section:
		// Try to open a server socket on port port_number (default 2222)
		// Note that we can't choose a port less than 1023 if we are not
		// privileged users (root)

		try {
			serverSocket = new ServerSocket(port_number);
		}
		catch (IOException e)
		{System.out.println(e);}

		// Create a socket object from the ServerSocket to listen and accept 
		// connections.
		// Open input and output streams for this socket will be created in 
		// client's thread since every client is served by the server in
		// an individual thread
		
		
		for (Event i:events){
			System.out.println(i.toString());
		}
		new java.util.Scanner(System.in).nextLine();
		
		new Announce(t, events).start();
		new Clock(t).start();
		
		while(true){
			try {
				clientSocket = serverSocket.accept();
				
				for(int i=0; i<5; i++){
					if(t[i]==null){
						(t[i] = new clientThread(clientSocket, t, i)).start();
						break;
					}
				}

			}
			catch (IOException e) {
				System.out.println(e);}
		}

	}
} 