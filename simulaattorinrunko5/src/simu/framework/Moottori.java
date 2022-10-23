package simu.framework;

import controller.IControllerMtoV;

import controller.ISimuController;
import simu.model.Palvelupiste;


/**
 * Represent Moottori class which extends Thread and implements the IMoottori interface
 * @author RYHMÄ 10
 * @version 1.8.0 Build 2022, October, 18
 *
 */


public abstract class Moottori extends Thread implements IMoottori {
	/**
	 * Instance variable for Database Connection
	 */
	private double simulointiaika = 0;

	private Kello kello;
	
	/**
	 * Instance variable for Database Connection
	 */
	private long viive = 200;
	protected Tapahtumalista tapahtumalista;
	protected Palvelupiste[] palvelupisteet;
	
	protected IControllerMtoV kontrolleri;
	
	/**
	 * Constructor for Moottori Class 
	 * @param kontrolleri
	 * Get the Kello (Clock) as a variable to simplify the code
	 * 
	 */

	public Moottori(IControllerMtoV kontrolleri){

		this.kontrolleri = kontrolleri;
		kello = Kello.getInstance(); // Otetaan kello muuttujaan yksinkertaistamaan koodia
		
		tapahtumalista = new Tapahtumalista();
		
		// Palvelupisteet luodaan simu.model-pakkauksessa Moottorin aliluokassa 
		
		
	}
	
	/**
	 * this sets the simulation time
	 */

	public void setSimulointiaika(double aika) {
		simulointiaika = aika;
	}
	
	/**
	 * This method runs the simulation, gets the clock and sets delay
	 * creates the events (A,B,C) 
	 */
	public void run(){
		alustukset(); // luodaan mm. ensimmäinen tapahtuma
		while (simuloidaan()){
			viive();
			//Trace.out(Trace.Level.INFO, "\nA-vaihe: kello on " + nykyaika());
			System.out.println("\nA-vaihe: kello on " + nykyaika());
			kello.setAika(nykyaika());
			
			//Trace.out(Trace.Level.INFO, "\nB-vaihe:" );
			System.out.println("\nB-vaihe:");
			suoritaBTapahtumat();
			
			//Trace.out(Trace.Level.INFO, "\nC-vaihe:" );
			System.out.println("\nC-vaihe:");
			yritaCTapahtumat();

		}
		tulokset();
		
	}
	
	/**
	 * this method run the B event
	 */
	
	private void suoritaBTapahtumat(){
		while (tapahtumalista.getSeuraavanAika() == kello.getAika()){
			suoritaTapahtuma(tapahtumalista.poista());
		}
	}
	
	
	/**
	 * this method runs the C event if the customer service point (PalveluPiste) is not busy
	 */
	private void yritaCTapahtumat(){
		for (Palvelupiste p: palvelupisteet){
			if (!p.onVarattu() && p.onJonossa()){
				p.aloitaPalvelu();
			}
		}
	}

	/**
	 * 
	 * @return the present time during the simulation
	 */
	
	private double nykyaika(){
		return tapahtumalista.getSeuraavanAika();
	}
	
	
	/**
	 * 
	 * @return True till the simulation time is greater than Clock time (0)
	 */
	private boolean simuloidaan(){
		return kello.getAika() < simulointiaika;
	}
	
	
	/**
	 * Delay method
	 * @InterruptedException e if the delay is not set successfully
	 */
	private void viive() { // UUSI
		//Trace.out(Trace.Level.INFO, "Viive " + viive);
		System.out.println("Viive " + viive);
		try {
			sleep(viive);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Sets delay
	 *	
	 */
	@Override
	public void setDelay(long viive) {
		// TODO Auto-generated method stub
		this.viive = viive;	
	}

	/**
	 * get the delay
	 */
	@Override
	public long getDelay() {
		// TODO Auto-generated method stub
		return viive;
	}	
	
	
	protected abstract void alustukset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
	protected abstract void suoritaTapahtuma(Tapahtuma t);  // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
	protected abstract void tulokset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
}