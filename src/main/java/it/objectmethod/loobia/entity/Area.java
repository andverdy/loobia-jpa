package it.objectmethod.loobia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zona")
public class Area {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name = "id")
	private Integer id;
	@Column(name = "codzona")
	private String codzona;
	@Column(name = "idagente")
	private Integer idagente;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodzona() {
		return codzona;
	}

	public void setCodzona(String codzona) {
		this.codzona = codzona;
	}

	public Integer getIdagente() {
		return idagente;
	}

	public void setIdagente(Integer idagente) {
		this.idagente = idagente;
	}

}
