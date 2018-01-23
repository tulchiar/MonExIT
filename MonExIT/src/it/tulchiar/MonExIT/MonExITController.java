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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class MonExITController {
	
	Model model;
	Scene scene;
	Boolean editabile = true;
	
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	public void setScene(Scene scene) {
		this.scene = scene;
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

    @FXML // fx:id="lblControvalore"
    private Label lblControvalore; // Value injected by FXMLLoader
    
    @FXML // fx:id="lblCambio"
    private Label lblCambio; // Value injected by FXMLLoader

    @FXML // fx:id="btnCalcola"
    private Button btnCalcola; // Value injected by FXMLLoader
    
    Clipboard clipboard;
    ClipboardContent clipboardContent;

    @FXML
    void doCalcola(ActionEvent event) {
	    	try {
	    		
	    		String giorno = txtGiorno.getText();
	    		String mese = txtMese.getText();
	    		String anno = txtAnno.getText();
	    		Double controvalore = 0.0;
	    		
	    		LocalDate data = LocalDate.parse(anno+"-"+mese+"-"+giorno);
	    		
		    	Double tassoDiCambio = model.getTassoDiCambio(new Valuta("USD"), new Valuta("EUR"), data);
		    	Double importoA = Double.parseDouble(txtImporto.getText());
		    	if(importoA != 0.0) {
		    		controvalore = (Double)(importoA / tassoDiCambio);
		    		lblCambio.setText("(cambio "+ String.format("%.4f", tassoDiCambio)+")");
		    		lblControvalore.setText(String.format("%.2f", controvalore));
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
    		lblControvalore.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){

				@Override
				public void handle(Event arg0) {
					clipboardContent.putString(lblControvalore.getText());
					clipboard.setContent(clipboardContent);
					
				}
    			
    		});

    }
    
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	
    	setup();
    	btnCalcola.setDefaultButton(true);
    	clipboard = Clipboard.getSystemClipboard();
    	clipboardContent = new ClipboardContent();
    	
        assert txtImporto != null : "fx:id=\"txtImporto\" was not injected: check your FXML file 'MonExIT.fxml'.";
        assert txtGiorno != null : "fx:id=\"txtGiorno\" was not injected: check your FXML file 'MonExIT.fxml'.";
        assert txtMese != null : "fx:id=\"txtMese\" was not injected: check your FXML file 'MonExIT.fxml'.";
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'MonExIT.fxml'.";
        assert lblControvalore != null : "fx:id=\"lblControvalore\" was not injected: check your FXML file 'MonExIT.fxml'.";
        assert lblCambio != null : "fx:id=\"lblCambio\" was not injected: check your FXML file 'MonExIT.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'MonExIT.fxml'.";

    }
}
