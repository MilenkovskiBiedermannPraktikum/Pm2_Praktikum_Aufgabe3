package RangierbahnhofMVC;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Controller extends Application {
	
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
	public TextArea textfeld10;
	
	
	//läuft Simulation schon?
	private boolean isStarted;
	
	public Controller(){
		this.isStarted = false;
	}
	
	public TextArea getTextFeld(int index) {
		TextArea[] t = {textfeld0,textfeld1,textfeld2,textfeld3,textfeld4,textfeld5,textfeld6,textfeld7,textfeld8,textfeld9,textfeld10};
		return t[index];
	}
	
	@FXML
	protected void start() {
		if(!isStarted) {
			BahnhofSimuliert bhfS = new BahnhofSimuliert(11);
			bhfS.getBhf().addObserver(new BahnhofVisualisierung(this));
			Thread bhfSThread = new Thread(bhfS);
			isStarted = true;
			bhfSThread.start();
		}
	}
	
	public static void main (String[] args) {
		Application.launch();	
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
	

}
