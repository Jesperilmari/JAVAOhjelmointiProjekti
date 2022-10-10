package controller;

import view.MainGUI;
import javax.swing.text.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import simu.framework.IMoottori;
import simu.model.OmaMoottori;
import simu.model.Palvelupiste;
import simu.model.Tulokset;
import testi.Simulaattori;

public class SimuController implements ISimuController, IControllerMtoV {
	
	//OmaMoottori omaMoottori = new OmaMoottori();
	
	private IMoottori moottori;
	
	Tulokset tulokset = Tulokset.getInstance();
	MainGUI view;
	
	private ObservableList<Palvelupiste> ppData = FXCollections.observableArrayList();
	
	
	public SimuController(MainGUI mainGUI){
		this.view = mainGUI;
	}
	
	public SimuController() {
	}

	public Palvelupiste[] getPp(){
		return moottori.getPalvelupisteet();
	}
	
	public void setMaarat(int kesto, int lentojenMaara, int turvaMaara, int passiMaara) {
		tulokset.setSimuloinnin_kokonaisaika(kesto * 1440);
		tulokset.setFlightNum(lentojenMaara);
		tulokset.setTurvatarkastuksienMaara(turvaMaara);
		tulokset.setPassitarkastuksienMaara(passiMaara);
	}

	@Override
	public void naytaLoppuaika(double aika) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visualisoiAsiakas() {
		// TODO -generated method stub
	}

	@Override
	public void kaynnistaSimulointi() {
		moottori = new OmaMoottori(this);
		moottori.setSimulointiaika(24000);    
		//moottori.setDelay(100);             
		//ui.getVisualisointi().tyhjennaNaytto();	

		((Thread)moottori).start();
		
	}

	@Override
	public void nopeuta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hidasta() {
		// TODO Auto-generated method stub
	}

	@Override
	public void piirraTurva() {
		// TODO Auto-generated method stub
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
