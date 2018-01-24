package it.tulchiar.MonExIT.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class testCambio {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Cambio cambio;
//		
//		cambio = new Cambio(1.00, LocalDate.parse("2016-01-19"));
//		System.out.println(cambio);
//		
//		cambio.setImportoValutaA(123.00);
//		cambio.setData(LocalDate.parse("2016-01-23"));
//		System.out.println(cambio);
//		
//		cambio.setImportoValutaA(1234.00);
//		cambio.setData(LocalDate.parse("2016-01-23"));
//		System.out.println(cambio);
//		
//		cambio.setImportoValutaA(1223.00);
//		cambio.setData(LocalDate.parse("2016-01-24"));
//		System.out.println(cambio);
//		
//		cambio.setImportoValutaA(1233.00);
//		cambio.setData(LocalDate.parse("2016-01-28"));
//		System.out.println(cambio);
//		
//		cambio.setImportoValutaA(1623.00);
//		cambio.setData(LocalDate.parse("2016-01-20"));
//		System.out.println(cambio);
//		
		
		LocalDate dataA = LocalDate.parse("2017-01-01");
		LocalDate dataB = LocalDate.parse("2017-01-05");
		
		Long startTime = System.currentTimeMillis();
		
		ArrayList<Cambio> elencoCambi = Cambio.getElencoCambi(dataA, dataB, new Valuta("USD"), new Valuta("EUR"));
		
		System.out.println("\n\nElenco cambi dalla data");
	
		for (Cambio cambio2 : elencoCambi) {
			System.out.println(cambio2.getData() + " - " + cambio2.getTassoDicambio());
		}
		
		Long endTime = System.currentTimeMillis();
		
		System.out.println("########### Tempo di esecuzione: " + (endTime-startTime));
		
	
	}

}
