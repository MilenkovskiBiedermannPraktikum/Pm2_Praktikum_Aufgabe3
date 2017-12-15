package RangierbahnhofMVC;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Controller {

	public TextArea textfeld0;
	public TextArea textfeld1;
	public TextArea textfeld2;
	public TextArea textfeld3;
	public TextArea textfeld4;
	public TextArea textfeld5;
	public TextArea textfeld6;
	public TextArea textfeld7;
	public TextArea textfeld8;
	public TextArea textfeld9;

	public TextArea warteschlange1;
	public TextArea warteschlange2;
	public TextArea warteschlange3;
	public TextArea warteschlange4;
	public TextArea warteschlange5;
	public TextArea warteschlange6;
	public TextArea warteschlange7;
	public TextArea warteschlange8;
	public TextArea warteschlange9;
	public TextArea warteschlange10;

	// läuft Simulation schon?
	private boolean isStarted;

	public Controller() {
		this.isStarted = false;
	}

	public static void main(String[] args) {
		Application.launch(BahnhofVisualisierung.class, args);
	}

	public TextArea getTextFeld(int index) {
		TextArea[] t = { textfeld0, textfeld1, textfeld2, textfeld3, textfeld4, textfeld5, textfeld6, textfeld7,
				textfeld8, textfeld9 };
		return t[index];
	}

	public TextArea getWarteschlange(int index) {
		TextArea[] w = { warteschlange1, warteschlange2, warteschlange3, warteschlange4, warteschlange5,
				warteschlange6, warteschlange7, warteschlange8, warteschlange9, warteschlange10 };
		return w[index];
	}

	@FXML
	protected void start() {
		if (!isStarted) {
			BahnhofSimuliert bhfS = new BahnhofSimuliert(10);
			bhfS.getBhf().addObserver(new BahnhofVisualisierung(this));
			Thread bhfSThread = new Thread(bhfS);
			isStarted = true;
			bhfSThread.start();

		}
	}

}
