package controller;

import view.MainGUI;
import javax.swing.text.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import simu.framework.IMoottori;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import simu.model.OmaMoottori;
import simu.model.Palvelupiste;
import simu.model.Tulokset;
import testi.Simulaattori;


/**
 * Represent SimuController Class extends Canvas which implements ISimuController and IControllerMtoV 
 * @author RYHMÄ 10
 * @version 1.8.0 Build 2022, October, 21
 *
 */
public class SimuController extends Canvas implements ISimuController, IControllerMtoV {
	
	//OmaMoottori omaMoottori = new OmaMoottori();
	
	
	
	
	private IMoottori moottori;
	
	Tulokset tulokset = Tulokset.getInstance();
	MainGUI view;
	
	private GraphicsContext turvaGc;
	private GraphicsContext passiGc;
	private GraphicsContext euGc;
	private GraphicsContext muuGc;
	private GraphicsContext jonoGc;
	
	private Label turvaJonoText;
	private Label passiJonoText;
	private Label euJonoText;
	private Label muuJonoText;
	/**
	 *Instance variable for SimuController 
	 */
	int turvaX = 33;
	int turvaY = 94;
	int passiX;
	int passiY;
	int euX;
	int euY;
	int muuX;
	int muuY;


	//Image image = new Image(passengerImgSrc);

	String passengerImgSrc = "\\view\\Images\\passengerFinal.png";
	String passengerBwImgSrc = "\\view\\Images\\passengerFinalBackwards.png";
	String lentoBgSrc = "\\view\\Images\\skyBackground.png";
	String turvaBgSrc = "\\view\\Images\\SecurityBG.png";
	String passportBgSrc = "\\view\\Images\\passportBG.png";

	String euBgSrc = "\\view\\Images\\euDepartureBG.png";
	String worldwideBgSrc = "\\view\\Images\\WorldwideBG.png";
	String textBgSrc = "\\view\\Images\\textBg.png";
	
	Image passengerImg = new Image(passengerImgSrc);
	Image passengerBackwardsImg = new Image(passengerBwImgSrc);
	Image lentoBgImg = new Image(lentoBgSrc);
	Image turvaBgImg = new Image(turvaBgSrc);
	Image passportBgImg = new Image(passportBgSrc);
	Image euBgImg = new Image(euBgSrc);
	Image muuBgImg = new Image(worldwideBgSrc);
	Image textBg = new Image(textBgSrc);
	
	//private ObservableList<Palvelupiste> ppData = FXCollections.observableArrayList();
	
	
	
	/**
	 * Default Constructor for SimuController
	 * @param mainGUI
	 * @param turvaGc
	 * @param passiGc
	 * @param euGc
	 * @param muuGc
	 * @param lentoGc
	 * @param turvaJonoText
	 * @param passiJonoText
	 * @param euJonoText
	 * @param muuJonoText
	 */
	public SimuController(MainGUI mainGUI, GraphicsContext turvaGc, GraphicsContext passiGc, GraphicsContext euGc, GraphicsContext muuGc, GraphicsContext lentoGc, Label turvaJonoText, Label passiJonoText, Label euJonoText, Label muuJonoText) {
		this.view = mainGUI;
		this.turvaGc = turvaGc;
		this.passiGc = passiGc;
		this.euGc = euGc;
		this.muuGc = muuGc;
		this.jonoGc = lentoGc;
		this.turvaJonoText = turvaJonoText;
		this.passiJonoText = passiJonoText;
		this.euJonoText = euJonoText;
		this.muuJonoText = euJonoText;
		
		jonoGc.setFont(Font.font("Arial" , FontWeight.BOLD ,24));
		jonoGc.setFill(Color.WHITE);
	}
	
	public SimuController() {
	}

	/*public Palvelupiste[] getPp(){
	}*/

	public void setMaarat(int kesto, int lentojenMaara, int turvaMaara, int passiMaara, int asiakkaat) {
		tulokset.setSimuloinnin_kokonaisaika(24000);
		tulokset.setFlightNum(lentojenMaara);
		tulokset.setTurvatarkastuksienMaara(turvaMaara);
		tulokset.setPassitarkastuksienMaara(passiMaara);
		tulokset.setnumOfCustomers(asiakkaat / turvaMaara);
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
		//setMaarat(7, 1, 10, 1);
		moottori.setSimulointiaika(tulokset.getSimuloinnin_kokonaisaika());    
		moottori.setDelay(100);             
		//ui.getVisualisointi().tyhjennaNaytto();	

		((Thread)moottori).start();
		
	}

	@Override
	public void nopeuta() {
		// TODO Auto-generated method stub
		moottori.setDelay((long)(moottori.getDelay()*0.9));
	}

	@Override
	public void hidasta() {
		// TODO Auto-generated method stub
		moottori.setDelay((long)(moottori.getDelay()*1.1));
	}

	@Override
	public void piirraTurva(int jononPituus) {
		turvaGc.clearRect(0, 0, 770, 200);
		turvaGc.drawImage(turvaBgImg, 0, 0, 770, 200);
		turvaX = 33;
		turvaY = 94;
		jonoGc.setFont(new Font("Arial", 24));
		jonoGc.drawImage(textBg, 500, 55);
		jonoGc.fillText(String.valueOf(jononPituus), 550, 75);
		for (int i = 0; i < jononPituus; i++) {
			turvaGc.drawImage(passengerImg, turvaX, turvaY, 32, 64);
			turvaX += 16; 
		}
	}

	@Override
	public void piirraPassi(int jononPituus) {
		// TODO Auto-generated method stub
		passiX = 33;
		passiY = 94;
		passiGc.clearRect(0, 0, 770, 200);
		jonoGc.drawImage(textBg, 500, 90);
		jonoGc.fillText(String.valueOf(jononPituus / Tulokset.getInstance().getPassitarkastuksienMaara()), 550, 110);
		//passiJonoText.setText(String.valueOf(jononPituus));
		passiGc.drawImage(passportBgImg, 0, 0, 770, 200);
		for (int i = 0; i < jononPituus / Tulokset.getInstance().getPassitarkastuksienMaara(); i++) {
			passiGc.drawImage(passengerImg, passiX, passiY, 32, 64);
			passiX += 16; 
		}
	}

	@Override
	public void piirraEu(int jononPituus) {
		euGc.clearRect(0, 0, 770, 200);
		euGc.drawImage(euBgImg, 0, 0, 770, 200);
		jonoGc.drawImage(textBg, 500, 120);
		jonoGc.fillText(String.valueOf(jononPituus), 550, 140);
		//euJonoText.setText(String.valueOf(jononPituus));
		euX = 33;
		euY = 94;
		for (int i = 0; i < jononPituus; i++) {
			euGc.drawImage(passengerImg, euX, euY, 32, 64);
			euX += 16; 
		}
		
		//x = x + 30;
		//gc.drawImage(image, x, y, 30, 30);
	}

	@Override
	public void piirraMuu(int jononPituus) {
		muuGc.clearRect(0, 0, 770, 200);
		muuGc.drawImage(muuBgImg, 0, 0, 770, 200);
		muuX = 33;
		muuY = 94;
		jonoGc.drawImage(textBg, 500, 150);
		jonoGc.fillText(String.valueOf(jononPituus), 550, 170);
		//muuJonoText.setText(String.valueOf(jononPituus));
		for (int i = 0; i < jononPituus; i++) {
			muuGc.drawImage(passengerImg, muuX, muuY, 32, 64);
			muuX += 16; 
		}
		
		//gc.drawImage(image, 10, 10, 10, 10);
		//gc.setFill(Color.RED);
		//gc.fillOval(10,10,10,10);

	}

	@Override
	public void piirraPituus() {
		
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
