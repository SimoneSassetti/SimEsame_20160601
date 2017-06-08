package it.polito.tdp.flight.model;

import java.util.*;

import org.jgrapht.*;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.BreadthFirstIterator;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import it.polito.tdp.flight.db.FlightDAO;

public class Model {
	
	private List<Airline> compagnie;
	private List<Airport> aeroporti;
	private Map<Integer, Airport> mappaPorti;
	private List<Route> viaggi;
	private SimpleDirectedWeightedGraph<Airport,DefaultWeightedEdge> grafo;
	
	FlightDAO dao;
	
	public Model(){
		dao=new FlightDAO();
		mappaPorti=new HashMap<>();
	}
	
	public List<Airline> getCompagnie() {
		if(compagnie==null){
			compagnie=dao.getCompagnie();
		}
		return compagnie;
	}
	
	public List<Airport> getAeroporti(){
		if(aeroporti==null)
			aeroporti=dao.getAllAirports();
		for(Airport a: aeroporti){
			mappaPorti.put(a.getAirportId(), a);
		}
		return aeroporti;
	}
	
	public void getRoute(Airline a){
		viaggi=dao.getRoute(a,mappaPorti);
	}
	
	public Set<Airport> creaGrafo(Airline comp){
		grafo=new SimpleDirectedWeightedGraph<Airport,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		this.getAeroporti();
		this.getRoute(comp);
		Set<Airport> portiRaggiunti=new HashSet<>();
		
		Graphs.addAllVertices(grafo, aeroporti);
		
		for(Route r: viaggi){
			Airport a=r.getSource();
			Airport b=r.getDestiation();
			portiRaggiunti.add(a);
			portiRaggiunti.add(b);
			
			DefaultWeightedEdge arco=grafo.addEdge(a, b);
			if(arco!=null){
				grafo.setEdgeWeight(arco, calcolaDistanza(a,b));
			}
		}
		return portiRaggiunti;
	}

	private double calcolaDistanza(Airport a, Airport b) {
		return LatLngTool.distance(a.getCoord(), b.getCoord(), LengthUnit.KILOMETER);
	}

	public List<Stat> getRaggiungibili(Airport source) {
		
		BreadthFirstIterator<Airport, DefaultWeightedEdge> bfi= new BreadthFirstIterator<Airport, DefaultWeightedEdge>(grafo, source);
		
		List<Airport> listaRaggiunti=new ArrayList<Airport>();
		List<Stat> stat=new ArrayList<Stat>();
		
		while(bfi.hasNext()){
			listaRaggiunti.add(bfi.next());
		}
		
		for(Airport a: listaRaggiunti){
			DijkstraShortestPath<Airport, DefaultWeightedEdge> dsp = new DijkstraShortestPath<>(grafo, source, a);
			GraphPath<Airport, DefaultWeightedEdge> p = dsp.getPath();
			if(p!=null){
				stat.add(new Stat(a,p.getWeight(),p.getEdgeList().size()));
			}
		}
		
		Collections.sort(stat);
		return stat;
	}
}
