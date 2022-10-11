package simu.model;

import java.util.ArrayList;

public class Tietokantatulokset {
	
	
	private int id;
	private int simulationtime;
	private int number_of_flights;
	private int flight_capacity;
	private int number_of_customers;
	
	private ArrayList<Tietokantatulokset> tulokset = new ArrayList<Tietokantatulokset>();
	
	public Tietokantatulokset(int id, int simulationtime, int number_of_flights, int flight_capacity, int number_of_customers, ArrayList<Tietokantatulokset> tulokset) {
		this.setId(id);
		this.setSimulationtime(simulationtime);
		this.setNumber_of_flights(number_of_flights);
		this.setFlight_capacity(flight_capacity);
		this.setNumber_of_customers(number_of_customers);
		this.tulokset = tulokset;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSimulationtime() {
		return simulationtime;
	}

	public void setSimulationtime(int simulationtime) {
		this.simulationtime = simulationtime;
	}

	public int getNumber_of_flights() {
		return number_of_flights;
	}

	public void setNumber_of_flights(int number_of_flights) {
		this.number_of_flights = number_of_flights;
	}

	public int getFlight_capacity() {
		return flight_capacity;
	}

	public void setFlight_capacity(int flight_capacity) {
		this.flight_capacity = flight_capacity;
	}

	public int getNumber_of_customers() {
		return number_of_customers;
	}

	public void setNumber_of_customers(int number_of_customers) {
		this.number_of_customers = number_of_customers;
	}
	
}
