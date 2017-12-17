package RangierbahnhofMVC;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Die Klasse RangierBahnhof repräsentiert einen Bahnhof. Jeder Bahnhof besteht
 * aus einer bestimmten Anzahl an Gleisen, eingefahrenen Zuegen und Zuegen, die
 * auf ein freies Gleis warten.
 * 
 * @author katerina milenkovski und daniel biedermann
 *
 */
public class RangierBahnhof extends Observable {
	/**
	 * Anzahl der vorhandenen Gleise eines Bahnhofs.
	 */
	private int gleisAnzahl;
	/**
	 * Alle Zuege, die sich auf den Gleisen eines Bahnhofs befinden.
	 */
	private Zug[] zuege;
	/**
	 * Warteschlange des Bahnhofs, d.h. wie viele Zuege warten an jeweiligem
	 * Gleis aus die Einfahrt.
	 */
	private Map<Integer, Integer> warteschlange;

	/**
	 * Konstruktor.
	 * 
	 * @param gleisAnzahl
	 */
	public RangierBahnhof(int gleisAnzahl) {
		this.gleisAnzahl = gleisAnzahl;
		this.zuege = new Zug[gleisAnzahl];
		warteschlange = new HashMap<>();
		for (int i = 0; i < gleisAnzahl; i++) {
			warteschlange.put(i, 0);
		}
	}

	/**
	 * Getter der Warteschlange.
	 * 
	 * @return warteschlange
	 */
	public Map<Integer, Integer> getWarteschlange() {
		return warteschlange;
	}

	/**
	 * Einfahren-Methode eines jeweiligen Zuges. Gleisnummer muss im Bahnhof
	 * enthalten und >=0 sein, sonst wird Zug nicht eingefahren. Außerdem wird
	 * die Warteschlange aktualisiert.
	 * 
	 * @param zug
	 * @param gleisNummer
	 */
	public synchronized void einfahren(Zug zug, int gleisNummer) {
		if (gleisNummer < zuege.length && gleisNummer >= 0) {
			// addiere einen Zug in der Warteschlange dazu
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

	/**
	 * Ausfahren Methode eines Zuges. Gkeisnummer muss im Bahnhof enthalten und
	 * >=0 sein, sonst kann Zug nicht ausgefahren werden.
	 * 
	 * @param gleisNummer
	 * @return ausgefahrener Zug
	 */
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

	/**
	 * Methode zum Erzeugen eines Zuges. Hier wird jedem Zug eine zufällige ID
	 * gegeben.
	 * 
	 * @return Zug
	 */
	public Zug erzeugeZug() {
		int id = (int) (Math.random() * 100 + 1);
		Zug neuerZug = new Zug(id);
		return neuerZug;
	}

	/**
	 * Getter für Gleisnummer.
	 * 
	 * @return gleisnummer
	 */
	public int getGleisNummer() {
		int gleisNummer = (int) (Math.random() * gleisAnzahl);
		return gleisNummer;
	}

	/**
	 * Getter für die Zuege auf allen Gleisen.
	 * 
	 * @return zuege
	 */
	public Zug[] getZuege() {
		return zuege;
	}

	/**
	 * Getzter der Gleisanzahl
	 * 
	 * @return gleisanzahl
	 */
	public int getGleisAnzahl() {
		return gleisAnzahl;
	}

}
