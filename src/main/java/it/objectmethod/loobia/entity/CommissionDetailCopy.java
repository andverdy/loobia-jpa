package it.objectmethod.loobia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "copia_commissione_dettaglio")
public class CommissionDetailCopy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "sconto")
	private Integer sconto;
	@Column(name = "tot_pezzi")
	private Integer totPezzi;
	@Column(name = "id_prodotto")
	private Integer idProdotto;
	@Column(name = "prezzo_singolo")
	private Float prezzoSingolo;
	@Column(name = "importo")
	private Double importo;
	@Column(name = "importo_scontato")
	private Double importoScontato;
	@Column(name = "evaso")
	private Integer evaso;
	@Column(name = "pezzi_inviati")
	private Integer pezziInviati;

	@JsonBackReference
	@ManyToOne()
	@JoinColumn(name = "id_copia_commissione")
	private CommissionCopy commissionCopy;

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

	public Integer getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(Integer idProdotto) {
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

	public CommissionCopy getCommissionCopy() {
		return commissionCopy;
	}

	public void setCommissionCopy(CommissionCopy commissionCopy) {
		this.commissionCopy = commissionCopy;
	}

}
