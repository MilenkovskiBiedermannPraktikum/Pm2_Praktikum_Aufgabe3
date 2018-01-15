package RangierbahnhofMVC;

import javafx.application.Application;

/**
 * Simulation eines Rangierbahnhofs.
 * 
 * @author katerina milenkovski und daniel biedermann
 *
 */
public class BahnhofSimuliert implements Runnable {
	/**
	 * Referenz auf den zu simulieremdem Bahnhof.
	 */
	private RangierBahnhof bhf;

	/**
	 * Konstruktor.
	 * 
	 * @param gleisanzahl
	 */
	public BahnhofSimuliert(int gleisanzahl) {
		this.bhf = new RangierBahnhof(gleisanzahl);
	}

	/**
	 * Getter des Bahnhofs.
	 * 
	 * @return Bahnhof
	 */
	public RangierBahnhof getBhf() {
		return bhf;
	}

	/**
	 * Run-Methode, die alle 5 Sekunden einen Lokführer erstellt und sie ein-
	 * bzw. ausfahren lässt.
	 */
	@Override
	public void run() {
		while (true) {
			Lokfuehrer lokfuehrer = new Lokfuehrer(bhf);
			lokfuehrer.start();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.err.println(e.getStackTrace());
			}

		}
	}
	}

