package simu.model;

import java.util.Random;

import simu.framework.Kello;
import simu.framework.Trace;


// TODO:
// Asiakas koodataan simulointimallin edellyttämällä tavalla (data!)
public class Asiakas {
	private double saapumisaika;
	private double poistumisaika;
	private int id;
	private static int i = 1;
	private static long sum = 0;
	private int test1;
	private int lento;
	
	
	private boolean onkoEU = true; // onko lento EU:n sisällä vai ei
	
	private final Random r = new Random();
	
	public Asiakas(){
	    id = i++;
		saapumisaika = Kello.getInstance().getAika();
		
		setOnkoEU(r.nextBoolean());
		
		//Trace.out(Trace.Level.INFO, "Uusi asiakas nro " + id + " saapui klo "+saapumisaika);
		System.out.println("Uusi asiakas nro " + id + " saapui klo "+saapumisaika);
	}

	public double getPoistumisaika() {
		return poistumisaika;
	}

	public void setPoistumisaika(double poistumisaika) {
		this.poistumisaika = poistumisaika;
	}

	public double getSaapumisaika() {
		return saapumisaika;
	}

	public void setSaapumisaika(double saapumisaika) {
		this.saapumisaika = saapumisaika;
	}
	
	public int getId() {
		return id;
	}
	
	
	public void raportti(){
		//Trace.out(Trace.Level.INFO, "Asiakas "+id+ " saapui:" +saapumisaika);
		System.out.println("Asiakas "+id+ " saapui:" +saapumisaika);
		//Trace.out(Trace.Level.INFO,"Asiakas "+id+ " poistui:" +poistumisaika);
		System.out.println("Asiakas "+id+ " poistui:" +poistumisaika);
		//Trace.out(Trace.Level.INFO,"Asiakas "+id+ " viipyi:" +(poistumisaika-saapumisaika));
		System.out.println("Asiakas "+id+ " viipyi:" + (poistumisaika-saapumisaika));
		sum += (poistumisaika-saapumisaika);
		double keskiarvo = sum/id;
		System.out.println("Asiakkaiden läpimenoaikojen keskiarvo "+ keskiarvo);
	}

	public boolean isOnkoEU() {
		return onkoEU;
	}

	public void setOnkoEU(boolean onkoEU) {
		this.onkoEU = onkoEU;
	}
	


}
