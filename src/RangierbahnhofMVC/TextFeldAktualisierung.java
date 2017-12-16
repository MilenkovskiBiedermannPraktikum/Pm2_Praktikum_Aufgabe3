package RangierbahnhofMVC;

import javafx.scene.control.*;
import javafx.application.Platform;
import javafx.concurrent.Task;

public class TextFeldAktualisierung extends Task<Boolean> {

	private final TextArea textFeld;
	private final RangierBahnhof bhf;
	private final int textFeldNummer;

	public TextFeldAktualisierung(TextArea textFeld, RangierBahnhof bhf, int textFeldNummer) {
		this.textFeld = textFeld;
		this.bhf = bhf;
		this.textFeldNummer = textFeldNummer;
	}

	@Override
	public Boolean call() throws Exception {
		updateTextFeld();
		return true;
	}

	public Boolean warteschlangenCall() throws Exception {
		updateWarteschlange();
		return true;
	}

	private void updateTextFeld() {
		if (bhf.getZuege()[textFeldNummer] == null) {
			Platform.runLater(() -> textFeld.setText("Gleis leer"));
		} else {
			System.err.println( bhf.getZuege()[textFeldNummer].getID());
			int id = bhf.getZuege()[textFeldNummer].getID();
			Platform.runLater(() -> textFeld.setText("Zug " + id + " eingefahren"));
		}
	}

	private void updateWarteschlange() {
		Platform.runLater(() -> textFeld.setText(bhf.getWarteschlange().get(textFeldNummer).toString()));
	}

}
