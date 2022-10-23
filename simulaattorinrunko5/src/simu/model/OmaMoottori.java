package simu.model;

import java.util.ArrayList;
import java.util.Random;
import controller.IControllerMtoV;
import controller.ISimuController;
import controller.SimuController;
import java.util.Random;

import controller.SimuController;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import eduni.distributions.Uniform;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;
import controller.SimuController;


public class OmaMoottori extends Moottori{
	
	private Saapumisprosessi saapumisprosessi;
	
	public static int lennot = 20;
	private int palvellut;
	
	private int eu_asiakkaat;
	private int ei_eu_asiakkaat;
	
	double temp1 = 0;
	double temp2 = 0;
	double temp3 = 0;
	
	private int testt = 0;

	private boolean joku = false;

	public OmaMoottori(IControllerMtoV kontrolleri){
		super(kontrolleri);
		palvelupisteet = new Palvelupiste[6];
		
		//for(int i = 0; i < palvelupisteet.length; i++) {
			
		//}
		
		//
		palvelupisteet[0]=new Palvelupiste(new Normal(1*16.66, 1), tapahtumalista, TapahtumanTyyppi.turvaIn, 1);
		
		
		// 3min ~
		palvelupisteet[1]=new Palvelupiste(new Normal(1*16.66, 1), tapahtumalista, TapahtumanTyyppi.lahtoporttiInEU, 2);
		
		// 3min ~
		palvelupisteet[2]=new Palvelupiste(new Normal(1*16.66, 1), tapahtumalista, TapahtumanTyyppi.lahtoporttiInMuu, 3);
		
		//
		palvelupisteet[3]=new Palvelupiste(new Normal(3*16.66, 1), tapahtumalista, TapahtumanTyyppi.passiIn, 4);
		
		//
		palvelupisteet[4]=new Palvelupiste(new Normal(1, 1), tapahtumalista, TapahtumanTyyppi.lahtoporttiOut, 5);
		
		palvelupisteet[5]=new Palvelupiste(new Normal(1, 1), tapahtumalista, TapahtumanTyyppi.lahtoporttiOut_eiEU, 6);
		
		
		saapumisprosessi = new Saapumisprosessi(new Normal(2.146, 1), tapahtumalista, TapahtumanTyyppi.luoLennot);
		

	}

	
	@Override
	protected void alustukset() {
		Kello.getInstance().setAika(0);
		
		
		//Tulokset.getInstance().setnumOfCustomers(1600);
		//Tulokset.getInstance().setPassitarkastuksienMaara(3);
		saapumisprosessi.paivitaJakauma(new Normal((30000 / Tulokset.getInstance().getNumOfCustomers()), 1));
		saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
	}
	
	@Override
	protected void suoritaTapahtuma(Tapahtuma t){  // B-vaiheen tapahtumat
		Asiakas a;
		
		//Jonon pituus eri tunneilla
		haeAjat();
		
		//Päivitetään jakauma hitaaksi/nopeammaksi !
		
		
		
		switch (t.getTyyppi()) {
		
			
		case luoLennot:
		
			System.out.println(Tulokset.getInstance().getNumOfCustomers());
			if (Tulokset.getInstance().getNumOfCustomers() > testt) {
				kontrolleri.piirraTurva(palvelupisteet[0].jononPituus());
				palvelupisteet[0].lisaaJonoon(new Asiakas());
				saapumisprosessi.generoiSeuraava();	
				
				palvelupisteet[0].setAloitusAika(Kello.getInstance().getAika());
				testt++;
			}
			//saapumisprosessi.generoiSeuraava();	
			
			
			break;
			
		case turvaIn:
			// Katsotaan, onko asiakas lentämässä EU vai ei EU alueelle
			// Katsotaan, missä turvatarkastuksessa on lyhyin jono
			
			
			
			a = palvelupisteet[0].otaJonosta();
			kontrolleri.piirraTurva(palvelupisteet[0].jononPituus());
			palvelupisteet[0].lisaaPalveltuAsiakas();
			palvelupisteet[0].setLoppuAika(Kello.getInstance().getAika());
			
			palvelupisteet[0].lisaaPalveluAikaa(palvelupisteet[0].getLoppuAika() - palvelupisteet[0].getAloitusAika());
			if (a.isOnkoEU() == true) {
				palvelupisteet[1].lisaaJonoon(a);
				kontrolleri.piirraEu(palvelupisteet[1].jononPituus());
			} else if (a.isOnkoEU() == false) {
				palvelupisteet[2].lisaaJonoon(a);
				kontrolleri.piirraMuu(palvelupisteet[2].jononPituus());

			}
			
			break;
			
		case lahtoporttiInEU:
			a = palvelupisteet[1].otaJonosta();
			kontrolleri.piirraEu(palvelupisteet[1].jononPituus());
			palvelupisteet[4].lisaaJonoon(a);
			
			break;
			
		case lahtoporttiInMuu:
			a = palvelupisteet[2].otaJonosta();
			kontrolleri.piirraEu(palvelupisteet[1].jononPituus());
			palvelupisteet[3].lisaaJonoon(a);
			kontrolleri.piirraPassi(palvelupisteet[3].jononPituus());
			palvelupisteet[3].setAloitusAika(Kello.getInstance().getAika());
			
			break;
			
		case passiIn:
			a = palvelupisteet[3].otaJonosta();
			kontrolleri.piirraPassi(palvelupisteet[3].jononPituus());
			palvelupisteet[3].lisaaPalveltuAsiakas();
			palvelupisteet[3].setLoppuAika(Kello.getInstance().getAika());
			palvelupisteet[3].lisaaPalveluAikaa((palvelupisteet[3].getLoppuAika() - palvelupisteet[3].getAloitusAika()) / Tulokset.getInstance().getPassitarkastuksienMaara());
			
			palvelupisteet[5].lisaaJonoon(a);

			break;
			
		case lahtoporttiOut:
			
			a = palvelupisteet[4].otaJonosta();
			a.setPoistumisaika(Kello.getInstance().getAika());
			eu_asiakkaat++;
			palvellut++;
			a.raportti();
			break;

		case lahtoporttiOut_eiEU:
			a = palvelupisteet[5].otaJonosta();
			
			if (a == null) {
				break;
			} else {
				a.setPoistumisaika(Kello.getInstance().getAika());
				ei_eu_asiakkaat++;
				palvellut++;
				a.raportti();
			}
			break;
	
		case checkinIn:
			break;
		case checkinOut:
			break;
		case luoAsiakas:
			break;
		case passiOut:
			break;
		case turvaOut:
			break;
		default:
			break; 
		}	
	}
	
	@Override
	protected void tulokset() {	
		
		DAO dao = new DAO();
		dao.tallennaTiedot();
		
		
		System.out.println("************************************************* Tulokset *************************************************");
		
		System.out.println("\n\n" + "Simulointi päättyi kello " + Kello.getInstance().getAika());
		System.out.println("Palveltuja asiakkaita yhteensä: " + palvellut);
		Tulokset.getInstance().setPalvellut_asiakkaat(palvellut);
		System.out.println("Palveltuja EU asiakkaita: " + eu_asiakkaat + " ja palveltuja ei EU asiakkaita: " + ei_eu_asiakkaat);
		Tulokset.getInstance().lisaaeiEU(eu_asiakkaat);
		Tulokset.getInstance().lisaaeiEU(ei_eu_asiakkaat);
		//System.out.println("Jonottamaan jäi yhteensä: " + (palvelupisteet[0].jononPituus() + palvelupisteet[1].jononPituus() + palvelupisteet[2].jononPituus() + palvelupisteet[3].jononPituus() + palvelupisteet[4].jononPituus() + palvelupisteet[5].jononPituus()));
		
		//System.out.println("Turvatarkastuksen palvellut asiakkaat: " + palvelupisteet[0].getPalvellut_asiakkaat() + " ja niiden palvelemiseen käytetty aika: " + palvelupisteet[0].getPalveluaika());
		//System.out.println("Turvatarkastuksen keskimääräinen palveluaika: " + (palvelupisteet[0].getKeskimaarainen_palveluaika()));
		//System.out.println(palvelupisteet[0].jononPituus());
		//palvelupisteet[0].getKeskimaarainen_palveluaika();
		//System.out.println("Jos haluat ehtiä turvatarkastuksesta tule: " + (palvelupisteet[0].ehtiAika()) + " minuuttia etuajassa kentälle!");
		
		
		
		
		//System.out.println("Passitarkastuksen palvellut asiakkaat: " + palvelupisteet[3].getPalvellut_asiakkaat() + " ja niiden palvelemiseen käytetty aika: " + palvelupisteet[3].getPalveluaika());
		//System.out.println("Passitarkastuksen keskimääräinen palveluaika: " + (palvelupisteet[3].getKeskimaarainen_palveluaika()));
		//System.out.println(palvelupisteet[3].jononPituus());
		//palvelupisteet[5].getKeskimaarainen_palveluaika();
		//System.out.println("Jos haluat ehtiä passitarkastuken tule: " + (palvelupisteet[3].ehtiAika()) + " minuuttia etuajassa kentälle!");
	
		Tulokset.getInstance().setPalvellut_asiakkaat(palvellut);
		Tulokset.getInstance().setSimuloinnin_kokonaisaika(Kello.getInstance().getAika());
		
		Tulokset.getInstance().printSaapumisAjat();
		System.out.println("\n\n" + "************************************************************************************************************");
		//System.out.println(palvelupisteet[3].getPalvellut_asiakkaat());
		
		
		
		
		
	}

	
	public void haeAjat() {
		
		palvelupisteet[0].getKeskimaarainen_palveluaika();
		palvelupisteet[3].getKeskimaarainen_palveluaika();
		
		if (Kello.getInstance().getAika() > 500 && Kello.getInstance().getAika() < 1000 && joku == false) {
			Tulokset.getInstance().lisaaSaapumisAika(palvelupisteet[0].ehtiAika());
			Tulokset.getInstance().lisaaEiEUAika(palvelupisteet[3].ehtiAika());
			joku = true;
		} else if (Kello.getInstance().getAika() > 1000 && Kello.getInstance().getAika() < 2000 && joku == true) {
			Tulokset.getInstance().lisaaSaapumisAika(palvelupisteet[0].ehtiAika());
			Tulokset.getInstance().lisaaEiEUAika(palvelupisteet[3].ehtiAika());
			joku = false;
		} else if (Kello.getInstance().getAika() > 2000 && Kello.getInstance().getAika() < 3000 && joku == false) {
			Tulokset.getInstance().lisaaSaapumisAika(palvelupisteet[0].ehtiAika());
			Tulokset.getInstance().lisaaEiEUAika(palvelupisteet[3].ehtiAika());
			joku = true;
		} else if (Kello.getInstance().getAika() > 3000 && Kello.getInstance().getAika() < 4000 && joku == true) {
			Tulokset.getInstance().lisaaSaapumisAika(palvelupisteet[0].ehtiAika());
			Tulokset.getInstance().lisaaEiEUAika(palvelupisteet[3].ehtiAika());
			joku = false;
		} else if (Kello.getInstance().getAika() > 4000 && Kello.getInstance().getAika() < 5000 && joku == false) {
			Tulokset.getInstance().lisaaSaapumisAika(palvelupisteet[0].ehtiAika());
			Tulokset.getInstance().lisaaEiEUAika(palvelupisteet[3].ehtiAika());
			joku = true;
		} else if (Kello.getInstance().getAika() > 5000 && Kello.getInstance().getAika() < 6000 && joku == true) {
			
			saapumisprosessi.paivitaJakauma(new Normal((15000 / Tulokset.getInstance().getNumOfCustomers()), 1));
			
			Tulokset.getInstance().lisaaSaapumisAika(palvelupisteet[0].ehtiAika());
			Tulokset.getInstance().lisaaEiEUAika(palvelupisteet[3].ehtiAika());
			joku = false;
		} else if (Kello.getInstance().getAika() > 6000 && Kello.getInstance().getAika() < 7000 && joku == false) {
			
			Tulokset.getInstance().lisaaSaapumisAika(palvelupisteet[0].ehtiAika());
			Tulokset.getInstance().lisaaEiEUAika(palvelupisteet[3].ehtiAika());
			joku = true;
		} else if (Kello.getInstance().getAika() > 7000 && Kello.getInstance().getAika() < 8000 && joku == true) {
			
			saapumisprosessi.paivitaJakauma(new Normal((25000 / Tulokset.getInstance().getNumOfCustomers()), 1));
			Tulokset.getInstance().lisaaSaapumisAika(palvelupisteet[0].ehtiAika());
			Tulokset.getInstance().lisaaEiEUAika(palvelupisteet[3].ehtiAika());
			joku = false;
		} else if (Kello.getInstance().getAika() > 8000 && Kello.getInstance().getAika() < 9000 && joku == false) {
			Tulokset.getInstance().lisaaSaapumisAika(palvelupisteet[0].ehtiAika());
			Tulokset.getInstance().lisaaEiEUAika(palvelupisteet[3].ehtiAika());
			joku = true;
		} else if (Kello.getInstance().getAika() > 9000 && Kello.getInstance().getAika() < 10000 && joku == true) {
			Tulokset.getInstance().lisaaSaapumisAika(palvelupisteet[0].ehtiAika());
			Tulokset.getInstance().lisaaEiEUAika(palvelupisteet[3].ehtiAika());
			joku = false;
		} else if (Kello.getInstance().getAika() > 10000 && Kello.getInstance().getAika() < 11000 && joku == false) {
			Tulokset.getInstance().lisaaSaapumisAika(palvelupisteet[0].ehtiAika());
			Tulokset.getInstance().lisaaEiEUAika(palvelupisteet[3].ehtiAika());
			joku = true;
		} else if (Kello.getInstance().getAika() > 11000 && Kello.getInstance().getAika() < 12000 && joku == true) {
			Tulokset.getInstance().lisaaSaapumisAika(palvelupisteet[0].ehtiAika());
			Tulokset.getInstance().lisaaEiEUAika(palvelupisteet[3].ehtiAika());
			joku = false;
		} else if (Kello.getInstance().getAika() > 12000 && Kello.getInstance().getAika() < 13000 && joku == false) {
			Tulokset.getInstance().lisaaSaapumisAika(palvelupisteet[0].ehtiAika());
			Tulokset.getInstance().lisaaEiEUAika(palvelupisteet[3].ehtiAika());
			joku = true;
		} else if (Kello.getInstance().getAika() > 13000 && Kello.getInstance().getAika() < 14000 && joku == true) {
			Tulokset.getInstance().lisaaSaapumisAika(palvelupisteet[0].ehtiAika());
			Tulokset.getInstance().lisaaEiEUAika(palvelupisteet[3].ehtiAika());
			joku = false;
		} else if (Kello.getInstance().getAika() > 14000 && Kello.getInstance().getAika() < 15000 && joku == false) {
			
			saapumisprosessi.paivitaJakauma(new Normal((20000 / Tulokset.getInstance().getNumOfCustomers()), 1));
			
			Tulokset.getInstance().lisaaSaapumisAika(palvelupisteet[0].ehtiAika());
			Tulokset.getInstance().lisaaEiEUAika(palvelupisteet[3].ehtiAika());
			joku = true;
		} else if (Kello.getInstance().getAika() > 15000 && Kello.getInstance().getAika() < 16000 && joku == true) {
			Tulokset.getInstance().lisaaSaapumisAika(palvelupisteet[0].ehtiAika());
			Tulokset.getInstance().lisaaEiEUAika(palvelupisteet[3].ehtiAika());
			joku = false;
		} else if (Kello.getInstance().getAika() > 16000 && Kello.getInstance().getAika() < 17000 && joku == false) {
			Tulokset.getInstance().lisaaSaapumisAika(palvelupisteet[0].ehtiAika());
			Tulokset.getInstance().lisaaEiEUAika(palvelupisteet[3].ehtiAika());
			joku = true;
		} else if (Kello.getInstance().getAika() > 17000 && Kello.getInstance().getAika() < 18000 && joku == true) {
			
			saapumisprosessi.paivitaJakauma(new Normal((30000 / Tulokset.getInstance().getNumOfCustomers()), 1));
			
			Tulokset.getInstance().lisaaSaapumisAika(palvelupisteet[0].ehtiAika());
			Tulokset.getInstance().lisaaEiEUAika(palvelupisteet[3].ehtiAika());
			joku = false;
		} else if (Kello.getInstance().getAika() > 18000 && Kello.getInstance().getAika() < 19000 && joku == false) {
			Tulokset.getInstance().lisaaSaapumisAika(palvelupisteet[0].ehtiAika());
			Tulokset.getInstance().lisaaEiEUAika(palvelupisteet[3].ehtiAika());
			joku = true;
		} else if (Kello.getInstance().getAika() > 19000 && Kello.getInstance().getAika() < 20000 && joku == true) {
			Tulokset.getInstance().lisaaSaapumisAika(palvelupisteet[0].ehtiAika());
			Tulokset.getInstance().lisaaEiEUAika(palvelupisteet[3].ehtiAika());
			joku = false;
		} else if (Kello.getInstance().getAika() > 20000 && Kello.getInstance().getAika() < 21000 && joku == false) {
			Tulokset.getInstance().lisaaSaapumisAika(palvelupisteet[0].ehtiAika());
			Tulokset.getInstance().lisaaEiEUAika(palvelupisteet[3].ehtiAika());
			joku = true;
		} else if (Kello.getInstance().getAika() > 21000 && Kello.getInstance().getAika() < 22000 && joku == true) {
			Tulokset.getInstance().lisaaSaapumisAika(palvelupisteet[0].ehtiAika());
			Tulokset.getInstance().lisaaEiEUAika(palvelupisteet[3].ehtiAika());
			joku = false;
		} else if (Kello.getInstance().getAika() > 22000 && Kello.getInstance().getAika() < 23000 && joku == false) {
			Tulokset.getInstance().lisaaSaapumisAika(palvelupisteet[0].ehtiAika());
			Tulokset.getInstance().lisaaEiEUAika(palvelupisteet[3].ehtiAika());
			joku = true;
		} else if (Kello.getInstance().getAika() > 23000 && Kello.getInstance().getAika() < 24000 && joku == true) {
			Tulokset.getInstance().lisaaSaapumisAika(palvelupisteet[0].ehtiAika());
			Tulokset.getInstance().lisaaEiEUAika(palvelupisteet[3].ehtiAika());
			joku = false;
		}
	}




	@Override
	public Palvelupiste[] getPalvelupisteet() {
		// TODO Auto-generated method stub
		return null;
	}




	
}
