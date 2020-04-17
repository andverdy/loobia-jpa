package it.objectmethod.loobia.dto;

public class PaymentConditionsDto {

	private Long id;
	private String descrizione;
	private String codice;
	private Float spesa;
	private boolean attivo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Float getSpesa() {
		return spesa;
	}

	public void setSpesa(Float spesa) {
		this.spesa = spesa;
	}

	public boolean isAttivo() {
		return attivo;
	}

	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}

}
