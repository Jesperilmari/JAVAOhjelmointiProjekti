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

<<<<<<< HEAD
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Trace.setTraceLevel(Level.INFO);
		Moottori m = new OmaMoottori();
		m.setSimulointiaika(tulokset.getSimuloinnin_kokonaisaika());
		m.aja();

	}
=======
@Override
public void run() {
	// TODO Auto-generated method stub
	
	Trace.setTraceLevel(Level.INFO);
	Moottori m = new OmaMoottori();
	DAO d = new DAO();
	m.setSimulointiaika(24000);
	m.aja();
	
	
	Tulokset.getInstance().setSimuloinnin_kokonaisaika(2400);
	Tulokset.getInstance().setFlightNum(10);
	Tulokset.getInstance().setnumOfCustomers(200);
	
	d.tallennaTiedot();
	
}
>>>>>>> branch 'main' of https://github.com/Jesperilmari/JAVAOhjelmointiProjekti.git
}
