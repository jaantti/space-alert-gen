import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class clientThread extends Thread{

	Socket s;
	ObjectInputStream ois = null;
	ObjectOutputStream oos = null;
	clientThread t[];
	int playerId;

	public clientThread(Socket s, clientThread[] t, int playerId) {
		super();
		this.s = s;
		this.t = t;
		this.playerId = playerId;
	}       


	@Override
	public void run() 
	{

		try{
			int[][] in;
			
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());			
			
			oos.writeInt(playerId);

			while (true) {
				
				in = (int[][])ois.readObject();
				if(in == null) break;
				
				for(int i=0; i<5; i++){
					if (t[i]!=null && t[i]!= this) {
						t[i].getOos().writeObject(in);
						//t[i].getOos().flush();
					}
				}

				// Clean up:
				// Set to null the current thread variable such that other client could
				// be accepted by the server

				/*for(int i=0; i<5; i++)
					if (t[i]==this) t[i]=null;*/  

				// close the output stream
				// close the input stream
				// close the socket
				}
			//ois.close();
			//oos.close();
			//s.close();
			
		}
		catch(IOException e){
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	public ObjectOutputStream getOos() {
		return oos;
	}
}
