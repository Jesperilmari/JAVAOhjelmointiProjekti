package controller;

import view.MainGUI;
import javax.swing.text.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import simu.model.OmaMoottori;
import simu.model.Palvelupiste;

public class SimuController {
	
	int kesto;
	int lentojenMaara;
	int turvaMaara;
	int passiMaara;
	
	OmaMoottori omaMoottori = new OmaMoottori();
	MainGUI view;
	private ObservableList<Palvelupiste> ppData = FXCollections.observableArrayList();
	
	public SimuController(MainGUI mainGUI){
		this.view = mainGUI;
	}
	
	public Palvelupiste[] getPp(){
		return omaMoottori.getPalvelupisteet();
	}
	
	public void setMaarat(int kesto, int lentojenMaara, int turvaMaara, int passiMaara) {
		this.kesto = kesto;
		this.lentojenMaara = lentojenMaara;
		this.turvaMaara = turvaMaara;
		this.passiMaara = passiMaara;
	}
	
	/*public void setPpData() {
		for(int i = 0; i < getPp().length; i++) {
			ppData.add(getPp()[i]);
		}
	}
	
	public ObservableList<Palvelupiste> getPpData(){
		setPpData();
		return ppData;
	}*/
}
