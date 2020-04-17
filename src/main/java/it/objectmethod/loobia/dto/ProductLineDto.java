package it.objectmethod.loobia.dto;

public class ProductLineDto {

	private Long id;
	private String codice;
	private String descrizione;
	private Long idLineaProdottoPadre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Long getIdLineaProdottoPadre() {
		return idLineaProdottoPadre;
	}

	public void setIdLineaProdottoPadre(Long idLineaProdottoPadre) {
		this.idLineaProdottoPadre = idLineaProdottoPadre;
	}

}
