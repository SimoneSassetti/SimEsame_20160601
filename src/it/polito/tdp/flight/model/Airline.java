package it.polito.tdp.flight.model;

public class Airline {
	
	private int airlineId ;
	private String name ;
	private String callsign ;
	private String country ;
	
	public Airline(int airlineId, String name, String callsign, String country) {
		super();
		this.airlineId = airlineId;
		this.name = name;
		this.callsign = callsign;
		this.country = country;
	}

	public int getAirlineId() {
		return airlineId;
	}
	public void setAirlineId(int airlineId) {
		this.airlineId = airlineId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCallsign() {
		return callsign;
	}
	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + airlineId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Airline other = (Airline) obj;
		if (airlineId != other.airlineId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return airlineId+" "+name;
	}
	
	

}
