package simu.model;

import java.util.ArrayList;

/**
 * Represent DAO class 
 * @author RYHMÄ 10
 * @version 1.8.0 Build 2022, October, 18
 *
 */


public class Tietokantatulokset {

	/**
	 * 
	 * Instance Variable for Database results (Tietokantatulokset) 
	 */
	private int id;
	private int simulationtime;
	private int number_of_flights;
	private int flight_capacity;
	private int number_of_customers;
	
	/**
	 * ArrayList of result 
	 */
	private ArrayList<Tietokantatulokset> tulokset = new ArrayList<Tietokantatulokset>();
	
	
	/**
	 * Default Constructor for Database result (Tietokantatulokset) 
	 * @param id AUTO_INCREMENT ID for customers
	 * @param simulationtime the total time of simulation
	 * @param number_of_flights, the total amount of airplanes
	 * @param flight_capacity, Capacity of a flight
	 * @param number_of_customers
	 * @param tulokset
	 */
	public Tietokantatulokset(int id, int simulationtime, int number_of_flights, int flight_capacity, int number_of_customers, ArrayList<Tietokantatulokset> tulokset) {
		this.setId(id);
		this.setSimulationtime(simulationtime);
		this.setNumber_of_flights(number_of_flights);
		this.setFlight_capacity(flight_capacity);
		this.setNumber_of_customers(number_of_customers);
		this.tulokset = tulokset;
	}
	
	/**
	 * Gets the ID 
	 * @return the ID 
	 */

	public int getId() {
		return id;
	}
	
	/**
	 * sets id
	 * @param id
	 */

	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the total amount of time which simulation run
	 * @return total time of simulation
	 */
	public int getSimulationtime() {
		return simulationtime;
	}
	
	/**
	 * sets the simulation time
	 * @param simulationtime
	 */

	public void setSimulationtime(int simulationtime) {
		this.simulationtime = simulationtime;
	}
	
	/**
	 * Gets the amount of flights
	 * @return the total amount of the flights
	 */
	public int getNumber_of_flights() {
		return number_of_flights;
	}
	
	
	/**
	 * sets the amount of flights
	 * @param number_of_flights
	 */

	public void setNumber_of_flights(int number_of_flights) {
		this.number_of_flights = number_of_flights;
	}
	
	/**
	 * Gets the flight capacity in a flight
	 * @return flight capacity
	 */

	public int getFlight_capacity() {
		return flight_capacity;
	}
	
	/**
	 * set flight capacity 
	 * @param flight_capacity
	 */

	public void setFlight_capacity(int flight_capacity) {
		this.flight_capacity = flight_capacity;
	}
	
	/**
	 * Gets the amount of customers 
	 * @return	the amount of the customers
	 */
	public int getNumber_of_customers() {
		return number_of_customers;
	}
	
	/**
	 * sets the amount of the customers
	 * @param number_of_customers
	 */

	public void setNumber_of_customers(int number_of_customers) {
		this.number_of_customers = number_of_customers;
	}
	
}
