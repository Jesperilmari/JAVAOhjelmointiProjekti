package testi;
import simu.framework.*;
import simu.framework.Trace.Level;
import simu.model.DAO;
import simu.model.OmaMoottori;
import simu.model.Tulokset;

public class Simulaattori implements Runnable { //Tekstipohjainen

	public static void main(String[] args) {
		
		Simulaattori simu = new Simulaattori();
		Thread t = new Thread(simu);
		t.start();
		//comment here
	}

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
}
