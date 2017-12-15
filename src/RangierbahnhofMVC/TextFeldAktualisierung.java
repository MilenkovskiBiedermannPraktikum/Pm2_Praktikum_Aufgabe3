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
		return null;
	}
	public Boolean warteschlangenCall() throws Exception{
		updateWarteschlange();
		return null;
	}

	private void updateTextFeld() {
		if(bhf.getZuege()[textFeldNummer] != null) {
			Platform.runLater(()-> textFeld.setText("Zug " + bhf.getZuege()[textFeldNummer].getID() + " eingefahren"));
		} else {
			Platform.runLater(()-> textFeld.setText("Gleis leer"));
		}

	}
	
	private void updateWarteschlange() {
		Platform.runLater(()->textFeld.setText(bhf.getWarteschlange().get(textFeldNummer).toString()));	
		}
		
	}


