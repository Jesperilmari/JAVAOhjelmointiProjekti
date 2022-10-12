package testi;
import controller.IControllerMtoV;
import controller.ISimuController;
import controller.SimuController;
import simu.framework.*;
import simu.framework.Trace.Level;
import simu.model.DAO;
import simu.model.OmaMoottori;
import simu.model.Tulokset;

public class Simulaattori implements Runnable { //Tekstipohjainen
	
	private ISimuController kontrolleri;

	Tulokset tulokset = Tulokset.getInstance();

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
	
	//Moottori m = new OmaMoottori();
	DAO d = new DAO();
	kontrolleri = new SimuController();
	
	kontrolleri.kaynnistaSimulointi();
	
	
	//Tulokset.getInstance().setSimuloinnin_kokonaisaika(100);
	//Tulokset.getInstance().setFlightNum(10);
	//Tulokset.getInstance().setnumOfCustomers(200);
	
	//m.setSimulointiaika(tulokset.getSimuloinnin_kokonaisaika());
	//m.aja();

		d.tallennaTiedot();

	}
}
