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
import javafx.scene.control.Label;
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
	private Button hidastaBtn;
	@FXML
	private Button nopeutaBtn;
	@FXML
	private TextField kestoTF;
	@FXML
	private TextField lentojenMaaraTF;
	@FXML
	private TextField turvaMaaraTF;
	@FXML
	private TextField passiMaaraTF;
	@FXML
	private Canvas turvaCanvas;
	private Canvas passiCanvas;
	private Canvas euCanvas;
	private Canvas muuCanvas;
	private Canvas lentoCanvas;
	
	private GraphicsContext turvaGc;
	private GraphicsContext passiGc;
	private GraphicsContext euGc;
	private GraphicsContext muuGc;
	private GraphicsContext lentoGc;
	
	@FXML
	private Label turvaJonoText;
	@FXML
	private Label passiJonoText;
	@FXML
	private Label euJonoText;
	@FXML
	private Label muuJonoText;
	
	String passengerImgSrc = "\\view\\Images\\passengerFinal.png";
	String passengerBwImgSrc = "\\view\\Images\\passengerFinalBackwards.png";
	String lentoBgSrc = "\\view\\Images\\skyBackground.png";
	String turvaBgSrc = "\\view\\Images\\SecurityBG.png";
	String passportBgSrc = "\\view\\Images\\passportBG.png";
	String euBgSrc = "\\view\\Images\\euDepartureBG.png";
	String worldwideBgSrc = "\\view\\Images\\WorldwideBG.png";
	String jonoBgSrc = "\\view\\Images\\JononPituus.png";
	
	Image passengerImg = new Image(passengerImgSrc);
	Image passengerBackwardsImg = new Image(passengerBwImgSrc);
	Image lentoBgImg = new Image(lentoBgSrc);
	Image turvaBgImg = new Image(turvaBgSrc);
	Image passportBgImg = new Image(passportBgSrc);
	Image euBgImg = new Image(euBgSrc);
	Image worldwideBgImg = new Image(worldwideBgSrc);
	Image jono = new Image(jonoBgSrc);
	
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
		hidastaBtn = (Button) scene.lookup("#hidastaBtn");
		nopeutaBtn = (Button) scene.lookup("#nopeutaBtn");

		kestoTF = (TextField) scene.lookup("#kestoTF");
		lentojenMaaraTF = (TextField) scene.lookup("#lentojenMaaraTF");
		turvaMaaraTF = (TextField) scene.lookup("#turvaMaaraTF");
		passiMaaraTF = (TextField) scene.lookup("#passiMaaraTF");
		
		turvaCanvas = (Canvas) scene.lookup("#turvaCanvas");
		passiCanvas = (Canvas) scene.lookup("#passiCanvas");
		euCanvas = (Canvas) scene.lookup("#euCanvas");
		muuCanvas = (Canvas) scene.lookup("#muuCanvas");
		lentoCanvas = (Canvas) scene.lookup("#lentoCanvas");
		
		turvaJonoText = (Label) scene.lookup("#turvaJonoText");
		passiJonoText = (Label) scene.lookup("#passiJonoText");
		euJonoText = (Label) scene.lookup("#euJonoText");
		muuJonoText = (Label) scene.lookup("#muuJonoText");
		
		turvaJonoText.setText("");
		passiJonoText.setText("");
		euJonoText.setText("");
		muuJonoText.setText("");
		
		turvaGc = turvaCanvas.getGraphicsContext2D();
		passiGc = passiCanvas.getGraphicsContext2D();
		euGc = euCanvas.getGraphicsContext2D();
		muuGc = muuCanvas.getGraphicsContext2D();
		lentoGc = lentoCanvas.getGraphicsContext2D();
		
		turvaGc.drawImage(turvaBgImg, 0, 0, 770, 200);
		lentoGc.drawImage(jono, 0, 0, 770, 200);
		passiGc.drawImage(passportBgImg, 0, 0, 770, 200);
		euGc.drawImage(euBgImg, 0, 0, 770, 200);
		muuGc.drawImage(worldwideBgImg, 0, 0, 770, 200);
		
		SimuController simuController = new SimuController(this, turvaGc, passiGc, euGc, muuGc, lentoGc, turvaJonoText, passiJonoText, euJonoText, muuJonoText);
	
		
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
		
		hidastaBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					simuController.hidasta();
				} catch (NumberFormatException e ) {
					e.printStackTrace();
				}
			}
		});
		
		nopeutaBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					simuController.nopeuta();
				} catch (NumberFormatException e ) {
					e.printStackTrace();
				}
			}
		});
		
		
		//hidastaBtn.setOnAction(e -> simuController.hidasta());
        //nopeutaBtn.setOnAction(e -> simuController.nopeuta());
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}

