package it.objectmethod.loobia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comune")
public class Municipality {

	@Id
	@Column(name = "istat")
	private Integer istat;
	@Column(name = "nome")
	private String nome;
	@Column(name = "provincia")
	private String provincia;
	@Column(name = "regione")
	private String regione;
	@Column(name = "prefisso")
	private Integer prefisso;
	@Column(name = "cap")
	private String cap;
	@Column(name = "cod_fisco")
	private String cod_fisco;
	@Column(name = "abitanti")
	private Integer abitanti;
	@Column(name = "link")
	private String link;

	public Integer getIstat() {
		return istat;
	}

	public void setIstat(Integer istat) {
		this.istat = istat;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getRegione() {
		return regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
	}

	public Integer getPrefisso() {
		return prefisso;
	}

	public void setPrefisso(Integer prefisso) {
		this.prefisso = prefisso;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCod_fisco() {
		return cod_fisco;
	}

	public void setCod_fisco(String cod_fisco) {
		this.cod_fisco = cod_fisco;
	}

	public Integer getAbitanti() {
		return abitanti;
	}

	public void setAbitanti(Integer abitanti) {
		this.abitanti = abitanti;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
