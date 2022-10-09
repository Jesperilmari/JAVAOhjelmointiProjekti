package simu.model;

import java.util.LinkedList;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;
import simu.framework.Trace;

// TODO:
// Palvelupistekohtaiset toiminnallisuudet, laskutoimitukset (+ tarvittavat muuttujat) ja raportointi koodattava
public class Palvelupiste {

	private LinkedList<Asiakas> jono = new LinkedList<Asiakas>(); // Tietorakennetoteutus
	
	private ContinuousGenerator generator;
	private Tapahtumalista tapahtumalista;
	private TapahtumanTyyppi skeduloitavanTapahtumanTyyppi; 
	
	private double palveluaika;
	private int palvellut_asiakkaat;
	private double keskimaarainen_palveluaika;
	
	//JonoStartegia strategia; //optio: asiakkaiden järjestys
	
	private boolean varattu = false;


	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi){
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
				
	}


	public void lisaaJonoon(Asiakas a){   // Jonon 1. asiakas aina palvelussa
		jono.add(a);
		
	}

	public Asiakas otaJonosta(){  // Poistetaan palvelussa ollut
		varattu = false;
		return jono.poll(); //poll() hakee jonon ensimmäisessä alkiossa olevan asiakkkaan
	}

	public void aloitaPalvelu(){  //Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
		
		Trace.out(Trace.Level.INFO, "Aloitetaan uusi palvelu asiakkaalle " + jono.peek().getId()); //peek() hakee jonon ensimmäisessä alkiossa olevan asiakkaan
		
		varattu = true;
		double palveluaika = generator.sample();
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika()+palveluaika));
	}


	public boolean onVarattu(){
		return varattu;
	}


	public boolean onJonossa(){
		return jono.size() != 0;
	}
	
	public int jononPituus() {
		return jono.size();
	}


	public double getPalveluaika() {
		return palveluaika;
	}


	public void lisaaPalveluAikaa(double aika) {
		palveluaika += aika;
	}
	
	public void setPalveluaika(double palveluaika) {
		this.palveluaika = palveluaika;
	}


	public int getPalvellut_asiakkaat() {
		return palvellut_asiakkaat;
	}


	public void setPalvellut_asiakkaat(int palvellut_asiakkaat) {
		this.palvellut_asiakkaat = palvellut_asiakkaat;
	}
	
	public void lisaaPalveltuAsiakas() {
		palvellut_asiakkaat++;
	}


	public double getKeskimaarainen_palveluaika() {
		keskimaarainen_palveluaika = palveluaika / palvellut_asiakkaat;
		return keskimaarainen_palveluaika;
	}


	public void setKeskimaarainen_palveluaika(double keskimaarainen_palveluaika) {
		this.keskimaarainen_palveluaika = keskimaarainen_palveluaika;
	}

}
