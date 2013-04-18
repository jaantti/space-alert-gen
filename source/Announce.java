import java.io.*;


public class Announce extends Thread{

	Event[] events;
	clientThread t[];

	public Announce(clientThread t[], Event[] events) {
		super();
		this.t = t;
		this.events = events;
	}


	@Override
	public void run(){
		try {
			sleep(1000);
			for (int i = 0; i < events.length; i++){
				for (int j = 0; j < 5; j++){
					if (t[j] != null) t[j].getOos().writeObject(events[i]);
				}

				sleep((events[i+1].getTime()-events[i].getTime())*1000);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("uhh,hh e1");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.err.println("uhh,hh1 e2");
		}

	}

}
