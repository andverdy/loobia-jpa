package it.objectmethod.loobia.entity;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "copia_commissione")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "data")
	private Date data;

	@ManyToOne()
	@JoinColumn(name = "id_cliente")
	private Customer customerOrder;

	@Column(name = "note")
	private String note;
	@Column(name = "stato")
	private Byte stato;

	@ManyToOne()
	@JoinColumn(name = "id_condizioni_pagamento")
	private PaymentConditions paymentConditions;

	@Column(name = "numero")
	private Integer numero;
	@Column(name = "mezzo_consegna_vettore")
	private Byte mezzoConsegnaVettore;
	@Column(name = "id_indirizzi_cliente")
	private Integer idIndirizziCliente;
	@Column(name = "id_agente")
	private Long idAgente;
	@Column(name = "ragione_sociale_cliente")
	private String ragioneSocialeCliente;
	@Column(name = "esportato")
	private String esportato;
	@Column(name = "evaso")
	private Byte evaso;
	@Column(name = "importo_tot")
	private Float importoTot;
	@Column(name = "importo_tot_scontato")
	private Float importoTotScontato;
	@Column(name = "spesa_incasso")
	private Float spesaIncasso;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "order")
	private List<OrderDetails> detailOrders;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Customer getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(Customer customerOrder) {
		this.customerOrder = customerOrder;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Byte getStato() {
		return stato;
	}

	public void setStato(Byte stato) {
		this.stato = stato;
	}

	public PaymentConditions getPaymentConditions() {
		return paymentConditions;
	}

	public void setPaymentConditions(PaymentConditions paymentConditions) {
		this.paymentConditions = paymentConditions;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Byte getMezzoConsegnaVettore() {
		return mezzoConsegnaVettore;
	}

	public void setMezzoConsegnaVettore(Byte mezzoConsegnaVettore) {
		this.mezzoConsegnaVettore = mezzoConsegnaVettore;
	}

	public Integer getIdIndirizziCliente() {
		return idIndirizziCliente;
	}

	public void setIdIndirizziCliente(Integer idIndirizziCliente) {
		this.idIndirizziCliente = idIndirizziCliente;
	}

	public Long getIdAgente() {
		return idAgente;
	}

	public void setIdAgente(Long idAgente) {
		this.idAgente = idAgente;
	}

	public String getRagioneSocialeCliente() {
		return ragioneSocialeCliente;
	}

	public void setRagioneSocialeCliente(String ragioneSocialeCliente) {
		this.ragioneSocialeCliente = ragioneSocialeCliente;
	}

	public String getEsportato() {
		return esportato;
	}

	public void setEsportato(String esportato) {
		this.esportato = esportato;
	}

	public Byte getEvaso() {
		return evaso;
	}

	public void setEvaso(Byte evaso) {
		this.evaso = evaso;
	}

	public Float getImportoTot() {
		return importoTot;
	}

	public void setImportoTot(Float importoTot) {
		this.importoTot = importoTot;
	}

	public Float getImportoTotScontato() {
		return importoTotScontato;
	}

	public void setImportoTotScontato(Float importoTotScontato) {
		this.importoTotScontato = importoTotScontato;
	}

	public Float getSpesaIncasso() {
		return spesaIncasso;
	}

	public void setSpesaIncasso(Float spesaIncasso) {
		this.spesaIncasso = spesaIncasso;
	}

	public List<OrderDetails> getDetailOrders() {
		return detailOrders;
	}

	public void setDetailOrders(List<OrderDetails> detailOrders) {
		this.detailOrders = detailOrders;
	}

}
