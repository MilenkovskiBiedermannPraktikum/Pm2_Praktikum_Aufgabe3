package RangierbahnhofMVC;
import java.awt.TextArea;

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
		return null;
	}

	private void updateTextFeld() {
		if(bhf.getZuege()[textFeldNummer] != null) {
			Platform.runLater(()-> textFeld.setText("Zug " + bhf.getZuege()[textFeldNummer].getID()));
		} else {
			Platform.runLater(()-> textFeld.setText("Gleis leer"));
		}
	
	}

}
