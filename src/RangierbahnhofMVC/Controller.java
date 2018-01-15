package RangierbahnhofMVC;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * Die Klasse Controller steuert die Interaktion zwischen der BahnhofSimulation
 * und der BahnhifVisualisierung. Besteht aus Textfeldern, die die Gleise und
 * die sich darauf befindlichen Zuege bzw die wartenden Zuege darstellen.
 * 
 * @author katerina milenkovski und daniel biedermann
 *
 */
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

	/**
	 * Zeigt an ob die Simulaation bereits gestartet ist oder nicht.
	 */
	private boolean isStarted;

	/**
	 * Konstruktor.
	 */
	public Controller() {
		this.isStarted = false;
	}

	/**
	 * Main()-Methode des Controllers. Startet die Simulation.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(BahnhofVisualisierung.class, args);
	}

	/**
	 * Getter
	 * 
	 * @param index
	 * @return TextArea
	 */
	public TextArea getTextFeld(int index) {
		TextArea[] t = { textfeld0, textfeld1, textfeld2, textfeld3, textfeld4, textfeld5, textfeld6, textfeld7,
				textfeld8, textfeld9 };
		return t[index];
	}

	/**
	 * Getter
	 * 
	 * @param index
	 * @return TextArea
	 */
	public TextArea getWarteschlange(int index) {
		TextArea[] w = { warteschlange1, warteschlange2, warteschlange3, warteschlange4, warteschlange5, warteschlange6,
				warteschlange7, warteschlange8, warteschlange9, warteschlange10 };
		return w[index];
	}

	/**
	 * Start()-Methode beginnt die BahnhofSimulation.
	 */
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
