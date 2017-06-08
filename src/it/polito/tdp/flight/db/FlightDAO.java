package it.polito.tdp.flight.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.javadocmd.simplelatlng.LatLng;

import it.polito.tdp.flight.model.Airline;
import it.polito.tdp.flight.model.Airport;
import it.polito.tdp.flight.model.Route;

public class FlightDAO {
	
	public static void main(String args[]) {
		FlightDAO dao = new FlightDAO() ;
		
		List<Airport> arps = dao.getAllAirports() ;
		System.out.println(arps);
	}
	
	public List<Airport> getAllAirports() {
		
		String sql = "SELECT * FROM airport" ;
		List<Airport> list = new ArrayList<>() ;
		
		try {
			Connection conn = DBConnect.getConnection() ;
			PreparedStatement st = conn.prepareStatement(sql) ;
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				list.add( new Airport(
						res.getInt("Airport_ID"),
						res.getString("name"),
						res.getString("city"),
						res.getString("country"),
						res.getString("IATA_FAA"),
						res.getString("ICAO"),
						new LatLng(res.getDouble("Latitude"),res.getDouble("Longitude")),
						res.getFloat("timezone"),
						res.getString("dst"),
						res.getString("tz"))) ;
			}
			conn.close();
			return list ;
		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}
	


	public List<Airline> getCompagnie() {
		String sql = "SELECT Airline_ID,Name,Callsign,Country FROM airline" ;
		
		List<Airline> list = new ArrayList<>() ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet rs = st.executeQuery() ;
			
			while(rs.next()) {
				list.add( new Airline(rs.getInt("Airline_ID"),rs.getString("Name"),rs.getString("Callsign"),rs.getString("Country")));
			}
			conn.close();
			return list ;
		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}

	public List<Route> getRoute(Airline a, Map<Integer, Airport> mappa) {
		
		String sql="SELECT * FROM route WHERE Airline_ID=?";
		List<Route> list = new ArrayList<>() ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, a.getAirlineId());
			ResultSet rs = st.executeQuery() ;
			
			while(rs.next()) {
				if(mappa.get(rs.getInt("Source_airport_ID"))==null || mappa.get(rs.getInt("Destination_airport_ID"))==null){
					System.out.println("Errore! Aeroporto non definito.");
				}else{
					list.add(new Route(rs.getString("Airline"),rs.getInt("Airline_ID"),
						mappa.get(rs.getInt("Source_airport_ID")),mappa.get(rs.getInt("Destination_airport_ID")),
						rs.getString("Codeshare"),rs.getInt("Stops"),rs.getString("equipment")));
				}
			}
			conn.close();
			return list ;
		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}
	
}
