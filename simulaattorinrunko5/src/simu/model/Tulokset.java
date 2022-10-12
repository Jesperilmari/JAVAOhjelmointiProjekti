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
			System.out.println("EU Lennot: Jos saavut kentälle klo: " + i + ", niin tule näin monta minuuttia etuajassa: " + saapumiset.get(i));
			System.out.println("Ei-EU Lennot: Jos saavut kentälle klo: " + i + ", niin tule näin monta minuuttia etuajassa: " + (saapumiset.get(i) + (saapumiset_eiEU.get(i) / Tulokset.getInstance().getPassitarkastuksienMaara())));
		}
	}
}
