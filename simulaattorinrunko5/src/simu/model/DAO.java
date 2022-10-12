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
		
		try (PreparedStatement t = conn.prepareStatement("INSERT INTO startData (simulationtime, number_of_flights, flight_capacity, number_of_customers)" + "VALUES(?,?,?,?)")) {
			t.setDouble(1, Tulokset.getInstance().getSimuloinnin_kokonaisaika());
			t.setDouble(2, Tulokset.getInstance().getFlightNum());
			t.setInt(3, Tulokset.getInstance().gettotalNumOfCusterms());
			t.setInt(4, Tulokset.getInstance().getNumOfCustomers());
			
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
