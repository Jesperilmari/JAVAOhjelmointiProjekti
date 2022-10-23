package simu.model;

import java.util.Random;

import simu.framework.Kello;
import simu.framework.Trace;


/** Represents Customers
 * 
 * 
 * @author RYHMÄ 10
 * @version 1.8.0 Build 2022, October, 18
 *
 */

public class Asiakas {
	/**
	 * The arriving time of this customer
	 */
	private double saapumisaika;
	/**
	 * The Departure of this customer
	 */
	private double poistumisaika;
	/**
	 * ID of this customer
	 */
	private int id;
	private static int i = 1;
	private static long sum = 0;

	
	
	private boolean onkoEU = true; // onko lento EU:n sisällä vai ei
	
	private final Random r = new Random();
	
	
	/**
	 * Default Constructor
	 * Constructor for class Asiakas 
	 * @param other <The purpose of this default constructor is to create instances of Asiakas Objects.n>
	 */
	
	public Asiakas(){
	    id = i++;
		saapumisaika = Kello.getInstance().getAika();
		setOnkoEU(r.nextBoolean());
		//Trace.out(Trace.Level.INFO, "Uusi asiakas nro " + id + " saapui klo "+saapumisaika);
		System.out.println("Uusi asiakas nro " + id + " saapui klo "+saapumisaika);
	}
	
	/**
	 * Gets the Departure time of this customer
	 * @return Departure time of this customer
	 */

	public double getPoistumisaika() {
		return poistumisaika;
	}
	
	/**
	 * Changes the time of the departure this customer
	 * 
	 * @param poistumisaika	This Customer's new departure Time.
	 * 						  	
	 * 
	 */

	public void setPoistumisaika(double poistumisaika) {
		this.poistumisaika = poistumisaika;
	}
	
	/**
	 * Gets the arriving time of this customer
	 * @return Arriving time of this customer
	 */

	public double getSaapumisaika() {
		return saapumisaika;
	}

	/**
	 * Changes the  time of the arriving this customer
	 * 
	 * @param poistumisaika	This Customer's new arriving Time.
	 * 						  	
	 * 
	 */

	public void setSaapumisaika(double saapumisaika) {
		this.saapumisaika = saapumisaika;
	}
	
	
	/**
	 * Gets the Customer's ID
	 * @return Customer's ID
	 */
	
	public int getId() {
		return id;
	}
	
	
	/**
	 * Gets the average time of this customer from the time when this customer arrives at airport till when this customer departure
	 */
	 void raportti(){
		System.out.println("Asiakas "+id+ " saapui:" +saapumisaika);
		//Trace.out(Trace.Level.INFO,"Asiakas "+id+ " poistui:" +poistumisaika);
		System.out.println("Asiakas "+id+ " poistui:" +poistumisaika);
		//Trace.out(Trace.Level.INFO,"Asiakas "+id+ " viipyi:" +(poistumisaika-saapumisaika));
		System.out.println("Asiakas "+id+ " viipyi:" + (poistumisaika-saapumisaika));
		sum += (poistumisaika-saapumisaika);
		double keskiarvo = sum/id;
		System.out.println("Asiakkaiden läpimenoaikojen keskiarvo "+ keskiarvo);
	}
	 
	 /**
	  * Tests if this customer is traveling outside of EU.
	  * @return True if this customer is traveling inside EU and false otherwise.
	  */

	public boolean isOnkoEU() {
		return onkoEU;
	}
	
	/**
	 * 
	 * @param onkoEU
	 */

	public void setOnkoEU(boolean onkoEU) {
		this.onkoEU = onkoEU;
	}
	


}
