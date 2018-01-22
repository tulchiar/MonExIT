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
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MonExITController {
	
	Model model;
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtImporto"
    private TextField txtImporto; // Value injected by FXMLLoader

    @FXML // fx:id="txtData"
    private TextField txtData; // Value injected by FXMLLoader

    @FXML // fx:id="txtControvalore"
    private TextField txtControvalore; // Value injected by FXMLLoader

    @FXML // fx:id="btnCalcola"
    private Button btnCalcola; // Value injected by FXMLLoader

    @FXML
    void doCalcola(ActionEvent event) {
    	try {
	    	Double tassoDiCambio = model.getTassoDiCambio(new Valuta("USD"), new Valuta("EUR"), LocalDate.parse(txtData.getText()));
	    	Double importoA = Double.parseDouble(txtImporto.getText());
	    	if(importoA != 0.0) {
	    		txtControvalore.setText(((Double)(importoA / tassoDiCambio)).toString());
	    	}
    	} catch(Exception e) {
    		System.err.println("Errore: " + e.getMessage());
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtImporto != null : "fx:id=\"txtImporto\" was not injected: check your FXML file 'MonExIT.fxml'.";
        assert txtData != null : "fx:id=\"txtData\" was not injected: check your FXML file 'MonExIT.fxml'.";
        assert txtControvalore != null : "fx:id=\"txtControvalore\" was not injected: check your FXML file 'MonExIT.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'MonExIT.fxml'.";

    }
}
