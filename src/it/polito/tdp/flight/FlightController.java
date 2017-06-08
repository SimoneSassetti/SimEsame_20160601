package it.polito.tdp.flight;

import java.net.URL;
import java.util.*;

import it.polito.tdp.flight.model.Airline;
import it.polito.tdp.flight.model.Airport;
import it.polito.tdp.flight.model.Model;
import it.polito.tdp.flight.model.Stat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FlightController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Airline> boxAirline;

    @FXML
    private ComboBox<Airport> boxAirport;

    @FXML
    private TextArea txtResult;

    @FXML
    void doRaggiungibili(ActionEvent event) {
    	txtResult.clear();
    	Airport source=boxAirport.getValue();
    	if(source==null){
    		txtResult.appendText("Selezionare un aeroporto.\n");
    		return;
    	}
    	
    	List<Stat>stat=model.getRaggiungibili(source);
    	txtResult.appendText("Aeroporti raggiungibili da "+source+":\n");
    	for(Stat s: stat){
    		txtResult.appendText(String.format("%s %.2f\n", s.getDest().getName(),s.getDist()));
    	}
    }

    @FXML
    void doServiti(ActionEvent event) {
    	txtResult.clear();
    	Airline comp=boxAirline.getValue();
    	if(comp==null){
    		txtResult.appendText("Selezionare una compagnia.\n");
    		return;
    	}
    	txtResult.appendText("Aeroporti raggiunti da "+comp+":\n");
    	Set<Airport> lista=model.creaGrafo(comp);
    	for(Airport a: lista){
    		txtResult.appendText(a.getName()+"\n");
    	}
    	txtResult.appendText(""+lista.size());
  
    	boxAirport.getItems().addAll(lista);
    }

    @FXML
    void initialize() {
        assert boxAirline != null : "fx:id=\"boxAirline\" was not injected: check your FXML file 'Flight.fxml'.";
        assert boxAirport != null : "fx:id=\"boxAirport\" was not injected: check your FXML file 'Flight.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Flight.fxml'.";
    }
    
    Model model;
	public void setModel(Model model) {
		this.model=model;
		
		boxAirline.getItems().addAll(model.getCompagnie());
	}
}
