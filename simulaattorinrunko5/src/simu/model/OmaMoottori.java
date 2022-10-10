package simu.model;

import java.util.ArrayList;
import java.util.Random;

<<<<<<< Upstream, based on origin/main
import controller.IControllerMtoV;
import controller.ISimuController;
=======
import controller.SimuController;
>>>>>>> 17a0606 ui juttui
import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import eduni.distributions.Uniform;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;
import controller.SimuController;

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
	double temp3 = 0;
	
	private int testt = 100000;
	//ArrayList<String> cars = new ArrayList<String>();
	//ArrayList<int[]> arr = new ArrayList<int[]>();
	
	public OmaMoottori(IControllerMtoV kontrolleri){
		
<<<<<<< Upstream, based on origin/main
		super(kontrolleri);
=======
>>>>>>> 17a0606 ui juttui
		palvelupisteet = new Palvelupiste[6];
		
		//for(int i = 0; i < palvelupisteet.length; i++) {
			
		//}
		
		//
		palvelupisteet[0]=new Palvelupiste(new Normal(3, 1), tapahtumalista, TapahtumanTyyppi.turvaIn, 1);
		
		
		//
		palvelupisteet[1]=new Palvelupiste(new Normal(3, 1), tapahtumalista, TapahtumanTyyppi.lahtoporttiInEU, 2);
		
		//
		palvelupisteet[2]=new Palvelupiste(new Normal(3, 1), tapahtumalista, TapahtumanTyyppi.lahtoporttiInMuu, 3);
		
		//
		palvelupisteet[3]=new Palvelupiste(new Normal(3, 1), tapahtumalista, TapahtumanTyyppi.passiIn, 4);
		
		//
		palvelupisteet[4]=new Palvelupiste(new Normal(3, 1), tapahtumalista, TapahtumanTyyppi.lahtoporttiOut, 5);
		
		palvelupisteet[5]=new Palvelupiste(new Normal(3, 1), tapahtumalista, TapahtumanTyyppi.lahtoporttiOut_eiEU, 6);
		
		// Asiakkaita saapuu ~2min välein
		saapumisprosessi = new Saapumisprosessi(new Normal(1, 1), tapahtumalista, TapahtumanTyyppi.luoLennot);

	}

	
	@Override
	protected void alustukset() {
		Kello.getInstance().setAika(0);
		
		saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
	}
	
	@Override
	protected void suoritaTapahtuma(Tapahtuma t){  // B-vaiheen tapahtumat
		Asiakas a;
		
		//Jonon pituus eri tunneilla
		if (Kello.getInstance().getAika() > 1000 && Kello.getInstance().getAika() < 2000) {
			temp2 = palvelupisteet[0].jononPituus() + palvelupisteet[3].jononPituus();
		} else if (Kello.getInstance().getAika() > 2000 && Kello.getInstance().getAika() < 3000) {
			temp1 = palvelupisteet[0].jononPituus() + palvelupisteet[3].jononPituus();
		}
		
		
		switch (t.getTyyppi()) {
		
			
		case luoLennot:
		
			if (testt > 0) {
				palvelupisteet[0].lisaaJonoon(new Asiakas());
				saapumisprosessi.generoiSeuraava();	
				
				palvelupisteet[0].setAloitusAika(Kello.getInstance().getAika());
				testt--;
			}
			//saapumisprosessi.generoiSeuraava();	
			
			
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
			
			palvelupisteet[0].lisaaPalveltuAsiakas();
			palvelupisteet[0].setLoppuAika(Kello.getInstance().getAika());
			
			palvelupisteet[0].lisaaPalveluAikaa(palvelupisteet[0].getLoppuAika() - palvelupisteet[0].getAloitusAika());
			if (a.isOnkoEU() == true) {
				palvelupisteet[1].lisaaJonoon(a);

			} else if (a.isOnkoEU() == false) {
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
			palvelupisteet[3].setAloitusAika(Kello.getInstance().getAika());
			
			break;
			
		case passiIn:
			a = palvelupisteet[3].otaJonosta();
			
			palvelupisteet[3].lisaaPalveltuAsiakas();
			palvelupisteet[3].setLoppuAika(Kello.getInstance().getAika());
			palvelupisteet[3].lisaaPalveluAikaa(palvelupisteet[3].getLoppuAika() - palvelupisteet[3].getAloitusAika());
			
			palvelupisteet[5].lisaaJonoon(a);

<<<<<<< Upstream, based on origin/main
=======
			
		
>>>>>>> 17a0606 ui juttui
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
		System.out.println("\n\n" + "Simulointi päättyi kello " + Kello.getInstance().getAika());
		System.out.println("Palveltuja asiakkaita yhteensä: " + palvellut);
		System.out.println("Palveltuja EU asiakkaita: " + eu_asiakkaat + " ja palveltuja ei EU asiakkaita: " + ei_eu_asiakkaat);
		
		System.out.println("Turvatarkastuksen palvellut asiakkaat: " + palvelupisteet[0].getPalvellut_asiakkaat() + " ja niiden palvelemiseen käytetty aika: " + palvelupisteet[0].getPalveluaika());
		System.out.println("Turvatarkastuksen keskimääräinen palveluaika: " + (palvelupisteet[0].getKeskimaarainen_palveluaika()));
		System.out.println(palvelupisteet[0].jononPituus());
		//palvelupisteet[0].getKeskimaarainen_palveluaika();
		System.out.println("Jos haluat ehtiä turvatarkastuksesta tule: " + (palvelupisteet[0].ehtiAika()) + " minuuttia etuajassa kentälle!");
		
		
		
		
		System.out.println("Passitarkastuksen palvellut asiakkaat: " + palvelupisteet[3].getPalvellut_asiakkaat() + " ja niiden palvelemiseen käytetty aika: " + palvelupisteet[3].getPalveluaika());
		System.out.println("Passitarkastuksen keskimääräinen palveluaika: " + (palvelupisteet[3].getKeskimaarainen_palveluaika()));
		System.out.println(palvelupisteet[3].jononPituus());
		//palvelupisteet[5].getKeskimaarainen_palveluaika();
		System.out.println("Jos haluat ehtiä passitarkastuken tule: " + (palvelupisteet[3].ehtiAika()) + " minuuttia etuajassa kentälle!");
	
		Tulokset.getInstance().setPalvellut_asiakkaat(palvellut);
		Tulokset.getInstance().setSimuloinnin_kokonaisaika(Kello.getInstance().getAika());
		//System.out.println("Tulokset ... puuttuvat vielä");
		
		System.out.println(temp2);
		System.out.println(temp1);
		
	}


	@Override
	public void setDelay(long aika) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public long getDelay() {
		// TODO Auto-generated method stub
		return 0;
	}




	
}
