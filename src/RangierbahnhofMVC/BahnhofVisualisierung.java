package RangierbahnhofMVC;
import java.io.File;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BahnhofVisualisierung extends Application implements Observer {

	private Controller c;
	
	public BahnhofVisualisierung(Controller controller) {
		this.c = controller;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
		Parent wurzel = FXMLLoader.load(getClass().getResource("layout.fxml"));
		primaryStage.setTitle("Bahnhofsimulation");
			primaryStage.setScene(new Scene(wurzel));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if( o instanceof RangierBahnhof){
			RangierBahnhof bahnhof = (RangierBahnhof) o ;
			for(int i = 0; i < bahnhof.getGleisAnzahl(); i++) {
			TextFeldAktualisierung textfeld = new TextFeldAktualisierung(c.getTextFeld(i), bahnhof, i);
		}
		
		}
	}

}
