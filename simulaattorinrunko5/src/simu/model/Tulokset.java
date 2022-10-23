package simu.model;

import java.util.ArrayList;

//Singleton tuloksille!
public class Tulokset {
	
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
	
	
	
	private Tulokset() {
		
		numOfCustomers = totalNumOfCusterms * flightNum;
	
	}
	
	public static Tulokset getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Tulokset();
		}
		return INSTANCE;
	}
	
	
	public void setnumOfCustomers(int numOfCustomers) {
		this.numOfCustomers = numOfCustomers;
	}
	
	public int getNumOfCustomers() {
		return numOfCustomers;
	}

	public double getSimulationTime() {
		return simulationTime;
	}

	public void setSimulationTime(double simulationTime) {
		this.simulationTime = simulationTime;
	}

	public int getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(int flightNum) {
		this.flightNum = flightNum;
	}



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



	public int getPalveluaika() {
		return palveluaika;
	}



	public void setPalveluaika(int palveluaika) {
		this.palveluaika = palveluaika;
	}



	public int getKeskimaarainen_palveluaika() {
		return keskimaarainen_palveluaika;
	}



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
	
	public void printSaapumisAjat() {
		for (int i = 0; i < saapumiset.size(); i++) {
			System.out.println("EU Lennot: Jos lentosi lähtee klo: " + i + ", niin varaa " + saapumiset.get(i) + " minuuttia jonottamiseen!");
			System.out.println("Ei-EU Lennot: Jos lentosi lähtee klo: " + i + ", niin varaa " + (saapumiset.get(i) + (saapumiset_eiEU.get(i) / Tulokset.getInstance().getPassitarkastuksienMaara())) + " minuuttia jonottamiseen!");
		}
	}
	
	public void lisaaEU(int i) {
		this.eu_asiakkaat = i;
	}
	
	public void lisaaeiEU(int i) {
		this.ei_eu_asiakkaat = i;
	}
	
	public int getEU() {
		return eu_asiakkaat;
	}
	
	public int geteiEU() {
		return ei_eu_asiakkaat;
	}
	
	public double getSaapuminenX(int x) {
		return saapumiset.get(x);
	}
	
	public double getSaapuminenEiEU(int x) {
		return saapumiset_eiEU.get(x);
	}
	
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
