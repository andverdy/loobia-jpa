package it.objectmethod.loobia.dto;

public class OrderDetailsDto {

	private Integer id;
	private Integer sconto;
	private Integer totPezzi;
	private Long idProdotto;
	private Float prezzoSingolo;
	private Double importo;
	private Double importoScontato;
	private Integer evaso;
	private Integer pezziInviati;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSconto() {
		return sconto;
	}

	public void setSconto(Integer sconto) {
		this.sconto = sconto;
	}

	public Integer getTotPezzi() {
		return totPezzi;
	}

	public void setTotPezzi(Integer totPezzi) {
		this.totPezzi = totPezzi;
	}

	public Long getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(Long idProdotto) {
		this.idProdotto = idProdotto;
	}

	public Float getPrezzoSingolo() {
		return prezzoSingolo;
	}

	public void setPrezzoSingolo(Float prezzoSingolo) {
		this.prezzoSingolo = prezzoSingolo;
	}

	public Double getImporto() {
		return importo;
	}

	public void setImporto(Double importo) {
		this.importo = importo;
	}

	public Double getImportoScontato() {
		return importoScontato;
	}

	public void setImportoScontato(Double importoScontato) {
		this.importoScontato = importoScontato;
	}

	public Integer getEvaso() {
		return evaso;
	}

	public void setEvaso(Integer evaso) {
		this.evaso = evaso;
	}

	public Integer getPezziInviati() {
		return pezziInviati;
	}

	public void setPezziInviati(Integer pezziInviati) {
		this.pezziInviati = pezziInviati;
	}

}
