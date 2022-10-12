package simu.framework;

import java.util.PriorityQueue;

import simu.model.Asiakas;

public class Tapahtumalista {
	private PriorityQueue<Tapahtuma> lista = new PriorityQueue<Tapahtuma>();
	
	public Tapahtumalista(){
	 
	}
	
	public Tapahtuma poista(){
		//Trace.out(Trace.Level.INFO,"Tapahtumalistasta poisto " + lista.peek().getTyyppi() + " " + lista.peek().getAika() );
		return lista.remove();
	}
	
	public void lisaa(Tapahtuma t){
		
		if(t.getTyyppi().toString() == "turvaIn") {
			//Trace.out(Trace.Level.INFO, "Asiakas siirtyi turvatarkastukseen");
		} else if(t.getTyyppi().toString() == "lahtoporttiIn") {
			//Trace.out(Trace.Level.INFO, "Asiakas siirtyi lähtöportti jonoon");
		} else if(t.getTyyppi().toString() == "lahtoporttiOut") {
			//Trace.out(Trace.Level.INFO, "Asiakas  siirtyi lentokoneeseen");
		}
		//Trace.out(Trace.Level.INFO,"Tapahtumalistaan lisätään uusi " + t.getTyyppi() + " " + t.getAika());
		lista.add(t);
	}
	
	public double getSeuraavanAika(){
		return lista.peek().getAika();
	}
	
	
}
