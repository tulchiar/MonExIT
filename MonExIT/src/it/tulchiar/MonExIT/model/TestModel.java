package it.tulchiar.MonExIT.model;

import java.time.LocalDate;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		Double tassoDiCambio = model.getTassoDiCambio(new Valuta("USD"), new Valuta("EUR"), LocalDate.parse("2017-12-15"));
		
	
		System.out.println(tassoDiCambio);
		
	}

}
