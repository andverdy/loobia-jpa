package it.objectmethod.loobia.entity;

import java.sql.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "prodotto")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "attivo")
	private String attivo;
	@Column(name = "barcode")
	private String barcode;
	@Column(name = "barcode_cf_ct")
	private String barcodeCfCt;
	@Column(name = "codice")
	private String codice;
	@Column(name = "data_creazione")
	private Date dataCreazione;
	@Column(name = "data_ultima_modifica")
	private Date dataUltimaModifica;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "descrizione_inglese")
	private String descrizioneInglese;
	@Column(name = "erogatore")
	private String erogatore;
	@Column(name = "esportato")
	private String esportato;
	@Column(name = "formato")
	private String formato;
	@Column(name = "fornitore")
	private String fornitore;
	@Column(name = "giacenza")
	private String giacenza;

	@ManyToOne()
	@JoinColumn(name = "id_tipo_prod")
	private ProductType productType;

	@Column(name = "immagine")
	private String immagine;
	@Column(name = "linea")
	private String linea;
	@Column(name = "linea_inglese")
	private String lineaInglese;
	@Column(name = "molteplicita")
	private String molteplicita;
	@Column(name = "molteplicita_cf_ct")
	private String molteplicitaCfCt;
	@Column(name = "numerazione")
	private String numerazione;
	@Column(name = "omaggio")
	private String omaggio;
	@Column(name = "peso_lordo")
	private String pesoLordo;
	@Column(name = "peso_lordo_cf_ct")
	private String pesoLordoCfCt;
	@Column(name = "prezzo")
	private Double prezzo;
	@Column(name = "volume")
	private String volume;
	@Column(name = "esportato_pbb")
	private String esportatoPbb;
	@Column(name = "descrizione_sito")
	private String descrizioneSito;
	@Column(name = "modo_uso")
	private String modoUso;
	@Column(name = "web")
	private Byte web;
	@Column(name = "id_linea_prodotto")
	private Long idLineaProdotto;
	@Column(name = "volume_prodotto")
	private String volumeProdotto;
	@Column(name = "descrizione_breve")
	private String descrizioneBreve;
	@Column(name = "path_brochure")
	private String pathBrochure;

	@OneToMany(mappedBy = "product")
	private List<OrderDetails> detailOrders;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAttivo() {
		return attivo;
	}

	public void setAttivo(String attivo) {
		this.attivo = attivo;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getBarcodeCfCt() {
		return barcodeCfCt;
	}

	public void setBarcodeCfCt(String barcodeCfCt) {
		this.barcodeCfCt = barcodeCfCt;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Date getDataUltimaModifica() {
		return dataUltimaModifica;
	}

	public void setDataUltimaModifica(Date dataUltimaModifica) {
		this.dataUltimaModifica = dataUltimaModifica;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDescrizioneInglese() {
		return descrizioneInglese;
	}

	public void setDescrizioneInglese(String descrizioneInglese) {
		this.descrizioneInglese = descrizioneInglese;
	}

	public String getErogatore() {
		return erogatore;
	}

	public void setErogatore(String erogatore) {
		this.erogatore = erogatore;
	}

	public String getEsportato() {
		return esportato;
	}

	public void setEsportato(String esportato) {
		this.esportato = esportato;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getFornitore() {
		return fornitore;
	}

	public void setFornitore(String fornitore) {
		this.fornitore = fornitore;
	}

	public String getGiacenza() {
		return giacenza;
	}

	public void setGiacenza(String giacenza) {
		this.giacenza = giacenza;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public String getLineaInglese() {
		return lineaInglese;
	}

	public void setLineaInglese(String lineaInglese) {
		this.lineaInglese = lineaInglese;
	}

	public String getMolteplicita() {
		return molteplicita;
	}

	public void setMolteplicita(String molteplicita) {
		this.molteplicita = molteplicita;
	}

	public String getMolteplicitaCfCt() {
		return molteplicitaCfCt;
	}

	public void setMolteplicitaCfCt(String molteplicitaCfCt) {
		this.molteplicitaCfCt = molteplicitaCfCt;
	}

	public String getNumerazione() {
		return numerazione;
	}

	public void setNumerazione(String numerazione) {
		this.numerazione = numerazione;
	}

	public String getOmaggio() {
		return omaggio;
	}

	public void setOmaggio(String omaggio) {
		this.omaggio = omaggio;
	}

	public String getPesoLordo() {
		return pesoLordo;
	}

	public void setPesoLordo(String pesoLordo) {
		this.pesoLordo = pesoLordo;
	}

	public String getPesoLordoCfCt() {
		return pesoLordoCfCt;
	}

	public void setPesoLordoCfCt(String pesoLordoCfCt) {
		this.pesoLordoCfCt = pesoLordoCfCt;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getEsportatoPbb() {
		return esportatoPbb;
	}

	public void setEsportatoPbb(String esportatoPbb) {
		this.esportatoPbb = esportatoPbb;
	}

	public String getDescrizioneSito() {
		return descrizioneSito;
	}

	public void setDescrizioneSito(String descrizioneSito) {
		this.descrizioneSito = descrizioneSito;
	}

	public String getModoUso() {
		return modoUso;
	}

	public void setModoUso(String modoUso) {
		this.modoUso = modoUso;
	}

	public Byte getWeb() {
		return web;
	}

	public void setWeb(Byte web) {
		this.web = web;
	}

	public Long getIdLineaProdotto() {
		return idLineaProdotto;
	}

	public void setIdLineaProdotto(Long idLineaProdotto) {
		this.idLineaProdotto = idLineaProdotto;
	}

	public String getVolumeProdotto() {
		return volumeProdotto;
	}

	public void setVolumeProdotto(String volumeProdotto) {
		this.volumeProdotto = volumeProdotto;
	}

	public String getDescrizioneBreve() {
		return descrizioneBreve;
	}

	public void setDescrizioneBreve(String descrizioneBreve) {
		this.descrizioneBreve = descrizioneBreve;
	}

	public String getPathBrochure() {
		return pathBrochure;
	}

	public void setPathBrochure(String pathBrochure) {
		this.pathBrochure = pathBrochure;
	}

	public List<OrderDetails> getDetailOrders() {
		return detailOrders;
	}

	public void setDetailOrders(List<OrderDetails> detailOrders) {
		this.detailOrders = detailOrders;
	}

}
