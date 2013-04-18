import java.io.Serializable;


abstract public class Event implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3953537119971846155L;
	int time;
	String declare;
	
	Event(){
	}
	
	Event(int time){
		this.time = time;
	}
	
	abstract public boolean tPluss(int[] tPluss, int muutuja);
	
	public static Event[] sort(Event[] list){ // event listi sortimine aja järgi
		Event abi;
		
		for (int i=0; i<list.length; i++){
			for (int j=0; j<list.length-1; j++){
				
				if (list[j].time>list[j+1].time){
					abi=list[j+1];
					list[j+1]=list[j];
					list[j]=abi;
				}
			}
		}
		return list;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getDeclare() {
		return declare;
	}

	public void setDeclare(String declare) {
		this.declare = declare;
	}

	public abstract String toString();
}