package simu.model;

import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;

public class OmaMoottori extends Moottori{
	
	private Saapumisprosessi saapumisprosessi;
	public static int lennot = 20;
	
	
	public OmaMoottori(){
		
		
		palvelupisteet = new Palvelupiste[3];
		//for(int i = 0; i < palvelupisteet.length; i++) {
			
		//}
		
		palvelupisteet[0]=new Palvelupiste(new Normal(5,5), tapahtumalista, TapahtumanTyyppi.turvaIn);
		palvelupisteet[1]=new Palvelupiste(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.lahtoporttiIn);
		palvelupisteet[2]=new Palvelupiste(new Normal(5,2), tapahtumalista, TapahtumanTyyppi.lahtoporttiOut);
		
		saapumisprosessi = new Saapumisprosessi(new Negexp(10,5), tapahtumalista, TapahtumanTyyppi.luoLennot);

	}

	
	@Override
	protected void alustukset() {
		saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
	}
	
	@Override
	protected void suoritaTapahtuma(Tapahtuma t){  // B-vaiheen tapahtumat

		Asiakas a;
		switch (t.getTyyppi()) {
		
			
		case luoLennot:
			palvelupisteet[0].lisaaJonoon(new Asiakas());	
			saapumisprosessi.generoiSeuraava();	
			break;
			
		case turvaIn:
			a = palvelupisteet[0].otaJonosta();
			palvelupisteet[1].lisaaJonoon(a);
			break;
			
		case lahtoporttiIn:
			a = palvelupisteet[1].otaJonosta();
			palvelupisteet[2].lisaaJonoon(a); 
			break;
			
		case lahtoporttiOut:
			
			a = palvelupisteet[2].otaJonosta();
			a.setPoistumisaika(Kello.getInstance().getAika());
			a.raportti();
			          
		case checkinIn:
			break;
		case checkinOut:
			break;
		case luoAsiakas:
			break;
		case passiIn:
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
		//System.out.println("Tulokset ... puuttuvat vielä");
	}

	
}
