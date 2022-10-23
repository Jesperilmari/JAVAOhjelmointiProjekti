package test;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import simu.model.DAO;

@DisplayName("Ohjelmointi Projekti DAO class testi")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DAOTestClass {
	
	DAO dao = new DAO();
	
	@Test
	void testDAO() {
		
		
	}

	@Test
	void testTallennaTiedot() {
		System.out.print("--TallennaTiedot Test: testTallennaTiedot()");
		assertTrue(dao.tallennaTiedot(), "TallennTiedot(): Tallentaminen epäonnistuneet!");
		System.out.println("TallennaTiedot(): - OK");
		
	}

	@Test
	void testHaeTiedot() {
		System.out.print("--HaeTiedot Test: HaeTideot()");
		assertTrue(dao.haeTiedot(0) == null, "HaeTideot(): ei piti olla NULL" );
		
		System.out.println("HaeTiedot(): - OK");
	}

}
