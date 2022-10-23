package simu.framework;
import eduni.distributions.*;

/**
 * Represent Arriving process class 
 * @author RYHMÄ 10
 * @version 1.8.0 Build 2022, October, 21
 *
 */
import simu.model.TapahtumanTyyppi;
public class Saapumisprosessi {
	
	
	/**
	 * instance variable
	 */
	private ContinuousGenerator generaattori;
	/**
	 * instance variable
	 */
	private Tapahtumalista tapahtumalista;
	/**
	 * instance variable
	 */
	private TapahtumanTyyppi tyyppi;
	
	/**
	 * Constructor method for Saapumisprosessi Class
	 * @param g
	 * @param tl
	 * @param tyyppi
	 */

	public Saapumisprosessi(ContinuousGenerator g, Tapahtumalista tl, TapahtumanTyyppi tyyppi){
		this.generaattori = g;
		this.tapahtumalista = tl;
		this.tyyppi = tyyppi;
	}

	public void generoiSeuraava(){
		Tapahtuma t = new Tapahtuma(tyyppi, Kello.getInstance().getAika()+generaattori.sample());
		tapahtumalista.lisaa(t);
	}
	
	public void paivitaJakauma(ContinuousGenerator g) {
		generaattori = g;
	}

}
