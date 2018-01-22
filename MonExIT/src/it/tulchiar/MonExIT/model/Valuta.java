package it.tulchiar.MonExIT.model;

public class Valuta {
	private String valuta;

	public String getValuta() {
		return valuta;
	}

	public void setValuta(String valuta) {
		this.valuta = valuta;
	}

	public Valuta(String valuta) {
		super();
		this.valuta = valuta;
	}

	@Override
	public String toString() {
		return valuta;
	}
	
	
		
}
