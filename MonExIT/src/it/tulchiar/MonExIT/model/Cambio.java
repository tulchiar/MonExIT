package it.tulchiar.MonExIT.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Cambio {
	private Double importoValutaA;
	private Valuta valutaA;
	private Double tassoDicambio;
	private LocalDate data;
	private Double importoValutaB;
	private Valuta valutaB;
	
	public Double getImportoValutaA() {
		return importoValutaA;
	}
	public void setImportoValutaA(Double importoValutaA) {
		this.importoValutaA = importoValutaA;
	}
	public Valuta getValutaA() {
		return valutaA;
	}
	public void setValutaA(Valuta valutaA) {
		this.valutaA = valutaA;
	}
	public Double getTassoDicambio() {
		
		if(this.valutaA != null & this.valutaB != null & this.data != null) {
			tassoDicambio = getTassoDiCambio(this.valutaA, this.valutaB, this.data);
			return tassoDicambio;
		} else {
			return null;
		}
	
	}
	public void setTassoDicambio(Double tassoDicambio) {
		this.tassoDicambio = tassoDicambio;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Double getImportoValutaB() {
		return importoValutaB;
	}
	public void setImportoValutaB(Double importoValutaB) {
		this.importoValutaB = importoValutaB;
	}
	public Valuta getValutaB() {
		return valutaB;
	}
	public void setValutaB(Valuta valutaB) {
		this.valutaB = valutaB;
	}
	public Cambio(Double importoValutaA, LocalDate data) {
		super();
		this.importoValutaA = importoValutaA;
		this.data = data;
		this.valutaA = new Valuta("USD");
		this.valutaB = new Valuta("EUR");
		this.tassoDicambio = getTassoDiCambio(valutaA, valutaB, data);
		this.importoValutaB = importoValutaA / tassoDicambio;
	}
	
	public Cambio() {
		super();
		this.importoValutaA = null;
		this.importoValutaB = null;
		this.data = null;
		this.tassoDicambio = null;
		this.valutaA = new Valuta("USD");
		this.valutaB = new Valuta("EUR");
	}
	
	private Double getTassoDiCambio(Valuta valutaA, Valuta valutaB, LocalDate data) {
		//https://tassidicambio.bancaditalia.it/terzevalute-wf-web/rest/v1.0/dailyRates?referenceDate=2018-01-19&baseCurrencyIsoCode=USD&currencyIsoCode=EUR&lang=it
		
		Double result = 0.0;
		
		String urlString = "https://tassidicambio.bancaditalia.it/terzevalute-wf-web/rest/v1.0/dailyRates?"
				+ "referenceDate=" + data.toString()
				+ "&baseCurrencyIsoCode=" + valutaA.toString()
				+ "&currencyIsoCode=" + valutaB.toString()
				+ "&lang=it";
		
//		System.out.println(urlString);
		
		try {

				URL url = new URL(urlString);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
					
					JSONParser jsonParser = new JSONParser();
					JSONObject jsonObject = (JSONObject) jsonParser.parse(output);
					
				    JSONArray rates = (JSONArray) jsonObject.get("rates");  
				    
				    for (int i=0; i<rates.size(); i++) {										
				    	System.out.println(((JSONObject) rates.get(i)).get("referenceDate") + " --- " +((JSONObject) rates.get(i)).get("avgRate"));  
				    	result = Double.parseDouble(((JSONObject) rates.get(i)).get("avgRate").toString());
				    }
					
				}

				conn.disconnect();

			  } catch (MalformedURLException e) {

				e.printStackTrace();

			  } catch (IOException e) {

				e.printStackTrace();

			  } catch (org.json.simple.parser.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return result;
	}
	
	@Override
	public String toString() {
		return "Cambio da " + valutaA + " a " + valutaB + " in data " + data + " tasso di cambio " + tassoDicambio + "\n" 
				+ importoValutaA + " ===> " + importoValutaB;
	}
	
	public static ArrayList<Cambio> getElencoCambi(LocalDate dataA, LocalDate dataB, Valuta valutaA, Valuta valutaB) {
		
		ArrayList<Cambio> elencoCambi = new ArrayList<Cambio>();

//		/dailyTimeSeries?startDate={}&endDate={}&baseCurrencyIsoCode={}&currencyIsoCode
//		={}&lang={}

		String urlString = "https://tassidicambio.bancaditalia.it/terzevalute-wf-web/rest/v1.0/dailyTimeSeries?"
				+ "&startDate=" + dataA.toString()
				+ "&endDate=" + dataB.toString()
				+ "&baseCurrencyIsoCode=" + valutaA.toString()
				+ "&currencyIsoCode=" + valutaB.toString()
				+ "&lang=it";
		
//		System.out.println(urlString);
		
		try {

				URL url = new URL(urlString);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

				String output;
				    
			    JSONParser jsonParser = new JSONParser();
				
			    while ((output = br.readLine()) != null) {
				
			    	JSONObject jsonObject = (JSONObject) jsonParser.parse(output);
				    					
				    JSONArray rates = (JSONArray) jsonObject.get("rates");  
				    						    
				    for (int i=0; i<rates.size(); i++) {
				    					    	
				    	Double tassoDiCambio = Double.parseDouble(((JSONObject)rates.get(i)).get("avgRate").toString());
				    	LocalDate data = LocalDate.parse(((JSONObject) rates.get(i)).get("referenceDate").toString());
				    	
				    	Cambio cambio = new Cambio();
						cambio.setData(data);
				    	cambio.setTassoDicambio(tassoDiCambio);
				    	
				    	elencoCambi.add(cambio);
				    	
				    }
										
				}

				conn.disconnect();

			  } catch (MalformedURLException e) {
				  e.printStackTrace();
			  } catch (IOException e) {
				  e.printStackTrace();
			  } catch (org.json.simple.parser.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return elencoCambi;
	}
	
}
