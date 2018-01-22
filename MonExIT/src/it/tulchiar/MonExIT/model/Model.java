package it.tulchiar.MonExIT.model;

import java.time.LocalDate;

public class Model {
	Cambio cambio = new Cambio();
	
	public Double getTassoDiCambio(Valuta valutaA, Valuta valutaB, LocalDate data) {
		
		cambio.setValutaA(valutaA);
		cambio.setValutaB(valutaB);
		cambio.setData(data);
		
		return cambio.getTassoDicambio();
		
	}
	
}
