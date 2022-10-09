package simu.model;

import java.sql.*;


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
	
	
	
	
}
