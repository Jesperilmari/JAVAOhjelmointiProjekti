package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import simu.model.Asiakas;

class AsiakasTestClass {

	@Test
	void testGetPoistumisaika() {
		System.out.print("--Asiakas Test: GetPoistumis()");
		Asiakas asiakas = new Asiakas();
		double as = asiakas.getPoistumisaika();
		assertTrue(as >= 0, "Palautti väärän ARVON - ");
		System.out.println("--- GetPoistumis(): OK");
		
	}

	@Test
	void testGetSaapumisaika() {
		System.out.print("--Asiakas Test: GetSaapumisaika()");
		Asiakas asiakas = new Asiakas();
		double as = asiakas.getPoistumisaika();
		assertTrue(as >= 0, "Palautti väärän ARVON - ");
		System.out.println("--- GetPoistumis(): OK");
		
	}

	@Test
	void testGetId() {
		System.out.print("--Asiakas Test: getId()");
		Asiakas asiakas = new Asiakas();
		double as = asiakas.getPoistumisaika();
		Assert.assertTrue(Integer.class.isInstance(as));
		System.out.println("--- GetPoistumis(): OK");
	}

}
