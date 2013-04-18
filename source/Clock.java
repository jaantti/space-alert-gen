import java.io.IOException;


public class Clock extends Thread {
	
	clientThread t[];
	Integer sec = new Integer(0);
	
	public Clock(clientThread t[]) {
		super();
		this.t = t;
	}
	
	public void run(){
		try {
			sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (true){
			for (int j = 0; j < 5; j++){
				if (t[j] != null)
					try {
						t[j].getOos().writeObject(sec);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			sec++;
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
