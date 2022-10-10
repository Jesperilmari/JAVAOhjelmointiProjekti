package simu.framework;

import simu.model.Palvelupiste;

public interface IMoottori {
	public void setSimulointiaika(double aika);
	public void setDelay(long aika);
	public long getDelay();
	public Palvelupiste[] getPalvelupisteet();

}
