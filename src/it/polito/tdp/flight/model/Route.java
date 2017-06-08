package it.polito.tdp.flight.model;

public class Route {
	
	private String airline ;
	private int airlineId ;
	private Airport source;
	private Airport destiation;
	private String codeshare ;
	private int stops ;
	private String equipment ;
	
	public Route(String airline, int airlineId, Airport source, Airport destiation, String codeshare, int stops,
			String equipment) {
		super();
		this.airline = airline;
		this.airlineId = airlineId;
		this.source = source;
		this.destiation = destiation;
		this.codeshare = codeshare;
		this.stops = stops;
		this.equipment = equipment;
	}

	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public int getAirlineId() {
		return airlineId;
	}
	public void setAirlineId(int airlineId) {
		this.airlineId = airlineId;
	}
	public Airport getSource() {
		return source;
	}
	public void setSource(Airport source) {
		this.source = source;
	}
	public Airport getDestiation() {
		return destiation;
	}
	public void setDestiation(Airport destiation) {
		this.destiation = destiation;
	}
	public String getCodeshare() {
		return codeshare;
	}
	public void setCodeshare(String codeshare) {
		this.codeshare = codeshare;
	}
	public int getStops() {
		return stops;
	}
	public void setStops(int stops) {
		this.stops = stops;
	}
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

}
