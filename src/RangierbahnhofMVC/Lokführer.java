package RangierbahnhofMVC;

/**
 * Die Klasse Lokführer repräsentiert einen Lokführer eines Bahnhofs, welcher
 * jeweils einem Bahnhof zugewiesen ist, an dem er arbeitet, einem Zug, den er
 * fährt und eine AUfgabe, die entscheidet was er mit dem Zug tun soll.
 * 
 * @author katerina milenkovski und daniel biedermann
 *
 */
public class Lokführer extends Thread {
	/**
	 * Referenz auf den Bahnhof, an dem der Lokführer arbeitet.
	 */
	private RangierBahnhof bhf;
	/**
	 * Aufgabe des Lokführers, zufällig entweder "einfahren" oder "ausfahren".
	 */
	private String aufgabe;
	/**
	 * Referenz auf den Zug, den der Lokführer fährt.
	 */
	private Zug zug;

	/**
	 * Konstruktor, der zufällig die Aufgabe des Lokführers generiert.
	 * 
	 * @param bhf
	 */
	public Lokführer(RangierBahnhof bhf) {
		this.bhf = bhf;
		if ((Math.random() * 10) > 5) {
			aufgabe = "einfahren";
		} else {
			aufgabe = "ausfahren";
		}
	}

	/**
	 * Run()-Methode der Klasse Lokführer sorgt dafür, dass die Aufgabe des
	 * jeweiligen Lokführers ausgeführt wird.
	 */
	@Override
	public void run() {
		int gleisNummer = bhf.getGleisNummer();
		if (aufgabe.equals("einfahren")) {
			this.zug = bhf.erzeugeZug();
			bhf.einfahren(zug, gleisNummer);
		} else {
			bhf.ausfahren(gleisNummer);
		}

	}

}
