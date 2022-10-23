package controller;

/**
 * Represent IControllerMtoV Interface 
 * @author RYHMÄ 10
 * @version 1.8.0 Build 2022, October, 21
 *
 */

public interface IControllerMtoV {
	
	public void naytaLoppuaika(double aika);
	public void visualisoiAsiakas();
	
	public void piirraTurva(int jononPituus);
	public void piirraPassi(int jononPituus);
	public void piirraEu(int jononPituus);
	public void piirraMuu(int jononPituus);
	public void piirraPituus();
}
