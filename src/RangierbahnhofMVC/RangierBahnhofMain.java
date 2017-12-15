package RangierbahnhofMVC;


public class RangierBahnhofMain {

	public static void main (String[] args) {
		Thread bhf = new Thread(new BahnhofSimuliert());
		bhf.start();
	}
}


