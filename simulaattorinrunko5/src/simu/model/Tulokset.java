package simu.model;

import java.util.ArrayList;

//Singleton tuloksille!

/**
 * Represents Singleton class of Result
 * @author RYHMÄ 10
 *@version 1.8.0 Build 2022, October, 18
 */

public class Tulokset {
	
	/**
	 * Instance variable for this class
	 */

	private static Tulokset INSTANCE = null;
	
	private double simuloinnin_kokonaisaika;
	private int palvellut_asiakkaat;
	private int palveluaika;
	private int keskimaarainen_palveluaika;
	private int turvaTarkastustenMaara;
	private int passiTarkastustenMaara;
	private int ei_eu_asiakkaat;
	private int eu_asiakkaat;
	
	private double simulationTime;
	private int flightNum;
	private int numOfCustomers;
	private int totalNumOfCusterms = 1000;
	
	ArrayList<Double> saapumiset = new ArrayList<Double>();
	ArrayList<Double> saapumiset_eiEU = new ArrayList<Double>();
	
	/**
	 * Don't let anyone else instantiate this class
	 * 
	 */
	
	private Tulokset() {
		numOfCustomers = totalNumOfCusterms * flightNum;
	
	}
	
	/**
	 * Gets the result
	 * @return
	 */
	
	public static Tulokset getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Tulokset();
		}
		return INSTANCE;
	}
	
	
	/**
	 * set the number of customers
	 * @param numOfCustomers
	 */
	
	public void setnumOfCustomers(int numOfCustomers) {
		this.numOfCustomers = numOfCustomers;
	}
	
	
	/**
	 * Getting the number of customers
	 * @return
	 */
	public int getNumOfCustomers() {
		return numOfCustomers;
	}
	
	/**
	 *Get Simulation run  time 
	 * @return simulation time
	 */

	public double getSimulationTime() {
		return simulationTime;
	}
	
	/**
	 * set the simulation time
	 * @param simulationTime
	 */

	public void setSimulationTime(double simulationTime) {
		this.simulationTime = simulationTime;
	}
	
	/**
	 * Gets the flight number
	 * @return
	 */

	public int getFlightNum() {
		return flightNum;
	}
	
	/**
	 * set the flight number
	 * @param flightNum
	 */

	public void setFlightNum(int flightNum) {
		this.flightNum = flightNum;
	}
	/**
	 * Gets the Simulation total time
	 * @return simulation total amount of time
	 */

	public double getSimuloinnin_kokonaisaika() {
		return simuloinnin_kokonaisaika;
	}
	


	public int gettotalNumOfCusterms() {
		return totalNumOfCusterms;
	}


	public void setSimuloinnin_kokonaisaika(double simuloinnin_kokonaisaika) {
		this.simuloinnin_kokonaisaika = simuloinnin_kokonaisaika;
	}



	public int getPalvellut_asiakkaat() {
		return palvellut_asiakkaat;
	}



	public void setPalvellut_asiakkaat(int palvellut_asiakkaat) {
		this.palvellut_asiakkaat = palvellut_asiakkaat;
	}

	
	
	/**
	 * gets the service time
	 * @return
	 */

	public int getPalveluaika() {
		return palveluaika;
	}
	
	/**
	 * sets the service time
	 * @param palveluaika
	 */



	public void setPalveluaika(int palveluaika) {
		this.palveluaika = palveluaika;
	}
	
	
	/**
	 * Average time of service time
	 * @return
	 */



	public int getKeskimaarainen_palveluaika() {
		return keskimaarainen_palveluaika;
	}

	/**
	 * 
	 * @param keskimaarainen_palveluaika
	 */
	
	
	
	public void setKeskimaarainen_palveluaika(int keskimaarainen_palveluaika) {
		this.keskimaarainen_palveluaika = keskimaarainen_palveluaika;
	}
	
	public void setTurvatarkastuksienMaara(int turvaTarkastustenMaara) {
		this.turvaTarkastustenMaara = turvaTarkastustenMaara;
	}
	
	public int getTurvatarkastuksienMaara() {
		return turvaTarkastustenMaara;
	}
	
	public void setPassitarkastuksienMaara(int passiTarkastustenMaara) {
		this.passiTarkastustenMaara = passiTarkastustenMaara;
	}
	
	public int getPassitarkastuksienMaara() {
		return passiTarkastustenMaara;
	}
	
	public void lisaaSaapumisAika(double aika) {
		saapumiset.add(aika);
	}
	
	public void lisaaEiEUAika(double aika) {
		saapumiset_eiEU.add(aika);
	}
	
	/**
	 * Gets the arriving time
	 */
	
	public void printSaapumisAjat() {
		for (int i = 0; i < saapumiset.size(); i++) {
			System.out.println("EU Lennot: Jos lentosi lähtee klo: " + i + ", niin varaa " + saapumiset.get(i) + " minuuttia jonottamiseen!");
			System.out.println("Ei-EU Lennot: Jos lentosi lähtee klo: " + i + ", niin varaa " + (saapumiset.get(i) + (saapumiset_eiEU.get(i) / Tulokset.getInstance().getPassitarkastuksienMaara())) + " minuuttia jonottamiseen!");
		}
	}
	
	public void lisaaEU(int i) {
		this.eu_asiakkaat = i;
	}
	
	/**
	 * adding the EU customers 
	 * @param i
	 */
	
	public void lisaaeiEU(int i) {
		this.ei_eu_asiakkaat = i;
	}
	
	/**
	 * Gets the customers inside EU
	 * @return EU Customers
	 */
	
	public int getEU() {
		return eu_asiakkaat;
	}
	/**
	 * 
	 * @return the Outside EU customers
	 */
	
	public int geteiEU() {
		return ei_eu_asiakkaat;
	}
	
	/**
	 * 
	 * @param x
	 * @return
	 */
	
	public double getSaapuminenX(int x) {
		return saapumiset.get(x);
	}
	
	/**
	 * This method gets the amount of customers which are not traveling inside EU 
	 * @param x
	 * @return arriving amount of customers
	 */
	
	public double getSaapuminenEiEU(int x) {
		return saapumiset_eiEU.get(x);
	}
	
	/**
	 * Gets the results of how much time the simulation run, security checkpoint service time, passport control service time, and departure point service 
	 * @return Simulation total amount of time
	 */
	
	public String getTulokset() {
		String tulokset = "\n" + "Tulokset: " + "\n" + "Simuloinnin kokonaisaika: " + Tulokset.getInstance().getSimuloinnin_kokonaisaika() + "\n" + "Jokainen turvatarkastuspiste palveli: " + Tulokset.getInstance().getPalvellut_asiakkaat() + " asiakasta.";
		tulokset += "\n" + "Jokainen passitarkastus palveli: " + Tulokset.getInstance().geteiEU() + " asiakasta.";
		for (int i = 0; i < saapumiset.size(); i++) {
			tulokset += "\n" + "EU Lennot: Jos lentosi lähtee klo: " + i + ", niin varaa " + saapumiset.get(i) + " minuuttia jonottamiseen!";
			tulokset += "Ei-EU Lennot: Jos lentosi lähtee klo: " + i + ", niin varaa " + (saapumiset.get(i) + (saapumiset_eiEU.get(i) / Tulokset.getInstance().getPassitarkastuksienMaara()) + " minuuttia jonottamiseen!");
		}
		return tulokset;
	}
}
