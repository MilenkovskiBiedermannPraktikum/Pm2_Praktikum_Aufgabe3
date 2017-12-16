package RangierbahnhofMVC;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class RangierBahnhof extends Observable {

	private int gleisAnzahl;
	private Zug[] zuege;
	private Map<Integer, Integer> warteschlange;

	public RangierBahnhof(int gleisAnzahl) {
		this.gleisAnzahl = gleisAnzahl;
		this.zuege = new Zug[gleisAnzahl];
		warteschlange = new HashMap<>();
		for (int i = 0; i < gleisAnzahl; i++) {
			warteschlange.put(i, 0);
		}
	}

	public Map<Integer, Integer> getWarteschlange() {
		return warteschlange;
	}

	public synchronized void einfahren(Zug zug, int gleisNummer) {
		//Zug ist noch als eingefahren auf der Liste, wird aber vor der Aktualisierung schon entnommen (von anderem Thread?) und ist dann "null"
		if (gleisNummer < zuege.length && gleisNummer >= 0) {
			//addiere einen Zug in der Warteschlange dazu
			warteschlange.replace(gleisNummer, warteschlange.get(gleisNummer) + 1);
			// solange Bahnhof voll ist, warten
			while (zuege[gleisNummer] != null) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// dann zug auf gleisnummer einfahren lassen
			zuege[gleisNummer] = zug;
			warteschlange.replace(gleisNummer, warteschlange.get(gleisNummer) - 1);
			System.err.println("Zug " + zug.getID() + " eingefügt auf Gleis " + gleisNummer);
			setChanged();
			notifyObservers();
			notifyAll();
		}
	}

	public synchronized Zug ausfahren(int gleisNummer) {
		Zug ausfahrender = null;
		if (gleisNummer < zuege.length && gleisNummer >= 0) {
			while (zuege[gleisNummer] == null) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			ausfahrender = zuege[gleisNummer];
			zuege[gleisNummer] = null;
			System.err.println("Zug " + ausfahrender.getID() + " entnommen von Gleis " + gleisNummer);
			setChanged();
			notifyObservers();
			notifyAll();
		}
		return ausfahrender;
	}

	public Zug erzeugeZug() {
		int id = (int) (Math.random() * 100 + 1);
		Zug neuerZug = new Zug(id);
		return neuerZug;
	}

	public int getGleisNummer() {
		int gleisNummer = (int) (Math.random() * gleisAnzahl);
		return gleisNummer;
	}

	public Zug[] getZuege() {
		return zuege;
	}

	public int getGleisAnzahl() {
		return gleisAnzahl;
	}

}
