package simu.model;

import java.util.LinkedList;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;
import simu.framework.Trace;


/**
 * Represent OmaMoottori class 
 * @author RYHMÄ 10
 * @version 1.8.0 Build 2022, October, 18
 *
 */

public class Palvelupiste {
	
	
	/**
	 * LinkedList for Asiakas
	 * 
	 */
	private LinkedList<Asiakas> jono = new LinkedList<Asiakas>(); // Tietorakennetoteutus
	
	/**
	 * Instance variables for Palvelupiste Class
	 */
	private ContinuousGenerator generator;
	private Tapahtumalista tapahtumalista;
	private TapahtumanTyyppi skeduloitavanTapahtumanTyyppi;
	public int id;
	
	private double palveluaika;
	private int palvellut_asiakkaat;
	private double keskimaarainen_palveluaika;
	
	
	
	private double ehtiAika;
	private double aloitusAika;
	private double loppuAika;
	
	//JonoStartegia strategia; //optio: asiakkaiden järjestys
	
	
	/**
	 * Gets the start Time Palvelupiste
	 * @return start time Palvelupiste
	 */
	public double getAloitusAika() {
		return aloitusAika;
	}
	/**
	 * Changes the start time Palvelupiste
	 * @param d this start time Palvelupiste
	 */
	public void setAloitusAika(double d) {
		this.aloitusAika = d;
	}
	/**
	 * Gets the end Time Palvelupiste
	 * @return end time 
	 */
	public double getLoppuAika() {
		return loppuAika;
	}
	
	/**
	 * Changes the end time of Palvelupiste
	 * @param d this start time Palvelupiste
	 */
	public void setLoppuAika(double loppuAika) {
		this.loppuAika = loppuAika;
	}


	private boolean varattu = false;

	
	
	/**
	 * parameterized constructor of PalveluPiste Class
	 * @param generator 
	 * @param tapahtumalista
	 * @param tyyppi
	 * @param id Customer service point id
	 */
	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi, int id){
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
		this.id = id;
				
	}
	
	/**
	 * This method adds customers to the line
	 * @param a 
	 */


	public void lisaaJonoon(Asiakas a){   // Jonon 1. asiakas aina palvelussa
		jono.add(a);
		
	}
	
	/**
	 * Removing customers from the service point
	 * @return fetches the first customer in the queue
	 */

	public Asiakas otaJonosta(){  // Poistetaan palvelussa ollut
		varattu = false;
		return jono.poll(); //poll() hakee jonon ensimmäisessä alkiossa olevan asiakkkaan
	}
	
	/**
	 * Starting the service and the customer in the queue during the service
	 */
	public void aloitaPalvelu(){  //Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
		
		//Trace.out(Trace.Level.INFO, "Aloitetaan uusi palvelu asiakkaalle " + jono.peek().getId()); //peek() hakee jonon ensimmäisessä alkiossa olevan asiakkaan
		System.out.println("Aloitetaan uusi palvelu asiakkaalle " + jono.peek().getId());
		
		varattu = true;
		double palveluaika = generator.sample();
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika()+palveluaika));
	}
	
	

	/**
	 * Tests if the service point is busy
	 * @return True if the customer service is busy and false otherwise.
	 */
	public boolean onVarattu(){
		return varattu;
	}
	
	
	public boolean onJonossa(){
		return jono.size() != 0;
	}
	
	/**
	 * Tests the queue size
	 * @return the queue size 
	 */
	public int jononPituus() {
		return jono.size();
	}
	/**
	 * Get the service time 
	 * @return the service time 
	 */
	public double getPalveluaika() {
		return palveluaika;
	}

	/**
	 * Total time that customers have been serviced in service point
	 * @param aika aika + aika = palveluaika
	 */
	public void lisaaPalveluAikaa(double aika) {
		palveluaika += aika;
	}
	
	
	/**
	 * Changes the time for this Customer service point  (Palvelupiste)
	 * @param palveluaika 
	 */
	public void setPalveluaika(double palveluaika) {
		this.palveluaika = palveluaika;
	}

	/**
	 * Get the amount of customers which have been serviced at service point
	 * @return amount of customers have been serviced in service points
	 */
	public int getPalvellut_asiakkaat() {
		return palvellut_asiakkaat;
	}
	
	/**
	 * 
	 * @param palvellut_asiakkaat sets the palvellut asiakkaat
	 */
	

	public void setPalvellut_asiakkaat(int palvellut_asiakkaat) {
		this.palvellut_asiakkaat = palvellut_asiakkaat;
	}
	
	/**
	 * in this method every time a customer is goes through service point it increments the amount of serviced customers
	 *
	 */
	public void lisaaPalveltuAsiakas() {
		palvellut_asiakkaat++;
	}
	/**
	 * Gets the average time of service time in customer service points
	 */
	public double getKeskimaarainen_palveluaika() {
		keskimaarainen_palveluaika = palveluaika / palvellut_asiakkaat;
		return keskimaarainen_palveluaika;
	}
	
	/**
	 * sets the average time
	 */

	public void setKeskimaarainen_palveluaika(double keskimaarainen_palveluaika) {
		this.keskimaarainen_palveluaika = keskimaarainen_palveluaika;
	}
	

	/**
	 * in this method we look what is a good time that customer should arrive at the airport
	 * @return the time in minutes which customer should arrive before flight
	 */
	public double ehtiAika () {
		 ehtiAika = (int) (this.keskimaarainen_palveluaika * this.jononPituus() / 16.6666666667);
		 return ehtiAika;
	}
	

}
