package controller;

import view.MainGUI;
import javax.swing.text.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import simu.model.OmaMoottori;
import simu.model.Palvelupiste;
import simu.model.Tulokset;

public class SimuController {
	
	OmaMoottori omaMoottori = new OmaMoottori();
	Tulokset tulokset = Tulokset.getInstance();
	MainGUI view;
	
	private ObservableList<Palvelupiste> ppData = FXCollections.observableArrayList();
	
	
	public SimuController(MainGUI mainGUI){
		this.view = mainGUI;
	}
	
	public Palvelupiste[] getPp(){
		return omaMoottori.getPalvelupisteet();
	}
	
	public void setMaarat(int kesto, int lentojenMaara, int turvaMaara, int passiMaara) {
		tulokset.setSimuloinnin_kokonaisaika(kesto * 1440);
		tulokset.setFlightNum(lentojenMaara);
		tulokset.setTurvatarkastuksienMaara(turvaMaara);
		tulokset.setPassitarkastuksienMaara(passiMaara);
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
