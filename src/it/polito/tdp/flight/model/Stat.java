package it.polito.tdp.flight.model;

public class Stat implements Comparable<Stat>{
	private Airport dest;
	private double dist;
	private int size;//quanti passi, cioè quanti aeroporti ho attraversato prima di raggiungere la destinazione
	
	public Stat(Airport dest, double dist, int size) {
		super();
		this.dest = dest;
		this.dist = dist;
		this.size = size;
	}
	public Airport getDest() {
		return dest;
	}
	public void setDest(Airport dest) {
		this.dest = dest;
	}
	public double getDist() {
		return dist;
	}
	public void setDist(double dist) {
		this.dist = dist;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	@Override
	public int compareTo(Stat s) {
		return Double.compare(this.dist, s.dist);
	}
	
}
