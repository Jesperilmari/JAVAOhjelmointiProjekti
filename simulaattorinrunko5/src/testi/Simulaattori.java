package testi;
import controller.IControllerMtoV;
import controller.ISimuController;
import controller.SimuController;
import simu.framework.*;
import simu.framework.Trace.Level;
import simu.model.DAO;
import simu.model.OmaMoottori;
import simu.model.Tulokset;

<<<<<<< HEAD
public class Simulaattori implements Runnable { //Tekstipohjainen
=======
public class Simulaattori implements Runnable { //Tekstipohjainen


	Tulokset tulokset = Tulokset.getInstance();
>>>>>>> refs/remotes/origin/main
	
	private ISimuController kontrolleri;

<<<<<<< HEAD
	Tulokset tulokset = Tulokset.getInstance();
=======

>>>>>>> refs/remotes/origin/main

	public static void main(String[] args) {
		Simulaattori simu = new Simulaattori();
		Thread t = new Thread(simu);
		t.start();
		//comment here
	}

<<<<<<< HEAD
=======



>>>>>>> refs/remotes/origin/main
@Override
public void run() {
	// TODO Auto-generated method stub
	
	Trace.setTraceLevel(Level.INFO);
	
	//Moottori m = new OmaMoottori();
	DAO d = new DAO();
	//kontrolleri = new SimuController();
	
	//kontrolleri.kaynnistaSimulointi();
	//
	
	//Tulokset.getInstance().setSimuloinnin_kokonaisaika(100);
	//Tulokset.getInstance().setFlightNum(10);
	//Tulokset.getInstance().setnumOfCustomers(200);
	
	//m.setSimulointiaika(tulokset.getSimuloinnin_kokonaisaika());
	//m.aja();
<<<<<<< HEAD
=======

>>>>>>> refs/remotes/origin/main

	//d.tallennaTiedot();
	d.haeTiedot(2);
		

	}
}
