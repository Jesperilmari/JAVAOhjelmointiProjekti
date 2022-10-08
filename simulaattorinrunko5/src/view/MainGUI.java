package view;

import java.util.ArrayList;

import controller.SimuController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//import simu.model.Palvelupiste;

public class MainGUI extends Application{

	private Parent root;

	int kesto;
	int lentojenMaara;
	int turvaMaara;
	int passiMaara;

	@FXML
	private ListView ppListView;
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


	//private ObservableList<Palvelupiste> ppData = FXCollections.observableArrayList();
	//ArrayList<Palvelupiste> pp = new ArrayList<Palvelupiste>();

	SimuController simuController = new SimuController(this);


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
		primaryStage.show();

		Alert alert = new Alert(AlertType.WARNING);

		ppListView = (ListView) scene.lookup("#ppListView");

		startBtn = (Button) scene.lookup("#startBtn");

		kestoTF = (TextField) scene.lookup("#kestoTF");

		lentojenMaaraTF = (TextField) scene.lookup("#lentojenMaaraTF");

		turvaMaaraTF = (TextField) scene.lookup("#turvaMaaraTF");

		passiMaaraTF = (TextField) scene.lookup("#passiMaaraTF");

		startBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					kesto = Integer.parseInt(kestoTF.getText());
					lentojenMaara = Integer.parseInt(lentojenMaaraTF.getText());
					turvaMaara = Integer.parseInt(turvaMaaraTF.getText());
					passiMaara = Integer.parseInt(passiMaaraTF.getText());
					
					simuController.setMaarat(kesto, lentojenMaara, turvaMaara, passiMaara);
					
					populateListView();
					
				} catch (NumberFormatException e ) {
					alert.showAndWait();
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	private void populateListView() {
		System.out.println(ppListView);
		String nimi = "";
		for(int i = 0; i < simuController.getPp().length; i++) {
			if (simuController.getPp()[i].id == 1) {
				nimi = "Turvatarkastus";
			} else if (simuController.getPp()[i].id == 2) {
				nimi = "Passitarkastus";
			} else if (simuController.getPp()[i].id == 3) {
				nimi = "Lähtöportti, EU:n sisäinen";
			}  else if (simuController.getPp()[i].id == 4) {
				nimi = "Lähtöportti, EU:n ulkopuolinen";
			}  else if (simuController.getPp()[i].id == 5) {
				nimi = "Viimeinen piste";
			}
			ppListView.getItems().add(nimi);
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}

