package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "dendi_kartukredit")
public class KartuKredit {
	@Id
	@Column(name = "id_kartukredit")
	private String idKartuKredit;
	
	@Column(name = "id_nasabah")
	private String idNasabah;
	
	@Column(name = "no_kartukredit")
	private String noKartuKredit;
	@Column(name = "status")
	private String status;
	
//	@OneToMany(mappedBy = "kartuKredit")
//	List<TransaksiKartuKredit> listTransaksiKartuKredit;
	public KartuKredit() {
		super();
	}

public KartuKredit(String idKartuKredit, String idNasabah, String noKartuKredit, String status) {
	super();
	this.idKartuKredit = idKartuKredit;
	this.idNasabah = idNasabah;
	this.noKartuKredit = noKartuKredit;
	this.status = status;
}

public String getIdKartuKredit() {
	return idKartuKredit;
}

public void setIdKartuKredit(String idKartuKredit) {
	this.idKartuKredit = idKartuKredit;
}

public String getIdNasabah() {
	return idNasabah;
}

public void setIdNasabah(String idNasabah) {
	this.idNasabah = idNasabah;
}

public String getNoKartuKredit() {
	return noKartuKredit;
}

public void setNoKartuKredit(String noKartuKredit) {
	this.noKartuKredit = noKartuKredit;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}
	
	
	
}
