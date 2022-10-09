package simu.model;

import java.util.Random;

import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import eduni.distributions.Uniform;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;

//jaa lennot tasaisesti simuloinnin ajalle

//



public class OmaMoottori extends Moottori{
	
	private Saapumisprosessi saapumisprosessi;
	public static int lennot = 20;
	private int palvellut;
	
	private int eu_asiakkaat;
	private int ei_eu_asiakkaat;
	
	double temp1 = 0;
	double temp2 = 0;
	
	public OmaMoottori(){
		
		
		palvelupisteet = new Palvelupiste[6];
		
		//for(int i = 0; i < palvelupisteet.length; i++) {
			
		//}
		
		// 
		palvelupisteet[0]=new Palvelupiste(new Normal(3, 1), tapahtumalista, TapahtumanTyyppi.turvaIn, 1);
		
		
		//
		palvelupisteet[1]=new Palvelupiste(new Normal(7, 1), tapahtumalista, TapahtumanTyyppi.lahtoporttiInEU, 2);
		
		//
		palvelupisteet[2]=new Palvelupiste(new Normal(3, 10), tapahtumalista, TapahtumanTyyppi.lahtoporttiInMuu, 3);
		
		//
		palvelupisteet[3]=new Palvelupiste(new Normal(22, 1), tapahtumalista, TapahtumanTyyppi.passiIn, 4);
		
		//
		palvelupisteet[4]=new Palvelupiste(new Normal(3, 10), tapahtumalista, TapahtumanTyyppi.lahtoporttiOut, 5);
		
		palvelupisteet[5]=new Palvelupiste(new Normal(5, 10), tapahtumalista, TapahtumanTyyppi.lahtoporttiOut_eiEU, 6);
		// Asiakkaita saapuu ~2min välein
		saapumisprosessi = new Saapumisprosessi(new Normal(2, 1), tapahtumalista, TapahtumanTyyppi.luoLennot);

	}

	
	@Override
	protected void alustukset() {
		Kello.getInstance().setAika(0);
		saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
	}
	
	@Override
	protected void suoritaTapahtuma(Tapahtuma t){  // B-vaiheen tapahtumat

		Asiakas a;
		switch (t.getTyyppi()) {
		
			
		case luoLennot:
			//
			int test = 1;
			/*
			if (test == 1) {
				palvelupisteet[0].lisaaJonoon(new Asiakas());
				test++;
			} else if (test == 2) {
				palvelupisteet[5].lisaaJonoon(new Asiakas());
				test--;
			}
			*/
			
			palvelupisteet[0].lisaaJonoon(new Asiakas());
			palvelupisteet[0].setAloitusAika(Kello.getInstance().getAika());
			
			saapumisprosessi.generoiSeuraava();	
			break;
			
		case turvaIn:
			// Katsotaan, onko asiakas lentämässä EU vai ei EU alueelle
			// Katsotaan, missä turvatarkastuksessa on lyhyin jono
			
			
			/*
			if (palvelupisteet[0].jononPituus() >= palvelupisteet[5].jononPituus()) {
				a = palvelupisteet[0].otaJonosta();
			} else {
				a = palvelupisteet[5].otaJonosta();
			}
			*/
			
			a = palvelupisteet[0].otaJonosta();
			
			if (a.isOnkoEU() == true) {
				palvelupisteet[1].lisaaJonoon(a);
				palvelupisteet[0].lisaaPalveltuAsiakas();
				palvelupisteet[0].setLoppuAika(Kello.getInstance().getAika());
				palvelupisteet[0].lisaaPalveluAikaa(palvelupisteet[0].getLoppuAika() - palvelupisteet[0].getAloitusAika());
				
			} else if (a.isOnkoEU() == false) {
				palvelupisteet[0].lisaaPalveltuAsiakas();
				palvelupisteet[2].lisaaJonoon(a);
			}
			
			break;
			
		case lahtoporttiInEU:
			a = palvelupisteet[1].otaJonosta();
			palvelupisteet[4].lisaaJonoon(a);
			
			break;
			
		case lahtoporttiInMuu:
			a = palvelupisteet[2].otaJonosta();
			palvelupisteet[3].lisaaJonoon(a);
			
			break;
			
		case passiIn:
			a = palvelupisteet[3].otaJonosta();
			palvelupisteet[5].lisaaJonoon(a);

			palvelupisteet[5].setAloitusAika(Kello.getInstance().getAika());
			
		
			break;
			
		case lahtoporttiOut:
			
			a = palvelupisteet[4].otaJonosta();
			a.setPoistumisaika(Kello.getInstance().getAika());
			eu_asiakkaat++;
			palvellut++;
			a.raportti();

		case lahtoporttiOut_eiEU:
			a = palvelupisteet[5].otaJonosta();
			palvelupisteet[5].lisaaPalveltuAsiakas();
			palvelupisteet[5].setLoppuAika(Kello.getInstance().getAika());
			palvelupisteet[5].lisaaPalveluAikaa(palvelupisteet[5].getLoppuAika() - palvelupisteet[5].getAloitusAika());
			if (a == null) {
				break;
			} else {
				a.setPoistumisaika(Kello.getInstance().getAika());
				ei_eu_asiakkaat++;
				palvellut++;
				a.raportti();
			}
	
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

	public Palvelupiste[] getPalvelupisteet() {
		return palvelupisteet;
	}
	
	@Override
	protected void tulokset() {	
		System.out.println("\n\n" + "Simulointi päättyi kello " + Kello.getInstance().getAika());
		System.out.println("Palveltuja asiakkaita yhteensä: " + palvellut);
		System.out.println("Palveltuja EU asiakkaita: " + eu_asiakkaat + " ja palveltuja ei EU asiakkaita: " + ei_eu_asiakkaat);
		
		System.out.println("Turvatarkastuksen palvellut asiakkaat: " + palvelupisteet[0].getPalvellut_asiakkaat() + " ja niiden palvelemiseen käytetty aika: " + palvelupisteet[0].getPalveluaika());
		System.out.println("Turvatarkastuksen keskimääräinen palveluaika: " + (palvelupisteet[0].getKeskimaarainen_palveluaika()));
		System.out.println(palvelupisteet[0].jononPituus());
		System.out.println("Jos haluat ehtiä turvatarkastuksesta tule: " + (palvelupisteet[0].ehtiAika()) + " minuuttia etuajassa kentälle!");
		
		
		
		
		System.out.println("Turvatarkastuksen palvellut asiakkaat: " + palvelupisteet[5].getPalvellut_asiakkaat() + " ja niiden palvelemiseen käytetty aika: " + palvelupisteet[5].getPalveluaika());
		System.out.println("Turvatarkastuksen keskimääräinen palveluaika: " + (palvelupisteet[5].getKeskimaarainen_palveluaika()));
		System.out.println(palvelupisteet[5].jononPituus());
		System.out.println("Jos haluat ehtiä passitarkastuken tule: " + (palvelupisteet[5].ehtiAika()) + " minuuttia etuajassa kentälle!");
	
		Tulokset.getInstance().setPalvellut_asiakkaat(palvellut);
		Tulokset.getInstance().setSimuloinnin_kokonaisaika(Kello.getInstance().getAika());
		//System.out.println("Tulokset ... puuttuvat vielä");
		
	}

	
}
