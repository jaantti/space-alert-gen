
public class Generator {

	static Event[] Gen(int raskus){
		Event[] aEvents;
		if (raskus == 1){
			aEvents = fill(3,5,0,2,22);
		}
		else if (raskus == 2){
			aEvents = fill(4,7,1,3,27);
			
		}
		else if (raskus == 3){
			aEvents = fill(6,8,2,3,27);
		}
		else {aEvents = new Event[0];
		}
		return aEvents;	
	}
	static Event[] fill(int minthreat, int maxthreat, int seriousmin,
			int seriousmax, int arv) {
		
		//asjade korduste vältimislistid
		for (int i = 0; i < 17; i++) {
			Library.threat.add(i);
		}
		
		for (int i = 0; i < 10; i++) {
			Library.sthreat.add(i);
		}
		
		for (int i = 0; i < 13; i++) {
			Library.inthreat.add(i);
		}
		
		for (int i = 0; i < 11; i++) {
			Library.sinthreat.add(i);
		}
		
		for (int i = 0; i < 61; i++) {
			Library.aeg[i] = i*10;
		} 
		
		Event[] aEvents = new Event[arv]; //list kuhu lähevad eventid
		
		int threatct = Random.randint(minthreat, maxthreat);
		int seriousct = Random.randint(seriousmin, seriousmax);
		
		aEvents[arv-10] = new MiscEvent(Library.getEvents()[18], 0);
		aEvents[arv-9] = new MiscEvent(Library.getEvents()[9], 240);
		aEvents[arv-8] = new MiscEvent(Library.getEvents()[12], 180);
		aEvents[arv-7] = new MiscEvent(Library.getEvents()[13], 220);
		aEvents[arv-6] = new MiscEvent(Library.getEvents()[10], 420);
		aEvents[arv-5] = new MiscEvent(Library.getEvents()[14], 360);
		aEvents[arv-4] = new MiscEvent(Library.getEvents()[15], 400);
		aEvents[arv-3] = new MiscEvent(Library.getEvents()[11], 600);
		aEvents[arv-2] = new MiscEvent(Library.getEvents()[16], 540);
		aEvents[arv-1] = new MiscEvent(Library.getEvents()[17], 580);
		Library.setAeg(24, -1); Library.setAeg(18, -1); Library.setAeg(22, -1);
		Library.setAeg(42, -1); Library.setAeg(36, -1); Library.setAeg(40, -1);
		Library.setAeg(60, -1); Library.setAeg(54, -1); Library.setAeg(58, -1);
		Library.setAeg(0, -1);
		//faasilõpud + missiooni algus
		
		aEvents = seriousThreats(seriousct, aEvents);
		aEvents = Threats(threatct, seriousct, aEvents);
		aEvents = miscEvents(arv-10-threatct, threatct, aEvents);
		
		aEvents = Event.sort(aEvents);
		
		aEvents = tPluss(threatct, aEvents);
		
		return aEvents;
	}
	static Event[] seriousThreats(int seriousct, Event[] aEvents) { //serious threatid
		int time;
		for (int i = 0; i < seriousct; i++) {
			switch (i%2) { //jaotan threatid esimese ja teise faasi vahel
				case 1: {
					time = Random.randAeg(25, 40);
					break;
				}
				default: {
					time = Random.randAeg(1, 21); 
					break;
				}
			}			
			aEvents[i] = new seriousThreat(time);
		}
		return aEvents;
	}
	static Event[] Threats(int threatct, int seriousct, Event[] aEvents) { //tavalised threatid
		int time;
		for (int i = 0; i < (threatct-seriousct); i++) {
			switch (i%2) { //faasidesse jaotus
				case 1: {
					time = Random.randAeg(25,40);
					break;
				}
				default: {
					time = Random.randAeg(1,25);
					break;
				}
			}
			aEvents[i+seriousct] = new Threat(time);
		}
		return aEvents;
	}
	static Event[] miscEvents(int miscct, int threatct, Event[] aEvents) { //misc events
		int time;
		for (int i = 0; i < miscct; i++) {
			int misctype = Random.randint(4, 6); //data transfer, incoming data ja comm down
			switch(i%3) { //faasidesse jaotus
				case 0: {
					time = Random.randAeg(25, 41);
					break;
				}
				case 1: {
					time = Random.randAeg(1, 22);
					break;
				}
				default: {
					time = Random.randAeg(43, 58);
					break;
				}
			}
			if (misctype == 6) {
				int duration = Random.randint(3, 10);
				aEvents[i+threatct] = new CommDown(time, duration);
			}
			else {
				aEvents[i+threatct] = new MiscEvent(time);
			}
		}
		return aEvents;
	}
	static Event[] tPluss(int threatct, Event[] aEvents) {
		int[] tPluss = new int[8];
		for (int i = 1; i <= 8; i++) {
			tPluss[i-1] = i;
		}
		for (int i = 0; i < 8-threatct; i++) {
			tPluss[Random.randint(0, 8-threatct)] = 0;
		}
		
		int muutuja = 0;
		for (Event i:aEvents) {
			if (muutuja == 8) break;
			while(true){
				if (muutuja == 8) break;
				if (tPluss[muutuja] != 0) {
					if (i.tPluss(tPluss, muutuja)) tPluss[muutuja] = 0;
					break;
					
				}
				muutuja++;
			}
		}
		return aEvents;
	}
	
}