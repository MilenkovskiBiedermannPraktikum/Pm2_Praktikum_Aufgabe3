package RangierbahnhofMVC;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Die Klasse BahnhofsVisualisierung präsentiert die Daten des Modells, wird bei
 * Änderungen aufgerufen.
 * 
 * @author katerina milenkovski und daniel biedermann
 *
 */
public class BahnhofVisualisierung extends Application implements Observer {
	/**
	 * Referenz auf Controller der Darstellung
	 */
	private Controller c;

	/**
	 * Konstruktor
	 * 
	 * @param controller
	 */
	public BahnhofVisualisierung(Controller controller) {
		this.c = controller;
	}
	
	public BahnhofVisualisierung(){
	}

	/**
	 * Start()-Methode der BahnhofsVisualisierung. Stellt Einstiegspunkt der
	 * Anwendung und startet diese.
	 */
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent wurzel = FXMLLoader.load(getClass().getResource("layout.fxml"));
		primaryStage.setTitle("Bahnhofsimulation");
		primaryStage.setScene(new Scene(wurzel));
		primaryStage.show();
	}

	/**
	 * Update()-Methode der BahnhofsVisualisierung. Hier werden Änderungen des
	 * Bahnhofs aktualisiert und die Verbindung zum Modell hergestellt.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof RangierBahnhof) {
			RangierBahnhof bahnhof = (RangierBahnhof) o;
			for (int i = 0; i < bahnhof.getGleisAnzahl(); i++) {
				TextFeldAktualisierung textfeld = new TextFeldAktualisierung(c.getTextFeld(i), bahnhof, i);
				try {
					textfeld.call();
				} catch (Exception e) {
					e.printStackTrace();
				}
				TextFeldAktualisierung warteschlange = new TextFeldAktualisierung(c.getWarteschlange(i), bahnhof, i);
				try {
					warteschlange.warteschlangenCall();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

}
