package RangierbahnhofMVC;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class RangierBahnhof extends Observable {

	private int gleisAnzahl;
	private Zug[] zuege = new Zug[gleisAnzahl];
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
		if (gleisNummer < zuege.length && gleisNummer >= 0) {
			warteschlange.replace(gleisNummer, warteschlange.get(gleisNummer) + 1);
			// solange Bahnhof voll ist, warten
			while (zuege[gleisNummer] != null) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
			// dann zug auf gleisnummer einfahren lassen
			zuege[gleisNummer] = zug;
			warteschlange.replace(gleisNummer, warteschlange.get(gleisNummer) - 1);
			setChanged();
			notifyObservers();
			System.out.println("Zug " + zug.getID() + " eingefügt auf Gleis " + gleisNummer);
			this.notifyAll();
		}
	}

	public synchronized Zug ausfahren(int gleisNummer) {
		Zug ausfahrender = null;
		if (gleisNummer < zuege.length && gleisNummer >= 0) {
			while (zuege[gleisNummer] == null) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
			ausfahrender = zuege[gleisNummer];
			zuege[gleisNummer] = null;
			setChanged();
			notifyObservers();
			System.out.println("Zug " + ausfahrender.getID() + " entnommen von Gleis " + gleisNummer);
			this.notifyAll();
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
