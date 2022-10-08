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

	public OmaMoottori(){


		palvelupisteet = new Palvelupiste[6];
		//for(int i = 0; i < palvelupisteet.length; i++) {

		//}

		// 
		palvelupisteet[0]=new Palvelupiste(new Normal(5, 10), tapahtumalista, TapahtumanTyyppi.turvaIn, 1);
		palvelupisteet[5]=new Palvelupiste(new Normal(5, 10), tapahtumalista, TapahtumanTyyppi.turvaIn, 1);

		//
		palvelupisteet[1]=new Palvelupiste(new Normal(3, 10), tapahtumalista, TapahtumanTyyppi.lahtoporttiInEU, 3);

		//
		palvelupisteet[2]=new Palvelupiste(new Normal(3, 10), tapahtumalista, TapahtumanTyyppi.lahtoporttiInMuu, 4);

		//
		palvelupisteet[3]=new Palvelupiste(new Normal(7, 10), tapahtumalista, TapahtumanTyyppi.passiIn, 2);

		//
		palvelupisteet[4]=new Palvelupiste(new Normal(3, 10), tapahtumalista, TapahtumanTyyppi.lahtoporttiOut, 5);

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


			saapumisprosessi.generoiSeuraava();	
			break;

		case turvaIn:
			// Katsotaan, onko asiakas lentämässä EU vai ei EU alueelle
			// Katsotaan, missä turvatarkastuksessa on lyhyin jono

			System.out.println(palvelupisteet[0].jononPituus());

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
			} else if (a.isOnkoEU() == false) {
				palvelupisteet[2].lisaaJonoon(a);
			}

			break;

		case lahtoporttiInEU:
			a = palvelupisteet[1].otaJonosta();
			palvelupisteet[4].lisaaJonoon(a);
			eu_asiakkaat++;
			break;

		case lahtoporttiInMuu:
			a = palvelupisteet[2].otaJonosta();
			palvelupisteet[3].lisaaJonoon(a);
			ei_eu_asiakkaat++;
			break;

		case passiIn:
			a = palvelupisteet[3].otaJonosta();
			palvelupisteet[4].lisaaJonoon(a);
			break;

		case lahtoporttiOut:

			a = palvelupisteet[4].otaJonosta();
			a.setPoistumisaika(Kello.getInstance().getAika());
			palvellut++;
			a.raportti();

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
		System.out.println("EU asiakkaita: " + eu_asiakkaat + " ja ei EU asiakkaita: " + ei_eu_asiakkaat);

		Tulokset.getInstance().setPalvellut_asiakkaat(palvellut);
		Tulokset.getInstance().setSimuloinnin_kokonaisaika(Kello.getInstance().getAika());
		//System.out.println("Tulokset ... puuttuvat vielä");

	}

}
