package simu.model;

import java.sql.*;
import java.util.ArrayList;


public class DAO {
	
	private static String Osoite = "localhost";
	private static String db = "kanta";
	private static String kayttis = "kayttis";
	private static String salis = "root";
	
	private final int kerta = 1;
	
	
	private Connection conn;
	
	
	public DAO() {
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://" + Osoite + "/" + db + "?user=" + kayttis + "&password=" + salis);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public boolean tallennaTiedot() {
		
		
		try (PreparedStatement t = conn.prepareStatement("INSERT INTO startData (simulationtime, number_of_flights, flight_capacity, number_of_customers, arrive01, arrive02, arrive03, arrive04, arrive05, arrive06, arrive07, arrive08, arrive09, arrive10, arrive11, arrive12, arrive13, arrive14, arrive15, arrive16, arrive17, arrive18, arrive19, arrive20, arrive21, arrive22, arrive23)" +
		"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)")) {
			t.setDouble(1, Tulokset.getInstance().getSimuloinnin_kokonaisaika());
			t.setDouble(2, Tulokset.getInstance().getFlightNum());
			t.setInt(3, Tulokset.getInstance().gettotalNumOfCusterms());
			t.setInt(4, Tulokset.getInstance().getNumOfCustomers());
			t.setDouble(5, Tulokset.getInstance().getSaapuminenX(1));
			t.setDouble(6, Tulokset.getInstance().getSaapuminenX(2));
			t.setDouble(7, Tulokset.getInstance().getSaapuminenX(3));
			t.setDouble(8, Tulokset.getInstance().getSaapuminenX(4));
			t.setDouble(9, Tulokset.getInstance().getSaapuminenX(5));
			t.setDouble(10, Tulokset.getInstance().getSaapuminenX(6));
			t.setDouble(11, Tulokset.getInstance().getSaapuminenX(7));
			t.setDouble(12, Tulokset.getInstance().getSaapuminenX(8));
			t.setDouble(13, Tulokset.getInstance().getSaapuminenX(9));
			t.setDouble(14, Tulokset.getInstance().getSaapuminenX(10));
			t.setDouble(15, Tulokset.getInstance().getSaapuminenX(11));
			t.setDouble(16, Tulokset.getInstance().getSaapuminenX(12));
			t.setDouble(17, Tulokset.getInstance().getSaapuminenX(13));
			t.setDouble(18, Tulokset.getInstance().getSaapuminenX(14));
			t.setDouble(19, Tulokset.getInstance().getSaapuminenX(15));
			t.setDouble(20, Tulokset.getInstance().getSaapuminenX(16));
			t.setDouble(21, Tulokset.getInstance().getSaapuminenX(17));
			t.setDouble(22, Tulokset.getInstance().getSaapuminenX(18));
			t.setDouble(23, Tulokset.getInstance().getSaapuminenX(19));
			t.setDouble(24, Tulokset.getInstance().getSaapuminenX(20));
			t.setDouble(25, Tulokset.getInstance().getSaapuminenX(21));
			t.setDouble(26, Tulokset.getInstance().getSaapuminenX(22));
			t.setDouble(27, Tulokset.getInstance().getSaapuminenX(23));
			
			t.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		return true;
		
	}
	
	
	
	public Tietokantatulokset haeTiedot(int id) {
		
		Tietokantatulokset tt = null;
		ArrayList<Tietokantatulokset> tulokset = new ArrayList<>();
		
		try (PreparedStatement t = conn.prepareStatement("SELECT * FROM startData WHERE id = (?)")) {
			t.setInt(1, id);
			ResultSet r = t.executeQuery();
			
			while (r.next()) {
				Tietokantatulokset tulos = new Tietokantatulokset(r.getInt(1), r.getInt(2), r.getInt(3), r.getInt(4), r.getInt(5), tulokset);
				tulokset.add(tulos);
				System.out.println(tulos.getFlight_capacity());
			}

		} catch (SQLException e) {
			System.out.println(e);
		}
		return tt;
	}
	
	
	
	
}
