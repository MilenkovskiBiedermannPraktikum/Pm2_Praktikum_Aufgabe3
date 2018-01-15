package RangierbahnhofMVC;

import javafx.scene.control.*;
import javafx.application.Platform;
import javafx.concurrent.Task;

/**
 * Die Klasse TextFeldAktualisierung stellt alle Textfelder der Visualisierung
 * dar und updatet diese. Sie besteht aus dem jeweiligen Textfeld, dem Bahnhof,
 * der dargestellt wird und der Nummer des Textfeldes.
 * 
 * @author katerina milenkovski und daniel biedermann
 *
 */
public class TextFeldAktualisierung extends Task<Boolean> {
	/**
	 * Textfeld der Visualisierung
	 */
	private final TextArea textFeld;
	/**
	 * Referenz auf den dargestellten Bahnhof
	 */
	private final RangierBahnhof bhf;
	/**
	 * Nummer des Textfeldes, das aktualisiert wird.
	 */
	private final int textFeldNummer;

	/**
	 * Konstruktor
	 * 
	 * @param textFeld
	 * @param bhf
	 * @param textFeldNummer
	 */
	public TextFeldAktualisierung(TextArea textFeld, RangierBahnhof bhf, int textFeldNummer) {
		this.textFeld = textFeld;
		this.bhf = bhf;
		this.textFeldNummer = textFeldNummer;
	}
	/**
	 * call()-Methode; updatet jeweiliges Textfeld.
	 */
	@Override
	public Boolean call() throws Exception {
		updateTextFeld();
		return true;
	}
	/**
	 * call()-Methode der Warteschlange, updatet die Warteschlange eines
	 * Bahnhofs.
	 * 
	 * @return true
	 * @throws Exception
	 */
	public Boolean warteschlangenCall() throws Exception {
		updateWarteschlange();
		return true;
	}
	/**
	 * Update()-Methode eines TextFeldes. Je nachdem ob ein Gleis leer ist oder
	 * sich ein Zug darauf befindet, wird das jeweilige TextFeld aktualisiert.
	 */
	private void updateTextFeld() {
		if (bhf.getZuege()[textFeldNummer] == null) {
			Platform.runLater(() -> textFeld.setText("Gleis leer"));
		} else {
			System.err.println(bhf.getZuege()[textFeldNummer].getID());
			int id = bhf.getZuege()[textFeldNummer].getID();
			Platform.runLater(() -> textFeld.setText("Zug " + id + " eingefahren"));
		}
	}

	/**
	 * Warteschlange des Bahnhofs wird in der Visualisierung geupdatet.
	 */
	private void updateWarteschlange() {
		Platform.runLater(() -> textFeld.setText(bhf.getWarteschlange().get(textFeldNummer).toString()));
	}

}
