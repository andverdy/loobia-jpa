package it.objectmethod.loobia.dto;

import java.util.Date;
import java.util.List;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


public class OrderDto {

	private Integer id;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date data;
	private Integer idCliente;
	private String note;
	private Byte stato;
	private Long idCondizioniPagamento;
	private Integer numero;
	private Byte mezzoConsegnaVettore;
	private Integer idIndirizziCliente;
	private Long idAgente;
	private String ragioneSocialeCliente;
	private String esportato;
	private Byte evaso;
	private Float importoTot;
	private Float importoTotScontato;
	private Float spesaIncasso;
	private List<OrderDetailsDto> detailOrdersDto;

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

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
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

	public Long getIdCondizioniPagamento() {
		return idCondizioniPagamento;
	}

	public void setIdCondizioniPagamento(Long idCondizioniPagamento) {
		this.idCondizioniPagamento = idCondizioniPagamento;
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

	public List<OrderDetailsDto> getDetailOrdersDto() {
		return detailOrdersDto;
	}

	public void setDetailOrdersDto(List<OrderDetailsDto> detailOrdersDto) {
		this.detailOrdersDto = detailOrdersDto;
	}

}
