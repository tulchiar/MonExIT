/**
 * Sample Skeleton for 'MonExIT.fxml' Controller Class
 */

package it.tulchiar.MonExIT;


import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import it.tulchiar.MonExIT.model.Model;
import it.tulchiar.MonExIT.model.Valuta;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MonExITController {
	
	Model model;
	Boolean editabile = true;
	
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtImporto"
    private TextField txtImporto; // Value injected by FXMLLoader

    @FXML // fx:id="txtGiorno"
    private TextField txtGiorno; // Value injected by FXMLLoader
    
    @FXML // fx:id="txtMese"
    private TextField txtMese; // Value injected by FXMLLoader
    
    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtControvalore"
    private TextField txtControvalore; // Value injected by FXMLLoader

    @FXML // fx:id="btnCalcola"
    private Button btnCalcola; // Value injected by FXMLLoader

    @FXML
    void doCalcola(ActionEvent event) {
	    	try {
	    		
	    		String giorno = txtGiorno.getText();
	    		String mese = txtMese.getText();
	    		String anno = txtAnno.getText();
	    		
	    		LocalDate data = LocalDate.parse(anno+"-"+mese+"-"+giorno);
	    		
		    	Double tassoDiCambio = model.getTassoDiCambio(new Valuta("USD"), new Valuta("EUR"), data);
		    	Double importoA = Double.parseDouble(txtImporto.getText());
		    	if(importoA != 0.0) {
		    		txtControvalore.setText(((Double)(importoA / tassoDiCambio)).toString());
		    	}
	    	} catch(Exception e) {
	    		System.err.println("Errore: " + e.getMessage());
	    	}
    }

    public void setup(){
    		txtGiorno.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					
					if(editabile) {
						txtMese.setDisable(true);
						txtAnno.setDisable(true);
						editabile = false;
					} else {
						txtMese.setDisable(false);
						txtAnno.setDisable(false);
						editabile = true;
					}
					
					
				}
			});
    }
    
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	
    	setup();
    	btnCalcola.setDefaultButton(true);
    	
    	
        assert txtImporto != null : "fx:id=\"txtImporto\" was not injected: check your FXML file 'MonExIT.fxml'.";
        assert txtGiorno != null : "fx:id=\"txtGiorno\" was not injected: check your FXML file 'MonExIT.fxml'.";
        assert txtMese != null : "fx:id=\"txtMese\" was not injected: check your FXML file 'MonExIT.fxml'.";
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'MonExIT.fxml'.";
        assert txtControvalore != null : "fx:id=\"txtControvalore\" was not injected: check your FXML file 'MonExIT.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'MonExIT.fxml'.";

    }
}
