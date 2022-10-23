package simu.framework;



/**
 * Represent Kello class 
 * @author RYHMÄ 10
 * @version 1.8.0 Build 2022, October, 21
 *
 */

public class Kello {
	
	/**
	 * Instance variable for Kello class
	 */
	private double aika;
	private static Kello instanssi;
	
	
	/**
	 * Constructor for Kello class
	 * sets the time to 0 which customer arrives at 0
	 */
	private Kello(){
		aika = 0;
	}
	
	
	/**
	 * 
	 * @return the instance
	 */
	public static Kello getInstance(){
		if (instanssi == null){
			instanssi = new Kello();	
		}
		return instanssi;
	}
	
	/**
	 * sets the time 
	 * @param aika
	 */
	
	public void setAika(double aika){
		this.aika = aika;
	}
	
	
	/**
	 * Gets the time 
	 * @return aika
	 */
	public double getAika(){
		return aika;
	}
}
