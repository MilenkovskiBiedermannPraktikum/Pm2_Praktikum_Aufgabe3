package RangierbahnhofMVC;
	import java.util.Observable;
	
public class RangierBahnhof extends Observable {

		private int gleisAnzahl;
		private Zug[] zuege = new Zug[gleisAnzahl];
		
		public RangierBahnhof(int gleisAnzahl) {
			this.gleisAnzahl = gleisAnzahl;
			this.zuege = new Zug[gleisAnzahl];
		}

		public synchronized void einfahren(Zug zug, int gleisNummer) {
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
			setChanged();
			notifyObservers();
			System.out.println("Zug " + zug.getID() + " eingefügt auf Gleis " + gleisNummer);
			this.notifyAll();
		}

		public synchronized Zug ausfahren(int gleisNummer) {
			while (zuege[gleisNummer] == null) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
				Zug ausfahrender = zuege[gleisNummer];
				zuege[gleisNummer] = null;
				setChanged();
				notifyObservers();
				System.out.println("Zug " + ausfahrender.getID() + " entnommen von Gleis " + gleisNummer);
				this.notifyAll();
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
		
		
	}


