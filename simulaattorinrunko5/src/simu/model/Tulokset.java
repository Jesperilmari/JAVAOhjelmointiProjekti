package simu.model;


//Singleton tuloksille!
public class Tulokset {
	
	private static Tulokset INSTANCE = null;
	
	private double simuloinnin_kokonaisaika;
	private int palvellut_asiakkaat;
	private int palveluaika;
	private int keskimaarainen_palveluaika;
	
	
	
	public static Tulokset getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Tulokset();
		}
		return INSTANCE;
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
}
