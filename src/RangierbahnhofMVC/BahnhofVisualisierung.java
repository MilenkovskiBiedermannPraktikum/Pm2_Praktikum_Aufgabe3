package RangierbahnhofMVC;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BahnhofVisualisierung extends Application implements Observer {

	private Controller c;

	public BahnhofVisualisierung(Controller controller) {
		this.c = controller;
	}

	public BahnhofVisualisierung() {
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent wurzel = FXMLLoader.load(getClass().getResource("layout.fxml"));
		primaryStage.setTitle("Bahnhofsimulation");
		primaryStage.setScene(new Scene(wurzel));
		primaryStage.show();
	}

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
