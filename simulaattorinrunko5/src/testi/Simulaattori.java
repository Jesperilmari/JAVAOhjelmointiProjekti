package testi;
import simu.framework.*;
import simu.framework.Trace.Level;
import simu.model.DAO;
import simu.model.OmaMoottori;
import simu.model.Tulokset;

public class Simulaattori implements Runnable { //Tekstipohjainen
	
	Tulokset tulokset = Tulokset.getInstance();
	
	public static void main(String[] args) {
		Simulaattori simu = new Simulaattori();
		Thread t = new Thread(simu);
		t.start();
		//comment here
	}

}