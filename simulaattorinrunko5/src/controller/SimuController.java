package controller;

import view.MainGUI;
import javax.swing.text.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import simu.framework.IMoottori;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import simu.model.OmaMoottori;
import simu.model.Palvelupiste;
import simu.model.Tulokset;
import testi.Simulaattori;

public class SimuController implements ISimuController, IControllerMtoV {
	
	//OmaMoottori omaMoottori = new OmaMoottori();
	
	private IMoottori moottori;
	
	Tulokset tulokset = Tulokset.getInstance();
	MainGUI view;
	GraphicsContext gc;
	int x = 15;
	int y = 20;

	String passengerImgSrc = "\\view\\Images\\passengerImg.png";

	Image image = new Image(passengerImgSrc);

	//private ObservableList<Palvelupiste> ppData = FXCollections.observableArrayList();

	public SimuController(MainGUI mainGUI, GraphicsContext gc){
		this.view = mainGUI;
		this.gc = gc;
	}
	
	public SimuController() {
	}

	/*public Palvelupiste[] getPp(){
	}*/

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

	public void piirraTurva1() {
		
		x = x + 30;
		gc.drawImage(image, x, y, 30, 30);
	}

	public void piirra() {
		
		gc.drawImage(image, 10, 10, 10, 10);
		gc.setFill(Color.RED);
		gc.fillOval(10,10,10,10);

	}

	public String test() {
		return "testi";

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
