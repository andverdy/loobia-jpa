package it.objectmethod.loobia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "indirizzi_cliente")
public class CustomerAddresses {

	@Id
	private Integer id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Customer customer;

	@Column(name = "indirizzo")
	private String indirizzo;
	@Column(name = "citta")
	private String citta;
	@Column(name = "cap")
	private String cap;
	@Column(name = "provincia")
	private String provincia;
	@Column(name = "nazione")
	private String nazione;
	@Column(name = "fatturazione")
	private Boolean fatturazione;
	@Column(name = "destinatario")
	private String destinatario;
	@Column(name = "attivo")
	private Boolean attivo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getNazione() {
		return nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	public Boolean getFatturazione() {
		return fatturazione;
	}

	public void setFatturazione(Boolean fatturazione) {
		this.fatturazione = fatturazione;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public Boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}

}
