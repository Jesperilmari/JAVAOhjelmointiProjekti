package simu.framework;

import controller.IControllerMtoV;
import controller.ISimuController;
import simu.model.Palvelupiste;

public abstract class Moottori extends Thread implements IMoottori {
	
	private double simulointiaika = 0;
	
	private Kello kello;
	private long viive = 200;
	protected Tapahtumalista tapahtumalista;
	protected Palvelupiste[] palvelupisteet;
	
	protected IControllerMtoV kontrolleri;

	public Moottori(IControllerMtoV kontrolleri){

		this.kontrolleri = kontrolleri;
		kello = Kello.getInstance(); // Otetaan kello muuttujaan yksinkertaistamaan koodia
		
		tapahtumalista = new Tapahtumalista();
		
		// Palvelupisteet luodaan simu.model-pakkauksessa Moottorin aliluokassa 
		
		
	}

	public void setSimulointiaika(double aika) {
		simulointiaika = aika;
	}
	
	
	public void run(){
		alustukset(); // luodaan mm. ensimmäinen tapahtuma
		while (simuloidaan()){
			viive();
			//Trace.out(Trace.Level.INFO, "\nA-vaihe: kello on " + nykyaika());
			kello.setAika(nykyaika());
			
			//Trace.out(Trace.Level.INFO, "\nB-vaihe:" );
			suoritaBTapahtumat();
			
			//Trace.out(Trace.Level.INFO, "\nC-vaihe:" );
			yritaCTapahtumat();

		}
		tulokset();
		
	}
	
	private void suoritaBTapahtumat(){
		while (tapahtumalista.getSeuraavanAika() == kello.getAika()){
			suoritaTapahtuma(tapahtumalista.poista());
		}
	}

	private void yritaCTapahtumat(){
		for (Palvelupiste p: palvelupisteet){
			if (!p.onVarattu() && p.onJonossa()){
				p.aloitaPalvelu();
			}
		}
	}

	
	private double nykyaika(){
		return tapahtumalista.getSeuraavanAika();
	}
	
	private boolean simuloidaan(){
		return kello.getAika() < simulointiaika;
	}
	
	private void viive() { // UUSI
		//Trace.out(Trace.Level.INFO, "Viive " + viive);
		System.out.println("Viive " + viive);
		try {
			sleep(viive);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void setDelay(long viive) {
		// TODO Auto-generated method stub
		this.viive = viive;	
	}


	@Override
	public long getDelay() {
		// TODO Auto-generated method stub
		return viive;
	}	

	protected abstract void alustukset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
	protected abstract void suoritaTapahtuma(Tapahtuma t);  // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
	protected abstract void tulokset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
}