package view;

import java.net.URL;
import java.util.ResourceBundle;

import controller.SimuController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
//import simu.model.Palvelupiste;

public class MainGUI extends Application{

	private Parent root;

	int kesto;
	int lentojenMaara;
	int turvaMaara;
	int passiMaara;


	/*@FXML
	private ListView ppListView;*/
	@FXML
	private Button startBtn;
	@FXML
	private TextField kestoTF;
	@FXML
	private TextField lentojenMaaraTF;
	@FXML
	private TextField turvaMaaraTF;
	@FXML
	private TextField passiMaaraTF;
	@FXML
	private Canvas mainCanvas;
	
	private GraphicsContext gc;
	
	//private ObservableList<Palvelupiste> ppData = FXCollections.observableArrayList();
	//ArrayList<Palvelupiste> pp = new ArrayList<Palvelupiste>();

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainGUI.class.getResource("MainView.fxml"));
			root = loader.load();
			//Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
		} catch(Exception e) {

			e.printStackTrace();
		}

		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Lentokenttäsimulaattori");
		primaryStage.show();

		Alert alert = new Alert(AlertType.WARNING);

		startBtn = (Button) scene.lookup("#startBtn");

		kestoTF = (TextField) scene.lookup("#kestoTF");

		lentojenMaaraTF = (TextField) scene.lookup("#lentojenMaaraTF");

		turvaMaaraTF = (TextField) scene.lookup("#turvaMaaraTF");

		passiMaaraTF = (TextField) scene.lookup("#passiMaaraTF");
		
		mainCanvas = (Canvas) scene.lookup("#mainCanvas");

		gc = mainCanvas.getGraphicsContext2D();
		
		SimuController simuController = new SimuController(this, gc);
		
		String turvaTarkastusImgSrc = "\\view\\Images\\Turva.png";
		
		Image turvaTarkastusImg = new Image(turvaTarkastusImgSrc);
		
		gc.drawImage(turvaTarkastusImg, 10, 10, 40, 40);
		
		try {
			simuController.piirraTurva();
			simuController.piirraTurva();
		} catch(Exception e) {
			e.printStackTrace();
		}
	
		
		startBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					kesto = Integer.parseInt(kestoTF.getText());
					lentojenMaara = Integer.parseInt(lentojenMaaraTF.getText());
					turvaMaara = Integer.parseInt(turvaMaaraTF.getText());
					passiMaara = Integer.parseInt(passiMaaraTF.getText());

					simuController.setMaarat(kesto, lentojenMaara, turvaMaara, passiMaara);
					simuController.kaynnistaSimulointi();

				} catch (NumberFormatException e ) {
					alert.setTitle("Error");
					alert.setHeaderText("Kenttiä ei voi jättää tyhjäksi ja niissä on käytettävä numeroita!");
					alert.setContentText("Paina ok jatkaaksesi");
					alert.showAndWait();
					e.printStackTrace();
				}
			}
		});
	}


	public static void main(String[] args) {
		launch(args);
	}

}

