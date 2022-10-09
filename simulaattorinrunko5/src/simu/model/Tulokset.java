package simu.model;


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
	private int numOfCustomers ;
	private int totalNumOfCusterms = 300;
	
	
	private Tulokset() {
		
		numOfCustomers = totalNumOfCusterms * flightNum;
	
	}
	
	public static Tulokset getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Tulokset();
		}
		return INSTANCE;
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
}
