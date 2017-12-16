package RangierbahnhofMVC;

public class Lokführer extends Thread {
	private RangierBahnhof bhf;
	private String aufgabe;
	private Zug zug;

	public Lokführer(RangierBahnhof bhf) {
		this.bhf = bhf;
		if((Math.random() * 10) > 5){
			aufgabe = "einfahren";
		} else { 
			aufgabe = "ausfahren";
		}
	}
	
	@Override
	public void run() {
		int gleisNummer = bhf.getGleisNummer();
		if(aufgabe.equals("einfahren")){
			this.zug = bhf.erzeugeZug();
			bhf.einfahren(zug, gleisNummer);
		} else {
			bhf.ausfahren(gleisNummer);
		}

	}

}
